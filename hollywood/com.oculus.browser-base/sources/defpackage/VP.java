package defpackage;

import android.net.Uri;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

/* renamed from: VP  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class VP {

    /* renamed from: a  reason: collision with root package name */
    public final String f9080a;
    public final HashMap b = new HashMap();

    public VP(String str) {
        this.f9080a = str;
    }

    public File a(Uri uri) {
        String encodedPath = uri.getEncodedPath();
        int indexOf = encodedPath.indexOf(47, 1);
        String decode = Uri.decode(encodedPath.substring(1, indexOf));
        String decode2 = Uri.decode(encodedPath.substring(indexOf + 1));
        File file = (File) this.b.get(decode);
        if (file != null) {
            File file2 = new File(file, decode2);
            try {
                File canonicalFile = file2.getCanonicalFile();
                if (canonicalFile.getPath().startsWith(file.getPath())) {
                    return canonicalFile;
                }
                throw new SecurityException("Resolved path jumped beyond configured root");
            } catch (IOException unused) {
                throw new IllegalArgumentException(AbstractC2531fV.e("Failed to resolve canonical path for ", file2));
            }
        } else {
            throw new IllegalArgumentException(AbstractC2531fV.c("Unable to find configured root for ", uri));
        }
    }
}
