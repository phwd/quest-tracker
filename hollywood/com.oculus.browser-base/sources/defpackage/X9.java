package defpackage;

import org.chromium.base.ApplicationStatus;

/* renamed from: X9  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class X9 implements Runnable {
    public void run() {
        if (ApplicationStatus.f == null) {
            W9 w9 = new W9(this);
            ApplicationStatus.f = w9;
            ApplicationStatus.h.b(w9);
        }
    }
}
