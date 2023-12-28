package github.erb3.fabric.seagulls.entity;

import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.util.math.MatrixStack;

public class SeagullEntityModel extends EntityModel<SeagullEntity> {
    private final ModelPart feet;
    private final ModelPart legs;
    private final ModelPart body;
    private final ModelPart face;
    private final ModelPart bb_main;

    public SeagullEntityModel(ModelPart root) {
        this.feet = root.getChild("feet");
        this.legs = root.getChild("legs");
        this.body = root.getChild("body");
        this.face = root.getChild("face");
        this.bb_main = root.getChild("bb_main");
    }

    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();
        
        modelPartData.addChild("feet", ModelPartBuilder.create().uv(22, 3).cuboid(-2.0F, -1.0F, -2.0F, 3.0F, 1.0F, 1.0F, new Dilation(0.0F))
                .uv(0, 17).cuboid(-1.0F, -1.0F, 0.0F, 1.0F, 1.0F, 3.0F, new Dilation(0.0F))
                .uv(4, 21).cuboid(-2.0F, -1.0F, 1.0F, 3.0F, 1.0F, 1.0F, new Dilation(0.0F))
                .uv(8, 17).cuboid(-1.0F, -1.0F, -3.0F, 1.0F, 1.0F, 3.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 24.0F, 0.0F));

        modelPartData.addChild("legs", ModelPartBuilder.create().uv(0, 9).cuboid(-1.0F, -4.0F, 1.0F, 1.0F, 3.0F, 1.0F, new Dilation(0.0F))
                .uv(0, 0).cuboid(-1.0F, -4.0F, -2.0F, 1.0F, 3.0F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 24.0F, 0.0F));

        modelPartData.addChild("body", ModelPartBuilder.create().uv(14, 9).cuboid(-3.0F, -9.0F, -3.0F, 5.0F, 4.0F, 1.0F, new Dilation(0.0F))
                .uv(0, 0).cuboid(-4.0F, -9.0F, -2.0F, 7.0F, 5.0F, 4.0F, new Dilation(0.0F))
                .uv(14, 14).cuboid(-3.0F, -9.0F, 2.0F, 5.0F, 4.0F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 24.0F, 0.0F));

        modelPartData.addChild("face", ModelPartBuilder.create().uv(0, 9).cuboid(2.0F, -13.0F, -2.0F, 3.0F, 4.0F, 4.0F, new Dilation(0.0F))
                .uv(5, 17).cuboid(3.0F, -9.0F, -1.0F, 1.0F, 1.0F, 2.0F, new Dilation(0.0F))
                .uv(0, 21).cuboid(1.0F, -11.0F, -1.0F, 1.0F, 2.0F, 2.0F, new Dilation(0.0F))
                .uv(20, 20).cuboid(-1.0F, -10.0F, -1.0F, 2.0F, 1.0F, 2.0F, new Dilation(0.0F))
                .uv(14, 19).cuboid(5.0F, -11.0F, -1.0F, 2.0F, 1.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 24.0F, 0.0F));

        modelPartData.addChild("bb_main", ModelPartBuilder.create().uv(18, 0).cuboid(-6.0F, -5.0F, -1.0F, 2.0F, 1.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 24.0F, 0.0F));
        return TexturedModelData.of(modelData, 32, 32);
    }

    @Override
    public void setAngles(SeagullEntity entity, float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch) {

    }

    @Override
    public void render(MatrixStack matrices, VertexConsumer vertices, int light, int overlay, float red, float green, float blue, float alpha) {
        feet.render(matrices, vertices, light, overlay, red, green, blue, alpha);
        legs.render(matrices, vertices, light, overlay, red, green, blue, alpha);
        body.render(matrices, vertices, light, overlay, red, green, blue, alpha);
        face.render(matrices, vertices, light, overlay, red, green, blue, alpha);
        bb_main.render(matrices, vertices, light, overlay, red, green, blue, alpha);
    }
}
