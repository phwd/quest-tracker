package X;

import com.fasterxml.jackson.databind.JsonDeserializer;
import java.io.IOException;
import java.io.Serializable;

/* renamed from: X.0GP  reason: invalid class name */
public final class AnonymousClass0GP extends AbstractC03720fx implements Serializable {
    public static final long serialVersionUID = 5345570420394408290L;

    @Override // X.AbstractC03720fx, X.AnonymousClass0m9
    public final AnonymousClass0m9 A04(AbstractC04030gh r2) {
        if (r2 == this._property) {
            return this;
        }
        return new AnonymousClass0GP(this, r2);
    }

    private final Object A00(AbstractC04100gp r6, AbstractC04020gg r7) throws IOException, AnonymousClass0jg {
        EnumC04820ji A0a = r6.A0a();
        EnumC04820ji r4 = EnumC04820ji.START_OBJECT;
        if (A0a == r4) {
            EnumC04820ji A0b = r6.A0b();
            EnumC04820ji r3 = EnumC04820ji.FIELD_NAME;
            if (A0b == r3) {
                String A0e = r6.A0e();
                JsonDeserializer<Object> A0D = A0D(r7, A0e);
                r6.A0b();
                if (this._typeIdVisible && r6.A0a() == r4) {
                    AnonymousClass0Fv r1 = new AnonymousClass0Fv(null);
                    r1.A07();
                    r1.A09(this._typePropertyName);
                    r1.A0A(A0e);
                    r6 = AnonymousClass08Y.A00(r1.A03(r6), r6);
                    r6.A0b();
                }
                Object A09 = A0D.A09(r6, r7);
                EnumC04820ji A0b2 = r6.A0b();
                EnumC04820ji r12 = EnumC04820ji.END_OBJECT;
                if (A0b2 == r12) {
                    return A09;
                }
                throw AbstractC04020gg.A00(r6, r12, "expected closing END_OBJECT after type information and deserialized value");
            }
            throw AbstractC04020gg.A00(r6, r3, AnonymousClass006.A07("need JSON String that contains type id (for subtype of ", this._baseType._class.getName(), ")"));
        }
        throw AbstractC04020gg.A00(r6, r4, AnonymousClass006.A05("need JSON Object to contain As.WRAPPER_OBJECT type information for class ", this._baseType._class.getName()));
    }

    @Override // X.AbstractC03720fx, X.AnonymousClass0m9
    public final EnumC04710jK A03() {
        return EnumC04710jK.WRAPPER_OBJECT;
    }

    @Override // X.AnonymousClass0m9
    public final Object A07(AbstractC04100gp r2, AbstractC04020gg r3) throws IOException, AnonymousClass0jg {
        return A00(r2, r3);
    }

    @Override // X.AnonymousClass0m9
    public final Object A08(AbstractC04100gp r2, AbstractC04020gg r3) throws IOException, AnonymousClass0jg {
        return A00(r2, r3);
    }

    @Override // X.AnonymousClass0m9
    public final Object A09(AbstractC04100gp r2, AbstractC04020gg r3) throws IOException, AnonymousClass0jg {
        return A00(r2, r3);
    }

    @Override // X.AnonymousClass0m9
    public final Object A0A(AbstractC04100gp r2, AbstractC04020gg r3) throws IOException, AnonymousClass0jg {
        return A00(r2, r3);
    }

    /* JADX WARN: Incorrect args count in method signature: (LX/0gb;LX/0mA;Ljava/lang/String;ZLjava/lang/Class<*>;)V */
    public AnonymousClass0GP(AbstractC04000gb r7, AbstractC05940mA r8, String str, boolean z) {
        super(r7, r8, str, z, null);
    }

    public AnonymousClass0GP(AnonymousClass0GP r1, AbstractC04030gh r2) {
        super(r1, r2);
    }
}
