package defpackage;

import J.N;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.util.Size;
import android.view.View;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import org.chromium.base.ThreadUtils;
import org.chromium.base.UnguessableToken;
import org.chromium.base.task.PostTask;
import org.chromium.components.paintpreview.player.PlayerCompositorDelegateImpl;

/* renamed from: SD0  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class SD0 implements AbstractC1809bE0, TD0 {

    /* renamed from: a  reason: collision with root package name */
    public final UnguessableToken f8884a;
    public final Size b;
    public final List c = new ArrayList();
    public final List d = new ArrayList();
    public final List e = new ArrayList();
    public final List f = new ArrayList();
    public final UH0 g;
    public final AbstractC5900zD0 h;
    public final C1980cE0 i;
    public boolean j;
    public float k;
    public final Matrix l;
    public final KD0 m;
    public C2151dE0 n;

    public SD0(UH0 uh0, AbstractC5900zD0 zd0, C2151dE0 de0, UnguessableToken unguessableToken, Size size, int i2, int i3) {
        Matrix matrix = new Matrix();
        this.l = matrix;
        this.g = uh0;
        uh0.m(UD0.f, matrix);
        this.h = zd0;
        this.n = de0;
        C1980cE0 ce0 = new C1980cE0();
        this.i = ce0;
        this.j = false;
        this.k = 0.0f;
        this.f8884a = unguessableToken;
        this.b = size;
        this.m = new KD0(unguessableToken, ce0, size, zd0, this, PostTask.a(C3070if1.c));
        ce0.b.postTranslate((float) i2, (float) i3);
        ce0.g(0.0f);
    }

    public void a() {
        f(true);
        for (int i2 = 0; i2 < this.c.size(); i2++) {
            if (((View) this.c.get(i2)).getVisibility() == 0) {
                ((SD0) this.e.get(i2)).a();
            }
        }
    }

    public void b(Matrix matrix, float f2) {
        float[] fArr = new float[9];
        matrix.getValues(fArr);
        this.l.setValues(fArr);
        Matrix matrix2 = new Matrix();
        matrix2.setScale(fArr[0], fArr[4]);
        for (int i2 = 0; i2 < this.c.size(); i2++) {
            if (((View) this.c.get(i2)).getVisibility() == 0) {
                SD0 sd0 = (SD0) this.e.get(i2);
                Objects.requireNonNull(sd0);
                if (!matrix2.isIdentity()) {
                    sd0.e(sd0.i.a(), f2);
                }
                sd0.b(matrix2, f2);
            }
        }
        this.g.m(UD0.f, this.l);
    }

    public final void c(float f2) {
        this.k = f2;
        if (this.c != null) {
            for (int i2 = 0; i2 < this.c.size(); i2++) {
                ((SD0) this.e.get(i2)).c(this.k);
            }
        }
    }

    public void d(float f2) {
        this.i.g(f2);
        for (int i2 = 0; i2 < this.c.size(); i2++) {
            ((SD0) this.e.get(i2)).d(f2);
        }
    }

    public void e(Rect rect, float f2) {
        for (int i2 = 0; i2 < this.d.size(); i2++) {
            Rect rect2 = (Rect) this.f.get(i2);
            Rect rect3 = (Rect) this.d.get(i2);
            rect2.set((int) (((float) rect3.left) * f2), (int) (((float) rect3.top) * f2), (int) (((float) rect3.right) * f2), (int) (((float) rect3.bottom) * f2));
            if (!Rect.intersects(rect2, rect)) {
                ((View) this.c.get(i2)).setVisibility(8);
                rect2.set(0, 0, 0, 0);
            } else {
                int i3 = rect2.left - rect.left;
                int i4 = rect2.top - rect.top;
                rect2.set(i3, i4, rect2.width() + i3, rect2.height() + i4);
                ((View) this.c.get(i2)).setVisibility(0);
            }
        }
        this.g.m(UD0.e, this.f);
        this.g.m(UD0.d, this.c);
    }

    public void f(boolean z) {
        Set set;
        float c2 = this.i.c();
        ID0 a2 = this.m.a(z);
        if (!(a2.f == null && a2.d != null)) {
            Rect a3 = this.i.a();
            e(a3, c2);
            if (a2 == this.m.b) {
                this.g.m(UD0.b, a2.b);
                this.g.m(UD0.c, a3);
            }
            if (!(a2.f == null || a2.d == null)) {
                if (a2.g != null) {
                    for (int i2 = 0; i2 < a2.g.length; i2++) {
                        int i3 = 0;
                        while (true) {
                            boolean[][] zArr = a2.g;
                            if (i3 >= zArr[i2].length) {
                                break;
                            }
                            zArr[i2][i3] = false;
                            a2.f[i2][i3] = false;
                            i3++;
                        }
                    }
                }
                int max = Math.max(0, (int) Math.floor(((double) a3.top) / ((double) a2.b.getHeight())));
                int min = Math.min(a2.f.length, (int) Math.ceil(((double) a3.bottom) / ((double) a2.b.getHeight())));
                int max2 = Math.max(0, (int) Math.floor(((double) a3.left) / ((double) a2.b.getWidth())));
                int min2 = Math.min(a2.f[0].length, (int) Math.ceil(((double) a3.right) / ((double) a2.b.getWidth())));
                for (int i4 = max2; i4 < min2; i4++) {
                    for (int i5 = max; i5 < min; i5++) {
                        a2.g[i5][i4] = true;
                        if (a2.d(i5, i4) && (set = a2.j) != null) {
                            set.add(Integer.valueOf((a2.d.length * i5) + i4));
                        }
                    }
                }
                C3762mi0 mi0 = C3762mi0.f10441a;
                Objects.requireNonNull(mi0);
                Object obj = ThreadUtils.f10596a;
                if (mi0.b < 1) {
                    while (max2 < min2) {
                        for (int i6 = max; i6 < min; i6++) {
                            if (a2.d != null) {
                                if (i6 > 0) {
                                    a2.d(i6 - 1, max2);
                                }
                                if (i6 < a2.d.length - 1) {
                                    a2.d(i6 + 1, max2);
                                }
                                if (max2 > 0) {
                                    a2.d(i6, max2 - 1);
                                }
                                if (max2 < a2.d[i6].length - 1) {
                                    a2.d(i6, max2 + 1);
                                }
                            }
                        }
                        max2++;
                    }
                }
                if (!(a2.e == null || a2.f == null)) {
                    for (int i7 = 0; i7 < a2.e.length; i7++) {
                        int i8 = 0;
                        while (true) {
                            HD0[][] hd0Arr = a2.e;
                            if (i8 >= hd0Arr[i7].length) {
                                break;
                            }
                            if (hd0Arr[i7][i8] != null && !a2.f[i7][i8]) {
                                HD0 hd0 = hd0Arr[i7][i8];
                                AbstractC5900zD0 zd0 = hd0.e.h;
                                int i9 = hd0.d;
                                long j2 = ((PlayerCompositorDelegateImpl) zd0).b;
                                if (j2 == 0 ? false : N.MNwIEnLr(j2, i9)) {
                                    a2.e[i7][i8] = null;
                                }
                            }
                            i8++;
                        }
                    }
                }
            }
        }
    }
}
