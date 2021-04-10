package X;

import com.facebook.infer.annotation.Nullsafe;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import javax.annotation.Nullable;

@Nullsafe(Nullsafe.Mode.LOCAL)
public final class MX {
    @Nullable
    public static volatile String A00;
    public static volatile boolean A01;

    @Nullable
    public static String A00() {
        String str;
        if (!A01) {
            try {
                FileInputStream fileInputStream = new FileInputStream(new File("/proc/self/cmdline"));
                byte[] bArr = new byte[512];
                try {
                    int read = fileInputStream.read(bArr);
                    if (read != -1) {
                        str = new String(bArr, 0, read).trim();
                        A00 = str;
                        A01 = true;
                    } else {
                        throw new EOFException();
                    }
                } finally {
                    fileInputStream.close();
                }
            } catch (IOException unused) {
                str = null;
            }
        }
        return A00;
    }
}
