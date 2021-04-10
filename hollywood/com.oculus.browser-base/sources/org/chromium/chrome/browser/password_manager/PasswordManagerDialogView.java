package org.chromium.chrome.browser.password_manager;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import com.oculus.browser.R;
import org.chromium.ui.widget.ChromeImageButton;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class PasswordManagerDialogView extends ScrollView {
    public static final /* synthetic */ int F = 0;
    public ChromeImageButton G;
    public ChromeImageButton H;
    public ImageView I;

    /* renamed from: J  reason: collision with root package name */
    public TextView f10739J;
    public TextView K;

    public PasswordManagerDialogView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void onFinishInflate() {
        super.onFinishInflate();
        this.G = (ChromeImageButton) findViewById(R.id.password_dialog_help_button);
        this.H = (ChromeImageButton) findViewById(R.id.password_dialog_inline_help_button);
        this.I = (ImageView) findViewById(R.id.password_manager_dialog_illustration);
        this.f10739J = (TextView) findViewById(R.id.password_manager_dialog_title);
        this.K = (TextView) findViewById(R.id.password_manager_dialog_details);
    }
}
