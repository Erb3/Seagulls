package github.erb3.fabric.seagulls;

import github.erb3.fabric.seagulls.entity.SeagullEntityModel;
import github.erb3.fabric.seagulls.entity.SeagullEntityRenderer;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.util.Identifier;

public class SeagullsClient implements ClientModInitializer {
    public static final EntityModelLayer MODEL_SEAGULL_LAYER = new EntityModelLayer(new Identifier("seagulls", "seagull"), "main");


    @Override
    public void onInitializeClient() {
        EntityRendererRegistry.register(Seagulls.SEAGULL, SeagullEntityRenderer::new);
        EntityModelLayerRegistry.registerModelLayer(MODEL_SEAGULL_LAYER, SeagullEntityModel::getTexturedModelData);
    }
}
