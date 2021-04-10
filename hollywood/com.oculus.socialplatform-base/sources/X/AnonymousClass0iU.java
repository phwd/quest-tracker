package X;

import com.fasterxml.jackson.core.json.PackageVersion;
import java.io.IOException;
import java.io.Reader;
import java.io.Serializable;
import java.io.StringReader;
import java.io.Writer;
import java.lang.ref.SoftReference;

/* renamed from: X.0iU  reason: invalid class name */
public class AnonymousClass0iU implements AbstractC03700oK, Serializable {
    public static final AbstractC03670oH A02 = C02240iK.A01;
    public static final int A03 = AnonymousClass0o6.collectDefaults();
    public static final int A04 = AnonymousClass0o7.collectDefaults();
    public static final int A05 = EnumC03610oA.collectDefaults();
    public static final ThreadLocal<SoftReference<C03930oq>> A06 = new ThreadLocal<>();
    public static final long serialVersionUID = 8726401676402117450L;
    public final transient C03870oi A00;
    public final transient C03890ok A01;
    public AbstractC03740oS _characterEscapes;
    public int _factoryFeatures;
    public int _generatorFeatures;
    public AbstractC03760oU _inputDecorator;
    public AbstractC03650oF _objectCodec;
    public AnonymousClass0oZ _outputDecorator;
    public int _parserFeatures;
    public AbstractC03670oH _rootValueSeparator;

    public static final C03930oq A00() {
        C03930oq r0;
        ThreadLocal<SoftReference<C03930oq>> threadLocal = A06;
        SoftReference<C03930oq> softReference = threadLocal.get();
        if (softReference != null && (r0 = softReference.get()) != null) {
            return r0;
        }
        C03930oq r02 = new C03930oq();
        threadLocal.set(new SoftReference<>(r02));
        return r02;
    }

    public AbstractC02300iS A01(Writer writer, C03750oT r5) throws IOException {
        AnonymousClass05Z r2 = new AnonymousClass05Z(r5, this._generatorFeatures, this._objectCodec, writer);
        AbstractC03670oH r1 = this._rootValueSeparator;
        if (r1 != A02) {
            ((AnonymousClass0HW) r2).A01 = r1;
        }
        return r2;
    }

    public AbstractC02280iQ A02(Reader reader, C03750oT r16) throws IOException, C02290iR {
        String[] strArr;
        C03880oj[] r6;
        int i;
        int i2;
        int i3;
        int i4 = this._parserFeatures;
        AbstractC03650oF r12 = this._objectCodec;
        C03890ok r2 = this.A01;
        AnonymousClass0o6 r0 = AnonymousClass0o6.CANONICALIZE_FIELD_NAMES;
        int i5 = this._factoryFeatures;
        boolean z = false;
        if ((r0.getMask() & i5) != 0) {
            z = true;
        }
        boolean z2 = false;
        if ((AnonymousClass0o6.INTERN_FIELD_NAMES.getMask() & i5) != 0) {
            z2 = true;
        }
        synchronized (r2) {
            strArr = r2.A07;
            r6 = r2.A06;
            i = r2.A02;
            i2 = r2.A08;
            i3 = r2.A01;
        }
        return new C002005a(r16, i4, reader, r12, new C03890ok(r2, z, z2, strArr, r6, i, i2, i3));
    }

    public final AbstractC02280iQ A03(String str) throws IOException, C02290iR {
        StringReader stringReader = new StringReader(str);
        return A02(stringReader, new C03750oT(A00(), stringReader, true));
    }

    public Object readResolve() {
        return new AnonymousClass0iU(this);
    }

    public AbstractC03650oF A04() {
        return this._objectCodec;
    }

    @Override // X.AbstractC03700oK
    public C03690oJ version() {
        return PackageVersion.VERSION;
    }

    public AnonymousClass0iU() {
        this((AbstractC03650oF) null);
    }

    public AnonymousClass0iU(AnonymousClass0iU r2) {
        this.A01 = C03890ok.A00();
        System.currentTimeMillis();
        this.A00 = new C03870oi();
        this._factoryFeatures = A03;
        this._parserFeatures = A05;
        this._generatorFeatures = A04;
        this._rootValueSeparator = A02;
        this._objectCodec = null;
        this._factoryFeatures = r2._factoryFeatures;
        this._parserFeatures = r2._parserFeatures;
        this._generatorFeatures = r2._generatorFeatures;
        this._characterEscapes = null;
        this._inputDecorator = null;
        this._outputDecorator = null;
        this._rootValueSeparator = r2._rootValueSeparator;
    }

    public AnonymousClass0iU(AbstractC03650oF r2) {
        this.A01 = C03890ok.A00();
        System.currentTimeMillis();
        this.A00 = new C03870oi();
        this._factoryFeatures = A03;
        this._parserFeatures = A05;
        this._generatorFeatures = A04;
        this._rootValueSeparator = A02;
        this._objectCodec = r2;
    }
}
