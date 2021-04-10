package X;

import com.facebook.fbuploader.Content;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

/* renamed from: X.1Q9  reason: invalid class name */
public final class AnonymousClass1Q9 {
    public long A00;
    public final File A01;
    public final String A02;
    public final String A03;
    public final Map<String, Content.DigestInfo> A04 = new HashMap();

    public AnonymousClass1Q9(File file, String str) {
        this.A01 = file;
        file.getAbsolutePath();
        this.A00 = this.A01.length();
        this.A03 = "octet-stream";
        this.A02 = str;
    }
}
