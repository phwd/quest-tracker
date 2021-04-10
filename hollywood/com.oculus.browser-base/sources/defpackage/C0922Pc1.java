package defpackage;

import android.content.Context;
import android.text.TextUtils;
import android.view.ViewGroup;
import com.oculus.browser.R;
import java.util.Objects;
import org.chromium.chrome.browser.tasks.tab_management.MessageCardView;
import org.chromium.components.browser_ui.widget.text.TemplatePreservingTextView;
import org.chromium.ui.widget.ChromeImageView;

/* renamed from: Pc1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C0922Pc1 implements YH0 {
    @Override // defpackage.YH0
    public void a(Object obj, Object obj2, Object obj3) {
        UH0 uh0 = (UH0) obj;
        KH0 kh0 = (KH0) obj3;
        MessageCardView messageCardView = (MessageCardView) ((ViewGroup) obj2);
        TH0 th0 = AbstractC0516Ij0.b;
        if (th0 == kh0) {
            messageCardView.I.setText((String) uh0.g(th0));
            messageCardView.I.setOnClickListener(new View$OnClickListenerC0394Gj0(uh0));
            return;
        }
        TH0 th02 = AbstractC0516Ij0.c;
        if (th02 == kh0) {
            messageCardView.H.setText((CharSequence) uh0.g(th02));
            return;
        }
        TH0 th03 = AbstractC0516Ij0.d;
        if (th03 == kh0) {
            String str = (String) uh0.g(th03);
            TemplatePreservingTextView templatePreservingTextView = messageCardView.H;
            Objects.requireNonNull(templatePreservingTextView);
            if (TextUtils.isEmpty(str)) {
                str = null;
            }
            templatePreservingTextView.I = str;
            return;
        }
        TH0 th04 = AbstractC0516Ij0.e;
        if (th04 == kh0) {
            messageCardView.G.setImageDrawable(((AbstractC0272Ej0) uh0.g(th04)).a());
            return;
        }
        TH0 th05 = AbstractC0516Ij0.j;
        if (th05 == kh0) {
            messageCardView.f10779J.setContentDescription((String) uh0.g(th05));
            messageCardView.f10779J.setOnClickListener(new View$OnClickListenerC0455Hj0(uh0));
            return;
        }
        RH0 rh0 = J91.b;
        if (rh0 == kh0) {
            messageCardView.setAlpha(uh0.e(rh0));
            return;
        }
        QH0 qh0 = AbstractC0516Ij0.l;
        if (qh0 == kh0) {
            boolean h = uh0.h(qh0);
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) messageCardView.H.getLayoutParams();
            if (!h) {
                messageCardView.removeView(messageCardView.G);
                marginLayoutParams.setMargins((int) messageCardView.getContext().getResources().getDimension(R.dimen.f25470_resource_name_obfuscated_RES_2131166166), 0, 0, 0);
            } else if (messageCardView.indexOfChild(messageCardView.G) == -1) {
                messageCardView.addView(messageCardView.G, 0);
                marginLayoutParams.setMargins(0, 0, 0, 0);
            }
        } else {
            QH0 qh02 = AbstractC0516Ij0.m;
            if (qh02 == kh0) {
                boolean h2 = uh0.h(qh02);
                messageCardView.setBackgroundResource(h2 ? R.drawable.f33730_resource_name_obfuscated_RES_2131231413 : R.drawable.f33720_resource_name_obfuscated_RES_2131231412);
                AbstractC3153j7.i(messageCardView.H, h2 ? R.style.f72000_resource_name_obfuscated_RES_2132017773 : R.style.f71970_resource_name_obfuscated_RES_2132017770);
                AbstractC3153j7.i(messageCardView.I, h2 ? R.style.f70910_resource_name_obfuscated_RES_2132017664 : R.style.f70900_resource_name_obfuscated_RES_2132017663);
                ChromeImageView chromeImageView = messageCardView.f10779J;
                Context context = chromeImageView.getContext();
                int i = h2 ? R.color.f11320_resource_name_obfuscated_RES_2131099822 : R.color.f11220_resource_name_obfuscated_RES_2131099812;
                ThreadLocal threadLocal = AbstractC5544x8.f11592a;
                chromeImageView.setImageTintList(context.getColorStateList(i));
            }
        }
    }
}
