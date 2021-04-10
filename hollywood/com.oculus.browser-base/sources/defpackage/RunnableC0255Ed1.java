package defpackage;

import android.content.Context;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import com.oculus.browser.R;
import org.chromium.chrome.browser.tab.TabImpl;

/* renamed from: Ed1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class RunnableC0255Ed1 implements Runnable {
    public final C3372kO0 F;

    public RunnableC0255Ed1(C3372kO0 ko0) {
        this.F = ko0;
    }

    public void run() {
        SpannableString spannableString;
        C3372kO0 ko0 = this.F;
        TabImpl tabImpl = ko0.F;
        if (tabImpl.L != null) {
            boolean z = ko0.H >= 1;
            RunnableC2860hO0 ho0 = new RunnableC2860hO0(ko0);
            RunnableC3031iO0 io0 = new RunnableC3031iO0(ko0, z);
            boolean z2 = tabImpl.H;
            Context context = tabImpl.I;
            View inflate = ((LayoutInflater) context.getSystemService("layout_inflater")).inflate(R.layout.f41220_resource_name_obfuscated_RES_2131624431, (ViewGroup) null);
            inflate.setLayoutParams(new ViewGroup.LayoutParams(-1, -1));
            ((TextView) inflate.findViewById(R.id.sad_tab_title)).setText(z ? R.string.f60360_resource_name_obfuscated_RES_2131953353 : R.string.f60400_resource_name_obfuscated_RES_2131953357);
            if (z) {
                TextView textView = (TextView) inflate.findViewById(R.id.sad_tab_suggestions_title);
                textView.setVisibility(0);
                textView.setText(R.string.f60370_resource_name_obfuscated_RES_2131953354);
                TextView textView2 = (TextView) inflate.findViewById(R.id.sad_tab_suggestions);
                textView2.setVisibility(0);
                SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
                if (!z2) {
                    spannableStringBuilder.append((CharSequence) C3372kO0.V(context, R.string.f60310_resource_name_obfuscated_RES_2131953348)).append((CharSequence) "\n");
                }
                spannableStringBuilder.append((CharSequence) C3372kO0.V(context, R.string.f60340_resource_name_obfuscated_RES_2131953351)).append((CharSequence) "\n").append((CharSequence) C3372kO0.V(context, R.string.f60350_resource_name_obfuscated_RES_2131953352)).append((CharSequence) "\n");
                textView2.setText(spannableStringBuilder);
            }
            TextView textView3 = (TextView) inflate.findViewById(R.id.sad_tab_message);
            C4467qp0 qp0 = new C4467qp0(context.getResources(), new C2689gO0(z, ho0));
            if (z) {
                spannableString = new SpannableString(context.getString(R.string.f60330_resource_name_obfuscated_RES_2131953350));
                spannableString.setSpan(qp0, 0, spannableString.length(), 0);
            } else {
                spannableString = FY0.a(context.getString(R.string.f60300_resource_name_obfuscated_RES_2131953347) + "\n\n" + context.getString(R.string.f60390_resource_name_obfuscated_RES_2131953356), new EY0("<link>", "</link>", qp0));
            }
            textView3.setText(spannableString);
            textView3.setMovementMethod(LinkMovementMethod.getInstance());
            Button button = (Button) inflate.findViewById(R.id.sad_tab_button);
            button.setText(z ? R.string.f60380_resource_name_obfuscated_RES_2131953355 : R.string.f60320_resource_name_obfuscated_RES_2131953349);
            button.setOnClickListener(new View$OnClickListenerC3201jO0(ko0, z, io0));
            C3372kO0.X(z, 0);
            ko0.G = inflate;
            ko0.H++;
            ko0.F.O.a(ko0);
        }
    }
}
