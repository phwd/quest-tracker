package defpackage;

import org.chromium.chrome.browser.autofill.PersonalDataManager;
import org.chromium.components.payments.PaymentApp;

/* renamed from: aT0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class RunnableC1665aT0 implements Runnable {
    public final /* synthetic */ AbstractC2016cT0 F;
    public final /* synthetic */ PersonalDataManager.CreditCard G;

    public RunnableC1665aT0(C2187dT0 dt0, AbstractC2016cT0 ct0, PersonalDataManager.CreditCard creditCard) {
        this.F = ct0;
        this.G = creditCard;
    }

    public void run() {
        AbstractC1289Vd vd;
        PaymentApp b;
        AbstractC2016cT0 ct0 = this.F;
        PersonalDataManager.CreditCard creditCard = this.G;
        AB0 ab0 = (AB0) ct0;
        if (ab0.y && ab0.B != null && (vd = ab0.E) != null && (b = ((C1533Zd) vd).b(creditCard)) != null) {
            ab0.B.a(b);
            ab0.s();
            TA0 ta0 = ab0.w;
            if (ta0 != null) {
                ta0.o(4, ab0.B);
            }
        }
    }
}
