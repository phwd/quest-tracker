package defpackage;

import android.view.View;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import java.util.ArrayList;
import java.util.Objects;

/* renamed from: TZ0  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class TZ0 {

    /* renamed from: a  reason: collision with root package name */
    public ArrayList f8965a = new ArrayList();
    public int b = Integer.MIN_VALUE;
    public int c = Integer.MIN_VALUE;
    public int d = 0;
    public final int e;
    public final /* synthetic */ StaggeredGridLayoutManager f;

    public TZ0(StaggeredGridLayoutManager staggeredGridLayoutManager, int i) {
        this.f = staggeredGridLayoutManager;
        this.e = i;
    }

    public void a(View view) {
        PZ0 j = j(view);
        j.e = this;
        this.f8965a.add(view);
        this.c = Integer.MIN_VALUE;
        if (this.f8965a.size() == 1) {
            this.b = Integer.MIN_VALUE;
        }
        if (j.c() || j.b()) {
            this.d = this.f.t.c(view) + this.d;
        }
    }

    public void b() {
        ArrayList arrayList = this.f8965a;
        View view = (View) arrayList.get(arrayList.size() - 1);
        PZ0 j = j(view);
        this.c = this.f.t.b(view);
        Objects.requireNonNull(j);
    }

    public void c() {
        View view = (View) this.f8965a.get(0);
        PZ0 j = j(view);
        this.b = this.f.t.e(view);
        Objects.requireNonNull(j);
    }

    public void d() {
        this.f8965a.clear();
        this.b = Integer.MIN_VALUE;
        this.c = Integer.MIN_VALUE;
        this.d = 0;
    }

    public int e() {
        if (this.f.y) {
            return g(this.f8965a.size() - 1, -1, true);
        }
        return g(0, this.f8965a.size(), true);
    }

    public int f() {
        if (this.f.y) {
            return g(0, this.f8965a.size(), true);
        }
        return g(this.f8965a.size() - 1, -1, true);
    }

    public int g(int i, int i2, boolean z) {
        int k = this.f.t.k();
        int g = this.f.t.g();
        int i3 = i2 > i ? 1 : -1;
        while (i != i2) {
            View view = (View) this.f8965a.get(i);
            int e2 = this.f.t.e(view);
            int b2 = this.f.t.b(view);
            boolean z2 = false;
            boolean z3 = !z ? e2 < g : e2 <= g;
            if (!z ? b2 > k : b2 >= k) {
                z2 = true;
            }
            if (z3 && z2 && (e2 < k || b2 > g)) {
                return this.f.R(view);
            }
            i += i3;
        }
        return -1;
    }

    public int h(int i) {
        int i2 = this.c;
        if (i2 != Integer.MIN_VALUE) {
            return i2;
        }
        if (this.f8965a.size() == 0) {
            return i;
        }
        b();
        return this.c;
    }

    public View i(int i, int i2) {
        View view = null;
        if (i2 != -1) {
            int size = this.f8965a.size() - 1;
            while (size >= 0) {
                View view2 = (View) this.f8965a.get(size);
                StaggeredGridLayoutManager staggeredGridLayoutManager = this.f;
                if (staggeredGridLayoutManager.y && staggeredGridLayoutManager.R(view2) >= i) {
                    break;
                }
                StaggeredGridLayoutManager staggeredGridLayoutManager2 = this.f;
                if ((!staggeredGridLayoutManager2.y && staggeredGridLayoutManager2.R(view2) <= i) || !view2.hasFocusable()) {
                    break;
                }
                size--;
                view = view2;
            }
        } else {
            int size2 = this.f8965a.size();
            int i3 = 0;
            while (i3 < size2) {
                View view3 = (View) this.f8965a.get(i3);
                StaggeredGridLayoutManager staggeredGridLayoutManager3 = this.f;
                if (staggeredGridLayoutManager3.y && staggeredGridLayoutManager3.R(view3) <= i) {
                    break;
                }
                StaggeredGridLayoutManager staggeredGridLayoutManager4 = this.f;
                if ((!staggeredGridLayoutManager4.y && staggeredGridLayoutManager4.R(view3) >= i) || !view3.hasFocusable()) {
                    break;
                }
                i3++;
                view = view3;
            }
        }
        return view;
    }

    public PZ0 j(View view) {
        return (PZ0) view.getLayoutParams();
    }

    public int k(int i) {
        int i2 = this.b;
        if (i2 != Integer.MIN_VALUE) {
            return i2;
        }
        if (this.f8965a.size() == 0) {
            return i;
        }
        c();
        return this.b;
    }

    public void l() {
        int size = this.f8965a.size();
        View view = (View) this.f8965a.remove(size - 1);
        PZ0 j = j(view);
        j.e = null;
        if (j.c() || j.b()) {
            this.d -= this.f.t.c(view);
        }
        if (size == 1) {
            this.b = Integer.MIN_VALUE;
        }
        this.c = Integer.MIN_VALUE;
    }

    public void m() {
        View view = (View) this.f8965a.remove(0);
        PZ0 j = j(view);
        j.e = null;
        if (this.f8965a.size() == 0) {
            this.c = Integer.MIN_VALUE;
        }
        if (j.c() || j.b()) {
            this.d -= this.f.t.c(view);
        }
        this.b = Integer.MIN_VALUE;
    }

    public void n(View view) {
        PZ0 j = j(view);
        j.e = this;
        this.f8965a.add(0, view);
        this.b = Integer.MIN_VALUE;
        if (this.f8965a.size() == 1) {
            this.c = Integer.MIN_VALUE;
        }
        if (j.c() || j.b()) {
            this.d = this.f.t.c(view) + this.d;
        }
    }
}
