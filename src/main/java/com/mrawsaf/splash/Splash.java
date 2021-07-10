package com.mrawsaf.splash;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;

@Mod(Splash.MOD_ID)
public class Splash
{
    public static final String MOD_ID = "splash";

    public Splash() {
        MinecraftForge.EVENT_BUS.register(this);
    }

}
