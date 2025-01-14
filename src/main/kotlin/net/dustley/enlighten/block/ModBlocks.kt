package net.dustley.enlighten.block

import net.dustley.enlighten.Enlighten
import net.dustley.enlighten.block.cosmetic.begrimed_mire.BegrimedMireFluidBlock
import net.dustley.enlighten.block.cosmetic.wispflare.Wispflare
import net.dustley.enlighten.block.utility.nebulite_scaffolding.NebuliteScaffoldingBlock
import net.dustley.enlighten.fluid.ModFluids
import net.dustley.enlighten.item.ModItems.ENLIGHTEN_ITEM_GROUP_KEY
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents.ModifyEntries
import net.minecraft.block.*
import net.minecraft.block.AbstractBlock.Settings
import net.minecraft.block.enums.NoteBlockInstrument
import net.minecraft.block.piston.PistonBehavior
import net.minecraft.client.render.RenderLayer
import net.minecraft.item.BlockItem
import net.minecraft.item.Item
import net.minecraft.item.PlaceableOnWaterItem
import net.minecraft.item.ScaffoldingItem
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.sound.BlockSoundGroup


object ModBlocks {

    // DARK CHROMATIC
    val DARK_CHROMATIC: Block = registerBlock("dark_chromatic", Block( 
        Settings.create()
            .mapColor(MapColor.DEEPSLATE_GRAY)
            .instrument(NoteBlockInstrument.BASEDRUM)
            .sounds(BlockSoundGroup.TUFF)
            .strength(1.5f, 6.0f)
        )
    )
    val DARK_CHROMATIC_STAIRS = registerBlock("dark_chromatic_stairs", StairsBlock(DARK_CHROMATIC.defaultState, Settings.copy(DARK_CHROMATIC)))
    val DARK_CHROMATIC_SLAB = registerBlock("dark_chromatic_slab", SlabBlock(Settings.copy(DARK_CHROMATIC)))
    val DARK_CHROMATIC_WALL = registerBlock("dark_chromatic_wall", WallBlock(Settings.copy(DARK_CHROMATIC)))

    val DARK_CHROMATIC_BRICKS: Block = registerBlock("dark_chromatic_bricks", Block(Settings.copy(DARK_CHROMATIC)))
    val DARK_CHROMATIC_BRICK_STAIRS = registerBlock("dark_chromatic_brick_stairs", StairsBlock(DARK_CHROMATIC_BRICKS.defaultState, Settings.copy(DARK_CHROMATIC_BRICKS)))
    val DARK_CHROMATIC_BRICK_SLAB = registerBlock("dark_chromatic_brick_slab", SlabBlock(Settings.copy(DARK_CHROMATIC_BRICKS)))
    val DARK_CHROMATIC_BRICK_WALL = registerBlock("dark_chromatic_brick_wall", WallBlock(Settings.copy(DARK_CHROMATIC_BRICKS)))

    val DARK_POLISHED_CHROMATIC: Block = registerBlock("dark_polished_chromatic", Block(Settings.copy(DARK_CHROMATIC)))
    val DARK_POLISHED_CHROMATIC_STAIRS = registerBlock("dark_polished_chromatic_stairs", StairsBlock(DARK_POLISHED_CHROMATIC.defaultState, Settings.copy(DARK_POLISHED_CHROMATIC)))
    val DARK_POLISHED_CHROMATIC_SLAB = registerBlock("dark_polished_chromatic_slab", SlabBlock(Settings.copy(DARK_POLISHED_CHROMATIC)))
    val DARK_POLISHED_CHROMATIC_WALL = registerBlock("dark_polished_chromatic_wall", WallBlock(Settings.copy(DARK_POLISHED_CHROMATIC)))
    
    val DARK_CHROMATIC_PILLAR: Block = registerBlock("dark_chromatic_pillar", PillarBlock(Settings.copy(DARK_CHROMATIC)))
        
    // MIXED CHROMATIC
    val CHROMATIC: Block = registerBlock("chromatic", Block(
        Settings.create()
            .mapColor(MapColor.GRAY)
            .instrument(NoteBlockInstrument.BASEDRUM)
            .sounds(BlockSoundGroup.TUFF)
            .strength(1.5f, 6.0f)
        )
    )
    val CHROMATIC_STAIRS = registerBlock("chromatic_stairs", StairsBlock(CHROMATIC.defaultState, Settings.copy(CHROMATIC)))
    val CHROMATIC_SLAB = registerBlock("chromatic_slab", SlabBlock(Settings.copy(CHROMATIC)))
    val CHROMATIC_WALL = registerBlock("chromatic_wall", WallBlock(Settings.copy(CHROMATIC)))

