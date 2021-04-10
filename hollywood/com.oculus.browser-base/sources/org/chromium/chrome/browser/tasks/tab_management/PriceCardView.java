package org.chromium.chrome.browser.tasks.tab_management;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.TextView;
import com.oculus.browser.R;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class PriceCardView extends FrameLayout {
    public TextView F;
    public TextView G;

    public PriceCardView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void onFinishInflate() {
        super.onFinishInflate();
        LayoutInflater.from(getContext()).inflate(R.layout.f40890_resource_name_obfuscated_RES_2131624398, this);
        this.F = (TextView) findViewById(R.id.current_price);
        TextView textView = (TextView) findViewById(R.id.previous_price);
        this.G = textView;
        textView.setPaintFlags(textView.getPaintFlags() | 16);
    }
}
