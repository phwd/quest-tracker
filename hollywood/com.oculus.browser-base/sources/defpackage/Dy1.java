package defpackage;

import J.N;
import java.util.HashMap;
import java.util.Map;
import org.chromium.components.browser_ui.site_settings.WebsitePreferenceBridge;
import org.chromium.components.embedder_support.browser_context.BrowserContextHandle;

/* renamed from: Dy1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class Dy1 {

    /* renamed from: a  reason: collision with root package name */
    public BrowserContextHandle f7928a;
    public WebsitePreferenceBridge b;
    public final Map c = new HashMap();
    public final boolean d;

    public Dy1(BrowserContextHandle browserContextHandle, boolean z) {
        this.f7928a = browserContextHandle;
        this.d = z;
        this.b = new WebsitePreferenceBridge();
    }

    public static int e(int i) {
        if (i == 0 || i == 2 || i == 4) {
            return 1;
        }
        if (!(i == 5 || i == 6 || i == 9 || i == 10)) {
            if (i == 13) {
                return 1;
            }
            if (i != 14) {
                if (i == 21) {
                    return 3;
                }
                if (i == 22) {
                    return 1;
                }
                if (!(i == 57 || i == 58)) {
                    if (i == 2) {
                        return 1;
                    }
                    if (i != 16) {
                        if (i == 26 || i == 31) {
                            return 1;
                        }
                        if (i != 33) {
                            if (i == 37) {
                                return 3;
                            }
                            if (i != 40) {
                                if (i != 44) {
                                    return (i == 52 || i == 54) ? 2 : 0;
                                }
                                return 1;
                            }
                        }
                    }
                }
            }
        }
    }

    public final void a(C6025zy1 zy1, int i) {
        int e = e(i);
        if (e != 0) {
            if (i == 44 && !AbstractC1575Zv.e().g("enable-experimental-web-platform-features")) {
                return;
            }
            if (i == 52 && !N.Mudil8Bg("WebNFC")) {
                return;
            }
            if (i != 21 || N.Mudil8Bg("WebBluetoothNewPermissionsBackend")) {
                int a2 = AbstractC5580xK0.a(e);
                if (a2 == 0) {
                    zy1.add(new C4835sy1(this, i));
                } else if (a2 == 1) {
                    zy1.add(new C5515wy1(this, i));
                } else if (a2 == 2) {
                    zy1.add(new C4665ry1(this, i));
                }
            }
        }
    }

    public void b(Cy1 cy1) {
        C6025zy1 zy1 = new C6025zy1(null);
        zy1.add(new C5175uy1(this, null));
        zy1.add(new By1(this, null));
        for (int i = 0; i < 69; i++) {
            a(zy1, i);
        }
        zy1.add(new C5685xy1(this, cy1, null));
        zy1.a();
    }

    public void c(QX0 qx0, Cy1 cy1) {
        if (qx0.r(0)) {
            b(cy1);
            return;
        }
        C6025zy1 zy1 = new C6025zy1(null);
        if (qx0.r(22)) {
            zy1.add(new C5175uy1(this, null));
            zy1.add(new By1(this, null));
        } else {
            a(zy1, qx0.i());
        }
        zy1.add(new C5685xy1(this, cy1, null));
        zy1.a();
    }

    public final C3469ky1 d(String str, String str2) {
        if (str2 != null && (str2.equals(str) || str2.equals("*"))) {
            str2 = null;
        }
        C3640ly1 b2 = C3640ly1.b(str);
        C3640ly1 b3 = C3640ly1.b(str2);
        C5345vy1 vy1 = new C5345vy1(b2, b3);
        C3469ky1 ky1 = (C3469ky1) this.c.get(vy1);
        if (ky1 != null) {
            return ky1;
        }
        C3469ky1 ky12 = new C3469ky1(b2, b3);
        this.c.put(vy1, ky12);
        return ky12;
    }
}
