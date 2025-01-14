package net.dustley.enlighten.fluid

import net.dustley.enlighten.Enlighten
import net.dustley.enlighten.block.ModBlocks
import net.dustley.enlighten.item.ModItems
import net.dustley.enlighten.particle.ModParticles
import net.minecraft.block.Block
import net.minecraft.block.BlockState
import net.minecraft.block.FluidBlock
import net.minecraft.fluid.FlowableFluid
import net.minecraft.fluid.Fluid
import net.minecraft.fluid.FluidState
import net.minecraft.item.Item
import net.minecraft.particle.ParticleEffect
import net.minecraft.particle.ParticleTypes
import net.minecraft.registry.tag.FluidTags
import net.minecraft.sound.SoundCategory
import net.minecraft.sound.SoundEvent
import net.minecraft.sound.SoundEvents
import net.minecraft.state.StateManager
import net.minecraft.util.Identifier
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Direction
import net.minecraft.world.BlockView
import net.minecraft.world.World
import net.minecraft.world.WorldAccess
import net.minecraft.world.WorldView
import java.util.*


abstract class BegrimedMireFluid : FlowableFluid() {

    override fun getFlowing(): Fluid = ModFluids.BEGRIMED_MIRE_FLOWING
    override fun getStill(): Fluid = ModFluids.BEGRIMED_MIRE_STILL
    override fun getBucketItem(): Item = ModItems.BEGRIMED_MIRE_BUCKET

    override fun randomDisplayTick(
        world: World,
        pos: BlockPos,
        state: FluidState,
        random: net.minecraft.util.math.random.Random,
    ) {
        if (!state.isStill && !state.get(FALLING)) {
            if (random.nextInt(64) === 0) {
                world.playSound(
                    pos.x.toDouble() + 0.5,
                    pos.y.toDouble() + 0.5, pos.z.toDouble() + 0.5,
                    SoundEvents.BLOCK_WATER_AMBIENT, SoundCategory.BLOCKS,
                    random.nextFloat() * 0.25f + 0.75f, random.nextFloat() + 0.5f,
                    false
                )
            }
        } else if (random.nextInt(10) === 0) {
            world.addParticle(
                ParticleTypes.MYCELIUM, pos.x.toDouble() + random.nextDouble(),
                pos.y.toDouble() + random.nextDouble(),
                pos.z.toDouble() + random.nextDouble(),
                0.0, 0.0, 0.0
            )
        }
    }

    public override fun getParticle(): ParticleEffect? = ModParticles.MIRE_DRIP_PARTICLE

    override fun beforeBreakingBlock(world: WorldAccess, pos: BlockPos?, state: BlockState) {
        val blockEntity = if (state.hasBlockEntity()) world.getBlockEntity(pos) else null
        Block.dropStacks(state, world, pos, blockEntity)
    }

    public override fun toBlockState(state: FluidState?): BlockState = ModBlocks.BEGRIMED_MIRE_FLUID.getDefaultState().with(FluidBlock.LEVEL, getBlockStateLevel(state))
    override fun matchesType(fluid: Fluid): Boolean = fluid === ModFluids.BEGRIMED_MIRE_STILL || fluid === ModFluids.BEGRIMED_MIRE_FLOWING

    public override fun getLevelDecreasePerBlock(world: WorldView?): Int = 2
    override fun getTickRate(world: WorldView?): Int = 10

    public override fun canBeReplacedWith(state: FluidState?, world: BlockView?, pos: BlockPos?, fluid: Fluid, direction: Direction): Boolean =
        direction == Direction.DOWN && !fluid.isIn(FluidTags.WATER)

    override fun getBlastResistance(): Float = 100.0f
    override fun getBucketFillSound(): Optional<SoundEvent> = Optional.of(SoundEvents.ITEM_BUCKET_FILL)

    companion object {
        val TEX_STILL: Identifier = Enlighten.identifier("block/begrimed_mire/begrimed_mire_still")
        val TEX_FLOWING: Identifier = Enlighten.identifier("block/begrimed_mire/begrimed_mire_flow")
        val TEX_OVERLAY: Identifier = Enlighten.identifier("block/begrimed_mire/begrimed_mire_overlay")
    }

    class Flowing : BegrimedMireFluid() {
        override fun isInfinite(world: World?): Boolean = false
        override fun isStill(state: FluidState?): Boolean = false
        override fun getMaxFlowDistance(world: WorldView?): Int = 2
        override fun getLevel(state: FluidState): Int = state.get(LEVEL) as Int
        override fun appendProperties(builder: StateManager.Builder<Fluid?, FluidState?>) {
            super.appendProperties(builder)
            builder.add(LEVEL)
        }
    }

    class Still : BegrimedMireFluid() {
        override fun isInfinite(world: World?): Boolean = false
        override fun isStill(state: FluidState?): Boolean = true
        override fun getMaxFlowDistance(world: WorldView?): Int = 2
        override fun getLevel(state: FluidState): Int = 4
        override fun appendProperties(builder: StateManager.Builder<Fluid?, FluidState?>) {
            super.appendProperties(builder)
            builder.add(LEVEL)
        }
    }
}