package net.dustley.enlighten.client;

import net.dustley.enlighten.Enlighten;
import net.minecraft.client.render.model.BakedModel;
import net.minecraft.client.render.model.json.ModelTransformationMode;
import net.minecraft.client.util.ModelIdentifier;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public class ClientSetup {
    public static final Map<ModelIdentifier, Map<ModelTransformationMode, ModelIdentifier>> LARGE_MODEL = new HashMap<>();

    public static final Map<ModelIdentifier, BakedModel> BAKED_MODELS = new HashMap<>();

    public static ModelIdentifier getCustomModel(ModelIdentifier item, ModelTransformationMode context) {
        return LARGE_MODEL.containsKey(item) && LARGE_MODEL.get(item).containsKey(context) ? new ModelIdentifier(LARGE_MODEL.get(item).get(context).id().withPrefixedPath("item/"), "fabric_resource") : item;
    }

    public static void modifyBakingResult(Map<ModelIdentifier, BakedModel> models) {
        for (ModelIdentifier id : models.keySet()) {
            BAKED_MODELS.put(id, models.get(id));
            System.out.println(id);
        }

    }
    
    private static void registerSimpleSpecialModel(String id) {
        LARGE_MODEL.put(ModelIdentifier.ofInventoryVariant(Enlighten.INSTANCE.identifier(id)), Map.of(
                ModelTransformationMode.GUI, ModelIdentifier.ofInventoryVariant(Enlighten.INSTANCE.identifier(id + "_inventory")),
                ModelTransformationMode.GROUND, ModelIdentifier.ofInventoryVariant(Enlighten.INSTANCE.identifier(id + "_inventory"))
        ));
    }
    
    public static void registerExtraBakedModels(Consumer<ModelIdentifier> registration) {
        System.out.println("registered models");
        registration.accept(ModelIdentifier.ofInventoryVariant(Enlighten.INSTANCE.identifier("ebonized_wish_inventory")));
        registration.accept(ModelIdentifier.ofInventoryVariant(Enlighten.INSTANCE.identifier("shard_cannon_inventory")));
    }

    public static void clientSetup() {
        registerSimpleSpecialModel("ebonized_wish");
        registerSimpleSpecialModel("shard_cannon");
        System.out.println("client was called");
    }

}