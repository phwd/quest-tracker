package X;

import androidx.annotation.NonNull;
import java.io.File;
import java.nio.ByteBuffer;

/* renamed from: X.1b5  reason: invalid class name and case insensitive filesystem */
public final class C06821b5 implements AbstractC07011bT<File, ByteBuffer> {
    /* Return type fixed from 'X.1bb' to match base method */
    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object, int, int, X.1cO] */
    @Override // X.AbstractC07011bT
    public final C07091bb<ByteBuffer> A1r(@NonNull File file, int i, int i2, @NonNull AnonymousClass1cO r7) {
        File file2 = file;
        return new C07091bb(new AnonymousClass1S3(file2), new C06621al(file2));
    }

    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object] */
    @Override // X.AbstractC07011bT
    public final boolean A5N(@NonNull File file) {
        return true;
    }
}
