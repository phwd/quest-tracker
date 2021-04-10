package org.chromium.components.messages;

import org.chromium.content_public.browser.WebContents;
import org.chromium.ui.base.WindowAndroid;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class MessageDispatcherBridge {
    public static void dismissMessage(MessageWrapper messageWrapper, WebContents webContents, int i) {
        ((C0820Nj0) ((AbstractC0699Lj0) AbstractC0881Oj0.f8643a.e(webContents.I().U))).k(messageWrapper.b, i);
    }

    public static void enqueueMessage(MessageWrapper messageWrapper, WebContents webContents) {
        WindowAndroid I = webContents.I();
        UH0 uh0 = messageWrapper.b;
        C0820Nj0 nj0 = (C0820Nj0) ((AbstractC0699Lj0) AbstractC0881Oj0.f8643a.e(I.U));
        WW0 ww0 = new WW0(nj0.G, uh0, new C0759Mj0(nj0), nj0.H, nj0.I, nj0.f8571J);
        C3084ik0 ik0 = nj0.F;
        if (!ik0.b.containsKey(uh0)) {
            ik0.b.put(uh0, ww0);
            ik0.f10159a.add(ww0);
            ik0.a();
            return;
        }
        throw new IllegalStateException("Message with the given key has already been enqueued");
    }
}
