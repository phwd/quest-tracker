package defpackage;

import android.view.View;
import android.widget.AutoCompleteTextView;
import java.util.concurrent.atomic.AtomicInteger;

/* renamed from: AK  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class AK implements View.OnLayoutChangeListener {
    public final /* synthetic */ DK F;

    public AK(DK dk) {
        this.F = dk;
    }

    public void onLayoutChange(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
        AutoCompleteTextView autoCompleteTextView = this.F.f7881J;
        AtomicInteger atomicInteger = AbstractC1920bu1.f9571a;
        autoCompleteTextView.setPaddingRelative(autoCompleteTextView.getPaddingStart(), this.F.f7881J.getPaddingTop(), this.F.K.getWidth(), this.F.f7881J.getPaddingBottom());
    }
}
