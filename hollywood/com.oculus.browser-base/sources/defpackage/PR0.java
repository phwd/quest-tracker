package defpackage;

import java.io.File;
import java.io.IOException;
import org.chromium.base.ContextUtils;
import org.chromium.ui.base.SelectFileDialog;

/* renamed from: PR0  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class PR0 implements Runnable {
    public void run() {
        String[] strArr = SelectFileDialog.b;
        try {
            File d = AbstractC2417ep1.d(ContextUtils.getApplicationContext());
            if (d.isDirectory()) {
                File[] listFiles = d.listFiles();
                if (listFiles != null) {
                    long currentTimeMillis = System.currentTimeMillis();
                    for (File file : listFiles) {
                        if (currentTimeMillis - file.lastModified() > SelectFileDialog.f11020a && !file.delete()) {
                            AbstractC1220Ua0.a("SelectFileDialog", "Failed to delete: " + file, new Object[0]);
                        }
                    }
                }
            }
        } catch (IOException e) {
            AbstractC1220Ua0.f("SelectFileDialog", "Failed to delete captured camera files.", e);
        }
    }
}
