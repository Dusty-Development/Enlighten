package net.dustley.enlighten.item.tool.snare_stone

import net.dustley.enlighten.item.ModItemComponents
import net.dustley.enlighten.network.s2c.SnareStoneClientPacketPayload
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking
import net.minecraft.entity.Entity
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.server.network.ServerPlayerEntity
import net.minecraft.util.Hand
import net.minecraft.util.Rarity
import net.minecraft.util.TypedActionResult
import net.minecraft.util.math.Box
import net.minecraft.world.World

class SnareStoneItem : Item(createItemSettings()) {

    override fun use(world: World, user: PlayerEntity, hand: Hand): TypedActionResult<ItemStack> {
        if(!world.isClient) {
            val snatcherID = user.id
            val snatchedEntity = getClosestEntity(user, 5.0)
            val snatchedID = snatchedEntity?.id

            if(snatchedID != null) {
                for (player in world.players) {
                    ServerPlayNetworking.send(player as ServerPlayerEntity?, SnareStoneClientPacketPayload(snatchedID, snatcherID, user.getStackInHand(hand)))
                    setSnaredEntityID(user.getStackInHand(hand), snatchedID)
                }
            }
        }

        return super.use(world, user, hand)
    }

    override fun inventoryTick(stack: ItemStack, world: World, entity: Entity, slot: Int, selected: Boolean) {
        super.inventoryTick(stack, world, entity, slot, selected)
        if(!(entity as PlayerEntity).isHolding(stack.item)) setSnaredEntityID(stack, -1)
    }

    fun getClosestEntity(player: PlayerEntity, radius: Double): Entity? {
        val playerPos = player.pos
        val searchBox = Box(playerPos.subtract(radius, radius, radius), playerPos.add(radius, radius, radius))
        return player.world.getEntitiesByClass(
            Entity::class.java, searchBox
        ) { e: Entity -> e !== player }.stream()
            .min { e1: Entity?, e2: Entity? ->
                e1!!.squaredDistanceTo(player).compareTo(e2!!.squaredDistanceTo(player))
            }
            .orElse(null)
    }


    companion object {
        const val MAX_DISTANCE = 25

        // Charge count
        fun getSnaredEntityID(stack: ItemStack): Int = stack.get(ModItemComponents.ENTITY_ID) ?: -1
        fun setSnaredEntityID(stack: ItemStack, value: Int) = stack.set(ModItemComponents.ENTITY_ID, value)

        fun createItemSettings(): Settings = Settings()
            .component(ModItemComponents.ENTITY_ID, -1)
            .rarity(Rarity.UNCOMMON)
            .maxDamage(1024)
    }
}