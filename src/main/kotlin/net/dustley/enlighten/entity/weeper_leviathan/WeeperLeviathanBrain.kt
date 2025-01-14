package net.dustley.enlighten.entity.weeper_leviathan

import net.minecraft.entity.LivingEntity
import net.minecraft.entity.ai.brain.Brain

object WeeperLeviathanBrain {
    
    fun create(brain: Brain<out LivingEntity>): Brain<out LivingEntity> {
//        addCoreActivities(brain)
//        addIdleActivities(brain)
//        brain.setCoreActivities(ImmutableSet.of(Activity.CORE))
//        brain.setDefaultActivity(Activity.IDLE)
//        brain.resetPossibleActivities()
        return brain
    }

    private fun addCoreActivities(brain: Brain<WeeperLeviathanEntity>) {
//        brain.setTaskList(
//            Activity.CORE,
//            0,
//            listOf(
//                FleeTask<Any?>(2.0f),
//                LookAroundTask(45, 90),
//                MoveToTargetTask(),
//                TemptationCooldownTask(MemoryModuleType.TEMPTATION_COOLDOWN_TICKS)
//            )
//        )
    }

    private fun addIdleActivities(brain: Brain<WeeperLeviathanEntity>) {
//        brain.setTaskList(
//            Activity.IDLE, ImmutableList.of(
//                Pair.of<Int, Task<LivingEntity>>(
//                    0,
//                    LookAtMobWithIntervalTask.follow(EntityType.PLAYER, 6.0f, UniformIntProvider.create(30, 60))
//                ),
//                Pair.of<Int, TemptTask>(1, TemptTask { livingEntity: LivingEntity? -> 1.25f }),
//                Pair.of<Int, CompositeTask<*>>(
//                    2, CompositeTask<Any?>(
//                        ImmutableMap.of<MemoryModuleType<WalkTarget>, MemoryModuleState>(
//                            MemoryModuleType.WALK_TARGET,
//                            MemoryModuleState.VALUE_ABSENT
//                        ),
//                        ImmutableSet.of<Any>(),
//                        CompositeTask.Order.ORDERED,
//                        CompositeTask.RunMode.TRY_ALL,
//                        ImmutableList.of<Pair<out Task<out LivingEntity?>?, Int>>(
//                            Pair.of<Task<PathAwareEntity?>?, Int>(StrollTask.createDynamicRadius(0.5f), 2),
//                            Pair.of<SingleTickTask<LivingEntity?>?, Int>(GoTowardsLookTargetTask.create(0.5f, 3), 3),
//                            Pair.of<SingleTickTask<LivingEntity?>?, Int>(
//                                TaskTriggerer.predicate<LivingEntity?> { obj: LivingEntity? -> obj!!.isInsideWaterOrBubbleColumn },
//                                5
//                            )
//                        )
//                    )
//                )
//            )
//        )
    }

    fun updateActivities(weeperLeviathan: WeeperLeviathanEntity) {
//        weeperLeviathan.brain.resetPossibleActivities(ImmutableList.of(Activity.IDLE))
    }
}