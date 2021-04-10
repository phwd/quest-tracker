package defpackage;

import android.os.Build;
import android.view.accessibility.AccessibilityNodeInfo;

/* renamed from: A  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class A {

    /* renamed from: a  reason: collision with root package name */
    public static final A f7647a = new A(16, null);
    public static final A b = new A(4096, null);
    public static final A c = new A(8192, null);
    public static final A d = new A(262144, null);
    public static final A e = new A(524288, null);
    public static final A f = new A(1048576, null);
    public static final A g = new A(AccessibilityNodeInfo.AccessibilityAction.ACTION_SCROLL_UP, 16908344, null, null, null);
    public static final A h = new A(AccessibilityNodeInfo.AccessibilityAction.ACTION_SCROLL_DOWN, 16908346, null, null, null);
    public final Object i;
    public final int j;
    public final Class k;
    public final AbstractC2620g0 l;

    static {
        AccessibilityNodeInfo.AccessibilityAction accessibilityAction = null;
        new A(1, null);
        new A(2, null);
        new A(4, null);
        new A(8, null);
        new A(32, null);
        new A(64, null);
        new A(128, null);
        new A(256, null, Z.class);
        new A(512, null, Z.class);
        new A(1024, null, AbstractC1586a0.class);
        new A(2048, null, AbstractC1586a0.class);
        new A(16384, null);
        new A(32768, null);
        new A(65536, null);
        new A(131072, null, AbstractC2278e0.class);
        new A(2097152, null, AbstractC2449f0.class);
        int i2 = Build.VERSION.SDK_INT;
        new A(AccessibilityNodeInfo.AccessibilityAction.ACTION_SHOW_ON_SCREEN, 16908342, null, null, null);
        new A(AccessibilityNodeInfo.AccessibilityAction.ACTION_SCROLL_TO_POSITION, 16908343, null, null, AbstractC1937c0.class);
        new A(AccessibilityNodeInfo.AccessibilityAction.ACTION_SCROLL_LEFT, 16908345, null, null, null);
        new A(AccessibilityNodeInfo.AccessibilityAction.ACTION_SCROLL_RIGHT, 16908347, null, null, null);
        new A(i2 >= 29 ? AccessibilityNodeInfo.AccessibilityAction.ACTION_PAGE_UP : null, 16908358, null, null, null);
        new A(i2 >= 29 ? AccessibilityNodeInfo.AccessibilityAction.ACTION_PAGE_DOWN : null, 16908359, null, null, null);
        new A(i2 >= 29 ? AccessibilityNodeInfo.AccessibilityAction.ACTION_PAGE_LEFT : null, 16908360, null, null, null);
        new A(i2 >= 29 ? AccessibilityNodeInfo.AccessibilityAction.ACTION_PAGE_RIGHT : null, 16908361, null, null, null);
        new A(AccessibilityNodeInfo.AccessibilityAction.ACTION_CONTEXT_CLICK, 16908348, null, null, null);
        new A(AccessibilityNodeInfo.AccessibilityAction.ACTION_SET_PROGRESS, 16908349, null, null, AbstractC2108d0.class);
        new A(i2 >= 26 ? AccessibilityNodeInfo.AccessibilityAction.ACTION_MOVE_WINDOW : null, 16908354, null, null, AbstractC1766b0.class);
        new A(i2 >= 28 ? AccessibilityNodeInfo.AccessibilityAction.ACTION_SHOW_TOOLTIP : null, 16908356, null, null, null);
        new A(i2 >= 28 ? AccessibilityNodeInfo.AccessibilityAction.ACTION_HIDE_TOOLTIP : null, 16908357, null, null, null);
        new A(i2 >= 30 ? AccessibilityNodeInfo.AccessibilityAction.ACTION_PRESS_AND_HOLD : null, 16908362, null, null, null);
        if (i2 >= 30) {
            accessibilityAction = AccessibilityNodeInfo.AccessibilityAction.ACTION_IME_ENTER;
        }
        new A(accessibilityAction, 16908372, null, null, null);
    }

    public A(int i2, CharSequence charSequence) {
        this(null, i2, charSequence, null, null);
    }

    public int a() {
        return ((AccessibilityNodeInfo.AccessibilityAction) this.i).getId();
    }

    public CharSequence b() {
        return ((AccessibilityNodeInfo.AccessibilityAction) this.i).getLabel();
    }

    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof A)) {
            return false;
        }
        A a2 = (A) obj;
        Object obj2 = this.i;
        if (obj2 == null) {
            if (a2.i != null) {
                return false;
            }
            return true;
        } else if (!obj2.equals(a2.i)) {
            return false;
        } else {
            return true;
        }
    }

    public int hashCode() {
        Object obj = this.i;
        if (obj != null) {
            return obj.hashCode();
        }
        return 0;
    }

    public A(int i2, CharSequence charSequence, Class cls) {
        this(null, i2, null, null, cls);
    }

    public A(Object obj, int i2, CharSequence charSequence, AbstractC2620g0 g0Var, Class cls) {
        this.j = i2;
        this.l = g0Var;
        if (obj == null) {
            this.i = new AccessibilityNodeInfo.AccessibilityAction(i2, charSequence);
        } else {
            this.i = obj;
        }
        this.k = cls;
    }
}
