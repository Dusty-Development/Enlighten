package net.dustley.enlighten.block

import net.dustley.enlighten.Enlighten


object ModBlockEntities {

//    val TEMPORAL_DUSK_LOCK: BlockEntityType<TemporalDuskLockBlockEntity> = Registry.register(
//        Registries.BLOCK_ENTITY_TYPE, Enlighten.identifier("temporal_dusk_lock"),
//        FabricBlockEntityTypeBuilder.create(
//            ::TemporalDuskLockBlockEntity,
//            ModBlocks.TEMPORAL_DUSK_LOCK
//        ).build()
//    )

    fun registerBlockEntities() {
        Enlighten.LOGGER.info("Registering Block Entities for " + Enlighten.MOD_ID)
    }

}