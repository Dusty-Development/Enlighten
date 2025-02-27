package net.dustley.enlighten.block.cosmetic.wispflare

import com.mojang.serialization.MapCodec
import net.dustley.enlighten.particle.ModParticles
import net.minecraft.block.BlockState
import net.minecraft.block.IceBlock
import net.minecraft.block.PlantBlock
import net.minecraft.block.ShapeContext
import net.minecraft.entity.Entity
import net.minecraft.entity.vehicle.BoatEntity
import net.minecraft.fluid.Fluids
import net.minecraft.particle.ParticleTypes
import net.minecraft.server.world.ServerWorld
import net.minecraft.sound.SoundCategory
import net.minecraft.sound.SoundEvents
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Vec3d
import net.minecraft.util.math.random.Random
import net.minecraft.util.shape.VoxelShape
import net.minecraft.world.BlockView
import net.minecraft.world.World

class Wispflare(settings: Settings) : PlantBlock(settings) {

    companion object {
        val CODEC: MapCodec<Wispflare> = createCodec { settings: Settings ->
            Wispflare(
                settings
            )
        }
        val SHAPE: VoxelShape = createCuboidShape(1.0, 0.0, 1.0, 15.0, 1.5, 15.0)
    }

    public override fun getCodec(): MapCodec<Wispflare> = CODEC

    override fun onEntityCollision(state: BlockState?, world: World, pos: BlockPos?, entity: Entity?) {
        super.onEntityCollision(state, world, pos, entity)
        if (world is ServerWorld && entity is BoatEntity) {
            world.breakBlock(BlockPos(pos), true, entity)
        }
    }

    override fun getOutlineShape(state: BlockState?, world: BlockView?, pos: BlockPos?, context: ShapeContext?): VoxelShape = SHAPE


    override fun canPlantOnTop(floor: BlockState, world: BlockView, pos: BlockPos): Boolean {
        val fluidState = world.getFluidState(pos)
        val fluidState2 = world.getFluidState(pos.up())
        return (fluidState.fluid === Fluids.WATER || floor.block is IceBlock) && fluidState2.fluid === Fluids.EMPTY
    }

    override fun hasRandomTicks(state: BlockState): Boolean = true
    override fun randomDisplayTick(state: BlockState, world: World, pos: BlockPos, random: Random) {
        super.randomDisplayTick(state, world, pos, random)

        val centerPos = pos.toCenterPos()
        val direction = Vec3d(1.0,1.0,1.0).rotateY(random.nextFloat() * 0.5f).normalize()
        val velHorizontal = 0.25
        val velVertical = 0.5
        val velocity = direction.multiply(velHorizontal, velVertical, velHorizontal)

        if (random.nextDouble() >= 0.75) {
            world.playSound(centerPos.x, centerPos.y, centerPos.z, SoundEvents.BLOCK_AMETHYST_BLOCK_RESONATE, SoundCategory.BLOCKS, 0.25f, 0.1f, true)

            world.addParticle(
                ModParticles.WISP_PARTICLE,
                centerPos.x,
                centerPos.y,
                centerPos.z,
                velocity.x,
                velocity.y,
                velocity.z
            )
        }
    }

}