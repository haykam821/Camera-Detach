package io.github.haykam821.cameradetach.keybinding;

import org.lwjgl.glfw.GLFW;

import io.github.haykam821.cameradetach.CameraOverriddenEntity;
import io.github.haykam821.cameradetach.Main;
import net.fabricmc.fabric.api.client.keybinding.FabricKeyBinding;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.util.InputUtil;
import net.minecraft.util.Identifier;

public class DetachKeyBinding extends FabricKeyBinding {
	public DetachKeyBinding(Identifier identifier) {
		super(identifier, InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_LEFT_ALT, "key.categories.misc");
	}

	private boolean detached = false;

	@Override
	public void setPressed(boolean pressed) {
		super.setPressed(pressed);

		MinecraftClient client = MinecraftClient.getInstance();
		CameraOverriddenEntity cameraOverriddenEntity = (CameraOverriddenEntity) client.player;

		if (!this.wasPressed() && cameraOverriddenEntity != null) {
			if (Main.CONFIG.toggleDetachment) {
				if (detached) {
					this.onReattach(client, cameraOverriddenEntity);
				} else {
					this.onDetach(client, cameraOverriddenEntity);
				}
			} else if (pressed) {
				this.onDetach(client, cameraOverriddenEntity);
			} else {
				this.onReattach(client, cameraOverriddenEntity);
			}
		}
	}

	public void onDetach(MinecraftClient client, CameraOverriddenEntity cameraOverriddenEntity) {
		detached = true;

		cameraOverriddenEntity.setCameraPitch(client.player.pitch);
		cameraOverriddenEntity.setCameraYaw(client.player.yaw);
	}

	public void onReattach(MinecraftClient client, CameraOverriddenEntity cameraOverriddenEntity) {
		detached = false;

		client.player.lastRenderPitch = client.player.renderPitch = cameraOverriddenEntity.getCameraPitch();
		client.player.lastRenderYaw = client.player.renderYaw = cameraOverriddenEntity.getCameraYaw();

		if (!Main.CONFIG.resetCamera) {
			client.player.pitch = client.player.lastRenderPitch;
			client.player.yaw = client.player.lastRenderYaw;
		}
	}

	public boolean isDetached() {
		return this.detached;
	}
}