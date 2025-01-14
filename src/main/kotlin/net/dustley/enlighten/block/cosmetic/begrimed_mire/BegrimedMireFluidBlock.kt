package net.dustley.enlighten.block.cosmetic.begrimed_mire

import net.minecraft.block.Blocks
import net.minecraft.block.FluidBlock
import net.minecraft.fluid.FlowableFluid

class BegrimedMireFluidBlock(fluid: FlowableFluid) : FluidBlock(fluid, Settings.copy(Blocks.WATER).noCollision().nonOpaque().dropsNothing()) {
}