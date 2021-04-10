package X;

import com.fasterxml.jackson.dataformat.smile.PackageVersion;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;

/* renamed from: X.0OC  reason: invalid class name */
public final class AnonymousClass0OC extends AnonymousClass0iU {
    public static final int A00 = EnumC05000rg.collectDefaults();
    public static final int A01 = EnumC05030rj.collectDefaults();
    public static final long serialVersionUID = -1696783009312472365L;
    public boolean _cfgDelegateToTextual;
    public int _smileGeneratorFeatures;
    public int _smileParserFeatures;

    @Override // X.AnonymousClass0iU
    public final AbstractC02300iS A01(Writer writer, C03750oT r4) throws IOException {
        if (this._cfgDelegateToTextual) {
            return super.A01(writer, r4);
        }
        throw new UnsupportedOperationException("Can not create generator for non-byte-based target");
    }

    @Override // X.AnonymousClass0iU
    public final AbstractC02280iQ A02(Reader reader, C03750oT r4) throws IOException, C02290iR {
        if (this._cfgDelegateToTextual) {
            return super.A02(reader, r4);
        }
        throw new UnsupportedOperationException("Can not create generator for non-byte-based target");
    }

    @Override // X.AnonymousClass0iU
    public Object readResolve() {
        return new AnonymousClass0OC(this);
    }

    @Override // X.AnonymousClass0iU, X.AbstractC03700oK
    public final C03690oJ version() {
        return PackageVersion.VERSION;
    }

    public AnonymousClass0OC() {
        this((AbstractC03650oF) null);
    }

    public AnonymousClass0OC(AbstractC03650oF r2) {
        super(r2);
        this._smileParserFeatures = A01;
        this._smileGeneratorFeatures = A00;
    }

    public AnonymousClass0OC(AnonymousClass0OC r2) {
        super(r2);
        this._cfgDelegateToTextual = r2._cfgDelegateToTextual;
        this._smileParserFeatures = r2._smileParserFeatures;
        this._smileGeneratorFeatures = r2._smileGeneratorFeatures;
    }
}
