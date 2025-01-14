package net.dustley.enlighten.client;

import net.dustley.enlighten.Enlighten;
import net.fabricmc.fabric.api.client.model.loading.v1.ModelLoadingPlugin;
import net.minecraft.client.util.ModelIdentifier;

import java.util.ArrayList;
import java.util.List;

public class CustomModelLoadingPlugin implements ModelLoadingPlugin {
    public static final List<ModelIdentifier> MODELS = new ArrayList<>();

    // Static block to add models on class load
    static {
        // Add your custom model identifiers here
        MODELS.add(new ModelIdentifier(Enlighten.INSTANCE.identifier("ebonized_wish_inventory"), "inventory"));
        MODELS.add(new ModelIdentifier(Enlighten.INSTANCE.identifier("shard_cannon_inventory"), "inventory"));
    }

    @Override
    public void onInitializeModelLoader(Context pluginContext) {
        pluginContext.addModels(MODELS.stream().map(l -> l.id().withPrefixedPath("item/")).toList());
        pluginContext.addModels(MODELS.stream().map(ModelIdentifier::id).toList());
    }
}
