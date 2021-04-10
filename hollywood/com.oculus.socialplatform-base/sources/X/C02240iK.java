package X;

import com.fasterxml.jackson.core.util.Instantiatable;
import java.io.IOException;
import org.apache.commons.cli.HelpFormatter;

/* renamed from: X.0iK  reason: invalid class name and case insensitive filesystem */
public final class C02240iK implements AbstractC03660oG, Instantiatable<C02240iK> {
    public static final C02270iP A01 = new C02270iP(HelpFormatter.DEFAULT_LONG_OPT_SEPARATOR);
    public static final long serialVersionUID = -5512586643324525213L;
    public transient int A00 = 0;
    public AbstractC03950os _arrayIndenter = AnonymousClass0Su.A00;
    public AbstractC03950os _objectIndenter = AnonymousClass0Sr.A01;
    public final AbstractC03670oH _rootSeparator;
    public boolean _spacesInObjectEntries = true;

    public C02240iK() {
        C02270iP r1 = A01;
        this._rootSeparator = r1;
    }

    @Override // X.AbstractC03660oG
    public final void A1Y(AbstractC02300iS r3) throws IOException, C02310iT {
        this._arrayIndenter.ABG(r3, this.A00);
    }

    @Override // X.AbstractC03660oG
    public final void A1Z(AbstractC02300iS r3) throws IOException, C02310iT {
        this._objectIndenter.ABG(r3, this.A00);
    }

    @Override // X.AbstractC03660oG
    public final void ABD(AbstractC02300iS r3) throws IOException, C02310iT {
        r3.A0J(',');
        this._arrayIndenter.ABG(r3, this.A00);
    }

    @Override // X.AbstractC03660oG
    public final void ABE(AbstractC02300iS r3, int i) throws IOException, C02310iT {
        AbstractC03950os r1 = this._arrayIndenter;
        if (!r1.A61()) {
            this.A00--;
        }
        if (i > 0) {
            r1.ABG(r3, this.A00);
        } else {
            r3.A0J(' ');
        }
        r3.A0J(']');
    }

    @Override // X.AbstractC03660oG
    public final void ABF(AbstractC02300iS r3, int i) throws IOException, C02310iT {
        AbstractC03950os r1 = this._objectIndenter;
        if (!r1.A61()) {
            this.A00--;
        }
        if (i > 0) {
            r1.ABG(r3, this.A00);
        } else {
            r3.A0J(' ');
        }
        r3.A0J('}');
    }

    @Override // X.AbstractC03660oG
    public final void ABH(AbstractC02300iS r3) throws IOException, C02310iT {
        r3.A0J(',');
        this._objectIndenter.ABG(r3, this.A00);
    }

    @Override // X.AbstractC03660oG
    public final void ABI(AbstractC02300iS r2) throws IOException, C02310iT {
        if (this._spacesInObjectEntries) {
            r2.A0T(" : ");
        } else {
            r2.A0J(':');
        }
    }

    @Override // X.AbstractC03660oG
    public final void ABJ(AbstractC02300iS r2) throws IOException, C02310iT {
        AbstractC03670oH r0 = this._rootSeparator;
        if (r0 != null) {
            r2.A0B(r0);
        }
    }

    @Override // X.AbstractC03660oG
    public final void ABK(AbstractC02300iS r2) throws IOException, C02310iT {
        if (!this._arrayIndenter.A61()) {
            this.A00++;
        }
        r2.A0J('[');
    }

    @Override // X.AbstractC03660oG
    public final void ABL(AbstractC02300iS r2) throws IOException, C02310iT {
        r2.A0J('{');
        if (!this._objectIndenter.A61()) {
            this.A00++;
        }
    }
}
