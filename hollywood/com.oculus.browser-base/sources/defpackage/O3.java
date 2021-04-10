package defpackage;

import J.N;
import java.util.Objects;
import org.chromium.base.Callback;
import org.chromium.base.ThreadUtils;
import org.chromium.chrome.browser.autofill.PersonalDataManager;

/* renamed from: O3  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class O3 implements Runnable {
    public final S3 F;
    public final Callback G;
    public final C2892hd H;

    public O3(S3 s3, Callback callback, C2892hd hdVar) {
        this.F = s3;
        this.G = callback;
        this.H = hdVar;
    }

    public void run() {
        S3 s3 = this.F;
        Callback callback = this.G;
        C2892hd hdVar = this.H;
        s3.q = true;
        PersonalDataManager c = PersonalDataManager.c();
        Objects.requireNonNull(c);
        Object obj = ThreadUtils.f10596a;
        N.MCBooW5W(c.b);
        callback.onResult(hdVar);
    }
}
