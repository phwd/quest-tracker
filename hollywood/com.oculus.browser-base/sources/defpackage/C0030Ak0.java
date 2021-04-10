package defpackage;

import android.net.Uri;
import android.webkit.MimeTypeMap;
import java.io.File;
import java.io.FileFilter;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

/* renamed from: Ak0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C0030Ak0 implements FileFilter {

    /* renamed from: a  reason: collision with root package name */
    public HashSet f7691a = new HashSet();
    public HashSet b = new HashSet();
    public HashSet c = new HashSet();
    public MimeTypeMap d;
    public boolean e;
    public boolean f;

    public C0030Ak0(List list, boolean z) {
        Iterator it = list.iterator();
        while (it.hasNext()) {
            String lowerCase = ((String) it.next()).trim().toLowerCase(Locale.US);
            if (lowerCase.startsWith(".")) {
                this.f7691a.add(lowerCase.substring(1));
            } else if (lowerCase.equals("*/*")) {
                this.e = true;
            } else if (lowerCase.endsWith("/*")) {
                this.c.add(lowerCase.substring(0, lowerCase.length() - 2));
            } else if (lowerCase.contains("/")) {
                this.b.add(lowerCase);
            }
        }
        this.d = MimeTypeMap.getSingleton();
        this.f = z;
    }

    public boolean a(Uri uri, String str) {
        if (uri != null) {
            String fileExtensionFromUrl = MimeTypeMap.getFileExtensionFromUrl(uri.toString());
            Locale locale = Locale.US;
            String lowerCase = fileExtensionFromUrl.toLowerCase(locale);
            if (this.f7691a.contains(lowerCase)) {
                return true;
            }
            if (str == null) {
                String mimeTypeFromExtension = this.d.getMimeTypeFromExtension(lowerCase);
                str = mimeTypeFromExtension != null ? mimeTypeFromExtension.toLowerCase(locale) : null;
            }
        }
        if (str == null || (!this.e && !this.b.contains(str) && !this.c.contains(str.split("/", 2)[0]))) {
            return false;
        }
        return true;
    }

    public boolean accept(File file) {
        if (file.isDirectory()) {
            return this.f;
        }
        return a(Uri.fromFile(file), null);
    }
}
