package defpackage;

import android.content.Context;
import android.view.View;
import android.widget.FrameLayout;
import com.oculus.browser.R;

/* renamed from: ZJ  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class ZJ extends FrameLayout {
    public C0079Bg F;
    public View G;

    public ZJ(Context context) {
        super(context, null);
        this.F = new C0079Bg(context, R.layout.f40100_resource_name_obfuscated_RES_2131624319);
        FrameLayout.LayoutParams generateDefaultLayoutParams = generateDefaultLayoutParams();
        generateDefaultLayoutParams.width = -1;
        generateDefaultLayoutParams.height = -2;
        addView(this.F, generateDefaultLayoutParams);
        this.G = new View(context, null, 0, R.style.f68660_resource_name_obfuscated_RES_2132017439);
        FrameLayout.LayoutParams generateDefaultLayoutParams2 = generateDefaultLayoutParams();
        generateDefaultLayoutParams2.gravity = 80;
        generateDefaultLayoutParams2.width = -1;
        generateDefaultLayoutParams2.height = getResources().getDimensionPixelSize(R.dimen.f18710_resource_name_obfuscated_RES_2131165490);
        addView(this.G, generateDefaultLayoutParams2);
    }

    public void setSelected(boolean z) {
        Runnable runnable;
        C0079Bg bg = this.F;
        bg.G.setSelected(z);
        if (z && (runnable = bg.H) != null) {
            runnable.run();
        }
    }
}
