package X;

import android.util.Base64OutputStream;
import com.facebook.infer.annotation.NullsafeStrict;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.concurrent.ThreadSafe;

@NullsafeStrict
@ThreadSafe
/* renamed from: X.0Lo  reason: invalid class name */
public final class AnonymousClass0Lo {
    public static final Map<AnonymousClass0Lo, Map<String, Object>> A02 = new HashMap();
    public final File A00;
    public final Object A01 = new Object();

    public final String A00() {
        String str;
        try {
            File file = this.A00;
            FileInputStream fileInputStream = new FileInputStream(file);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream((int) file.length());
            Base64OutputStream base64OutputStream = new Base64OutputStream(byteArrayOutputStream, 0);
            try {
                byte[] bArr = new byte[1024];
                while (true) {
                    int read = fileInputStream.read(bArr);
                    if (read != -1) {
                        base64OutputStream.write(bArr, 0, read);
                    } else {
                        base64OutputStream.close();
                        return byteArrayOutputStream.toString("UTF-8");
                    }
                }
            } finally {
                fileInputStream.close();
                base64OutputStream.close();
            }
        } catch (IOException e) {
            if (e.getMessage() != null) {
                str = e.getMessage();
            } else {
                str = "description N/A";
            }
            return AnonymousClass006.A09("[I/O error: ", str, "]");
        }
    }

    public AnonymousClass0Lo(File file) {
        this.A00 = file;
    }
}
