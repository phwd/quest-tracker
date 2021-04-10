package sun.security.x509;

import android.icu.impl.number.Padder;
import java.lang.reflect.Field;
import sun.misc.HexDumpEncoder;

/* access modifiers changed from: package-private */
/* compiled from: CertificateExtensions */
public class UnparseableExtension extends Extension {
    private String name = "";
    private Throwable why;

    public UnparseableExtension(Extension ext, Throwable why2) {
        super(ext);
        try {
            Class<?> extClass = OIDMap.getClass(ext.getExtensionId());
            if (extClass != null) {
                Field field = extClass.getDeclaredField("NAME");
                this.name = ((String) field.get(null)) + Padder.FALLBACK_PADDING_STRING;
            }
        } catch (Exception e) {
        }
        this.why = why2;
    }

    @Override // sun.security.x509.Extension
    public String toString() {
        return super.toString() + "Unparseable " + this.name + "extension due to\n" + ((Object) this.why) + "\n\n" + new HexDumpEncoder().encodeBuffer(getExtensionValue());
    }
}
