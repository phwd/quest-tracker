package sun.security.x509;

import java.lang.reflect.Field;
import sun.misc.HexDumpEncoder;

/* access modifiers changed from: package-private */
/* compiled from: CertificateExtensions */
public class UnparseableExtension extends Extension {
    private String name = "";
    private Throwable why;

    public UnparseableExtension(Extension extension, Throwable th) {
        super(extension);
        try {
            Class cls = OIDMap.getClass(extension.getExtensionId());
            if (cls != null) {
                Field declaredField = cls.getDeclaredField("NAME");
                this.name = ((String) declaredField.get(null)) + " ";
            }
        } catch (Exception unused) {
        }
        this.why = th;
    }

    @Override // sun.security.x509.Extension
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString());
        sb.append("Unparseable ");
        sb.append(this.name);
        sb.append("extension due to\n");
        sb.append(this.why);
        sb.append("\n\n");
        new HexDumpEncoder().encodeBuffer(getExtensionValue());
        throw null;
    }
}
