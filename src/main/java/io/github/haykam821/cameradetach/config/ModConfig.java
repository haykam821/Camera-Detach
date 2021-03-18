package io.github.haykam821.cameradetach.config;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;

@Config(name = "cameradetach")
@Config.Gui.Background("minecraft:textures/block/gray_wool.png")
public class ModConfig implements ConfigData {
	@ConfigEntry.Gui.Tooltip(count = 2)
	public boolean resetCamera = true;

	@ConfigEntry.Gui.Tooltip
	public boolean toggleDetachment = false;
}