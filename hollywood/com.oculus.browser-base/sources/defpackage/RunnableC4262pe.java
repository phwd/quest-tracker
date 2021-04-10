package defpackage;

import J.N;
import java.util.Objects;
import org.chromium.base.ThreadUtils;
import org.chromium.base.task.PostTask;
import org.chromium.chrome.browser.autofill.PersonalDataManager;
import org.chromium.chrome.browser.autofill.settings.AutofillProfilesFragment;

/* renamed from: pe  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class RunnableC4262pe implements Runnable {
    public final String F;

    public RunnableC4262pe(String str) {
        this.F = str;
    }

    public void run() {
        String str = this.F;
        int i = AutofillProfilesFragment.G0;
        PersonalDataManager c = PersonalDataManager.c();
        Objects.requireNonNull(c);
        Object obj = ThreadUtils.f10596a;
        N.MIAwuIe5(c.b, c, str);
        C2187dT0 a2 = C2187dT0.a();
        Objects.requireNonNull(a2);
        for (AbstractC2016cT0 ct0 : C2187dT0.f9784a) {
            PostTask.b(Zo1.f9374a, new ZS0(a2, ct0, str), 0);
        }
    }
}
