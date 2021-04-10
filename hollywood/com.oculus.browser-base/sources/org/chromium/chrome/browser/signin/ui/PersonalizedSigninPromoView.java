package org.chromium.chrome.browser.signin.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.oculus.browser.R;
import org.chromium.ui.widget.ButtonCompat;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class PersonalizedSigninPromoView extends LinearLayout {
    public ImageView F;
    public ImageButton G;
    public TextView H;
    public TextView I;

    /* renamed from: J  reason: collision with root package name */
    public ButtonCompat f10765J;
    public Button K;

    public PersonalizedSigninPromoView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void onFinishInflate() {
        super.onFinishInflate();
        this.F = (ImageView) findViewById(R.id.signin_promo_image);
        this.G = (ImageButton) findViewById(R.id.signin_promo_close_button);
        this.H = (TextView) findViewById(R.id.signin_promo_status_message);
        this.I = (TextView) findViewById(R.id.signin_promo_description);
        this.f10765J = (ButtonCompat) findViewById(R.id.signin_promo_signin_button);
        this.K = (Button) findViewById(R.id.signin_promo_choose_account_button);
    }
}
