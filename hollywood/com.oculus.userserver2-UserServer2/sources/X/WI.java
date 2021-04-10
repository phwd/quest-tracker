package X;

import com.facebook.acra.util.HttpRequestMultipart;
import javax.annotation.Nullable;

public final class WI {
    public int A00;
    public int A01;
    public WI A02;
    public WI A03;
    public boolean A04;
    public boolean A05;
    public final byte[] A06;

    public final WI A01() {
        this.A05 = true;
        return new WI(this.A06, this.A01, this.A00);
    }

    @Nullable
    public final WI A00() {
        WI wi = this.A02;
        if (wi == this) {
            wi = null;
        }
        WI wi2 = this.A03;
        wi2.A02 = wi;
        this.A02.A03 = wi2;
        this.A02 = null;
        this.A03 = null;
        return wi;
    }

    public final void A02(WI wi) {
        wi.A03 = this;
        wi.A02 = this.A02;
        this.A02.A03 = wi;
        this.A02 = wi;
    }

    public final void A03(WI wi, int i) {
        if (wi.A04) {
            int i2 = wi.A00;
            int i3 = i2 + i;
            if (i3 > 8192) {
                if (!wi.A05) {
                    int i4 = wi.A01;
                    if (i3 - i4 <= 8192) {
                        byte[] bArr = wi.A06;
                        System.arraycopy(bArr, i4, bArr, 0, i2 - i4);
                        i2 = wi.A00 - wi.A01;
                        wi.A00 = i2;
                        wi.A01 = 0;
                    }
                }
            }
            System.arraycopy(this.A06, this.A01, wi.A06, i2, i);
            wi.A00 += i;
            this.A01 += i;
            return;
        }
        throw new IllegalArgumentException();
    }

    public WI() {
        this.A06 = new byte[HttpRequestMultipart.STREAM_BLOCK_SIZE];
        this.A04 = true;
        this.A05 = false;
    }

    public WI(byte[] bArr, int i, int i2) {
        this.A06 = bArr;
        this.A01 = i;
        this.A00 = i2;
        this.A05 = true;
        this.A04 = false;
    }
}
