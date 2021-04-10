package defpackage;

import android.util.Pair;
import java.util.List;
import org.chromium.chrome.browser.autofill.PersonalDataManager;

/* renamed from: vm  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C5307vm extends AbstractC0823Nl {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ PersonalDataManager.CreditCard f11496a;
    public final /* synthetic */ List b;
    public final /* synthetic */ C5647xm c;

    public C5307vm(C5647xm xmVar, PersonalDataManager.CreditCard creditCard, List list) {
        this.c = xmVar;
        this.f11496a = creditCard;
        this.b = list;
    }

    @Override // org.chromium.base.Callback
    public void onResult(Object obj) {
        PersonalDataManager.AutofillProfile autofillProfile;
        Pair pair = (Pair) obj;
        boolean equals = "add".equals(pair.first);
        boolean containsKey = this.c.e.containsKey(pair.first);
        if (equals || containsKey) {
            if (equals) {
                autofillProfile = new PersonalDataManager.AutofillProfile();
                autofillProfile.e = this.f11496a.getIsLocal() ? this.c.q.s.toString() : this.f11496a.getName();
            } else {
                autofillProfile = C5647xm.c(this.c.d, (String) pair.first);
            }
            this.c.f.d(new C2892hd(this.c.b, autofillProfile), new C5137um(this, containsKey, pair));
        }
    }
}
