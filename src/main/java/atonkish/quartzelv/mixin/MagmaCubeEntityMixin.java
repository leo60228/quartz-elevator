package atonkish.quartzelv.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.MagmaCubeEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;

import atonkish.quartzelv.QuartzElevatorMod;
import atonkish.quartzelv.utils.MixinUtil;

@Mixin(MagmaCubeEntity.class)
public abstract class MagmaCubeEntityMixin extends Entity {
    public MagmaCubeEntityMixin(EntityType<?> type, World world) {
        super(type, world);
    }

    @Inject(at = @At("HEAD"), method = "jump", cancellable = true)
    private void jump(CallbackInfo info) {
        if (!QuartzElevatorMod.CONFIG.isPlayerOnly) {
            // `isPlayerOnly`: false -> Magma Cube entities can also teleport
            MixinUtil.teleportUp(world, getBlockPos(), getBoundingBox(), (Double y) -> {
                if (world instanceof ServerWorld) {
                    refreshPositionAfterTeleport(getX(), y, getZ());
                } else {
                    teleport(getX(), y, getZ());
                }
                return (Void) null;
            });
        }
    }
}
