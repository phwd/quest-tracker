package X;

import com.fasterxml.jackson.databind.JsonDeserializer;
import java.io.IOException;
import java.io.Serializable;

/* renamed from: X.0GQ  reason: invalid class name */
public class AnonymousClass0GQ extends AbstractC03720fx implements Serializable {
    public static final long serialVersionUID = 5345570420394408290L;

    @Override // X.AbstractC03720fx, X.AnonymousClass0m9
    public AnonymousClass0m9 A04(AbstractC04030gh r2) {
        if (r2 == this._property) {
            return this;
        }
        return new AnonymousClass0GQ(this, r2);
    }

    private final Object A01(AbstractC04100gp r6, AbstractC04020gg r7) throws IOException, AnonymousClass0jg {
        String A4k;
        EnumC04820ji r1;
        boolean A0G = r6.A0G();
        if (!A0G) {
            if (this._defaultImpl == null) {
                throw AbstractC04020gg.A00(r6, EnumC04820ji.START_ARRAY, AnonymousClass006.A05("need JSON Array to contain As.WRAPPER_ARRAY type information for class ", this._baseType._class.getName()));
            }
            A4k = this._idResolver.A4k();
        } else {
            EnumC04820ji A0b = r6.A0b();
            EnumC04820ji r3 = EnumC04820ji.VALUE_STRING;
            if (A0b == r3) {
                A4k = r6.A0e();
                r6.A0b();
            } else {
                if (this._defaultImpl == null) {
                    throw AbstractC04020gg.A00(r6, r3, AnonymousClass006.A07("need JSON String that contains type id (for subtype of ", this._baseType._class.getName(), ")"));
                }
                A4k = this._idResolver.A4k();
            }
        }
        JsonDeserializer<Object> A0D = A0D(r7, A4k);
        if (this._typeIdVisible && r6.A0a() == EnumC04820ji.START_OBJECT) {
            AnonymousClass0Fv r12 = new AnonymousClass0Fv(null);
            r12.A07();
            r12.A09(this._typePropertyName);
            r12.A0A(A4k);
            r6 = AnonymousClass08Y.A00(r12.A03(r6), r6);
            r6.A0b();
        }
        Object A09 = A0D.A09(r6, r7);
        if (!A0G || r6.A0b() == (r1 = EnumC04820ji.END_ARRAY)) {
            return A09;
        }
        throw AbstractC04020gg.A00(r6, r1, "expected closing END_ARRAY after type information and deserialized value");
    }

    @Override // X.AbstractC03720fx, X.AnonymousClass0m9
    public EnumC04710jK A03() {
        return EnumC04710jK.WRAPPER_ARRAY;
    }

    @Override // X.AnonymousClass0m9
    public Object A07(AbstractC04100gp r2, AbstractC04020gg r3) throws IOException, AnonymousClass0jg {
        return A01(r2, r3);
    }

    @Override // X.AnonymousClass0m9
    public final Object A08(AbstractC04100gp r2, AbstractC04020gg r3) throws IOException, AnonymousClass0jg {
        return A01(r2, r3);
    }

    @Override // X.AnonymousClass0m9
    public Object A09(AbstractC04100gp r2, AbstractC04020gg r3) throws IOException, AnonymousClass0jg {
        return A01(r2, r3);
    }

    @Override // X.AnonymousClass0m9
    public final Object A0A(AbstractC04100gp r2, AbstractC04020gg r3) throws IOException, AnonymousClass0jg {
        return A01(r2, r3);
    }

    public AnonymousClass0GQ(AbstractC04000gb r1, AbstractC05940mA r2, String str, boolean z, Class<?> cls) {
        super(r1, r2, str, z, cls);
    }

    public AnonymousClass0GQ(AnonymousClass0GQ r1, AbstractC04030gh r2) {
        super(r1, r2);
    }
}
