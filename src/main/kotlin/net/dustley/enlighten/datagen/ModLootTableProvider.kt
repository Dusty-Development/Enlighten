package net.dustley.enlighten.datagen

import net.dustley.enlighten.block.ModBlocks.CAUTION_NEBULITE
import net.dustley.enlighten.block.ModBlocks.CAUTION_NEBULITE_SLAB
import net.dustley.enlighten.block.ModBlocks.CAUTION_NEBULITE_STAIRS
import net.dustley.enlighten.block.ModBlocks.CHISELED_NEBULITE
import net.dustley.enlighten.block.ModBlocks.CHROMATIC
import net.dustley.enlighten.block.ModBlocks.CHROMATIC_BRICKS
import net.dustley.enlighten.block.ModBlocks.CHROMATIC_BRICK_SLAB
import net.dustley.enlighten.block.ModBlocks.CHROMATIC_BRICK_STAIRS
import net.dustley.enlighten.block.ModBlocks.CHROMATIC_BRICK_WALL
import net.dustley.enlighten.block.ModBlocks.CHROMATIC_PILLAR
import net.dustley.enlighten.block.ModBlocks.CHROMATIC_SLAB
import net.dustley.enlighten.block.ModBlocks.CHROMATIC_STAIRS
import net.dustley.enlighten.block.ModBlocks.CHROMATIC_WALL
import net.dustley.enlighten.block.ModBlocks.COMPACTED_NEBULITE
import net.dustley.enlighten.block.ModBlocks.DARK_CHROMATIC
import net.dustley.enlighten.block.ModBlocks.DARK_CHROMATIC_BRICKS
import net.dustley.enlighten.block.ModBlocks.DARK_CHROMATIC_BRICK_SLAB
import net.dustley.enlighten.block.ModBlocks.DARK_CHROMATIC_BRICK_STAIRS
import net.dustley.enlighten.block.ModBlocks.DARK_CHROMATIC_BRICK_WALL
import net.dustley.enlighten.block.ModBlocks.DARK_CHROMATIC_PILLAR
import net.dustley.enlighten.block.ModBlocks.DARK_CHROMATIC_SLAB
import net.dustley.enlighten.block.ModBlocks.DARK_CHROMATIC_STAIRS
import net.dustley.enlighten.block.ModBlocks.DARK_CHROMATIC_WALL
import net.dustley.enlighten.block.ModBlocks.DARK_POLISHED_CHROMATIC
import net.dustley.enlighten.block.ModBlocks.DARK_POLISHED_CHROMATIC_SLAB
import net.dustley.enlighten.block.ModBlocks.DARK_POLISHED_CHROMATIC_STAIRS
import net.dustley.enlighten.block.ModBlocks.DARK_POLISHED_CHROMATIC_WALL
import net.dustley.enlighten.block.ModBlocks.DENTED_NEBULITE
import net.dustley.enlighten.block.ModBlocks.DENTED_NEBULITE_SLAB
import net.dustley.enlighten.block.ModBlocks.DENTED_NEBULITE_STAIRS
import net.dustley.enlighten.block.ModBlocks.LIGHT_CHROMATIC
import net.dustley.enlighten.block.ModBlocks.LIGHT_CHROMATIC_BRICKS
import net.dustley.enlighten.block.ModBlocks.LIGHT_CHROMATIC_BRICK_SLAB
import net.dustley.enlighten.block.ModBlocks.LIGHT_CHROMATIC_BRICK_STAIRS
import net.dustley.enlighten.block.ModBlocks.LIGHT_CHROMATIC_BRICK_WALL
import net.dustley.enlighten.block.ModBlocks.LIGHT_CHROMATIC_PILLAR
import net.dustley.enlighten.block.ModBlocks.LIGHT_CHROMATIC_SLAB
import net.dustley.enlighten.block.ModBlocks.LIGHT_CHROMATIC_STAIRS
import net.dustley.enlighten.block.ModBlocks.LIGHT_CHROMATIC_WALL
import net.dustley.enlighten.block.ModBlocks.LIGHT_POLISHED_CHROMATIC
import net.dustley.enlighten.block.ModBlocks.LIGHT_POLISHED_CHROMATIC_SLAB
import net.dustley.enlighten.block.ModBlocks.LIGHT_POLISHED_CHROMATIC_STAIRS
import net.dustley.enlighten.block.ModBlocks.LIGHT_POLISHED_CHROMATIC_WALL
import net.dustley.enlighten.block.ModBlocks.NEBULITE_PILLAR
import net.dustley.enlighten.block.ModBlocks.NEBULITE_SCAFFOLDING
import net.dustley.enlighten.block.ModBlocks.NEBULITE_SUPPORT
import net.dustley.enlighten.block.ModBlocks.POLISHED_CHROMATIC
import net.dustley.enlighten.block.ModBlocks.POLISHED_CHROMATIC_SLAB
import net.dustley.enlighten.block.ModBlocks.POLISHED_CHROMATIC_STAIRS
import net.dustley.enlighten.block.ModBlocks.POLISHED_CHROMATIC_WALL
import net.dustley.enlighten.block.ModBlocks.WISPFLARE
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider
import net.minecraft.registry.RegistryWrapper
import java.util.concurrent.CompletableFuture


