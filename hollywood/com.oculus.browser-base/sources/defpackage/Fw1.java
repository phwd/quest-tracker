package defpackage;

import J.N;
import android.content.Intent;
import android.content.pm.PackageInfo;
import org.chromium.chrome.browser.webapps.WebApkHandlerDelegate;

/* renamed from: Fw1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class Fw1 implements Hw1 {

    /* renamed from: a  reason: collision with root package name */
    public final WebApkHandlerDelegate f8053a;
    public final PackageInfo b;

    public Fw1(WebApkHandlerDelegate webApkHandlerDelegate, PackageInfo packageInfo) {
        this.f8053a = webApkHandlerDelegate;
        this.b = packageInfo;
    }

    @Override // defpackage.Hw1
    public void a(boolean z, String str) {
        C1761ay1 ay1;
        String str2;
        boolean z2;
        long j;
        long j2;
        String str3;
        boolean z3;
        WebApkHandlerDelegate webApkHandlerDelegate = this.f8053a;
        PackageInfo packageInfo = this.b;
        if (webApkHandlerDelegate.f10802a != 0) {
            AbstractC3767mk a2 = Kw1.a(new Intent(), packageInfo.packageName, "", 0, false, false, null, null);
            if (a2 == null) {
                ay1 = null;
            } else {
                ay1 = new C1761ay1(a2);
            }
            if (ay1 != null) {
                Xx1 c = AbstractC2957hy1.f10115a.c(ay1.c().f9308a);
                String str4 = "Not updatable";
                if (c != null) {
                    long j3 = c.c.getLong("last_check_web_manifest_update_time", 0);
                    long j4 = c.c.getLong("last_update_request_complete_time", 0);
                    boolean z4 = c.c.getBoolean("relax_updates", false);
                    if (!c.e()) {
                        if (c.c.getBoolean("update_scheduled", false)) {
                            str3 = "Scheduled";
                        } else if (c.c.getBoolean("should_force_update", false)) {
                            str3 = "Pending";
                        } else {
                            if (c.c.getLong("last_update_request_complete_time", 0) == 0) {
                                z3 = true;
                            } else {
                                z3 = c.c.getBoolean("did_last_update_request_succeed", false);
                            }
                            str3 = z3 ? "Succeeded" : "Failed";
                        }
                        str4 = str3;
                    }
                    str2 = str4;
                    j2 = j3;
                    z2 = z4;
                    j = j4;
                } else {
                    j2 = 0;
                    j = 0;
                    str2 = str4;
                    z2 = false;
                }
                long j5 = webApkHandlerDelegate.f10802a;
                String str5 = ay1.c().e;
                String str6 = ay1.c().f;
                String g = ay1.g();
                String str7 = ay1.c().f9308a;
                int i = ay1.b().b;
                int i2 = packageInfo.versionCode;
                String f = ay1.f();
                String str8 = ay1.c().c;
                String d = ay1.d();
                String str9 = ay1.b().d;
                int a3 = ay1.a();
                int i3 = ay1.c().h;
                long e = ay1.e();
                Integer num = ay1.c().j;
                N.MXibFIFs(j5, str5, str6, g, str7, i, i2, f, str8, d, str9, a3, i3, e, num != null ? (long) num.intValue() : 2147483648L, j2, j, z2, str, z, str2);
            }
        }
    }
}
