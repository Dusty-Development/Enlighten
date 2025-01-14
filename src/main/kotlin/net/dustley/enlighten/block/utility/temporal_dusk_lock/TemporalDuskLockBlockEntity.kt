//package net.dustley.enlighten.block.utility.temporal_dusk_lock
//
//import mod.chloeprime.aaaparticles.api.client.effekseer.ParticleEmitter
//import mod.chloeprime.aaaparticles.api.common.AAALevel
//import mod.chloeprime.aaaparticles.api.common.ParticleEmitterInfo
//import mod.chloeprime.aaaparticles.client.registry.EffectRegistry
//import net.dustley.enlighten.Enlighten
//import net.dustley.enlighten.block.ModBlockEntities
//import net.minecraft.block.BlockState
//import net.minecraft.block.wish_contract.BlockEntity
//import net.minecraft.nbt.NbtCompound
//import net.minecraft.registry.RegistryWrapper
//import net.minecraft.server.world.ServerWorld
//import net.minecraft.sound.SoundCategory
//import net.minecraft.sound.SoundEvents
//import net.minecraft.text.Text
//import net.minecraft.util.math.BlockPos
//import net.minecraft.util.math.Box
//import net.minecraft.util.math.Vec3d
//import net.minecraft.world.World
//import kotlin.jvm.optionals.getOrNull
//
//class TemporalDuskLockBlockEntity(pos: BlockPos, state: BlockState) : BlockEntity(ModBlockEntities.TEMPORAL_DUSK_LOCK, pos, state) {
//
//    var isLoaded = false
//    var isStarted = false
//    var activeTicks = 0
//    var startingTick = 0
//    var effect: ParticleEmitterInfo? = null
//    var emitter: ParticleEmitter? = null
//
//    override fun readNbt(nbt: NbtCompound, registryLookup: RegistryWrapper.WrapperLookup) {
//        super.readNbt(nbt, registryLookup)
//        startingTick = nbt.getInt(STARTING_TICK_NBT_KEY)
//        isStarted = nbt.getBoolean(IS_STARTED_NBT_KEY)
//    }
//
//    override fun writeNbt(nbt: NbtCompound, registryLookup: RegistryWrapper.WrapperLookup) {
//        nbt.putInt(STARTING_TICK_NBT_KEY, startingTick)
//        nbt.putBoolean(IS_STARTED_NBT_KEY, isStarted)
//        super.writeNbt(nbt, registryLookup)
//    }
//
//    fun tick() {
//        if(cachedState.get(TemporalDuskLockBlock.POWERED) && isStarted) lateLoaded()
//        if(cachedState.get(TemporalDuskLockBlock.POWERED) && !isStarted) onUse()
//        if(!cachedState.get(TemporalDuskLockBlock.POWERED)) return
//
//        activeTicks++
//
//        if(world?.isClient == true) {
//            world?.players?.forEach {
//                it.sendMessage(Text.literal("$activeTicks / 12400"), true)
//            }
//        }
//
//        if(activeTicks >= 12200) world?.addBlockBreakParticles(pos, cachedState)
//        if(activeTicks >= 12400) {
//            onBreak()
//            world?.breakBlock(pos, false)
//        }
//
//        val center = pos.toCenterPos()
//        val radius = BARRIER_DISTANCE.toDouble() / 2.0
//        val box = Box.of(center, radius * 3.0, radius * 3.0, radius * 3.0)
//
//        val tolerance = 7.5
//
//        world?.getOtherEntities(null, box)?.forEach { wish_contract ->
//            val entityPos = wish_contract.pos.add(0.0,wish_contract.height.toDouble() * 0.5,0.0)
//            val distance = entityPos.distanceTo(center)
//
//            if (distance > radius + (tolerance*0.5) && distance < radius + tolerance) {
//                //OUTSIDE
//                val direction = center.subtract(entityPos).normalize()
//                wish_contract.velocity = reflectVelocity(wish_contract.velocity, direction)
//
//                val correction = direction.multiply(distance - (radius + tolerance))
//                val pos = entityPos.add(correction)
//                wish_contract.setPos(pos.x, pos.y - wish_contract.height.toDouble() * 0.5, pos.z)
//            }
//            if (distance > radius && distance < radius + (tolerance*0.5)) {
//                // INSIDE
//                val direction = entityPos.subtract(center).normalize()
//                wish_contract.velocity = reflectVelocity(wish_contract.velocity, direction)
//
//                val correction = direction.multiply((radius - distance))
//                val pos = entityPos.add(correction)
//                wish_contract.setPos(pos.x, pos.y - wish_contract.height.toDouble() * 0.5, pos.z)
//            }
//        }
//
////        if(world?.isClient == true && isStarted) {
////            val frame = (activeTicks * 20) / 60f
////            getParticleEmitter()?.setProgress(270f)
////        }
//    }
//
//    fun reflectVelocity(velocity: Vec3d, normal: Vec3d): Vec3d {
//        // Normalize the normal to ensure proper reflection
//        val normalizedNormal = normal.normalize()
//        // Calculate the reflection using the formula
//        return velocity.subtract(normalizedNormal.multiply(2 * velocity.dotProduct(normalizedNormal)))
//    }
//
//    fun onBreak() {
//        world?.createExplosion(null, pos.toCenterPos().x, pos.toCenterPos().y, pos.toCenterPos().z, 5f, true, World.ExplosionSourceType.BLOCK)
//
//        world?.playSoundAtBlockCenter(pos, SoundEvents.BLOCK_SCULK_SHRIEKER_SHRIEK, SoundCategory.BLOCKS, 5f, 0.35f, false)
//        world?.playSoundAtBlockCenter(pos, SoundEvents.ENTITY_WITHER_DEATH, SoundCategory.BLOCKS, 5f, 0.05f, false)
//        activeTicks = 12400
//
//        if(world?.isClient == true) {
//            getParticleEmitter()?.setProgress(4100f)
//        }
//    }
//
//    fun onPlace() {
//
//    }
//
//    fun lateLoaded() {
//        println("Just late")
//        isStarted = true
//        emitter = null
//
//        activeTicks = world!!.time.toInt() - startingTick
//        println(activeTicks)
//
//        if(world?.isClient == true) {
//            effect = BARRIER_EFFECT.clone().position(pos.toCenterPos()).scale(BARRIER_DISTANCE / 10)
//            AAALevel.addParticle(world, false, effect)
//
//            val frame = (activeTicks * 20) / 60f
//            getParticleEmitter()?.setProgress(frame)
//        }
//    }
//
//    fun onUse() {
////        if(world?.isClient == true) {
////            world?.players?.forEach {
////                it.sendMessage(Text.literal("Nice try buddy"), true)
////            }
////        }
////
////        return
//
//
//        println("Used")
//        startingTick = world!!.time.toInt()
//        isStarted = true
//        emitter = null
//
//        if(world?.isClient == true) {
//            effect = BARRIER_EFFECT.clone().position(pos.toCenterPos()).scale(BARRIER_DISTANCE / 10)
//            AAALevel.addParticle(world, false, effect)
//        }
//
//        if(!world?.isClient!!) (world as ServerWorld).timeOfDay = 13200
//        world?.playSoundAtBlockCenter(pos, SoundEvents.ENTITY_WARDEN_SONIC_BOOM, SoundCategory.BLOCKS, 5f, 0.2f, false)
//    }
//
//    private fun getParticleEmitter(): ParticleEmitter? {
//        val effectDefinition = EffectRegistry.get(Enlighten.identifier("temporal_dusk_lock"))
//        emitter = effectDefinition?.getNamedEmitter(ParticleEmitter.Type.WORLD, Enlighten.identifier("temporal_dusk_lock_emitter"))?.getOrNull();
//        return emitter
//    }
//
//    companion object {
//        const val BARRIER_DISTANCE = 150f
//
//        const val STARTING_TICK_NBT_KEY = "StartingTick"
//        const val IS_STARTED_NBT_KEY = "IsStarted"
//
//        val BARRIER_EFFECT = ParticleEmitterInfo(Enlighten.identifier("temporal_dusk_lock"), Enlighten.identifier("temporal_dusk_lock_emitter"))
//
//    }
//}