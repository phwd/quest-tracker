package com.facebook.msys.mci;

import X.AnonymousClass0l0;
import android.annotation.SuppressLint;
import com.facebook.proguard.annotations.DoNotStrip;
import com.facebook.simplejni.NativeHolder;
import java.util.Map;
import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;

@DoNotStrip
@SuppressLint({"MissingNativeLoadLibrary"})
@Immutable
public class MediaSendConfig {
    @DoNotStrip
    public final NativeHolder mNativeHolder;

    @DoNotStrip
    public static native NativeHolder initNativeHolder(String str, boolean z, boolean z2, boolean z3, boolean z4, boolean z5, boolean z6, @Nullable Map<String, Object> map, @Nullable Map<String, Object> map2);

    @DoNotStrip
    private native boolean nativeEquals(Object obj);

    @DoNotStrip
    @Nullable
    public native Map<String, Object> getExtraLoggingData();

    @DoNotStrip
    public native boolean getIsPostingStory();

    @DoNotStrip
    public native boolean getIsSecure();

    @DoNotStrip
    public native String getJobId();

    @DoNotStrip
    @Nullable
    public native Map<String, Object> getOptionalConfig();

    @DoNotStrip
    public native boolean getSendByServer();

    @DoNotStrip
    public native boolean getShouldDedupe();

    @DoNotStrip
    public native boolean getShouldTranscode();

    @DoNotStrip
    public native boolean getUseDoublePhase();

    public native int hashCode();

    public native String toString();

    static {
        AnonymousClass0l0.A06("msysjniinfranosqlite");
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof MediaSendConfig)) {
            return false;
        }
        return nativeEquals(obj);
    }

    @DoNotStrip
    public MediaSendConfig(NativeHolder nativeHolder) {
        this.mNativeHolder = nativeHolder;
    }
}
