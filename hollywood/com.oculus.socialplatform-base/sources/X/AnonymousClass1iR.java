package X;

import com.facebook.infer.annotation.Nullsafe;
import java.io.IOException;
import java.io.InputStream;
import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
@Nullsafe(Nullsafe.Mode.LOCAL)
/* renamed from: X.1iR  reason: invalid class name */
public final class AnonymousClass1iR implements AnonymousClass0JW {
    public final AnonymousClass0JZ A00;
    public final AbstractC09461i1 A01;

    @Override // X.AnonymousClass0JW
    public final AnonymousClass0JV A6R(InputStream inputStream) throws IOException {
        AbstractC09461i1 r2 = this.A01;
        AnonymousClass1iS r1 = new AnonymousClass1iS(r2, r2.A00[0]);
        try {
            this.A00.A00(inputStream, r1);
            return r1.A01();
        } finally {
            r1.close();
        }
    }

    @Override // X.AnonymousClass0JW
    public final AnonymousClass0JV A6S(InputStream inputStream, int i) throws IOException {
        AnonymousClass1iS r1 = new AnonymousClass1iS(this.A01, i);
        try {
            this.A00.A00(inputStream, r1);
            return r1.A01();
        } finally {
            r1.close();
        }
    }

    @Override // X.AnonymousClass0JW
    public final AnonymousClass0JV A6T(byte[] bArr) {
        AbstractC09461i1 r0 = this.A01;
        int length = bArr.length;
        AnonymousClass1iS r2 = new AnonymousClass1iS(r0, length);
        try {
            r2.write(bArr, 0, length);
            AnonymousClass1iV A02 = r2.A01();
            r2.close();
            return A02;
        } catch (IOException e) {
            C00770Im.A00(e);
            throw new RuntimeException("Redex: Unreachable code after no-return invoke");
        } catch (Throwable th) {
            r2.close();
            throw th;
        }
    }

    @Override // X.AnonymousClass0JW
    public final AnonymousClass0JY A6U() {
        AbstractC09461i1 r2 = this.A01;
        return new AnonymousClass1iS(r2, r2.A00[0]);
    }

    @Override // X.AnonymousClass0JW
    public final AnonymousClass0JY A6V(int i) {
        return new AnonymousClass1iS(this.A01, i);
    }

    public AnonymousClass1iR(AbstractC09461i1 r1, AnonymousClass0JZ r2) {
        this.A01 = r1;
        this.A00 = r2;
    }
}
