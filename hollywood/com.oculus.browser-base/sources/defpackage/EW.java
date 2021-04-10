package defpackage;

import java.util.Arrays;

/* renamed from: EW  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class EW {

    /* renamed from: a  reason: collision with root package name */
    public CW[] f7965a;
    public int b;
    public CW[][] c;
    public int[] d;
    public final /* synthetic */ CW[] e;
    public final /* synthetic */ FW f;

    public EW(FW fw, CW[] cwArr) {
        this.f = fw;
        this.e = cwArr;
        int length = cwArr.length;
        this.f7965a = new CW[length];
        this.b = length - 1;
        int h = fw.h() + 1;
        CW[][] cwArr2 = new CW[h][];
        int[] iArr = new int[h];
        for (CW cw : cwArr) {
            int i = cw.f7816a.f8160a;
            iArr[i] = iArr[i] + 1;
        }
        for (int i2 = 0; i2 < h; i2++) {
            cwArr2[i2] = new CW[iArr[i2]];
        }
        Arrays.fill(iArr, 0);
        for (CW cw2 : cwArr) {
            int i3 = cw2.f7816a.f8160a;
            CW[] cwArr3 = cwArr2[i3];
            int i4 = iArr[i3];
            iArr[i3] = i4 + 1;
            cwArr3[i4] = cw2;
        }
        this.c = cwArr2;
        this.d = new int[(this.f.h() + 1)];
    }

    public void a(int i) {
        int[] iArr = this.d;
        if (iArr[i] == 0) {
            iArr[i] = 1;
            CW[] cwArr = this.c[i];
            for (CW cw : cwArr) {
                a(cw.f7816a.b);
                CW[] cwArr2 = this.f7965a;
                int i2 = this.b;
                this.b = i2 - 1;
                cwArr2[i2] = cw;
            }
            this.d[i] = 2;
        }
    }
}
