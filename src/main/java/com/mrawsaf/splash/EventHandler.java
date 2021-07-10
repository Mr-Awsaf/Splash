package com.mrawsaf.splash;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.PotionEntity;
import net.minecraft.entity.projectile.ProjectileItemEntity;
import net.minecraft.entity.projectile.ThrowableEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.PotionItem;
import net.minecraft.potion.*;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraftforge.event.entity.ProjectileImpactEvent;
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.function.Predicate;

@Mod.EventBusSubscriber(modid = Splash.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class EventHandler {

    @SubscribeEvent
    public static void onWaterPotionThrown(final ProjectileImpactEvent.Throwable event) {
        ThrowableEntity throwable = event.getThrowable();
        if(throwable instanceof PotionEntity){
            if(PotionUtils.getPotion(((ProjectileItemEntity)throwable).getItem()) == Potions.WATER){
                AxisAlignedBB area = throwable.getBoundingBox().inflate(4, 2,4);
                Predicate<LivingEntity> predicate = entity-> entity.isOnFire() && throwable.distanceToSqr(entity) < 4 * 4 && !entity.isSensitiveToWater();
                throwable.level.getEntitiesOfClass(LivingEntity.class, area, predicate).forEach(Entity::clearFire);
            }
        }
    }

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
