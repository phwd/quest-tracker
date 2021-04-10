package com.oculus.vrshell.config;

import android.content.Context;
import android.util.Log;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.os.enterprise.Configuration;
import com.oculus.os.enterprise.EnterpriseServer;
import com.oculus.os.enterprise.GuardianMode;
import com.oculus.os.enterprise.HandTracking;
import com.oculus.os.enterprise.HomeButtonBehaviour;
import com.oculus.os.enterprise.Mode;
import java.io.UnsupportedEncodingException;
import java.util.Optional;

public final class EnterpriseConfig {
    private static final String TAG = LoggingUtil.tag(EnterpriseConfig.class);
    private final Configuration mConfig;
    private final Context mContext;

    public EnterpriseConfig(Context context) {
        this.mContext = context;
        this.mConfig = EnterpriseServer.getConfiguration(context);
    }

    public String[] getModes() {
        Mode[] modes = this.mConfig.getModes();
        String[] strArr = new String[modes.length];
        for (int i = 0; i < modes.length; i++) {
            strArr[i] = modes[i].getDisplayName();
        }
        return strArr;
    }

    public String[] getModePins() {
        Mode[] modes = this.mConfig.getModes();
        String[] strArr = new String[modes.length];
        for (int i = 0; i < modes.length; i++) {
            Optional<String> pin = modes[i].getPin();
            if (pin.isPresent()) {
                strArr[i] = pin.get();
            } else {
                strArr[i] = "";
            }
        }
        return strArr;
    }

    /* access modifiers changed from: package-private */
    public int getDefaultMode() {
        return this.mConfig.getDefaultModeIndex();
    }

    /* access modifiers changed from: package-private */
    public String getDefaultApplication(int i) {
        String defaultApplication = this.mConfig.getModes()[i].getDefaultApplication();
        return defaultApplication != null ? defaultApplication : "";
    }

    /* access modifiers changed from: package-private */
    public long getLicenseExpireUnixTime() {
        return this.mConfig.getLicenseExpirationDate().getTime() / 1000;
    }

    /* access modifiers changed from: package-private */
    public long getConfigUnixTime() {
        return this.mConfig.getTimestamp().getTime();
    }

    public int getGuardianMode() {
        int i = AnonymousClass1.$SwitchMap$com$oculus$os$enterprise$GuardianMode[this.mConfig.getGuardianMode().ordinal()];
        if (i == 1) {
            return 0;
        }
        if (i != 2) {
            String str = TAG;
            Log.e(str, "Unknown Guardian Mode: " + this.mConfig.getGuardianMode());
        }
        return 1;
    }

    public int getHomeButtonBehaviour(int i) {
        HomeButtonBehaviour homeButtonBehaviour = this.mConfig.getModes()[i].getHomeButtonBehaviour();
        int i2 = AnonymousClass1.$SwitchMap$com$oculus$os$enterprise$HomeButtonBehaviour[homeButtonBehaviour.ordinal()];
        if (i2 == 1) {
            return 0;
        }
        if (i2 == 2) {
            return 1;
        }
        if (i2 != 3) {
            String str = TAG;
            Log.e(str, "Unknown Home Button Behaviour: " + homeButtonBehaviour);
        }
        return 2;
    }

    public String[] getDynamicConfig() {
        String[] dynamicConfig = this.mConfig.getDynamicConfig();
        String str = TAG;
        Log.d(str, "Retrieved dynamic config with length: " + dynamicConfig.length);
        return dynamicConfig;
    }

    public int getHandTracking() {
        HandTracking handTracking = this.mConfig.getHandTracking();
        String str = TAG;
        Log.d(str, "Retrieved hand tracking state: " + handTracking.name());
        int i = AnonymousClass1.$SwitchMap$com$oculus$os$enterprise$HandTracking[handTracking.ordinal()];
        if (i == 1) {
            return 0;
        }
        if (i == 2) {
            return 1;
        }
        String str2 = TAG;
        Log.e(str2, "Unknown Hand Tracking state: " + handTracking.name());
        return 0;
    }

    /* renamed from: com.oculus.vrshell.config.EnterpriseConfig$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$oculus$os$enterprise$GuardianMode = new int[GuardianMode.values().length];
        static final /* synthetic */ int[] $SwitchMap$com$oculus$os$enterprise$HandTracking = new int[HandTracking.values().length];
        static final /* synthetic */ int[] $SwitchMap$com$oculus$os$enterprise$HomeButtonBehaviour = new int[HomeButtonBehaviour.values().length];

        /* JADX WARNING: Can't wrap try/catch for region: R(17:0|1|2|3|5|6|7|9|10|11|12|13|14|15|17|18|(3:19|20|22)) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x0032 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:13:0x003c */
        /* JADX WARNING: Missing exception handler attribute for start block: B:19:0x005a */
        static {
            /*
            // Method dump skipped, instructions count: 101
            */
            throw new UnsupportedOperationException("Method not decompiled: com.oculus.vrshell.config.EnterpriseConfig.AnonymousClass1.<clinit>():void");
        }
    }

    public String getMarshalledEnterpriseConfig() throws UnsupportedEncodingException {
        return this.mConfig.toMarshalledString();
    }
}
