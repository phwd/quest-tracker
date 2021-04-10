package org.chromium.chrome.browser.autofill.settings;

import J.N;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.TextView;
import com.oculus.browser.R;
import java.util.Objects;
import org.chromium.base.ThreadUtils;
import org.chromium.chrome.browser.autofill.PersonalDataManager;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class AutofillServerCardEditor extends AbstractC0009Ad {
    public static final /* synthetic */ int E0 = 0;
    public View F0;
    public View G0;

    @Override // org.chromium.chrome.browser.autofill.settings.AutofillEditorBase
    public int f1() {
        return R.layout.f36990_resource_name_obfuscated_RES_2131624008;
    }

    @Override // org.chromium.chrome.browser.autofill.settings.AutofillEditorBase
    public int g1(boolean z) {
        return R.string.f47370_resource_name_obfuscated_RES_2131952054;
    }

    @Override // org.chromium.chrome.browser.autofill.settings.AutofillEditorBase
    public boolean h1() {
        if (this.C0.getSelectedItem() == null || !(this.C0.getSelectedItem() instanceof PersonalDataManager.AutofillProfile)) {
            return true;
        }
        this.B0.l = ((PersonalDataManager.AutofillProfile) this.C0.getSelectedItem()).getGUID();
        PersonalDataManager c = PersonalDataManager.c();
        PersonalDataManager.CreditCard creditCard = this.B0;
        Objects.requireNonNull(c);
        Object obj = ThreadUtils.f10596a;
        N.MmUEbunT(c.b, c, creditCard);
        C2187dT0.a().b(this.B0);
        return true;
    }

    public final void j1() {
        ViewGroup viewGroup = (ViewGroup) this.G0.getParent();
        if (viewGroup != null) {
            viewGroup.removeView(this.F0);
            viewGroup.removeView(this.G0);
        }
    }

    @Override // defpackage.AbstractC0009Ad, defpackage.AbstractComponentCallbacksC3550lS, org.chromium.chrome.browser.autofill.settings.AutofillEditorBase
    public View l0(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View l0 = super.l0(layoutInflater, viewGroup, bundle);
        if (this.B0 == null) {
            u().finish();
            return l0;
        }
        ((TextView) l0.findViewById(R.id.title)).setText(this.B0.g);
        ((TextView) l0.findViewById(R.id.summary)).setText(this.B0.a(u()));
        l0.findViewById(R.id.edit_server_card).setOnClickListener(new View$OnClickListenerC5113ue(this));
        this.F0 = l0.findViewById(R.id.local_copy_label);
        this.G0 = l0.findViewById(R.id.clear_local_copy);
        if (this.B0.getIsCached()) {
            this.G0.setOnClickListener(new View$OnClickListenerC5283ve(this));
        } else {
            j1();
        }
        i1(l0);
        return l0;
    }

    @Override // android.widget.AdapterView.OnItemSelectedListener
    public void onItemSelected(AdapterView adapterView, View view, int i, long j) {
        if (adapterView == this.C0 && i != this.D0) {
            ((Button) this.k0.findViewById(R.id.button_primary)).setEnabled(true);
        }
    }
}
