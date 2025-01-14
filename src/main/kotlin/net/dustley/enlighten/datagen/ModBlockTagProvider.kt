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
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider.BlockTagProvider
import net.minecraft.registry.RegistryWrapper
import net.minecraft.registry.tag.BlockTags
import java.util.concurrent.CompletableFuture

class ModBlockTagProvider(output: FabricDataOutput?, registriesFuture: CompletableFuture<RegistryWrapper.WrapperLookup>?
) : BlockTagProvider(output, registriesFuture) {
    override fun configure(wrapperLookup: RegistryWrapper.WrapperLookup?) {

        getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE)
            .add(DARK_CHROMATIC)
            .add(DARK_CHROMATIC_STAIRS)
            .add(DARK_CHROMATIC_SLAB)
            .add(DARK_CHROMATIC_WALL)
            .add(DARK_CHROMATIC_BRICKS)
            .add(DARK_CHROMATIC_BRICK_STAIRS)
            .add(DARK_CHROMATIC_BRICK_SLAB)
            .add(DARK_CHROMATIC_BRICK_WALL)
            .add(DARK_POLISHED_CHROMATIC)
            .add(DARK_POLISHED_CHROMATIC_STAIRS)
            .add(DARK_POLISHED_CHROMATIC_SLAB)
            .add(DARK_POLISHED_CHROMATIC_WALL)
            .add(DARK_CHROMATIC_PILLAR)
            .add(CHROMATIC)
            .add(CHROMATIC_STAIRS)
            .add(CHROMATIC_SLAB)
            .add(CHROMATIC_WALL)
            .add(CHROMATIC_BRICKS)
            .add(CHROMATIC_BRICK_STAIRS)
            .add(CHROMATIC_BRICK_SLAB)
            .add(CHROMATIC_BRICK_WALL)
            .add(POLISHED_CHROMATIC)
            .add(POLISHED_CHROMATIC_STAIRS)
            .add(POLISHED_CHROMATIC_SLAB)
            .add(POLISHED_CHROMATIC_WALL)
            .add(CHROMATIC_PILLAR)
            .add(LIGHT_CHROMATIC)
            .add(LIGHT_CHROMATIC_STAIRS)
            .add(LIGHT_CHROMATIC_SLAB)
            .add(LIGHT_CHROMATIC_WALL)
            .add(LIGHT_CHROMATIC_BRICKS)
            .add(LIGHT_CHROMATIC_BRICK_STAIRS)
            .add(LIGHT_CHROMATIC_BRICK_SLAB)
            .add(LIGHT_CHROMATIC_BRICK_WALL)
            .add(LIGHT_POLISHED_CHROMATIC)
            .add(LIGHT_POLISHED_CHROMATIC_STAIRS)
            .add(LIGHT_POLISHED_CHROMATIC_SLAB)
            .add(LIGHT_POLISHED_CHROMATIC_WALL)
            .add(LIGHT_CHROMATIC_PILLAR)
            .add(COMPACTED_NEBULITE)
            .add(CAUTION_NEBULITE_STAIRS)
            .add(CAUTION_NEBULITE_SLAB)
            .add(DENTED_NEBULITE)
            .add(DENTED_NEBULITE_STAIRS)
            .add(DENTED_NEBULITE_SLAB)
            .add(CHISELED_NEBULITE)
            .add(CAUTION_NEBULITE)
            .add(NEBULITE_SUPPORT)
            .add(NEBULITE_SCAFFOLDING)
            .add(NEBULITE_PILLAR)

        getOrCreateTagBuilder(BlockTags.WALLS)
            .add(DARK_CHROMATIC_WALL)
            .add(DARK_CHROMATIC_BRICK_WALL)
            .add(DARK_POLISHED_CHROMATIC_WALL)
            .add(CHROMATIC_WALL)
            .add(CHROMATIC_BRICK_WALL)
            .add(POLISHED_CHROMATIC_WALL)
            .add(LIGHT_CHROMATIC_WALL)
            .add(LIGHT_CHROMATIC_BRICK_WALL)
            .add(LIGHT_POLISHED_CHROMATIC_WALL)

    }
}