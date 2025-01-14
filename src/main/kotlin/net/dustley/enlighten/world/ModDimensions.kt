package net.dustley.enlighten.world

import net.dustley.enlighten.Enlighten
import net.minecraft.registry.RegistryKey
import net.minecraft.registry.RegistryKeys
import net.minecraft.world.World
import net.minecraft.world.dimension.DimensionOptions
import net.minecraft.world.dimension.DimensionType


object ModDimensions {

    // The dimension options refer to the JSON-file in the dimension subfolder of the datapack,
    // which will always share it's ID with the world that is created from it
    val DIMENSION_KEY: RegistryKey<DimensionOptions> = RegistryKey.of(
        RegistryKeys.DIMENSION,
        Enlighten.identifier("whispering_abyss")
    )

    var WORLD_KEY: RegistryKey<World> = RegistryKey.of(
        RegistryKeys.WORLD,
        DIMENSION_KEY.value
    )

    val DIMENSION_TYPE_KEY: RegistryKey<DimensionType> = RegistryKey.of(
        RegistryKeys.DIMENSION_TYPE,
        Enlighten.identifier("whispering_abyss_type")
    )

    fun registerModDimensions() {

    }
}