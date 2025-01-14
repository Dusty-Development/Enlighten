package net.dustley.enlighten.mixin.inventory;

import net.dustley.enlighten.block.ModBlocks;
import net.dustley.enlighten.item.ModItems;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.RespawnAnchorBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Hand;
import net.minecraft.util.ItemActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static net.minecraft.block.RespawnAnchorBlock.CHARGES;

@Mixin(RespawnAnchorBlock.class)
public class RespawnAnchorRecipesMixin {

    @Inject(method = "onUseWithItem", at = @At("HEAD"), cancellable = true)
    private void useWithItem(ItemStack stack, BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit, CallbackInfoReturnable<ItemActionResult> cir) {
        if(state.get(CHARGES) > 0) {
            Item darkChromaticItem = ModBlocks.INSTANCE.getDARK_CHROMATIC().asItem();
            Item lightChromaticItem = ModBlocks.INSTANCE.getLIGHT_CHROMATIC().asItem();
            Item dentedNebuliteItem = ModBlocks.INSTANCE.getDENTED_NEBULITE().asItem();
//            Item nebuliteItem = ModBlocks.INSTANCE.getDARK_CHROMATIC().asItem();
            Item volumetricAnchorItem = ModItems.INSTANCE.getVOLUMETRIC_ANCHOR();
            Item snareStoneItem = ModItems.INSTANCE.getSNARE_STONE();
            Item snatchSlateItem = ModItems.INSTANCE.getSNATCH_SLATE();

            Item wishContractItem = ModItems.INSTANCE.getWISH_CONTRACT();
            Item enlightenmentContractItem = ModItems.INSTANCE.getENLIGHTENMENT_CONTRACT();

            if(stack.isOf(Blocks.IRON_BLOCK.asItem()) && stack.getCount() >= 2) {
                ItemStack newStack = dentedNebuliteItem.getDefaultStack();
                newStack.setCount(stack.getCount() / 2);
                stack.decrement(stack.getCount());
                player.giveItemStack(newStack);

                world.playSound(null, (double)pos.getX() + 0.5, (double)pos.getY() + 0.5, (double)pos.getZ() + 0.5, SoundEvents.BLOCK_RESPAWN_ANCHOR_CHARGE, SoundCategory.BLOCKS, 1.0F, 1.0F);
                cir.setReturnValue(ItemActionResult.SUCCESS);
                cir.cancel();
            }

            if(stack.isOf(Items.IRON_INGOT) && stack.getCount() >= 2) {
                ItemStack newStack = dentedNebuliteItem.getDefaultStack();
                newStack.setCount(stack.getCount() / 2);
                stack.decrement(stack.getCount());
                player.giveItemStack(newStack);

                world.playSound(null, (double)pos.getX() + 0.5, (double)pos.getY() + 0.5, (double)pos.getZ() + 0.5, SoundEvents.BLOCK_RESPAWN_ANCHOR_CHARGE, SoundCategory.BLOCKS, 1.0F, 1.0F);
                cir.setReturnValue(ItemActionResult.SUCCESS);
                cir.cancel();
            }

            if(stack.isOf(Blocks.DEEPSLATE.asItem()) || stack.isOf(Blocks.COBBLED_DEEPSLATE.asItem())) {
                ItemStack newStack = darkChromaticItem.getDefaultStack();
                newStack.setCount(stack.getCount());
                stack.decrement(stack.getCount());
                player.setStackInHand(hand, newStack);

                world.playSound(null, (double)pos.getX() + 0.5, (double)pos.getY() + 0.5, (double)pos.getZ() + 0.5, SoundEvents.BLOCK_RESPAWN_ANCHOR_CHARGE, SoundCategory.BLOCKS, 1.0F, 1.0F);
                cir.setReturnValue(ItemActionResult.SUCCESS);
                cir.cancel();
            }

            if(stack.isOf(Blocks.STONE.asItem()) || stack.isOf(Blocks.COBBLESTONE.asItem())) {
                ItemStack newStack = lightChromaticItem.getDefaultStack();
                newStack.setCount(stack.getCount());
                stack.decrement(stack.getCount());
                player.setStackInHand(hand, newStack);

                world.playSound(null, (double)pos.getX() + 0.5, (double)pos.getY() + 0.5, (double)pos.getZ() + 0.5, SoundEvents.BLOCK_RESPAWN_ANCHOR_CHARGE, SoundCategory.BLOCKS, 1.0F, 1.0F);
                cir.setReturnValue(ItemActionResult.SUCCESS);
                cir.cancel();
            }

            if(stack.isOf(ModItems.INSTANCE.getINCOMPLETE_VOLUMETRIC_ANCHOR()) && state.get(CHARGES) == 2) {
                ItemStack newStack = volumetricAnchorItem.getDefaultStack();
                newStack.setCount(stack.getCount());
                stack.decrement(stack.getCount());
                player.setStackInHand(hand, newStack);

                world.playSound(null, (double)pos.getX() + 0.5, (double)pos.getY() + 0.5, (double)pos.getZ() + 0.5, SoundEvents.BLOCK_RESPAWN_ANCHOR_SET_SPAWN, SoundCategory.BLOCKS, 1.0F, 1.0F);
                cir.setReturnValue(ItemActionResult.SUCCESS);
                cir.cancel();
            }

            if(stack.isOf(Items.WARD_ARMOR_TRIM_SMITHING_TEMPLATE) && state.get(CHARGES) == 2) {
                ItemStack newStack = snareStoneItem.getDefaultStack();
                newStack.setCount(stack.getCount());
                stack.decrement(stack.getCount());
                player.setStackInHand(hand, newStack);

                world.playSound(null, (double)pos.getX() + 0.5, (double)pos.getY() + 0.5, (double)pos.getZ() + 0.5, SoundEvents.BLOCK_RESPAWN_ANCHOR_SET_SPAWN, SoundCategory.BLOCKS, 1.0F, 1.0F);
                cir.setReturnValue(ItemActionResult.SUCCESS);
                cir.cancel();
            }

            if(stack.isOf(Items.RECOVERY_COMPASS) && state.get(CHARGES) == 3) {
                ItemStack newStack = snatchSlateItem.getDefaultStack();
                newStack.setCount(stack.getCount());
                stack.decrement(stack.getCount());
                player.setStackInHand(hand, newStack);

                world.playSound(null, (double)pos.getX() + 0.5, (double)pos.getY() + 0.5, (double)pos.getZ() + 0.5, SoundEvents.BLOCK_RESPAWN_ANCHOR_SET_SPAWN, SoundCategory.BLOCKS, 1.0F, 1.0F);
                cir.setReturnValue(ItemActionResult.SUCCESS);
                cir.cancel();
            }

            if(stack.isOf(Items.WRITTEN_BOOK) && state.get(CHARGES) == 1) {
                ItemStack newStack = wishContractItem.getDefaultStack();
                newStack.setCount(stack.getCount());
                stack.decrement(stack.getCount());
                player.setStackInHand(hand, newStack);

                world.playSound(null, (double)pos.getX() + 0.5, (double)pos.getY() + 0.5, (double)pos.getZ() + 0.5, SoundEvents.BLOCK_RESPAWN_ANCHOR_SET_SPAWN, SoundCategory.BLOCKS, 1.0F, 1.0F);
                cir.setReturnValue(ItemActionResult.SUCCESS);
                cir.cancel();
            }

            if(stack.isOf(Items.WRITTEN_BOOK) && state.get(CHARGES) > 1) {
                ItemStack newStack = enlightenmentContractItem.getDefaultStack();
                newStack.setCount(stack.getCount());
                stack.decrement(stack.getCount());
                player.setStackInHand(hand, newStack);

                world.playSound(null, (double)pos.getX() + 0.5, (double)pos.getY() + 0.5, (double)pos.getZ() + 0.5, SoundEvents.BLOCK_RESPAWN_ANCHOR_SET_SPAWN, SoundCategory.BLOCKS, 1.0F, 1.0F);
                cir.setReturnValue(ItemActionResult.SUCCESS);
                cir.cancel();
            }
        }
    }

    @Inject(method = "explode", at = @At("HEAD"), cancellable = true)
    private void explode(BlockState state, World world, BlockPos explodedPos, CallbackInfo ci) { ci.cancel(); }

}
