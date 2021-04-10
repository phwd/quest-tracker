package defpackage;

import org.chromium.chrome.browser.signin.ui.ConfirmSyncDataStateMachineDelegate$TimeoutDialogFragment;

/* renamed from: Ax  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class RunnableC0055Ax implements Runnable {
    public final C0177Cx F;

    public RunnableC0055Ax(C0177Cx cx) {
        this.F = cx;
    }

    public void run() {
        C0177Cx cx = this.F;
        C0421Gx gx = cx.e;
        C0116Bx bx = new C0116Bx(cx);
        gx.a();
        ConfirmSyncDataStateMachineDelegate$TimeoutDialogFragment confirmSyncDataStateMachineDelegate$TimeoutDialogFragment = new ConfirmSyncDataStateMachineDelegate$TimeoutDialogFragment();
        confirmSyncDataStateMachineDelegate$TimeoutDialogFragment.N0 = bx;
        gx.c(confirmSyncDataStateMachineDelegate$TimeoutDialogFragment, "ConfirmSyncProgressDialog");
    }
}
