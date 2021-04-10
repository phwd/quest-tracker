package defpackage;

import java.io.File;
import java.util.Comparator;

/* renamed from: YA  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class YA implements Comparator {
    @Override // java.util.Comparator
    public int compare(Object obj, Object obj2) {
        File file = (File) obj;
        File file2 = (File) obj2;
        if (file.lastModified() == file2.lastModified()) {
            return file.compareTo(file2);
        }
        return file.lastModified() < file2.lastModified() ? 1 : -1;
    }
}
