package com.oculus.http.core.interceptor;

import X.AbstractC0192Xx;
import X.EU;
import X.XK;
import X.XN;
import X.XO;
import X.XS;
import X.XT;
import X.XU;
import X.XX;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.provider.Settings;
import com.facebook.acra.util.HttpRequestMultipart;
import com.oculus.http.core.uuid.ApiUUID;
import com.squareup.okhttp.HttpUrl;
import com.squareup.okhttp.internal.DiskLruCache;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.UUID;
import javax.annotation.Nullable;

public class ApiInterceptor implements XS {
    public final String mBuildModel;
    public final int mBuildNumber;
    public final String mDeviceId;
    public final AbstractC0192Xx<Locale> mLocaleProvider;
    @Nullable
    public final String mUserAgent;

    @Override // X.XS
    public final XK A29(EU eu) throws IOException {
        String str;
        XN xn = eu.A01;
        XO xo = new XO(xn);
        String replace = this.mLocaleProvider.get().toString().replace('_', '-');
        XX xx = xo.A03;
        XX.A00("Accept-Language", replace);
        xx.A02("Accept-Language", replace);
        String str2 = this.mDeviceId;
        XX xx2 = xo.A03;
        XX.A00("X-ANDROID-ID", str2);
        xx2.A02("X-ANDROID-ID", str2);
        String str3 = this.mBuildModel;
        XX xx3 = xo.A03;
        XX.A00("X-Build-Model", str3);
        xx3.A02("X-Build-Model", str3);
        String valueOf = String.valueOf(this.mBuildNumber);
        XX xx4 = xo.A03;
        XX.A00("X-Build-Number", valueOf);
        xx4.A02("X-Build-Number", valueOf);
        XX xx5 = xo.A03;
        XX.A00("X-Oculus-Feature", DiskLruCache.VERSION_1);
        xx5.A02("X-Oculus-Feature", DiskLruCache.VERSION_1);
        String obj = UUID.randomUUID().toString();
        XX xx6 = xo.A03;
        XX.A00(ApiUUID.HEADER_NAME, obj);
        xx6.A02(ApiUUID.HEADER_NAME, obj);
        String str4 = this.mUserAgent;
        if (str4 != null) {
            XX xx7 = xo.A03;
            XX.A00(HttpRequestMultipart.USER_AGENT, str4);
            xx7.A02(HttpRequestMultipart.USER_AGENT, str4);
        }
        XU A0C = xn.A03.A0C();
        String obj2 = this.mLocaleProvider.get().toString();
        List list = A0C.A06;
        if (list == null) {
            list = new ArrayList();
            A0C.A06 = list;
        }
        list.add(XT.A02("forced_locale", 0, "forced_locale".length(), HttpUrl.QUERY_COMPONENT_ENCODE_SET, false, false, true, true));
        List<String> list2 = A0C.A06;
        if (obj2 != null) {
            str = XT.A02(obj2, 0, obj2.length(), HttpUrl.QUERY_COMPONENT_ENCODE_SET, false, false, true, true);
        } else {
            str = null;
        }
        list2.add(str);
        xo.A04 = A0C.A03();
        return eu.A00(xo.A00());
    }

    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    /* JADX WARNING: Missing exception handler attribute for start block: B:17:0x0045 */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x004d  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public ApiInterceptor(java.lang.String r5, @javax.annotation.Nullable java.lang.String r6, int r7, X.AbstractC0192Xx<java.util.Locale> r8) {
        /*
        // Method dump skipped, instructions count: 119
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.http.core.interceptor.ApiInterceptor.<init>(java.lang.String, java.lang.String, int, X.Xx):void");
    }

    public static ApiInterceptor A00(Context context, PackageInfo packageInfo, @Nullable String str, AbstractC0192Xx<Locale> xx) {
        int i;
        String string = Settings.Secure.getString(context.getContentResolver(), "android_id");
        if (string == null) {
            string = "";
        }
        int length = string.length();
        if (length < 16) {
            StringBuilder sb = new StringBuilder(16);
            while (length < 16) {
                sb.append('0');
                length++;
            }
            sb.append(string);
            string = sb.toString();
        }
        if (packageInfo != null) {
            i = packageInfo.versionCode;
        } else {
            i = -1;
        }
        return new ApiInterceptor(string, str, i, xx);
    }
}
