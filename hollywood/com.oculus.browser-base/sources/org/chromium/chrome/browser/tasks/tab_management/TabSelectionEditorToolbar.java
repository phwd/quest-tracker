package org.chromium.chrome.browser.tasks.tab_management;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.Button;
import com.oculus.browser.R;
import java.util.Collections;
import java.util.List;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class TabSelectionEditorToolbar extends AbstractView$OnClickListenerC2014cS0 {
    public static final List b1 = Collections.emptyList();
    public Button c1;
    public Integer d1;
    public int e1;
    public int f1 = 2;

    public TabSelectionEditorToolbar(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    @Override // defpackage.AbstractView$OnClickListenerC2014cS0
    public void S(int i) {
    }

    @Override // defpackage.AbstractView$OnClickListenerC2014cS0
    public void U() {
        super.W(b1, true);
        int i = this.e1;
        if (i != 0) {
            setBackgroundColor(i);
        }
    }

    @Override // defpackage.AbstractView$OnClickListenerC2014cS0
    public void W(List list, boolean z) {
        super.W(list, z);
        int i = this.e1;
        if (i != 0) {
            setBackgroundColor(i);
        }
    }

    @Override // defpackage.AbstractView$OnClickListenerC2014cS0, defpackage.AbstractC3039iS0
    public void b(List list) {
        super.b(list);
        int size = list.size();
        boolean z = size >= this.f1;
        this.c1.setEnabled(z);
        String str = null;
        if (z && this.d1 != null) {
            str = getContext().getResources().getQuantityString(this.d1.intValue(), size, Integer.valueOf(size));
        }
        this.c1.setContentDescription(str);
    }

    @Override // defpackage.AbstractView$OnClickListenerC2014cS0
    public void onFinishInflate() {
        super.onFinishInflate();
        C0636Ki1 a2 = C0636Ki1.a(getContext(), R.drawable.f29550_resource_name_obfuscated_RES_2131230995);
        Context context = getContext();
        ThreadLocal threadLocal = AbstractC5544x8.f11592a;
        a2.c(context.getColorStateList(R.color.f11310_resource_name_obfuscated_RES_2131099821));
        D(a2);
        B(AbstractC4772sd1.d() ? R.string.f45900_resource_name_obfuscated_RES_2131951907 : R.string.f49160_resource_name_obfuscated_RES_2131952233);
        this.c1 = (Button) findViewById(R.id.action_button);
        this.E0.L = R.string.f63180_resource_name_obfuscated_RES_2131953635;
    }
}
