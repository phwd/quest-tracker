package X;

import com.facebook.infer.annotation.Nullsafe;
import java.io.File;

@Nullsafe(Nullsafe.Mode.LOCAL)
/* renamed from: X.1XI  reason: invalid class name */
public final class AnonymousClass1XI extends File {
    public AnonymousClass1XI(File file) {
        super(file.getPath());
    }

    public AnonymousClass1XI(String str) {
        super((File) null, str);
    }
}
