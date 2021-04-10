package X;

import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ser.impl.UnwrappingBeanSerializer;

/* renamed from: X.0CT  reason: invalid class name */
public final class AnonymousClass0CT extends AnonymousClass0Og {
    public final AbstractC04870rR A00;

    @Override // X.AnonymousClass0Og
    public final JsonSerializer<Object> A00(AbstractC04690qz r5, Class<?> cls, AbstractC02120i3 r7) throws C02180iD {
        JsonSerializer<Object> A0B;
        AbstractC02190iF r0 = super.A00;
        if (r0 != null) {
            A0B = r7.A09(r7.A04(r0, cls), this);
        } else {
            A0B = r7.A0B(cls, this);
        }
        AbstractC04870rR r2 = this.A00;
        if (A0B.isUnwrappingSerializer()) {
            r2 = new C01710gt(r2, ((UnwrappingBeanSerializer) A0B).A00);
        }
        JsonSerializer<Object> unwrappingSerializer = A0B.unwrappingSerializer(r2);
        this.A04 = this.A04.A01(cls, unwrappingSerializer);
        return unwrappingSerializer;
    }

    @Override // X.AnonymousClass0Og
    public final AnonymousClass0Og A01(AbstractC04870rR r5) {
        return new AnonymousClass0CT(this, new C01710gt(r5, this.A00), new C02270iP(r5.A00(this.A06.getValue())));
    }

    @Override // X.AnonymousClass0Og
    public final void A04(JsonSerializer<Object> jsonSerializer) {
        super.A04(jsonSerializer);
        JsonSerializer<Object> jsonSerializer2 = this.A02;
        if (jsonSerializer2 != null) {
            AbstractC04870rR r2 = this.A00;
            if (jsonSerializer2.isUnwrappingSerializer()) {
                r2 = new C01710gt(r2, ((UnwrappingBeanSerializer) jsonSerializer2).A00);
            }
            this.A02 = jsonSerializer2.unwrappingSerializer(r2);
        }
    }

    @Override // X.AnonymousClass0Og
    public final void A06(Object obj, AbstractC02300iS r6, AbstractC02120i3 r7) throws Exception {
        boolean equals;
        Class<?> cls;
        AbstractC04690qz r0;
        Object A02 = A02(obj);
        if (A02 != null) {
            JsonSerializer<Object> jsonSerializer = this.A02;
            if (jsonSerializer == null && (jsonSerializer = (r0 = this.A04).A00((cls = A02.getClass()))) == null) {
                jsonSerializer = A00(r0, cls, r7);
            }
            Object obj2 = this.A0H;
            if (obj2 != null) {
                if (AnonymousClass0Og.A0I == obj2) {
                    equals = jsonSerializer.isEmpty(A02);
                } else {
                    equals = obj2.equals(A02);
                }
                if (equals) {
                    return;
                }
            }
            if (A02 != obj || jsonSerializer.usesObjectId()) {
                if (!jsonSerializer.isUnwrappingSerializer()) {
                    r6.A0P(this.A06);
                }
                AbstractC04550qd r02 = this.A03;
                if (r02 == null) {
                    jsonSerializer.serialize(A02, r6, r7);
                } else {
                    jsonSerializer.serializeWithType(A02, r6, r7, r02);
                }
            } else {
                throw new C02180iD("Direct self-reference leading to cycle");
            }
        }
    }

    public AnonymousClass0CT(AnonymousClass0Og r2, AbstractC04870rR r3) {
        super(r2, r2.A06);
        this.A00 = r3;
    }

    public AnonymousClass0CT(AnonymousClass0CT r1, AbstractC04870rR r2, C02270iP r3) {
        super(r1, r3);
        this.A00 = r2;
    }
}
