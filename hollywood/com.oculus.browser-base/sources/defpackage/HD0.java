package defpackage;

import android.graphics.Bitmap;

/* renamed from: HD0  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class HD0 extends AbstractC0823Nl {

    /* renamed from: a  reason: collision with root package name */
    public int f8146a;
    public int b;
    public boolean c;
    public int d;
    public final /* synthetic */ ID0 e;

    public HD0(ID0 id0, int i, int i2, float f, boolean z, GD0 gd0) {
        this.e = id0;
        this.f8146a = i;
        this.b = i2;
        this.c = z;
    }

    public void b() {
        ID0.b(this.e, this.f8146a, this.b);
        HD0[][] hd0Arr = this.e.e;
        if (hd0Arr != null) {
            hd0Arr[this.f8146a][this.b] = null;
        }
    }

    @Override // org.chromium.base.Callback
    public void onResult(Object obj) {
        HD0[][] hd0Arr;
        boolean[][] zArr;
        Bitmap bitmap = (Bitmap) obj;
        if (bitmap == null) {
            b();
            return;
        }
        ID0 id0 = this.e;
        C2952hx[][] hxVarArr = id0.d;
        if (!(hxVarArr == null || (hd0Arr = id0.e) == null || (zArr = id0.f) == null)) {
            int i = this.f8146a;
            HD0[] hd0Arr2 = hd0Arr[i];
            int i2 = this.b;
            if (hd0Arr2[i2] != null && zArr[i][i2]) {
                hxVarArr[i][i2] = new C2952hx(bitmap, id0.k, this.c);
                ID0.a(this.e);
                ID0.b(this.e, this.f8146a, this.b);
                this.e.e[this.f8146a][this.b] = null;
                return;
            }
        }
        bitmap.recycle();
        ID0.a(this.e);
        ID0.b(this.e, this.f8146a, this.b);
        HD0[][] hd0Arr3 = this.e.e;
        if (hd0Arr3 != null) {
            hd0Arr3[this.f8146a][this.b] = null;
        }
    }
}