class ModLootTableProvider(
    dataOutput: FabricDataOutput?,
    registryLookup: CompletableFuture<RegistryWrapper.WrapperLookup>?,
) : FabricBlockLootTableProvider(dataOutput, registryLookup) {
    override fun generate() {

        addDrop(DARK_CHROMATIC)
        addDrop(DARK_CHROMATIC_STAIRS)
        addDrop(DARK_CHROMATIC_SLAB)
        addDrop(DARK_CHROMATIC_WALL)
        addDrop(DARK_CHROMATIC_BRICKS)
        addDrop(DARK_CHROMATIC_BRICK_STAIRS)
        addDrop(DARK_CHROMATIC_BRICK_SLAB)
        addDrop(DARK_CHROMATIC_BRICK_WALL)
        addDrop(DARK_POLISHED_CHROMATIC)
        addDrop(DARK_POLISHED_CHROMATIC_STAIRS)
        addDrop(DARK_POLISHED_CHROMATIC_SLAB)
        addDrop(DARK_POLISHED_CHROMATIC_WALL)
        addDrop(DARK_CHROMATIC_PILLAR)

        addDrop(CHROMATIC)
        addDrop(CHROMATIC_STAIRS)
        addDrop(CHROMATIC_SLAB)
        addDrop(CHROMATIC_WALL)
        addDrop(CHROMATIC_BRICKS)
        addDrop(CHROMATIC_BRICK_STAIRS)
        addDrop(CHROMATIC_BRICK_SLAB)
        addDrop(CHROMATIC_BRICK_WALL)
        addDrop(POLISHED_CHROMATIC)
        addDrop(POLISHED_CHROMATIC_STAIRS)
        addDrop(POLISHED_CHROMATIC_SLAB)
        addDrop(POLISHED_CHROMATIC_WALL)
        addDrop(CHROMATIC_PILLAR)

        addDrop(LIGHT_CHROMATIC)
        addDrop(LIGHT_CHROMATIC_STAIRS)
        addDrop(LIGHT_CHROMATIC_SLAB)
        addDrop(LIGHT_CHROMATIC_WALL)
        addDrop(LIGHT_CHROMATIC_BRICKS)
        addDrop(LIGHT_CHROMATIC_BRICK_STAIRS)
        addDrop(LIGHT_CHROMATIC_BRICK_SLAB)
        addDrop(LIGHT_CHROMATIC_BRICK_WALL)
        addDrop(LIGHT_POLISHED_CHROMATIC)
        addDrop(LIGHT_POLISHED_CHROMATIC_STAIRS)
        addDrop(LIGHT_POLISHED_CHROMATIC_SLAB)
        addDrop(LIGHT_POLISHED_CHROMATIC_WALL)
        addDrop(LIGHT_CHROMATIC_PILLAR)

        addDrop(COMPACTED_NEBULITE)
        addDrop(CAUTION_NEBULITE_STAIRS)
        addDrop(CAUTION_NEBULITE_SLAB)
        addDrop(DENTED_NEBULITE)
        addDrop(DENTED_NEBULITE_STAIRS)
        addDrop(DENTED_NEBULITE_SLAB)
        addDrop(CHISELED_NEBULITE)
        addDrop(CAUTION_NEBULITE)
        addDrop(NEBULITE_SUPPORT)
        addDrop(NEBULITE_SCAFFOLDING)
        addDrop(NEBULITE_PILLAR)

        addDrop(WISPFLARE)
    }
}