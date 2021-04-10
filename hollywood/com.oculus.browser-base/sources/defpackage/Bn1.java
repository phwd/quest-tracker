package defpackage;

import android.content.SharedPreferences;
import java.util.Locale;
import java.util.Objects;
import org.chromium.base.Callback;
import org.chromium.base.task.PostTask;
import org.chromium.chrome.browser.profiles.Profile;

/* renamed from: Bn1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class Bn1 implements Runnable {
    public final Profile F;
    public final Callback G;

    public Bn1(Profile profile, Callback callback) {
        this.F = profile;
        this.G = callback;
    }

    public void run() {
        Profile profile = this.F;
        Callback callback = this.G;
        Objects.requireNonNull(Dn1.a());
        if (System.currentTimeMillis() >= Dn1.b().getLong("suppressed-until", -1)) {
            SharedPreferences.Editor edit = Dn1.b().edit();
            Objects.requireNonNull(Dn1.a());
            edit.putLong("suppressed-until", System.currentTimeMillis() + ((long) AbstractC2793h01.k.c())).commit();
            PostTask.b(Zo1.b, new Cn1(profile, AbstractC2793h01.l.c() + Locale.getDefault().getCountry(), callback), 0);
        }
    }
}
