package org.chromium.chrome.browser.signin.ui.account_picker;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.oculus.browser.R;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class AccountPickerDialogFragment extends OE {
    public C5693y1 M0;

    @Override // defpackage.OE
    public Dialog g1(Bundle bundle) {
        C2290e4 e4Var = new C2290e4(O0(), R.style.f72700_resource_name_obfuscated_RES_2132017843);
        RecyclerView recyclerView = (RecyclerView) LayoutInflater.from(e4Var.f9828a.f9407a).inflate(R.layout.f36660_resource_name_obfuscated_RES_2131623975, (ViewGroup) null);
        recyclerView.t0(new LinearLayoutManager(u()));
        this.M0 = new C5693y1(recyclerView, (AbstractC5523x1) R(), this.K.getString("AccountPickerDialogFragment.SelectedAccountName"), false);
        e4Var.g(R.string.f62020_resource_name_obfuscated_RES_2131953519);
        C1598a4 a4Var = e4Var.f9828a;
        a4Var.r = recyclerView;
        a4Var.q = 0;
        return e4Var.a();
    }

    @Override // defpackage.AbstractComponentCallbacksC3550lS
    public void m0() {
        this.i0 = true;
        J1 j1 = this.M0.f11654a;
        j1.c.Y(j1.h);
        j1.f.m(j1.g);
    }
}
