package defpackage;

/* renamed from: sf0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class RunnableC4777sf0 implements Runnable {
    public final /* synthetic */ DialogC0504If0 F;

    public RunnableC4777sf0(DialogC0504If0 if0) {
        this.F = if0;
    }

    public void run() {
        DialogC0504If0 if0 = this.F;
        if0.g(true);
        if0.i0.requestLayout();
        if0.i0.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver$OnGlobalLayoutListenerC4437qf0(if0));
    }
}
