package com.facebook.voltron.metadata;

import com.facebook.common.build.BuildConstants;
import com.facebook.infer.annotation.Nullsafe;
import javax.annotation.Nullable;

@Nullsafe(Nullsafe.Mode.LOCAL)
public final class VoltronModuleResolver {
    @Nullable
    public static String getModuleNameForClass(@Nullable String str) {
        if (!BuildConstants.areAppModulesEnabled() || str == null) {
            return null;
        }
        return VoltronModuleMetadata.getModuleNameForClass(str);
    }
}
