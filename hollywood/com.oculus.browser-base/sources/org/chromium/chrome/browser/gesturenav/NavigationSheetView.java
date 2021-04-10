package org.chromium.chrome.browser.gesturenav;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;
import android.widget.RelativeLayout;
import com.oculus.browser.R;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class NavigationSheetView extends RelativeLayout {
    public ListView F;

    public NavigationSheetView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void onFinishInflate() {
        super.onFinishInflate();
        this.F = (ListView) findViewById(R.id.navigation_entries);
    }
}
