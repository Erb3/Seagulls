package github.erb3.fabric.seagulls.entity;

import github.erb3.fabric.seagulls.SeagullsClient;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.util.Identifier;

public class SeagullEntityRenderer extends MobEntityRenderer<SeagullEntity, SeagullEntityModel> {
    public SeagullEntityRenderer(EntityRendererFactory.Context context) {
        super(context, new SeagullEntityModel(context.getPart(SeagullsClient.MODEL_SEAGULL_LAYER)), 0.5f);
    }

    @Override
    public Identifier getTexture(SeagullEntity entity) {
        return new Identifier("seagulls", "textures/entity/seagull/seagull.png");
    }
}
