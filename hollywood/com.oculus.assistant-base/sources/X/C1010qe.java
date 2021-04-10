package X;

import java.io.Serializable;
import java.io.Writer;
import java.lang.ref.Reference;
import java.lang.ref.SoftReference;

/* renamed from: X.qe  reason: case insensitive filesystem */
public class C1010qe implements Nc, Serializable {
    public static final Na A02 = C1019qo.A01;
    public static final int A03 = NR.collectDefaults();
    public static final int A04 = NS.collectDefaults();
    public static final int A05 = NU.collectDefaults();
    public static final ThreadLocal A06 = new ThreadLocal();
    public static final long serialVersionUID = 8726401676402117450L;
    public final transient C0254Ns A00;
    public final transient C0252Nq A01;
    public AbstractC0246Nf _characterEscapes;
    public int _factoryFeatures;
    public int _generatorFeatures;
    public Nh _inputDecorator;
    public NY _objectCodec;
    public Nl _outputDecorator;
    public int _parserFeatures;
    public Na _rootValueSeparator;

    public final AbstractC1012qg A01(Writer writer) {
        AnonymousClass7s r2 = new AnonymousClass7s(new C0247Ng(A00(), writer, false), this._generatorFeatures, this._objectCodec, writer);
        Na na = this._rootValueSeparator;
        if (na != A02) {
            ((AnonymousClass2K) r2).A01 = na;
        }
        return r2;
    }

    public static final C0258Nw A00() {
        C0258Nw nw;
        ThreadLocal threadLocal = A06;
        Reference reference = (Reference) threadLocal.get();
        if (reference != null && (nw = (C0258Nw) reference.get()) != null) {
            return nw;
        }
        C0258Nw nw2 = new C0258Nw();
        threadLocal.set(new SoftReference(nw2));
        return nw2;
    }

    public Object readResolve() {
        return new C1010qe(this);
    }

    public C1010qe() {
        this((NY) null);
    }

    public C1010qe(C1010qe qeVar) {
        this.A00 = C0254Ns.A00();
        System.currentTimeMillis();
        this.A01 = new C0252Nq();
        this._factoryFeatures = A03;
        this._parserFeatures = A05;
        this._generatorFeatures = A04;
        this._rootValueSeparator = A02;
        this._objectCodec = null;
        this._factoryFeatures = qeVar._factoryFeatures;
        this._parserFeatures = qeVar._parserFeatures;
        this._generatorFeatures = qeVar._generatorFeatures;
        this._characterEscapes = null;
        this._inputDecorator = null;
        this._outputDecorator = null;
        this._rootValueSeparator = qeVar._rootValueSeparator;
    }

    public C1010qe(NY ny) {
        this.A00 = C0254Ns.A00();
        System.currentTimeMillis();
        this.A01 = new C0252Nq();
        this._factoryFeatures = A03;
        this._parserFeatures = A05;
        this._generatorFeatures = A04;
        this._rootValueSeparator = A02;
        this._objectCodec = ny;
    }
}
