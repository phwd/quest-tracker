package X;

import android.graphics.Bitmap;
import java.io.IOException;

/* renamed from: X.1cf  reason: invalid class name and case insensitive filesystem */
public class C07571cf implements AnonymousClass1h7 {
    public final C06741ax A00;
    public final AnonymousClass1cV A01;

    @Override // X.AnonymousClass1h7
    public final void A6y(AbstractC07941di r2, Bitmap bitmap) throws IOException {
        IOException iOException = this.A01.A00;
        if (iOException != null) {
            if (bitmap != null) {
                r2.A8l(bitmap);
            }
            throw iOException;
        }
    }

    @Override // X.AnonymousClass1h7
    public final void A7S() {
        C06741ax r1 = this.A00;
        synchronized (r1) {
            r1.A00 = r1.A05.length;
        }
    }

    public C07571cf(C06741ax r1, AnonymousClass1cV r2) {
        this.A00 = r1;
        this.A01 = r2;
    }
}
