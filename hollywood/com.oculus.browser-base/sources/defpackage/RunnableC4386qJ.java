package defpackage;

import android.widget.AutoCompleteTextView;

/* renamed from: qJ  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class RunnableC4386qJ implements Runnable {
    public final /* synthetic */ AutoCompleteTextView F;
    public final /* synthetic */ C4556rJ G;

    public RunnableC4386qJ(C4556rJ rJVar, AutoCompleteTextView autoCompleteTextView) {
        this.G = rJVar;
        this.F = autoCompleteTextView;
    }

    public void run() {
        boolean isPopupShowing = this.F.isPopupShowing();
        AJ.e(this.G.F, isPopupShowing);
        this.G.F.g = isPopupShowing;
    }
}
