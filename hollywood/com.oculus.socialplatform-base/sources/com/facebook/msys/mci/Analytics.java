package com.facebook.msys.mci;

import androidx.annotation.Nullable;
import com.facebook.proguard.annotations.DoNotStrip;
import java.util.List;
import java.util.Map;

@DoNotStrip
public interface Analytics {
    @DoNotStrip
    void log(int i, int i2, boolean z, String str, String str2, long j, @Nullable Map<String, Object> map, @Nullable Map<String, Object> map2, @Nullable List<Object> list);
}
