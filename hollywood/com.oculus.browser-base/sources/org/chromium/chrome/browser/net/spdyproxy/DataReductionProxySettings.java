package org.chromium.chrome.browser.net.spdyproxy;

import J.N;
import java.util.List;
import org.chromium.base.Callback;
import org.chromium.base.ThreadUtils;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class DataReductionProxySettings {

    /* renamed from: a  reason: collision with root package name */
    public static DataReductionProxySettings f10699a;
    public Callback b;
    public final long c = N.M2kzbCDY(this);

    /* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
    public class ContentLengths {

        /* renamed from: a  reason: collision with root package name */
        public final long f10700a;
        public final long b;

        public ContentLengths(long j, long j2) {
            this.f10700a = j;
            this.b = j2;
        }

        public static ContentLengths create(long j, long j2) {
            return new ContentLengths(j, j2);
        }
    }

    public static void createDataUseItemAndAddToList(List list, String str, long j, long j2) {
        list.add(new HC(str, j, j2));
    }

    public static DataReductionProxySettings d() {
        Object obj = ThreadUtils.f10596a;
        if (f10699a == null) {
            f10699a = new DataReductionProxySettings();
        }
        return f10699a;
    }

    public long a() {
        ContentLengths contentLengths = (ContentLengths) N.MG86mkwd(this.c, this);
        return Math.max(contentLengths.f10700a - contentLengths.b, 0L);
    }

    public long b() {
        return N.MF6dNZ7w(this.c, this);
    }

    public long c() {
        return NU0.f8549a.h("BANDWIDTH_REDUCTION_FIRST_ENABLED_TIME", 0);
    }

    public boolean e() {
        return N.M9eQkbEA(this.c, this);
    }

    public boolean f() {
        return N.MO7d50bX(this.c, this);
    }

    public void g(boolean z) {
        if (z) {
            PU0 pu0 = NU0.f8549a;
            if (pu0.h("BANDWIDTH_REDUCTION_FIRST_ENABLED_TIME", 0) == 0) {
                pu0.o("BANDWIDTH_REDUCTION_FIRST_ENABLED_TIME", System.currentTimeMillis());
            }
        }
        NU0.f8549a.m("BANDWIDTH_REDUCTION_PROXY_ENABLED", z);
        N.MkmavAwt(this.c, this, z);
    }

    public void onQueryDataUsageComplete(List list) {
        Callback callback = this.b;
        if (callback != null) {
            callback.onResult(list);
        }
        this.b = null;
    }
}
