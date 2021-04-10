package defpackage;

import java.util.Collections;
import java.util.Map;

/* renamed from: UM  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class UM {

    /* renamed from: a  reason: collision with root package name */
    public static volatile UM f9024a;
    public static final UM b = new UM();
    public final Map c = Collections.emptyMap();

    public static UM a() {
        UM um = f9024a;
        if (um == null) {
            synchronized (UM.class) {
                um = f9024a;
                if (um == null) {
                    Class cls = TM.f8953a;
                    UM um2 = null;
                    if (cls != null) {
                        try {
                            um2 = (UM) cls.getDeclaredMethod("getEmptyRegistry", new Class[0]).invoke(null, new Object[0]);
                        } catch (Exception unused) {
                        }
                    }
                    if (um2 == null) {
                        um2 = b;
                    }
                    f9024a = um2;
                    um = um2;
                }
            }
        }
        return um;
    }
}