    val CHROMATIC_BRICKS: Block = registerBlock("chromatic_bricks", Block(Settings.copy(CHROMATIC)))
    val CHROMATIC_BRICK_STAIRS = registerBlock("chromatic_brick_stairs", StairsBlock(CHROMATIC_BRICKS.defaultState, Settings.copy(CHROMATIC_BRICKS)))
    val CHROMATIC_BRICK_SLAB = registerBlock("chromatic_brick_slab", SlabBlock(Settings.copy(CHROMATIC_BRICKS)))
    val CHROMATIC_BRICK_WALL = registerBlock("chromatic_brick_wall", WallBlock(Settings.copy(CHROMATIC_BRICKS)))

    val POLISHED_CHROMATIC: Block = registerBlock("polished_chromatic", Block(Settings.copy(CHROMATIC)))
    val POLISHED_CHROMATIC_STAIRS = registerBlock("polished_chromatic_stairs", StairsBlock(POLISHED_CHROMATIC.defaultState, Settings.copy(POLISHED_CHROMATIC)))
    val POLISHED_CHROMATIC_SLAB = registerBlock("polished_chromatic_slab", SlabBlock(Settings.copy(POLISHED_CHROMATIC)))
    val POLISHED_CHROMATIC_WALL = registerBlock("polished_chromatic_wall", WallBlock(Settings.copy(POLISHED_CHROMATIC)))

    val CHROMATIC_PILLAR: Block = registerBlock("chromatic_pillar", PillarBlock(Settings.copy(CHROMATIC)))

    // LIGHT CHROMATIC
    val LIGHT_CHROMATIC: Block = registerBlock("light_chromatic", Block(
        Settings.create()
            .mapColor(MapColor.OFF_WHITE)
            .instrument(NoteBlockInstrument.BASEDRUM)
            .sounds(BlockSoundGroup.TUFF)
            .strength(1.5f, 6.0f)
        )
    )
    val LIGHT_CHROMATIC_STAIRS = registerBlock("light_chromatic_stairs", StairsBlock(LIGHT_CHROMATIC.defaultState, Settings.copy(LIGHT_CHROMATIC)))
    val LIGHT_CHROMATIC_SLAB = registerBlock("light_chromatic_slab", SlabBlock(Settings.copy(LIGHT_CHROMATIC)))
    val LIGHT_CHROMATIC_WALL = registerBlock("light_chromatic_wall", WallBlock(Settings.copy(LIGHT_CHROMATIC)))

    val LIGHT_CHROMATIC_BRICKS: Block = registerBlock("light_chromatic_bricks", Block(Settings.copy(LIGHT_CHROMATIC)))
    val LIGHT_CHROMATIC_BRICK_STAIRS = registerBlock("light_chromatic_brick_stairs", StairsBlock(LIGHT_CHROMATIC_BRICKS.defaultState, Settings.copy(LIGHT_CHROMATIC_BRICKS)))
    val LIGHT_CHROMATIC_BRICK_SLAB = registerBlock("light_chromatic_brick_slab", SlabBlock(Settings.copy(LIGHT_CHROMATIC_BRICKS)))
    val LIGHT_CHROMATIC_BRICK_WALL = registerBlock("light_chromatic_brick_wall", WallBlock(Settings.copy(LIGHT_CHROMATIC_BRICKS)))

    val LIGHT_POLISHED_CHROMATIC: Block = registerBlock("light_polished_chromatic", Block(Settings.copy(LIGHT_CHROMATIC)))
    val LIGHT_POLISHED_CHROMATIC_STAIRS = registerBlock("light_polished_chromatic_stairs", StairsBlock(LIGHT_POLISHED_CHROMATIC.defaultState, Settings.copy(LIGHT_POLISHED_CHROMATIC)))
    val LIGHT_POLISHED_CHROMATIC_SLAB = registerBlock("light_polished_chromatic_slab", SlabBlock(Settings.copy(LIGHT_POLISHED_CHROMATIC)))
    val LIGHT_POLISHED_CHROMATIC_WALL = registerBlock("light_polished_chromatic_wall", WallBlock(Settings.copy(LIGHT_POLISHED_CHROMATIC)))

    val LIGHT_CHROMATIC_PILLAR: Block = registerBlock("light_chromatic_pillar", PillarBlock(Settings.copy(LIGHT_CHROMATIC)))

    // NEBULITE
    val COMPACTED_NEBULITE: Block = registerBlock("compacted_nebulite", Block(
        Settings.create()
            .mapColor(MapColor.DEEPSLATE_GRAY)
            .instrument(NoteBlockInstrument.BIT)
            .sounds(BlockSoundGroup.COPPER)
            .strength(1.5f, 6.0f)
        )
    )

