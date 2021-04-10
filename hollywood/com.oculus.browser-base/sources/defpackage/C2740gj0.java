package defpackage;

import java.nio.ByteBuffer;
import java.util.List;

/* renamed from: gj0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C2740gj0 {

    /* renamed from: a  reason: collision with root package name */
    public final ByteBuffer f10015a;
    public final List b;
    public IS0 c;

    public C2740gj0(ByteBuffer byteBuffer, List list) {
        this.f10015a = byteBuffer;
        this.b = list;
    }

    public IS0 a() {
        if (this.c == null) {
            this.c = new IS0(this);
        }
        return this.c;
    }
}
