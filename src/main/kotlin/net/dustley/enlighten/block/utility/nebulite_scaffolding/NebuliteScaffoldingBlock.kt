package net.dustley.enlighten.block.utility.nebulite_scaffolding

import net.dustley.enlighten.block.ModBlocks
import net.minecraft.block.BlockState
import net.minecraft.block.ScaffoldingBlock
import net.minecraft.block.Waterloggable
import net.minecraft.entity.FallingBlockEntity
import net.minecraft.server.world.ServerWorld
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Direction
import net.minecraft.util.math.random.Random
import net.minecraft.world.BlockView

class NebuliteScaffoldingBlock(settings: Settings) : ScaffoldingBlock(settings), Waterloggable {

    override fun scheduledTick(state: BlockState, world: ServerWorld, pos: BlockPos, random: Random?) {
        val i = calcDistance(world, pos)
        val blockState = (state.with(DISTANCE, i) as BlockState).with(BOTTOM, this.shouldBeBot(world, pos, i)) as BlockState
        if (blockState.get(DISTANCE) as Int == MAX_DISTANCE) {
            if (state.get(DISTANCE) as Int == MAX_DISTANCE) {
                FallingBlockEntity.spawnFromBlock(world, pos, blockState)
            } else {
                world.breakBlock(pos, true)
            }
        } else if (state !== blockState) {
            world.setBlockState(pos, blockState, 3)
        }
    }

    private fun shouldBeBot(world: BlockView, pos: BlockPos, distance: Int): Boolean {
        return distance > 0 && !world.getBlockState(pos.down()).isOf(this)
    }

    private fun calcDistance(world: BlockView, pos: BlockPos): Int {
        val mutable = pos.mutableCopy().move(Direction.DOWN)
        val bottomBlockState = world.getBlockState(mutable)

        if (bottomBlockState.isSideSolidFullSquare(world, mutable, Direction.UP)) {
            return 0
        }

        val horizontalIterator: Iterator<*> = Direction.Type.HORIZONTAL.iterator()

        var shouldBreak = true
        while (horizontalIterator.hasNext()) {
            val direction = horizontalIterator.next() as Direction
            val blockState2 = world.getBlockState(mutable.set(pos, direction))
            if (blockState2.isOf(ModBlocks.NEBULITE_SCAFFOLDING)) {
                shouldBreak = false
                break
            }
        }

        return if(!shouldBreak) 1 else MAX_DISTANCE

    }

}