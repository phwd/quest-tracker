package defpackage;

import java.io.File;

/* renamed from: Vx1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class Vx1 implements Runnable {
    public final String F;

    public Vx1(String str) {
        this.F = str;
    }

    public void run() {
        new File(this.F).delete();
    }
}
