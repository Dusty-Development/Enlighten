package net.dustley.enlighten.entity.weeper_leviathan

import com.google.common.collect.ImmutableList
import com.google.common.collect.ImmutableSet
import net.minecraft.entity.EntityType
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.ai.brain.Activity
import net.minecraft.entity.ai.brain.Brain
import net.minecraft.entity.ai.brain.MemoryModuleType
import net.minecraft.entity.ai.brain.task.*
import net.minecraft.util.math.intprovider.UniformIntProvider

object WeeperLeviathanBrain {
    
    fun create(brain: Brain<WeeperLeviathanEntity>): Brain<WeeperLeviathanEntity> {
        addCoreActivities(brain)
        addIdleActivities(brain)
        brain.setCoreActivities(ImmutableSet.of(Activity.CORE))
        brain.setDefaultActivity(Activity.IDLE)
        brain.resetPossibleActivities()
        return brain
    }

    private fun addCoreActivities(brain: Brain<WeeperLeviathanEntity>) {
        brain.setTaskList(
            Activity.CORE,
            0,
            ImmutableList.of(
                FleeTask<WeeperLeviathanEntity>(2.0f),
                LookAroundTask(45, 90),
                MoveToTargetTask(),
                TemptationCooldownTask(MemoryModuleType.TEMPTATION_COOLDOWN_TICKS)
            )
        )
    }

    private fun addIdleActivities(brain: Brain<WeeperLeviathanEntity>) {
        brain.setTaskList(
            Activity.IDLE, ImmutableList.of(
                com.mojang.datafixers.util.Pair.of<Int, Task<LivingEntity>>(
                    0,
                    LookAtMobWithIntervalTask.follow(EntityType.PLAYER, 6.0f, UniformIntProvider.create(30, 60))
                )
            )
        )
    }

    fun updateActivities(weeperLeviathan: WeeperLeviathanEntity) {
        weeperLeviathan.brain.resetPossibleActivities(ImmutableList.of(Activity.IDLE))
    }
}