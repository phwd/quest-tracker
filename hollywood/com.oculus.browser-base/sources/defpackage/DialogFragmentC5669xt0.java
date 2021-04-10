package defpackage;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.oculus.browser.R;

/* renamed from: xt0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class DialogFragmentC5669xt0 extends DialogFragment implements DialogInterface.OnClickListener {
    public static final /* synthetic */ int F = 0;

    public void onClick(DialogInterface dialogInterface, int i) {
        NU0.f8549a.m("org.chromium.chrome.browser.settings.privacy.PREF_OTHER_FORMS_OF_HISTORY_DIALOG_SHOWN", true);
        getActivity().finish();
    }

    public Dialog onCreateDialog(Bundle bundle) {
        super.onCreateDialog(bundle);
        View inflate = getActivity().getLayoutInflater().inflate(R.layout.f40160_resource_name_obfuscated_RES_2131624325, (ViewGroup) null);
        TextView textView = (TextView) inflate.findViewById(R.id.text);
        textView.setText(FY0.a(textView.getText().toString(), new EY0("<link>", "</link>", new C4467qp0(getResources(), new C5499wt0()))));
        textView.setMovementMethod(LinkMovementMethod.getInstance());
        C2290e4 e4Var = new C2290e4(getActivity(), R.style.f72700_resource_name_obfuscated_RES_2132017843);
        C1598a4 a4Var = e4Var.f9828a;
        a4Var.r = inflate;
        a4Var.q = 0;
        e4Var.g(R.string.f48820_resource_name_obfuscated_RES_2131952199);
        e4Var.e(R.string.f56560_resource_name_obfuscated_RES_2131952973, this);
        DialogC2461f4 a2 = e4Var.a();
        a2.setCanceledOnTouchOutside(false);
        return a2;
    }
}
