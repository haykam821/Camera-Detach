package io.github.haykam821.cameradetach.config;

import me.sargunvohra.mcmods.autoconfig1u.ConfigData;
import me.sargunvohra.mcmods.autoconfig1u.annotation.Config;
import me.sargunvohra.mcmods.autoconfig1u.annotation.ConfigEntry;

@Config(name = "cameradetach")
@Config.Gui.Background("minecraft:textures/block/gray_wool.png")
public class ModConfig implements ConfigData {
	@ConfigEntry.Gui.Tooltip(count = 2)
	public boolean resetCamera = true;

	@ConfigEntry.Gui.Tooltip
	public boolean toggleDetachment = false;
}