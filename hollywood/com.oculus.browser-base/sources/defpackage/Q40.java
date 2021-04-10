package defpackage;

import android.os.Build;
import android.util.SparseArray;
import android.view.KeyEvent;
import android.view.View;
import com.oculus.browser.R;
import java.lang.ref.WeakReference;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

/* renamed from: Q40  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class Q40 {

    /* renamed from: a  reason: collision with root package name */
    public static boolean f8737a = false;
    public static Method b = null;
    public static boolean c = false;
    public static Field d;

    public static boolean a(View view, KeyEvent keyEvent) {
        int indexOfKey;
        AtomicInteger atomicInteger = AbstractC1920bu1.f9571a;
        if (Build.VERSION.SDK_INT < 28) {
            ArrayList arrayList = C1749au1.f9498a;
            C1749au1 au1 = (C1749au1) view.getTag(R.id.tag_unhandled_key_event_manager);
            if (au1 == null) {
                au1 = new C1749au1();
                view.setTag(R.id.tag_unhandled_key_event_manager, au1);
            }
            WeakReference weakReference = au1.d;
            if (weakReference == null || weakReference.get() != keyEvent) {
                au1.d = new WeakReference(keyEvent);
                WeakReference weakReference2 = null;
                if (au1.c == null) {
                    au1.c = new SparseArray();
                }
                SparseArray sparseArray = au1.c;
                if (keyEvent.getAction() == 1 && (indexOfKey = sparseArray.indexOfKey(keyEvent.getKeyCode())) >= 0) {
                    weakReference2 = (WeakReference) sparseArray.valueAt(indexOfKey);
                    sparseArray.removeAt(indexOfKey);
                }
                if (weakReference2 == null) {
                    weakReference2 = (WeakReference) sparseArray.get(keyEvent.getKeyCode());
                }
                if (weakReference2 != null) {
                    View view2 = (View) weakReference2.get();
                    if (view2 == null || !view2.isAttachedToWindow()) {
                        return true;
                    }
                    au1.b(view2, keyEvent);
                    return true;
                }
            }
        }
        return false;
    }

    /* JADX WARNING: Removed duplicated region for block: B:53:0x00bb  */
    /* JADX WARNING: Removed duplicated region for block: B:70:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean b(defpackage.P40 r7, android.view.View r8, android.view.Window.Callback r9, android.view.KeyEvent r10) {
        /*
        // Method dump skipped, instructions count: 225
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.Q40.b(P40, android.view.View, android.view.Window$Callback, android.view.KeyEvent):boolean");
    }
}
