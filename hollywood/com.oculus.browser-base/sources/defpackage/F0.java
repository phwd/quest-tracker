package defpackage;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import com.oculus.browser.R;
import org.chromium.chrome.browser.password_manager.AccountChooserDialog;
import org.chromium.chrome.browser.password_manager.Credential;

/* renamed from: F0  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class F0 extends ArrayAdapter {
    public final /* synthetic */ AccountChooserDialog F;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public F0(AccountChooserDialog accountChooserDialog, Context context, int i, Credential[] credentialArr) {
        super(context, i, credentialArr);
        this.F = accountChooserDialog;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = LayoutInflater.from(getContext()).inflate(R.layout.f36590_resource_name_obfuscated_RES_2131623968, viewGroup, false);
        }
        view.setTag(Integer.valueOf(i));
        Credential credential = (Credential) getItem(i);
        ImageView imageView = (ImageView) view.findViewById(R.id.profile_image);
        Drawable drawable = credential.f;
        if (drawable == null) {
            drawable = AbstractC5544x8.a(getContext(), R.drawable.f33530_resource_name_obfuscated_RES_2131231393);
        }
        imageView.setImageDrawable(drawable);
        TextView textView = (TextView) view.findViewById(R.id.main_name);
        TextView textView2 = (TextView) view.findViewById(R.id.secondary_name);
        if (!credential.d.isEmpty()) {
            textView.setText(credential.f10736a);
            textView2.setText(credential.d);
            textView2.setVisibility(0);
        } else if (credential.b.isEmpty()) {
            textView.setText(credential.f10736a);
            textView2.setVisibility(8);
        } else {
            textView.setText(credential.b);
            textView2.setText(credential.f10736a);
            textView2.setVisibility(0);
        }
        ImageButton imageButton = (ImageButton) view.findViewById(R.id.psl_info_btn);
        String str = credential.c;
        if (!str.isEmpty()) {
            imageButton.setVisibility(0);
            imageButton.setOnClickListener(new E0(this, str));
        }
        return view;
    }
}
