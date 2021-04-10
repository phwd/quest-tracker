package org.chromium.chrome.browser.explore_sites;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.oculus.browser.R;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class ExperimentalExploreSitesCategoryTileView extends LinearLayout {
    public ExperimentalExploreSitesCategoryTileView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        context.getResources();
    }

    public void onFinishInflate() {
        super.onFinishInflate();
        TextView textView = (TextView) findViewById(R.id.experimental_explore_sites_category_tile_title);
        ImageView imageView = (ImageView) findViewById(R.id.experimental_explore_sites_category_tile_icon);
    }
}
