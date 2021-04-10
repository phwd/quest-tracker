package defpackage;

import org.chromium.chrome.browser.metrics.UmaUtils;
import org.chromium.content_public.browser.WebContents;

/* renamed from: d3  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C2117d3 extends AbstractC0601Jv0 {

    /* renamed from: a  reason: collision with root package name */
    public long f9742a = -1;
    public boolean b;
    public final /* synthetic */ C2287e3 c;

    public C2117d3(C2287e3 e3Var) {
        this.c = e3Var;
    }

    @Override // defpackage.AbstractC0601Jv0
    public void a(WebContents webContents, long j, long j2, long j3) {
        if (j == this.f9742a && this.b) {
            C2287e3 e3Var = this.c;
            long j4 = (j2 / 1000) + j3;
            if (e3Var.b != 0) {
                if (UmaUtils.b() && !UmaUtils.a()) {
                    long j5 = j4 - e3Var.f9827a;
                    StringBuilder i = AbstractC2531fV.i("Startup.Android.Cold.TimeToFirstContentfulPaint");
                    i.append(e3Var.c);
                    AbstractC3364kK0.j(i.toString(), j5);
                    if (e3Var.c.equals(".Tabbed") && !e3Var.g) {
                        e3Var.g = true;
                        AbstractC3364kK0.j("Startup.Android.Cold.TimeToVisibleContent", j5);
                    }
                }
                e3Var.a();
            }
        }
    }

    @Override // defpackage.AbstractC0601Jv0
    public void c(WebContents webContents, long j, boolean z) {
        if (this.f9742a == -1) {
            this.f9742a = j;
            this.b = this.c.f;
        }
    }
}
