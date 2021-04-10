package org.chromium.chrome.browser.tasks;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.oculus.browser.R;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class SingleTabView extends LinearLayout {
    public ImageView F;
    public TextView G;

    public SingleTabView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void onFinishInflate() {
        super.onFinishInflate();
        this.F = (ImageView) findViewById(R.id.tab_favicon_view);
        this.G = (TextView) findViewById(R.id.tab_title_view);
    }
}
