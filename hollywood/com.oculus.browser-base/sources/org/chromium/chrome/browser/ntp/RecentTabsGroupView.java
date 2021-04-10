package org.chromium.chrome.browser.ntp;

import android.content.Context;
import android.graphics.drawable.LevelListDrawable;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.oculus.browser.R;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class RecentTabsGroupView extends RelativeLayout {
    public ImageView F;

    public RecentTabsGroupView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void onFinishInflate() {
        super.onFinishInflate();
        RecentTabsGroupView recentTabsGroupView = (RecentTabsGroupView) getRootView().findViewById(R.id.recent_tabs_group_view);
        TextView textView = (TextView) findViewById(R.id.time_label);
        TextView textView2 = (TextView) findViewById(R.id.device_label);
        this.F = (ImageView) findViewById(R.id.expand_collapse_icon);
        LevelListDrawable levelListDrawable = new LevelListDrawable();
        levelListDrawable.addLevel(0, 0, C0636Ki1.a(getContext(), R.drawable.f30150_resource_name_obfuscated_RES_2131231055));
        levelListDrawable.addLevel(1, 1, C0636Ki1.a(getContext(), R.drawable.f30140_resource_name_obfuscated_RES_2131231054));
        this.F.setImageDrawable(levelListDrawable);
    }
}
