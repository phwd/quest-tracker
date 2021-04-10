package org.chromium.url;

import java.nio.ByteBuffer;
import java.util.ArrayList;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class Origin {

    /* renamed from: a  reason: collision with root package name */
    public final C4479qt0 f11030a;

    public Origin(ByteBuffer byteBuffer) {
        CC[] ccArr = C4479qt0.b;
        this.f11030a = C4479qt0.d(new C4709sD(new C2740gj0(byteBuffer, new ArrayList())));
    }

    public static ByteBuffer serialize(Origin origin) {
        return origin.f11030a.b();
    }

    public boolean a() {
        return this.f11030a.g != null;
    }
}
