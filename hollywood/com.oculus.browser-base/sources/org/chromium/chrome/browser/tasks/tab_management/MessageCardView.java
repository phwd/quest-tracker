package org.chromium.chrome.browser.tasks.tab_management;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import com.oculus.browser.R;
import java.lang.ref.WeakReference;
import org.chromium.components.browser_ui.widget.text.TemplatePreservingTextView;
import org.chromium.ui.widget.ButtonCompat;
import org.chromium.ui.widget.ChromeImageView;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class MessageCardView extends LinearLayout {
    public static WeakReference F;
    public ChromeImageView G;
    public TemplatePreservingTextView H;
    public ButtonCompat I;

    /* renamed from: J  reason: collision with root package name */
    public ChromeImageView f10779J;

    public MessageCardView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void onFinishInflate() {
        super.onFinishInflate();
        this.G = (ChromeImageView) findViewById(R.id.icon);
        this.H = (TemplatePreservingTextView) findViewById(R.id.description);
        this.I = (ButtonCompat) findViewById(R.id.action_button);
        this.f10779J = (ChromeImageView) findViewById(R.id.close_button);
        WeakReference weakReference = F;
        if (weakReference == null || weakReference.get() == null) {
            int dimension = (int) getResources().getDimension(R.dimen.f25380_resource_name_obfuscated_RES_2131166157);
            F = new WeakReference(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.f28430_resource_name_obfuscated_RES_2131230883), dimension, dimension, true));
        }
        this.f10779J.setImageBitmap((Bitmap) F.get());
    }
}
