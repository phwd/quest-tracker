package com.facebook.msys.mci;

import androidx.annotation.Nullable;
import com.facebook.infer.annotation.Nullsafe;
import com.facebook.proguard.annotations.DoNotStrip;
import java.util.List;
import java.util.Map;
import javax.annotation.concurrent.ThreadSafe;

@DoNotStrip
@ThreadSafe
@Nullsafe(Nullsafe.Mode.LOCAL)
public class NoOpAnalytics implements Analytics {
    public static final Analytics A00 = new NoOpAnalytics();

    @Override // com.facebook.msys.mci.Analytics
    @DoNotStrip
    public void log(int i, int i2, boolean z, String str, String str2, long j, @Nullable Map<String, Object> map, @Nullable Map<String, Object> map2, @Nullable List<Object> list) {
    }
}
