package net.dustley.enlighten.item

import com.mojang.serialization.Codec
import net.dustley.enlighten.Enlighten
import net.minecraft.component.ComponentType
import net.minecraft.item.ItemStack
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import java.util.function.UnaryOperator


object ModItemComponents {

    val CHARGE_COUNT = register("charge_count" ) { builder: ComponentType.Builder<Int> -> builder.codec( Codec.INT ) }
    val ENTITY_ID = register("entity_id" ) { builder: ComponentType.Builder<Int> -> builder.codec( Codec.INT ) }

    val CANNON_STACK: ComponentType<ItemStack> = Registry.register(
        Registries.DATA_COMPONENT_TYPE, Enlighten.identifier("cannon_stack"),
        ComponentType.builder<ItemStack>().codec(ItemStack.CODEC).build()
    )
    val IS_CANNON_FIRING = register("is_cannon_firing") { builder: ComponentType.Builder<Boolean> -> builder.codec( Codec.BOOL ) }
    val CANNON_FIRING_TICKS = register("cannon_firing_ticks") { builder: ComponentType.Builder<Int> -> builder.codec( Codec.INT ) }

    private fun <T> register(name: String, builderOperator: UnaryOperator<ComponentType.Builder<T>>): ComponentType<T> {
        return Registry.register(
            Registries.DATA_COMPONENT_TYPE, Enlighten.identifier(name),
            builderOperator.apply(ComponentType.builder()).build()
        )
    }

    fun registerDataComponentTypes() {
        Enlighten.LOGGER.info("Registering Data Component Types for " + Enlighten.MOD_ID)
    }

}