package com.facebook.msys.mci.network.common;

import X.AnonymousClass1Nh;
import android.annotation.SuppressLint;
import com.facebook.infer.annotation.Nullsafe;
import com.facebook.proguard.annotations.DoNotStrip;
import com.facebook.simplejni.NativeHolder;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;

@DoNotStrip
@SuppressLint({"MissingNativeLoadLibrary"})
@Immutable
@Nullsafe(Nullsafe.Mode.LOCAL)
public class UrlRequest {
    @DoNotStrip
    public final NativeHolder mNativeHolder;

    @DoNotStrip
    @Nullable
    private native String[] getHeaderKeys();

    @DoNotStrip
    @Nullable
    private native String[] getHeaderValues();

    @DoNotStrip
    @Nullable
    public native byte[] getHttpBody();

    @DoNotStrip
    public native String getHttpMethod();

    @DoNotStrip
    public native String getUrl();

    public Map<String, String> getHttpHeaders() {
        HashMap hashMap = new HashMap();
        String[] headerKeys = getHeaderKeys();
        String[] headerValues = getHeaderValues();
        if (!(headerKeys == null || headerValues == null)) {
            for (int i = 0; i < headerKeys.length; i++) {
                hashMap.put(headerKeys[i], headerValues[i]);
            }
        }
        return hashMap;
    }

    static {
        AnonymousClass1Nh.A00();
    }

    @DoNotStrip
    public UrlRequest(NativeHolder nativeHolder) {
        this.mNativeHolder = nativeHolder;
    }
}
