package com.oculus.http.core.interceptor;

import X.AbstractC08380wS;
import X.AbstractC08390wT;
import X.C08220wC;
import X.C08330wN;
import X.C08340wO;
import X.C08400wU;
import X.C08410wV;
import X.C08420wY;
import android.content.Context;
import android.content.pm.PackageInfo;
import com.facebook.GraphRequest;
import com.oculus.http.core.uuid.ApiUUID;
import com.oculus.util.device.DeviceUtils;
import com.squareup.okhttp.HttpUrl;
import com.squareup.okhttp.internal.DiskLruCache;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.UUID;
import javax.annotation.Nullable;
import javax.inject.Provider;

public class ApiInterceptor implements AbstractC08380wS {
    public final String mBuildModel = DeviceUtils.A02();
    public final int mBuildNumber;
    public final String mDeviceId;
    public final Provider<Locale> mLocaleProvider;
    @Nullable
    public final String mUserAgent;

    @Override // X.AbstractC08380wS
    public final C08220wC intercept(AbstractC08390wT r18) throws IOException {
        String str;
        C08330wN A8H = r18.A8H();
        C08340wO r4 = new C08340wO(A8H);
        String replace = this.mLocaleProvider.get().toString().replace('_', '-');
        C08420wY r0 = r4.A03;
        C08420wY.A00(GraphRequest.ACCEPT_LANGUAGE_HEADER, replace);
        r0.A02(GraphRequest.ACCEPT_LANGUAGE_HEADER, replace);
        String str2 = this.mDeviceId;
        C08420wY r02 = r4.A03;
        C08420wY.A00("X-ANDROID-ID", str2);
        r02.A02("X-ANDROID-ID", str2);
        String str3 = this.mBuildModel;
        C08420wY r03 = r4.A03;
        C08420wY.A00("X-Build-Model", str3);
        r03.A02("X-Build-Model", str3);
        String valueOf = String.valueOf(this.mBuildNumber);
        C08420wY r04 = r4.A03;
        C08420wY.A00("X-Build-Number", valueOf);
        r04.A02("X-Build-Number", valueOf);
        C08420wY r05 = r4.A03;
        C08420wY.A00("X-Oculus-Feature", DiskLruCache.VERSION_1);
        r05.A02("X-Oculus-Feature", DiskLruCache.VERSION_1);
        String obj = UUID.randomUUID().toString();
        C08420wY r06 = r4.A03;
        C08420wY.A00(ApiUUID.HEADER_NAME, obj);
        r06.A02(ApiUUID.HEADER_NAME, obj);
        String str4 = this.mUserAgent;
        if (str4 != null) {
            C08420wY r07 = r4.A03;
            C08420wY.A00("User-Agent", str4);
            r07.A02("User-Agent", str4);
        }
        C08410wV A0D = A8H.A03.A0D();
        String obj2 = this.mLocaleProvider.get().toString();
        List list = A0D.A06;
        if (list == null) {
            list = new ArrayList();
            A0D.A06 = list;
        }
        list.add(C08400wU.A04("forced_locale", HttpUrl.QUERY_COMPONENT_ENCODE_SET, false, false, true, true));
        List<String> list2 = A0D.A06;
        if (obj2 != null) {
            str = C08400wU.A04(obj2, HttpUrl.QUERY_COMPONENT_ENCODE_SET, false, false, true, true);
        } else {
            str = null;
        }
        list2.add(str);
        r4.A04 = A0D.A03();
        return r18.A7Z(r4.A00());
    }

    public ApiInterceptor(String str, @Nullable String str2, int i, Provider<Locale> provider) {
        this.mDeviceId = str;
        this.mUserAgent = str2;
        this.mBuildNumber = i;
        this.mLocaleProvider = provider;
    }

    public static ApiInterceptor A00(Context context, PackageInfo packageInfo, @Nullable String str, Provider<Locale> provider) {
        int i;
        String A03 = DeviceUtils.A03(context);
        if (packageInfo != null) {
            i = packageInfo.versionCode;
        } else {
            i = -1;
        }
        return new ApiInterceptor(A03, str, i, provider);
    }
}
