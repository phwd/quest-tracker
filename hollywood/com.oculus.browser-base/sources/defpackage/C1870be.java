package defpackage;

import J.N;
import android.content.Context;
import android.text.TextUtils;
import com.oculus.browser.R;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.chromium.chrome.browser.app.ChromeActivity;
import org.chromium.chrome.browser.autofill.PersonalDataManager;
import org.chromium.components.payments.PaymentApp;
import org.chromium.content_public.browser.WebContents;

/* renamed from: be  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C1870be extends PaymentApp implements PersonalDataManager.FullCardRequestDelegate, PersonalDataManager.NormalizedAddressRequestDelegate {
    public final WebContents l;
    public PersonalDataManager.CreditCard m;
    public String n;
    public PersonalDataManager.AutofillProfile o;
    public String p;
    public AbstractC1277Uy0 q;
    public boolean r;
    public boolean s;
    public boolean t;

    public C1870be(WebContents webContents, PersonalDataManager.CreditCard creditCard, PersonalDataManager.AutofillProfile autofillProfile, String str) {
        super(creditCard.getGUID(), creditCard.g, creditCard.getName(), null);
        this.l = webContents;
        this.m = creditCard;
        this.o = autofillProfile;
        this.c = true;
        this.p = str;
        ChromeActivity J0 = ChromeActivity.J0(webContents);
        if (J0 != null) {
            int i = creditCard.k;
            if (i != 0) {
                this.h = AbstractC5544x8.a(J0, i);
            }
            G(J0);
        }
    }

    @Override // org.chromium.components.payments.PaymentApp
    public boolean B(String str, C1401Wz0 wz0) {
        if (!((HashSet) p()).contains(str)) {
            return false;
        }
        int[] iArr = wz0.j;
        if ((iArr == null || iArr.length == 0) ? false : true) {
            if (!((HashSet) AbstractC1054Rg.a(wz0)).contains(this.m.getBasicCardIssuerNetwork())) {
                return false;
            }
        }
        return true;
    }

    public final void G(Context context) {
        int i;
        int i2;
        int i3;
        String str;
        boolean z = false;
        this.b = 0;
        int H = H();
        if ((H & 1) == 0) {
            this.b += 6;
        }
        if ((H & 8) == 0) {
            this.b += 10;
            i = 0;
            i2 = R.string.f58600_resource_name_obfuscated_RES_2131953177;
            i3 = 0;
        } else {
            i3 = R.string.f58450_resource_name_obfuscated_RES_2131953162;
            i2 = R.string.f58320_resource_name_obfuscated_RES_2131953149;
            i = 1;
        }
        this.t = true;
        if ((H & 2) == 0) {
            this.b += 8;
        } else {
            this.t = false;
            i3 = R.string.f58700_resource_name_obfuscated_RES_2131953187;
            i2 = R.string.f58390_resource_name_obfuscated_RES_2131953156;
            i++;
        }
        if ((H & 4) == 0) {
            this.b += 13;
        } else {
            this.t = false;
            i3 = R.string.f58500_resource_name_obfuscated_RES_2131953167;
            i2 = R.string.f58430_resource_name_obfuscated_RES_2131953160;
            i++;
        }
        if (i > 1) {
            i3 = R.string.f58680_resource_name_obfuscated_RES_2131953185;
            i2 = R.string.f58370_resource_name_obfuscated_RES_2131953154;
        }
        if (i3 == 0) {
            str = null;
        } else {
            str = context.getString(i3);
        }
        this.d = str;
        this.e = context.getString(i2);
        if (this.d == null) {
            z = true;
        }
        this.f9599a = z;
    }

    public int H() {
        boolean z = false;
        int i = this.o == null ? 8 : 0;
        PersonalDataManager.CreditCard creditCard = this.m;
        if (!creditCard.h.isEmpty() && !creditCard.i.isEmpty()) {
            Calendar instance = Calendar.getInstance();
            instance.set(2, Integer.parseInt(creditCard.h) - 1);
            instance.set(1, Integer.parseInt(creditCard.i));
            instance.add(12, 1);
            z = Calendar.getInstance().before(instance);
        }
        if (!z) {
            i |= 1;
        }
        if (!this.m.getIsLocal()) {
            return i;
        }
        if (TextUtils.isEmpty(this.m.getName())) {
            i |= 2;
        }
        return PersonalDataManager.c().a(this.m.getNumber().toString(), true) == null ? i | 4 : i;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:44:0x015e, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:46:?, code lost:
        ((defpackage.EA0) r7.q).z("User closed the Payment Request UI.");
        r7.q = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:48:0x016d, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:49:0x016e, code lost:
        r7.n = "";
     */
    /* JADX WARNING: Code restructure failed: missing block: B:50:0x0170, code lost:
        throw r1;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:45:0x0160 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void I() {
        /*
        // Method dump skipped, instructions count: 369
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.C1870be.I():void");
    }

    @Override // defpackage.C1997cK
    public String b(String str, int i) {
        StringBuilder sb = new StringBuilder(a());
        if (i < 0) {
            return sb.toString();
        }
        int indexOf = sb.indexOf(" ");
        if (indexOf > 0) {
            sb.delete(indexOf, sb.length());
        }
        if (sb.length() < i) {
            return sb.toString();
        }
        return sb.substring(0, i / 2);
    }

    @Override // defpackage.C1997cK
    public boolean d() {
        return this.f9599a;
    }

    @Override // org.chromium.components.payments.PaymentApp
    public boolean i() {
        if (AbstractC3984nz0.a("StrictHasEnrolledAutofillInstrument")) {
            return H() == 0 && this.k;
        }
        return this.t;
    }

    @Override // org.chromium.components.payments.PaymentApp
    public boolean j() {
        return this.f9599a;
    }

    @Override // org.chromium.components.payments.PaymentApp
    public void l() {
    }

    @Override // org.chromium.components.payments.PaymentApp
    public String o() {
        return C2892hd.i(this.o);
    }

    @Override // org.chromium.chrome.browser.autofill.PersonalDataManager.NormalizedAddressRequestDelegate
    public void onAddressNormalized(PersonalDataManager.AutofillProfile autofillProfile) {
        if (this.r) {
            this.r = false;
            if (autofillProfile != null) {
                this.o = autofillProfile;
            }
            if (!this.s) {
                I();
            }
        }
    }

    @Override // org.chromium.chrome.browser.autofill.PersonalDataManager.NormalizedAddressRequestDelegate
    public void onCouldNotNormalize(PersonalDataManager.AutofillProfile autofillProfile) {
        if (this.r) {
            this.r = false;
            if (!this.s) {
                I();
            }
        }
    }

    @Override // org.chromium.chrome.browser.autofill.PersonalDataManager.FullCardRequestDelegate
    public void onFullCardDetails(PersonalDataManager.CreditCard creditCard, String str) {
        AbstractC3254jk jkVar;
        TA0 ta0;
        this.n = str;
        this.m.f = creditCard.getNumber();
        this.m.h = creditCard.getMonth();
        this.m.i = creditCard.getYear();
        this.s = false;
        EA0 ea0 = (EA0) this.q;
        if (!(ea0.C == null || (jkVar = ea0.B) == null || (ta0 = ((C0289Es) jkVar).f.w) == null)) {
            ta0.f(true);
            ta0.K.a();
        }
        if (!this.r) {
            I();
        }
    }

    @Override // org.chromium.chrome.browser.autofill.PersonalDataManager.FullCardRequestDelegate
    public void onFullCardError() {
        ((EA0) this.q).z("User closed the Payment Request UI.");
        this.q = null;
    }

    @Override // org.chromium.components.payments.PaymentApp
    public Set p() {
        HashSet hashSet = new HashSet();
        hashSet.add(this.p);
        return hashSet;
    }

    @Override // org.chromium.components.payments.PaymentApp
    public int q() {
        return 1;
    }

    @Override // org.chromium.components.payments.PaymentApp
    public void w(String str, String str2, String str3, String str4, byte[][] bArr, Map map, C1035Qz0 qz0, List list, Map map2, C1523Yz0 yz0, List list2, AbstractC1277Uy0 uy0) {
        this.q = uy0;
        this.r = true;
        this.s = true;
        PersonalDataManager.c().i(this.o, this);
        PersonalDataManager c = PersonalDataManager.c();
        N.M_6hrjIQ(c.b, c, this.l, this.m, this);
    }

    @Override // org.chromium.components.payments.PaymentApp
    public boolean z() {
        return !this.m.getIsLocal();
    }
}
