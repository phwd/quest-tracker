package org.chromium.content_public.browser;

import android.os.Handler;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public interface MessagePort {
    boolean a();

    void b(FE0 fe0, Handler handler);

    boolean c();

    void close();

    void d(String str, MessagePort[] messagePortArr);

    boolean isClosed();
}
