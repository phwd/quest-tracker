package defpackage;

import android.view.View;
import java.util.List;

/* renamed from: bX  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class View$OnClickListenerC1852bX implements View.OnClickListener {
    public final XW F;

    public View$OnClickListenerC1852bX(XW xw) {
        this.F = xw;
    }

    public void onClick(View view) {
        XW xw = this.F;
        UH0 uh0 = xw.f9211a;
        QH0 qh0 = AbstractC2194dX.b;
        boolean z = !uh0.h(qh0);
        AbstractC3100ip1.f10165a.d(z ? "Omnibox.ToggleSuggestionGroupId.Off" : "Omnibox.ToggleSuggestionGroupId.On", xw.b);
        xw.f9211a.j(qh0, z);
        AbstractC5531x31 x31 = xw.c.f9278a;
        int i = xw.b;
        C4215pJ pJVar = ((C2379ed) x31).h0;
        if (((Boolean) pJVar.e.get(i, Boolean.FALSE)).booleanValue() != z) {
            pJVar.e.put(i, Boolean.valueOf(z));
            int i2 = 0;
            if (z) {
                int size = pJVar.f11061a.size() - 1;
                while (size >= 0) {
                    C2848hJ hJVar = (C2848hJ) pJVar.f11061a.get(size);
                    if (pJVar.a(hJVar, i)) {
                        break;
                    }
                    int i3 = hJVar.d;
                    if (i3 != i) {
                        if (i2 > 0 && i3 != i) {
                            break;
                        }
                    } else {
                        i2++;
                    }
                    size--;
                }
                if (i2 > 0) {
                    C4935tb0 tb0 = pJVar.f11061a;
                    int i4 = size + 1;
                    tb0.G.subList(i4, i4 + i2).clear();
                    tb0.p(i4, i2);
                    return;
                }
                return;
            }
            int i5 = 0;
            while (i5 < pJVar.f11061a.size() && !pJVar.a((C2848hJ) pJVar.f11061a.get(i5), i)) {
                i5++;
            }
            if (i5 != pJVar.f11061a.size()) {
                int i6 = i5 + 1;
                if (i6 >= pJVar.f11061a.size() || ((C2848hJ) pJVar.f11061a.get(i6)).d != i) {
                    int i7 = 0;
                    int i8 = -1;
                    while (i2 < pJVar.d.size()) {
                        C2848hJ hJVar2 = (C2848hJ) pJVar.d.get(i2);
                        if (!pJVar.a(hJVar2, i)) {
                            int i9 = hJVar2.d;
                            if (i9 != i) {
                                if (i7 > 0 && i9 != i) {
                                    break;
                                }
                            } else {
                                i7++;
                            }
                        } else {
                            i8 = i2 + 1;
                        }
                        i2++;
                    }
                    if (i7 != 0 && i8 != -1) {
                        C4935tb0 tb02 = pJVar.f11061a;
                        List subList = pJVar.d.subList(i8, i7 + i8);
                        tb02.G.addAll(i6, subList);
                        tb02.o(i6, subList.size());
                    }
                }
            }
        }
    }
}
