package defpackage;

import android.content.Context;
import java.util.Objects;
import org.chromium.base.ThreadUtils;
import org.chromium.base.task.PostTask;
import org.chromium.content.browser.BrowserStartupControllerImpl;

/* renamed from: sm0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class AbstractC4798sm0 implements AbstractC0865Oe {

    /* renamed from: a  reason: collision with root package name */
    public int f11299a;
    public boolean b;
    public boolean c;
    public boolean d;
    public C3107is e;

    @Override // defpackage.AbstractC0865Oe
    public final boolean a(Context context, C2046cf1 cf1) {
        Object obj = ThreadUtils.f10596a;
        this.b = true;
        i();
        if (((BrowserStartupControllerImpl) AbstractC4280pk.a()).f()) {
            return h(context, cf1);
        }
        return g(context, cf1);
    }

    @Override // defpackage.AbstractC0865Oe
    public final boolean b(Context context, C2046cf1 cf1, AbstractC0804Ne ne) {
        Object obj = ThreadUtils.f10596a;
        this.f11299a = cf1.f9623a;
        C4116om0 om0 = new C4116om0(this, ne);
        int e2 = e(context, cf1, om0);
        boolean z = false;
        if (e2 == 2) {
            return false;
        }
        if (e2 == 1) {
            PostTask.b(Zo1.f9374a, new RunnableC4458qm0(this, ne), 0);
            return true;
        }
        RunnableC4628rm0 rm0 = new RunnableC4628rm0(this, context, cf1, om0);
        RunnableC4458qm0 qm0 = new RunnableC4458qm0(this, om0);
        if (((BrowserStartupControllerImpl) AbstractC4280pk.a()).f()) {
            this.c = false;
            d().a(this.f11299a, this.c);
            this.e.a(this.f11299a, this.c);
            PostTask.b(Zo1.f9374a, rm0, 0);
        } else {
            BrowserStartupControllerImpl browserStartupControllerImpl = (BrowserStartupControllerImpl) AbstractC4280pk.a();
            Objects.requireNonNull(browserStartupControllerImpl);
            if (browserStartupControllerImpl.l && !browserStartupControllerImpl.h && browserStartupControllerImpl.i) {
                z = true;
            }
            this.c = j();
            d().a(this.f11299a, this.c);
            PostTask.b(Zo1.f9374a, new RunnableC4287pm0(this, z, rm0, qm0), 0);
        }
        return true;
    }

    public final AbstractC2556ff d() {
        Objects.requireNonNull(this.e);
        return C5116uf.f();
    }

    public abstract int e(Context context, C2046cf1 cf1, AbstractC0804Ne ne);

    public abstract void f(Context context, C2046cf1 cf1, AbstractC0804Ne ne);

    public abstract boolean g(Context context, C2046cf1 cf1);

    public abstract boolean h(Context context, C2046cf1 cf1);

    public final void i() {
        Object obj = ThreadUtils.f10596a;
        if (!this.d) {
            this.d = true;
            AbstractC2556ff d2 = d();
            int i = this.f11299a;
            boolean z = this.c;
            C5116uf ufVar = (C5116uf) d2;
            int b2 = AbstractC2556ff.b(i);
            ufVar.c("Android.NativeBackgroundTask.TaskFinished", b2);
            if (z) {
                ufVar.c("Android.NativeBackgroundTask.TaskFinished.ReducedMode", b2);
            } else {
                ufVar.c("Android.NativeBackgroundTask.TaskFinished.FullBrowser", b2);
            }
        }
    }

    public boolean j() {
        return false;
    }
}
