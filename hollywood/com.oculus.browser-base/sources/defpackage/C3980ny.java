package defpackage;

import android.content.Context;
import android.text.TextUtils;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import org.chromium.chrome.browser.autofill.PersonalDataManager;

/* renamed from: ny  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C3980ny extends C5084uR0 {
    public final Context f;
    public final C4663ry g;
    public final List h;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public C3980ny(Context context, Collection collection, C4663ry ryVar, H40 h40) {
        super(3, null);
        boolean z;
        String str;
        String str2;
        String str3;
        String str4;
        String str5;
        String str6;
        C5960zd zdVar = null;
        this.f = context;
        this.g = ryVar;
        this.h = new ArrayList(collection);
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        int i = 0;
        for (int i2 = 0; i2 < this.h.size(); i2++) {
            C5960zd j = j((PersonalDataManager.AutofillProfile) this.h.get(i2));
            if (j != null) {
                C4663ry ryVar2 = this.g;
                String str7 = j.q;
                Objects.requireNonNull(ryVar2);
                if (!TextUtils.isEmpty(str7)) {
                    ryVar2.g.add(str7);
                }
                C4663ry ryVar3 = this.g;
                String str8 = j.r;
                if (ryVar3.e().a(str8)) {
                    ryVar3.h.add(str8);
                }
                C4663ry ryVar4 = this.g;
                String str9 = j.s;
                if (ryVar4.d().a(str9)) {
                    ryVar4.i.add(str9);
                }
                arrayList.add(j);
            }
        }
        Collections.sort(arrayList, new C3809my(this));
        for (int i3 = 0; i3 < arrayList.size(); i3++) {
            C5960zd zdVar2 = (C5960zd) arrayList.get(i3);
            int i4 = 0;
            while (true) {
                if (i4 >= arrayList2.size()) {
                    z = true;
                    break;
                }
                C5960zd zdVar3 = (C5960zd) arrayList2.get(i4);
                if ((!zdVar3.n || (((str5 = zdVar3.q) != null || zdVar2.q == null) && (str5 == null || (str6 = zdVar2.q) == null || str5.equalsIgnoreCase(str6)))) && (!zdVar3.o || (((str3 = zdVar3.r) != null || zdVar2.r == null) && (str3 == null || (str4 = zdVar2.r) == null || TextUtils.equals(str3, str4)))) && (!zdVar3.p || (((str = zdVar3.s) != null || zdVar2.s == null) && (str == null || (str2 = zdVar2.s) == null || str.equalsIgnoreCase(str2))))) {
                    z = false;
                    break;
                }
                i4++;
            }
            if (z) {
                arrayList2.add(zdVar2);
            }
            if (arrayList2.size() == 4) {
                break;
            }
        }
        int i5 = (arrayList2.isEmpty() || !((C5960zd) arrayList2.get(0)).f9599a) ? -1 : 0;
        if (h40 != null) {
            h40.b(0, arrayList2.size(), i5 != -1);
        }
        zdVar = !arrayList2.isEmpty() ? (C5960zd) arrayList2.get(0) : zdVar;
        if (this.g.c && (zdVar == null || TextUtils.isEmpty(zdVar.q))) {
            i = 1;
        }
        if (this.g.d && (zdVar == null || TextUtils.isEmpty(zdVar.r))) {
            i |= 2;
        }
        if (this.g.e && (zdVar == null || TextUtils.isEmpty(zdVar.s))) {
            i |= 4;
        }
        if (i != 0) {
            AbstractC3100ip1.f10165a.d("PaymentRequest.MissingContactFields", i);
        }
        h(i5, arrayList2);
    }

    public void i(C2892hd hdVar) {
        C5960zd j = j(hdVar.m);
        if (j != null) {
            for (int i = 0; i < this.f11410a.size(); i++) {
                if (((C5960zd) this.f11410a.get(i)).k.getGUID().equals(hdVar.m.getGUID())) {
                    this.f11410a.remove(i);
                    this.f11410a.add(i, j);
                    return;
                }
            }
            this.f11410a.add(j);
        }
    }

    public final C5960zd j(PersonalDataManager.AutofillProfile autofillProfile) {
        C4663ry ryVar = this.g;
        boolean z = ryVar.c;
        boolean z2 = ryVar.d;
        boolean z3 = ryVar.e;
        String fullName = (!z || TextUtils.isEmpty(autofillProfile.getFullName())) ? null : autofillProfile.getFullName();
        String phoneNumber = (!z2 || TextUtils.isEmpty(autofillProfile.getPhoneNumber())) ? null : autofillProfile.getPhoneNumber();
        String emailAddress = (!z3 || TextUtils.isEmpty(autofillProfile.getEmailAddress())) ? null : autofillProfile.getEmailAddress();
        if (fullName == null && phoneNumber == null && emailAddress == null) {
            return null;
        }
        return new C5960zd(this.f, autofillProfile, fullName, phoneNumber, emailAddress, this.g.c(fullName, phoneNumber, emailAddress), z, z2, z3);
    }
}
