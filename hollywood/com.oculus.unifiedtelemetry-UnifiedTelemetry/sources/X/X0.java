package X;

import java.io.Serializable;
import java.lang.ref.SoftReference;

public class X0 implements q8, Serializable {
    public static final ThreadLocal<SoftReference<k2>> A02 = new ThreadLocal<>();
    public static final q5 A03 = C0228Wq.A00;
    public static final int A04 = EnumC0467pu.collectDefaults();
    public static final int A05 = EnumC0468pv.collectDefaults();
    public static final int A06 = py.collectDefaults();
    public static final long serialVersionUID = 8726401676402117450L;
    public final transient C0444kS A00;
    public final transient C0446kU A01;
    public XB _characterEscapes;
    public int _factoryFeatures;
    public int _generatorFeatures;
    public AbstractC0473qB _inputDecorator;
    public AbstractC0471q3 _objectCodec;
    public AbstractC0455mn _outputDecorator;
    public int _parserFeatures;
    public q5 _rootValueSeparator;

    public Object readResolve() {
        return new X0(this);
    }

    public AbstractC0471q3 A00() {
        return this._objectCodec;
    }

    public X0() {
        this((AbstractC0471q3) null);
    }

    public X0(X0 x0) {
        this.A00 = C0444kS.A00();
        System.currentTimeMillis();
        this.A01 = new C0446kU();
        this._factoryFeatures = A04;
        this._parserFeatures = A06;
        this._generatorFeatures = A05;
        this._rootValueSeparator = A03;
        this._objectCodec = null;
        this._factoryFeatures = x0._factoryFeatures;
        this._parserFeatures = x0._parserFeatures;
        this._generatorFeatures = x0._generatorFeatures;
        this._characterEscapes = null;
        this._inputDecorator = null;
        this._outputDecorator = null;
        this._rootValueSeparator = x0._rootValueSeparator;
    }

    public X0(AbstractC0471q3 q3Var) {
        this.A00 = C0444kS.A00();
        System.currentTimeMillis();
        this.A01 = new C0446kU();
        this._factoryFeatures = A04;
        this._parserFeatures = A06;
        this._generatorFeatures = A05;
        this._rootValueSeparator = A03;
        this._objectCodec = q3Var;
    }
}
