package X;

import java.io.File;

/* renamed from: X.1oD  reason: invalid class name */
public final class AnonymousClass1oD {
    public static void A00(File file) throws AnonymousClass1oI {
        if (file.exists()) {
            if (file.isDirectory()) {
                return;
            }
            if (!file.delete()) {
                throw new AnonymousClass1oI(file.getAbsolutePath(), new AnonymousClass1oL(file.getAbsolutePath()));
            }
        }
        if (!file.mkdirs() && !file.isDirectory()) {
            throw new AnonymousClass1oI(file.getAbsolutePath());
        }
    }
}
