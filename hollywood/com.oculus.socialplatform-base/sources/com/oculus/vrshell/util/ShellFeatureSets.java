package com.oculus.vrshell.util;

import android.text.TextUtils;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class ShellFeatureSets {
    public static final String ENVIRONMENT_KEY_FEATURE_SETS = "_oc_shell_feature_sets";
    public static final String FEATURE_SET_DRAG_DROP_V1 = "dragDrop-v1";
    public static final String FEATURE_SET_PANEL_ADAPTABILITY_TABLET_V1 = "panelAdaptabilityTablet-v1";
    public static final String FEATURE_SET_PANEL_ADAPTABILITY_V1 = "panelAdaptability-v1";
    public final Set<String> mShellFeatureSets = new HashSet();

    public boolean hasFeature(String str) {
        return this.mShellFeatureSets.contains(str);
    }

    public ShellFeatureSets(String str) {
        if (!TextUtils.isEmpty(str)) {
            this.mShellFeatureSets.addAll(Arrays.asList(str.split(",")));
        }
    }
}
