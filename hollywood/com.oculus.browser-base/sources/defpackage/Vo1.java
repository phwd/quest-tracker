package defpackage;

import J.N;
import java.net.URISyntaxException;
import org.chromium.url.GURL;

@Deprecated
/* renamed from: Vo1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class Vo1 extends GURL {
    public Vo1(String str) {
        super(str);
        if (!this.b) {
            throw new URISyntaxException(str, "Uri could not be parsed as a valid GURL");
        }
    }

    @Override // org.chromium.url.GURL
    public GURL e() {
        Vo1 vo1 = new Vo1();
        N.MNBd3mFA(this.f11029a, this.b, this.c.c(), vo1);
        return vo1;
    }

    public String toString() {
        return this.f11029a;
    }

    public Vo1() {
    }
}
