package X;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewParent;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.UiThread;
import com.oculus.socialplatform.R;
import java.lang.ref.WeakReference;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.WeakHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/* renamed from: X.07f  reason: invalid class name */
public final class AnonymousClass07f {
    public static Field A00;
    public static boolean A01;
    public static AnonymousClass07S A02 = new AnonymousClass07S();
    public static WeakHashMap<View, C003007j> A03;
    public static final AtomicInteger A04 = new AtomicInteger(1);

    @SuppressLint({"InlinedApi"})
    public static int A00(@NonNull View view) {
        if (Build.VERSION.SDK_INT >= 26) {
            return view.getImportantForAutofill();
        }
        return 0;
    }

    @Nullable
    public static View.AccessibilityDelegate A01(@NonNull View view) {
        if (Build.VERSION.SDK_INT >= 29) {
            return view.getAccessibilityDelegate();
        }
        if (A01) {
            return null;
        }
        if (A00 == null) {
            try {
                Field declaredField = View.class.getDeclaredField("mAccessibilityDelegate");
                A00 = declaredField;
                declaredField.setAccessible(true);
            } catch (Throwable unused) {
                A01 = true;
                return null;
            }
        }
        Object obj = A00.get(view);
        if (obj instanceof View.AccessibilityDelegate) {
            return (View.AccessibilityDelegate) obj;
        }
        return null;
    }

    @NonNull
    public static C003007j A02(@NonNull View view) {
        WeakHashMap<View, C003007j> weakHashMap = A03;
        if (weakHashMap == null) {
            weakHashMap = new WeakHashMap<>();
            A03 = weakHashMap;
        }
        C003007j r1 = weakHashMap.get(view);
        if (r1 != null) {
            return r1;
        }
        C003007j r12 = new C003007j(view);
        A03.put(view, r12);
        return r12;
    }

    public static void A03(@NonNull View view) {
        if (Build.VERSION.SDK_INT >= 26) {
            view.setImportantForAutofill(8);
        }
    }

