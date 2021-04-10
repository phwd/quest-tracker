package org.chromium.chrome.browser.language.settings;

import J.N;
import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.oculus.browser.R;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.chromium.chrome.browser.translate.TranslateBridge;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class AddLanguageFragment extends AbstractComponentCallbacksC3550lS {
    public static final /* synthetic */ int y0 = 0;
    public String A0;
    public RecyclerView B0;
    public C4849t3 C0;
    public List D0;
    public C4338q3 E0;
    public SearchView z0;

    @Override // defpackage.AbstractComponentCallbacksC3550lS
    public void h0(Bundle bundle) {
        super.h0(bundle);
        if (N.M09VlOh_("DetailedLanguageSettings")) {
            u().setTitle(R.string.f53940_resource_name_obfuscated_RES_2131952711);
        } else {
            u().setTitle(R.string.f46550_resource_name_obfuscated_RES_2131951972);
        }
        V0(true);
    }

    @Override // defpackage.AbstractComponentCallbacksC3550lS
    public void k0(Menu menu, MenuInflater menuInflater) {
        menu.clear();
        menuInflater.inflate(R.menu.f42420_resource_name_obfuscated_RES_2131689476, menu);
        SearchView searchView = (SearchView) menu.findItem(R.id.search).getActionView();
        this.z0 = searchView;
        searchView.V.setImeOptions(33554432);
        SearchView searchView2 = this.z0;
        searchView2.s0 = new C4508r3(this);
        searchView2.r0 = new C4679s3(this);
    }

    @Override // defpackage.AbstractComponentCallbacksC3550lS
    public View l0(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.f36780_resource_name_obfuscated_RES_2131623987, viewGroup, false);
        this.A0 = "";
        Activity u = u();
        this.B0 = (RecyclerView) inflate.findViewById(R.id.language_list);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(u);
        this.B0.t0(linearLayoutManager);
        this.B0.g(new C4547rG(u, linearLayoutManager.r));
        int intExtra = u().getIntent().getIntExtra("AddLanguageFragment.LanguageOptions", 0);
        if (intExtra == 1) {
            T60 a2 = T60.a();
            Objects.requireNonNull(a2);
            ArrayList arrayList = new ArrayList();
            for (B60 b60 : a2.b.values()) {
                if (b60.e) {
                    arrayList.add(b60);
                }
            }
            this.D0 = arrayList;
            arrayList.add(0, B60.a());
        } else if (intExtra == 2) {
            T60 a3 = T60.a();
            Objects.requireNonNull(a3);
            ArrayList arrayList2 = new ArrayList();
            for (B60 b602 : a3.b.values()) {
                if (b602.d) {
                    arrayList2.add(b602);
                }
            }
            this.D0 = arrayList2;
        } else {
            T60 a4 = T60.a();
            Objects.requireNonNull(a4);
            List a5 = TranslateBridge.a();
            ArrayList arrayList3 = new ArrayList();
            for (B60 b603 : a4.b.values()) {
                if (!((ArrayList) a5).contains(b603.f7716a)) {
                    arrayList3.add(b603);
                }
            }
            this.D0 = arrayList3;
        }
        this.E0 = new C4338q3(u);
        C4849t3 t3Var = new C4849t3(this, u);
        this.C0 = t3Var;
        this.B0.q0(t3Var);
        this.C0.t(this.D0);
        this.B0.getViewTreeObserver().addOnScrollChangedListener(new ViewTreeObserver$OnScrollChangedListenerC2699gT0(this.B0, inflate.findViewById(R.id.shadow)));
        return inflate;
    }
}
