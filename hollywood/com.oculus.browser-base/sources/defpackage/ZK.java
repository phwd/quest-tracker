package defpackage;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.List;

/* renamed from: ZK  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class ZK {

    /* renamed from: a  reason: collision with root package name */
    public final SA f9338a;
    public ByteBuffer b;
    public final List c = new ArrayList();
    public int d;

    public ZK(SA sa, int i, YK yk) {
        this.f9338a = sa;
        ByteBuffer allocateDirect = ByteBuffer.allocateDirect(i <= 0 ? 1024 : i);
        this.b = allocateDirect;
        allocateDirect.order(ByteOrder.LITTLE_ENDIAN);
        this.d = 0;
    }
}
