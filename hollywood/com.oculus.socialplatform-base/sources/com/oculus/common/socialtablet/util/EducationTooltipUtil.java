package com.oculus.common.socialtablet.util;

import androidx.annotation.VisibleForTesting;
import com.oculus.os.PreferencesManager;

public final class EducationTooltipUtil {
    @VisibleForTesting
    public static final String PROFILE_SWITCHER_EDUCATION_TOOLTIP_SEEN_PREF_KEY = "is_profile_switcher_education_tooltip_seen";
    public static PreferencesManager sPrefManager;

    public enum EducationTooltipType {
        PROFILE_SWITCHER
    }

    /* renamed from: com.oculus.common.socialtablet.util.EducationTooltipUtil$1  reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
        public static final /* synthetic */ int[] $SwitchMap$com$oculus$common$socialtablet$util$EducationTooltipUtil$EducationTooltipType;

        static {
            int[] iArr = new int[EducationTooltipType.values().length];
            $SwitchMap$com$oculus$common$socialtablet$util$EducationTooltipUtil$EducationTooltipType = iArr;
            try {
                iArr[EducationTooltipType.PROFILE_SWITCHER.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
        }
    }

    public static boolean shouldShowTooltip(EducationTooltipType educationTooltipType) {
        PreferencesManager preferencesManager = sPrefManager;
        if (preferencesManager == null) {
            preferencesManager = new PreferencesManager();
            sPrefManager = preferencesManager;
        }
        if (educationTooltipType.ordinal() != 0 || !((Boolean) preferencesManager.getBoolean(PROFILE_SWITCHER_EDUCATION_TOOLTIP_SEEN_PREF_KEY).first).booleanValue()) {
            return false;
        }
        return !((Boolean) sPrefManager.getBoolean(PROFILE_SWITCHER_EDUCATION_TOOLTIP_SEEN_PREF_KEY).second).booleanValue();
    }

    public static void setTooltipPrefKey(EducationTooltipType educationTooltipType, Object obj) {
        if (educationTooltipType.ordinal() == 0) {
            sPrefManager.set(PROFILE_SWITCHER_EDUCATION_TOOLTIP_SEEN_PREF_KEY, ((Boolean) obj).booleanValue());
        }
    }
}
