package org.chromium.chrome.browser.firstrun;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.SystemClock;
import android.text.SpannableString;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import com.oculus.browser.R;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class ToSAndUMAFirstRunFragment extends AbstractComponentCallbacksC3550lS implements UQ {
    public CheckBox A0;
    public TextView B0;
    public View C0;
    public View D0;
    public long E0;
    public boolean y0;
    public Button z0;

    @Override // defpackage.AbstractComponentCallbacksC3550lS
    public void F0(View view, Bundle bundle) {
        SpannableString spannableString;
        this.C0 = view.findViewById(R.id.title);
        View findViewById = view.findViewById(R.id.progress_spinner);
        this.D0 = findViewById;
        findViewById.setVisibility(8);
        this.z0 = (Button) view.findViewById(R.id.terms_accept);
        this.A0 = (CheckBox) view.findViewById(R.id.send_report_checkbox);
        this.B0 = (TextView) view.findViewById(R.id.tos_and_privacy);
        this.z0.setOnClickListener(new View$OnClickListenerC0879Oi1(this));
        this.A0.setChecked(true);
        if (!e1()) {
            this.A0.setVisibility(8);
        }
        this.B0.setMovementMethod(LinkMovementMethod.getInstance());
        Resources I = I();
        C4467qp0 qp0 = new C4467qp0(I, new C0940Pi1(this));
        C4467qp0 qp02 = new C4467qp0(I, new C1001Qi1(this));
        C4467qp0 qp03 = new C4467qp0(I, new C1062Ri1(this));
        if (TQ.a(this).D().getInt("ChildAccountStatus", 0) == 1) {
            spannableString = FY0.a(O(R.string.f52390_resource_name_obfuscated_RES_2131952556), new EY0("<LINK1>", "</LINK1>", qp0), new EY0("<LINK2>", "</LINK2>", qp02), new EY0("<LINK3>", "</LINK3>", qp03));
        } else {
            spannableString = FY0.a(O(R.string.f52380_resource_name_obfuscated_RES_2131952555), new EY0("<LINK1>", "</LINK1>", qp0), new EY0("<LINK2>", "</LINK2>", qp02));
        }
        this.B0.setText(spannableString);
        if (NU0.f8549a.d("skip_welcome_page", false)) {
            k1(true);
        }
    }

    @Override // defpackage.AbstractComponentCallbacksC3550lS
    public void e0(Context context) {
        super.e0(context);
        TQ.a(this).G().g(new C0818Ni1(this));
    }

    public boolean e1() {
        return true;
    }

    public final void f1() {
    }

    public final void g1() {
        this.y0 = true;
        this.E0 = SystemClock.elapsedRealtime();
        k1(true);
    }

    public final void h1() {
        if (U()) {
            TQ.a(this).h(R.string.f52430_resource_name_obfuscated_RES_2131952560);
        }
    }

    public final void i1() {
        if (U()) {
            TQ.a(this).h(R.string.f48700_resource_name_obfuscated_RES_2131952187);
        }
    }

    public final void j1() {
        if (U()) {
            TQ.a(this).h(R.string.f52220_resource_name_obfuscated_RES_2131952539);
        }
    }

    public final void k1(boolean z) {
        boolean z2 = !z;
        l1(z2);
        int i = 0;
        this.C0.setVisibility(z2 ? 0 : 4);
        View view = this.D0;
        if (!z) {
            i = 8;
        }
        view.setVisibility(i);
    }

    @Override // defpackage.AbstractComponentCallbacksC3550lS
    public View l0(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return layoutInflater.inflate(R.layout.f38600_resource_name_obfuscated_RES_2131624169, viewGroup, false);
    }

    public void l1(boolean z) {
        int i = z ? 0 : 8;
        this.z0.setVisibility(i);
        this.B0.setVisibility(i);
        if (e1()) {
            this.A0.setVisibility(i);
        }
    }
}
