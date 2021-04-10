package defpackage;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.oculus.browser.R;
import java.util.Collections;
import java.util.List;
import org.chromium.components.page_info.PageInfoRowView;

/* renamed from: Iv0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C0540Iv0 extends View$OnClickListenerC0479Hv0 {
    public LinearLayout a0;
    public PageInfoRowView b0;
    public PageInfoRowView c0;
    public PageInfoRowView d0;

    public C0540Iv0(Context context, C0296Ev0 ev0) {
        super(context);
        LayoutInflater.from(context).inflate(R.layout.f40220_resource_name_obfuscated_RES_2131624331, (ViewGroup) this, true);
        super.b(ev0);
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.page_info_row_wrapper);
        this.a0 = linearLayout;
        j(linearLayout, true, null);
    }

    @Override // defpackage.View$OnClickListenerC0479Hv0
    public List a() {
        return Collections.emptyList();
    }

    @Override // defpackage.View$OnClickListenerC0479Hv0
    public void b(C0296Ev0 ev0) {
        super.b(ev0);
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.page_info_row_wrapper);
        this.a0 = linearLayout;
        j(linearLayout, true, null);
    }

    @Override // defpackage.View$OnClickListenerC0479Hv0
    public void c(C0296Ev0 ev0) {
        this.b0 = (PageInfoRowView) findViewById(R.id.page_info_connection_row);
    }

    @Override // defpackage.View$OnClickListenerC0479Hv0
    public void d(C0296Ev0 ev0) {
        this.d0 = (PageInfoRowView) findViewById(R.id.page_info_cookies_row);
        this.M = ev0.n;
    }

    @Override // defpackage.View$OnClickListenerC0479Hv0
    public void e(C0296Ev0 ev0) {
    }

    @Override // defpackage.View$OnClickListenerC0479Hv0
    public void f(C0296Ev0 ev0) {
        this.c0 = (PageInfoRowView) findViewById(R.id.page_info_permissions_row);
    }

    @Override // defpackage.View$OnClickListenerC0479Hv0
    public void g(C0296Ev0 ev0) {
        TextView textView = (TextView) findViewById(R.id.page_info_preview_load_original);
        this.H = textView;
        j(textView, ev0.f, ev0.m);
        this.H.setText(ev0.p);
    }

    @Override // defpackage.View$OnClickListenerC0479Hv0
    public void h(C0296Ev0 ev0) {
    }

    @Override // defpackage.View$OnClickListenerC0479Hv0
    public void i(C0296Ev0 ev0) {
    }

    @Override // defpackage.View$OnClickListenerC0479Hv0
    public void l() {
        throw new RuntimeException();
    }
}
