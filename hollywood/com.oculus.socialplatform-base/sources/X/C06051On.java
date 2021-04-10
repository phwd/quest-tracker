package X;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;

/* renamed from: X.1On  reason: invalid class name and case insensitive filesystem */
public final class C06051On {
    public static final Charset A00 = Charset.forName("UTF-8");
    public static final Charset A01 = Charset.forName("US-ASCII");

    public static void A00(File file) throws IOException {
        File[] listFiles = file.listFiles();
        if (listFiles != null) {
            for (File file2 : listFiles) {
                if (file2.isDirectory()) {
                    A00(file2);
                }
                if (!file2.delete()) {
                    StringBuilder sb = new StringBuilder("failed to delete file: ");
                    sb.append(file2);
                    throw new IOException(sb.toString());
                }
            }
            return;
        }
        StringBuilder sb2 = new StringBuilder("not a readable directory: ");
        sb2.append(file);
        throw new IOException(sb2.toString());
    }
}
