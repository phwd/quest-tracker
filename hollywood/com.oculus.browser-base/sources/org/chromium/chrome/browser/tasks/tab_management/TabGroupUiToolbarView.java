package org.chromium.chrome.browser.tasks.tab_management;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import com.oculus.browser.R;
import org.chromium.ui.widget.ChromeImageView;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class TabGroupUiToolbarView extends FrameLayout {
    public ChromeImageView F;
    public ChromeImageView G;
    public ChromeImageView H;
    public ChromeImageView I;

    /* renamed from: J  reason: collision with root package name */
    public ChromeImageView f10783J;
    public ViewGroup K;
    public EditText L;
    public LinearLayout M;

    public TabGroupUiToolbarView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void a(int i) {
        this.M.setBackgroundColor(i);
        ChromeImageView chromeImageView = this.I;
        if (chromeImageView != null && this.f10783J != null) {
            chromeImageView.setColorFilter(i, PorterDuff.Mode.SRC_IN);
            this.f10783J.setColorFilter(i, PorterDuff.Mode.SRC_IN);
        }
    }

    public void b(ColorStateList colorStateList) {
        this.G.setImageTintList(colorStateList);
        this.F.setImageTintList(colorStateList);
        EditText editText = this.L;
        if (editText != null) {
            editText.setTextColor(colorStateList);
        }
        ChromeImageView chromeImageView = this.H;
        if (chromeImageView != null) {
            chromeImageView.setImageTintList(colorStateList);
        }
    }

    public void onFinishInflate() {
        super.onFinishInflate();
        this.G = (ChromeImageView) findViewById(R.id.toolbar_left_button);
        this.F = (ChromeImageView) findViewById(R.id.toolbar_right_button);
        this.H = (ChromeImageView) findViewById(R.id.toolbar_menu_button);
        this.I = (ChromeImageView) findViewById(R.id.tab_strip_fading_edge_start);
        this.f10783J = (ChromeImageView) findViewById(R.id.tab_strip_fading_edge_end);
        this.K = (ViewGroup) findViewById(R.id.toolbar_container_view);
        this.L = (EditText) findViewById(R.id.title);
        this.M = (LinearLayout) findViewById(R.id.main_content);
    }
}
