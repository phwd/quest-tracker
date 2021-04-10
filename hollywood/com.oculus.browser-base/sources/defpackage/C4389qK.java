package defpackage;

import android.content.Context;
import android.text.TextUtils;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import com.oculus.browser.R;
import java.util.ArrayList;
import java.util.List;

/* renamed from: qK  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C4389qK implements AbstractC4899tK {
    public final Context F;
    public final C4729sK G;
    public final View H;
    public final TextView I;

    /* renamed from: J  reason: collision with root package name */
    public final Spinner f11134J;
    public final View K;
    public final TextView L;
    public int M;
    public ArrayAdapter N;

    public C4389qK(Context context, ViewGroup viewGroup, C4729sK sKVar, Runnable runnable) {
        this.F = context;
        this.G = sKVar;
        View inflate = LayoutInflater.from(context).inflate(R.layout.f40520_resource_name_obfuscated_RES_2131624361, viewGroup, false);
        this.H = inflate;
        TextView textView = (TextView) inflate.findViewById(R.id.spinner_label);
        this.I = textView;
        textView.setText(sKVar.d() ? ((Object) sKVar.p) + "*" : sKVar.p);
        this.K = inflate.findViewById(R.id.spinner_underline);
        this.L = (TextView) inflate.findViewById(R.id.spinner_error);
        List list = sKVar.d;
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < list.size(); i++) {
            arrayList.add((CharSequence) ((Pair) ((C3749me) list.get(i))).second);
        }
        C4729sK sKVar2 = this.G;
        if (sKVar2.t != null) {
            if (sKVar2.A) {
                this.N = new C3560lX(context, R.layout.f39780_resource_name_obfuscated_RES_2131624287, R.id.spinner_item, arrayList, this.G.t.toString());
            } else {
                this.N = new C3389kX(context, R.layout.f39780_resource_name_obfuscated_RES_2131624287, R.id.spinner_item, arrayList, this.G.t.toString());
            }
            this.N.setDropDownViewResource(R.layout.f40500_resource_name_obfuscated_RES_2131624359);
        } else {
            C2165dJ dJVar = new C2165dJ(context, R.layout.f39780_resource_name_obfuscated_RES_2131624287, arrayList);
            this.N = dJVar;
            dJVar.setDropDownViewResource(17367049);
        }
        int position = TextUtils.isEmpty(this.G.s) ? 0 : this.N.getPosition(this.G.s.toString());
        this.M = position;
        if (position < 0) {
            ArrayAdapter arrayAdapter = this.N;
            C4729sK sKVar3 = this.G;
            this.M = arrayAdapter.getPosition((CharSequence) sKVar3.e.get(sKVar3.s.toString()));
        }
        if (this.M < 0) {
            this.M = 0;
        }
        Spinner spinner = (Spinner) this.H.findViewById(R.id.spinner);
        this.f11134J = spinner;
        spinner.setTag(this);
        spinner.setAdapter((SpinnerAdapter) this.N);
        spinner.setSelection(this.M);
        spinner.setOnItemSelectedListener(new C4047oK(this, runnable));
        spinner.setOnTouchListener(new View$OnTouchListenerC4218pK(this));
    }

    @Override // defpackage.AbstractC4899tK
    public boolean a() {
        return this.G.e();
    }

    @Override // defpackage.AbstractC4899tK
    public boolean b() {
        return this.G.d();
    }

    @Override // defpackage.AbstractC4899tK
    public void c(boolean z) {
        View selectedView = this.f11134J.getSelectedView();
        if (selectedView != null && (selectedView instanceof TextView)) {
            if (z) {
                Fs1 a2 = Fs1.a(this.F.getResources(), R.drawable.f30040_resource_name_obfuscated_RES_2131231044, this.F.getTheme());
                a2.setBounds(0, 0, a2.getIntrinsicWidth(), a2.getIntrinsicHeight());
                ((TextView) selectedView).setError(this.G.o, a2);
                this.K.setBackgroundColor(this.F.getResources().getColor(R.color.f11510_resource_name_obfuscated_RES_2131099841));
                this.L.setText(this.G.o);
                this.L.setVisibility(0);
                return;
            }
            ((TextView) selectedView).setError(null);
            this.K.setBackgroundColor(this.F.getResources().getColor(R.color.f13520_resource_name_obfuscated_RES_2131100042));
            this.L.setText((CharSequence) null);
            this.L.setVisibility(8);
        }
    }

    @Override // defpackage.AbstractC4899tK
    public void d() {
        c(!this.G.e());
        e();
    }

    public final void e() {
        C3493l60.F.d(this.f11134J);
        ViewGroup viewGroup = (ViewGroup) this.f11134J.getParent();
        if (viewGroup != null) {
            Spinner spinner = this.f11134J;
            viewGroup.requestChildFocus(spinner, spinner);
        }
        this.f11134J.sendAccessibilityEvent(8);
    }
}
