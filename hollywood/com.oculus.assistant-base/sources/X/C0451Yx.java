package X;

import java.io.File;

/* renamed from: X.Yx  reason: case insensitive filesystem */
public final class C0451Yx {
    public static File A00() {
        StringBuilder sb = new StringBuilder();
        sb.append(BX.A00().getFilesDir());
        sb.append(File.separator);
        sb.append("UtteranceLogs");
        return new File(sb.toString());
    }
}
