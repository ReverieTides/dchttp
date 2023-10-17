package com.dchttp;

import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;
import net.runelite.client.config.Range;

@ConfigGroup("dchttp")
public interface dchttpConfig extends Config
{
	@ConfigItem(keyName = "MaxObjectDistance", name = "Max Object Distance", description = "Yolo")
	@Range(min = 0, max = 2400)
	default int reachedDistance()
	{
		return 1200;
	}
}