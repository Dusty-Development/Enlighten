package net.dustley.enlighten.item

import net.dustley.enlighten.Enlighten
import net.minecraft.item.ArmorItem
import net.minecraft.item.ArmorMaterial
import net.minecraft.recipe.Ingredient
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.registry.entry.RegistryEntry
import net.minecraft.sound.SoundEvent
import net.minecraft.sound.SoundEvents
import java.util.function.Supplier


object ModArmorMaterials {

    val MIRE_CLOAK_MAT = registerMaterial("mire_cloak", java.util.Map.of(
        ArmorItem.Type.HELMET, 2,
        ArmorItem.Type.CHESTPLATE, 7,
        ArmorItem.Type.LEGGINGS, 0,
        ArmorItem.Type.BOOTS, 0),
        15,
        SoundEvents.ITEM_ARMOR_EQUIP_LEATHER,
        { Ingredient.ofItems(ModItems.BEGRIMED_MIRE_CLOTH) },
        2.5f,
        0.0f, false)

    private fun registerMaterial(id: String, defensePoints: Map<ArmorItem.Type?, Int?>, enchantability: Int, equipSound: RegistryEntry<SoundEvent?>, repairIngredientSupplier: Supplier<Ingredient?>, toughness: Float, knockbackResistance: Float, dyeable: Boolean): RegistryEntry<ArmorMaterial> {
        // Get the supported layers for the armor material
        val layers = listOf(ArmorMaterial.Layer(Enlighten.identifier(id), "", dyeable))
        var material = ArmorMaterial(
            defensePoints,
            enchantability,
            equipSound,
            repairIngredientSupplier,
            layers,
            toughness,
            knockbackResistance
        )

        material = Registry.register(Registries.ARMOR_MATERIAL, Enlighten.identifier(id), material)
        return RegistryEntry.of(material)
    }

    fun registerModArmorMaterials() {

    }
}