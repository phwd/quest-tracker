package defpackage;

import android.content.Context;
import android.text.TextUtils;
import com.oculus.browser.R;
import org.chromium.chrome.browser.autofill.PersonalDataManager;

/* renamed from: zd  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C5960zd extends C1997cK {
    public final PersonalDataManager.AutofillProfile k;
    public final Context l;
    public int m;
    public boolean n;
    public boolean o;
    public boolean p;
    public String q;
    public String r;
    public String s;

    public C5960zd(Context context, PersonalDataManager.AutofillProfile autofillProfile, String str, String str2, String str3, int i, boolean z, boolean z2, boolean z3) {
        super(autofillProfile.getGUID(), null, null, null, null);
        this.l = context;
        this.k = autofillProfile;
        this.n = z;
        this.o = z2;
        this.p = z3;
        this.c = true;
        h(autofillProfile.getGUID(), str, str2, str3);
        i(i);
    }

    @Override // defpackage.C1997cK
    public boolean d() {
        return this.f9599a;
    }

    public int g() {
        int i = 1;
        if (!this.n || (this.m & 1) != 0) {
            i = 0;
        }
        if (this.o && (this.m & 2) == 0) {
            i++;
        }
        return (!this.p || (this.m & 4) != 0) ? i : i + 1;
    }

    public final void h(String str, String str2, String str3, String str4) {
        String str5 = null;
        if (TextUtils.isEmpty(str2)) {
            str2 = null;
        }
        this.q = str2;
        if (TextUtils.isEmpty(str3)) {
            str3 = null;
        }
        this.r = str3;
        if (TextUtils.isEmpty(str4)) {
            str4 = null;
        }
        this.s = str4;
        String str6 = this.q;
        if (str6 == null) {
            String str7 = this.r;
            String str8 = str7 == null ? str4 : str7;
            if (str7 == null) {
                str4 = null;
            }
            e(str, str8, str4, null);
            return;
        }
        String str9 = this.r;
        String str10 = str9 == null ? str4 : str9;
        if (str9 != null) {
            str5 = str4;
        }
        e(str, str6, str10, str5);
    }

    public final void i(int i) {
        this.m = i;
        this.f9599a = i == 0;
        if (i == 0) {
            this.d = null;
            this.e = this.l.getString(R.string.f58610_resource_name_obfuscated_RES_2131953178);
        } else if (i == 1) {
            this.d = this.l.getString(R.string.f58710_resource_name_obfuscated_RES_2131953188);
            this.e = this.l.getString(R.string.f58380_resource_name_obfuscated_RES_2131953155);
        } else if (i == 2) {
            this.d = this.l.getString(R.string.f58750_resource_name_obfuscated_RES_2131953192);
            this.e = this.l.getString(R.string.f58400_resource_name_obfuscated_RES_2131953157);
        } else if (i != 4) {
            this.d = this.l.getString(R.string.f58680_resource_name_obfuscated_RES_2131953185);
            this.e = this.l.getString(R.string.f58370_resource_name_obfuscated_RES_2131953154);
        } else {
            this.d = this.l.getString(R.string.f58630_resource_name_obfuscated_RES_2131953180);
            this.e = this.l.getString(R.string.f58360_resource_name_obfuscated_RES_2131953153);
        }
    }
}
