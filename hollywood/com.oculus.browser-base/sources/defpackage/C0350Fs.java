package defpackage;

import J.N;
import java.util.Objects;
import org.chromium.base.ThreadUtils;
import org.chromium.chrome.browser.autofill.PersonalDataManager;
import org.chromium.components.payments.PayerData;
import org.chromium.components.payments.PaymentApp;

/* renamed from: Fs  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C0350Fs implements PersonalDataManager.NormalizedAddressRequestDelegate, AbstractC3166jB0 {

    /* renamed from: a  reason: collision with root package name */
    public final C5960zd f8043a;
    public final PaymentApp b;
    public final C1523Yz0 c;
    public final boolean d;
    public final C2825hB0 e;
    public C2892hd f;
    public AbstractC2996iB0 g;
    public boolean h;
    public boolean i = true;
    public PayerData j;

    public C0350Fs(C1997cK cKVar, C1997cK cKVar2, C5960zd zdVar, PaymentApp paymentApp, C1523Yz0 yz0, boolean z) {
        C2825hB0 hb0 = new C2825hB0();
        this.e = hb0;
        hb0.h = new C0911Oy0();
        this.b = paymentApp;
        this.c = yz0;
        this.d = z;
        this.f8043a = zdVar;
        if (yz0.g && !paymentApp.v() && !z) {
            hb0.g = cKVar2.g;
        }
        if (yz0.g && !paymentApp.v() && !z) {
            this.f = (C2892hd) cKVar;
            PersonalDataManager c2 = PersonalDataManager.c();
            String guid = this.f.m.getGUID();
            Objects.requireNonNull(c2);
            Object obj = ThreadUtils.f10596a;
            N.MT65YYp8(c2.b, c2, guid);
            hb0.f = this.f.o();
            this.h = true;
            PersonalDataManager.c().i(this.f.m, this);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:87:0x01d9, code lost:
        if (r2 != false) goto L_0x01db;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void a() {
        /*
        // Method dump skipped, instructions count: 504
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.C0350Fs.a():void");
    }

    @Override // org.chromium.chrome.browser.autofill.PersonalDataManager.NormalizedAddressRequestDelegate
    public void onAddressNormalized(PersonalDataManager.AutofillProfile autofillProfile) {
        if (this.h) {
            this.h = false;
            if (autofillProfile != null) {
                this.f.p(autofillProfile);
                this.e.f = this.f.o();
            }
            if (!this.i) {
                a();
            }
        }
    }

    @Override // org.chromium.chrome.browser.autofill.PersonalDataManager.NormalizedAddressRequestDelegate
    public void onCouldNotNormalize(PersonalDataManager.AutofillProfile autofillProfile) {
        if (this.h) {
            this.h = false;
            if (autofillProfile != null) {
                this.f.p(autofillProfile);
                this.e.f = this.f.o();
            }
            if (!this.i) {
                a();
            }
        }
    }
}
