package mods.tesseract.bce;

import net.minecraft.entity.EntityLivingBase;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInterModComms;

/**
 * @author exidex.
 * @since 02.04.2017.
 */
@Mod(modid = BCE.MODID, name = BCE.NAME, version = BCE.VERSION)
public class BCE {

    public static final String MODID = "bce";
    public static final String VERSION = "@VERSION@";
    public static final String NAME = "Better Combat Experience";

    @Mod.EventHandler
    public void handleIMCMassages(FMLInterModComms.IMCEvent event) {
        event.getMessages().forEach(imcMessage -> {
            imcMessage.getFunctionValue(EntityLivingBase.class, Boolean.class).ifPresent(EventHandler.PREDICATES::add);
        });
    }
}
