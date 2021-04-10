package X;

import com.facebook.infer.annotation.Nullsafe;
import java.io.Serializable;
import java.security.cert.Certificate;
import java.util.LinkedHashSet;
import java.util.Map;

@Nullsafe(Nullsafe.Mode.LOCAL)
/* renamed from: X.1no  reason: invalid class name */
public final class AnonymousClass1no implements Serializable {
    public static final long serialVersionUID = 84430201;
    public final Map<Byte, Certificate[]> certs;
    public final String cipher;
    public final int port;
    public final LinkedHashSet<AnonymousClass1np> psks;
    public final String sni;

    public AnonymousClass1no(String str, int i, String str2, LinkedHashSet<AnonymousClass1np> linkedHashSet, Map<Byte, Certificate[]> map) {
        this.psks = linkedHashSet;
        this.certs = map;
        this.sni = str;
        this.port = i;
        this.cipher = str2;
    }
}
