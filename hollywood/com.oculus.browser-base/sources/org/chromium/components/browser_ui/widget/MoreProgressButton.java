package org.chromium.components.browser_ui.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import com.oculus.browser.R;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class MoreProgressButton extends FrameLayout implements View.OnClickListener {
    public View F;
    public View G;
    public int H = -1;

    public MoreProgressButton(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void a(int i) {
        if (i != this.H) {
            this.H = i;
            int i2 = 0;
            this.G.setVisibility(1 == i ? 0 : 8);
            View view = this.F;
            if (2 != i) {
                i2 = 8;
            }
            view.setVisibility(i2);
        }
    }

    public void onClick(View view) {
        a(2);
    }

    public void onFinishInflate() {
        super.onFinishInflate();
        View findViewById = findViewById(R.id.action_button);
        this.G = findViewById;
        findViewById.setOnClickListener(this);
        this.F = findViewById(R.id.progress_spinner);
        a(0);
    }
}
