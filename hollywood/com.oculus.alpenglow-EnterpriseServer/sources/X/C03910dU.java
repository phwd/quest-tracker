package X;

import android.net.Uri;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/* renamed from: X.0dU  reason: invalid class name and case insensitive filesystem */
public class C03910dU implements AnonymousClass07h {
    public final HashMap<String, File> A00 = new HashMap<>();
    public final String A01;

    public C03910dU(String str) {
        this.A01 = str;
    }

    @Override // X.AnonymousClass07h
    public final File A3Y(Uri uri) {
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
                throw new IllegalArgumentException("Failed to resolve canonical path for " + file2);
            }
        } else {
            throw new IllegalArgumentException("Unable to find configured root for " + uri);
        }
    }

    @Override // X.AnonymousClass07h
    public final Uri A4q(File file) {
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
                return new Uri.Builder().scheme("content").authority(this.A01).encodedPath(AnonymousClass006.A00(Uri.encode(entry.getKey()), '/', Uri.encode(canonicalPath.substring(length), "/"))).build();
            }
            throw new IllegalArgumentException(AnonymousClass006.A05("Failed to find configured root that contains ", canonicalPath));
        } catch (IOException unused) {
            throw new IllegalArgumentException("Failed to resolve canonical path for " + file);
        }
    }
}
