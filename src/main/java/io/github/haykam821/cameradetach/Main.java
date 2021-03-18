package io.github.haykam821.cameradetach;

import io.github.haykam821.cameradetach.config.ModConfig;
import io.github.haykam821.cameradetach.keybinding.DetachKeyBinding;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.GsonConfigSerializer;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.util.Identifier;

public class Main implements ClientModInitializer {
	private static final String MOD_ID = "cameradetach";

	private static final Identifier DETACH_ID = new Identifier(MOD_ID, "detach");
	public static final DetachKeyBinding DETACH = new DetachKeyBinding(DETACH_ID);

	public static final ModConfig CONFIG = AutoConfig.register(ModConfig.class, GsonConfigSerializer::new).getConfig();

	@Override
	public void onInitializeClient() {
		KeyBindingHelper.registerKeyBinding(DETACH);
	}
}