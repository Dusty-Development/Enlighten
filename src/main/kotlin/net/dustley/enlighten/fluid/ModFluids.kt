package net.dustley.enlighten.fluid

import net.dustley.enlighten.Enlighten
import net.fabricmc.fabric.api.client.render.fluid.v1.FluidRenderHandlerRegistry
import net.fabricmc.fabric.api.client.render.fluid.v1.SimpleFluidRenderHandler
import net.minecraft.fluid.FlowableFluid
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry


object ModFluids {

    val BEGRIMED_MIRE_STILL: FlowableFluid = register("begrimed_mire_still", BegrimedMireFluid.Still())
    val BEGRIMED_MIRE_FLOWING: FlowableFluid = register("begrimed_mire_flowing", BegrimedMireFluid.Flowing())

    private fun register(name: String, flowableFluid: FlowableFluid): FlowableFluid {
        return Registry.register(Registries.FLUID, Enlighten.identifier(name), flowableFluid)
    }

    fun registerModFluidRenderers() {

        // BEGRIMED MIRE
        FluidRenderHandlerRegistry.INSTANCE.register(
            BEGRIMED_MIRE_STILL,
            SimpleFluidRenderHandler(
                BegrimedMireFluid.TEX_STILL,
                BegrimedMireFluid.TEX_FLOWING,
                BegrimedMireFluid.TEX_OVERLAY,
                -1
            )
        )
        FluidRenderHandlerRegistry.INSTANCE.register(
            BEGRIMED_MIRE_FLOWING,
            SimpleFluidRenderHandler(
                BegrimedMireFluid.TEX_STILL,
                BegrimedMireFluid.TEX_FLOWING,
                BegrimedMireFluid.TEX_OVERLAY,
                -1
            )
        )
    }
}