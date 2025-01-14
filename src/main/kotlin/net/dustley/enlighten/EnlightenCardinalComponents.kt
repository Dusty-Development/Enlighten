package net.dustley.enlighten

import net.dustley.enlighten.component.ContractedPlayerComponent
import org.ladysnake.cca.api.v3.component.ComponentKey
import org.ladysnake.cca.api.v3.component.ComponentRegistry
import org.ladysnake.cca.api.v3.scoreboard.ScoreboardComponentFactoryRegistry
import org.ladysnake.cca.api.v3.scoreboard.ScoreboardComponentInitializer


object EnlightenCardinalComponents : ScoreboardComponentInitializer {

    val CONTRACTED_PLAYER: ComponentKey<ContractedPlayerComponent> = ComponentRegistry.getOrCreate(
        Enlighten.identifier("contracted_player"),
        ContractedPlayerComponent::class.java
    )

    override fun registerScoreboardComponentFactories(registry: ScoreboardComponentFactoryRegistry) {
        Enlighten.LOGGER.info("Registering Scoreboard Components for " + Enlighten.MOD_ID)

        registry.registerScoreboardComponent(CONTRACTED_PLAYER, ::ContractedPlayerComponent)
    }

}