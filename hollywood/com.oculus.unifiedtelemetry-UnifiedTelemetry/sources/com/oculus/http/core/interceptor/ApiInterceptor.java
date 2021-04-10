package com.oculus.http.core.interceptor;

import X.C0359dg;
import X.C0362dj;
import X.C0363dk;
import X.C0367dp;
import X.C0368dq;
import X.C0370dt;
import X.Cdo;
import X.L3;
import X.eJ;
import com.oculus.http.core.uuid.ApiUUID;
import com.squareup.okhttp.HttpUrl;
import com.squareup.okhttp.internal.DiskLruCache;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.UUID;
import javax.annotation.Nullable;

public class ApiInterceptor implements Cdo {
    public final String mBuildModel;
    public final int mBuildNumber;
    public final String mDeviceId;
    public final eJ<Locale> mLocaleProvider;
    @Nullable
    public final String mUserAgent;

    @Override // X.Cdo
    public final C0359dg A38(L3 l3) throws IOException {
        String str;
        C0362dj djVar = l3.A01;
        C0363dk dkVar = new C0363dk(djVar);
        String replace = this.mLocaleProvider.get().toString().replace('_', '-');
        C0370dt dtVar = dkVar.A03;
        C0370dt.A00("Accept-Language", replace);
        dtVar.A02("Accept-Language", replace);
        String str2 = this.mDeviceId;
        C0370dt dtVar2 = dkVar.A03;
        C0370dt.A00("X-ANDROID-ID", str2);
        dtVar2.A02("X-ANDROID-ID", str2);
        String str3 = this.mBuildModel;
        C0370dt dtVar3 = dkVar.A03;
        C0370dt.A00("X-Build-Model", str3);
        dtVar3.A02("X-Build-Model", str3);
        String valueOf = String.valueOf(this.mBuildNumber);
        C0370dt dtVar4 = dkVar.A03;
        C0370dt.A00("X-Build-Number", valueOf);
        dtVar4.A02("X-Build-Number", valueOf);
        C0370dt dtVar5 = dkVar.A03;
        C0370dt.A00("X-Oculus-Feature", DiskLruCache.VERSION_1);
        dtVar5.A02("X-Oculus-Feature", DiskLruCache.VERSION_1);
        String obj = UUID.randomUUID().toString();
        C0370dt dtVar6 = dkVar.A03;
        C0370dt.A00(ApiUUID.HEADER_NAME, obj);
        dtVar6.A02(ApiUUID.HEADER_NAME, obj);
        String str4 = this.mUserAgent;
        if (str4 != null) {
            C0370dt dtVar7 = dkVar.A03;
            C0370dt.A00("User-Agent", str4);
            dtVar7.A02("User-Agent", str4);
        }
        C0368dq A0D = djVar.A03.A0D();
        String obj2 = this.mLocaleProvider.get().toString();
        List list = A0D.A06;
        if (list == null) {
            list = new ArrayList();
            A0D.A06 = list;
        }
        list.add(C0367dp.A04("forced_locale", HttpUrl.QUERY_COMPONENT_ENCODE_SET, false, false, true, true));
        List<String> list2 = A0D.A06;
        if (obj2 != null) {
            str = C0367dp.A04(obj2, HttpUrl.QUERY_COMPONENT_ENCODE_SET, false, false, true, true);
        } else {
            str = null;
        }
        list2.add(str);
        dkVar.A04 = A0D.A03();
        return l3.A00(dkVar.A00());
    }

    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    /* JADX WARNING: Missing exception handler attribute for start block: B:17:0x0045 */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x004d  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public ApiInterceptor(java.lang.String r5, @javax.annotation.Nullable java.lang.String r6, int r7, X.eJ<java.util.Locale> r8) {
        /*
        // Method dump skipped, instructions count: 119
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.http.core.interceptor.ApiInterceptor.<init>(java.lang.String, java.lang.String, int, X.eJ):void");
    }
}
