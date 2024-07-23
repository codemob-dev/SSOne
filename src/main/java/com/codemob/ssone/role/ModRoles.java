package com.codemob.ssone.role;

import com.codemob.ssone.SSOne;
import com.codemob.ssone.role.system.RoleManager;
import net.minecraft.resources.ResourceLocation;

public class ModRoles {
    public static void registerRoles() {
        RoleManager.registerRole(
                MadScientistRole.class,
                MadScientistRole::new,
                ResourceLocation.fromNamespaceAndPath(SSOne.MODID, "mad_scientist"));
        RoleManager.registerRole(
                NetherLordRole.class,
                NetherLordRole::new,
                ResourceLocation.fromNamespaceAndPath(SSOne.MODID, "nether_lord"));
    }
}
