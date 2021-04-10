package defpackage;

import android.util.Pair;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import org.chromium.base.Callback;

/* renamed from: Gv  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class AbstractC0417Gv {
    public static void a(Collection collection, Callback callback) {
        for (Object obj : collection) {
            callback.onResult(obj);
        }
    }

    public static int[] b(List list) {
        int[] iArr = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            iArr[i] = ((Integer) list.get(i)).intValue();
        }
        return iArr;
    }

    @SafeVarargs
    public static HashMap c(Pair... pairArr) {
        HashMap hashMap = new HashMap();
        for (Pair pair : pairArr) {
            hashMap.put(pair.first, pair.second);
        }
        return hashMap;
    }

    @SafeVarargs
    public static HashSet d(Object... objArr) {
        HashSet hashSet = new HashSet(objArr.length);
        Collections.addAll(hashSet, objArr);
        return hashSet;
    }

    public static List e(Collection collection) {
        ArrayList arrayList = new ArrayList(collection.size());
        Iterator it = collection.iterator();
        while (it.hasNext()) {
            Object obj = ((WeakReference) it.next()).get();
            if (obj == null) {
                it.remove();
            } else {
                arrayList.add(obj);
            }
        }
        return arrayList;
    }
}
