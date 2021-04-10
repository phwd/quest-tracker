package defpackage;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.widget.TextView;
import java.lang.ref.WeakReference;
import java.util.Arrays;

/* renamed from: M8  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class M8 {

    /* renamed from: a  reason: collision with root package name */
    public final TextView f8461a;
    public C0392Gi1 b;
    public C0392Gi1 c;
    public C0392Gi1 d;
    public C0392Gi1 e;
    public C0392Gi1 f;
    public C0392Gi1 g;
    public final R8 h;
    public int i = 0;
    public int j = -1;
    public Typeface k;
    public boolean l;

    public M8(TextView textView) {
        this.f8461a = textView;
        this.h = new R8(textView);
    }

    public static C0392Gi1 c(Context context, C3840n8 n8Var, int i2) {
        ColorStateList d2 = n8Var.d(context, i2);
        if (d2 == null) {
            return null;
        }
        C0392Gi1 gi1 = new C0392Gi1();
        gi1.d = true;
        gi1.f8104a = d2;
        return gi1;
    }

    public final void a(Drawable drawable, C0392Gi1 gi1) {
        if (drawable != null && gi1 != null) {
            C3840n8.f(drawable, gi1, this.f8461a.getDrawableState());
        }
    }

    public void b() {
        if (!(this.b == null && this.c == null && this.d == null && this.e == null)) {
            Drawable[] compoundDrawables = this.f8461a.getCompoundDrawables();
            a(compoundDrawables[0], this.b);
            a(compoundDrawables[1], this.c);
            a(compoundDrawables[2], this.d);
            a(compoundDrawables[3], this.e);
        }
        if (this.f != null || this.g != null) {
            Drawable[] compoundDrawablesRelative = this.f8461a.getCompoundDrawablesRelative();
            a(compoundDrawablesRelative[0], this.f);
            a(compoundDrawablesRelative[2], this.g);
        }
    }

    public boolean d() {
        R8 r8 = this.h;
        return r8.i() && r8.d != 0;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:194:0x036e, code lost:
        if (r3 != null) goto L_0x0375;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void e(android.util.AttributeSet r23, int r24) {
        /*
        // Method dump skipped, instructions count: 964
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.M8.e(android.util.AttributeSet, int):void");
    }

    public void f(Context context, int i2) {
        String m;
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(i2, FJ0.I0);
        C0514Ii1 ii1 = new C0514Ii1(context, obtainStyledAttributes);
        if (ii1.o(14)) {
            this.f8461a.setAllCaps(ii1.a(14, false));
        }
        int i3 = Build.VERSION.SDK_INT;
        if (ii1.o(0) && ii1.f(0, -1) == 0) {
            this.f8461a.setTextSize(0, 0.0f);
        }
        j(context, ii1);
        if (i3 >= 26 && ii1.o(13) && (m = ii1.m(13)) != null) {
            this.f8461a.setFontVariationSettings(m);
        }
        obtainStyledAttributes.recycle();
        Typeface typeface = this.k;
        if (typeface != null) {
            this.f8461a.setTypeface(typeface, this.i);
        }
    }

    public void g(int i2, int i3, int i4, int i5) {
        R8 r8 = this.h;
        if (r8.i()) {
            DisplayMetrics displayMetrics = r8.m.getResources().getDisplayMetrics();
            r8.j(TypedValue.applyDimension(i5, (float) i2, displayMetrics), TypedValue.applyDimension(i5, (float) i3, displayMetrics), TypedValue.applyDimension(i5, (float) i4, displayMetrics));
            if (r8.g()) {
                r8.a();
            }
        }
    }

    public void h(int[] iArr, int i2) {
        R8 r8 = this.h;
        if (r8.i()) {
            int length = iArr.length;
            if (length > 0) {
                int[] iArr2 = new int[length];
                if (i2 == 0) {
                    iArr2 = Arrays.copyOf(iArr, length);
                } else {
                    DisplayMetrics displayMetrics = r8.m.getResources().getDisplayMetrics();
                    for (int i3 = 0; i3 < length; i3++) {
                        iArr2[i3] = Math.round(TypedValue.applyDimension(i2, (float) iArr[i3], displayMetrics));
                    }
                }
                r8.i = r8.b(iArr2);
                if (!r8.h()) {
                    StringBuilder i4 = AbstractC2531fV.i("None of the preset sizes is valid: ");
                    i4.append(Arrays.toString(iArr));
                    throw new IllegalArgumentException(i4.toString());
                }
            } else {
                r8.j = false;
            }
            if (r8.g()) {
                r8.a();
            }
        }
    }

    public void i(int i2) {
        R8 r8 = this.h;
        if (!r8.i()) {
            return;
        }
        if (i2 == 0) {
            r8.d = 0;
            r8.g = -1.0f;
            r8.h = -1.0f;
            r8.f = -1.0f;
            r8.i = new int[0];
            r8.e = false;
        } else if (i2 == 1) {
            DisplayMetrics displayMetrics = r8.m.getResources().getDisplayMetrics();
            r8.j(TypedValue.applyDimension(2, 12.0f, displayMetrics), TypedValue.applyDimension(2, 112.0f, displayMetrics), 1.0f);
            if (r8.g()) {
                r8.a();
            }
        } else {
            throw new IllegalArgumentException(AbstractC2531fV.w("Unknown auto-size text type: ", i2));
        }
    }

    public final void j(Context context, C0514Ii1 ii1) {
        String m;
        this.i = ii1.j(2, this.i);
        int i2 = Build.VERSION.SDK_INT;
        boolean z = false;
        if (i2 >= 28) {
            int j2 = ii1.j(11, -1);
            this.j = j2;
            if (j2 != -1) {
                this.i = (this.i & 2) | 0;
            }
        }
        int i3 = 10;
        if (ii1.o(10) || ii1.o(12)) {
            this.k = null;
            if (ii1.o(12)) {
                i3 = 12;
            }
            int i4 = this.j;
            int i5 = this.i;
            if (!context.isRestricted()) {
                try {
                    Typeface i6 = ii1.i(i3, this.i, new L8(this, i4, i5, new WeakReference(this.f8461a)));
                    if (i6 != null) {
                        if (i2 < 28 || this.j == -1) {
                            this.k = i6;
                        } else {
                            this.k = Typeface.create(Typeface.create(i6, 0), this.j, (this.i & 2) != 0);
                        }
                    }
                    this.l = this.k == null;
                } catch (Resources.NotFoundException | UnsupportedOperationException unused) {
                }
            }
            if (this.k == null && (m = ii1.m(i3)) != null) {
                if (Build.VERSION.SDK_INT < 28 || this.j == -1) {
                    this.k = Typeface.create(m, this.i);
                    return;
                }
                Typeface create = Typeface.create(m, 0);
                int i7 = this.j;
                if ((this.i & 2) != 0) {
                    z = true;
                }
                this.k = Typeface.create(create, i7, z);
            }
        } else if (ii1.o(1)) {
            this.l = false;
            int j3 = ii1.j(1, 1);
            if (j3 == 1) {
                this.k = Typeface.SANS_SERIF;
            } else if (j3 == 2) {
                this.k = Typeface.SERIF;
            } else if (j3 == 3) {
                this.k = Typeface.MONOSPACE;
            }
        }
    }
}
