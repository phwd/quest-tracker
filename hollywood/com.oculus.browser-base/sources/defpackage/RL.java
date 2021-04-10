package defpackage;

import java.io.File;
import java.util.Comparator;

/* renamed from: RL  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class RL implements Comparator {
    public RL(UL ul) {
    }

    @Override // java.util.Comparator
    public int compare(Object obj, Object obj2) {
        int i = (((File) obj).lastModified() > ((File) obj2).lastModified() ? 1 : (((File) obj).lastModified() == ((File) obj2).lastModified() ? 0 : -1));
        if (i == 0) {
            return 0;
        }
        return i < 0 ? 1 : -1;
    }
}
