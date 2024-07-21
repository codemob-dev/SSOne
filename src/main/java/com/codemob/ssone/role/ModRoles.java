package com.codemob.ssone.role;

import com.codemob.ssone.SSOne;
import com.codemob.ssone.role.system.DefaultRole;
import com.codemob.ssone.role.system.RoleManager;
import net.minecraft.resources.ResourceLocation;

public class ModRoles {
    public void registerRoles() {
        RoleManager.registerRole(
                DefaultRole.class,
                DefaultRole::new,
                ResourceLocation.fromNamespaceAndPath(SSOne.MODID, "default"));
        RoleManager.registerRole(
                MadScientistRole.class,
                MadScientistRole::new,
                ResourceLocation.fromNamespaceAndPath(SSOne.MODID, "madScientist"));
        RoleManager.registerRole(
                NetherLordRole.class,
                NetherLordRole::new,
                ResourceLocation.fromNamespaceAndPath(SSOne.MODID, "netherLord"));
    }
}
