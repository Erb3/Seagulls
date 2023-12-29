package github.erb3.fabric.seagulls.entity;

import github.erb3.fabric.seagulls.Seagulls;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.AboveGroundTargeting;
import net.minecraft.entity.ai.NoPenaltySolidTargeting;
import net.minecraft.entity.ai.control.FlightMoveControl;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.ai.pathing.PathNodeType;
import net.minecraft.entity.attribute.AttributeContainer;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.FlyingEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.BeeEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.EnumSet;

public class SeagullEntity extends AnimalEntity {
    private static final Ingredient BREEDING_INGREDIENT = Ingredient.ofItems(Items.BREAD);

    public SeagullEntity(EntityType<? extends AnimalEntity> entityType, World world) {
        super(entityType, world);
        this.moveControl = new FlightMoveControl(this, 10, true);
        this.setPathfindingPenalty(PathNodeType.DAMAGE_FIRE, -16);
        this.setPathfindingPenalty(PathNodeType.STICKY_HONEY, -8);
        this.setPathfindingPenalty(PathNodeType.WATER, 16);
        this.setPathfindingPenalty(PathNodeType.WATER_BORDER, 32);
    }

    protected void initGoals() {
        this.goalSelector.add(1, new EscapeDangerGoal(this, 1.4));
        this.goalSelector.add(2, new AnimalMateGoal(this, 1.0));
        this.goalSelector.add(2, new LookAtEntityGoal(this, PlayerEntity.class, 6.0F));
        this.goalSelector.add(3, new TemptGoal(this, 1.0, BREEDING_INGREDIENT, false));
        this.goalSelector.add(4, new FollowParentGoal(this, 1.25));
        this.goalSelector.add(5, new LookAroundGoal(this));
//        this.goalSelector.add(32, new FlyGoal(this, 6.0F));
        this.goalSelector.add(32, new SeagullWanderAroundGoal(this));
    }

    public static DefaultAttributeContainer.Builder attributes() {
        return MobEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_FLYING_SPEED, 0.7D);
    }

    @Nullable
    @Override
    public PassiveEntity createChild(ServerWorld world, PassiveEntity entity) {
        return Seagulls.SEAGULL.create(world);
    }

    @Override
    public Text getStyledDisplayName() {
        return super.getStyledDisplayName();
    }

    @Override
    public boolean cannotBeSilenced() {
        return true;
    }

    public boolean isBreedingItem(ItemStack stack) {
        return BREEDING_INGREDIENT.test(stack);
    }

    static class SeagullWanderAroundGoal extends WanderAroundFarGoal {

        public SeagullWanderAroundGoal(PathAwareEntity pathAwareEntity) {
            super(pathAwareEntity, 2.5f);
        }

        @Nullable
        protected Vec3d getWanderTarget() {
            Vec3d vec3d = this.mob.getRotationVec(0.0F);
            Vec3d vec3d2 = AboveGroundTargeting.find(this.mob, 8, 7, vec3d.x, vec3d.z, (float) (Math.PI / 2), 3, 1);
            return vec3d2 != null ? vec3d2 : NoPenaltySolidTargeting.find(this.mob, 8, 4, -2, vec3d.x, vec3d.z, (float) (Math.PI / 2));
        }
    }
}
