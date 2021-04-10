package X;

/* renamed from: X.0i7  reason: invalid class name and case insensitive filesystem */
public class C02140i7 implements AbstractC04050p8 {
    public final /* synthetic */ C02130i6 A00;
    public final /* synthetic */ C02130i6 A01;

    public C02140i7(C02130i6 r1, C02130i6 r2) {
        this.A00 = r1;
        this.A01 = r2;
    }

    @Override // X.AbstractC04050p8
    public final void A1E(AbstractC04590qj r3) {
        C02130i6 r1 = this.A01;
        r1._serializerFactory = r1._serializerFactory.A05(r3);
    }

    @Override // X.AbstractC04050p8
    public final void A1F(AbstractC04260pg r4) {
        C02130i6 r2 = this.A01;
        r2._deserializationContext = r2._deserializationContext.A0Q(r2._deserializationContext._factory.A0L(r4));
    }

    @Override // X.AbstractC04050p8
    public final void A1M(AbstractC04640qs r3) {
        C02130i6 r1 = this.A01;
        r1._serializerFactory = r1._serializerFactory.A06(r3);
    }

    @Override // X.AbstractC04050p8
    public final void A1O(AbstractC04730rA r6) {
        C04750rC r1;
        AbstractC04730rA[] r0;
        AnonymousClass0HU r02;
        AnonymousClass0HM r03;
        C02130i6 r4 = this.A01;
        AnonymousClass0r9 r12 = r4._typeFactory;
        AbstractC04730rA[] r04 = r12._modifiers;
        if (r04 == null) {
            r1 = r12._parser;
            r0 = new AbstractC04730rA[]{r6};
        } else {
            r1 = r12._parser;
            r0 = (AbstractC04730rA[]) C04790rG.A01(r04, r6);
        }
        AnonymousClass0r9 r3 = new AnonymousClass0r9(r1, r0);
        r4._typeFactory = r3;
        AnonymousClass0HU r2 = r4._deserializationConfig;
        C04140pQ r05 = r2._base;
        C04140pQ A012 = r05.A01(r3);
        if (r05 == A012) {
            r02 = r2;
        } else {
            r02 = new AnonymousClass0HU(r2, A012);
        }
        r4._deserializationConfig = r02;
        AnonymousClass0HM r22 = r4._serializationConfig;
        C04140pQ r06 = r22._base;
        C04140pQ A013 = r06.A01(r3);
        if (r06 == A013) {
            r03 = r22;
        } else {
            r03 = new AnonymousClass0HM(r22, A013);
        }
        r4._serializationConfig = r03;
    }
}
