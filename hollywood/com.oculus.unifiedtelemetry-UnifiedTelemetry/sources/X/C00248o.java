package X;

import com.facebook.infer.annotation.Nullsafe;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.Writer;

@Nullsafe(Nullsafe.Mode.LOCAL)
/* renamed from: X.8o  reason: invalid class name and case insensitive filesystem */
public final class C00248o extends I9 {
    public final ByteArrayOutputStream A00;

    @Override // X.I9
    public final void A02() {
    }

    @Override // X.I9
    public final void A03() {
        this.A00.reset();
    }

    @Override // X.I9
    public final void A04(Writer writer) throws IOException {
        writer.write(this.A00.toString());
    }

    public C00248o(ByteArrayOutputStream byteArrayOutputStream, Fw fw) {
        super(fw, byteArrayOutputStream);
        this.A00 = byteArrayOutputStream;
    }

    @Override // X.I9
    public final AbstractC0081Fy A00() {
        return C0271Yv.A01().A02(this.A00);
    }
}
