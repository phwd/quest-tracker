package com.oculus.messengervr.fb;

import androidx.annotation.Nullable;
import com.facebook.infer.annotation.Nullsafe;
import com.facebook.msys.mci.Analytics;
import java.util.List;
import java.util.Map;

@Nullsafe(Nullsafe.Mode.LOCAL)
public final class VrMsysAnalytics implements Analytics {
    public static final String TAG = "VrMsysAnalytics";

    @Override // com.facebook.msys.mci.Analytics
    public void log(int i, int i2, boolean z, String str, String str2, long j, @Nullable Map<String, Object> map, @Nullable Map<String, Object> map2, @Nullable List<Object> list) {
    }
}
