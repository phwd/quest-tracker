package com.oculus.panelapp.anytimeui.config;

import android.content.Context;
import android.util.Log;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.os.enterprise.AUICapability;
import com.oculus.os.enterprise.Configuration;
import com.oculus.os.enterprise.GuardianMode;
import com.oculus.os.enterprise.HandTracking;
import com.oculus.os.enterprise.HomeButtonBehaviour;
import com.oculus.os.enterprise.Mode;
import com.oculus.panelapp.anytimeui.config.SystemUXConfiguration;

public final class EnterpriseConfiguration extends SystemUXConfiguration {
    private static final String ADMIN_MODE = "admin";
    private static final String TAG = LoggingUtil.tag(EnterpriseConfiguration.class);

    public static EnterpriseConfiguration init(Context context, int i, Configuration configuration) {
        boolean z = false;
        if (configuration == null) {
            return new EnterpriseConfiguration(buildConfig(context, i, new Mode[0], false, false));
        }
        Mode[] modes = configuration.getModes();
        boolean z2 = configuration.getHandTracking() == HandTracking.ENABLED;
        if (configuration.getGuardianMode() == GuardianMode.ENABLED) {
            z = true;
        }
        return new EnterpriseConfiguration(buildConfig(context, i, modes, z2, z));
    }

    private EnterpriseConfiguration(SystemUXConfiguration.Builder builder) {
        super(builder);
    }

