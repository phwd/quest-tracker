package defpackage;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.oculus.browser.R;
import org.chromium.components.browser_ui.widget.text.AlertDialogEditText;

/* renamed from: eb0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C2374eb0 {

    /* renamed from: a  reason: collision with root package name */
    public final Context f9862a;
    public final String b;
    public final AbstractC2204db0 c;
    public DialogC2461f4 d;
    public AlertDialogEditText e;
    public AlertDialogEditText f;

    public C2374eb0(Context context, String str, String str2, AbstractC2204db0 db0) {
        this.f9862a = context;
        this.b = str;
        this.c = db0;
        View inflate = LayoutInflater.from(context).inflate(R.layout.f38670_resource_name_obfuscated_RES_2131624176, (ViewGroup) null);
        this.e = (AlertDialogEditText) inflate.findViewById(R.id.username);
        AlertDialogEditText alertDialogEditText = (AlertDialogEditText) inflate.findViewById(R.id.password);
        this.f = alertDialogEditText;
        alertDialogEditText.setOnEditorActionListener(new C1525Za0(this));
        ((TextView) inflate.findViewById(R.id.explanation)).setText(str);
        C2246dp1 dp1 = new C2246dp1(context, R.style.f72700_resource_name_obfuscated_RES_2132017843);
        dp1.g(R.string.f54250_resource_name_obfuscated_RES_2131952742);
        C1598a4 a4Var = dp1.f9828a;
        a4Var.r = inflate;
        a4Var.q = 0;
        dp1.e(R.string.f54230_resource_name_obfuscated_RES_2131952740, new DialogInterface$OnClickListenerC1682ab0(this));
        dp1.d(R.string.f48470_resource_name_obfuscated_RES_2131952164, new DialogInterface$OnClickListenerC1862bb0(this));
        dp1.f9828a.l = new DialogInterface$OnCancelListenerC2033cb0(this);
        DialogC2461f4 a2 = dp1.a();
        this.d = a2;
        ((LayoutInflater$Factory2C3156j8) a2.a()).c0 = false;
        this.d.getWindow().setSoftInputMode(4);
    }
}
