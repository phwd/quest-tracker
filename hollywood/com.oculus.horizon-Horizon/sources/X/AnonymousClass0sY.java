package X;

import android.net.Uri;
import com.oculus.horizon.logging.LoggingKeys;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/* renamed from: X.0sY  reason: invalid class name */
public class AnonymousClass0sY implements AnonymousClass04K {
    public final HashMap<String, File> A00 = new HashMap<>();
    public final String A01;

    public AnonymousClass0sY(String str) {
        this.A01 = str;
    }

    @Override // X.AnonymousClass04K
    public final File A3Q(Uri uri) {
        String encodedPath = uri.getEncodedPath();
        int indexOf = encodedPath.indexOf(47, 1);
        String decode = Uri.decode(encodedPath.substring(1, indexOf));
        String decode2 = Uri.decode(encodedPath.substring(indexOf + 1));
        File file = this.A00.get(decode);
        if (file != null) {
            File file2 = new File(file, decode2);
            try {
                File canonicalFile = file2.getCanonicalFile();
                if (canonicalFile.getPath().startsWith(file.getPath())) {
                    return canonicalFile;
                }
                throw new SecurityException("Resolved path jumped beyond configured root");
            } catch (IOException unused) {
                StringBuilder sb = new StringBuilder("Failed to resolve canonical path for ");
                sb.append(file2);
                throw new IllegalArgumentException(sb.toString());
            }
        } else {
            StringBuilder sb2 = new StringBuilder("Unable to find configured root for ");
            sb2.append(uri);
            throw new IllegalArgumentException(sb2.toString());
        }
    }

    @Override // X.AnonymousClass04K
    public final Uri A4b(File file) {
        try {
            String canonicalPath = file.getCanonicalPath();
            Map.Entry<String, File> entry = null;
            for (Map.Entry<String, File> entry2 : this.A00.entrySet()) {
                String path = entry2.getValue().getPath();
                if (canonicalPath.startsWith(path) && (entry == null || path.length() > entry.getValue().getPath().length())) {
                    entry = entry2;
                }
            }
            if (entry != null) {
                String path2 = entry.getValue().getPath();
                boolean endsWith = path2.endsWith("/");
                int length = path2.length();
                if (!endsWith) {
                    length++;
                }
                return new Uri.Builder().scheme(LoggingKeys.REFERRER_CONTENT).authority(this.A01).encodedPath(AnonymousClass006.A00(Uri.encode(entry.getKey()), '/', Uri.encode(canonicalPath.substring(length), "/"))).build();
            }
            throw new IllegalArgumentException(AnonymousClass006.A05("Failed to find configured root that contains ", canonicalPath));
        } catch (IOException unused) {
            StringBuilder sb = new StringBuilder("Failed to resolve canonical path for ");
            sb.append(file);
            throw new IllegalArgumentException(sb.toString());
        }
    }
}
