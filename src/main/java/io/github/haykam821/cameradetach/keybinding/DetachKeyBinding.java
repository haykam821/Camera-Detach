package io.github.haykam821.cameradetach.keybinding;

import org.lwjgl.glfw.GLFW;

import io.github.haykam821.cameradetach.CameraOverriddenEntity;
import net.fabricmc.fabric.api.client.keybinding.FabricKeyBinding;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.util.InputUtil;
import net.minecraft.util.Identifier;

public class DetachKeyBinding extends FabricKeyBinding {
	public DetachKeyBinding(Identifier identifier) {
		super(identifier, InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_LEFT_ALT, "key.categories.misc");
	}

	@Override
	public void setPressed(boolean pressed) {
		if (pressed && !this.isPressed()) {
			MinecraftClient client = MinecraftClient.getInstance();

			CameraOverriddenEntity cameraOverriddenEntity = (CameraOverriddenEntity) client.player;
			cameraOverriddenEntity.setCameraPitch(client.player.pitch);
			cameraOverriddenEntity.setCameraYaw(client.player.yaw);
		}

		super.setPressed(pressed);
	}
}