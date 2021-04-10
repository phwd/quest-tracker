package defpackage;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;

/* renamed from: NA  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class NA extends ViewGroup.MarginLayoutParams {

    /* renamed from: a  reason: collision with root package name */
    public AbstractC4993tu1 f8531a;
    public boolean b = false;
    public int c = 0;
    public int d = 0;
    public int e = -1;
    public int f = -1;
    public int g = 0;
    public int h = 0;
    public int i;
    public int j;
    public View k;
    public View l;
    public boolean m;
    public boolean n;
    public boolean o;
    public boolean p;
    public final Rect q = new Rect();

    public NA(int i2, int i3) {
        super(i2, i3);
    }

    public boolean a(int i2) {
        if (i2 == 0) {
            return this.n;
        }
        if (i2 != 1) {
            return false;
        }
        return this.o;
    }

    public void b(int i2, boolean z) {
        if (i2 == 0) {
            this.n = z;
        } else if (i2 == 1) {
            this.o = z;
        }
    }

    public NA(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        AbstractC4993tu1 tu1;
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, FJ0.H);
        this.c = obtainStyledAttributes.getInteger(0, 0);
        this.f = obtainStyledAttributes.getResourceId(1, -1);
        this.d = obtainStyledAttributes.getInteger(2, 0);
        this.e = obtainStyledAttributes.getInteger(6, -1);
        this.g = obtainStyledAttributes.getInt(5, 0);
        this.h = obtainStyledAttributes.getInt(4, 0);
        boolean hasValue = obtainStyledAttributes.hasValue(3);
        this.b = hasValue;
        if (hasValue) {
            String string = obtainStyledAttributes.getString(3);
            String str = CoordinatorLayout.F;
            if (TextUtils.isEmpty(string)) {
                tu1 = null;
            } else {
                if (string.startsWith(".")) {
                    string = context.getPackageName() + string;
                } else if (string.indexOf(46) < 0) {
                    String str2 = CoordinatorLayout.F;
                    if (!TextUtils.isEmpty(str2)) {
                        string = str2 + '.' + string;
                    }
                }
                try {
                    ThreadLocal threadLocal = CoordinatorLayout.H;
                    Map map = (Map) threadLocal.get();
                    if (map == null) {
                        map = new HashMap();
                        threadLocal.set(map);
                    }
                    Constructor<?> constructor = (Constructor) map.get(string);
                    if (constructor == null) {
                        constructor = Class.forName(string, false, context.getClassLoader()).getConstructor(CoordinatorLayout.G);
                        constructor.setAccessible(true);
                        map.put(string, constructor);
                    }
                    tu1 = (AbstractC4993tu1) constructor.newInstance(context, attributeSet);
                } catch (Exception e2) {
                    throw new RuntimeException(AbstractC2531fV.f("Could not inflate Behavior subclass ", string), e2);
                }
            }
            this.f8531a = tu1;
        }
        obtainStyledAttributes.recycle();
    }

    public NA(NA na) {
        super((ViewGroup.MarginLayoutParams) na);
    }

    public NA(ViewGroup.MarginLayoutParams marginLayoutParams) {
        super(marginLayoutParams);
    }

    public NA(ViewGroup.LayoutParams layoutParams) {
        super(layoutParams);
    }
}
