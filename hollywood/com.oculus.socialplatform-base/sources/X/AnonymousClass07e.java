package X;

import android.util.SparseArray;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.oculus.socialplatform.R;
import java.lang.ref.WeakReference;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.WeakHashMap;

/* renamed from: X.07e  reason: invalid class name */
public class AnonymousClass07e {
    public static final ArrayList<WeakReference<View>> A03 = new ArrayList<>();
    public SparseArray<WeakReference<View>> A00 = null;
    public WeakReference<KeyEvent> A01 = null;
    @Nullable
    public WeakHashMap<View, Boolean> A02 = null;

    @Nullable
    public static View A00(AnonymousClass07e r4, View view, KeyEvent keyEvent) {
        View A002;
        WeakHashMap<View, Boolean> weakHashMap = r4.A02;
        if (weakHashMap != null && weakHashMap.containsKey(view)) {
            if (view instanceof ViewGroup) {
                ViewGroup viewGroup = (ViewGroup) view;
                int childCount = viewGroup.getChildCount();
                do {
                    childCount--;
                    if (childCount >= 0) {
                        A002 = A00(r4, viewGroup.getChildAt(childCount), keyEvent);
                    }
                } while (A002 == null);
                return A002;
            }
            A01(view);
        }
        return null;
    }

    public static void A01(@NonNull View view) {
        int size;
        AbstractList abstractList = (AbstractList) view.getTag(R.id.tag_unhandled_key_listeners);
        if (abstractList != null && (size = abstractList.size() - 1) >= 0) {
            abstractList.get(size);
            throw new NullPointerException("onUnhandledKeyEvent");
        }
    }
}
