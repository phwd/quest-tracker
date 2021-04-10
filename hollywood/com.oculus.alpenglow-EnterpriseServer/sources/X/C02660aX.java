package X;

import java.io.Serializable;
import java.lang.ref.SoftReference;

/* renamed from: X.0aX  reason: invalid class name and case insensitive filesystem */
public class C02660aX implements AbstractC05990ll, Serializable {
    public static final AbstractC05960li A02 = C02600aN.A01;
    public static final ThreadLocal<SoftReference<AnonymousClass0mI>> A03 = new ThreadLocal<>();
    public static final int A04 = EnumC05860lX.collectDefaults();
    public static final int A05 = EnumC05870lY.collectDefaults();
    public static final int A06 = EnumC05900lb.collectDefaults();
    public static final long serialVersionUID = 8726401676402117450L;
    public final transient C06160mA A00;
    public final transient AnonymousClass0mC A01;
    public AbstractC06070lu _characterEscapes;
    public int _factoryFeatures;
    public int _generatorFeatures;
    public AbstractC06090lw _inputDecorator;
    public AbstractC05940lg _objectCodec;
    public AbstractC06140m1 _outputDecorator;
    public int _parserFeatures;
    public AbstractC05960li _rootValueSeparator;

    public AbstractC05940lg A00() {
        return this._objectCodec;
    }

    public Object readResolve() {
        return new C02660aX(this);
    }

    public C02660aX() {
        this((AbstractC05940lg) null);
    }

    public C02660aX(C02660aX r6) {
        System.currentTimeMillis();
        AnonymousClass0mC r0 = AnonymousClass0mC.A04;
        this.A01 = new AnonymousClass0mC(r0.A03, r0.A02, r0.A01, r0.A00);
        System.currentTimeMillis();
        this.A00 = new C06160mA();
        this._factoryFeatures = A04;
        this._parserFeatures = A06;
        this._generatorFeatures = A05;
        this._rootValueSeparator = A02;
        this._objectCodec = null;
        this._factoryFeatures = r6._factoryFeatures;
        this._parserFeatures = r6._parserFeatures;
        this._generatorFeatures = r6._generatorFeatures;
        this._characterEscapes = null;
        this._inputDecorator = null;
        this._outputDecorator = null;
        this._rootValueSeparator = r6._rootValueSeparator;
    }

    public C02660aX(AbstractC05940lg r6) {
        System.currentTimeMillis();
        AnonymousClass0mC r0 = AnonymousClass0mC.A04;
        this.A01 = new AnonymousClass0mC(r0.A03, r0.A02, r0.A01, r0.A00);
        System.currentTimeMillis();
        this.A00 = new C06160mA();
        this._factoryFeatures = A04;
        this._parserFeatures = A06;
        this._generatorFeatures = A05;
        this._rootValueSeparator = A02;
        this._objectCodec = r6;
    }
}
