package org.chromium.chrome.browser.autofill;

import J.N;
import android.content.Context;
import com.oculus.browser.R;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import org.chromium.base.ThreadUtils;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.components.prefs.PrefService;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class PersonalDataManager {

    /* renamed from: a  reason: collision with root package name */
    public static PersonalDataManager f10612a;
    public final long b = N.MQw_0Q1A(this);
    public final List c = new ArrayList();

    /* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
    public class CreditCard {

        /* renamed from: a  reason: collision with root package name */
        public String f10614a;
        public String b;
        public boolean c;
        public boolean d;
        public String e;
        public String f;
        public String g;
        public String h;
        public String i;
        public String j;
        public int k;
        public String l;
        public String m;
        public String n;
        public String o;

        public CreditCard(String str, String str2, boolean z, boolean z2, String str3, String str4, String str5, String str6, String str7, String str8, int i2, String str9, String str10, String str11, String str12) {
            this.f10614a = str;
            this.b = str2;
            this.c = z;
            this.d = z2;
            this.e = str3;
            this.f = str4;
            this.g = str5;
            this.h = str6;
            this.i = str7;
            this.j = str8;
            this.k = i2;
            this.l = str9;
            this.m = str10;
            this.n = str11;
            this.o = str12;
        }

        public static CreditCard create(String str, String str2, boolean z, boolean z2, String str3, String str4, String str5, String str6, String str7, String str8, int i2, String str9, String str10, String str11, String str12) {
            return new CreditCard(str, str2, z, z2, str3, str4, str5, str6, str7, str8, i2, str9, str10, str11, str12);
        }

        public String a(Context context) {
            return getMonth() + context.getResources().getString(R.string.f47440_resource_name_obfuscated_RES_2131952061) + getYear();
        }

        public String getBasicCardIssuerNetwork() {
            return this.j;
        }

        public String getBillingAddressId() {
            return this.l;
        }

        public String getGUID() {
            return this.f10614a;
        }

        public boolean getIsCached() {
            return this.d;
        }

        public boolean getIsLocal() {
            return this.c;
        }

        public String getMonth() {
            return this.h;
        }

        public String getName() {
            return this.e;
        }

        public String getNickname() {
            return this.o;
        }

        public String getNumber() {
            return this.f;
        }

        public String getOrigin() {
            return this.b;
        }

        public String getServerId() {
            return this.m;
        }

        public String getYear() {
            return this.i;
        }
    }

    /* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
    public interface FullCardRequestDelegate {
        void onFullCardDetails(CreditCard creditCard, String str);

        void onFullCardError();
    }

    /* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
    public interface GetSubKeysRequestDelegate {
        void onSubKeysReceived(String[] strArr, String[] strArr2);
    }

    /* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
    public interface NormalizedAddressRequestDelegate {
        void onAddressNormalized(AutofillProfile autofillProfile);

        void onCouldNotNormalize(AutofillProfile autofillProfile);
    }

    public static PersonalDataManager c() {
        Object obj = ThreadUtils.f10596a;
        if (f10612a == null) {
            f10612a = new PersonalDataManager();
        }
        return f10612a;
    }

    public static PrefService d() {
        return Wr1.a(Profile.b());
    }

    public static boolean g() {
        return N.MzIXnlkD(d().f10883a, "autofill.credit_card_enabled");
    }

    public static boolean h() {
        return N.MzIXnlkD(d().f10883a, "autofill.profile_enabled");
    }

    public String a(String str, boolean z) {
        Object obj = ThreadUtils.f10596a;
        return N.My_CbjBa(this.b, this, str, z);
    }

    public final ArrayList b(String[] strArr) {
        ArrayList arrayList = new ArrayList(strArr.length);
        for (String str : strArr) {
            arrayList.add((CreditCard) N.M3g2doJx(this.b, this, str));
        }
        return arrayList;
    }

    public AutofillProfile e(String str) {
        Object obj = ThreadUtils.f10596a;
        return (AutofillProfile) N.M172IO7Q(this.b, this, str);
    }

    public final ArrayList f(String[] strArr, String[] strArr2) {
        ArrayList arrayList = new ArrayList(strArr2.length);
        for (int i = 0; i < strArr2.length; i++) {
            AutofillProfile autofillProfile = (AutofillProfile) N.M172IO7Q(this.b, this, strArr2[i]);
            autofillProfile.p = strArr[i];
            arrayList.add(autofillProfile);
        }
        return arrayList;
    }

    public void i(AutofillProfile autofillProfile, NormalizedAddressRequestDelegate normalizedAddressRequestDelegate) {
        Object obj = ThreadUtils.f10596a;
        N.M7ysHTTC(this.b, this, autofillProfile, 5, normalizedAddressRequestDelegate);
    }

    public String j(CreditCard creditCard) {
        Object obj = ThreadUtils.f10596a;
        return N.M7sdleUt(this.b, this, creditCard);
    }

    public final void personalDataChanged() {
        Object obj = ThreadUtils.f10596a;
        for (AbstractC3853nC0 nc0 : this.c) {
            nc0.p();
        }
    }

    /* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
    public class AutofillProfile {

        /* renamed from: a  reason: collision with root package name */
        public String f10613a;
        public String b;
        public boolean c;
        public String d;
        public String e;
        public String f;
        public String g;
        public String h;
        public String i;
        public String j;
        public String k;
        public String l;
        public String m;
        public String n;
        public String o;
        public String p;
        public String q;

        public AutofillProfile(String str, String str2, boolean z, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10, String str11, String str12, String str13, String str14, String str15) {
            this.f10613a = str;
            this.b = str2;
            this.c = z;
            this.d = str3;
            this.e = str4;
            this.f = str5;
            this.g = str6;
            this.h = str7;
            this.i = str8;
            this.j = str9;
            this.k = str10;
            this.l = str11;
            this.m = str12;
            this.n = str13;
            this.o = str14;
            this.q = str15;
        }

        public static AutofillProfile create(String str, String str2, boolean z, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10, String str11, String str12, String str13, String str14, String str15) {
            return new AutofillProfile(str, str2, z, str3, str4, str5, str6, str7, str8, str9, str10, str11, str12, str13, str14, str15);
        }

        public String getCompanyName() {
            return this.f;
        }

        public String getCountryCode() {
            return this.m;
        }

        public String getDependentLocality() {
            return this.j;
        }

        public String getEmailAddress() {
            return this.o;
        }

        public String getFullName() {
            return this.e;
        }

        public String getGUID() {
            return this.f10613a;
        }

        public String getHonorificPrefix() {
            return this.d;
        }

        public String getLanguageCode() {
            return this.q;
        }

        public String getLocality() {
            return this.i;
        }

        public String getOrigin() {
            return this.b;
        }

        public String getPhoneNumber() {
            return this.n;
        }

        public String getPostalCode() {
            return this.k;
        }

        public String getRegion() {
            return this.h;
        }

        public String getSortingCode() {
            return this.l;
        }

        public String getStreetAddress() {
            return this.g;
        }

        public String toString() {
            return this.p;
        }

        public AutofillProfile() {
            this("", "Chrome settings", true, "", "", "", "", "", "", "", "", "", Locale.getDefault().getCountry(), "", "", "");
        }

        public AutofillProfile(AutofillProfile autofillProfile) {
            this.f10613a = autofillProfile.getGUID();
            this.b = autofillProfile.getOrigin();
            this.c = autofillProfile.c;
            this.d = autofillProfile.getHonorificPrefix();
            this.e = autofillProfile.getFullName();
            this.f = autofillProfile.getCompanyName();
            this.g = autofillProfile.getStreetAddress();
            this.h = autofillProfile.getRegion();
            this.i = autofillProfile.getLocality();
            this.j = autofillProfile.getDependentLocality();
            this.k = autofillProfile.getPostalCode();
            this.l = autofillProfile.getSortingCode();
            this.m = autofillProfile.getCountryCode();
            this.n = autofillProfile.getPhoneNumber();
            this.o = autofillProfile.getEmailAddress();
            this.q = autofillProfile.getLanguageCode();
            this.p = autofillProfile.p;
        }
    }
}
