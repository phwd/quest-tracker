package defpackage;

import android.content.res.Resources;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import com.oculus.browser.R;

/* renamed from: AA0  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class AA0 extends View {
    public AA0(ViewGroup viewGroup, int i) {
        super(viewGroup.getContext());
        Resources resources = viewGroup.getContext().getResources();
        setBackground(WX.a(getContext()));
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, resources.getDimensionPixelSize(R.dimen.f18710_resource_name_obfuscated_RES_2131165490));
        int dimensionPixelSize = resources.getDimensionPixelSize(R.dimen.f19000_resource_name_obfuscated_RES_2131165519);
        layoutParams.setMarginStart(dimensionPixelSize);
        layoutParams.setMarginEnd(dimensionPixelSize);
        viewGroup.addView(this, i, layoutParams);
    }
}
