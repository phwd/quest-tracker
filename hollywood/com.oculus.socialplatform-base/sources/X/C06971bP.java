package X;

import androidx.annotation.NonNull;
import java.io.InputStream;
import java.net.URL;

/* renamed from: X.1bP  reason: invalid class name and case insensitive filesystem */
public final class C06971bP implements AbstractC07011bT<URL, InputStream> {
    public final AbstractC07011bT<AnonymousClass1Rx, InputStream> A00;

    /* Return type fixed from 'X.1bb' to match base method */
    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object, int, int, X.1cO] */
    @Override // X.AbstractC07011bT
    public final C07091bb<InputStream> A1r(@NonNull URL url, int i, int i2, @NonNull AnonymousClass1cO r6) {
        return this.A00.A1r(new AnonymousClass1Rx(url), i, i2, r6);
    }

    public C06971bP(AbstractC07011bT<AnonymousClass1Rx, InputStream> r1) {
        this.A00 = r1;
    }

    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object] */
    @Override // X.AbstractC07011bT
    public final boolean A5N(@NonNull URL url) {
        return true;
    }
}
