package defpackage;

import J.N;
import android.text.TextUtils;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Set;
import org.chromium.base.ThreadUtils;
import org.chromium.chrome.browser.autofill.PersonalDataManager;
import org.chromium.components.payments.PaymentApp;

/* renamed from: Zd  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class C1533Zd implements AbstractC1289Vd {

    /* renamed from: a  reason: collision with root package name */
    public final AbstractC1460Xy0 f9354a;
    public final boolean b;
    public final Set c;

    public C1533Zd(AbstractC1460Xy0 xy0, C1411Xd xd) {
        this.f9354a = xy0;
        Set b2 = C1690ae.b(xy0.d().h());
        this.c = b2;
        this.b = !b2.isEmpty();
    }

    public static boolean a(C1533Zd zd) {
        if (zd.f9354a.d().e() || !zd.b) {
            return false;
        }
        PersonalDataManager c2 = PersonalDataManager.c();
        boolean M1X7xdZV = N.M1X7xdZV("ReturnGooglePayInBasicCard");
        Objects.requireNonNull(c2);
        Object obj = ThreadUtils.f10596a;
        ArrayList b2 = c2.b(N.M00Q2SNr(c2.b, c2, M1X7xdZV));
        int size = b2.size();
        for (int i = 0; i < size; i++) {
            PaymentApp b3 = zd.b((PersonalDataManager.CreditCard) b2.get(i));
            if (b3 != null) {
                zd.f9354a.j(b3);
            }
        }
        return true;
    }

    public PaymentApp b(PersonalDataManager.CreditCard creditCard) {
        PersonalDataManager.AutofillProfile autofillProfile;
        if (!this.b || this.f9354a.d().e() || !this.c.contains(creditCard.getBasicCardIssuerNetwork())) {
            return null;
        }
        if (TextUtils.isEmpty(creditCard.getBillingAddressId())) {
            autofillProfile = null;
        } else {
            autofillProfile = PersonalDataManager.c().e(creditCard.getBillingAddressId());
        }
        if (!(autofillProfile == null || C2892hd.g(autofillProfile, 1) == 0)) {
            autofillProfile = null;
        }
        if (autofillProfile == null) {
            creditCard.l = null;
        }
        return new C1870be(this.f9354a.d().t(), creditCard, autofillProfile, "basic-card");
    }
}
