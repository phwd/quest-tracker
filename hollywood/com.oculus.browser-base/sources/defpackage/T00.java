package defpackage;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;
import com.oculus.browser.R;
import org.chromium.chrome.browser.ui.tablet.emptybackground.incognitotoggle.IncognitoToggleButtonTablet;
import org.chromium.ui.widget.ChromeImageButton;

/* renamed from: T00  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class T00 extends ChromeImageButton {
    public AbstractC0124Ca1 H;
    public AbstractC0612Ka1 I;

    public T00(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public final void d() {
        AbstractC0124Ca1 ca1 = this.H;
        if (ca1 != null && ((AbstractC0246Ea1) ca1).i() != null) {
            setContentDescription(getContext().getString(((AbstractC0246Ea1) this.H).r() ? R.string.f46050_resource_name_obfuscated_RES_2131951922 : R.string.f46060_resource_name_obfuscated_RES_2131951923));
            ((IncognitoToggleButtonTablet) this).setImageResource(((AbstractC0246Ea1) this.H).r() ? R.drawable.f33520_resource_name_obfuscated_RES_2131231392 : R.drawable.f28640_resource_name_obfuscated_RES_2131230904);
        }
    }

    public void onFinishInflate() {
        super.onFinishInflate();
        setScaleType(ImageView.ScaleType.CENTER);
    }
}
