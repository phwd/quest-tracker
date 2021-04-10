package defpackage;

import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.oculus.browser.R;

/* renamed from: A0  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class A0 extends AbstractC5180v0 {
    public A0(ViewGroup viewGroup) {
        super(viewGroup, R.layout.f38990_resource_name_obfuscated_RES_2131624208);
    }

    @Override // defpackage.AbstractC5180v0
    public void y(Object obj, View view) {
        String str = (String) obj;
        TextView textView = (TextView) ((LinearLayout) view).findViewById(R.id.tab_title);
        textView.setText(str);
        textView.setContentDescription(str);
    }

    public A0(ViewGroup viewGroup, int i) {
        super(viewGroup, i);
    }
}
