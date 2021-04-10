package defpackage;

import J.N;
import android.graphics.drawable.Drawable;
import java.util.Objects;
import org.chromium.base.Callback;
import org.chromium.base.ThreadUtils;
import org.chromium.chrome.browser.app.ChromeActivity;
import org.chromium.chrome.browser.autofill.PersonalDataManager;

/* renamed from: qm  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class RunnableC4457qm implements Runnable {
    public final C5647xm F;
    public final PersonalDataManager.CreditCard G;
    public final boolean H;
    public final C1870be I;

    /* renamed from: J  reason: collision with root package name */
    public final Callback f11162J;

    public RunnableC4457qm(C5647xm xmVar, PersonalDataManager.CreditCard creditCard, boolean z, C1870be beVar, Callback callback) {
        this.F = xmVar;
        this.G = creditCard;
        this.H = z;
        this.I = beVar;
        this.f11162J = callback;
    }

    public void run() {
        C5647xm xmVar = this.F;
        PersonalDataManager.CreditCard creditCard = this.G;
        boolean z = this.H;
        C1870be beVar = this.I;
        Callback callback = this.f11162J;
        creditCard.l = xmVar.t.s.toString();
        PersonalDataManager c = PersonalDataManager.c();
        if (creditCard.getIsLocal()) {
            creditCard.f = xmVar.p.s.toString().replace(" ", "").replace("-", "");
            creditCard.e = xmVar.q.s.toString();
            creditCard.h = xmVar.r.s.toString();
            creditCard.i = xmVar.s.s.toString();
            String number = creditCard.getNumber();
            Objects.requireNonNull(c);
            Object obj = ThreadUtils.f10596a;
            PersonalDataManager.CreditCard creditCard2 = (PersonalDataManager.CreditCard) N.MHzz0BSK(c.b, c, number);
            creditCard.j = creditCard2.getBasicCardIssuerNetwork();
            creditCard.g = creditCard2.g;
            creditCard.k = creditCard2.k;
            if (z) {
                C4729sK sKVar = xmVar.u;
                if (sKVar != null && NU0.f8549a.d(sKVar.s.toString(), true)) {
                    creditCard.f10614a = c.j(creditCard);
                }
            } else if (!xmVar.A) {
                c.j(creditCard);
            }
        } else if (!xmVar.A) {
            Objects.requireNonNull(c);
            Object obj2 = ThreadUtils.f10596a;
            N.MmUEbunT(c.b, c, creditCard);
        }
        String basicCardIssuerNetwork = creditCard.getBasicCardIssuerNetwork();
        if (xmVar.i.contains(basicCardIssuerNetwork)) {
            basicCardIssuerNetwork = "basic-card";
        }
        PersonalDataManager.AutofillProfile c2 = C5647xm.c(xmVar.d, creditCard.getBillingAddressId());
        beVar.m = creditCard;
        beVar.p = basicCardIssuerNetwork;
        beVar.o = c2;
        ChromeActivity J0 = ChromeActivity.J0(beVar.l);
        if (J0 != null) {
            String guid = creditCard.getGUID();
            String str = creditCard.g;
            String name = creditCard.getName();
            Drawable a2 = AbstractC5544x8.a(J0, creditCard.k);
            beVar.e(guid, str, name, null);
            beVar.h = a2;
            beVar.G(J0);
        }
        callback.onResult(beVar);
    }
}
