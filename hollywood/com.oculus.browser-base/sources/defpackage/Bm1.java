package defpackage;

import java.io.File;
import org.chromium.base.ContextUtils;

/* renamed from: Bm1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class Bm1 implements Runnable {
    public void run() {
        File[] listFiles = new File(ContextUtils.getApplicationContext().getCacheDir() + "/traces").listFiles();
        if (listFiles != null) {
            long currentTimeMillis = System.currentTimeMillis() - 3600000;
            for (File file : listFiles) {
                if (file.lastModified() <= currentTimeMillis) {
                    file.delete();
                }
            }
        }
    }
}
