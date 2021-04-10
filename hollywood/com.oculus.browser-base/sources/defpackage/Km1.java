package defpackage;

import java.io.File;

/* renamed from: Km1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class Km1 implements Runnable {
    public File F;

    public Km1(File file) {
        this.F = file;
    }

    public void run() {
        this.F.delete();
    }
}
