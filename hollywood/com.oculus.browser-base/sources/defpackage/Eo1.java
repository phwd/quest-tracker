package defpackage;

import android.widget.NumberPicker;

/* renamed from: Eo1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class Eo1 implements NumberPicker.OnValueChangeListener {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ Go1 f7978a;

    public Eo1(Go1 go1) {
        this.f7978a = go1;
    }

    public void onValueChange(NumberPicker numberPicker, int i, int i2) {
        int i3;
        int g = this.f7978a.g();
        int f = this.f7978a.f();
        Go1 go1 = this.f7978a;
        if (numberPicker == go1.F) {
            if (i == numberPicker.getMaxValue() && i2 == numberPicker.getMinValue()) {
                i2 = g + 1;
                i3 = this.f7978a.d(i2);
            } else if (i == numberPicker.getMinValue() && i2 == numberPicker.getMaxValue()) {
                i2 = g - 1;
                i3 = this.f7978a.b(i2);
            } else {
                i3 = i2;
                i2 = g;
            }
        } else if (numberPicker == go1.G) {
            i3 = f;
        } else {
            throw new IllegalArgumentException();
        }
        this.f7978a.i(i2, i3);
        this.f7978a.j();
        Go1 go12 = this.f7978a;
        go12.sendAccessibilityEvent(4);
        Fo1 fo1 = go12.H;
        if (fo1 != null) {
            int g2 = go12.g();
            int f2 = go12.f();
            Go1 go13 = ((Ho1) fo1).F;
            go13.i(g2, f2);
            go13.j();
            go13.H = null;
        }
    }
}
