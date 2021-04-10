package X;

import android.os.Build;
import android.util.SparseArray;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewParent;
import androidx.annotation.UiThread;
import com.oculus.userserver2.R;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.WeakHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/* renamed from: X.8Q  reason: invalid class name */
public final class AnonymousClass8Q {
    public static AnonymousClass8D A00 = new AnonymousClass8D();
    public static final AtomicInteger A01 = new AtomicInteger(1);

    @UiThread
    public static boolean A00(View view, KeyEvent keyEvent) {
        if (Build.VERSION.SDK_INT >= 28) {
            return false;
        }
        AnonymousClass8P r6 = (AnonymousClass8P) view.getTag(R.id.tag_unhandled_key_event_manager);
        if (r6 == null) {
            r6 = new AnonymousClass8P();
            view.setTag(R.id.tag_unhandled_key_event_manager, r6);
        }
        if (keyEvent.getAction() == 0) {
            WeakHashMap<View, Boolean> weakHashMap = r6.A02;
            if (weakHashMap != null) {
                weakHashMap.clear();
            }
            ArrayList<WeakReference<View>> arrayList = AnonymousClass8P.A03;
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
        View A002 = AnonymousClass8P.A00(r6, view, keyEvent);
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
    public static boolean A01(android.view.View r3, android.view.KeyEvent r4) {
        /*
        // Method dump skipped, instructions count: 134
        */
        throw new UnsupportedOperationException("Method not decompiled: X.AnonymousClass8Q.A01(android.view.View, android.view.KeyEvent):boolean");
    }
}
