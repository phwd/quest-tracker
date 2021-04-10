package defpackage;

import J.N;
import android.app.ProgressDialog;
import android.os.Handler;
import com.oculus.browser.R;
import com.oculus.os.Version;
import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import org.chromium.base.Callback;
import org.chromium.base.ThreadUtils;
import org.chromium.chrome.browser.autofill.PersonalDataManager;
import org.chromium.chrome.browser.autofill.settings.AutofillProfileBridge;

/* renamed from: S3  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class S3 extends AbstractC2168dK implements PersonalDataManager.GetSubKeysRequestDelegate {
    public final Handler c = new Handler();
    public final Map d = new HashMap();
    public final Set e = new HashSet();
    public final int f;
    public final boolean g;
    public final boolean h;
    public final C4024oC0 i;
    public final R3 j;
    public AutofillProfileBridge k;
    public C4729sK l;
    public C4729sK m;
    public C4729sK n;
    public C4729sK o;
    public List p;
    public boolean q;
    public String r;
    public Runnable s;
    public PersonalDataManager.AutofillProfile t;
    public C5409wK u;
    public ProgressDialog v;
    public T3 w;

    public S3(int i2, boolean z) {
        this.f = i2;
        boolean z2 = i2 != 2;
        this.g = z2;
        this.h = z;
        this.i = new C4024oC0();
        this.j = new R3(!z2);
    }

    public static String e(CharSequence charSequence) {
        return charSequence == null ? "" : charSequence.toString();
    }

    public static void h(PersonalDataManager.AutofillProfile autofillProfile, int i2, CharSequence charSequence) {
        switch (i2) {
            case 0:
                autofillProfile.m = e(charSequence);
                return;
            case 1:
                autofillProfile.h = e(charSequence);
                return;
            case 2:
                autofillProfile.i = e(charSequence);
                return;
            case 3:
                autofillProfile.j = e(charSequence);
                return;
            case 4:
                autofillProfile.l = e(charSequence);
                return;
            case 5:
                autofillProfile.k = e(charSequence);
                return;
            case 6:
                autofillProfile.g = e(charSequence);
                return;
            case Version.VERSION_7 /*{ENCODED_INT: 7}*/:
                autofillProfile.f = e(charSequence);
                return;
            case Version.VERSION_8 /*{ENCODED_INT: 8}*/:
                autofillProfile.e = e(charSequence);
                return;
            default:
                return;
        }
    }

    public final void c(String str, String str2) {
        String str3;
        int i2;
        C4729sK sKVar;
        AutofillProfileBridge autofillProfileBridge = this.k;
        Objects.requireNonNull(autofillProfileBridge);
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        ArrayList arrayList3 = new ArrayList();
        ArrayList arrayList4 = new ArrayList();
        ArrayList arrayList5 = new ArrayList();
        autofillProfileBridge.f10615a = N.MRFNh4wI(str, str2, arrayList, arrayList2, arrayList3, arrayList4);
        int i3 = 0;
        while (true) {
            boolean z = true;
            if (i3 >= arrayList.size()) {
                break;
            }
            int intValue = ((Integer) arrayList.get(i3)).intValue();
            String str4 = (String) arrayList2.get(i3);
            boolean z2 = ((Integer) arrayList3.get(i3)).intValue() == 1;
            if (((Integer) arrayList4.get(i3)).intValue() != 1) {
                z = false;
            }
            arrayList5.add(new C3578le(intValue, str4, z2, z));
            i3++;
        }
        this.p = arrayList5;
        this.l.n = f(0);
        this.u.b.add(this.l);
        int i4 = 0;
        while (true) {
            str3 = null;
            if (i4 >= this.p.size()) {
                break;
            }
            C3578le leVar = (C3578le) this.p.get(i4);
            if (leVar.f10359a == 8 && (sKVar = this.m) != null) {
                this.u.b.add(sKVar);
            }
            C4729sK sKVar2 = (C4729sK) this.d.get(Integer.valueOf(leVar.f10359a));
            sKVar2.p = leVar.b;
            sKVar2.z = leVar.d || (i2 = leVar.f10359a) == 2 || i2 == 3;
            if (this.g && (leVar.c || leVar.f10359a == 8)) {
                str3 = this.b.getString(R.string.f59020_resource_name_obfuscated_RES_2131953219);
            }
            sKVar2.l = str3;
            sKVar2.n = f(leVar.f10359a);
            this.u.b.add(sKVar2);
            i4++;
        }
        C4729sK sKVar3 = this.n;
        T3 t3 = this.w;
        if (t3 != null) {
            str3 = t3.i;
        }
        sKVar3.n = str3;
        this.u.b.add(sKVar3);
        C4729sK sKVar4 = this.o;
        if (sKVar4 != null) {
            this.u.b.add(sKVar4);
        }
    }

    public void d(C2892hd hdVar, Callback callback) {
        String str;
        C2892hd hdVar2;
        if (this.k == null) {
            this.k = new AutofillProfileBridge();
        }
        if (hdVar == null) {
            hdVar2 = new C2892hd(this.b, new PersonalDataManager.AutofillProfile());
            str = this.b.getString(R.string.f47290_resource_name_obfuscated_RES_2131952046);
        } else {
            str = hdVar.e;
            hdVar2 = hdVar;
        }
        this.u = new C5409wK(str);
        this.t = hdVar2.m;
        String str2 = null;
        this.r = null;
        if (this.l == null) {
            String string = this.b.getString(R.string.f47550_resource_name_obfuscated_RES_2131952072);
            ArrayList arrayList = new ArrayList();
            ArrayList arrayList2 = new ArrayList();
            ArrayList arrayList3 = new ArrayList();
            N.M6KwIT3h(arrayList, arrayList2);
            for (int i2 = 0; i2 < arrayList.size(); i2++) {
                arrayList3.add(new C3749me((String) arrayList.get(i2), (CharSequence) arrayList2.get(i2)));
            }
            Collator instance = Collator.getInstance(Locale.getDefault());
            instance.setStrength(0);
            Collections.sort(arrayList3, new C3236je(instance));
            this.l = C4729sK.a(string, arrayList3, null);
        }
        C4729sK sKVar = this.l;
        sKVar.u = new Q3(this);
        sKVar.s = C2892hd.i(this.t);
        this.j.f8807a = this.l.s.toString();
        this.i.G = this.l.s.toString();
        if (this.f == 2 && N.M09VlOh_("AutofillEnableUIForHonorificPrefixesInSettings")) {
            if (this.m == null) {
                C4729sK b = C4729sK.b();
                this.m = b;
                b.p = this.b.getString(R.string.f47570_resource_name_obfuscated_RES_2131952074);
            }
            this.m.s = this.t.getHonorificPrefix();
        }
        if (this.d.isEmpty()) {
            this.d.put(2, C4729sK.b());
            this.d.put(3, C4729sK.b());
            this.d.put(7, C4729sK.b());
            this.d.put(4, new C4729sK(6));
            this.d.put(5, new C4729sK(6));
            this.d.put(6, new C4729sK(3));
            this.d.put(8, new C4729sK(4));
        }
        if (this.n == null) {
            if (this.g) {
                str2 = this.b.getString(R.string.f59020_resource_name_obfuscated_RES_2131953219);
            }
            this.n = C4729sK.c(1, this.b.getString(R.string.f47580_resource_name_obfuscated_RES_2131952075), this.e, this.i, this.j, null, str2, this.b.getString(R.string.f58740_resource_name_obfuscated_RES_2131953191), null);
        }
        this.n.s = this.t.getPhoneNumber();
        if (this.f == 2) {
            if (this.o == null) {
                this.o = C4729sK.c(2, this.b.getString(R.string.f47560_resource_name_obfuscated_RES_2131952073), null, null, null, null, null, this.b.getString(R.string.f58620_resource_name_obfuscated_RES_2131953179), null);
            }
            this.o.s = this.t.getEmailAddress();
        }
        C5409wK wKVar = this.u;
        wKVar.d = new O3(this, callback, hdVar);
        wKVar.c = new P3(this, hdVar2, callback);
        g(this.l.s.toString());
        if (this.w != null) {
            this.f9770a.h();
        }
    }

    public final String f(int i2) {
        T3 t3 = this.w;
        if (t3 == null) {
            return null;
        }
        switch (i2) {
            case 0:
                return t3.f;
            case 1:
                return t3.l;
            case 2:
                return t3.e;
            case 3:
                return t3.g;
            case 4:
                return t3.m;
            case 5:
                return t3.j;
            case 6:
                return t3.d;
            case Version.VERSION_7 /*{ENCODED_INT: 7}*/:
                return t3.h;
            case Version.VERSION_8 /*{ENCODED_INT: 8}*/:
                return t3.k;
            default:
                return null;
        }
    }

    public final void g(String str) {
        this.q = false;
        this.f9770a.a0 = false;
        if (((long) 5) * 1000 == 0) {
            this.q = true;
            this.d.put(1, new C4729sK(5));
            if (this.r != null) {
                ProgressDialog progressDialog = this.v;
                if (progressDialog != null && progressDialog.isShowing()) {
                    this.v.dismiss();
                }
                this.v = null;
                c(this.r, Locale.getDefault().getLanguage());
                this.c.post(this.s);
                return;
            }
            for (Map.Entry entry : this.d.entrySet()) {
                ((C4729sK) entry.getValue()).s = C2892hd.k(this.t, ((Integer) entry.getKey()).intValue());
            }
            c(this.l.s.toString(), this.t.getLanguageCode());
            this.f9770a.g(this.u);
            return;
        }
        PersonalDataManager c2 = PersonalDataManager.c();
        Objects.requireNonNull(c2);
        Object obj = ThreadUtils.f10596a;
        N.M4kIHYDl(c2.b, c2, str);
        PersonalDataManager c3 = PersonalDataManager.c();
        Objects.requireNonNull(c3);
        N.M8TAYWBI(c3.b, c3, str, 5, this);
    }

    @Override // org.chromium.chrome.browser.autofill.PersonalDataManager.GetSubKeysRequestDelegate
    public void onSubKeysReceived(String[] strArr, String[] strArr2) {
        C4729sK sKVar;
        if (!this.q) {
            this.q = true;
            if (!this.f9770a.a0) {
                Map map = this.d;
                if (strArr == null || strArr2 == null || strArr.length == 0 || strArr.length != strArr2.length) {
                    sKVar = new C4729sK(5);
                } else {
                    ArrayList arrayList = new ArrayList();
                    for (int i2 = 0; i2 < strArr.length; i2++) {
                        arrayList.add(new C3749me(strArr[i2], strArr2[i2]));
                    }
                    Collator instance = Collator.getInstance(Locale.getDefault());
                    instance.setStrength(0);
                    Collections.sort(arrayList, new C3407ke(instance));
                    sKVar = C4729sK.a(null, arrayList, this.b.getString(R.string.f61160_resource_name_obfuscated_RES_2131953433));
                }
                map.put(1, sKVar);
                if (this.r != null) {
                    ProgressDialog progressDialog = this.v;
                    if (progressDialog != null && progressDialog.isShowing()) {
                        this.v.dismiss();
                    }
                    this.v = null;
                    c(this.r, Locale.getDefault().getLanguage());
                    this.c.post(this.s);
                    return;
                }
                for (Map.Entry entry : this.d.entrySet()) {
                    ((C4729sK) entry.getValue()).s = C2892hd.k(this.t, ((Integer) entry.getKey()).intValue());
                }
                c(this.l.s.toString(), this.t.getLanguageCode());
                this.f9770a.g(this.u);
            }
        }
    }
}
