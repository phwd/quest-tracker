package X;

import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ser.impl.UnwrappingBeanSerializer;

/* renamed from: X.0DN  reason: invalid class name */
public final class AnonymousClass0DN extends AnonymousClass0Jw {
    public final AbstractC07200ov A00;

    @Override // X.AnonymousClass0Jw
    public final JsonSerializer<Object> A00(AbstractC06960oT r5, Class<?> cls, AnonymousClass0a8 r7) throws AnonymousClass0aG {
        JsonSerializer<Object> A0B;
        AnonymousClass0aI r0 = super.A00;
        if (r0 != null) {
            A0B = r7.A08(r7.A04(r0, cls), this);
        } else {
            A0B = r7.A0B(cls, this);
        }
        AbstractC07200ov r2 = this.A00;
        if (A0B.A07()) {
            r2 = new AnonymousClass0ZJ(r2, ((UnwrappingBeanSerializer) A0B).A00);
        }
        JsonSerializer<Object> A09 = A0B.A09(r2);
        this.A04 = this.A04.A01(cls, A09);
        return A09;
    }

    @Override // X.AnonymousClass0Jw
    public final AnonymousClass0Jw A01(AbstractC07200ov r5) {
        return new AnonymousClass0DN(this, new AnonymousClass0ZJ(r5, this.A00), new C02620aS(r5.A00(this.A06.getValue())));
    }

    @Override // X.AnonymousClass0Jw
    public final void A04(JsonSerializer<Object> jsonSerializer) {
        super.A04(jsonSerializer);
        JsonSerializer<Object> jsonSerializer2 = this.A02;
        if (jsonSerializer2 != null) {
            AbstractC07200ov r2 = this.A00;
            if (jsonSerializer2.A07()) {
                r2 = new AnonymousClass0ZJ(r2, ((UnwrappingBeanSerializer) jsonSerializer2).A00);
            }
            this.A02 = jsonSerializer2.A09(r2);
        }
    }

    @Override // X.AnonymousClass0Jw
    public final void A06(Object obj, AbstractC02640aV r6, AnonymousClass0a8 r7) throws Exception {
        boolean equals;
        Class<?> cls;
        AbstractC06960oT r0;
        Object A02 = A02(obj);
        if (A02 != null) {
            JsonSerializer<Object> jsonSerializer = this.A02;
            if (jsonSerializer == null && (jsonSerializer = (r0 = this.A04).A00((cls = A02.getClass()))) == null) {
                jsonSerializer = A00(r0, cls, r7);
            }
            Object obj2 = this.A0G;
            if (obj2 != null) {
                if (AnonymousClass0Jw.A0I == obj2) {
                    equals = jsonSerializer.A0B(A02);
                } else {
                    equals = obj2.equals(A02);
                }
                if (equals) {
                    return;
                }
            }
            if (A02 != obj || jsonSerializer.A08()) {
                if (!jsonSerializer.A07()) {
                    r6.A0N(this.A06);
                }
                AnonymousClass0o6 r02 = this.A03;
                if (r02 == null) {
                    jsonSerializer.A0D(A02, r6, r7);
                } else {
                    jsonSerializer.A0A(A02, r6, r7, r02);
                }
            } else {
                throw new AnonymousClass0aG("Direct self-reference leading to cycle");
            }
        }
    }

    public AnonymousClass0DN(AnonymousClass0Jw r2, AbstractC07200ov r3) {
        super(r2, r2.A06);
        this.A00 = r3;
    }

    public AnonymousClass0DN(AnonymousClass0DN r1, AbstractC07200ov r2, C02620aS r3) {
        super(r1, r3);
        this.A00 = r2;
    }
}
