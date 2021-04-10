package X;

import java.io.File;
import java.io.IOException;

/* renamed from: X.0qK  reason: invalid class name and case insensitive filesystem */
public final class C06930qK extends AnonymousClass0GE<File> {
    public static AnonymousClass0GE A00;

    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object] */
    @Override // X.AnonymousClass0GE
    public final AnonymousClass0GD A00(File file) {
        File file2 = file;
        try {
            return new C06940qM(this, file2);
        } catch (IOException e) {
            StringBuilder sb = new StringBuilder("Unexpected error, failed to create file: ");
            sb.append(file2);
            throw new AnonymousClass0GN(sb.toString(), e);
        }
    }
}
