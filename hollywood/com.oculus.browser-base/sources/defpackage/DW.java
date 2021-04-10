package defpackage;

import android.util.Pair;
import java.lang.reflect.Array;
import java.util.ArrayList;

/* renamed from: DW  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class DW extends ArrayList {
    public final Class F;
    public final Class G;

    public DW(Class cls, Class cls2) {
        this.F = cls;
        this.G = cls2;
    }

    public KW a() {
        int size = size();
        Object[] objArr = (Object[]) Array.newInstance(this.F, size);
        Object[] objArr2 = (Object[]) Array.newInstance(this.G, size);
        for (int i = 0; i < size; i++) {
            objArr[i] = ((Pair) get(i)).first;
            objArr2[i] = ((Pair) get(i)).second;
        }
        return new KW(objArr, objArr2);
    }
}
