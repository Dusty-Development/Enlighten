package net.dustley.enlighten.particle

import net.dustley.enlighten.Enlighten
import net.dustley.enlighten.particle.mire.MireDripParticle
import net.dustley.enlighten.particle.mire.MireSplatParticle
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry
import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes
import net.minecraft.particle.SimpleParticleType
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry


object ModParticles {

    val MIRE_DRIP_PARTICLE: SimpleParticleType = FabricParticleTypes.simple()
    val MIRE_SPLAT_PARTICLE: SimpleParticleType = FabricParticleTypes.simple()

    fun registerParticles() {
        Registry.register(Registries.PARTICLE_TYPE, Enlighten.identifier("mire_drip_particle"), MIRE_DRIP_PARTICLE)
        Registry.register(Registries.PARTICLE_TYPE, Enlighten.identifier("mire_splat_particle"), MIRE_SPLAT_PARTICLE)
    }

    fun registerClientParticles() {
        ParticleFactoryRegistry.getInstance().register(MIRE_DRIP_PARTICLE) { sprites -> MireDripParticle.Companion.Factory(sprites) }
        ParticleFactoryRegistry.getInstance().register(MIRE_SPLAT_PARTICLE) { sprites -> MireSplatParticle.Companion.Factory(sprites) }
    }
}