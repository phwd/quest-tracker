package defpackage;

import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.oculus.browser.R;

/* renamed from: Ix0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C0544Ix0 extends AbstractC5180v0 {
    public C0544Ix0(ViewGroup viewGroup) {
        super(viewGroup, R.layout.f40250_resource_name_obfuscated_RES_2131624334);
    }

    @Override // defpackage.AbstractC5180v0
    public void y(Object obj, View view) {
        String str = (String) obj;
        TextView textView = (TextView) ((LinearLayout) view).findViewById(R.id.tab_title);
        textView.setText(str);
        textView.setContentDescription(str);
    }
}
