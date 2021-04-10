package defpackage;

import java.io.File;

/* renamed from: cb1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class RunnableC2034cb1 implements Runnable {
    public RunnableC2034cb1(C4766sb1 sb1) {
    }

    public void run() {
        Object obj = AbstractC1102Sb1.f8901a;
        File[] listFiles = AbstractC1041Rb1.f8842a.listFiles();
        if (listFiles != null) {
            for (File file : listFiles) {
                if (file.isDirectory()) {
                    File[] listFiles2 = file.listFiles();
                    if (listFiles2 != null) {
                        for (File file2 : listFiles2) {
                            if (!file2.delete()) {
                                AbstractC1220Ua0.a("tabmodel", AbstractC2531fV.e("Failed to delete file: ", file2), new Object[0]);
                            }
                        }
                    }
                } else if (!file.delete()) {
                    AbstractC1220Ua0.a("tabmodel", AbstractC2531fV.e("Failed to delete file: ", file), new Object[0]);
                }
            }
        }
    }
}
