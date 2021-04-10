package defpackage;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.oculus.browser.R;
import org.chromium.chrome.browser.password_manager.PasswordManagerDialogView;
import org.chromium.ui.widget.ChromeImageButton;

/* renamed from: hy0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C2956hy0 implements YH0 {
    @Override // defpackage.YH0
    public void a(Object obj, Object obj2, Object obj3) {
        UH0 uh0 = (UH0) obj;
        KH0 kh0 = (KH0) obj3;
        PasswordManagerDialogView passwordManagerDialogView = (PasswordManagerDialogView) ((View) obj2);
        OH0 oh0 = AbstractC3810my0.f10462a;
        int i = 0;
        if (oh0 == kh0) {
            Runnable runnable = (Runnable) uh0.g(oh0);
            ChromeImageButton chromeImageButton = passwordManagerDialogView.G;
            if (chromeImageButton != null) {
                chromeImageButton.setOnClickListener(new View$OnClickListenerC3981ny0(runnable));
                passwordManagerDialogView.H.setOnClickListener(new View$OnClickListenerC4152oy0(runnable));
                passwordManagerDialogView.G.setVisibility(0);
                return;
            }
            return;
        }
        NH0 nh0 = AbstractC3810my0.b;
        if (nh0 == kh0) {
            passwordManagerDialogView.I.setImageResource(uh0.f(nh0));
            return;
        }
        QH0 qh0 = AbstractC3810my0.c;
        if (qh0 == kh0) {
            if (uh0.h(qh0)) {
                passwordManagerDialogView.I.setVisibility(0);
            } else {
                passwordManagerDialogView.I.setVisibility(8);
            }
            boolean z = !uh0.h(qh0);
            if (passwordManagerDialogView.G != null) {
                LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) passwordManagerDialogView.f10739J.getLayoutParams();
                layoutParams.setMarginEnd(passwordManagerDialogView.getResources().getDimensionPixelSize(z ? R.dimen.f23790_resource_name_obfuscated_RES_2131165998 : R.dimen.f23780_resource_name_obfuscated_RES_2131165997));
                passwordManagerDialogView.f10739J.setLayoutParams(layoutParams);
                passwordManagerDialogView.G.setVisibility(z ? 8 : 0);
                ChromeImageButton chromeImageButton2 = passwordManagerDialogView.H;
                if (!z) {
                    i = 8;
                }
                chromeImageButton2.setVisibility(i);
            }
            LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(passwordManagerDialogView.I.getLayoutParams());
            layoutParams2.setMarginStart(passwordManagerDialogView.getContext().getResources().getDimensionPixelSize(R.dimen.f23780_resource_name_obfuscated_RES_2131165997));
            layoutParams2.setMarginEnd(passwordManagerDialogView.getContext().getResources().getDimensionPixelSize(R.dimen.f23780_resource_name_obfuscated_RES_2131165997));
            passwordManagerDialogView.I.setLayoutParams(layoutParams2);
            passwordManagerDialogView.I.setScaleType(ImageView.ScaleType.CENTER_CROP);
            return;
        }
        OH0 oh02 = AbstractC3810my0.d;
        if (oh02 == kh0) {
            passwordManagerDialogView.f10739J.setText((String) uh0.g(oh02));
            return;
        }
        OH0 oh03 = AbstractC3810my0.e;
        if (oh03 == kh0) {
            passwordManagerDialogView.K.setText((CharSequence) uh0.g(oh03));
        }
    }
}
