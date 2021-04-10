package X;

import java.io.IOException;
import java.io.Serializable;
import java.io.StringReader;
import java.lang.ref.SoftReference;

/* renamed from: X.0gt  reason: invalid class name and case insensitive filesystem */
public class C04140gt implements AbstractC04870jo, Serializable {
    public static final ThreadLocal<SoftReference<AnonymousClass0kL>> A02 = new ThreadLocal<>();
    public static final AbstractC04840jl A03 = C04050gj.A00;
    public static final int A04 = EnumC04810ja.collectDefaults();
    public static final int A05 = AnonymousClass0jb.collectDefaults();
    public static final int A06 = AnonymousClass0je.collectDefaults();
    public static final long serialVersionUID = 8726401676402117450L;
    public final transient AnonymousClass0kF A00;
    public final transient C05070kD A01;
    public AbstractC04960jx _characterEscapes;
    public int _factoryFeatures;
    public int _generatorFeatures;
    public AbstractC04980jz _inputDecorator;
    public AnonymousClass0jj _objectCodec;
    public AbstractC05020k4 _outputDecorator;
    public int _parserFeatures;
    public AbstractC04840jl _rootValueSeparator;

    public final AbstractC04100gp A00(String str) throws IOException, C04110gq {
        AnonymousClass0kL r0;
        String[] strArr;
        C05080kE[] r6;
        int i;
        int i2;
        int i3;
        StringReader stringReader = new StringReader(str);
        ThreadLocal<SoftReference<AnonymousClass0kL>> threadLocal = A02;
        SoftReference<AnonymousClass0kL> softReference = threadLocal.get();
        if (softReference == null || (r0 = softReference.get()) == null) {
            r0 = new AnonymousClass0kL();
            threadLocal.set(new SoftReference<>(r0));
        }
        C04970jy r10 = new C04970jy(r0, stringReader);
        int i4 = this._parserFeatures;
        AnonymousClass0jj r13 = this._objectCodec;
        AnonymousClass0kF r2 = this.A00;
        EnumC04810ja r02 = EnumC04810ja.CANONICALIZE_FIELD_NAMES;
        int i5 = this._factoryFeatures;
        boolean z = false;
        if ((r02.getMask() & i5) != 0) {
            z = true;
        }
        boolean z2 = false;
        if ((EnumC04810ja.INTERN_FIELD_NAMES.getMask() & i5) != 0) {
            z2 = true;
        }
        synchronized (r2) {
            strArr = r2.A07;
            r6 = r2.A06;
            i = r2.A02;
            i2 = r2.A08;
            i3 = r2.A01;
        }
        return new AnonymousClass04G(r10, i4, stringReader, r13, new AnonymousClass0kF(r2, z, z2, strArr, r6, i, i2, i3));
    }

    public Object readResolve() {
        return new C04140gt(this);
    }

    public AnonymousClass0jj A01() {
        return this._objectCodec;
    }

    public C04140gt() {
        this((AnonymousClass0jj) null);
    }

    public C04140gt(C04140gt r2) {
        this.A00 = AnonymousClass0kF.A00();
        System.currentTimeMillis();
        this.A01 = new C05070kD();
        this._factoryFeatures = A04;
        this._parserFeatures = A06;
        this._generatorFeatures = A05;
        this._rootValueSeparator = A03;
        this._objectCodec = null;
        this._factoryFeatures = r2._factoryFeatures;
        this._parserFeatures = r2._parserFeatures;
        this._generatorFeatures = r2._generatorFeatures;
        this._characterEscapes = null;
        this._inputDecorator = null;
        this._outputDecorator = null;
        this._rootValueSeparator = r2._rootValueSeparator;
    }

    public C04140gt(AnonymousClass0jj r2) {
        this.A00 = AnonymousClass0kF.A00();
        System.currentTimeMillis();
        this.A01 = new C05070kD();
        this._factoryFeatures = A04;
        this._parserFeatures = A06;
        this._generatorFeatures = A05;
        this._rootValueSeparator = A03;
        this._objectCodec = r2;
    }
}
