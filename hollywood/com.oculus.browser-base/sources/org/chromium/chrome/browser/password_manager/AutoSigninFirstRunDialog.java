package org.chromium.chrome.browser.password_manager;

import J.N;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.text.SpannableString;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.oculus.browser.R;
import org.chromium.ui.base.WindowAndroid;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class AutoSigninFirstRunDialog implements DialogInterface.OnClickListener, DialogInterface.OnDismissListener {
    public final Context F;
    public final String G;
    public final String H;
    public final int I;

    /* renamed from: J  reason: collision with root package name */
    public final int f10733J;
    public final String K;
    public final String L;
    public long M;
    public DialogC2461f4 N;
    public boolean O;

    public AutoSigninFirstRunDialog(Context context, long j, String str, String str2, int i, int i2, String str3, String str4) {
        this.M = j;
        this.F = context;
        this.G = str;
        this.H = str2;
        this.I = i;
        this.f10733J = i2;
        this.K = str3;
        this.L = str4;
    }

    public static AutoSigninFirstRunDialog createAndShowDialog(WindowAndroid windowAndroid, long j, String str, String str2, int i, int i2, String str3, String str4) {
        Activity activity = (Activity) windowAndroid.s0().get();
        if (activity == null) {
            return null;
        }
        AutoSigninFirstRunDialog autoSigninFirstRunDialog = new AutoSigninFirstRunDialog(activity, j, str, str2, i, i2, str3, str4);
        C2290e4 e4Var = new C2290e4(activity, R.style.f72700_resource_name_obfuscated_RES_2132017843);
        C1598a4 a4Var = e4Var.f9828a;
        a4Var.d = str;
        a4Var.g = str3;
        a4Var.h = autoSigninFirstRunDialog;
        a4Var.i = str4;
        a4Var.j = autoSigninFirstRunDialog;
        View inflate = LayoutInflater.from(activity).inflate(R.layout.f36880_resource_name_obfuscated_RES_2131623997, (ViewGroup) null);
        TextView textView = (TextView) inflate.findViewById(R.id.summary);
        if (i == i2 || i2 == 0) {
            textView.setText(str2);
            textView.setMovementMethod(LinkMovementMethod.getInstance());
        } else {
            SpannableString spannableString = new SpannableString(str2);
            spannableString.setSpan(new C3743mc(autoSigninFirstRunDialog), i, i2, 18);
            textView.setText(spannableString);
            textView.setMovementMethod(LinkMovementMethod.getInstance());
        }
        C1598a4 a4Var2 = e4Var.f9828a;
        a4Var2.r = inflate;
        a4Var2.q = 0;
        DialogC2461f4 a2 = e4Var.a();
        autoSigninFirstRunDialog.N = a2;
        a2.setCanceledOnTouchOutside(false);
        autoSigninFirstRunDialog.N.setOnDismissListener(autoSigninFirstRunDialog);
        autoSigninFirstRunDialog.N.show();
        return autoSigninFirstRunDialog;
    }

    public final void dismissDialog() {
        this.O = true;
        this.N.dismiss();
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        if (i == -2) {
            N.MNvg9$ZU(this.M, this);
        } else if (i == -1) {
            N.MV90asHX(this.M, this);
        }
    }

    public void onDismiss(DialogInterface dialogInterface) {
        N.MTjiTA74(this.M, this);
        this.M = 0;
        this.N = null;
    }
}
