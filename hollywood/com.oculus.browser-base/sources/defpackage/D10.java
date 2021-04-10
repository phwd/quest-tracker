package defpackage;

import android.content.Context;
import android.content.res.Resources;
import android.view.View;
import android.widget.FrameLayout;
import com.oculus.browser.R;

/* renamed from: D10  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class D10 extends FrameLayout {
    public final C10 F;

    public D10(Context context, C10 c10) {
        super(context);
        this.F = c10;
        Resources resources = context.getResources();
        int dimensionPixelSize = resources.getDimensionPixelSize(R.dimen.f19990_resource_name_obfuscated_RES_2131165618);
        int dimensionPixelSize2 = resources.getDimensionPixelSize(R.dimen.f20000_resource_name_obfuscated_RES_2131165619);
        setMinimumHeight(dimensionPixelSize + dimensionPixelSize2);
        setBackgroundResource(R.drawable.f33440_resource_name_obfuscated_RES_2131231384);
        setPadding(0, dimensionPixelSize2, 0, 0);
    }

    public void onViewAdded(View view) {
        view.setLayoutParams(new FrameLayout.LayoutParams(-1, -2, 48));
    }
}
