package org.chromium.chrome.browser.omnibox.suggestions;

import J.N;
import android.text.TextUtils;
import android.util.Base64;
import android.util.SparseArray;
import java.util.ArrayList;
import java.util.List;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.components.omnibox.AutocompleteMatch;
import org.chromium.components.omnibox.AutocompleteResult;
import org.chromium.url.GURL;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class AutocompleteController {

    /* renamed from: a  reason: collision with root package name */
    public long f10726a;
    public long b;
    public AbstractC4597rc c;
    public final Uv1 d = new Uv1();
    public boolean e;
    public boolean f;
    public boolean g;

    public static boolean isEquivalentOmniboxSuggestion(AutocompleteMatch autocompleteMatch, int i) {
        return autocompleteMatch.hashCode() == i;
    }

    public void a(Profile profile, String str, int i, String str2, int i2, boolean z, String str3, boolean z2) {
        Object[] objArr = new Object[2];
        objArr[0] = Boolean.valueOf(profile == null);
        objArr[1] = Boolean.valueOf(TextUtils.isEmpty(str));
        AbstractC1220Ua0.f("Autocomplete", "starting autocomplete controller..[%b][%b]", objArr);
        if (profile != null && !TextUtils.isEmpty(str)) {
            long MHKRbGMP = N.MHKRbGMP(this, profile);
            this.f10726a = MHKRbGMP;
            if (MHKRbGMP != 0) {
                N.Mc4QrncX(MHKRbGMP, this, str2, i2, null, str, i, z, false, false, true, str3, z2);
                this.g = false;
            }
        }
    }

    public void b(boolean z) {
        if (z) {
            this.d.f9054a.clear();
        }
        this.b = 0;
        this.g = false;
        if (this.f10726a != 0) {
            AbstractC1220Ua0.f("Autocomplete", "stopping autocomplete.", new Object[0]);
            N.MktNJvjP(this.f10726a, this, z);
        }
    }

    public GURL c(int i, int i2, long j, String str, List list) {
        String[] strArr;
        long j2 = this.f10726a;
        if (list == null) {
            strArr = null;
        } else {
            strArr = (String[]) list.toArray(new String[list.size()]);
        }
        return (GURL) N.M$ADdPWO(j2, this, i, i2, j, str, strArr);
    }

    public final void notifyNativeDestroyed() {
        this.f10726a = 0;
    }

    public void onSuggestionsReceived(AutocompleteResult autocompleteResult, String str, long j) {
        AutocompleteResult autocompleteResult2;
        String str2;
        int i;
        if (!this.f) {
            Uv1 uv1 = this.d;
            List list = autocompleteResult.f10862a;
            if (uv1.f9054a.size() != 0) {
                ArrayList arrayList = new ArrayList();
                if (list != null && list.size() > 0) {
                    arrayList.addAll(list);
                }
                Qv1 qv1 = (Qv1) uv1.f9054a.get(0);
                uv1.a(arrayList, qv1, 0.0f);
                int size = list != null ? list.size() : 0;
                if (qv1.b < uv1.c) {
                    for (int i2 = 1; i2 < uv1.f9054a.size() && arrayList.size() < size + 3; i2++) {
                        uv1.a(arrayList, (Qv1) uv1.f9054a.get(i2), uv1.b);
                    }
                }
                list = arrayList;
            }
            autocompleteResult2 = new AutocompleteResult(list, autocompleteResult.b);
        } else {
            autocompleteResult2 = autocompleteResult;
        }
        this.b = j;
        ((C2379ed) this.c).n(autocompleteResult2, str);
        if (this.g) {
            PU0 pu0 = NU0.f8549a;
            List list2 = autocompleteResult.f10862a;
            pu0.n("zero_suggest_list_size", 0);
            int i3 = 0;
            for (int i4 = 0; i4 < list2.size(); i4++) {
                AutocompleteMatch autocompleteMatch = (AutocompleteMatch) list2.get(i4);
                if ((autocompleteMatch.a() || (i = autocompleteMatch.f10861a) == 19 || i == 26 || i == 27 || i == 29) ? false : true) {
                    pu0.p(AbstractC0533Is.j.a(i3), autocompleteMatch.j.l());
                    pu0.p(AbstractC0533Is.k.a(i3), autocompleteMatch.d);
                    pu0.p(AbstractC0533Is.l.a(i3), autocompleteMatch.f);
                    pu0.n(AbstractC0533Is.m.a(i3), autocompleteMatch.f10861a);
                    pu0.q(AbstractC0533Is.n.a(i3), AbstractC4624rl.a(autocompleteMatch.b, new C4283pl()));
                    pu0.m(AbstractC0533Is.o.a(i3), autocompleteMatch.c);
                    pu0.m(AbstractC0533Is.r.a(i3), autocompleteMatch.o);
                    pu0.p(AbstractC0533Is.t.a(i3), autocompleteMatch.p);
                    String a2 = AbstractC0533Is.u.a(i3);
                    byte[] bArr = autocompleteMatch.q;
                    if (bArr == null) {
                        str2 = null;
                    } else {
                        str2 = Base64.encodeToString(bArr, 0);
                    }
                    pu0.p(a2, str2);
                    pu0.n(AbstractC0533Is.q.a(i3), autocompleteMatch.r);
                    i3++;
                }
            }
            pu0.n("zero_suggest_list_size", i3);
            SparseArray sparseArray = autocompleteResult.b;
            int size2 = sparseArray.size();
            pu0.n("zero_suggest_header_list_size", size2);
            for (int i5 = 0; i5 < size2; i5++) {
                C2550fd fdVar = (C2550fd) sparseArray.valueAt(i5);
                String str3 = fdVar.f9934a;
                boolean z = fdVar.b;
                pu0.n(AbstractC0533Is.v.a(i5), sparseArray.keyAt(i5));
                pu0.p(AbstractC0533Is.w.a(i5), str3);
                pu0.m(AbstractC0533Is.x.a(i5), z);
            }
        }
    }
}
