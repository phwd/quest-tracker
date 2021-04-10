package X;

import com.facebook.acra.util.HttpRequestMultipart;
import javax.annotation.Nullable;

/* renamed from: X.ce  reason: case insensitive filesystem */
public final class C0315ce {
    public int A00;
    public int A01;
    public C0315ce A02;
    public C0315ce A03;
    public boolean A04;
    public boolean A05;
    public final byte[] A06;

    public final C0315ce A01() {
        this.A05 = true;
        return new C0315ce(this.A06, this.A01, this.A00);
    }

    @Nullable
    public final C0315ce A00() {
        C0315ce ceVar = this.A02;
        if (ceVar == this) {
            ceVar = null;
        }
        C0315ce ceVar2 = this.A03;
        ceVar2.A02 = ceVar;
        this.A02.A03 = ceVar2;
        this.A02 = null;
        this.A03 = null;
        return ceVar;
    }

    public final void A02(C0315ce ceVar) {
        ceVar.A03 = this;
        ceVar.A02 = this.A02;
        this.A02.A03 = ceVar;
        this.A02 = ceVar;
    }

    public final void A03(C0315ce ceVar, int i) {
        if (ceVar.A04) {
            int i2 = ceVar.A00;
            int i3 = i2 + i;
            if (i3 > 8192) {
                if (!ceVar.A05) {
                    int i4 = ceVar.A01;
                    if (i3 - i4 <= 8192) {
                        byte[] bArr = ceVar.A06;
                        System.arraycopy(bArr, i4, bArr, 0, i2 - i4);
                        i2 = ceVar.A00 - ceVar.A01;
                        ceVar.A00 = i2;
                        ceVar.A01 = 0;
                    }
                }
            }
            System.arraycopy(this.A06, this.A01, ceVar.A06, i2, i);
            ceVar.A00 += i;
            this.A01 += i;
            return;
        }
        throw new IllegalArgumentException();
    }

    public C0315ce() {
        this.A06 = new byte[HttpRequestMultipart.STREAM_BLOCK_SIZE];
        this.A04 = true;
        this.A05 = false;
    }

    public C0315ce(byte[] bArr, int i, int i2) {
        this.A06 = bArr;
        this.A01 = i;
        this.A00 = i2;
        this.A05 = true;
        this.A04 = false;
    }
}
