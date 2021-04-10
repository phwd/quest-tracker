package X;

import com.facebook.infer.annotation.Nullsafe;
import java.io.Serializable;
import java.security.cert.Certificate;
import java.util.LinkedHashSet;
import java.util.Map;

@Nullsafe(Nullsafe.Mode.LOCAL)
/* renamed from: X.0Xo  reason: invalid class name and case insensitive filesystem */
public final class C01810Xo implements Serializable {
    public static final long serialVersionUID = 84430201;
    public final Map<Byte, Certificate[]> certs;
    public final String cipher;
    public final int port;
    public final LinkedHashSet<C10711us> psks;
    public final String sni;

    public C01810Xo(String str, int i, String str2, LinkedHashSet<C10711us> linkedHashSet, Map<Byte, Certificate[]> map) {
        this.psks = linkedHashSet;
        this.certs = map;
        this.sni = str;
        this.port = i;
        this.cipher = str2;
    }
}
