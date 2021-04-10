package X;

import android.graphics.Bitmap;
import androidx.annotation.NonNull;
import java.io.IOException;
import java.nio.ByteBuffer;

/* renamed from: X.1cd  reason: invalid class name and case insensitive filesystem */
public final class C07551cd implements AnonymousClass1dN<ByteBuffer, Bitmap> {
    public final AnonymousClass1gC A00;

    /* Return type fixed from 'X.1fR' to match base method */
    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object, int, int, X.1cO] */
    @Override // X.AnonymousClass1dN
    public final AnonymousClass1fR<Bitmap> A2V(@NonNull ByteBuffer byteBuffer, int i, int i2, @NonNull AnonymousClass1cO r13) throws IOException {
        C07881dc r2 = new C07881dc(byteBuffer);
        AnonymousClass1gC r3 = this.A00;
        return AnonymousClass1gC.A01(r3, new C07581cg(r2, r3.A04, r3.A02), i, i2, r13, AnonymousClass1gC.A0A);
    }

    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object, X.1cO] */
    @Override // X.AnonymousClass1dN
    public final boolean A5O(@NonNull ByteBuffer byteBuffer, @NonNull AnonymousClass1cO r3) throws IOException {
        return true;
    }

    public C07551cd(AnonymousClass1gC r1) {
        this.A00 = r1;
    }
}
