package org.chromium.components.translate;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.oculus.browser.R;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class TranslateTabContent extends FrameLayout {
    public TextView F;
    public ProgressBar G;

    public TranslateTabContent(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void onFinishInflate() {
        super.onFinishInflate();
        this.F = (TextView) findViewById(R.id.translate_infobar_tab_text);
        this.G = (ProgressBar) findViewById(R.id.translate_infobar_tab_progressbar);
    }
}
