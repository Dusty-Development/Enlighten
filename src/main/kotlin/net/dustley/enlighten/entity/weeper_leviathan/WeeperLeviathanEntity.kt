package net.dustley.enlighten.entity.weeper_leviathan

import com.google.common.collect.ImmutableList
import com.mojang.serialization.Dynamic
import net.minecraft.entity.EntityType
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.ai.brain.Brain
import net.minecraft.entity.ai.brain.MemoryModuleType
import net.minecraft.entity.ai.brain.sensor.SensorType
import net.minecraft.entity.ai.control.MoveControl
import net.minecraft.entity.ai.control.YawAdjustingLookControl
import net.minecraft.entity.ai.pathing.EntityNavigation
import net.minecraft.entity.ai.pathing.SwimNavigation
import net.minecraft.entity.attribute.DefaultAttributeContainer
import net.minecraft.entity.attribute.EntityAttributes
import net.minecraft.entity.damage.DamageSource
import net.minecraft.entity.mob.PathAwareEntity
import net.minecraft.item.ItemStack
import net.minecraft.server.network.DebugInfoSender
import net.minecraft.server.world.ServerWorld
import net.minecraft.sound.SoundEvent
import net.minecraft.sound.SoundEvents
import net.minecraft.util.math.Vec3d
import net.minecraft.world.World

class WeeperLeviathanEntity(world: World, entityType: EntityType<out PathAwareEntity>?) : PathAwareEntity(entityType, world) {

    //==// CONSTRUCTORS \\==\\
    // This is the other stuffs
    constructor(world: World, spawnPos: Vec3d, itemStack: ItemStack, entityType: EntityType<out PathAwareEntity>?) : this(world, entityType) {

    }

    init {
        this.moveControl = MoveControl(this)
        this.lookControl = YawAdjustingLookControl(this, 10)
    }

    //==// AI \\==\\

    override fun createNavigation(world: World?): EntityNavigation {
        return SwimNavigation(this, world)
    }

    override fun createBrainProfile(): Brain.Profile<*>? {
        return Brain.createProfile(MEMORY_MODULES, SENSORS)
    }

    override fun deserializeBrain(dynamic: Dynamic<*>): Brain<out LivingEntity>? {
        return createBrainProfile()?.let { WeeperLeviathanBrain.create(it.deserialize(dynamic)) }
    }

    override fun getBrain(): Brain<WeeperLeviathanEntity> {
        return super.getBrain() as Brain<WeeperLeviathanEntity>
    }


    override fun mobTick() {
        world.profiler.push("weeperLeviathanBrain")
        getBrain().tick(world as ServerWorld, this)
        world.profiler.pop()
        world.profiler.push("weeperLeviathanActivityUpdate")
//        WeeperLeviathanBrain.updateActivities(this)
        world.profiler.pop()
        super.mobTick()
    }

    fun createWeeperLeviathanAttributes(): DefaultAttributeContainer.Builder {
        return createMobAttributes().add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 1.0)
            .add(EntityAttributes.GENERIC_MAX_HEALTH, 6.0)
    }


    override fun getAmbientSound(): SoundEvent? {
        return null
    }

    override fun getHurtSound(source: DamageSource?): SoundEvent? {
        return SoundEvents.ENTITY_WARDEN_HURT
    }

    override fun getDeathSound(): SoundEvent? {
        return SoundEvents.ENTITY_WARDEN_DEATH
    }

    override fun sendAiDebugData() {
        super.sendAiDebugData()
        DebugInfoSender.sendBrainDebugData(this)
    }

    override fun shouldDropXp(): Boolean {
        return false
    }

    companion object {
        val SENSORS = ImmutableList.of(
            SensorType.NEAREST_LIVING_ENTITIES,
            SensorType.NEAREST_PLAYERS,
//            SensorType.HURT_BY,
//            SensorType.FROG_TEMPTATIONS
        );
        val MEMORY_MODULES = ImmutableList.of(
            MemoryModuleType.LOOK_TARGET,
            MemoryModuleType.VISIBLE_MOBS,
            MemoryModuleType.WALK_TARGET,
            MemoryModuleType.CANT_REACH_WALK_TARGET_SINCE,
            MemoryModuleType.PATH,
//            MemoryModuleType.NEAREST_VISIBLE_ADULT,
//            MemoryModuleType.TEMPTATION_COOLDOWN_TICKS,
//            MemoryModuleType.IS_TEMPTED,
//            MemoryModuleType.TEMPTING_PLAYER,
//            MemoryModuleType.BREED_TARGET,
//            MemoryModuleType.IS_PANICKING
        );

        fun createEntityAttributes(): DefaultAttributeContainer.Builder {
            return createMobAttributes()
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 1.0)
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 400.0)
        }
    }
}