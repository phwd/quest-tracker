package com.facebook.msys.mci;

import com.facebook.proguard.annotations.DoNotStrip;
import javax.annotation.Nullable;

@DoNotStrip
public interface Settings {
    @DoNotStrip
    boolean readBooleanSetting(String str, boolean z);

    @DoNotStrip
    long readLongSetting(String str, long j);

    @DoNotStrip
    @Nullable
    String readStringSetting(String str, @Nullable String str2);

    @DoNotStrip
    void writeBooleanSetting(String str, boolean z);

    @DoNotStrip
    void writeLongSetting(String str, long j);

    @DoNotStrip
    void writeStringSetting(String str, @Nullable String str2);
}
