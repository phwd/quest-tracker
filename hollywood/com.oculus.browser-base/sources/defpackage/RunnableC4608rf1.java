package defpackage;

import java.util.Objects;
import org.chromium.base.task.PostTask;
import org.chromium.chrome.browser.profiles.Profile;

/* renamed from: rf1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class RunnableC4608rf1 implements Runnable {
    public final C4778sf1 F;

    public RunnableC4608rf1(C4778sf1 sf1) {
        this.F = sf1;
    }

    public void run() {
        C4778sf1 sf1 = this.F;
        Objects.requireNonNull(sf1);
        Dn1.c(Profile.b());
        Jn1 jn1 = sf1.e;
        Objects.requireNonNull(jn1);
        PostTask.b(C3070if1.i, new Gn1(jn1), 0);
    }
}
