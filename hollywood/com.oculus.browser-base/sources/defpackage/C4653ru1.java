package defpackage;

import java.io.Closeable;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/* renamed from: ru1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C4653ru1 {

    /* renamed from: a  reason: collision with root package name */
    public final HashMap f11233a = new HashMap();

    public final void a() {
        for (AbstractC4312pu1 pu1 : this.f11233a.values()) {
            Map map = pu1.f11098a;
            if (map != null) {
                synchronized (map) {
                    for (Object obj : pu1.f11098a.values()) {
                        if (obj instanceof Closeable) {
                            try {
                                ((Closeable) obj).close();
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                        }
                    }
                }
            }
            pu1.a();
        }
        this.f11233a.clear();
    }
}
