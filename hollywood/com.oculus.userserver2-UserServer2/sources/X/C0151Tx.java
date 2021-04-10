package X;

import android.net.Uri;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/* renamed from: X.Tx  reason: case insensitive filesystem */
public class C0151Tx implements AnonymousClass54 {
    public final HashMap<String, File> A00 = new HashMap<>();
    public final String A01;

    public C0151Tx(String str) {
        this.A01 = str;
    }

    @Override // X.AnonymousClass54
    public final File A1f(Uri uri) {
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

    @Override // X.AnonymousClass54
    public final Uri A22(File file) {
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
                return new Uri.Builder().scheme("content").authority(this.A01).encodedPath(AnonymousClass06.A00(Uri.encode(entry.getKey()), '/', Uri.encode(canonicalPath.substring(length), "/"))).build();
            }
            throw new IllegalArgumentException(AnonymousClass06.A03("Failed to find configured root that contains ", canonicalPath));
        } catch (IOException unused) {
            StringBuilder sb = new StringBuilder("Failed to resolve canonical path for ");
            sb.append(file);
            throw new IllegalArgumentException(sb.toString());
        }
    }
}
