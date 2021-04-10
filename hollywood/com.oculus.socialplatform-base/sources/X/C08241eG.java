package X;

import java.io.IOException;
import java.io.InputStream;

/* renamed from: X.1eG  reason: invalid class name and case insensitive filesystem */
public class C08241eG implements AbstractC08281eK {
    public final /* synthetic */ AnonymousClass1hX A00;
    public final /* synthetic */ InputStream A01;

    public C08241eG(InputStream inputStream, AnonymousClass1hX r2) {
        this.A01 = inputStream;
        this.A00 = r2;
    }

    @Override // X.AbstractC08281eK
    public final int A4Z(AbstractC08251eH r3) throws IOException {
        try {
            InputStream inputStream = this.A01;
            int A4a = r3.A4a(inputStream, this.A00);
            inputStream.reset();
            return A4a;
        } catch (Throwable th) {
            this.A01.reset();
            throw th;
        }
    }
}
