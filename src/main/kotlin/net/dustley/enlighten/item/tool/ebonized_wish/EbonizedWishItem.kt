package net.dustley.enlighten.item.tool.ebonized_wish

import net.dustley.enlighten.EnlightenCardinalComponents.CONTRACTED_PLAYER
import net.dustley.enlighten.block.ModBlocks
import net.dustley.enlighten.network.s2c.WishKillClientPacketPayload
import net.dustley.enlighten.world.ModDimensions
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking
import net.minecraft.component.DataComponentTypes
import net.minecraft.component.type.AttributeModifierSlot
import net.minecraft.component.type.AttributeModifiersComponent
import net.minecraft.component.type.ToolComponent
import net.minecraft.entity.Entity
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.attribute.EntityAttributeModifier
import net.minecraft.entity.attribute.EntityAttributes
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.ItemStack
import net.minecraft.item.SwordItem
import net.minecraft.item.ToolMaterials
import net.minecraft.network.packet.s2c.play.PositionFlag
import net.minecraft.server.network.ServerPlayerEntity
import net.minecraft.server.world.ServerWorld
import net.minecraft.text.Style
import net.minecraft.text.Text
import net.minecraft.util.Rarity
import net.minecraft.world.World

class EbonizedWishItem : SwordItem(ToolMaterials.NETHERITE, createItemSettings()) {

    override fun postDamageEntity(stack: ItemStack, target: LivingEntity, attacker: LivingEntity) {
        val world = attacker.world

        if(target.isDead) {
            // We killed the target lock em up
            target.heal(target.maxHealth)
            if(world is ServerWorld) {
                val server = world.server
                val abyssWorld = server.getWorld(ModDimensions.WORLD_KEY)

                val flags: Set<PositionFlag> = setOf()
                if(target.world != abyssWorld) {
                    target.teleport(abyssWorld, target.x, target.y, target.z, flags, target.yaw, target.pitch)
                }

                for (player in world.players) {
                    if(player !is ServerPlayerEntity) return
                    ServerPlayNetworking.send(player, WishKillClientPacketPayload(target.pos, stack))
                }

                world.setBlockState(target.blockPos, ModBlocks.BEGRIMED_MIRE_FLUID.defaultState)
            }

        }
        super.postDamageEntity(stack, target, attacker)
    }

    override fun getName(stack: ItemStack?): Text {
        return Text.translatable(this.getTranslationKey(stack)).setStyle(Style.EMPTY.withColor(0xd696c5))
    }

    override fun getName(): Text {
        val text = super.getName().copy()
        return text.fillStyle(Style.EMPTY.withColor(0xd696c5))
    }

    override fun inventoryTick(stack: ItemStack, world: World, entity: Entity, slot: Int, selected: Boolean) {
        if(entity is PlayerEntity) {
            val contractedPlayerComponentOptional = CONTRACTED_PLAYER.maybeGet(world.scoreboard)
            if (contractedPlayerComponentOptional.isPresent) {
                val isAssigned = contractedPlayerComponentOptional.get().getIsAssigned()
                val playerUUID = contractedPlayerComponentOptional.get().getValue()

                if(isAssigned && entity.uuid != playerUUID) {
                    entity.dropStack(stack)
                    entity.inventory.removeStack(slot)
                }
            }
        }
        super.inventoryTick(stack, world, entity, slot, selected)
    }

    companion object {

        val BASE_ATTACK_DAMMAGE = 4f
        val BASE_ATTACK_SPEED = -2.6f

        val DEFAULT_MINING_SPEED = 1.0f
        val MINING_DAMAGE_PER_BLOCK = 2

        fun createItemSettings(): Settings = Settings()
            .attributeModifiers(ATTRIBUTE_MODIFIERS)
            .component(DataComponentTypes.TOOL, ToolComponent(listOf(), DEFAULT_MINING_SPEED, MINING_DAMAGE_PER_BLOCK))
            .rarity(Rarity.RARE)
            .fireproof()
            .maxDamage(2048)

        private val ATTRIBUTE_MODIFIERS: AttributeModifiersComponent = createAttributeModifiers()
        private fun createAttributeModifiers() = AttributeModifiersComponent.builder()
            .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, EntityAttributeModifier(BASE_ATTACK_DAMAGE_MODIFIER_ID, (BASE_ATTACK_DAMMAGE + ToolMaterials.NETHERITE.attackDamage).toDouble(), EntityAttributeModifier.Operation.ADD_VALUE), AttributeModifierSlot.MAINHAND )
            .add(EntityAttributes.GENERIC_ATTACK_SPEED, EntityAttributeModifier(BASE_ATTACK_SPEED_MODIFIER_ID, BASE_ATTACK_SPEED.toDouble(), EntityAttributeModifier.Operation.ADD_VALUE), AttributeModifierSlot.MAINHAND).build()
    }
}