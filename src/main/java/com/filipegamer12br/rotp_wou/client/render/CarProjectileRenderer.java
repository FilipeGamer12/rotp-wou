package com.filipegamer12br.rotp_wou.client.render;

import com.filipegamer12br.rotp_wou.WonderOfYouAddon;
import com.filipegamer12br.rotp_wou.entity.CarProjectileEntity;
import com.filipegamer12br.rotp_wou.init.InitStands;
import com.github.standobyte.jojo.client.standskin.StandSkinsManager;
import com.github.standobyte.jojo.power.impl.stand.IStandPower;
import com.github.standobyte.jojo.power.impl.stand.type.StandType;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

public class CarProjectileRenderer extends MobRenderer<CarProjectileEntity,CarProjectileModel> {

    private static final ResourceLocation TEXTURE =
            new ResourceLocation(WonderOfYouAddon.MOD_ID, "textures/entity/car_projectile.png");

    protected static ResourceLocation CAR = TEXTURE;


    public CarProjectileRenderer(EntityRendererManager renderManager) {
        super(renderManager, new CarProjectileModel(),0F);
    }



    @Override
    public ResourceLocation getTextureLocation(CarProjectileEntity entity) {
        if(entity.getOwner() != null){
            IStandPower.getStandPowerOptional(entity.getOwner()).ifPresent(power -> {
                StandType<?> KQ = InitStands.WONDER_OF_YOU.getStandType();
                if(power.getType() == KQ){
                    CAR = StandSkinsManager.getInstance() != null? (StandSkinsManager.getInstance().getRemappedResPath(manager -> manager
                            .getStandSkin(power.getStandInstance().get()), TEXTURE)): TEXTURE ;
                }else {
                    CAR = TEXTURE;
                }
            });
        }
        return CAR;
    }
}