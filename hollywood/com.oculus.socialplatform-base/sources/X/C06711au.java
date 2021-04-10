package X;

import androidx.annotation.NonNull;
import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;

/* renamed from: X.1au  reason: invalid class name and case insensitive filesystem */
public final class C06711au implements AbstractC06701at<ByteBuffer> {
    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object, java.io.File, X.1cO] */
    @Override // X.AbstractC06701at
    public final boolean A2m(@NonNull ByteBuffer byteBuffer, @NonNull File file, @NonNull AnonymousClass1cO r4) {
        try {
            C06631am.A00(byteBuffer, file);
            return true;
        } catch (IOException unused) {
            return false;
        }
    }
}
