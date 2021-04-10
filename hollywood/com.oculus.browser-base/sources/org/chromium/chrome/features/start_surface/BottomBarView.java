package org.chromium.chrome.features.start_surface;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import com.google.android.material.tabs.TabLayout;
import com.oculus.browser.R;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class BottomBarView extends FrameLayout {
    public TabLayout F;
    public D81 G;
    public D81 H;
    public H01 I;

    public BottomBarView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void onFinishInflate() {
        super.onFinishInflate();
        TabLayout tabLayout = (TabLayout) findViewById(R.id.bottom_tab_layout);
        this.F = tabLayout;
        this.G = tabLayout.i(0);
        this.H = this.F.i(1);
        TabLayout tabLayout2 = this.F;
        C1705aj ajVar = new C1705aj(this);
        if (!tabLayout2.m0.contains(ajVar)) {
            tabLayout2.m0.add(ajVar);
        }
    }
}
