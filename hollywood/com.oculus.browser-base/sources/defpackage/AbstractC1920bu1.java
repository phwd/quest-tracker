package defpackage;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseArray;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewParent;
import android.view.WindowInsets;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityManager;
import com.oculus.browser.R;
import java.lang.ref.WeakReference;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.WeakHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/* renamed from: bu1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class AbstractC1920bu1 {

    /* renamed from: a  reason: collision with root package name */
    public static final AtomicInteger f9571a = new AtomicInteger(1);
    public static WeakHashMap b = null;
    public static Field c;
    public static boolean d = false;

    static {
        new WeakHashMap();
    }

    public static Zu1 a(View view) {
        if (b == null) {
            b = new WeakHashMap();
        }
        Zu1 zu1 = (Zu1) b.get(view);
        if (zu1 != null) {
            return zu1;
        }
        Zu1 zu12 = new Zu1(view);
        b.put(view, zu12);
        return zu12;
    }

    public static C3985nz1 b(View view, C3985nz1 nz1) {
        WindowInsets g = nz1.g();
        if (g != null) {
            WindowInsets dispatchApplyWindowInsets = view.dispatchApplyWindowInsets(g);
            if (!dispatchApplyWindowInsets.equals(g)) {
                return C3985nz1.i(dispatchApplyWindowInsets, view);
            }
        }
        return nz1;
    }

    public static boolean c(View view, KeyEvent keyEvent) {
        if (Build.VERSION.SDK_INT >= 28) {
            return false;
        }
        ArrayList arrayList = C1749au1.f9498a;
        C1749au1 au1 = (C1749au1) view.getTag(R.id.tag_unhandled_key_event_manager);
        if (au1 == null) {
            au1 = new C1749au1();
            view.setTag(R.id.tag_unhandled_key_event_manager, au1);
        }
        if (keyEvent.getAction() == 0) {
            WeakHashMap weakHashMap = au1.b;
            if (weakHashMap != null) {
                weakHashMap.clear();
            }
            ArrayList arrayList2 = C1749au1.f9498a;
            if (!arrayList2.isEmpty()) {
                synchronized (arrayList2) {
                    if (au1.b == null) {
                        au1.b = new WeakHashMap();
                    }
                    int size = arrayList2.size();
                    while (true) {
                        size--;
                        if (size < 0) {
                            break;
                        }
                        ArrayList arrayList3 = C1749au1.f9498a;
                        View view2 = (View) ((WeakReference) arrayList3.get(size)).get();
                        if (view2 == null) {
                            arrayList3.remove(size);
                        } else {
                            au1.b.put(view2, Boolean.TRUE);
                            for (ViewParent parent = view2.getParent(); parent instanceof View; parent = parent.getParent()) {
                                au1.b.put((View) parent, Boolean.TRUE);
                            }
                        }
                    }
                }
            }
        }
        View a2 = au1.a(view, keyEvent);
        if (keyEvent.getAction() == 0) {
            int keyCode = keyEvent.getKeyCode();
            if (a2 != null && !KeyEvent.isModifierKey(keyCode)) {
                if (au1.c == null) {
                    au1.c = new SparseArray();
                }
                au1.c.put(keyCode, new WeakReference(a2));
            }
        }
        if (a2 != null) {
            return true;
        }
        return false;
    }

    public static C5349w d(View view) {
        View.AccessibilityDelegate e = e(view);
        if (e == null) {
            return null;
        }
        if (e instanceof C5179v) {
            return ((C5179v) e).f11453a;
        }
        return new C5349w(e);
    }

    public static View.AccessibilityDelegate e(View view) {
        if (Build.VERSION.SDK_INT >= 29) {
            return view.getAccessibilityDelegate();
        }
        if (d) {
            return null;
        }
        if (c == null) {
            try {
                Field declaredField = View.class.getDeclaredField("mAccessibilityDelegate");
                c = declaredField;
                declaredField.setAccessible(true);
            } catch (Throwable unused) {
                d = true;
                return null;
            }
        }
        try {
            Object obj = c.get(view);
            if (obj instanceof View.AccessibilityDelegate) {
                return (View.AccessibilityDelegate) obj;
            }
            return null;
        } catch (Throwable unused2) {
            d = true;
            return null;
        }
    }

    public static CharSequence f(View view) {
        return (CharSequence) new Vt1(R.id.tag_accessibility_pane_title, CharSequence.class, 8, 28).c(view);
    }

    public static C3985nz1 g(View view) {
        WindowInsets rootWindowInsets = view.getRootWindowInsets();
        if (rootWindowInsets == null) {
            return null;
        }
        C3985nz1 h = C3985nz1.h(rootWindowInsets);
        h.b.k(h);
        h.b.d(view.getRootView());
        return h;
    }

    public static void h(View view, int i) {
        AccessibilityManager accessibilityManager = (AccessibilityManager) view.getContext().getSystemService("accessibility");
        if (accessibilityManager.isEnabled()) {
            boolean z = f(view) != null && view.getVisibility() == 0;
            int i2 = 32;
            if (view.getAccessibilityLiveRegion() != 0 || z) {
                AccessibilityEvent obtain = AccessibilityEvent.obtain();
                if (!z) {
                    i2 = 2048;
                }
                obtain.setEventType(i2);
                obtain.setContentChangeTypes(i);
                if (z) {
                    obtain.getText().add(f(view));
                    if (view.getImportantForAccessibility() == 0) {
                        view.setImportantForAccessibility(1);
                    }
                    ViewParent parent = view.getParent();
                    while (true) {
                        if (!(parent instanceof View)) {
                            break;
                        } else if (((View) parent).getImportantForAccessibility() == 4) {
                            view.setImportantForAccessibility(2);
                            break;
                        } else {
                            parent = parent.getParent();
                        }
                    }
                }
                view.sendAccessibilityEventUnchecked(obtain);
            } else if (i == 32) {
                AccessibilityEvent obtain2 = AccessibilityEvent.obtain();
                view.onInitializeAccessibilityEvent(obtain2);
                obtain2.setEventType(32);
                obtain2.setContentChangeTypes(i);
                obtain2.setSource(view);
                view.onPopulateAccessibilityEvent(obtain2);
                obtain2.getText().add(f(view));
                accessibilityManager.sendAccessibilityEvent(obtain2);
            } else if (view.getParent() != null) {
                try {
                    view.getParent().notifySubtreeAccessibilityStateChanged(view, view, i);
                } catch (AbstractMethodError e) {
                    Log.e("ViewCompat", view.getParent().getClass().getSimpleName() + " does not fully implement ViewParent", e);
                }
            }
        }
    }

    public static C3985nz1 i(View view, C3985nz1 nz1) {
        WindowInsets g = nz1.g();
        if (g != null) {
            WindowInsets onApplyWindowInsets = view.onApplyWindowInsets(g);
            if (!onApplyWindowInsets.equals(g)) {
                return C3985nz1.i(onApplyWindowInsets, view);
            }
        }
        return nz1;
    }

    public static void j(View view, int i) {
        k(i, view);
        h(view, 0);
    }

    public static void k(int i, View view) {
        ArrayList arrayList = (ArrayList) view.getTag(R.id.tag_accessibility_actions);
        if (arrayList == null) {
            arrayList = new ArrayList();
            view.setTag(R.id.tag_accessibility_actions, arrayList);
        }
        for (int i2 = 0; i2 < arrayList.size(); i2++) {
            if (((A) arrayList.get(i2)).a() == i) {
                arrayList.remove(i2);
                return;
            }
        }
    }

    public static void l(View view, A a2, CharSequence charSequence, AbstractC2620g0 g0Var) {
        if (g0Var == null) {
            k(a2.a(), view);
            h(view, 0);
            return;
        }
        A a3 = new A(null, a2.j, null, g0Var, a2.k);
        C5349w d2 = d(view);
        if (d2 == null) {
            d2 = new C5349w();
        }
        n(view, d2);
        k(a3.a(), view);
        ArrayList arrayList = (ArrayList) view.getTag(R.id.tag_accessibility_actions);
        if (arrayList == null) {
            arrayList = new ArrayList();
            view.setTag(R.id.tag_accessibility_actions, arrayList);
        }
        arrayList.add(a3);
        h(view, 0);
    }

    public static void m(View view, Context context, int[] iArr, AttributeSet attributeSet, TypedArray typedArray, int i, int i2) {
        if (Build.VERSION.SDK_INT >= 29) {
            view.saveAttributeDataForStyleable(context, iArr, attributeSet, typedArray, i, i2);
        }
    }

    public static void n(View view, C5349w wVar) {
        View.AccessibilityDelegate accessibilityDelegate;
        if (wVar == null && (e(view) instanceof C5179v)) {
            wVar = new C5349w();
        }
        if (wVar == null) {
            accessibilityDelegate = null;
        } else {
            accessibilityDelegate = wVar.c;
        }
        view.setAccessibilityDelegate(accessibilityDelegate);
    }

    public static void o(View view, AbstractC0290Es0 es0) {
        if (es0 == null) {
            view.setOnApplyWindowInsetsListener(null);
        } else {
            view.setOnApplyWindowInsetsListener(new Tt1(es0));
        }
    }
}
