package defpackage;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.oculus.browser.R;

/* renamed from: Wk0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C1371Wk0 extends AbstractC4277pj {

    /* renamed from: a  reason: collision with root package name */
    public final View f9170a;
    public final View b;
    public final Context c;
    public final Button d;
    public final Button e;
    public final ImageView f;
    public final ImageView g;
    public final ImageView h;
    public final TextView i;
    public final TextView j;
    public final TextView k;
    public final TextView l;
    public final TextView m;
    public final TextView n;
    public final TextView o;
    public final TextView p;
    public final TextView q;
    public final TextView r;
    public final View s;
    public final View t;
    public final View u;
    public final View v;
    public final View w;
    public boolean x;

    public C1371Wk0(Context context) {
        this.c = context;
        View inflate = LayoutInflater.from(context).inflate(R.layout.f40450_resource_name_obfuscated_RES_2131624354, (ViewGroup) null);
        this.f9170a = inflate;
        View inflate2 = LayoutInflater.from(context).inflate(R.layout.f40460_resource_name_obfuscated_RES_2131624355, (ViewGroup) null);
        this.b = inflate2;
        this.d = (Button) inflate.findViewById(R.id.pay_button);
        this.e = (Button) inflate2.findViewById(R.id.pay_button);
        this.f = (ImageView) inflate.findViewById(R.id.status_icon);
        this.g = (ImageView) inflate2.findViewById(R.id.payment_app_icon);
        this.h = (ImageView) inflate2.findViewById(R.id.status_icon);
        this.i = (TextView) inflate.findViewById(R.id.account_balance);
        this.j = (TextView) inflate.findViewById(R.id.account_balance_currency);
        this.k = (TextView) inflate.findViewById(R.id.payment_amount);
        this.l = (TextView) inflate.findViewById(R.id.payment_currency);
        this.m = (TextView) inflate.findViewById(R.id.status_message);
        this.n = (TextView) inflate2.findViewById(R.id.large_status_message);
        this.o = (TextView) inflate2.findViewById(R.id.payment_app_name);
        this.p = (TextView) inflate2.findViewById(R.id.small_emphasized_status_message);
        this.q = (TextView) inflate2.findViewById(R.id.amount);
        this.r = (TextView) inflate2.findViewById(R.id.currency);
        this.s = inflate.findViewById(R.id.account_balance_label);
        this.t = inflate.findViewById(R.id.processing_spinner);
        this.u = inflate.findViewById(R.id.line_item_separator);
        this.v = inflate.findViewById(R.id.payment_label);
        this.w = inflate2.findViewById(R.id.processing_spinner);
    }

    @Override // defpackage.AbstractC4277pj
    public void f() {
    }

    @Override // defpackage.AbstractC4277pj
    public View g() {
        return this.f9170a;
    }

    @Override // defpackage.AbstractC4277pj
    public float h() {
        return -1.0f;
    }

    @Override // defpackage.AbstractC4277pj
    public int j() {
        return this.x ? 0 : -2;
    }

    @Override // defpackage.AbstractC4277pj
    public int k() {
        return 0;
    }

    @Override // defpackage.AbstractC4277pj
    public int l() {
        return R.string.f58280_resource_name_obfuscated_RES_2131953145;
    }

    @Override // defpackage.AbstractC4277pj
    public int m() {
        return R.string.f58280_resource_name_obfuscated_RES_2131953145;
    }

    @Override // defpackage.AbstractC4277pj
    public int n() {
        return R.string.f58280_resource_name_obfuscated_RES_2131953145;
    }

    @Override // defpackage.AbstractC4277pj
    public int o() {
        return R.string.f58280_resource_name_obfuscated_RES_2131953145;
    }

    @Override // defpackage.AbstractC4277pj
    public View p() {
        return this.b;
    }

    @Override // defpackage.AbstractC4277pj
    public int q() {
        return 0;
    }

    @Override // defpackage.AbstractC4277pj
    public boolean v() {
        return true;
    }
}
