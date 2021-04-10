package defpackage;

import java.util.ArrayList;

/* renamed from: eK  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class RunnableC2338eK implements Runnable {
    public final View$OnClickListenerC3876nK F;

    public RunnableC2338eK(View$OnClickListenerC3876nK nKVar) {
        this.F = nKVar;
    }

    public void run() {
        View$OnClickListenerC3876nK nKVar = this.F;
        ArrayList arrayList = (ArrayList) nKVar.c(false);
        if (!arrayList.isEmpty()) {
            ((AbstractC4899tK) arrayList.get(0)).d();
        } else {
            nKVar.Q.requestFocus();
        }
        if (nKVar.getCurrentFocus() != null) {
            C3493l60.F.i(nKVar.getCurrentFocus());
        }
    }
}
