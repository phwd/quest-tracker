package com.oculus.logging.analytics2;

import X.SU;
import android.content.Context;
import com.oculus.os.SettingsManager;
import javax.annotation.Nullable;

public class OculusDeviceIdProvider implements SU {
    public static final String TAG = "OculusDeviceIdProvider";
    @Nullable
    public String cachedDeviceID = null;
    public Context context;
    public SettingsManager settingsManager;

    /* JADX WARNING: Code restructure failed: missing block: B:15:0x006c, code lost:
        if (android.text.TextUtils.isEmpty(r0) == false) goto L_0x0047;
     */
    @Override // X.SU
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.String A2F() {
        /*
        // Method dump skipped, instructions count: 111
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.logging.analytics2.OculusDeviceIdProvider.A2F():java.lang.String");
    }

    public OculusDeviceIdProvider(Context context2) {
        Context applicationContext = context2.getApplicationContext();
        this.context = applicationContext;
        this.settingsManager = new SettingsManager(applicationContext);
    }
}
