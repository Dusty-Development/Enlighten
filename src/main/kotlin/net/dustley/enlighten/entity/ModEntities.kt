package net.dustley.enlighten.entity

import net.dustley.enlighten.Enlighten
import net.dustley.enlighten.entity.ebonized_wish.EbonizedWishEntity
import net.dustley.enlighten.entity.ebonized_wish.EbonizedWishEntityRenderer
import net.dustley.enlighten.entity.weeper_leviathan.WeeperLeviathanEntity
import net.dustley.enlighten.entity.weeper_leviathan.render.WeeperLeviathanEntityRenderer
import net.dustley.enlighten.entity.weeper_leviathan.render.WeeperLeviathanModel
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry
import net.fabricmc.fabric.api.`object`.builder.v1.entity.FabricDefaultAttributeRegistry
import net.minecraft.client.render.entity.EmptyEntityRenderer
import net.minecraft.client.render.entity.model.EntityModelLayer
import net.minecraft.client.render.entity.model.EntityModelLayers
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
                .dimensions(2f,2f)
                .build("weeper_leviathan")
        )


    fun registerModEntities() {
        FabricDefaultAttributeRegistry.register(WEEPER_LEVIATHAN, WeeperLeviathanEntity.createEntityAttributes());
    }

    fun registerClientModEntities() {
        EntityRendererRegistry.register(EBONIZED_WISH, ::EbonizedWishEntityRenderer)

        EntityModelLayerRegistry.registerModelLayer(WeeperLeviathanModel.modelLayer, WeeperLeviathanModel::getTexturedModelData)
        EntityRendererRegistry.register(WEEPER_LEVIATHAN) { ctx -> WeeperLeviathanEntityRenderer(ctx, WeeperLeviathanModel(ctx.getPart(WeeperLeviathanModel.modelLayer))) }
    }
}