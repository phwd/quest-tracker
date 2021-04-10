package defpackage;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.viewpager.widget.ViewPager;
import java.util.HashMap;
import java.util.Map;

/* renamed from: j0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C3132j0 extends AbstractC0966Pv0 implements AbstractC2136d90 {
    public final C1794b90 c;
    public final Map d;

    public C3132j0(C1794b90 b90) {
        this.c = b90;
        this.d = new HashMap(b90.size());
    }

    @Override // defpackage.AbstractC2136d90
    public void a(Object obj, Object obj2, int i, int i2) {
        C1794b90 b90 = (C1794b90) obj;
        ViewPager viewPager = (ViewPager) obj2;
        i();
    }

    @Override // defpackage.AbstractC2136d90
    public void b(Object obj, Object obj2, int i, int i2) {
        C1794b90 b90 = (C1794b90) obj;
        ViewPager viewPager = (ViewPager) obj2;
        i();
    }

    @Override // defpackage.AbstractC2136d90
    public void c(Object obj, Object obj2, int i, int i2) {
        C1794b90 b90 = (C1794b90) obj;
        ViewPager viewPager = (ViewPager) obj2;
        i();
    }

    @Override // defpackage.AbstractC0966Pv0
    public void d(ViewGroup viewGroup, int i, Object obj) {
        if (obj != null) {
            ViewGroup viewGroup2 = (ViewGroup) obj;
            if (viewGroup.indexOfChild(viewGroup2) != -1) {
                viewGroup.removeView(viewGroup2);
            }
            for (Map.Entry entry : this.d.entrySet()) {
                if (((ViewGroup) entry.getValue()).equals(viewGroup2)) {
                    this.d.remove(entry.getKey());
                    return;
                }
            }
        }
    }

    @Override // defpackage.AbstractC0966Pv0
    public int e() {
        return this.c.size();
    }

    @Override // defpackage.AbstractC0966Pv0
    public int f(Object obj) {
        ViewGroup viewGroup = (ViewGroup) obj;
        for (int i = 0; i < this.c.size(); i++) {
            if (viewGroup.equals(this.d.get(this.c.get(i)))) {
                return i;
            }
        }
        return -2;
    }

    @Override // defpackage.AbstractC0966Pv0
    public Object g(ViewGroup viewGroup, int i) {
        C3148j50 j50 = (C3148j50) this.c.G.get(i);
        ViewGroup viewGroup2 = (ViewGroup) this.d.get(j50);
        if (viewGroup2 == null) {
            viewGroup2 = (ViewGroup) LayoutInflater.from(viewGroup.getContext()).inflate(j50.e, viewGroup, false);
            this.d.put(j50, viewGroup2);
            if (viewGroup.indexOfChild(viewGroup2) == -1) {
                viewGroup.addView(viewGroup2);
            }
            AbstractC4329q0 q0Var = j50.g;
            if (q0Var != null) {
                q0Var.b(viewGroup2);
            }
        }
        return viewGroup2;
    }

    @Override // defpackage.AbstractC0966Pv0
    public boolean h(View view, Object obj) {
        return view == obj;
    }
}
