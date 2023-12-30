package github.erb3.fabric.seagulls.entity;

import net.minecraft.entity.AnimationState;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.control.FlightMoveControl;
import net.minecraft.entity.ai.control.MoveControl;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.ai.pathing.PathNodeType;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.FlyingEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;

import java.util.EnumSet;

public class SeagullEntity extends FlyingEntity {
    public SeagullEntity(EntityType<? extends FlyingEntity> entityType, World world) {
        super(entityType, world);
        this.moveControl = new FlightMoveControl(this, 10, true);
        this.setPathfindingPenalty(PathNodeType.DAMAGE_FIRE, -16);
        this.setPathfindingPenalty(PathNodeType.STICKY_HONEY, -8);
        this.setPathfindingPenalty(PathNodeType.WATER, 16);
        this.setPathfindingPenalty(PathNodeType.WATER_BORDER, 32);
    }

    public final AnimationState flyingAnimationState = new AnimationState();
    public final AnimationState screamingAnimationState = new AnimationState();

    protected void initGoals() {
        this.goalSelector.add(7, new LookAroundGoal(this));
        this.goalSelector.add(12, new LookAtEntityGoal(this, PlayerEntity.class, 6.0F));
        this.goalSelector.add(32, new FlyRandomlyGoal(this));
    }

    public static DefaultAttributeContainer.Builder attributes() {
        return MobEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_FLYING_SPEED, 0.7D);
    }

    @Override
    public Text getStyledDisplayName() {
        return super.getStyledDisplayName();
    }

    @Override
    public boolean cannotBeSilenced() {
        return true;
    }

    static class FlyRandomlyGoal extends Goal {
        private final SeagullEntity seagull;

        public FlyRandomlyGoal(SeagullEntity seagull) {
            this.seagull = seagull;
            this.setControls(EnumSet.of(Goal.Control.MOVE));
        }

        @Override
        public boolean canStart() {
            MoveControl moveControl = this.seagull.getMoveControl();
            if (!moveControl.isMoving()) {
                return true;
            } else {
                double d = moveControl.getTargetX() - this.seagull.getX();
                double e = moveControl.getTargetY() - this.seagull.getY();
                double f = moveControl.getTargetZ() - this.seagull.getZ();
                double g = d * d + e * e + f * f;
                return g < 1.0 || g > 3600.0;
            }
        }

        @Override
        public boolean shouldContinue() {
            return false;
        }

        @Override
        public void start() {
            Random random = this.seagull.getRandom();
            double d = this.seagull.getX() + (double)((random.nextFloat() * 2.0F - 1.0F) * 16.0F);
            double e = this.seagull.getY() + (double)((random.nextFloat() * 2.0F - 1.0F) * 16.0F);
            double f = this.seagull.getZ() + (double)((random.nextFloat() * 2.0F - 1.0F) * 16.0F);
            this.seagull.getMoveControl().moveTo(d, e, f, 1.0);
        }
    }
}
