package net.boomexe.milkify.entity;

import net.boomexe.milkify.config.ModConfigReader;
import net.minecraft.entity.*;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.projectile.thrown.PotionEntity;
import net.minecraft.entity.projectile.thrown.ThrownItemEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionUtil;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.List;
import java.util.function.Predicate;

public class AntidoteMilkPotionEntity extends ThrownItemEntity implements FlyingItemEntity {
    public static final double field_30667 = 4.0;
    private static final double field_30668 = 16.0;
    public static final Predicate<LivingEntity> WATER_HURTS = LivingEntity::hurtByWater;

    public AntidoteMilkPotionEntity(EntityType<? extends PotionEntity> entityType, World world) {
        super(entityType, world);
    }

    public AntidoteMilkPotionEntity(World world, LivingEntity owner) {
        super(EntityType.POTION, owner, world);
    }

    public AntidoteMilkPotionEntity(World world, double x, double y, double z) {
        super(EntityType.POTION, x, y, z, world);
    }

    protected Item getDefaultItem() {
        return Items.SPLASH_POTION;
    }

    protected float getGravity() {
        return 0.05F;
    }

    protected void onBlockHit(BlockHitResult blockHitResult) {
        super.onBlockHit(blockHitResult);
        if (!this.world.isClient) {
            ItemStack itemStack = this.getStack();
            Potion potion = PotionUtil.getPotion(itemStack);
            Direction direction = blockHitResult.getSide();
            BlockPos blockPos = blockHitResult.getBlockPos();
            BlockPos blockPos2 = blockPos.offset(direction);
        }
    }

    protected void onCollision(HitResult hitResult) {
        super.onCollision(hitResult);
        if (!this.world.isClient) {
            ItemStack itemStack = this.getStack();
            Potion potion = PotionUtil.getPotion(itemStack);
            List<StatusEffectInstance> list = PotionUtil.getPotionEffects(itemStack);
            this.applyMilk();
            this.applySplashPotion(list, hitResult.getType() == HitResult.Type.ENTITY ? ((EntityHitResult)hitResult).getEntity() : null);

            this.world.syncWorldEvent(2002, this.getBlockPos(), 6813183);
            this.discard();
            if (this.isLingering()) {
                this.applyLingeringPotion(itemStack, potion);

                int i = potion.hasInstantEffect() ? 2007 : 2002;
                this.world.syncWorldEvent(i, this.getBlockPos(), 6813183);
                this.discard();
            } else {
                this.applySplashPotion(list, hitResult.getType() == HitResult.Type.ENTITY ? ((EntityHitResult)hitResult).getEntity() : null);
            }
        }
    }

    private void applyMilk() {
        Box box = this.getBoundingBox().expand(ModConfigReader.config.throwable_bottle_effect_range, 2.0, ModConfigReader.config.throwable_bottle_effect_range  );
        List<LivingEntity> list = this.world.getNonSpectatingEntities(LivingEntity.class, box);
        if (!list.isEmpty()) {
            Iterator var3 = list.iterator();

            while (var3.hasNext()) {
                LivingEntity livingEntity = (LivingEntity) var3.next();

                Iterator<StatusEffect> iterator = livingEntity.getActiveStatusEffects().keySet().iterator();

                try {
                    while(iterator.hasNext()) {
                        StatusEffect statusEffect = (StatusEffect)iterator.next();
                        StatusEffectInstance statusEffectInstance = (StatusEffectInstance)livingEntity.getActiveStatusEffects().get(statusEffect);
                        if (!statusEffect.isBeneficial()) {
                            livingEntity.removeStatusEffect(statusEffect);
                            iterator = livingEntity.getActiveStatusEffects().keySet().iterator();
                        }
                    }
                } catch (ConcurrentModificationException concurrentModificationException) {}
            }
        }
    }

    private void applySplashPotion(List<StatusEffectInstance> statusEffects, @Nullable Entity entity) {
        applyMilk();
//        Box box = this.getBoundingBox().expand(3.0, 2.0, 3.0);
//        List<LivingEntity> list = this.world.getNonSpectatingEntities(LivingEntity.class, box);
//        if (!list.isEmpty()) {
//            Iterator var3 = list.iterator();
//
//            while (var3.hasNext()) {
//                LivingEntity livingEntity = (LivingEntity) var3.next();
//                applyMilk();
//            }
//        }
    }

    private void applyLingeringPotion(ItemStack stack, Potion potion) {
        AreaEffectCloudEntity areaEffectCloudEntity = new AreaEffectCloudEntity(this.world, this.getX(), this.getY(), this.getZ());
        Entity entity = this.getOwner();
        if (entity instanceof LivingEntity) {
            areaEffectCloudEntity.setOwner((LivingEntity)entity);
        }

        areaEffectCloudEntity.setRadius(3.0F);
        areaEffectCloudEntity.setRadiusOnUse(-0.5F);
        areaEffectCloudEntity.setWaitTime(10);
        areaEffectCloudEntity.setRadiusGrowth(-areaEffectCloudEntity.getRadius() / (float)areaEffectCloudEntity.getDuration());
        areaEffectCloudEntity.setPotion(potion);
        Iterator var5 = PotionUtil.getCustomPotionEffects(stack).iterator();

        while(var5.hasNext()) {
            LivingEntity livingEntity = (LivingEntity) var5.next();
            livingEntity.clearStatusEffects();
        }

        NbtCompound nbtCompound = stack.getNbt();
        if (nbtCompound != null && nbtCompound.contains("CustomPotionColor", 99)) {
            areaEffectCloudEntity.setColor(nbtCompound.getInt("CustomPotionColor"));
        }

        this.world.spawnEntity(areaEffectCloudEntity);
    }

    private boolean isLingering() {
        return this.getStack().isOf(Items.LINGERING_POTION);
    }
}
