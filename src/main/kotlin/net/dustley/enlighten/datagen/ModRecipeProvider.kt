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
import net.minecraft.block.Block
import net.minecraft.data.DataOutput
import net.minecraft.data.server.recipe.RecipeExporter
import net.minecraft.data.server.recipe.RecipeProvider
import net.minecraft.recipe.book.RecipeCategory
import net.minecraft.registry.RegistryWrapper
import java.util.concurrent.CompletableFuture

class ModRecipeProvider(output: DataOutput?, registryLookupFuture: CompletableFuture<RegistryWrapper.WrapperLookup>?) :
    RecipeProvider(output, registryLookupFuture) {
    override fun generate(exporter: RecipeExporter?) {
        val darkChromaticBlocks = listOf(
            DARK_CHROMATIC,
            DARK_CHROMATIC_STAIRS,
            DARK_CHROMATIC_SLAB,
            DARK_CHROMATIC_WALL,
            DARK_CHROMATIC_BRICKS,
            DARK_CHROMATIC_BRICK_STAIRS,
            DARK_CHROMATIC_BRICK_SLAB,
            DARK_CHROMATIC_BRICK_WALL,
            DARK_POLISHED_CHROMATIC,
            DARK_POLISHED_CHROMATIC_STAIRS,
            DARK_POLISHED_CHROMATIC_SLAB,
            DARK_POLISHED_CHROMATIC_WALL,
            DARK_CHROMATIC_PILLAR
        )

        val chromaticBlocks = listOf(
            CHROMATIC,
            CHROMATIC_STAIRS,
            CHROMATIC_SLAB,
            CHROMATIC_WALL,
            CHROMATIC_BRICKS,
            CHROMATIC_BRICK_STAIRS,
            CHROMATIC_BRICK_SLAB,
            CHROMATIC_BRICK_WALL,
            POLISHED_CHROMATIC,
            POLISHED_CHROMATIC_STAIRS,
            POLISHED_CHROMATIC_SLAB,
            POLISHED_CHROMATIC_WALL,
            CHROMATIC_PILLAR
        )

        val lightChromaticBlocks = listOf(
            LIGHT_CHROMATIC,
            LIGHT_CHROMATIC_STAIRS,
            LIGHT_CHROMATIC_SLAB,
            LIGHT_CHROMATIC_WALL,
            LIGHT_CHROMATIC_BRICKS,
            LIGHT_CHROMATIC_BRICK_STAIRS,
            LIGHT_CHROMATIC_BRICK_SLAB,
            LIGHT_CHROMATIC_BRICK_WALL,
            LIGHT_POLISHED_CHROMATIC,
            LIGHT_POLISHED_CHROMATIC_STAIRS,
            LIGHT_POLISHED_CHROMATIC_SLAB,
            LIGHT_POLISHED_CHROMATIC_WALL,
            LIGHT_CHROMATIC_PILLAR
        )

        darkChromaticBlocks.forEach { a ->
            darkChromaticBlocks.forEach { b ->
                if(a != b) linkBlocks(exporter, RecipeCategory.BUILDING_BLOCKS, a, b)
            }
        }

        chromaticBlocks.forEach { a ->
            chromaticBlocks.forEach { b ->
                if(a != b) linkBlocks(exporter, RecipeCategory.BUILDING_BLOCKS, a, b)
            }
        }

        lightChromaticBlocks.forEach { a ->
            lightChromaticBlocks.forEach { b ->
                if(a != b) linkBlocks(exporter, RecipeCategory.BUILDING_BLOCKS, a, b)
            }
        }

        val nebuliteBlocks = listOf(
            COMPACTED_NEBULITE,
            CAUTION_NEBULITE_STAIRS,
            CAUTION_NEBULITE_SLAB,
            DENTED_NEBULITE,
            DENTED_NEBULITE_STAIRS,
            DENTED_NEBULITE_SLAB,
            CHISELED_NEBULITE,
            CAUTION_NEBULITE,
            NEBULITE_SUPPORT,
            NEBULITE_SCAFFOLDING,
            NEBULITE_PILLAR
        )

        nebuliteBlocks.forEach { a ->
            nebuliteBlocks.forEach { b ->
                if(a != b) linkBlocks(exporter, RecipeCategory.BUILDING_BLOCKS, a, b)
            }
        }

    }

    fun linkBlocks(exporter: RecipeExporter?, category: RecipeCategory, a: Block, b: Block) {
        offerStonecuttingRecipe(exporter, category, b, a)
    }

}