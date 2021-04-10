package X;

import android.net.Uri;
import androidx.annotation.NonNull;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/* renamed from: X.1bL  reason: invalid class name and case insensitive filesystem */
public final class C06931bL<Data> implements AbstractC07011bT<Uri, Data> {
    public static final Set<String> A01 = Collections.unmodifiableSet(new HashSet(Arrays.asList("http", "https")));
    public final AbstractC07011bT<AnonymousClass1Rx, Data> A00;

    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object] */
    @Override // X.AbstractC07011bT
    public final boolean A5N(@NonNull Uri uri) {
        return A01.contains(uri.getScheme());
    }

    public C06931bL(AbstractC07011bT<AnonymousClass1Rx, Data> r1) {
        this.A00 = r1;
    }

    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object, int, int, X.1cO] */
    @Override // X.AbstractC07011bT
    public final C07091bb A1r(@NonNull Uri uri, int i, int i2, @NonNull AnonymousClass1cO r6) {
        return this.A00.A1r(new AnonymousClass1Rx(uri.toString()), i, i2, r6);
    }
}
