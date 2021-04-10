package X;

import com.fasterxml.jackson.databind.JsonSerializer;

/* renamed from: X.0Cc  reason: invalid class name */
public final class AnonymousClass0Cc extends AnonymousClass0Og {
    public final AnonymousClass0Og A00;
    public final Class<?> A01;

    public AnonymousClass0Cc(AnonymousClass0Og r2, Class<?> cls) {
        super(r2, r2.A06);
        this.A00 = r2;
        this.A01 = cls;
    }

    @Override // X.AnonymousClass0Og
    public final AnonymousClass0Og A01(AbstractC04870rR r4) {
        return new AnonymousClass0Cc(this.A00.A01(r4), this.A01);
    }

    @Override // X.AnonymousClass0Og
    public final void A03(JsonSerializer<Object> jsonSerializer) {
        this.A00.A03(jsonSerializer);
    }

    @Override // X.AnonymousClass0Og
    public final void A04(JsonSerializer<Object> jsonSerializer) {
        this.A00.A04(jsonSerializer);
    }

    @Override // X.AnonymousClass0Og
    public final void A05(Object obj, AbstractC02300iS r4, AbstractC02120i3 r5) throws Exception {
        Class<?> cls = r5._serializationView;
        if (cls == null || this.A01.isAssignableFrom(cls)) {
            this.A00.A05(obj, r4, r5);
            return;
        }
        JsonSerializer<Object> jsonSerializer = this.A00.A01;
        if (jsonSerializer != null) {
            jsonSerializer.serialize(null, r4, r5);
        } else {
            r4.A0G();
        }
    }

    @Override // X.AnonymousClass0Og
    public final void A06(Object obj, AbstractC02300iS r4, AbstractC02120i3 r5) throws Exception {
        Class<?> cls = r5._serializationView;
        if (cls == null || this.A01.isAssignableFrom(cls)) {
            this.A00.A06(obj, r4, r5);
        }
    }
}
