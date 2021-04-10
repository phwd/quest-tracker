package defpackage;

import android.view.View;
import org.chromium.components.messages.MessageContainer;

/* renamed from: Jj0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class View$OnLayoutChangeListenerC0577Jj0 implements View.OnLayoutChangeListener {
    public final /* synthetic */ Runnable F;

    public View$OnLayoutChangeListenerC0577Jj0(MessageContainer messageContainer, Runnable runnable) {
        this.F = runnable;
    }

    public void onLayoutChange(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
        if (view.getHeight() != 0) {
            this.F.run();
            view.removeOnLayoutChangeListener(this);
        }
    }
}
