//package net.dustley.enlighten.block.utility.temporal_dusk_lock
//
//import com.mojang.serialization.MapCodec
//import net.dustley.enlighten.Enlighten
//import net.dustley.enlighten.block.ModBlockEntities
//import net.minecraft.block.Block
//import net.minecraft.block.BlockRenderType
//import net.minecraft.block.BlockState
//import net.minecraft.block.BlockWithEntity
//import net.minecraft.block.wish_contract.BlockEntity
//import net.minecraft.block.wish_contract.BlockEntityTicker
//import net.minecraft.block.wish_contract.BlockEntityType
//import net.minecraft.wish_contract.LivingEntity
//import net.minecraft.wish_contract.player.PlayerEntity
//import net.minecraft.item.ItemStack
//import net.minecraft.item.Items
//import net.minecraft.sound.BlockSoundGroup
//import net.minecraft.state.StateManager
//import net.minecraft.state.property.BooleanProperty
//import net.minecraft.state.property.Properties
//import net.minecraft.util.Hand
//import net.minecraft.util.ItemActionResult
//import net.minecraft.util.hit.BlockHitResult
//import net.minecraft.util.math.BlockPos
//import net.minecraft.world.World
//
//
//class TemporalDuskLockBlock : BlockWithEntity(Settings.create().strength(-1f, 3600000f).nonOpaque().luminance { 10 }.requiresTool().sounds(BlockSoundGroup.BONE)) {
//
//    init {
//        this.defaultState = (stateManager.defaultState as BlockState).with(POWERED, false) as BlockState
//    }
//
//    // REGISTER
//    override fun appendProperties(builder: StateManager.Builder<Block, BlockState>) { builder.add(POWERED) }
//    override fun getCodec(): MapCodec<out BlockWithEntity> = createCodec{ TemporalDuskLockBlock() }
//    override fun createBlockEntity(pos: BlockPos, state: BlockState): BlockEntity = TemporalDuskLockBlockEntity(pos, state)
//    override fun onPlaced(world: World?, pos: BlockPos?, state: BlockState?, placer: LivingEntity?, itemStack: ItemStack?) {
//        Enlighten.LOGGER.info("Place: ${world?.isClient}")
//        (world?.getBlockEntity(pos) as TemporalDuskLockBlockEntity).onPlace()
//        super.onPlaced(world, pos, state, placer, itemStack)
//    }
//    override fun onUseWithItem(stack: ItemStack, state: BlockState, world: World, pos: BlockPos, player: PlayerEntity, hand: Hand, hit: BlockHitResult): ItemActionResult {
//        if(stack.item == Items.ECHO_SHARD && !state.get(POWERED)) {
//            world.setBlockState(pos, state.withIfExists(POWERED, true))
//            (world.getBlockEntity(pos) as TemporalDuskLockBlockEntity).onUse()
//            if(!player.isInCreativeMode) stack.decrement(1)
//        }
//        return super.onUseWithItem(stack, state, world, pos, player, hand, hit)
//    }
//    override fun onBreak(world: World?, pos: BlockPos?, state: BlockState?, player: PlayerEntity?): BlockState {
//        Enlighten.LOGGER.info("Break: ${world?.isClient}")
//        (world?.getBlockEntity(pos) as TemporalDuskLockBlockEntity).onBreak()
//        return super.onBreak(world, pos, state, player)
//    }
//
//    // RENDERER
//    override fun getRenderType(state: BlockState?): BlockRenderType = BlockRenderType.MODEL
//
//    override fun <T : BlockEntity?> getTicker(
//        world: World,
//        state: BlockState,
//        type: BlockEntityType<T>,
//    ): BlockEntityTicker<T>? {
//
//        return validateTicker(type, ModBlockEntities.TEMPORAL_DUSK_LOCK) { _: World, _: BlockPos, _: BlockState, temporalDuskLockBlockEntity: TemporalDuskLockBlockEntity -> run { temporalDuskLockBlockEntity.tick() } }
//    }
//
//    companion object {
//        val POWERED: BooleanProperty = Properties.POWERED
//    }
//}