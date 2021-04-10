package X;

import java.io.Serializable;

/* renamed from: X.qo  reason: case insensitive filesystem */
public final class C1019qo implements NZ, Serializable {
    public static final C1015qj A01 = new C1015qj(" ");
    public static final long serialVersionUID = -5512586643324525213L;
    public transient int A00 = 0;
    public AbstractC0260Ny _arrayIndenter = VF.A00;
    public AbstractC0260Ny _objectIndenter = VE.A00;
    public final Na _rootSeparator;
    public boolean _spacesInObjectEntries = true;

    public C1019qo() {
        C1015qj qjVar = A01;
        this._rootSeparator = qjVar;
    }

    @Override // X.NZ
    public final void A1K(AbstractC1012qg qgVar) {
        this._arrayIndenter.A5q(qgVar, this.A00);
    }

    @Override // X.NZ
    public final void A1L(AbstractC1012qg qgVar) {
        this._objectIndenter.A5q(qgVar, this.A00);
    }

    @Override // X.NZ
    public final void A5l(AbstractC1012qg qgVar) {
        qgVar.A0D(',');
        this._arrayIndenter.A5q(qgVar, this.A00);
    }

    @Override // X.NZ
    public final void A5n(AbstractC1012qg qgVar, int i) {
        AbstractC0260Ny ny = this._arrayIndenter;
        if (!ny.A3W()) {
            this.A00--;
        }
        if (i > 0) {
            ny.A5q(qgVar, this.A00);
        } else {
            qgVar.A0D(' ');
        }
        qgVar.A0D(']');
    }

    @Override // X.NZ
    public final void A5o(AbstractC1012qg qgVar, int i) {
        AbstractC0260Ny ny = this._objectIndenter;
        if (!ny.A3W()) {
            this.A00--;
        }
        if (i > 0) {
            ny.A5q(qgVar, this.A00);
        } else {
            qgVar.A0D(' ');
        }
        qgVar.A0D('}');
    }

    @Override // X.NZ
    public final void A5s(AbstractC1012qg qgVar) {
        qgVar.A0D(',');
        this._objectIndenter.A5q(qgVar, this.A00);
    }

    @Override // X.NZ
    public final void A5t(AbstractC1012qg qgVar) {
        if (this._spacesInObjectEntries) {
            qgVar.A0O(" : ");
        } else {
            qgVar.A0D(':');
        }
    }

    @Override // X.NZ
    public final void A5v(AbstractC1012qg qgVar) {
        Na na = this._rootSeparator;
        if (na == null) {
            return;
        }
        if (!(qgVar instanceof JD)) {
            qgVar.A0O(na.getValue());
            return;
        }
        throw new UnsupportedOperationException("Called operation not supported for TokenBuffer");
    }

    @Override // X.NZ
    public final void A5x(AbstractC1012qg qgVar) {
        if (!this._arrayIndenter.A3W()) {
            this.A00++;
        }
        qgVar.A0D('[');
    }

    @Override // X.NZ
    public final void A5y(AbstractC1012qg qgVar) {
        qgVar.A0D('{');
        if (!this._objectIndenter.A3W()) {
            this.A00++;
        }
    }
}
