package X;

import com.fasterxml.jackson.core.util.Instantiatable;
import java.io.IOException;

/* renamed from: X.0aN  reason: invalid class name and case insensitive filesystem */
public final class C02600aN implements AbstractC05950lh, Instantiatable<C02600aN> {
    public static final C02620aS A01 = new C02620aS(" ");
    public static final long serialVersionUID = -5512586643324525213L;
    public transient int A00 = 0;
    public AbstractC06200mK _arrayIndenter = AnonymousClass0LN.A00;
    public AbstractC06200mK _objectIndenter = AnonymousClass0LM.A01;
    public final AbstractC05960li _rootSeparator;
    public boolean _spacesInObjectEntries = true;

    public C02600aN() {
        C02620aS r1 = A01;
        this._rootSeparator = r1;
    }

    @Override // X.AbstractC05950lh
    public final void A1F(AbstractC02640aV r3) throws IOException, C02650aW {
        this._arrayIndenter.A96(r3, this.A00);
    }

    @Override // X.AbstractC05950lh
    public final void A1G(AbstractC02640aV r3) throws IOException, C02650aW {
        this._objectIndenter.A96(r3, this.A00);
    }

    @Override // X.AbstractC05950lh
    public final void A90(AbstractC02640aV r3) throws IOException, C02650aW {
        r3.A0G(',');
        this._arrayIndenter.A96(r3, this.A00);
    }

    @Override // X.AbstractC05950lh
    public final void A93(AbstractC02640aV r3, int i) throws IOException, C02650aW {
        AbstractC06200mK r1 = this._arrayIndenter;
        if (!r1.A5S()) {
            this.A00--;
        }
        if (i > 0) {
            r1.A96(r3, this.A00);
        } else {
            r3.A0G(' ');
        }
        r3.A0G(']');
    }

    @Override // X.AbstractC05950lh
    public final void A94(AbstractC02640aV r3, int i) throws IOException, C02650aW {
        AbstractC06200mK r1 = this._objectIndenter;
        if (!r1.A5S()) {
            this.A00--;
        }
        if (i > 0) {
            r1.A96(r3, this.A00);
        } else {
            r3.A0G(' ');
        }
        r3.A0G('}');
    }

    @Override // X.AbstractC05950lh
    public final void A98(AbstractC02640aV r3) throws IOException, C02650aW {
        r3.A0G(',');
        this._objectIndenter.A96(r3, this.A00);
    }

    @Override // X.AbstractC05950lh
    public final void A99(AbstractC02640aV r2) throws IOException, C02650aW {
        if (this._spacesInObjectEntries) {
            r2.A0R(" : ");
        } else {
            r2.A0G(':');
        }
    }

    @Override // X.AbstractC05950lh
    public final void A9B(AbstractC02640aV r2) throws IOException, C02650aW {
        AbstractC05960li r0 = this._rootSeparator;
        if (r0 != null) {
            r2.A0M(r0);
        }
    }

    @Override // X.AbstractC05950lh
    public final void A9D(AbstractC02640aV r2) throws IOException, C02650aW {
        if (!this._arrayIndenter.A5S()) {
            this.A00++;
        }
        r2.A0G('[');
    }

    @Override // X.AbstractC05950lh
    public final void A9E(AbstractC02640aV r2) throws IOException, C02650aW {
        r2.A0G('{');
        if (!this._objectIndenter.A5S()) {
            this.A00++;
        }
    }
}
