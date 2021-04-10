package org.chromium.chrome.browser.signin.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.oculus.browser.R;
import org.chromium.ui.widget.ButtonCompat;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class SigninView extends LinearLayout {
    public SigninScrollView F;
    public ImageView G;
    public TextView H;
    public View I;

    /* renamed from: J  reason: collision with root package name */
    public ImageView f10766J;
    public TextView K;
    public TextView L;
    public ImageView M;
    public TextView N;
    public TextView O;
    public TextView P;
    public ButtonCompat Q;
    public Button R;
    public Button S;
    public View T;
    public O6 U;

    public SigninView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void onFinishInflate() {
        super.onFinishInflate();
        this.F = (SigninScrollView) findViewById(R.id.signin_scroll_view);
        this.G = (ImageView) findViewById(R.id.signin_header_image);
        this.H = (TextView) findViewById(R.id.signin_title);
        this.I = findViewById(R.id.signin_account_picker);
        this.f10766J = (ImageView) findViewById(R.id.account_image);
        this.K = (TextView) findViewById(R.id.account_text_primary);
        this.L = (TextView) findViewById(R.id.account_text_secondary);
        this.M = (ImageView) findViewById(R.id.account_picker_end_image);
        this.N = (TextView) findViewById(R.id.signin_sync_title);
        this.O = (TextView) findViewById(R.id.signin_sync_description);
        this.P = (TextView) findViewById(R.id.signin_details_description);
        this.Q = (ButtonCompat) findViewById(R.id.positive_button);
        this.R = (Button) findViewById(R.id.negative_button);
        this.S = (Button) findViewById(R.id.more_button);
        this.T = findViewById(R.id.positive_button_end_padding);
        this.U = new O6(this.G.getDrawable());
    }
}
