package defpackage;

import android.graphics.Rect;
import android.util.Size;
import java.lang.reflect.Array;
import java.util.HashSet;
import java.util.Set;
import org.chromium.base.UnguessableToken;

/* renamed from: ID0  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class ID0 {

    /* renamed from: a  reason: collision with root package name */
    public final UnguessableToken f8208a;
    public final Size b;
    public float c;
    public C2952hx[][] d;
    public HD0[][] e;
    public boolean[][] f;
    public boolean[][] g;
    public final AbstractC5900zD0 h;
    public final KD0 i;
    public Set j = new HashSet();
    public final BS0 k;

    public ID0(UnguessableToken unguessableToken, int i2, int i3, float f2, Size size, AbstractC5900zD0 zd0, KD0 kd0, BS0 bs0) {
        this.f8208a = unguessableToken;
        this.b = new Size(i2, i3);
        this.c = f2;
        this.h = zd0;
        this.i = kd0;
        this.k = bs0;
        int ceil = (int) Math.ceil((double) ((((float) size.getHeight()) * f2) / ((float) i3)));
        int ceil2 = (int) Math.ceil((double) ((((float) size.getWidth()) * f2) / ((float) i2)));
        int[] iArr = new int[2];
        iArr[1] = ceil2;
        iArr[0] = ceil;
        this.d = (C2952hx[][]) Array.newInstance(C2952hx.class, iArr);
        int[] iArr2 = new int[2];
        iArr2[1] = ceil2;
        iArr2[0] = ceil;
        this.e = (HD0[][]) Array.newInstance(HD0.class, iArr2);
        int[] iArr3 = new int[2];
        iArr3[1] = ceil2;
        iArr3[0] = ceil;
        this.f = (boolean[][]) Array.newInstance(boolean.class, iArr3);
        int[] iArr4 = new int[2];
        iArr4[1] = ceil2;
        iArr4[0] = ceil;
        this.g = (boolean[][]) Array.newInstance(boolean.class, iArr4);
    }

    public static void a(ID0 id0) {
        if (!(id0.d == null || id0.f == null)) {
            for (int i2 = 0; i2 < id0.d.length; i2++) {
                int i3 = 0;
                while (true) {
                    C2952hx[][] hxVarArr = id0.d;
                    if (i3 >= hxVarArr[i2].length) {
                        break;
                    }
                    C2952hx hxVar = hxVarArr[i2][i3];
                    if (!id0.f[i2][i3] && hxVar != null) {
                        hxVar.b();
                        id0.d[i2][i3] = null;
                    }
                    i3++;
                }
            }
        }
    }

    public static void b(ID0 id0, int i2, int i3) {
        C2952hx[][] hxVarArr = id0.d;
        if (hxVarArr != null) {
            Set set = id0.j;
            if (set != null) {
                set.remove(Integer.valueOf((i2 * hxVarArr.length) + i3));
                if (id0.j.isEmpty()) {
                    id0.j = null;
                } else {
                    return;
                }
            }
            id0.i.b(id0);
        }
    }

    public void c() {
        this.f = null;
        this.e = null;
        for (int i2 = 0; i2 < this.d.length; i2++) {
            int i3 = 0;
            while (true) {
                C2952hx[][] hxVarArr = this.d;
                if (i3 >= hxVarArr[i2].length) {
                    break;
                }
                if (hxVarArr[i2][i3] != null) {
                    hxVarArr[i2][i3].b();
                }
                i3++;
            }
        }
        this.d = null;
    }

    public final boolean d(int i2, int i3) {
        boolean[][] zArr = this.f;
        if (zArr == null) {
            return false;
        }
        zArr[i2][i3] = true;
        HD0[][] hd0Arr = this.e;
        if (hd0Arr == null || hd0Arr[i2][i3] == null) {
            C2952hx[][] hxVarArr = this.d;
            if (hxVarArr == null || hd0Arr == null || hxVarArr[i2][i3] != null || hd0Arr[i2][i3] != null) {
                return false;
            }
            int height = this.b.getHeight() * i2;
            int width = this.b.getWidth() * i3;
            HD0 hd0 = new HD0(this, i2, i3, this.c, this.g[i2][i3], null);
            this.e[i2][i3] = hd0;
            int a2 = this.h.a(this.f8208a, new Rect(width, height, this.b.getWidth() + width, this.b.getHeight() + height), this.c, hd0, new FD0(hd0));
            HD0[][] hd0Arr2 = this.e;
            if (hd0Arr2[i2][i3] != null) {
                hd0Arr2[i2][i3].d = a2;
            }
            return true;
        }
        hd0Arr[i2][i3].c = this.g[i2][i3];
        return false;
    }
}
