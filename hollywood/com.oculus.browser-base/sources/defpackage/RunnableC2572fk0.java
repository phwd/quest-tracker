package defpackage;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.oculus.browser.R;
import org.chromium.components.messages.MessageBannerView;
import org.chromium.components.messages.MessageContainer;

/* renamed from: fk0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class RunnableC2572fk0 implements Runnable {
    public final AbstractC4452qk0 F;

    public RunnableC2572fk0(AbstractC4452qk0 qk0) {
        this.F = qk0;
    }

    public void run() {
        WW0 ww0 = (WW0) this.F;
        if (ww0.f9152a == null) {
            MessageBannerView messageBannerView = (MessageBannerView) LayoutInflater.from(ww0.c.getContext()).inflate(R.layout.f39280_resource_name_obfuscated_RES_2131624237, (ViewGroup) ww0.c, false);
            ww0.b = messageBannerView;
            ww0.f9152a = new C3594lj0(messageBannerView, ww0.d, ww0.g, ww0.c.getResources(), new QW0(ww0), ww0.i);
        }
        MessageContainer messageContainer = ww0.c;
        MessageBannerView messageBannerView2 = ww0.b;
        if (messageContainer.getChildCount() == 0) {
            messageContainer.addView(messageBannerView2);
            AbstractC4656rv1.f(messageContainer, false);
            RW0 rw0 = new RW0(ww0);
            MessageContainer messageContainer2 = ww0.c;
            View findViewById = messageContainer2.findViewById(R.id.message_banner);
            if (findViewById.getHeight() > 0) {
                rw0.run();
            } else {
                findViewById.addOnLayoutChangeListener(new View$OnLayoutChangeListenerC0577Jj0(messageContainer2, rw0));
            }
        } else {
            throw new IllegalStateException("Should not contain any view when adding a new message.");
        }
    }
}
