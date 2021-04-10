package defpackage;

import com.oculus.os.Version;
import java.util.BitSet;

/* renamed from: KU  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class KU extends NU {

    /* renamed from: a  reason: collision with root package name */
    public int f8368a = -1;
    public int b = -1;
    public int c = -1;
    public int d = -1;
    public boolean e;
    public final boolean f;

    public KU(int[] iArr, BitSet bitSet) {
        this.f = bitSet.get(110);
        int i = 0;
        for (int i2 : iArr) {
            switch (i2) {
                case Version.VERSION_11 /*{ENCODED_INT: 11}*/:
                case Version.VERSION_12 /*{ENCODED_INT: 12}*/:
                    this.c = i2;
                    break;
                case Version.VERSION_13 /*{ENCODED_INT: 13}*/:
                case Version.VERSION_14 /*{ENCODED_INT: 14}*/:
                    this.d = i2;
                    break;
                case Version.VERSION_15 /*{ENCODED_INT: 15}*/:
                case Version.VERSION_16 /*{ENCODED_INT: 16}*/:
                    i++;
                    break;
                case Version.VERSION_17 /*{ENCODED_INT: 17}*/:
                case Version.VERSION_23 /*{ENCODED_INT: 23}*/:
                    this.f8368a = i2;
                    break;
                case Version.VERSION_18 /*{ENCODED_INT: 18}*/:
                case Version.VERSION_19 /*{ENCODED_INT: 19}*/:
                case Version.VERSION_22 /*{ENCODED_INT: 22}*/:
                    this.b = i2;
                    break;
            }
        }
        if (i == 2) {
            this.e = true;
        }
    }

    @Override // defpackage.NU
    public int j() {
        return this.f ? 17 : 16;
    }

    @Override // defpackage.NU
    public void k(float[] fArr, float[] fArr2, float[] fArr3, float[] fArr4) {
        int i;
        int i2;
        NU.f(fArr2, fArr4);
        NU.g(fArr2, fArr4);
        NU.h(fArr2, fArr4);
        NU.i(fArr2, fArr4);
        fArr[0] = fArr3[0];
        fArr[1] = fArr3[1];
        int i3 = this.f8368a;
        if (i3 == -1 || (i2 = this.b) == -1) {
            float f2 = fArr4[104];
            float f3 = fArr4[105];
            fArr2[6] = f2;
            fArr2[7] = f3;
        } else {
            float f4 = fArr3[i3];
            float f5 = fArr3[i2];
            fArr2[6] = f4;
            fArr2[7] = f5;
        }
        int i4 = this.c;
        if (!(i4 == -1 || (i = this.d) == -1)) {
            float f6 = fArr3[i4];
            float f7 = fArr3[i];
            fArr[2] = f6;
            fArr[3] = f7;
        }
        if (this.e) {
            NU.a(fArr2, fArr3);
        } else {
            NU.e(fArr2, fArr4);
        }
    }
}
