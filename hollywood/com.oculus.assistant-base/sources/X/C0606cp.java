package X;

import com.facebook.acra.util.HttpRequestMultipart;

/* renamed from: X.cp  reason: case insensitive filesystem */
public final class C0606cp {
    public int A00;
    public int A01;
    public C0606cp A02;
    public C0606cp A03;
    public boolean A04;
    public boolean A05;
    public final byte[] A06;

    public final C0606cp A01() {
        this.A05 = true;
        return new C0606cp(this.A06, this.A01, this.A00);
    }

    public final C0606cp A00() {
        C0606cp cpVar = this.A02;
        if (cpVar == this) {
            cpVar = null;
        }
        C0606cp cpVar2 = this.A03;
        cpVar2.A02 = cpVar;
        this.A02.A03 = cpVar2;
        this.A02 = null;
        this.A03 = null;
        return cpVar;
    }

    public final void A02(C0606cp cpVar) {
        cpVar.A03 = this;
        cpVar.A02 = this.A02;
        this.A02.A03 = cpVar;
        this.A02 = cpVar;
    }

    public final void A03(C0606cp cpVar, int i) {
        if (cpVar.A04) {
            int i2 = cpVar.A00;
            int i3 = i2 + i;
            if (i3 > 8192) {
                if (!cpVar.A05) {
                    int i4 = cpVar.A01;
                    if (i3 - i4 <= 8192) {
                        byte[] bArr = cpVar.A06;
                        System.arraycopy(bArr, i4, bArr, 0, i2 - i4);
                        i2 = cpVar.A00 - cpVar.A01;
                        cpVar.A00 = i2;
                        cpVar.A01 = 0;
                    } else {
                        throw new IllegalArgumentException();
                    }
                } else {
                    throw new IllegalArgumentException();
                }
            }
            System.arraycopy(this.A06, this.A01, cpVar.A06, i2, i);
            cpVar.A00 += i;
            this.A01 += i;
            return;
        }
        throw new IllegalArgumentException();
    }

    public C0606cp() {
        this.A06 = new byte[HttpRequestMultipart.STREAM_BLOCK_SIZE];
        this.A04 = true;
        this.A05 = false;
    }

    public C0606cp(byte[] bArr, int i, int i2) {
        this.A06 = bArr;
        this.A01 = i;
        this.A00 = i2;
        this.A05 = true;
        this.A04 = false;
    }
}
