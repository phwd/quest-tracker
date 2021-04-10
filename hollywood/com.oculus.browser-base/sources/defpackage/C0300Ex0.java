package defpackage;

import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.oculus.browser.R;

/* renamed from: Ex0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C0300Ex0 extends AbstractC5180v0 {
    public C0300Ex0(ViewGroup viewGroup) {
        super(viewGroup, R.layout.f40260_resource_name_obfuscated_RES_2131624335);
    }

    @Override // defpackage.AbstractC5180v0
    public void y(Object obj, View view) {
        C2807h50 h50 = (C2807h50) obj;
        TextView textView = (TextView) ((LinearLayout) view).findViewById(R.id.footer_text);
        textView.setText(h50.f10047a);
        textView.setContentDescription(h50.f10047a);
        textView.setOnClickListener(new View$OnClickListenerC0178Cx0(h50));
        textView.setClickable(true);
    }
}
