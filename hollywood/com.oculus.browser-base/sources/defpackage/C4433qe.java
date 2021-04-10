package defpackage;

import J.N;
import java.util.Objects;
import org.chromium.base.ThreadUtils;
import org.chromium.base.task.PostTask;
import org.chromium.chrome.browser.autofill.PersonalDataManager;
import org.chromium.chrome.browser.autofill.settings.AutofillProfilesFragment;

/* renamed from: qe  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C4433qe extends AbstractC0823Nl {
    @Override // org.chromium.base.Callback
    public void onResult(Object obj) {
        C2892hd hdVar = (C2892hd) obj;
        int i = AutofillProfilesFragment.G0;
        if (hdVar != null) {
            PersonalDataManager c = PersonalDataManager.c();
            PersonalDataManager.AutofillProfile autofillProfile = hdVar.m;
            Objects.requireNonNull(c);
            Object obj2 = ThreadUtils.f10596a;
            N.MgzFcfQz(c.b, c, autofillProfile);
            C2187dT0 a2 = C2187dT0.a();
            Objects.requireNonNull(a2);
            for (AbstractC2016cT0 ct0 : C2187dT0.f9784a) {
                PostTask.b(Zo1.f9374a, new YS0(a2, ct0, hdVar), 0);
            }
        }
    }
}
