package org.chromium.chrome.browser.download.home.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import com.oculus.browser.R;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class SelectionView extends FrameLayout {
    public SelectionView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        LayoutInflater.from(context).inflate(R.layout.f39140_resource_name_obfuscated_RES_2131624223, (ViewGroup) this, true);
        ImageView imageView = (ImageView) findViewById(R.id.check);
        ImageView imageView2 = (ImageView) findViewById(R.id.circle);
        L6.a(context, R.drawable.f29740_resource_name_obfuscated_RES_2131231014);
    }

    public boolean isSelected() {
        return false;
    }
}
