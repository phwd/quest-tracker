package org.chromium.chrome.browser.ntp;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import com.oculus.browser.R;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class IncognitoNewTabPageView extends FrameLayout {
    public static final /* synthetic */ int F = 0;
    public boolean G = true;
    public NewTabPageScrollView H;
    public IncognitoDescriptionView I;

    public IncognitoNewTabPageView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (this.G) {
            throw null;
        }
    }

    public void onFinishInflate() {
        super.onFinishInflate();
        NewTabPageScrollView newTabPageScrollView = (NewTabPageScrollView) findViewById(R.id.ntp_scrollview);
        this.H = newTabPageScrollView;
        newTabPageScrollView.setBackgroundColor(getResources().getColor(R.color.f14380_resource_name_obfuscated_RES_2131100128));
        setContentDescription(getResources().getText(R.string.f45580_resource_name_obfuscated_RES_2131951875));
        this.H.setDescendantFocusability(131072);
        IncognitoDescriptionView incognitoDescriptionView = (IncognitoDescriptionView) findViewById(R.id.new_tab_incognito_container);
        this.I = incognitoDescriptionView;
        incognitoDescriptionView.M.setOnClickListener(new View$OnClickListenerC4671s00(this));
    }
}
