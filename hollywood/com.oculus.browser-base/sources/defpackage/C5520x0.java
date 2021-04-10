package defpackage;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.oculus.browser.R;

/* renamed from: x0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C5520x0 extends AbstractC5180v0 {
    public C5520x0(ViewGroup viewGroup) {
        super(viewGroup, R.layout.f40270_resource_name_obfuscated_RES_2131624336);
    }

    @Override // defpackage.AbstractC5180v0
    public void y(Object obj, View view) {
        C2807h50 h50 = (C2807h50) obj;
        TextView textView = (TextView) view;
        textView.setText(h50.f10047a);
        textView.setContentDescription(h50.f10047a);
        textView.setOnClickListener(new View$OnClickListenerC5350w0(h50));
    }
}
