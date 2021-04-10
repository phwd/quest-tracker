package com.oculus.deviceconfigclient;

import android.annotation.TargetApi;
import com.facebook.infer.annotation.Nullsafe;
import java.util.HashMap;
import java.util.Map;

/* access modifiers changed from: package-private */
@TargetApi(25)
@Nullsafe(Nullsafe.Mode.LOCAL)
public final class ConfigMemoryState {
    final Map<String, ValueInfo<Boolean>> mBooleanValues = new HashMap();
    final Map<String, ValueInfo<Double>> mDoubleValues = new HashMap();
    final Map<String, ValueInfo<Long>> mLongValues = new HashMap();
    String mParamsMapVersion = "##UNINITIALIZED_VERSION##";
    final Map<String, ValueInfo<String>> mStringValues = new HashMap();

    ConfigMemoryState() {
    }
}
