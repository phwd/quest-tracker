package defpackage;

import J.N;
import android.content.Intent;
import android.text.TextUtils;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.url.Origin;

/* renamed from: cu  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C2089cu implements R20 {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ AbstractActivityC2601fu f9728a;

    public C2089cu(AbstractActivityC2601fu fuVar, C1449Xt xt) {
        this.f9728a = fuVar;
    }

    @Override // defpackage.R20
    public void a(String str) {
    }

    @Override // defpackage.R20
    public void b(String str, String str2) {
        Tab K0 = this.f9728a.K0();
        if (K0 == null) {
            AbstractC5142un1.a(2);
        } else if (K0.a()) {
            AbstractC5142un1.a(3);
        } else if (str2 == null || !str2.equals(K0.s())) {
            AbstractC5142un1.a(4);
        } else if (!AbstractC5652xn1.a(K0, false)) {
            AbstractC5142un1.a(5);
        } else {
            if (str == null || TextUtils.isEmpty(str)) {
                N.M0540rIu(K0.l());
            } else {
                N.Mm4YgQEb(K0.l(), str);
            }
            AbstractC5142un1.a(6);
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:24:0x00a1  */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x00b8  */
    @Override // defpackage.R20
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void c(java.lang.String r18, java.lang.String r19, java.lang.String r20, int r21, java.lang.String r22, int r23, boolean r24, boolean r25, org.chromium.url.Origin r26, android.content.Intent r27) {
        /*
        // Method dump skipped, instructions count: 776
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.C2089cu.c(java.lang.String, java.lang.String, java.lang.String, int, java.lang.String, int, boolean, boolean, org.chromium.url.Origin, android.content.Intent):void");
    }

    public final boolean d(Intent intent, String str) {
        return S20.A(intent) || TextUtils.equals(str, this.f9728a.getPackageName());
    }

    public final void e(String str, Intent intent) {
        AbstractC3535lK0.a("MobileReceivedExternalIntent");
        if (d(intent, str)) {
            AbstractC3535lK0.a("MobileReceivedExternalIntent.Chrome");
        } else {
            AbstractC3535lK0.a("MobileReceivedExternalIntent.App");
        }
    }

    public final void f(String str, String str2, String str3, String str4, boolean z, Origin origin, Intent intent, boolean z2) {
        AbstractActivityC2601fu.r1(this.f9728a, str, str2, str3, str4, z2, z, origin, intent);
        e(str4, intent);
    }
}
