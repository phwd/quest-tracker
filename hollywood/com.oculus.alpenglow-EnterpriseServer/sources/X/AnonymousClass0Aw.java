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
import com.oculus.alpenglow.R;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.WeakHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/* renamed from: X.0Aw  reason: invalid class name */
public final class AnonymousClass0Aw {
    public static AnonymousClass0Aj A00 = new AnonymousClass0Aj();
    public static WeakHashMap<View, AnonymousClass0B0> A01;
    public static final AtomicInteger A02 = new AtomicInteger(1);

    @NonNull
    public static AnonymousClass0B0 A00(@NonNull View view) {
        WeakHashMap<View, AnonymousClass0B0> weakHashMap = A01;
        if (weakHashMap == null) {
            weakHashMap = new WeakHashMap<>();
            A01 = weakHashMap;
        }
        AnonymousClass0B0 r1 = weakHashMap.get(view);
        if (r1 != null) {
            return r1;
        }
        AnonymousClass0B0 r12 = new AnonymousClass0B0(view);
        A01.put(view, r12);
        return r12;
    }

    public static void A01(@NonNull View view, @NonNull @SuppressLint({"ContextFirst"}) Context context, @NonNull int[] iArr, @Nullable AttributeSet attributeSet, @NonNull TypedArray typedArray, int i) {
        if (Build.VERSION.SDK_INT >= 29) {
            AnonymousClass0An.A00(view, context, iArr, attributeSet, typedArray, i);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:5:0x0018, code lost:
        if (r3.getBackgroundTintMode() != null) goto L_0x001a;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void A02(@androidx.annotation.NonNull android.view.View r3, android.content.res.ColorStateList r4) {
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
        throw new UnsupportedOperationException("Method not decompiled: X.AnonymousClass0Aw.A02(android.view.View, android.content.res.ColorStateList):void");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:5:0x0018, code lost:
        if (r3.getBackgroundTintMode() != null) goto L_0x001a;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void A03(@androidx.annotation.NonNull android.view.View r3, android.graphics.PorterDuff.Mode r4) {
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
        throw new UnsupportedOperationException("Method not decompiled: X.AnonymousClass0Aw.A03(android.view.View, android.graphics.PorterDuff$Mode):void");
    }

    @UiThread
    public static boolean A04(View view, KeyEvent keyEvent) {
        if (Build.VERSION.SDK_INT >= 28) {
            return false;
        }
        AnonymousClass0Av r6 = (AnonymousClass0Av) view.getTag(R.id.tag_unhandled_key_event_manager);
        if (r6 == null) {
            r6 = new AnonymousClass0Av();
            view.setTag(R.id.tag_unhandled_key_event_manager, r6);
        }
        if (keyEvent.getAction() == 0) {
            WeakHashMap<View, Boolean> weakHashMap = r6.A02;
            if (weakHashMap != null) {
                weakHashMap.clear();
            }
            ArrayList<WeakReference<View>> arrayList = AnonymousClass0Av.A03;
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
        View A002 = AnonymousClass0Av.A00(r6, view, keyEvent);
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
    public static boolean A05(android.view.View r3, android.view.KeyEvent r4) {
        /*
        // Method dump skipped, instructions count: 134
        */
        throw new UnsupportedOperationException("Method not decompiled: X.AnonymousClass0Aw.A05(android.view.View, android.view.KeyEvent):boolean");
    }
}
