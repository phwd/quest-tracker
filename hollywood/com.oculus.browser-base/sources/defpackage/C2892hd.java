package defpackage;

import J.N;
import android.content.Context;
import android.telephony.PhoneNumberUtils;
import android.text.TextUtils;
import android.util.Pair;
import com.oculus.browser.R;
import com.oculus.os.Version;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Locale;
import java.util.regex.Pattern;
import org.chromium.chrome.browser.autofill.PersonalDataManager;

/* renamed from: hd  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C2892hd extends C1997cK {
    public static Pattern k;
    public Context l;
    public PersonalDataManager.AutofillProfile m;
    public String n;
    public String o;
    public String p;

    public C2892hd(Context context, PersonalDataManager.AutofillProfile autofillProfile) {
        super(autofillProfile.getGUID(), autofillProfile.getFullName(), autofillProfile.p, autofillProfile.getPhoneNumber(), null);
        this.l = context;
        this.m = autofillProfile;
        this.c = true;
        h();
    }

    public static int g(PersonalDataManager.AutofillProfile autofillProfile, int i) {
        boolean isEmpty = TextUtils.isEmpty(autofillProfile.getFullName());
        P21 f0 = P21.f0();
        if (i != 1) {
            try {
                if (!PhoneNumberUtils.isGlobalPhoneNumber(PhoneNumberUtils.stripSeparators(autofillProfile.getPhoneNumber().toString()))) {
                    isEmpty = (isEmpty ? 1 : 0) | true;
                }
            } catch (Throwable th) {
                AbstractC0754Mh1.f8495a.a(th, th);
            }
        }
        f0.close();
        String i2 = i(autofillProfile);
        ArrayList arrayList = new ArrayList();
        N.MdAohWFG(i2, arrayList);
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            int intValue = ((Integer) it.next()).intValue();
            if (intValue != 8 && intValue != 0 && TextUtils.isEmpty(k(autofillProfile, intValue))) {
                int i3 = isEmpty ? 1 : 0;
                char c = isEmpty ? 1 : 0;
                return i3 | 8;
            }
        }
        int i4 = isEmpty ? 1 : 0;
        int i5 = isEmpty ? 1 : 0;
        int i6 = isEmpty ? 1 : 0;
        return i4;
        throw th;
    }

    public static String i(PersonalDataManager.AutofillProfile autofillProfile) {
        if (k == null) {
            k = Pattern.compile("^[A-Z]{2}$");
        }
        return (autofillProfile == null || TextUtils.isEmpty(autofillProfile.getCountryCode()) || !k.matcher(autofillProfile.getCountryCode()).matches()) ? Locale.getDefault().getCountry() : autofillProfile.getCountryCode();
    }

    public static Pair j(int i) {
        int i2;
        int i3;
        if (i == 0) {
            i3 = R.string.f58580_resource_name_obfuscated_RES_2131953175;
            i2 = 0;
        } else if (i == 1) {
            i2 = R.string.f58800_resource_name_obfuscated_RES_2131953197;
            i3 = R.string.f58410_resource_name_obfuscated_RES_2131953158;
        } else if (i == 2) {
            i2 = R.string.f58750_resource_name_obfuscated_RES_2131953192;
            i3 = R.string.f58400_resource_name_obfuscated_RES_2131953157;
        } else if (i != 8) {
            i2 = R.string.f58680_resource_name_obfuscated_RES_2131953185;
            i3 = R.string.f58370_resource_name_obfuscated_RES_2131953154;
        } else {
            i2 = R.string.f58650_resource_name_obfuscated_RES_2131953182;
            i3 = R.string.f58420_resource_name_obfuscated_RES_2131953159;
        }
        return new Pair(Integer.valueOf(i2), Integer.valueOf(i3));
    }

    public static String k(PersonalDataManager.AutofillProfile autofillProfile, int i) {
        switch (i) {
            case 0:
                return autofillProfile.getCountryCode();
            case 1:
                return autofillProfile.getRegion();
            case 2:
                return autofillProfile.getLocality();
            case 3:
                return autofillProfile.getDependentLocality();
            case 4:
                return autofillProfile.getSortingCode();
            case 5:
                return autofillProfile.getPostalCode();
            case 6:
                return autofillProfile.getStreetAddress();
            case Version.VERSION_7:
                return autofillProfile.getCompanyName();
            case Version.VERSION_8:
                return autofillProfile.getFullName();
            default:
                return null;
        }
    }

    public final void h() {
        String str;
        int i = 0;
        Pair j = j(g(this.m, 0));
        String str2 = null;
        if (((Integer) j.first).intValue() == 0) {
            str = null;
        } else {
            str = this.l.getString(((Integer) j.first).intValue());
        }
        this.d = str;
        if (((Integer) j.second).intValue() != 0) {
            str2 = this.l.getString(((Integer) j.second).intValue());
        }
        this.e = str2;
        this.f9599a = this.d == null;
        int g = g(this.m, 0);
        if ((g & 1) == 0) {
            i = 1;
        }
        if ((g & 2) == 0) {
            i++;
        }
        if ((g & 8) == 0) {
            i += 10;
        }
        this.b = i;
    }

    public void l() {
        if (this.p == null) {
            PersonalDataManager c = PersonalDataManager.c();
            this.p = N.My71lPBY(c.b, c, this.m);
        }
        PersonalDataManager.AutofillProfile autofillProfile = this.m;
        String str = this.p;
        autofillProfile.p = str;
        f(str);
    }

    public void m() {
        if (this.n == null) {
            PersonalDataManager c = PersonalDataManager.c();
            this.n = N.MGJNOClH(c.b, c, this.m);
        }
        PersonalDataManager.AutofillProfile autofillProfile = this.m;
        String str = this.n;
        autofillProfile.p = str;
        f(str);
    }

    public void n() {
        if (this.o == null) {
            PersonalDataManager c = PersonalDataManager.c();
            this.o = N.MGlLlw0K(c.b, c, this.m);
        }
        PersonalDataManager.AutofillProfile autofillProfile = this.m;
        String str = this.o;
        autofillProfile.p = str;
        f(str);
    }

    public C1033Qy0 o() {
        C1033Qy0 qy0 = new C1033Qy0();
        qy0.d = i(this.m);
        qy0.e = this.m.getStreetAddress().split("\n");
        qy0.f = this.m.getRegion();
        qy0.g = this.m.getLocality();
        qy0.h = this.m.getDependentLocality();
        qy0.i = this.m.getPostalCode();
        qy0.j = this.m.getSortingCode();
        qy0.k = this.m.getCompanyName();
        qy0.l = this.m.getFullName();
        qy0.m = this.m.getPhoneNumber();
        return qy0;
    }

    public void p(PersonalDataManager.AutofillProfile autofillProfile) {
        this.n = null;
        this.o = null;
        this.p = null;
        this.m = autofillProfile;
        String guid = autofillProfile.getGUID();
        String fullName = this.m.getFullName();
        PersonalDataManager.AutofillProfile autofillProfile2 = this.m;
        e(guid, fullName, autofillProfile2.p, autofillProfile2.getPhoneNumber());
        h();
    }
}
