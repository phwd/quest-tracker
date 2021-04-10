package com.facebook.msys.mci.network.common;

import X.AnonymousClass1Nh;
import android.annotation.SuppressLint;
import android.util.Pair;
import com.facebook.infer.annotation.Nullsafe;
import com.facebook.proguard.annotations.DoNotStrip;
import com.facebook.simplejni.NativeHolder;
import java.util.Map;
import javax.annotation.concurrent.Immutable;

@DoNotStrip
@SuppressLint({"MissingNativeLoadLibrary"})
@Immutable
@Nullsafe(Nullsafe.Mode.LOCAL)
public class UrlResponse {
    @DoNotStrip
    public final NativeHolder mNativeHolder;

    @DoNotStrip
    public static native NativeHolder initNativeHolder(UrlRequest urlRequest, int i, String[] strArr, String[] strArr2);

    static {
        AnonymousClass1Nh.A00();
    }

    public UrlResponse(UrlRequest urlRequest, int i, Map<String, String> map) {
        if (urlRequest != null) {
            String[] strArr = new String[map.size()];
            String[] strArr2 = new String[map.size()];
            int i2 = 0;
            for (Map.Entry<String, String> entry : map.entrySet()) {
                strArr[i2] = entry.getKey();
                strArr2[i2] = entry.getValue();
                i2++;
            }
            Pair pair = new Pair(strArr, strArr2);
            this.mNativeHolder = initNativeHolder(urlRequest, i, (String[]) pair.first, (String[]) pair.second);
            return;
        }
        throw null;
    }
}
