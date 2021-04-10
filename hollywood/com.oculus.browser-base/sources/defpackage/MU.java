package defpackage;

/* renamed from: MU  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class MU extends NU {

    /* renamed from: a  reason: collision with root package name */
    public boolean f8477a;
    public boolean b;

    public MU(DU du) {
    }

    @Override // defpackage.NU
    public int j() {
        return 16;
    }

    @Override // defpackage.NU
    public void k(float[] fArr, float[] fArr2, float[] fArr3, float[] fArr4) {
        fArr2[0] = fArr4[96];
        fArr2[1] = fArr4[97];
        fArr2[2] = fArr4[98];
        fArr2[3] = fArr4[99];
        fArr2[4] = fArr4[100];
        fArr2[5] = fArr4[101];
        fArr2[8] = fArr4[102];
        fArr2[9] = fArr4[103];
        fArr2[10] = fArr4[104];
        fArr2[11] = fArr4[105];
        if (fArr3[11] != 0.0f) {
            this.f8477a = true;
        }
        if (fArr3[14] != 0.0f) {
            this.b = true;
        }
        if (this.f8477a) {
            fArr2[6] = (fArr3[11] + 1.0f) / 2.0f;
        } else {
            fArr2[6] = 0.0f;
        }
        if (this.b) {
            fArr2[7] = (fArr3[14] + 1.0f) / 2.0f;
        } else {
            fArr2[7] = 0.0f;
        }
        NU.a(fArr2, fArr3);
        fArr[0] = fArr3[0];
        fArr[1] = fArr3[1];
        NU.d(fArr, fArr3);
    }
}