    val CAUTION_NEBULITE: Block = registerBlock("caution_nebulite", Block(Settings.copy(COMPACTED_NEBULITE)))
    val CAUTION_NEBULITE_STAIRS = registerBlock("caution_nebulite_stairs", StairsBlock(CAUTION_NEBULITE.defaultState, Settings.copy(CAUTION_NEBULITE)))
    val CAUTION_NEBULITE_SLAB = registerBlock("caution_nebulite_slab", SlabBlock(Settings.copy(CAUTION_NEBULITE)))

    val DENTED_NEBULITE: Block = registerBlock("dented_nebulite", Block(Settings.copy(COMPACTED_NEBULITE)))
    val DENTED_NEBULITE_STAIRS = registerBlock("dented_nebulite_stairs", StairsBlock(DENTED_NEBULITE.defaultState, Settings.copy(DENTED_NEBULITE)))
    val DENTED_NEBULITE_SLAB = registerBlock("dented_nebulite_slab", SlabBlock(Settings.copy(DENTED_NEBULITE)))

    val CHISELED_NEBULITE: Block = registerBlock("chiseled_nebulite", Block(Settings.copy(COMPACTED_NEBULITE)))

    val NEBULITE_SUPPORT = registerBlock("nebulite_support", WallBlock(Settings.copy(COMPACTED_NEBULITE)))
    val NEBULITE_SCAFFOLDING = registerScaffoldingBlock("nebulite_scaffolding", NebuliteScaffoldingBlock(Settings.copy(COMPACTED_NEBULITE)))

    val NEBULITE_PILLAR: Block = registerBlock("nebulite_pillar", PillarBlock(Settings.copy(COMPACTED_NEBULITE)))

    // BLOCKS

//    val TEMPORAL_DUSK_LOCK: Block = registerBlock("temporal_dusk_lock", TemporalDuskLockBlock())

    val ENCASED_MIRE: Block = registerBlock("encased_mire", Block(Settings.create().dropsNothing().strength(-1f, 3600000f).sounds(BlockSoundGroup.SOUL_SOIL)))
    val WISPFLARE: Block = registerWaterLilyBlock("wispflare", Wispflare(
        Settings.create()
        .mapColor(MapColor.DARK_GREEN)
        .breakInstantly()
        .sounds(BlockSoundGroup.LILY_PAD)
        .nonOpaque()
        .ticksRandomly()
        .luminance { 5 }
        .pistonBehavior(PistonBehavior.DESTROY)))
    val BEGRIMED_MIRE_FLUID: Block = registerBlockWithoutBlockItem("begrimed_mire_fluid", BegrimedMireFluidBlock(ModFluids.BEGRIMED_MIRE_STILL))

    private fun registerBlockWithoutBlockItem(name: String, block: Block): Block {
        return Registry.register(Registries.BLOCK, Enlighten.identifier(name), block)
    }

    private fun registerBlock(name: String, block: Block): Block {
        val item = Registry.register(
            Registries.ITEM, Enlighten.identifier(name),
            BlockItem(block, Item.Settings())
        )
        ItemGroupEvents.modifyEntriesEvent(ENLIGHTEN_ITEM_GROUP_KEY).register(ModifyEntries { entries: FabricItemGroupEntries -> entries.add(item) })
        return Registry.register(Registries.BLOCK, Enlighten.identifier(name), block)
    }

    private fun registerScaffoldingBlock(name: String, block: ScaffoldingBlock): ScaffoldingBlock {
        val item = Registry.register(
            Registries.ITEM, Enlighten.identifier(name),
            ScaffoldingItem(block, Item.Settings())
        )
        ItemGroupEvents.modifyEntriesEvent(ENLIGHTEN_ITEM_GROUP_KEY).register(ModifyEntries { entries: FabricItemGroupEntries -> entries.add(item) })
        return Registry.register(Registries.BLOCK, Enlighten.identifier(name), block)
    }

    private fun registerWaterLilyBlock(name: String, block: Block): Block {
        val item = Registry.register(
            Registries.ITEM, Enlighten.identifier(name),
            PlaceableOnWaterItem(block, Item.Settings())
        )
        ItemGroupEvents.modifyEntriesEvent(ENLIGHTEN_ITEM_GROUP_KEY).register(ModifyEntries { entries: FabricItemGroupEntries -> entries.add(item) })
        return Registry.register(Registries.BLOCK, Enlighten.identifier(name), block)
    }


    fun registerModBlocks() {
        Enlighten.LOGGER.info("Registering Mod Blocks for " + Enlighten.MOD_ID)
    }

    fun registerClientModBlocks() {
        BlockRenderLayerMap.INSTANCE.putBlocks(RenderLayer.getCutout(), WISPFLARE)
        BlockRenderLayerMap.INSTANCE.putBlocks(RenderLayer.getCutout(), NEBULITE_SCAFFOLDING)
    }

}