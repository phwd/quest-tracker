package defpackage;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.TypedValue;

/* renamed from: Ii1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C0514Ii1 {

    /* renamed from: a  reason: collision with root package name */
    public final Context f8244a;
    public final TypedArray b;
    public TypedValue c;

    public C0514Ii1(Context context, TypedArray typedArray) {
        this.f8244a = context;
        this.b = typedArray;
    }

    public static C0514Ii1 p(Context context, AttributeSet attributeSet, int[] iArr) {
        return new C0514Ii1(context, context.obtainStyledAttributes(attributeSet, iArr));
    }

    public static C0514Ii1 q(Context context, AttributeSet attributeSet, int[] iArr, int i, int i2) {
        return new C0514Ii1(context, context.obtainStyledAttributes(attributeSet, iArr, i, i2));
    }

    public boolean a(int i, boolean z) {
        return this.b.getBoolean(i, z);
    }

    public int b(int i, int i2) {
        return this.b.getColor(i, i2);
    }

    public ColorStateList c(int i) {
        int resourceId;
        if (this.b.hasValue(i) && (resourceId = this.b.getResourceId(i, 0)) != 0) {
            Context context = this.f8244a;
            ThreadLocal threadLocal = AbstractC5544x8.f11592a;
            ColorStateList colorStateList = context.getColorStateList(resourceId);
            if (colorStateList != null) {
                return colorStateList;
            }
        }
        return this.b.getColorStateList(i);
    }

    public float d(int i, float f) {
        return this.b.getDimension(i, f);
    }

    public int e(int i, int i2) {
        return this.b.getDimensionPixelOffset(i, i2);
    }

    public int f(int i, int i2) {
        return this.b.getDimensionPixelSize(i, i2);
    }

    public Drawable g(int i) {
        int resourceId;
        if (!this.b.hasValue(i) || (resourceId = this.b.getResourceId(i, 0)) == 0) {
            return this.b.getDrawable(i);
        }
        return AbstractC5544x8.a(this.f8244a, resourceId);
    }

    public Drawable h(int i) {
        int resourceId;
        Drawable f;
        if (!this.b.hasValue(i) || (resourceId = this.b.getResourceId(i, 0)) == 0) {
            return null;
        }
        C3840n8 a2 = C3840n8.a();
        Context context = this.f8244a;
        synchronized (a2) {
            f = a2.c.f(context, resourceId, true);
        }
        return f;
    }

    public Typeface i(int i, int i2, AbstractC5414wM0 wm0) {
        int resourceId = this.b.getResourceId(i, 0);
        if (resourceId == 0) {
            return null;
        }
        if (this.c == null) {
            this.c = new TypedValue();
        }
        Context context = this.f8244a;
        TypedValue typedValue = this.c;
        if (context.isRestricted()) {
            return null;
        }
        return AbstractC5754yM0.a(context, resourceId, typedValue, i2, wm0, null, true, false);
    }

    public int j(int i, int i2) {
        return this.b.getInt(i, i2);
    }

    public int k(int i, int i2) {
        return this.b.getLayoutDimension(i, i2);
    }

    public int l(int i, int i2) {
        return this.b.getResourceId(i, i2);
    }

    public String m(int i) {
        return this.b.getString(i);
    }

    public CharSequence n(int i) {
        return this.b.getText(i);
    }

    public boolean o(int i) {
        return this.b.hasValue(i);
    }
}
