package net.dustley.enlighten

import net.dustley.enlighten.block.ModBlocks
import net.dustley.enlighten.client.ClientSetup
import net.dustley.enlighten.client.CustomModelLoadingPlugin
import net.dustley.enlighten.entity.ModEntities
import net.dustley.enlighten.event.ModEvents
import net.dustley.enlighten.fluid.ModFluids
import net.dustley.enlighten.item.ModItems
import net.dustley.enlighten.model.item.MireRoseModel
import net.dustley.enlighten.network.ModNetworking
import net.dustley.enlighten.particle.ModParticles
import net.dustley.enlighten.util.ModModelPredicates
import net.fabricmc.api.ClientModInitializer
import net.fabricmc.fabric.impl.client.model.loading.ModelLoadingPluginManager

object EnlightenClient : ClientModInitializer {
    override fun onInitializeClient() {
        ModEntities.registerClientModEntities()
//        ModParticles.registerParticles()
        ModItems.registerClientModItems()

        ClientSetup.clientSetup()
        ModelLoadingPluginManager.registerPlugin(CustomModelLoadingPlugin())
        ClientSetup.registerExtraBakedModels(CustomModelLoadingPlugin.MODELS::add)

        ModFluids.registerModFluidRenderers()
        ModBlocks.registerClientModBlocks()
        ModParticles.registerClientParticles()
        ModModelPredicates.registerModelPredicates()
        ModNetworking.registerClient()
        ModEvents.registerClientModEvents()

//        ReloadListenerRegistry.register(ResourceType.CLIENT_RESOURCES, EffekAssetLoader(), Enlighten.identifier("effeks"))
    }
}