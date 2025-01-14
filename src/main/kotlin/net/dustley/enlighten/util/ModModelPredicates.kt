package net.dustley.enlighten.util

import net.dustley.enlighten.Enlighten
import net.dustley.enlighten.item.ModItemComponents
import net.dustley.enlighten.item.ModItems
import net.dustley.enlighten.item.tool.snatch_slate.SnatchSlateItem
import net.minecraft.client.item.ModelPredicateProviderRegistry
import net.minecraft.client.world.ClientWorld
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.ItemStack


object ModModelPredicates {
    fun registerModelPredicates() {

        ModelPredicateProviderRegistry.register(
            ModItems.VOLUMETRIC_ANCHOR, Enlighten.identifier("charge_count")
        ) { stack: ItemStack, world: ClientWorld?, entity: LivingEntity?, seed: Int ->
            when (stack.get(ModItemComponents.CHARGE_COUNT)) {
                0 -> 0F
                1 -> 0.25F
                2 -> 0.5F
                3 -> 0.75F
                4 -> 1F
                else -> 1F
            }
        }

        ModelPredicateProviderRegistry.register(
            ModItems.SNARE_STONE, Enlighten.identifier("entity_id")
        ) { stack: ItemStack, world: ClientWorld?, entity: LivingEntity?, seed: Int ->
            when (stack.get(ModItemComponents.ENTITY_ID)) {
                -1 -> 0F
                else -> 1F
            }
        }

        ModelPredicateProviderRegistry.register(
            ModItems.SNATCH_SLATE, Enlighten.identifier("can_activate")
        ) { stack: ItemStack, world: ClientWorld?, entity: LivingEntity?, seed: Int ->
            if(entity is PlayerEntity) {
                val Snatchable = SnatchSlateItem.getClosestPlayerEntity(entity)
                if (Snatchable != null) {
                    return@register 1F
                }
            }
            return@register 0F
        }
    }
}