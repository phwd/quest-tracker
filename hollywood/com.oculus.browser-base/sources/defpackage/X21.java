package defpackage;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.content.Context;
import android.content.res.Resources;
import android.text.TextUtils;
import android.widget.ArrayAdapter;
import android.widget.ListPopupWindow;
import com.oculus.browser.R;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Objects;
import org.chromium.base.ContextUtils;
import org.chromium.chrome.browser.compositor.CompositorViewHolder;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.chrome.browser.tabmodel.TabModel;
import org.chromium.ui.base.DeviceFormFactor;
import org.chromium.ui.base.LocalizationUtils;

/* renamed from: X21  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class X21 implements AbstractC3827n31 {

    /* renamed from: a  reason: collision with root package name */
    public static final float f9189a = ((float) Math.tan(0.7853981633974483d));
    public boolean A;
    public float B;
    public long C;
    public C3998o31 D;
    public C0053Aw E;
    public float F;
    public float G;
    public long H;
    public float I;

    /* renamed from: J  reason: collision with root package name */
    public float f9190J;
    public final boolean K;
    public float L;
    public boolean M;
    public boolean N;
    public Context O;
    public final K70 b;
    public final F70 c;
    public TabModel d;
    public A61 e;
    public AbstractC4169p31 f;
    public C0947Pm g = new C0947Pm();
    public WP0 h = new WP0();
    public C3998o31[] i = new C3998o31[0];
    public C3998o31[] j = new C3998o31[0];
    public C3998o31[] k = new C3998o31[0];
    public final V21 l = new V21(this, null);
    public final W21 m = new W21(this, null);
    public Animator n;
    public final C0053Aw o;
    public final float p = 24.0f;
    public final float q = 58.0f;
    public final float r;
    public final float s;
    public final float t;
    public final ListPopupWindow u;
    public AZ0 v;
    public int w;
    public float x;
    public float y;
    public int z = 0;

    public X21(Context context, K70 k70, F70 f70, boolean z2) {
        float f2 = 0.0f;
        this.f9190J = LocalizationUtils.isLayoutRtl() ? 0.0f : 58.0f;
        this.I = LocalizationUtils.isLayoutRtl() ? 58.0f : f2;
        this.r = 190.0f;
        this.s = 265.0f;
        this.t = 50.0f;
        this.b = k70;
        this.c = f70;
        C0053Aw aw = new C0053Aw(context, 58.0f, 32.5f, new S21(this));
        this.o = aw;
        aw.h(R.drawable.f28620_resource_name_obfuscated_RES_2131230902, R.drawable.f28630_resource_name_obfuscated_RES_2131230903, R.drawable.f28620_resource_name_obfuscated_RES_2131230902, R.drawable.f28630_resource_name_obfuscated_RES_2131230903);
        aw.m = z2;
        aw.j(6.0f);
        aw.j = 4.0f;
        Resources resources = context.getResources();
        String string = resources.getString(R.string.f46280_resource_name_obfuscated_RES_2131951945);
        String string2 = resources.getString(R.string.f46270_resource_name_obfuscated_RES_2131951944);
        aw.o = string;
        aw.p = string2;
        this.O = context;
        this.K = z2;
        this.L = 1.0f;
        ListPopupWindow listPopupWindow = new ListPopupWindow(this.O);
        this.u = listPopupWindow;
        Context context2 = this.O;
        String[] strArr = new String[1];
        strArr[0] = context2.getString(!z2 ? R.string.f54500_resource_name_obfuscated_RES_2131952767 : R.string.f54490_resource_name_obfuscated_RES_2131952766);
        listPopupWindow.setAdapter(new ArrayAdapter(context2, 17367043, strArr));
        listPopupWindow.setOnItemClickListener(new T21(this));
        listPopupWindow.setWidth(this.O.getResources().getDimensionPixelSize(R.dimen.f20690_resource_name_obfuscated_RES_2131165688));
        listPopupWindow.setModal(true);
        boolean a2 = DeviceFormFactor.a(context);
        this.M = a2;
        this.f = a2 ? this.g : this.h;
        this.N = true;
    }

    /* JADX WARNING: Removed duplicated region for block: B:17:0x0037  */
    /* JADX WARNING: Removed duplicated region for block: B:20:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void a(long r5, boolean r7) {
        /*
            r4 = this;
            boolean r0 = r4.M
            if (r0 == 0) goto L_0x0005
            return
        L_0x0005:
            org.chromium.chrome.browser.tabmodel.TabModel r0 = r4.d
            int r1 = r0.index()
            org.chromium.chrome.browser.tab.Tab r0 = r0.getTabAt(r1)
            if (r0 != 0) goto L_0x0012
            return
        L_0x0012:
            int r0 = r0.getId()
            o31 r0 = r4.f(r0)
            if (r0 == 0) goto L_0x003e
            boolean r1 = r0.k
            r2 = 1
            if (r1 == 0) goto L_0x0033
            float r1 = r0.u
            r3 = 0
            int r3 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1))
            if (r3 < 0) goto L_0x0033
            float r3 = r0.w
            float r1 = r1 + r3
            float r3 = r4.F
            int r1 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1))
            if (r1 > 0) goto L_0x0033
            r1 = r2
            goto L_0x0034
        L_0x0033:
            r1 = 0
        L_0x0034:
            if (r1 == 0) goto L_0x0037
            goto L_0x003e
        L_0x0037:
            float r0 = r4.b(r0, r2, r2, r2)
            r4.p(r0, r7, r5)
        L_0x003e:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.X21.a(long, boolean):void");
    }

    public final float b(C3998o31 o31, boolean z2, boolean z3, boolean z4) {
        int i2;
        if (o31 == null) {
            return 0.0f;
        }
        int index = this.d.index();
        int e2 = AbstractC1160Ta1.e(this.d, o31.d);
        if (index == e2 && !z2) {
            return 0.0f;
        }
        float f2 = (this.F - this.I) - this.f9190J;
        float f3 = this.y - this.p;
        float f4 = ((float) (-e2)) * f3;
        float f5 = f2 - (((float) (e2 + 1)) * f3);
        if (e2 < index) {
            f5 -= f3;
        } else if (e2 > index) {
            f4 += f3;
        }
        if (this.M) {
            i2 = this.w;
            if (((float) i2) >= f4 || !z3) {
                if (((float) i2) <= f5 || !z4) {
                    return 0.0f;
                }
                return f5 - ((float) i2);
            }
        } else {
            i2 = this.w;
        }
        return f4 - ((float) i2);
    }

    public final void c(boolean z2) {
        int count = this.d.getCount();
        C3998o31[] o31Arr = new C3998o31[count];
        for (int i2 = 0; i2 < count; i2++) {
            Tab tabAt = this.d.getTabAt(i2);
            int id = tabAt.getId();
            C3998o31 f2 = f(id);
            if (f2 == null) {
                C3998o31 o31 = new C3998o31(this.O, id, this, this.m, this.c, this.b, this.K);
                o31.x = this.G;
                o31.i();
                o31.y.bottom = o31.v + o31.x;
                Objects.requireNonNull(this.f);
                o31.m = true;
                o31.e(true);
                f2 = o31;
            }
            o31Arr[i2] = f2;
            n(o31Arr[i2], tabAt);
        }
        int length = this.i.length;
        this.i = o31Arr;
        if (count != length) {
            if (z2) {
                l(true);
            } else {
                d(true);
            }
        }
        z();
    }

    public final void d(boolean z2) {
        this.l.removeMessages(1);
        int max = Math.max(this.i.length, 1);
        this.y = AbstractC4089od0.b(((this.p * ((float) (max - 1))) + ((this.F - this.I) - this.f9190J)) / ((float) max), this.r, this.s);
        g();
        ArrayList arrayList = z2 ? new ArrayList() : null;
        int i2 = 0;
        while (true) {
            C3998o31[] o31Arr = this.i;
            if (i2 >= o31Arr.length) {
                break;
            }
            C3998o31 o31 = o31Arr[i2];
            if (!o31.l) {
                if (arrayList != null) {
                    arrayList.add(C5677xw.d(((D70) this.b).h0, o31, C3998o31.c, o31.w, this.y, 150));
                } else {
                    C3998o31 o312 = o31Arr[i2];
                    o312.w = this.y;
                    o312.i();
                    o312.y.right = o312.u + o312.w;
                }
            }
            i2++;
        }
        if (arrayList != null) {
            AnimatorSet animatorSet = new AnimatorSet();
            animatorSet.playTogether(arrayList);
            this.n = animatorSet;
            animatorSet.start();
        }
    }

    public final int e(int i2) {
        if (this.i == null) {
            return -1;
        }
        int i3 = 0;
        while (true) {
            C3998o31[] o31Arr = this.i;
            if (i3 >= o31Arr.length) {
                return -1;
            }
            if (o31Arr[i3].d == i2) {
                return i3;
            }
            i3++;
        }
    }

    public C3998o31 f(int i2) {
        if (this.i == null) {
            return null;
        }
        int i3 = 0;
        while (true) {
            C3998o31[] o31Arr = this.i;
            if (i3 >= o31Arr.length) {
                return null;
            }
            if (o31Arr[i3].d == i2) {
                return o31Arr[i3];
            }
            i3++;
        }
    }

    public void g() {
        Animator animator = this.n;
        if (animator != null) {
            animator.end();
            this.n = null;
            ArrayList arrayList = new ArrayList();
            int i2 = 0;
            while (true) {
                C3998o31[] o31Arr = this.i;
                if (i2 >= o31Arr.length) {
                    break;
                }
                C3998o31 o31 = o31Arr[i2];
                if (o31.l) {
                    arrayList.add(o31);
                }
                i2++;
            }
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                AbstractC1160Ta1.a(this.d, ((C3998o31) it.next()).d, true);
            }
            if (!arrayList.isEmpty()) {
                ((D70) this.b).s(null);
            }
        }
    }

    public final float h(boolean z2) {
        float f2;
        if (this.M) {
            return 0.0f;
        }
        if (LocalizationUtils.isLayoutRtl() != z2) {
            f2 = (float) this.w;
        } else {
            f2 = this.x - ((float) this.w);
        }
        float f3 = -f2;
        if (f3 == 0.0f) {
            return 0.0f;
        }
        if (f3 >= 24.0f) {
            return 1.0f;
        }
        return f3 / 24.0f;
    }

    public final C3998o31 i(float f2) {
        for (int length = this.j.length - 1; length >= 0; length--) {
            C3998o31 o31 = this.j[length];
            if (o31.k) {
                float f3 = o31.u;
                if (f3 <= f2 && f2 <= f3 + o31.w) {
                    return o31;
                }
            }
        }
        return null;
    }

    public void j(float f2, float f3) {
        int i2 = (this.F > f2 ? 1 : (this.F == f2 ? 0 : -1));
        if (i2 != 0 || this.G != f3) {
            int i3 = 0;
            boolean z2 = i2 != 0;
            this.F = f2;
            this.G = f3;
            int i4 = 0;
            while (true) {
                C3998o31[] o31Arr = this.i;
                if (i4 >= o31Arr.length) {
                    break;
                }
                C3998o31 o31 = o31Arr[i4];
                o31.x = this.G;
                o31.i();
                o31.y.bottom = o31.v + o31.x;
                i4++;
            }
            if (z2) {
                d(false);
                boolean z3 = f2 >= 600.0f;
                if (z3 != this.M) {
                    this.M = z3;
                    AbstractC4169p31 p31 = z3 ? this.g : this.h;
                    if (p31 != this.f) {
                        ((D70) this.b).s(null);
                    }
                    this.f = p31;
                    while (true) {
                        C3998o31[] o31Arr2 = this.i;
                        if (i3 >= o31Arr2.length) {
                            break;
                        }
                        C3998o31 o312 = o31Arr2[i3];
                        Objects.requireNonNull(this.f);
                        o312.m = true;
                        o312.e(true);
                        i3++;
                    }
                    TabModel tabModel = this.d;
                    if (tabModel.getTabAt(tabModel.index()) != null) {
                        w();
                        TabModel tabModel2 = this.d;
                        this.w = (int) (((float) this.w) + b(f(tabModel2.getTabAt(tabModel2.index()).getId()), true, true, true));
                    }
                    y();
                }
            }
            if (this.i.length > 0) {
                ((D70) this.b).s(null);
            }
            this.u.dismiss();
        }
    }

    public final void k(int i2, int i3, int i4, boolean z2) {
        int e2;
        int i5;
        C3998o31 f2 = f(i2);
        if (f2 != null && i3 != i4 && (e2 = e(i2)) != i4) {
            if (!this.A || e2 == i3 || f2 != this.D) {
                C3998o31[] o31Arr = this.i;
                int i6 = -1;
                if (e2 <= i4) {
                    if (!(e2 == i4 || e2 + 1 == i4)) {
                        C3998o31 o31 = o31Arr[e2];
                        int i7 = e2;
                        while (true) {
                            i5 = i4 - 1;
                            if (i7 >= i5) {
                                break;
                            }
                            int i8 = i7 + 1;
                            o31Arr[i7] = o31Arr[i8];
                            i7 = i8;
                        }
                        o31Arr[i5] = o31;
                    }
                } else if (e2 != i4) {
                    C3998o31 o312 = o31Arr[e2];
                    int i9 = e2;
                    while (true) {
                        i9--;
                        if (i9 < i4) {
                            break;
                        }
                        o31Arr[i9 + 1] = o31Arr[i9];
                    }
                    o31Arr[i4] = o312;
                }
                if (e2 < i4) {
                    i4--;
                }
                if (z2) {
                    float f3 = this.y - this.p;
                    if (i3 <= i4) {
                        i6 = 1;
                    }
                    float f4 = ((float) i6) * f3;
                    if (LocalizationUtils.isLayoutRtl()) {
                        f4 = -f4;
                    }
                    C3998o31 o313 = this.i[i4 - i6];
                    g();
                    C5677xw d2 = C5677xw.d(((D70) this.b).h0, o313, C3998o31.f10529a, f4, 0.0f, 125);
                    this.n = d2;
                    d2.start();
                }
            }
        }
    }

    public final void l(boolean z2) {
        boolean hasMessages = this.l.hasMessages(1);
        if (hasMessages) {
            this.l.removeMessages(1);
        }
        if (hasMessages || z2) {
            this.l.sendEmptyMessageAtTime(1, 1500);
        }
    }

    public final void m(C3998o31 o31, String str, boolean z2) {
        if (o31 != null) {
            StringBuilder sb = new StringBuilder();
            if (!TextUtils.isEmpty(str)) {
                sb.append(str);
                sb.append(", ");
            }
            sb.append(this.O.getResources().getString(this.K ? z2 ? R.string.f46090_resource_name_obfuscated_RES_2131951926 : R.string.f46100_resource_name_obfuscated_RES_2131951927 : z2 ? R.string.f46070_resource_name_obfuscated_RES_2131951924 : R.string.f46080_resource_name_obfuscated_RES_2131951925));
            o31.q = sb.toString();
            String string = ContextUtils.getApplicationContext().getString(R.string.f46030_resource_name_obfuscated_RES_2131951920, str);
            C0575Ji1 ji1 = o31.j;
            ji1.o = string;
            ji1.p = string;
        }
    }

    public final void n(C3998o31 o31, Tab tab) {
        if (tab != null) {
            m(o31, tab.getTitle(), tab.isHidden());
        }
    }

    public void o(float f2) {
        if (LocalizationUtils.isLayoutRtl()) {
            this.I = f2 + this.q;
        } else {
            this.f9190J = f2 + this.q;
        }
    }

    public final void p(float f2, boolean z2, long j2) {
        if (f2 != 0.0f) {
            if (z2) {
                this.v.e(this.w, 0, (int) f2, 0, j2, 250);
            } else {
                this.w = (int) (((float) this.w) + f2);
            }
        }
    }

    public final void q(long j2, float f2) {
        if (!this.A) {
            C0053Aw aw = this.E;
            if (aw != null && aw.k) {
                aw.k = false;
            }
            this.E = null;
            C3998o31 i2 = i(f2);
            this.D = i2;
            if (i2 != null) {
                this.C = 0;
                this.z = 0;
                this.B = f2;
                this.A = true;
                TabModel tabModel = this.d;
                tabModel.x(AbstractC1160Ta1.e(tabModel, i2.d), 3);
                if (this.M) {
                    this.v.e(this.w, 0, (int) b(this.D, true, true, true), 0, j2, 250);
                }
                ((D70) this.b).s(null);
            }
        }
    }

    public void r(int i2) {
        if (f(i2) != null) {
            C3998o31[] o31Arr = this.i;
            c(!(o31Arr[o31Arr.length - 1].d == i2));
            ((D70) this.b).s(null);
        }
    }

    public void s(long j2, int i2, int i3, boolean z2) {
        boolean z3;
        if (f(i2) == null) {
            boolean z4 = false;
            c(false);
            C3998o31 f2 = f(i2);
            if (f2 != null) {
                g();
                C5677xw d2 = C5677xw.d(((D70) this.b).h0, f2, C3998o31.b, f2.x, 0.0f, 150);
                this.n = d2;
                d2.start();
            }
            C3998o31 f3 = f(i3);
            if (!z2) {
                f3 = f2;
            }
            boolean z5 = !z2;
            if (!this.M) {
                z3 = true;
                z5 = true;
            } else {
                f2 = f3;
                z3 = false;
            }
            if (f2 != null) {
                float b2 = b(f2, z3, z5, true);
                if (!this.M) {
                    if (!this.o.l) {
                        z4 = true;
                    }
                    p(b2, z4, j2);
                } else if (b2 != 0.0f) {
                    this.v.e(this.w, 0, (int) b2, 0, j2, 250);
                }
            }
            ((D70) this.b).s(null);
        }
    }

    public void t(int i2) {
        C3998o31 f2 = f(i2);
        if (f2 != null) {
            Y91 y91 = f2.g;
            if (y91.e) {
                y91.f9258a.removeCallbacks(y91.g);
                y91.f9258a.postDelayed(y91.g, 100);
            }
        }
    }

    public void u(long j2, int i2, int i3) {
        C3998o31 f2 = f(i2);
        if (f2 == null) {
            s(j2, i2, i3, true);
            return;
        }
        z();
        a(j2, true);
        ((D70) this.b).s(null);
        n(f2, AbstractC1160Ta1.d(this.d, i2));
        n(f(i3), AbstractC1160Ta1.d(this.d, i3));
    }

    /* JADX WARNING: Removed duplicated region for block: B:37:0x0065  */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x0079  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void v(float r11) {
        /*
        // Method dump skipped, instructions count: 171
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.X21.v(float):void");
    }

    public final void w() {
        float f2;
        float f3 = (this.F - this.I) - this.f9190J;
        if (this.M) {
            int i2 = 0;
            f2 = 0.0f;
            while (true) {
                C3998o31[] o31Arr = this.i;
                if (i2 >= o31Arr.length) {
                    break;
                }
                C3998o31 o31 = o31Arr[i2];
                f2 += o31.h() * (o31.w - this.p);
                i2++;
            }
        } else {
            f2 = (this.y - this.p) * ((float) this.i.length);
        }
        float min = Math.min(0.0f, f3 - (f2 + this.p));
        this.x = min;
        if (min > -0.001f) {
            this.x = 0.0f;
        }
        x(this.w);
    }

    public final void x(int i2) {
        int i3 = this.w;
        this.w = AbstractC4089od0.c(i2, (int) this.x, 0);
        if (this.A && this.v.d()) {
            int i4 = i3 - this.w;
            if (LocalizationUtils.isLayoutRtl()) {
                i4 = -i4;
            }
            v((float) i4);
        }
    }

    public final void y() {
        float f2;
        TabModel tabModel = this.d;
        if (tabModel != null) {
            if (this.i == null || tabModel.getCount() != this.i.length) {
                c(false);
            }
            w();
            if (!LocalizationUtils.isLayoutRtl()) {
                f2 = ((float) this.w) + this.I;
            } else {
                f2 = ((this.F - this.y) - ((float) this.w)) - this.f9190J;
            }
            int i2 = 0;
            while (true) {
                C3998o31[] o31Arr = this.i;
                if (i2 >= o31Arr.length) {
                    break;
                }
                C3998o31 o31 = o31Arr[i2];
                o31.r = f2;
                float h2 = o31.h() * (o31.w - this.p);
                if (LocalizationUtils.isLayoutRtl()) {
                    h2 = -h2;
                }
                f2 += h2;
                i2++;
            }
            this.f.c(this.d.index(), this.i, 4.0f, 4, this.p, this.I, this.f9190J, this.F, this.A);
            this.f.b(this.d.index(), this.i, this.F);
            int i3 = 0;
            int i4 = 0;
            while (true) {
                C3998o31[] o31Arr2 = this.j;
                if (i3 >= o31Arr2.length) {
                    break;
                }
                if (o31Arr2[i3].k) {
                    i4++;
                }
                i3++;
            }
            if (this.k.length != i4) {
                this.k = new C3998o31[i4];
            }
            int i5 = 0;
            int i6 = 0;
            while (true) {
                C3998o31[] o31Arr3 = this.j;
                if (i5 >= o31Arr3.length) {
                    break;
                }
                if (o31Arr3[i5].k) {
                    this.k[i6] = o31Arr3[i5];
                    i6++;
                }
                i5++;
            }
            if (!this.A) {
                C3998o31[] o31Arr4 = this.i;
                if (o31Arr4.length != 0) {
                    float a2 = this.f.a(o31Arr4, this.p, this.I, this.f9190J, this.F, this.q);
                    boolean isLayoutRtl = LocalizationUtils.isLayoutRtl();
                    if ((!isLayoutRtl || this.q + a2 >= 0.0f) && (isLayoutRtl || a2 <= this.F)) {
                        C0053Aw aw = this.o;
                        aw.l = true;
                        aw.i(a2);
                    } else {
                        this.o.l = false;
                    }
                    ((CompositorViewHolder) this.c).x();
                }
            }
            this.o.l = false;
            ((CompositorViewHolder) this.c).x();
        }
    }

    public final void z() {
        C3998o31[] o31Arr = this.i;
        if (o31Arr.length != this.j.length) {
            this.j = new C3998o31[o31Arr.length];
        }
        AbstractC4169p31 p31 = this.f;
        int index = this.d.index();
        C3998o31[] o31Arr2 = this.i;
        C3998o31[] o31Arr3 = this.j;
        Objects.requireNonNull(p31);
        int i2 = 0;
        int c2 = AbstractC4089od0.c(index, 0, o31Arr2.length);
        int i3 = 0;
        while (i2 < c2) {
            o31Arr3[i3] = o31Arr2[i2];
            i2++;
            i3++;
        }
        int length = o31Arr2.length - 1;
        while (length >= c2) {
            o31Arr3[i3] = o31Arr2[length];
            length--;
            i3++;
        }
    }
}
