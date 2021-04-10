package defpackage;

import android.content.Context;
import android.util.AttributeSet;
import com.oculus.browser.R;

/* renamed from: Os1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class Os1 extends C4011o8 {
    public boolean I;

    public Os1(Context context, AttributeSet attributeSet) {
        super(context, attributeSet, R.attr.f4110_resource_name_obfuscated_RES_2130968857);
    }

    /* JADX INFO: finally extract failed */
    public boolean bringPointIntoView(int i) {
        try {
            this.I = true;
            boolean bringPointIntoView = super.bringPointIntoView(i);
            this.I = false;
            return bringPointIntoView;
        } catch (Throwable th) {
            this.I = false;
            throw th;
        }
    }

    public void scrollTo(int i, int i2) {
        if (!this.I) {
            i2 = getScrollY();
        }
        super.scrollTo(i, i2);
    }
}
