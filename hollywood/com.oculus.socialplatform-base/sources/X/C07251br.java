package X;

import android.net.Uri;
import androidx.annotation.NonNull;
import com.oculus.horizon.logging.LoggingKeys;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/* renamed from: X.1br  reason: invalid class name and case insensitive filesystem */
public final class C07251br<Data> implements AbstractC07011bT<Uri, Data> {
    public static final Set<String> A01 = Collections.unmodifiableSet(new HashSet(Arrays.asList("file", "android.resource", LoggingKeys.REFERRER_CONTENT)));
    public final AbstractC07281bu<Data> A00;

    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object, int, int, X.1cO] */
    @Override // X.AbstractC07011bT
    public final C07091bb A1r(@NonNull Uri uri, int i, int i2, @NonNull AnonymousClass1cO r7) {
        Uri uri2 = uri;
        return new C07091bb(new AnonymousClass1S3(uri2), this.A00.A1m(uri2));
    }

    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object] */
    @Override // X.AbstractC07011bT
    public final boolean A5N(@NonNull Uri uri) {
        return A01.contains(uri.getScheme());
    }

    public C07251br(AbstractC07281bu<Data> r1) {
        this.A00 = r1;
    }
}
