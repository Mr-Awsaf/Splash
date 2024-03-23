package com.mrawsaf.splash;

import net.minecraft.world.item.PotionItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraft.world.item.alchemy.Potions;


@Mod.EventBusSubscriber(modid = Splash.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class EventHandler {

    @SubscribeEvent
    public static void onWaterPotionDrink(LivingEntityUseItemEvent.Finish event){
        ItemStack item = event.getItem();
        if(item.getItem() instanceof PotionItem){
            if(PotionUtils.getPotion(item) == Potions.WATER){
                if(event.getEntity().isOnFire()){
                    event.getEntity().clearFire();
                }
            }
        }
    }

}
