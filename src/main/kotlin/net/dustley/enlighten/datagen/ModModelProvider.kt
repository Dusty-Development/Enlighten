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
import net.dustley.enlighten.block.ModBlocks.CHROMATIC_SLAB
import net.dustley.enlighten.block.ModBlocks.CHROMATIC_STAIRS
import net.dustley.enlighten.block.ModBlocks.CHROMATIC_WALL
import net.dustley.enlighten.block.ModBlocks.COMPACTED_NEBULITE
import net.dustley.enlighten.block.ModBlocks.DARK_CHROMATIC
import net.dustley.enlighten.block.ModBlocks.DARK_CHROMATIC_BRICKS
import net.dustley.enlighten.block.ModBlocks.DARK_CHROMATIC_BRICK_SLAB
import net.dustley.enlighten.block.ModBlocks.DARK_CHROMATIC_BRICK_STAIRS
import net.dustley.enlighten.block.ModBlocks.DARK_CHROMATIC_BRICK_WALL
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
import net.dustley.enlighten.block.ModBlocks.LIGHT_CHROMATIC_SLAB
import net.dustley.enlighten.block.ModBlocks.LIGHT_CHROMATIC_STAIRS
import net.dustley.enlighten.block.ModBlocks.LIGHT_CHROMATIC_WALL
import net.dustley.enlighten.block.ModBlocks.LIGHT_POLISHED_CHROMATIC
import net.dustley.enlighten.block.ModBlocks.LIGHT_POLISHED_CHROMATIC_SLAB
import net.dustley.enlighten.block.ModBlocks.LIGHT_POLISHED_CHROMATIC_STAIRS
import net.dustley.enlighten.block.ModBlocks.LIGHT_POLISHED_CHROMATIC_WALL
import net.dustley.enlighten.block.ModBlocks.NEBULITE_SUPPORT
import net.dustley.enlighten.block.ModBlocks.POLISHED_CHROMATIC
import net.dustley.enlighten.block.ModBlocks.POLISHED_CHROMATIC_SLAB
import net.dustley.enlighten.block.ModBlocks.POLISHED_CHROMATIC_STAIRS
import net.dustley.enlighten.block.ModBlocks.POLISHED_CHROMATIC_WALL
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider
import net.minecraft.data.client.BlockStateModelGenerator
import net.minecraft.data.client.ItemModelGenerator


class ModModelProvider(output: FabricDataOutput) : FabricModelProvider(output) {
    override fun generateBlockStateModels(blockStateModelGenerator: BlockStateModelGenerator) {
        // DARK CHROMATIC
        val darkChromatic = blockStateModelGenerator.registerCubeAllModelTexturePool(DARK_CHROMATIC)
        darkChromatic.stairs(DARK_CHROMATIC_STAIRS)
        darkChromatic.slab(DARK_CHROMATIC_SLAB)
        darkChromatic.wall(DARK_CHROMATIC_WALL)
        
        val darkChromaticBrick = blockStateModelGenerator.registerCubeAllModelTexturePool(DARK_CHROMATIC_BRICKS)
        darkChromaticBrick.stairs(DARK_CHROMATIC_BRICK_STAIRS)
        darkChromaticBrick.slab(DARK_CHROMATIC_BRICK_SLAB)
        darkChromaticBrick.wall(DARK_CHROMATIC_BRICK_WALL)
        
        val darkPolishedChromatic = blockStateModelGenerator.registerCubeAllModelTexturePool(DARK_POLISHED_CHROMATIC)
        darkPolishedChromatic.stairs(DARK_POLISHED_CHROMATIC_STAIRS)
        darkPolishedChromatic.slab(DARK_POLISHED_CHROMATIC_SLAB)
        darkPolishedChromatic.wall(DARK_POLISHED_CHROMATIC_WALL)
        
        // MIXED CHROMATIC
        val chromatic = blockStateModelGenerator.registerCubeAllModelTexturePool(CHROMATIC)
        chromatic.stairs(CHROMATIC_STAIRS)
        chromatic.slab(CHROMATIC_SLAB)
        chromatic.wall(CHROMATIC_WALL)

        val chromaticBrick = blockStateModelGenerator.registerCubeAllModelTexturePool(CHROMATIC_BRICKS)
        chromaticBrick.stairs(CHROMATIC_BRICK_STAIRS)
        chromaticBrick.slab(CHROMATIC_BRICK_SLAB)
        chromaticBrick.wall(CHROMATIC_BRICK_WALL)

        val polishedChromatic = blockStateModelGenerator.registerCubeAllModelTexturePool(POLISHED_CHROMATIC)
        polishedChromatic.stairs(POLISHED_CHROMATIC_STAIRS)
        polishedChromatic.slab(POLISHED_CHROMATIC_SLAB)
        polishedChromatic.wall(POLISHED_CHROMATIC_WALL)

        // LIGHT CHROMATIC
        val lightChromatic = blockStateModelGenerator.registerCubeAllModelTexturePool(LIGHT_CHROMATIC)
        lightChromatic.stairs(LIGHT_CHROMATIC_STAIRS)
        lightChromatic.slab(LIGHT_CHROMATIC_SLAB)
        lightChromatic.wall(LIGHT_CHROMATIC_WALL)

        val lightChromaticBrick = blockStateModelGenerator.registerCubeAllModelTexturePool(LIGHT_CHROMATIC_BRICKS)
        lightChromaticBrick.stairs(LIGHT_CHROMATIC_BRICK_STAIRS)
        lightChromaticBrick.slab(LIGHT_CHROMATIC_BRICK_SLAB)
        lightChromaticBrick.wall(LIGHT_CHROMATIC_BRICK_WALL)

        val lightPolishedChromatic = blockStateModelGenerator.registerCubeAllModelTexturePool(LIGHT_POLISHED_CHROMATIC)
        lightPolishedChromatic.stairs(LIGHT_POLISHED_CHROMATIC_STAIRS)
        lightPolishedChromatic.slab(LIGHT_POLISHED_CHROMATIC_SLAB)
        lightPolishedChromatic.wall(LIGHT_POLISHED_CHROMATIC_WALL)

        val cautionNebulite = blockStateModelGenerator.registerCubeAllModelTexturePool(CAUTION_NEBULITE)
        cautionNebulite.stairs(CAUTION_NEBULITE_STAIRS)
        cautionNebulite.slab(CAUTION_NEBULITE_SLAB)
        cautionNebulite.wall(NEBULITE_SUPPORT)

        val dentedNebulite = blockStateModelGenerator.registerCubeAllModelTexturePool(DENTED_NEBULITE)
        dentedNebulite.stairs(DENTED_NEBULITE_STAIRS)
        dentedNebulite.slab(DENTED_NEBULITE_SLAB)

        blockStateModelGenerator.registerCubeAllModelTexturePool(CHISELED_NEBULITE)
        blockStateModelGenerator.registerCubeAllModelTexturePool(COMPACTED_NEBULITE)


        // PILLARS
//        registerPiller(blockStateModelGenerator, DARK_CHROMATIC_PILLAR)
//        registerPiller(blockStateModelGenerator, CHROMATIC_PILLAR)
//        registerPiller(blockStateModelGenerator, LIGHT_CHROMATIC_PILLAR)

    }

    override fun generateItemModels(itemModelGenerator: ItemModelGenerator) {

    }

}