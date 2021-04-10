package defpackage;

import android.view.MotionEvent;
import android.view.View;
import android.widget.AutoCompleteTextView;

/* renamed from: vJ  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class View$OnTouchListenerC5237vJ implements View.OnTouchListener {
    public final /* synthetic */ AutoCompleteTextView F;
    public final /* synthetic */ AJ G;

    public View$OnTouchListenerC5237vJ(AJ aj, AutoCompleteTextView autoCompleteTextView) {
        this.G = aj;
        this.F = autoCompleteTextView;
    }

    public boolean onTouch(View view, MotionEvent motionEvent) {
        if (motionEvent.getAction() == 1) {
            if (this.G.h()) {
                this.G.g = false;
            }
            AJ.f(this.G, this.F);
        }
        return false;
    }
}
