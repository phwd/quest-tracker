package X;

import androidx.annotation.NonNull;
import java.io.InputStream;

/* renamed from: X.1ci  reason: invalid class name and case insensitive filesystem */
public final class C07601ci implements AbstractC07621ck<InputStream> {
    public final AnonymousClass1hX A00;

    /* Return type fixed from 'X.1e8' to match base method */
    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object] */
    @Override // X.AbstractC07621ck
    @NonNull
    public final AbstractC08171e8<InputStream> A1n(InputStream inputStream) {
        return new C07591ch(inputStream, this.A00);
    }

    @Override // X.AbstractC07621ck
    @NonNull
    public final Class<InputStream> A3h() {
        return InputStream.class;
    }

    public C07601ci(AnonymousClass1hX r1) {
        this.A00 = r1;
    }
}
