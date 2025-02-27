package net.dustley.enlighten.item

import dev.emi.trinkets.api.client.TrinketRendererRegistry
import net.dustley.enlighten.Enlighten
import net.dustley.enlighten.fluid.ModFluids
import net.dustley.enlighten.item.armor.MireCloakItem
import net.dustley.enlighten.item.trinket.mire_flower_clip.MireFlowerClipItem
import net.dustley.enlighten.item.tool.begrimed_chorus_fruit.BegrimedChorusFruitItem
import net.dustley.enlighten.item.tool.contract.EnlightenmentContractItem
import net.dustley.enlighten.item.tool.contract.WishContractItem
import net.dustley.enlighten.item.tool.ebonized_wish.EbonizedWishItem
import net.dustley.enlighten.item.tool.snare_stone.SnareStoneItem
import net.dustley.enlighten.item.tool.snatch_slate.SnatchSlateItem
import net.dustley.enlighten.item.tool.volumetric_anchor.VolumetricAnchorItem
import net.dustley.enlighten.item.trinket.mire_flower_clip.MireFlowerClipRenderer
import net.dustley.enlighten.item.trinket.mire_monocle.MireMonocleItem
import net.dustley.enlighten.item.trinket.mire_monocle.MireMonocleRenderer
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents
import net.minecraft.item.*
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.registry.RegistryKey
import net.minecraft.text.Text


object ModItems {
    // Item group
    val ENLIGHTEN_ITEM_GROUP_KEY: RegistryKey<ItemGroup> =
        RegistryKey.of(Registries.ITEM_GROUP.key, Enlighten.identifier("item_group"))
    val ENLIGHTEN_ITEM_GROUP: ItemGroup = FabricItemGroup.builder()
        .icon { ItemStack(BEGRIMED_CHORUS_FRUIT) }
        .displayName(Text.translatable("itemGroup.${Enlighten.MOD_ID}"))
        .build()

    init { Registry.register(Registries.ITEM_GROUP, ENLIGHTEN_ITEM_GROUP_KEY, ENLIGHTEN_ITEM_GROUP) }

    // Items
    val BEGRIMED_MIRE_BUCKET = registerItem("begrimed_mire_bucket", BucketItem(ModFluids.BEGRIMED_MIRE_STILL, Item.Settings().maxCount(1).recipeRemainder(Items.BUCKET)))
    val BEGRIMED_MIRE_CLOTH = registerItem("begrimed_mire_cloth", Item(Item.Settings()))
    val BEGRIMED_CHORUS_FRUIT = registerItem("begrimed_chorus_fruit", BegrimedChorusFruitItem())
    val VOLUMETRIC_ANCHOR = registerItem("volumetric_anchor", VolumetricAnchorItem())
    val INCOMPLETE_VOLUMETRIC_ANCHOR = registerItem("incomplete_volumetric_anchor", Item(Item.Settings().maxCount(1)))
    val SNARE_STONE = registerItem("snare_stone", SnareStoneItem())
    val SNATCH_SLATE = registerItem("snatch_slate", SnatchSlateItem())
//    val SHARD_CANNON = registerItem("shard_cannon", ShardCannonItem())
    val ENLIGHTENMENT_CONTRACT = registerItem("enlightenment_contract", EnlightenmentContractItem())
    val WISH_CONTRACT = registerItem("wish_contract", WishContractItem())
    val EBONIZED_WISH = registerItem("ebonized_wish", EbonizedWishItem())

    val MIRE_CLOAK = registerItem("mire_cloak", MireCloakItem())
    val MIRE_ROSE_CLIP = registerItem("mire_rose_clip", MireFlowerClipItem())
    val MIRE_DANDELION_CLIP = registerItem("mire_dandelion_clip", MireFlowerClipItem())
    val MIRE_PEONY_CLIP = registerItem("mire_peony_clip", MireFlowerClipItem())
    val MIRE_LILY_CLIP = registerItem("mire_lily_clip", MireFlowerClipItem())
    val MIRE_WISP_CLIP = registerItem("mire_wisp_clip", MireFlowerClipItem())
    val MIRE_MONOCLE = registerItem("mire_monocle", MireMonocleItem())

    private fun registerItem(name: String, item: Item): Item {
        val it = Registry.register(Registries.ITEM, Enlighten.identifier(name), item)
        ItemGroupEvents.modifyEntriesEvent(ENLIGHTEN_ITEM_GROUP_KEY).register(ItemGroupEvents.ModifyEntries { entries: FabricItemGroupEntries -> entries.add(it) })
        return it
    }

    fun registerModItems() {
        Enlighten.LOGGER.info("Registering Mod Items for " + Enlighten.MOD_ID)

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.TOOLS)
            .register(ItemGroupEvents.ModifyEntries { entries: FabricItemGroupEntries ->
                entries.add(BEGRIMED_MIRE_BUCKET)
                entries.add(BEGRIMED_MIRE_CLOTH)
                entries.add(VOLUMETRIC_ANCHOR)
                entries.add(SNARE_STONE)
            })

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.FOOD_AND_DRINK)
            .register(ItemGroupEvents.ModifyEntries { entries: FabricItemGroupEntries ->
                entries.add(BEGRIMED_CHORUS_FRUIT)
            })

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.NATURAL)
            .register(ItemGroupEvents.ModifyEntries { entries: FabricItemGroupEntries ->
                entries.add(MIRE_ROSE_CLIP)
                entries.add(MIRE_DANDELION_CLIP)
                entries.add(MIRE_PEONY_CLIP)
                entries.add(MIRE_LILY_CLIP )
                entries.add(MIRE_WISP_CLIP )
                entries.add(MIRE_MONOCLE)
            })

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT)
            .register(ItemGroupEvents.ModifyEntries { entries: FabricItemGroupEntries ->
                entries.add(MIRE_CLOAK)
                entries.add(MIRE_ROSE_CLIP)
                entries.add(MIRE_DANDELION_CLIP)
                entries.add(MIRE_PEONY_CLIP)
                entries.add(MIRE_LILY_CLIP)
                entries.add(MIRE_WISP_CLIP)
                entries.add(MIRE_MONOCLE)
            })
    }

    fun registerClientModItems() {
        // peony
        // rose
        // dandelion
        // lily
        // wisp
        TrinketRendererRegistry.registerRenderer(MIRE_ROSE_CLIP, MireFlowerClipRenderer("rose"))
        TrinketRendererRegistry.registerRenderer(MIRE_DANDELION_CLIP, MireFlowerClipRenderer("dandelion"))
        TrinketRendererRegistry.registerRenderer(MIRE_PEONY_CLIP, MireFlowerClipRenderer("peony"))
        TrinketRendererRegistry.registerRenderer(MIRE_LILY_CLIP, MireFlowerClipRenderer("lily"))
        TrinketRendererRegistry.registerRenderer(MIRE_WISP_CLIP, MireFlowerClipRenderer("wisp"))
        TrinketRendererRegistry.registerRenderer(MIRE_MONOCLE, MireMonocleRenderer())
    }
}