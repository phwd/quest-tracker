package defpackage;

import android.view.ViewParent;

/* renamed from: aS  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class RunnableC1662aS implements Runnable {
    public final /* synthetic */ AbstractView$OnTouchListenerC2013cS F;

    public RunnableC1662aS(AbstractView$OnTouchListenerC2013cS cSVar) {
        this.F = cSVar;
    }

    public void run() {
        ViewParent parent = this.F.I.getParent();
        if (parent != null) {
            parent.requestDisallowInterceptTouchEvent(true);
        }
    }
}
