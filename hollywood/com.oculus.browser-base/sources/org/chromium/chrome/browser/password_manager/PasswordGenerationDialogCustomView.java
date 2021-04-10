package org.chromium.chrome.browser.password_manager;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.oculus.browser.R;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class PasswordGenerationDialogCustomView extends LinearLayout {
    public TextView F;
    public TextView G;

    public PasswordGenerationDialogCustomView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void onFinishInflate() {
        super.onFinishInflate();
        this.F = (TextView) findViewById(R.id.generated_password);
        this.G = (TextView) findViewById(R.id.generation_save_explanation);
    }
}
