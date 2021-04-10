package defpackage;

import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.preference.Preference;
import androidx.preference.PreferenceScreen;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

/* renamed from: lF0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C3520lF0 extends AbstractC5750yK0 {
    public AbstractC2837hF0 I;

    /* renamed from: J  reason: collision with root package name */
    public List f10335J;
    public List K;
    public List L;
    public Handler M;
    public Runnable N = new RunnableC3008iF0(this);

    public C3520lF0(AbstractC2837hF0 hf0) {
        this.I = hf0;
        this.M = new Handler();
        this.I.m0 = this;
        this.f10335J = new ArrayList();
        this.K = new ArrayList();
        this.L = new ArrayList();
        AbstractC2837hF0 hf02 = this.I;
        if (hf02 instanceof PreferenceScreen) {
            r(((PreferenceScreen) hf02).C0);
        } else {
            r(true);
        }
        x();
    }

    @Override // defpackage.AbstractC5750yK0
    public int b() {
        return this.K.size();
    }

    @Override // defpackage.AbstractC5750yK0
    public long c(int i) {
        if (!this.G) {
            return -1;
        }
        return u(i).k();
    }

    @Override // defpackage.AbstractC5750yK0
    public int d(int i) {
        C3349kF0 kf0 = new C3349kF0(u(i));
        int indexOf = this.L.indexOf(kf0);
        if (indexOf != -1) {
            return indexOf;
        }
        int size = this.L.size();
        this.L.add(kf0);
        return size;
    }

    @Override // defpackage.AbstractC5750yK0
    public void j(XK0 xk0, int i) {
        u(i).x((C4886tF0) xk0);
    }

    @Override // defpackage.AbstractC5750yK0
    public XK0 m(ViewGroup viewGroup, int i) {
        C3349kF0 kf0 = (C3349kF0) this.L.get(i);
        LayoutInflater from = LayoutInflater.from(viewGroup.getContext());
        TypedArray obtainStyledAttributes = viewGroup.getContext().obtainStyledAttributes((AttributeSet) null, FJ0.r);
        Drawable drawable = obtainStyledAttributes.getDrawable(0);
        if (drawable == null) {
            drawable = AbstractC5544x8.a(viewGroup.getContext(), 17301602);
        }
        obtainStyledAttributes.recycle();
        View inflate = from.inflate(kf0.f10267a, viewGroup, false);
        if (inflate.getBackground() == null) {
            AtomicInteger atomicInteger = AbstractC1920bu1.f9571a;
            inflate.setBackground(drawable);
        }
        ViewGroup viewGroup2 = (ViewGroup) inflate.findViewById(16908312);
        if (viewGroup2 != null) {
            int i2 = kf0.b;
            if (i2 != 0) {
                from.inflate(i2, viewGroup2);
            } else {
                viewGroup2.setVisibility(8);
            }
        }
        return new C4886tF0(inflate);
    }

    public final List s(AbstractC2837hF0 hf0) {
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        int d0 = hf0.d0();
        int i = 0;
        for (int i2 = 0; i2 < d0; i2++) {
            Preference c0 = hf0.c0(i2);
            if (c0.c0) {
                if (!v(hf0) || i < hf0.z0) {
                    arrayList.add(c0);
                } else {
                    arrayList2.add(c0);
                }
                if (!(c0 instanceof AbstractC2837hF0)) {
                    i++;
                } else {
                    AbstractC2837hF0 hf02 = (AbstractC2837hF0) c0;
                    if (!(!(hf02 instanceof PreferenceScreen))) {
                        continue;
                    } else if (!v(hf0) || !v(hf02)) {
                        Iterator it = ((ArrayList) s(hf02)).iterator();
                        while (it.hasNext()) {
                            Preference preference = (Preference) it.next();
                            if (!v(hf0) || i < hf0.z0) {
                                arrayList.add(preference);
                            } else {
                                arrayList2.add(preference);
                            }
                            i++;
                        }
                    } else {
                        throw new IllegalStateException("Nesting an expandable group inside of another expandable group is not supported!");
                    }
                }
            }
        }
        if (v(hf0) && i > hf0.z0) {
            C4393qM qMVar = new C4393qM(hf0.F, arrayList2, hf0.H);
            qMVar.K = new C3178jF0(this, hf0);
            arrayList.add(qMVar);
        }
        return arrayList;
    }

    public final void t(List list, AbstractC2837hF0 hf0) {
        synchronized (hf0) {
            Collections.sort(hf0.v0);
        }
        int d0 = hf0.d0();
        for (int i = 0; i < d0; i++) {
            Preference c0 = hf0.c0(i);
            list.add(c0);
            C3349kF0 kf0 = new C3349kF0(c0);
            if (!this.L.contains(kf0)) {
                this.L.add(kf0);
            }
            if (c0 instanceof AbstractC2837hF0) {
                AbstractC2837hF0 hf02 = (AbstractC2837hF0) c0;
                if (!(hf02 instanceof PreferenceScreen)) {
                    t(list, hf02);
                }
            }
            c0.m0 = this;
        }
    }

    public Preference u(int i) {
        if (i < 0 || i >= b()) {
            return null;
        }
        return (Preference) this.K.get(i);
    }

    public final boolean v(AbstractC2837hF0 hf0) {
        return hf0.z0 != Integer.MAX_VALUE;
    }

    public void w() {
        this.M.removeCallbacks(this.N);
        this.M.post(this.N);
    }

    public void x() {
        for (Preference preference : this.f10335J) {
            preference.m0 = null;
        }
        ArrayList arrayList = new ArrayList(this.f10335J.size());
        this.f10335J = arrayList;
        t(arrayList, this.I);
        this.K = s(this.I);
        C4375qF0 qf0 = this.I.G;
        this.F.b();
        for (Preference preference2 : this.f10335J) {
            Objects.requireNonNull(preference2);
        }
    }
}
