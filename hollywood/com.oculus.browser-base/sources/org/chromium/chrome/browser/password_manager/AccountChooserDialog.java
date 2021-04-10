package org.chromium.chrome.browser.password_manager;

import J.N;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import com.oculus.browser.R;
import org.chromium.ui.base.WindowAndroid;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class AccountChooserDialog implements DialogInterface.OnClickListener, DialogInterface.OnDismissListener {
    public static final /* synthetic */ int F = 0;
    public final Context G;
    public final Credential[] H;
    public final String I;

    /* renamed from: J  reason: collision with root package name */
    public final int f10732J;
    public final int K;
    public final String L;
    public final String M;
    public ArrayAdapter N;
    public boolean O;
    public boolean P;
    public Credential Q;
    public long R;
    public DialogC2461f4 S;
    public boolean T = false;

    public AccountChooserDialog(Context context, long j, Credential[] credentialArr, String str, int i, int i2, String str2, String str3) {
        this.R = j;
        this.G = context;
        this.H = (Credential[]) credentialArr.clone();
        this.I = str;
        this.f10732J = i;
        this.K = i2;
        this.L = str2;
        this.M = str3;
    }

    public static AccountChooserDialog createAndShowAccountChooser(WindowAndroid windowAndroid, long j, Credential[] credentialArr, String str, int i, int i2, String str2, String str3) {
        Activity activity = (Activity) windowAndroid.s0().get();
        if (activity == null) {
            return null;
        }
        AccountChooserDialog accountChooserDialog = new AccountChooserDialog(activity, j, credentialArr, str, i, i2, str2, str3);
        View inflate = LayoutInflater.from(activity).inflate(R.layout.f36600_resource_name_obfuscated_RES_2131623969, (ViewGroup) null);
        ((TextView) inflate.findViewById(R.id.origin)).setText(accountChooserDialog.L);
        TextView textView = (TextView) inflate.findViewById(R.id.title);
        if (accountChooserDialog.f10732J == 0 || accountChooserDialog.K == 0) {
            textView.setText(accountChooserDialog.I);
        } else {
            SpannableString spannableString = new SpannableString(accountChooserDialog.I);
            spannableString.setSpan(new G0(accountChooserDialog), accountChooserDialog.f10732J, accountChooserDialog.K, 18);
            textView.setText(spannableString, TextView.BufferType.SPANNABLE);
            textView.setMovementMethod(LinkMovementMethod.getInstance());
        }
        accountChooserDialog.N = new F0(accountChooserDialog, activity, 0, accountChooserDialog.H);
        C2290e4 e4Var = new C2290e4(activity, R.style.f72700_resource_name_obfuscated_RES_2132017843);
        e4Var.f9828a.e = inflate;
        e4Var.d(R.string.f48470_resource_name_obfuscated_RES_2131952164, accountChooserDialog);
        e4Var.b(accountChooserDialog.N, new H0(accountChooserDialog));
        if (!TextUtils.isEmpty(accountChooserDialog.M)) {
            e4Var.f(accountChooserDialog.M, accountChooserDialog);
        }
        DialogC2461f4 a2 = e4Var.a();
        accountChooserDialog.S = a2;
        a2.setOnDismissListener(accountChooserDialog);
        accountChooserDialog.S.show();
        return accountChooserDialog;
    }

    public final void dismissDialog() {
        this.P = true;
        this.S.dismiss();
    }

    public final void imageFetchComplete(int i, Bitmap bitmap) {
        View childAt;
        if (!this.O) {
            Drawable a2 = AbstractC0256Ee.a(this.G.getResources(), bitmap, bitmap.getHeight());
            this.H[i].f = a2;
            ListView listView = this.S.H.g;
            if (i >= listView.getFirstVisiblePosition() && i <= listView.getLastVisiblePosition() && (childAt = listView.getChildAt(i - listView.getFirstVisiblePosition())) != null) {
                ((ImageView) childAt.findViewById(R.id.profile_image)).setImageDrawable(a2);
            }
        }
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        if (i == -1) {
            this.Q = this.H[0];
            this.T = true;
        }
    }

    public void onDismiss(DialogInterface dialogInterface) {
        if (!this.P) {
            Credential credential = this.Q;
            if (credential != null) {
                N.MJZem$De(this.R, this, credential.e, this.T);
            } else {
                N.M$NQU8jD(this.R, this);
            }
        }
        this.O = true;
        N.M495Qln5(this.R, this);
        this.R = 0;
        this.S = null;
    }
}
