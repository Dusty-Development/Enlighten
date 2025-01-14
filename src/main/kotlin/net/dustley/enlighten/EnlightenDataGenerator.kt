package net.dustley.enlighten

import net.dustley.enlighten.datagen.*
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator


object EnlightenDataGenerator : DataGeneratorEntrypoint {
	override fun onInitializeDataGenerator(fabricDataGenerator: FabricDataGenerator) {
		val pack = fabricDataGenerator.createPack()

		pack.addProvider(::ModBlockTagProvider)
		pack.addProvider(::ModRecipeProvider)
		pack.addProvider(::ModLootTableProvider)
		pack.addProvider(::ModModelProvider)

		ModLanguageProvider().generate()
	}
}