    private static SystemUXConfiguration.Builder buildConfig(Context context, int i, Mode[] modeArr, boolean z, boolean z2) {
        AUICapability[] aUICapabilityArr;
        boolean z3;
        String str;
        AUICapability[] aUICapabilityArr2 = new AUICapability[0];
        String str2 = "";
        if (modeArr.length > 0) {
            Mode mode = modeArr[i];
            str2 = mode.getDisplayName();
            str = mode.getHomeButtonBehaviour().name();
            z3 = !mode.getDefaultApplication().isEmpty();
            aUICapabilityArr = mode.getAuiCapabilities();
        } else {
            z3 = false;
            aUICapabilityArr = aUICapabilityArr2;
            str = str2;
        }
        Log.d(TAG, "(modeIndex, modeName, homeButtonBehavior, kioskAppSet, handTrackingEnabled, guardianEnabled) = \n(" + i + ", " + str2 + ", " + str + ", " + z3 + ", " + z + ", " + z2 + ")");
        SystemUXConfiguration.Builder enterpriseHandTrackingEnabled = SystemUXConfiguration.sysUxBuilder().setEnterpriseMode(true).setAuiNavigateTabVisible(false).setLiveStreamingEnabled(false).setCameraRollEnabled(false).setAuiTabButtonBarReconfigured(true).setGuardianPillButtonsEnabled(z2).setAuiTabBarVisible(false).setAuiSocialIconsVisible(false).setAuiPeopleTabVisible(false).setAuiShareTabVisible(false).setCastingEnabled(false).setAuiNotificationsTabVisible(false).setWifiEditable(false).setDefaultApplicationSet(z3).setEnterpriseHandTrackingEnabled(z);
        if (str2.equals(ADMIN_MODE)) {
            enterpriseHandTrackingEnabled.setEnterpriseAdminModeEnabled(true);
            enterpriseHandTrackingEnabled.setCastingEnabled(true);
        }
        enterpriseHandTrackingEnabled.setDefaultApplicationCanBeRestarted(!str.equals(HomeButtonBehaviour.RESUME_ONLY.name()));
        for (AUICapability aUICapability : aUICapabilityArr) {
            int i2 = AnonymousClass1.$SwitchMap$com$oculus$os$enterprise$AUICapability[aUICapability.ordinal()];
            if (i2 == 1) {
                enterpriseHandTrackingEnabled.setAuiSocialIconsVisible(true);
            } else if (i2 == 2) {
                enterpriseHandTrackingEnabled.setAuiShareTabVisible(true);
                enterpriseHandTrackingEnabled.setAuiTabBarVisible(true);
            } else if (i2 == 3) {
                enterpriseHandTrackingEnabled.setCastingEnabled(true);
            } else if (i2 == 4) {
                enterpriseHandTrackingEnabled.setAuiNotificationsTabVisible(true);
                enterpriseHandTrackingEnabled.setAuiTabBarVisible(true);
            } else if (i2 == 5) {
                enterpriseHandTrackingEnabled.setWifiEditable(true);
            }
        }
        return enterpriseHandTrackingEnabled;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.oculus.panelapp.anytimeui.config.EnterpriseConfiguration$1  reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$oculus$os$enterprise$AUICapability = new int[AUICapability.values().length];

        /* JADX WARNING: Can't wrap try/catch for region: R(12:0|1|2|3|4|5|6|7|8|9|10|(3:11|12|14)) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x0040 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0014 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001f */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x002a */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0035 */
        static {
            /*
                com.oculus.os.enterprise.AUICapability[] r0 = com.oculus.os.enterprise.AUICapability.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                com.oculus.panelapp.anytimeui.config.EnterpriseConfiguration.AnonymousClass1.$SwitchMap$com$oculus$os$enterprise$AUICapability = r0
                int[] r0 = com.oculus.panelapp.anytimeui.config.EnterpriseConfiguration.AnonymousClass1.$SwitchMap$com$oculus$os$enterprise$AUICapability     // Catch:{ NoSuchFieldError -> 0x0014 }
                com.oculus.os.enterprise.AUICapability r1 = com.oculus.os.enterprise.AUICapability.SOCIAL     // Catch:{ NoSuchFieldError -> 0x0014 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0014 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0014 }
            L_0x0014:
                int[] r0 = com.oculus.panelapp.anytimeui.config.EnterpriseConfiguration.AnonymousClass1.$SwitchMap$com$oculus$os$enterprise$AUICapability     // Catch:{ NoSuchFieldError -> 0x001f }
                com.oculus.os.enterprise.AUICapability r1 = com.oculus.os.enterprise.AUICapability.SHARE     // Catch:{ NoSuchFieldError -> 0x001f }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001f }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001f }
            L_0x001f:
                int[] r0 = com.oculus.panelapp.anytimeui.config.EnterpriseConfiguration.AnonymousClass1.$SwitchMap$com$oculus$os$enterprise$AUICapability     // Catch:{ NoSuchFieldError -> 0x002a }
                com.oculus.os.enterprise.AUICapability r1 = com.oculus.os.enterprise.AUICapability.CASTING     // Catch:{ NoSuchFieldError -> 0x002a }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x002a }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x002a }
            L_0x002a:
                int[] r0 = com.oculus.panelapp.anytimeui.config.EnterpriseConfiguration.AnonymousClass1.$SwitchMap$com$oculus$os$enterprise$AUICapability     // Catch:{ NoSuchFieldError -> 0x0035 }
                com.oculus.os.enterprise.AUICapability r1 = com.oculus.os.enterprise.AUICapability.NOTIFICATIONS     // Catch:{ NoSuchFieldError -> 0x0035 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0035 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0035 }
            L_0x0035:
                int[] r0 = com.oculus.panelapp.anytimeui.config.EnterpriseConfiguration.AnonymousClass1.$SwitchMap$com$oculus$os$enterprise$AUICapability     // Catch:{ NoSuchFieldError -> 0x0040 }
                com.oculus.os.enterprise.AUICapability r1 = com.oculus.os.enterprise.AUICapability.WIFI     // Catch:{ NoSuchFieldError -> 0x0040 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0040 }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0040 }
            L_0x0040:
                int[] r0 = com.oculus.panelapp.anytimeui.config.EnterpriseConfiguration.AnonymousClass1.$SwitchMap$com$oculus$os$enterprise$AUICapability     // Catch:{ NoSuchFieldError -> 0x004b }
                com.oculus.os.enterprise.AUICapability r1 = com.oculus.os.enterprise.AUICapability.SEE_ALL_SETTINGS     // Catch:{ NoSuchFieldError -> 0x004b }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x004b }
                r2 = 6
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x004b }
            L_0x004b:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.oculus.panelapp.anytimeui.config.EnterpriseConfiguration.AnonymousClass1.<clinit>():void");
        }
    }
}
