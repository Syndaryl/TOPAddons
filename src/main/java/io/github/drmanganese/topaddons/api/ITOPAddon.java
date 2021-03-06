package io.github.drmanganese.topaddons.api;

import net.minecraft.item.ItemArmor;

import io.github.drmanganese.topaddons.AddonManager;
import io.github.drmanganese.topaddons.addons.AddonBlank;
import io.github.drmanganese.topaddons.reference.EnumChip;

import java.util.List;
import java.util.Map;

import mcjty.theoneprobe.api.IBlockDisplayOverride;
import mcjty.theoneprobe.api.IProbeConfigProvider;
import mcjty.theoneprobe.api.IProbeInfoEntityProvider;
import mcjty.theoneprobe.api.IProbeInfoProvider;

/**
 * Interface each addon has to implement (through extending {@link AddonBlank}
 * or directly), the {@link AddonManager} will look for it
 * if it finds a {@link TOPAddon} annotation.
 */
public interface ITOPAddon extends IProbeInfoProvider, IProbeInfoEntityProvider, IProbeConfigProvider {

    /**
     * @return <i>true</i> if the addon adds special helmets
     */
    boolean hasSpecialHelmets();

    /**
     * @return A {@link Map} containing {@link ItemArmor}, {@link EnumChip} pairs.
     */
    Map<Class<? extends ItemArmor>, EnumChip> getSpecialHelmets();

    /**
     * Custom {@link mcjty.theoneprobe.api.IElement}s have to be registered here.<p>
     *
     * <pre>
     * Example: {@code
     * ELEMENT_CUSTOM = TOPRegistrar.GetTheOneProbe.probe.registerElementFactory(ElementCustom::new);
     * }
     * </pre>
     * Where <i>ELEMENT_CUSTOM</i> is a static integer stored in the addon's class.
     */
    void registerElements();

    /**
     * Add a <{@link net.minecraftforge.fluids.Fluid}, {@link Integer}> pair to the Map in
     * {@link io.github.drmanganese.topaddons.reference.Colors}
     */
    void addFluidColors();

    /**
     * Add a <{@link net.minecraft.tileentity.TileEntity}, {@link String[]}> pair to the Map in
     * {@link io.github.drmanganese.topaddons.reference.Names}
     */
    void addTankNames();

    List<IBlockDisplayOverride> getBlockDisplayOverrides();

}