    public static void A04(@NonNull View view, @NonNull @SuppressLint({"ContextFirst"}) Context context, @NonNull int[] iArr, @Nullable AttributeSet attributeSet, @NonNull TypedArray typedArray, int i) {
        if (Build.VERSION.SDK_INT >= 29) {
            AnonymousClass07W.A00(view, context, iArr, attributeSet, typedArray, i);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:5:0x0018, code lost:
        if (r3.getBackgroundTintMode() != null) goto L_0x001a;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void A05(@androidx.annotation.NonNull android.view.View r3, android.content.res.ColorStateList r4) {
        /*
            r1 = 21
            r3.setBackgroundTintList(r4)
            int r0 = android.os.Build.VERSION.SDK_INT
            if (r0 != r1) goto L_0x002f
            android.graphics.drawable.Drawable r2 = r3.getBackground()
            android.content.res.ColorStateList r0 = r3.getBackgroundTintList()
            if (r0 != 0) goto L_0x001a
            android.graphics.PorterDuff$Mode r1 = r3.getBackgroundTintMode()
            r0 = 0
            if (r1 == 0) goto L_0x001b
        L_0x001a:
            r0 = 1
        L_0x001b:
            if (r2 == 0) goto L_0x002f
            if (r0 == 0) goto L_0x002f
            boolean r0 = r2.isStateful()
            if (r0 == 0) goto L_0x002c
            int[] r0 = r3.getDrawableState()
            r2.setState(r0)
        L_0x002c:
            r3.setBackground(r2)
        L_0x002f:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: X.AnonymousClass07f.A05(android.view.View, android.content.res.ColorStateList):void");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:5:0x0018, code lost:
        if (r3.getBackgroundTintMode() != null) goto L_0x001a;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void A06(@androidx.annotation.NonNull android.view.View r3, android.graphics.PorterDuff.Mode r4) {
        /*
            r1 = 21
            r3.setBackgroundTintMode(r4)
            int r0 = android.os.Build.VERSION.SDK_INT
            if (r0 != r1) goto L_0x002f
            android.graphics.drawable.Drawable r2 = r3.getBackground()
            android.content.res.ColorStateList r0 = r3.getBackgroundTintList()
            if (r0 != 0) goto L_0x001a
            android.graphics.PorterDuff$Mode r1 = r3.getBackgroundTintMode()
            r0 = 0
            if (r1 == 0) goto L_0x001b
        L_0x001a:
            r0 = 1
        L_0x001b:
            if (r2 == 0) goto L_0x002f
            if (r0 == 0) goto L_0x002f
            boolean r0 = r2.isStateful()
            if (r0 == 0) goto L_0x002c
            int[] r0 = r3.getDrawableState()
            r2.setState(r0)
        L_0x002c:
            r3.setBackground(r2)
        L_0x002f:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: X.AnonymousClass07f.A06(android.view.View, android.graphics.PorterDuff$Mode):void");
    }

    public static void A07(@NonNull View view, AnonymousClass06v r2) {
        View.AccessibilityDelegate accessibilityDelegate;
        if (r2 == null) {
            if (A01(view) instanceof C002706u) {
                r2 = new AnonymousClass06v();
            } else {
                accessibilityDelegate = null;
                view.setAccessibilityDelegate(accessibilityDelegate);
            }
        }
        accessibilityDelegate = r2.A00;
        view.setAccessibilityDelegate(accessibilityDelegate);
    }

    @UiThread
    public static boolean A08(View view, KeyEvent keyEvent) {
        if (Build.VERSION.SDK_INT >= 28) {
            return false;
        }
        AnonymousClass07e r6 = (AnonymousClass07e) view.getTag(R.id.tag_unhandled_key_event_manager);
        if (r6 == null) {
            r6 = new AnonymousClass07e();
            view.setTag(R.id.tag_unhandled_key_event_manager, r6);
        }
        if (keyEvent.getAction() == 0) {
            WeakHashMap<View, Boolean> weakHashMap = r6.A02;
            if (weakHashMap != null) {
                weakHashMap.clear();
            }
            ArrayList<WeakReference<View>> arrayList = AnonymousClass07e.A03;
            if (!arrayList.isEmpty()) {
                synchronized (arrayList) {
                    if (r6.A02 == null) {
                        r6.A02 = new WeakHashMap<>();
                    }
                    int size = arrayList.size();
                    while (true) {
                        size--;
                        if (size < 0) {
                            break;
                        }
                        View view2 = arrayList.get(size).get();
                        if (view2 == null) {
                            arrayList.remove(size);
                        } else {
                            r6.A02.put(view2, Boolean.TRUE);
                            for (ViewParent parent = view2.getParent(); parent instanceof View; parent = parent.getParent()) {
                                r6.A02.put((View) parent, Boolean.TRUE);
                            }
                        }
                    }
                }
            }
        }
        View A002 = AnonymousClass07e.A00(r6, view, keyEvent);
        if (keyEvent.getAction() == 0) {
            int keyCode = keyEvent.getKeyCode();
            if (A002 == null) {
                return false;
            }
            if (KeyEvent.isModifierKey(keyCode)) {
                return true;
            }
            SparseArray<WeakReference<View>> sparseArray = r6.A00;
            if (sparseArray == null) {
                sparseArray = new SparseArray<>();
                r6.A00 = sparseArray;
            }
            sparseArray.put(keyCode, new WeakReference<>(A002));
            return true;
        } else if (A002 != null) {
            return true;
        } else {
            return false;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0051, code lost:
        if (r0 == null) goto L_0x0053;
     */
    @androidx.annotation.UiThread
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean A09(android.view.View r2, android.view.KeyEvent r3) {
        /*
        // Method dump skipped, instructions count: 114
        */
        throw new UnsupportedOperationException("Method not decompiled: X.AnonymousClass07f.A09(android.view.View, android.view.KeyEvent):boolean");
    }
}
