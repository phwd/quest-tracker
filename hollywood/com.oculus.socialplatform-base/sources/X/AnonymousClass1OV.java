package X;

import com.facebook.infer.annotation.Nullsafe;
import java.io.File;

@Nullsafe(Nullsafe.Mode.LOCAL)
/* renamed from: X.1OV  reason: invalid class name */
public final class AnonymousClass1OV {
    public static void A00(File file) {
        File[] listFiles;
        if (file.isDirectory() && (listFiles = file.listFiles()) != null) {
            for (File file2 : listFiles) {
                A00(file2);
            }
        }
        file.delete();
    }
}
