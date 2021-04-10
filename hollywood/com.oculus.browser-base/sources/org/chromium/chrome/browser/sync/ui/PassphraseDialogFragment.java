package org.chromium.chrome.browser.sync.ui;

import J.N;
import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import com.oculus.browser.R;
import org.chromium.chrome.browser.sync.ProfileSyncService;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class PassphraseDialogFragment extends OE implements DialogInterface.OnClickListener {
    public EditText M0;
    public TextView N0;
    public Drawable O0;
    public Drawable P0;

    public static void l1(PassphraseDialogFragment passphraseDialogFragment) {
        AbstractC3807mx0 mx0;
        passphraseDialogFragment.M0.setBackground(passphraseDialogFragment.O0);
        passphraseDialogFragment.N0.setText(R.string.f63070_resource_name_obfuscated_RES_2131953624);
        String obj = passphraseDialogFragment.M0.getText().toString();
        AbstractComponentCallbacksC3550lS R = passphraseDialogFragment.R();
        if (R instanceof AbstractC3807mx0) {
            mx0 = (AbstractC3807mx0) R;
        } else {
            mx0 = (AbstractC3807mx0) passphraseDialogFragment.u();
        }
        if (!mx0.A(obj)) {
            passphraseDialogFragment.N0.setText(R.string.f62880_resource_name_obfuscated_RES_2131953605);
            passphraseDialogFragment.N0.setTextColor(passphraseDialogFragment.I().getColor(R.color.f12860_resource_name_obfuscated_RES_2131099976));
            passphraseDialogFragment.M0.setBackground(passphraseDialogFragment.P0);
        }
    }

    @Override // defpackage.AbstractComponentCallbacksC3550lS
    public void B0() {
        this.M0.setBackground(this.O0);
        this.i0 = true;
    }

    @Override // defpackage.OE
    public Dialog g1(Bundle bundle) {
        SpannableString spannableString;
        View inflate = u().getLayoutInflater().inflate(R.layout.f41680_resource_name_obfuscated_RES_2131624477, (ViewGroup) null);
        TextView textView = (TextView) inflate.findViewById(R.id.prompt_text);
        ProfileSyncService b = ProfileSyncService.b();
        String str = N.MWW_VhAo(b.e, b) + "\n\n";
        int e = b.e();
        if (N.MZ5PAkH1(b.e, b)) {
            String O = O(R.string.f52480_resource_name_obfuscated_RES_2131952565);
            if (e == 2) {
                StringBuilder i = AbstractC2531fV.i(str);
                i.append(N.MzdbY3ND(b.e, b));
                spannableString = m1(i.toString(), O);
            } else if (e != 3) {
                AbstractC1220Ua0.f("Sync_UI", "Found incorrect passphrase type " + e + ". Falling back to default string.", new Object[0]);
            } else {
                StringBuilder i2 = AbstractC2531fV.i(str);
                i2.append(N.Mm0TRqKH(b.e, b));
                spannableString = m1(i2.toString(), O);
            }
            textView.setText(spannableString);
            textView.setMovementMethod(LinkMovementMethod.getInstance());
            TextView textView2 = (TextView) inflate.findViewById(R.id.reset_text);
            Activity u = u();
            textView2.setText(FY0.a(u.getString(R.string.f62890_resource_name_obfuscated_RES_2131953606), new EY0("<resetlink>", "</resetlink>", new C3636lx0(this, u))));
            textView2.setMovementMethod(LinkMovementMethod.getInstance());
            textView2.setVisibility(0);
            this.N0 = (TextView) inflate.findViewById(R.id.verifying);
            EditText editText = (EditText) inflate.findViewById(R.id.passphrase);
            this.M0 = editText;
            editText.setOnEditorActionListener(new C2782gx0(this));
            Drawable background = this.M0.getBackground();
            this.O0 = background;
            Drawable newDrawable = background.getConstantState().newDrawable();
            this.P0 = newDrawable;
            newDrawable.mutate().setColorFilter(I().getColor(R.color.f12860_resource_name_obfuscated_RES_2131099976), PorterDuff.Mode.SRC_IN);
            C2290e4 e4Var = new C2290e4(u(), R.style.f72700_resource_name_obfuscated_RES_2132017843);
            C1598a4 a4Var = e4Var.f9828a;
            a4Var.r = inflate;
            a4Var.q = 0;
            e4Var.e(R.string.f62550_resource_name_obfuscated_RES_2131953572, new DialogInterface$OnClickListenerC2953hx0(this));
            e4Var.d(R.string.f48470_resource_name_obfuscated_RES_2131952164, this);
            e4Var.g(R.string.f61840_resource_name_obfuscated_RES_2131953501);
            DialogC2461f4 a2 = e4Var.a();
            ((LayoutInflater$Factory2C3156j8) a2.a()).c0 = false;
            a2.setOnShowListener(new DialogInterface$OnShowListenerC3294jx0(this, a2));
            return a2;
        }
        StringBuilder i3 = AbstractC2531fV.i(str);
        i3.append(N.M9pHrX0Y(b.e, b));
        spannableString = new SpannableString(i3.toString());
        textView.setText(spannableString);
        textView.setMovementMethod(LinkMovementMethod.getInstance());
        TextView textView22 = (TextView) inflate.findViewById(R.id.reset_text);
        Activity u2 = u();
        textView22.setText(FY0.a(u2.getString(R.string.f62890_resource_name_obfuscated_RES_2131953606), new EY0("<resetlink>", "</resetlink>", new C3636lx0(this, u2))));
        textView22.setMovementMethod(LinkMovementMethod.getInstance());
        textView22.setVisibility(0);
        this.N0 = (TextView) inflate.findViewById(R.id.verifying);
        EditText editText2 = (EditText) inflate.findViewById(R.id.passphrase);
        this.M0 = editText2;
        editText2.setOnEditorActionListener(new C2782gx0(this));
        Drawable background2 = this.M0.getBackground();
        this.O0 = background2;
        Drawable newDrawable2 = background2.getConstantState().newDrawable();
        this.P0 = newDrawable2;
        newDrawable2.mutate().setColorFilter(I().getColor(R.color.f12860_resource_name_obfuscated_RES_2131099976), PorterDuff.Mode.SRC_IN);
        C2290e4 e4Var2 = new C2290e4(u(), R.style.f72700_resource_name_obfuscated_RES_2132017843);
        C1598a4 a4Var2 = e4Var2.f9828a;
        a4Var2.r = inflate;
        a4Var2.q = 0;
        e4Var2.e(R.string.f62550_resource_name_obfuscated_RES_2131953572, new DialogInterface$OnClickListenerC2953hx0(this));
        e4Var2.d(R.string.f48470_resource_name_obfuscated_RES_2131952164, this);
        e4Var2.g(R.string.f61840_resource_name_obfuscated_RES_2131953501);
        DialogC2461f4 a22 = e4Var2.a();
        ((LayoutInflater$Factory2C3156j8) a22.a()).c0 = false;
        a22.setOnShowListener(new DialogInterface$OnShowListenerC3294jx0(this, a22));
        return a22;
    }

    public final SpannableString m1(String str, String str2) {
        return FY0.a(str, new EY0("<learnmore>", "</learnmore>", new C3465kx0(this, str2)));
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        AbstractC3807mx0 mx0;
        if (i == -2) {
            this.N0.getText().toString().equals(I().getString(R.string.f62880_resource_name_obfuscated_RES_2131953605));
            AbstractComponentCallbacksC3550lS R = R();
            if (R instanceof AbstractC3807mx0) {
                mx0 = (AbstractC3807mx0) R;
            } else {
                mx0 = (AbstractC3807mx0) u();
            }
            mx0.n();
        }
    }
}
