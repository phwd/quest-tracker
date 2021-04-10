package defpackage;

import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

/* renamed from: go  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C2754go {

    /* renamed from: a  reason: collision with root package name */
    public final C5240vK0 f10022a;
    public final C2583fo b = new C2583fo();
    public final List c = new ArrayList();

    public C2754go(C5240vK0 vk0) {
        this.f10022a = vk0;
    }

    public void a(View view, int i, boolean z) {
        int i2;
        if (i < 0) {
            i2 = this.f10022a.b();
        } else {
            i2 = f(i);
        }
        this.b.e(i2, z);
        if (z) {
            i(view);
        }
        C5240vK0 vk0 = this.f10022a;
        vk0.f11475a.addView(view, i2);
        RecyclerView recyclerView = vk0.f11475a;
        Objects.requireNonNull(recyclerView);
        XK0 M = RecyclerView.M(view);
        recyclerView.X();
        AbstractC5750yK0 yk0 = recyclerView.T;
        if (!(yk0 == null || M == null)) {
            yk0.p(M);
        }
        List list = recyclerView.o0;
        if (list != null) {
            int size = list.size();
            while (true) {
                size--;
                if (size >= 0) {
                    ((KK0) recyclerView.o0.get(size)).b(view);
                } else {
                    return;
                }
            }
        }
    }

    public void b(View view, int i, ViewGroup.LayoutParams layoutParams, boolean z) {
        int i2;
        if (i < 0) {
            i2 = this.f10022a.b();
        } else {
            i2 = f(i);
        }
        this.b.e(i2, z);
        if (z) {
            i(view);
        }
        C5240vK0 vk0 = this.f10022a;
        Objects.requireNonNull(vk0);
        XK0 M = RecyclerView.M(view);
        if (M != null) {
            if (M.p() || M.v()) {
                M.P &= -257;
            } else {
                StringBuilder sb = new StringBuilder();
                sb.append("Called attach on a child which is not detached: ");
                sb.append(M);
                throw new IllegalArgumentException(AbstractC2531fV.v(vk0.f11475a, sb));
            }
        }
        vk0.f11475a.attachViewToParent(view, i2, layoutParams);
    }

    public void c(int i) {
        XK0 M;
        int f = f(i);
        this.b.f(f);
        C5240vK0 vk0 = this.f10022a;
        View childAt = vk0.f11475a.getChildAt(f);
        if (!(childAt == null || (M = RecyclerView.M(childAt)) == null)) {
            if (!M.p() || M.v()) {
                M.b(256);
            } else {
                StringBuilder sb = new StringBuilder();
                sb.append("called detach on an already detached child ");
                sb.append(M);
                throw new IllegalArgumentException(AbstractC2531fV.v(vk0.f11475a, sb));
            }
        }
        vk0.f11475a.detachViewFromParent((RecyclerView) f);
    }

    public View d(int i) {
        return this.f10022a.a(f(i));
    }

    public int e() {
        return this.f10022a.b() - this.c.size();
    }

    public final int f(int i) {
        if (i < 0) {
            return -1;
        }
        int b2 = this.f10022a.b();
        int i2 = i;
        while (i2 < b2) {
            int b3 = i - (i2 - this.b.b(i2));
            if (b3 == 0) {
                while (this.b.d(i2)) {
                    i2++;
                }
                return i2;
            }
            i2 += b3;
        }
        return -1;
    }

    public View g(int i) {
        return this.f10022a.f11475a.getChildAt(i);
    }

    public int h() {
        return this.f10022a.b();
    }

    public final void i(View view) {
        this.c.add(view);
        C5240vK0 vk0 = this.f10022a;
        Objects.requireNonNull(vk0);
        XK0 M = RecyclerView.M(view);
        if (M != null) {
            RecyclerView recyclerView = vk0.f11475a;
            int i = M.W;
            if (i != -1) {
                M.V = i;
            } else {
                View view2 = M.G;
                AtomicInteger atomicInteger = AbstractC1920bu1.f9571a;
                M.V = view2.getImportantForAccessibility();
            }
            recyclerView.r0(M, 4);
        }
    }

    public int j(View view) {
        int indexOfChild = this.f10022a.f11475a.indexOfChild(view);
        if (indexOfChild != -1 && !this.b.d(indexOfChild)) {
            return indexOfChild - this.b.b(indexOfChild);
        }
        return -1;
    }

    public boolean k(View view) {
        return this.c.contains(view);
    }

    public final boolean l(View view) {
        if (!this.c.remove(view)) {
            return false;
        }
        C5240vK0 vk0 = this.f10022a;
        Objects.requireNonNull(vk0);
        XK0 M = RecyclerView.M(view);
        if (M == null) {
            return true;
        }
        vk0.f11475a.r0(M, M.V);
        M.V = 0;
        return true;
    }

    public String toString() {
        return this.b.toString() + ", hidden list:" + this.c.size();
    }
}
