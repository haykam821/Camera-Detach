package io.github.haykam821.cameradetach.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import io.github.haykam821.cameradetach.CameraOverriddenEntity;
import io.github.haykam821.cameradetach.Main;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.render.Camera;
import net.minecraft.entity.Entity;
import net.minecraft.world.BlockView;

@Mixin(Camera.class)
public abstract class CameraMixin {
	@Shadow
	public abstract void setRotation(float yaw, float pitch);

	@Inject(method = "update", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/render/Camera;setRotation(FF)V", shift = At.Shift.AFTER))
	public void lockRotation(BlockView focusedBlock, Entity cameraEntity, boolean isThirdPerson, boolean isFrontFacing, float f, CallbackInfo ci) {
		if (Main.DETACH.isDetached() && cameraEntity instanceof ClientPlayerEntity) {
			CameraOverriddenEntity cameraOverriddenEntity = (CameraOverriddenEntity) cameraEntity;
			this.setRotation(cameraOverriddenEntity.getCameraYaw(), cameraOverriddenEntity.getCameraPitch());
		}
	}
}