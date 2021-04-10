package defpackage;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.Executor;
import org.chromium.base.ApplicationStatus;
import org.chromium.base.Callback;
import org.chromium.base.task.PostTask;
import org.chromium.chrome.browser.app.ChromeActivity;

/* renamed from: wq1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C5491wq1 implements Z9 {
    public final C1322Vq0 F = new C1322Vq0();
    public final E10 G;
    public final C5151uq1 H;
    public final C6001zq1 I;

    /* renamed from: J  reason: collision with root package name */
    public C5321vq1 f11572J;
    public boolean K;

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x003f, code lost:
        if (r0.equals("inline_update_success") != false) goto L_0x008a;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public C5491wq1(defpackage.AbstractC4811sq1 r12) {
        /*
        // Method dump skipped, instructions count: 274
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.C5491wq1.<init>(sq1):void");
    }

    public boolean a(Callback callback) {
        if (this.F.F.contains(callback)) {
            return false;
        }
        this.F.b(callback);
        C5321vq1 vq1 = this.f11572J;
        if (vq1 != null) {
            PostTask.b(Zo1.f9374a, callback.a(vq1), 0);
            return true;
        } else if (this.H.f != 0) {
            return true;
        } else {
            C5151uq1 uq1 = this.H;
            Executor executor = AbstractC2032cb.f9616a;
            uq1.f();
            ((ExecutorC1463Ya) executor).execute(uq1.e);
            return true;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:40:0x00b6, code lost:
        if (r2 == 0) goto L_0x00c2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:0x00bc, code lost:
        if (r2 == 0) goto L_0x00c1;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void b() {
        /*
        // Method dump skipped, instructions count: 288
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.C5491wq1.b():void");
    }

    public void c(int i) {
        C5321vq1 vq1 = this.f11572J;
        if (vq1 != null && vq1.f11503a == 5) {
            AbstractC3364kK0.g("GoogleUpdate.Inline.UI.Install.Source", i, 3);
            this.G.a();
        }
    }

    public final void d() {
        Iterator it = this.F.iterator();
        while (true) {
            C1261Uq0 uq0 = (C1261Uq0) it;
            if (uq0.hasNext()) {
                ((Callback) uq0.next()).onResult(this.f11572J);
            } else {
                return;
            }
        }
    }

    public void e(Callback callback) {
        if (this.F.F.contains(callback)) {
            this.F.c(callback);
        }
    }

    public void f(int i, Activity activity) {
        C5321vq1 vq1 = this.f11572J;
        if (vq1 != null && vq1.f11503a == 3) {
            AbstractC3364kK0.g("GoogleUpdate.Inline.UI.Retry.Source", i, 3);
            this.I.a(1, i);
            this.G.d(activity);
        }
    }

    public void g(int i, Activity activity) {
        C5321vq1 vq1 = this.f11572J;
        if (vq1 != null && vq1.f11503a == 3) {
            AbstractC3364kK0.g("GoogleUpdate.Inline.UI.Start.Source", i, 3);
            this.I.a(1, i);
            this.G.d(activity);
        }
    }

    public boolean h(Context context, int i, boolean z) {
        C5321vq1 vq1 = this.f11572J;
        if (vq1 == null || vq1.f11503a != 1 || TextUtils.isEmpty(vq1.b)) {
            return false;
        }
        try {
            this.I.a(0, i);
            Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(this.f11572J.b));
            if (z) {
                intent.addFlags(268435456);
            }
            context.startActivity(intent);
            return true;
        } catch (ActivityNotFoundException unused) {
            return false;
        }
    }

    @Override // defpackage.Z9
    public void t(Activity activity, int i) {
        Iterator it = ((ArrayList) ApplicationStatus.d()).iterator();
        boolean z = false;
        while (it.hasNext()) {
            Activity activity2 = (Activity) it.next();
            if (activity2 != null && (activity2 instanceof ChromeActivity)) {
                z |= ApplicationStatus.e(activity2) == 3;
                if (z) {
                    break;
                }
            }
        }
        this.G.setEnabled(z);
    }
}
