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
import net.minecraft.block.Block
import java.util.*

class ModLanguageProvider {

    fun generate() {
        generateLang(DARK_CHROMATIC, " Block")
        generateLang(DARK_CHROMATIC_STAIRS)
        generateLang(DARK_CHROMATIC_SLAB)
        generateLang(DARK_CHROMATIC_WALL)
        generateLang(DARK_CHROMATIC_BRICKS)
        generateLang(DARK_CHROMATIC_BRICK_STAIRS)
        generateLang(DARK_CHROMATIC_BRICK_SLAB)
        generateLang(DARK_CHROMATIC_BRICK_WALL)
        generateLang(DARK_POLISHED_CHROMATIC)
        generateLang(DARK_POLISHED_CHROMATIC_STAIRS)
        generateLang(DARK_POLISHED_CHROMATIC_SLAB)
        generateLang(DARK_POLISHED_CHROMATIC_WALL)
        generateLang(DARK_CHROMATIC_PILLAR)

        generateLang(CHROMATIC, " Block")
        generateLang(CHROMATIC_STAIRS)
        generateLang(CHROMATIC_SLAB)
        generateLang(CHROMATIC_WALL)
        generateLang(CHROMATIC_BRICKS)
        generateLang(CHROMATIC_BRICK_STAIRS)
        generateLang(CHROMATIC_BRICK_SLAB)
        generateLang(CHROMATIC_BRICK_WALL)
        generateLang(POLISHED_CHROMATIC)
        generateLang(POLISHED_CHROMATIC_STAIRS)
        generateLang(POLISHED_CHROMATIC_SLAB)
        generateLang(POLISHED_CHROMATIC_WALL)
        generateLang(CHROMATIC_PILLAR)

        generateLang(LIGHT_CHROMATIC, " Block")
        generateLang(LIGHT_CHROMATIC_STAIRS)
        generateLang(LIGHT_CHROMATIC_SLAB)
        generateLang(LIGHT_CHROMATIC_WALL)
        generateLang(LIGHT_CHROMATIC_BRICKS)
        generateLang(LIGHT_CHROMATIC_BRICK_STAIRS)
        generateLang(LIGHT_CHROMATIC_BRICK_SLAB)
        generateLang(LIGHT_CHROMATIC_BRICK_WALL)
        generateLang(LIGHT_POLISHED_CHROMATIC)
        generateLang(LIGHT_POLISHED_CHROMATIC_STAIRS)
        generateLang(LIGHT_POLISHED_CHROMATIC_SLAB)
        generateLang(LIGHT_POLISHED_CHROMATIC_WALL)
        generateLang(LIGHT_CHROMATIC_PILLAR)

        generateLang(COMPACTED_NEBULITE)
        generateLang(CAUTION_NEBULITE_STAIRS)
        generateLang(CAUTION_NEBULITE_SLAB)
        generateLang(DENTED_NEBULITE)
        generateLang(DENTED_NEBULITE_STAIRS)
        generateLang(DENTED_NEBULITE_SLAB)
        generateLang(CHISELED_NEBULITE)
        generateLang(CAUTION_NEBULITE)
        generateLang(NEBULITE_SUPPORT)
        generateLang(NEBULITE_SCAFFOLDING)
        generateLang(NEBULITE_PILLAR)

        generateLang(WISPFLARE, " Block")
    }

    fun generateLang(block: Block, addition: String = "") {
        val keyAsName = block.translationKey.replace("block.enlighten.", "")

        val idAsName = keyAsName.split("_").joinToString(" ") { it.replaceFirstChar { it1 ->
            if (it1.isLowerCase()) it1.titlecase(
                Locale.getDefault()
            ) else it1.toString()
        } }

         println("${block.translationKey}${"\": \""}${idAsName}$addition",)
    }
}
