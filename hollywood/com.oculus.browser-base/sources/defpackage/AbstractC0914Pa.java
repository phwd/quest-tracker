package defpackage;

import android.util.SparseArray;
import java.util.concurrent.ExecutionException;
import org.chromium.base.TraceEvent;

/* renamed from: Pa  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class AbstractC0914Pa extends AbstractC4394qM0 {
    public final SparseArray c = new SparseArray();
    public final AbstractC0853Oa d;

    public AbstractC0914Pa(int i, AbstractC4223pM0 pm0, AbstractC0853Oa oa) {
        super(i, pm0);
        this.d = oa;
    }

    @Override // defpackage.AbstractC4394qM0
    public void a(int i) {
        C0792Na na = (C0792Na) this.c.get(i);
        if (na == null || na.b(false)) {
            e(d(i), i);
            return;
        }
        try {
            e((AbstractC3197jM0) na.g(), i);
        } catch (InterruptedException unused) {
            b(i, null);
        } catch (ExecutionException unused2) {
            b(i, null);
        }
    }

    @Override // defpackage.AbstractC4394qM0
    public void c(int i) {
        if (this.c.get(i) == null) {
            C0792Na na = new C0792Na(this, i);
            na.d(AbstractC2032cb.b);
            this.c.put(i, na);
        }
    }

    public final AbstractC3197jM0 d(int i) {
        try {
            TraceEvent.Y("AsyncPreloadResourceLoader.createResource", null);
            return this.d.a(i);
        } finally {
            TraceEvent.f0("AsyncPreloadResourceLoader.createResource");
        }
    }

    public final void e(AbstractC3197jM0 jm0, int i) {
        b(i, jm0);
        this.c.remove(i);
    }
}
