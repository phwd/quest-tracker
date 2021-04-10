package defpackage;

/* renamed from: JU  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class JU extends NU {

    /* renamed from: a  reason: collision with root package name */
    public final boolean f8290a;

    public JU(int[] iArr) {
        int length = iArr.length;
        boolean z = false;
        int i = 0;
        while (true) {
            if (i >= length) {
                break;
            } else if (iArr[i] == 12) {
                z = true;
                break;
            } else {
                i++;
            }
        }
        this.f8290a = z;
    }

    @Override // defpackage.NU
    public int j() {
        return 16;
    }

    @Override // defpackage.NU
    public void k(float[] fArr, float[] fArr2, float[] fArr3, float[] fArr4) {
        NU.f(fArr2, fArr4);
        NU.g(fArr2, fArr4);
        NU.i(fArr2, fArr4);
        fArr[0] = fArr3[0];
        fArr[1] = fArr3[1];
        NU.a(fArr2, fArr3);
        float f = fArr4[106];
        float f2 = fArr4[107];
        float f3 = fArr4[98];
        float f4 = fArr4[101];
        fArr2[10] = Math.max(f, f3);
        fArr2[11] = Math.max(f2, f4);
        if (this.f8290a) {
            float f5 = fArr3[11];
            float f6 = 0.0f;
            fArr2[6] = f5 > 0.01f ? f5 : 0.0f;
            float f7 = -f5;
            if (f7 > 0.01f) {
                f6 = f7;
            }
            fArr2[7] = f6;
            NU.d(fArr, fArr3);
            return;
        }
        float f8 = fArr4[104];
        float f9 = fArr4[105];
        fArr2[6] = f8;
        fArr2[7] = f9;
        NU.b(fArr, fArr3);
    }
}
