package defpackage;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import org.chromium.base.task.PostTask;

/* renamed from: q60  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C4348q60 extends AnimatorListenerAdapter {
    public final /* synthetic */ PopupWindow F;
    public final /* synthetic */ ViewGroup G;

    public C4348q60(PopupWindow popupWindow, ViewGroup viewGroup) {
        this.F = popupWindow;
        this.G = viewGroup;
    }

    public void onAnimationEnd(Animator animator) {
        PostTask.b(Zo1.f9374a, new RunnableC4177p60(this), 0);
    }
}
