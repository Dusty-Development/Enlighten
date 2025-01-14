package net.dustley.enlighten.entity

import net.dustley.enlighten.Enlighten
import net.dustley.enlighten.entity.ebonized_wish.EbonizedWishEntity
import net.dustley.enlighten.entity.ebonized_wish.EbonizedWishEntityRenderer
import net.dustley.enlighten.entity.weeper_leviathan.WeeperLeviathanEntity
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry
import net.minecraft.client.render.entity.EmptyEntityRenderer
import net.minecraft.entity.EntityType
import net.minecraft.entity.SpawnGroup
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry


object ModEntities {

    val EBONIZED_WISH: EntityType<EbonizedWishEntity> =
        Registry.register<EntityType<*>, EntityType<EbonizedWishEntity>>(
            Registries.ENTITY_TYPE,
            Enlighten.identifier("ebonized_wish"),
            EntityType.Builder.create(
                (EntityType.EntityFactory {
                        type: EntityType<EbonizedWishEntity>,
                        world -> EbonizedWishEntity(world, type)
                }), SpawnGroup.MISC)
                .dimensions(0.5f,2f)
                .makeFireImmune()
                .build("ebonized_wish")
        )

    val WEEPER_LEVIATHAN: EntityType<WeeperLeviathanEntity> =
        Registry.register<EntityType<*>, EntityType<WeeperLeviathanEntity>>(
            Registries.ENTITY_TYPE,
            Enlighten.identifier("weeper_leviathan"),
            EntityType.Builder.create(
                (EntityType.EntityFactory {
                        type: EntityType<WeeperLeviathanEntity>,
                        world -> WeeperLeviathanEntity(world, type)
                }), SpawnGroup.MISC)
                .dimensions(1.5f,1.5f)
                .build("weeper_leviathan")
        )


    fun registerModEntities() {

    }

    fun registerClientModEntities() {
        EntityRendererRegistry.register(EBONIZED_WISH, ::EbonizedWishEntityRenderer)

//        EntityModelLayerRegistry.registerModelLayer(WeeperLeviathanModel.MODEL, WeeperLeviathanModel::getTexturedModelData)
        EntityRendererRegistry.register(WEEPER_LEVIATHAN, ::EmptyEntityRenderer)
    }
}