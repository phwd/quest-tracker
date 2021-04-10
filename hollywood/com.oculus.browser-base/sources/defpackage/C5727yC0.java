package defpackage;

import org.chromium.components.browser_ui.photo_picker.PickerBitmapView;

/* renamed from: yC0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C5727yC0 extends XK0 implements AbstractC5729yD {
    public EC0 Z;
    public final PickerBitmapView a0;
    public C5557xC0 b0;

    public C5727yC0(PickerBitmapView pickerBitmapView) {
        super(pickerBitmapView);
        this.a0 = pickerBitmapView;
    }

    public String x() {
        C5557xC0 xc0 = this.b0;
        if (xc0 == null) {
            return null;
        }
        int i = xc0.H;
        if (i == 0 || i == 3) {
            return xc0.F.getPath();
        }
        return null;
    }
}
