package org.chromium.chrome.browser.explore_sites;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.oculus.browser.R;
import java.util.ArrayList;
import org.chromium.chrome.browser.suggestions.tile.TileGridLayout;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class ExploreSitesCategoryCardView extends LinearLayout {
    public ExploreSitesCategoryCardView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        new ArrayList();
    }

    public void onFinishInflate() {
        super.onFinishInflate();
        TextView textView = (TextView) findViewById(R.id.category_title);
        TileGridLayout tileGridLayout = (TileGridLayout) findViewById(R.id.category_sites);
    }
}
