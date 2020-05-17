package io.github.haykam821.cameradetach.mixin;

import java.util.List;
import java.util.Locale;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import io.github.haykam821.cameradetach.CameraOverriddenEntity;
import io.github.haykam821.cameradetach.Main;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.hud.DebugHud;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.MathHelper;

@Mixin(DebugHud.class)
public class DebugHudMixin {
	@Unique
	private String getAxisString(Direction direction) {
		switch (direction) {
			case NORTH:
				return "Towards negative Z";
			case SOUTH:
				return "Towards positive Z";
			case WEST:
				return "Towards negative X";
			case EAST:
				return "Towards positive X";
			default:
				return "Invalid";
		}
	}

	@Inject(method = "getLeftText", at = @At(value = "TAIL"))
	public void addCameraFacingText(CallbackInfoReturnable<List<String>> ci) {
		if (!Main.DETACH.isDetached()) return;

		MinecraftClient client = MinecraftClient.getInstance();
		CameraOverriddenEntity cameraOverriddenEntity = (CameraOverriddenEntity) client.player;
		
		Direction direction = Direction.fromRotation(cameraOverriddenEntity.getCameraYaw());
		String axisString = this.getAxisString(direction);

		float wrappedYaw = MathHelper.wrapDegrees(cameraOverriddenEntity.getCameraYaw());
		float wrappedPitch = MathHelper.wrapDegrees(cameraOverriddenEntity.getCameraPitch());

		List<String> leftText = ci.getReturnValue();
		leftText.add(14, String.format(Locale.ROOT, "Camera Facing: %s (%s) (%.1f / %.1f)", direction, axisString, wrappedYaw, wrappedPitch));
	}
}