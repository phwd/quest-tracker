package X;

import androidx.annotation.NonNull;
import java.io.File;
import java.io.IOException;

/* renamed from: X.1cb  reason: invalid class name and case insensitive filesystem */
public final class C07531cb implements AnonymousClass1dN<File, File> {
    /* Return type fixed from 'X.1fR' to match base method */
    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object, int, int, X.1cO] */
    @Override // X.AnonymousClass1dN
    public final AnonymousClass1fR<File> A2V(@NonNull File file, int i, int i2, @NonNull AnonymousClass1cO r5) throws IOException {
        return new C07521ca(file);
    }

    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object, X.1cO] */
    @Override // X.AnonymousClass1dN
    public final boolean A5O(@NonNull File file, @NonNull AnonymousClass1cO r3) throws IOException {
        return true;
    }
}
