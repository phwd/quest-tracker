package defpackage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* renamed from: Qg  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C0993Qg implements AbstractC3062ic1 {

    /* renamed from: a  reason: collision with root package name */
    public final int f8776a;

    public C0993Qg(int i) {
        this.f8776a = i;
    }

    @Override // defpackage.AbstractC3062ic1
    public List a(C3836n61 n61) {
        List list;
        if (n61 == null || (list = n61.f10472a) == null || list.size() < 1) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(n61.f10472a);
        C1529Zb1[] zb1Arr = new C1529Zb1[1];
        int i = this.f8776a;
        zb1Arr[0] = new C1529Zb1(arrayList, i, i != 1 ? "" : "StaleTabSuggestionProvider");
        return Arrays.asList(zb1Arr);
    }
}
