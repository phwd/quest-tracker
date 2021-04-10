package org.chromium.chrome.browser.tasks.tab_management;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.TextView;
import com.oculus.browser.R;
import java.lang.ref.WeakReference;
import org.chromium.ui.widget.ButtonCompat;
import org.chromium.ui.widget.ChromeImageView;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class PriceWelcomeMessageCardView extends FrameLayout {
    public static WeakReference F;
    public PriceCardView G;
    public TextView H;
    public TextView I;

    /* renamed from: J  reason: collision with root package name */
    public ButtonCompat f10780J;
    public ChromeImageView K;

    public PriceWelcomeMessageCardView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void onFinishInflate() {
        super.onFinishInflate();
        this.G = (PriceCardView) findViewById(R.id.price_info_box);
        this.H = (TextView) findViewById(R.id.title);
        this.I = (TextView) findViewById(R.id.content);
        this.f10780J = (ButtonCompat) findViewById(R.id.action_button);
        this.K = (ChromeImageView) findViewById(R.id.close_button);
        WeakReference weakReference = F;
        if (weakReference == null || weakReference.get() == null) {
            int dimension = (int) getResources().getDimension(R.dimen.f25380_resource_name_obfuscated_RES_2131166157);
            F = new WeakReference(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.f28430_resource_name_obfuscated_RES_2131230883), dimension, dimension, true));
        }
        this.K.setImageBitmap((Bitmap) F.get());
    }
}
