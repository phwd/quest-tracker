package X;

import com.facebook.infer.annotation.Nullsafe;
import java.io.File;
import javax.annotation.Nullable;

@Nullsafe(Nullsafe.Mode.LOCAL)
/* renamed from: X.1NL  reason: invalid class name */
public final class AnonymousClass1NL {
    @Nullable
    public static File A00(File file, String str) {
        File file2 = new File(file, str);
        if (file2.exists() && !file2.isDirectory() && file2.exists()) {
            file2.delete();
        }
        if (file2.exists() || file2.mkdir()) {
            return file2;
        }
        return null;
    }
}
