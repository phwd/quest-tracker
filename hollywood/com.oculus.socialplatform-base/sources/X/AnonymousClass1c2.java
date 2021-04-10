package X;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

/* renamed from: X.1c2  reason: invalid class name */
public class AnonymousClass1c2 implements AbstractC07341c1<InputStream> {
    public final /* synthetic */ AnonymousClass1c3 A00;

    public AnonymousClass1c2(AnonymousClass1c3 r1) {
        this.A00 = r1;
    }

    /* Return type fixed from 'java.lang.Object' to match base method */
    @Override // X.AbstractC07341c1
    public final InputStream A2H(byte[] bArr) {
        return new ByteArrayInputStream(bArr);
    }

    @Override // X.AbstractC07341c1
    public final Class<InputStream> A3h() {
        return InputStream.class;
    }
}
