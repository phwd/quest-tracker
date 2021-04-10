package X;

import java.io.File;

/* renamed from: X.1lr  reason: invalid class name and case insensitive filesystem */
public final class C10341lr {
    public static void A00(File file) throws AnonymousClass1mF {
        if (file.exists()) {
            if (file.isDirectory()) {
                return;
            }
            if (!file.delete()) {
                throw new AnonymousClass1mF(file.getAbsolutePath(), new AnonymousClass1mV(file.getAbsolutePath()));
            }
        }
        if (!file.mkdirs() && !file.isDirectory()) {
            throw new AnonymousClass1mF(file.getAbsolutePath());
        }
    }
}
