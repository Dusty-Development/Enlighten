package net.dustley.enlighten

import net.dustley.enlighten.block.ModBlockEntities
import net.dustley.enlighten.block.ModBlockProperties
import net.dustley.enlighten.block.ModBlocks
import net.dustley.enlighten.entity.ModEntities
import net.dustley.enlighten.event.ModEvents
import net.dustley.enlighten.item.ModArmorMaterials
import net.dustley.enlighten.item.ModItemComponents
import net.dustley.enlighten.item.ModItems
import net.dustley.enlighten.network.ModNetworking
import net.dustley.enlighten.particle.ModParticles
import net.dustley.enlighten.world.ModDimensions
import net.fabricmc.api.ModInitializer
import net.minecraft.util.Identifier
import org.slf4j.LoggerFactory


object Enlighten : ModInitializer {
	const val MOD_ID = "enlighten"
    val LOGGER = LoggerFactory.getLogger(MOD_ID)

	fun identifier(id:String) : Identifier {
		return Identifier.of(MOD_ID, id)
	}

	override fun onInitialize() {
		LOGGER.info("Initialization started for: $MOD_ID")


//		ModEnchantments.initialize()
		ModEntities.registerModEntities()
		ModBlockProperties
		ModBlocks.registerModBlocks()
		ModBlockEntities.registerBlockEntities()
		ModDimensions.registerModDimensions()
		ModEvents.registerModEvents()
		ModParticles.registerParticles()
		ModArmorMaterials.registerModArmorMaterials()
		ModItems.registerModItems()
		ModItemComponents.registerDataComponentTypes()
		ModNetworking.registerCommon()

		LOGGER.info("Initialization finished for: $MOD_ID")
	}
}