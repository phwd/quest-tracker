package defpackage;

import android.util.SparseArray;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import com.oculus.browser.R;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.WeakHashMap;

/* renamed from: au1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C1749au1 {

    /* renamed from: a  reason: collision with root package name */
    public static final ArrayList f9498a = new ArrayList();
    public WeakHashMap b = null;
    public SparseArray c = null;
    public WeakReference d = null;

    public final View a(View view, KeyEvent keyEvent) {
        WeakHashMap weakHashMap = this.b;
        if (weakHashMap != null && weakHashMap.containsKey(view)) {
            if (view instanceof ViewGroup) {
                ViewGroup viewGroup = (ViewGroup) view;
                for (int childCount = viewGroup.getChildCount() - 1; childCount >= 0; childCount--) {
                    View a2 = a(viewGroup.getChildAt(childCount), keyEvent);
                    if (a2 != null) {
                        return a2;
                    }
                }
            }
            if (b(view, keyEvent)) {
                return view;
            }
        }
        return null;
    }

    public final boolean b(View view, KeyEvent keyEvent) {
        ArrayList arrayList = (ArrayList) view.getTag(R.id.tag_unhandled_key_listeners);
        if (arrayList == null) {
            return false;
        }
        for (int size = arrayList.size() - 1; size >= 0; size--) {
            if (((Zt1) arrayList.get(size)).a(view, keyEvent)) {
                return true;
            }
        }
        return false;
    }
}
