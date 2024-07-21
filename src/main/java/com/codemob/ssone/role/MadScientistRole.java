package com.codemob.ssone.role;

import com.codemob.ssone.role.system.MagicAbilities;
import com.codemob.ssone.role.system.DefaultRole;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public class MadScientistRole extends DefaultRole {
    @Override
    public UUID getDefaultUUID() {
        return UUID.fromString("3f8bbc1a-0fe7-48c8-a509-3b0a7ef80865"); // Codemob
    }

    @Override
    public @NotNull MagicAbilities getMagicAbilities() {
        return new MagicAbilities()
                .earth(.6)
                .air(.65)
                .fire(.7)
                .water(.1)
                .dark(.55);
    }
}
