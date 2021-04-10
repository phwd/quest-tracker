package X;

import com.fasterxml.jackson.databind.JsonSerializer;

/* renamed from: X.0Dw  reason: invalid class name and case insensitive filesystem */
public final class C01190Dw extends AnonymousClass0Jw {
    public final AnonymousClass0Jw A00;
    public final Class<?>[] A01;

    public C01190Dw(AnonymousClass0Jw r2, Class<?>[] clsArr) {
        super(r2, r2.A06);
        this.A00 = r2;
        this.A01 = clsArr;
    }

    @Override // X.AnonymousClass0Jw
    public final AnonymousClass0Jw A01(AbstractC07200ov r4) {
        return new C01190Dw(this.A00.A01(r4), this.A01);
    }

    @Override // X.AnonymousClass0Jw
    public final void A03(JsonSerializer<Object> jsonSerializer) {
        this.A00.A03(jsonSerializer);
    }

    @Override // X.AnonymousClass0Jw
    public final void A04(JsonSerializer<Object> jsonSerializer) {
        this.A00.A04(jsonSerializer);
    }

    @Override // X.AnonymousClass0Jw
    public final void A05(Object obj, AbstractC02640aV r7, AnonymousClass0a8 r8) throws Exception {
        Class<?> cls = r8._serializationView;
        if (cls != null) {
            int i = 0;
            Class<?>[] clsArr = this.A01;
            int length = clsArr.length;
            while (i < length && !clsArr[i].isAssignableFrom(cls)) {
                i++;
            }
            if (i == length) {
                JsonSerializer<Object> jsonSerializer = this.A00.A01;
                if (jsonSerializer != null) {
                    jsonSerializer.A0D(null, r7, r8);
                    return;
                } else {
                    r7.A0D();
                    return;
                }
            }
        }
        this.A00.A05(obj, r7, r8);
    }

    @Override // X.AnonymousClass0Jw
    public final void A06(Object obj, AbstractC02640aV r7, AnonymousClass0a8 r8) throws Exception {
        Class<?> cls = r8._serializationView;
        if (cls != null) {
            int i = 0;
            Class<?>[] clsArr = this.A01;
            int length = clsArr.length;
            while (i < length && !clsArr[i].isAssignableFrom(cls)) {
                i++;
            }
            if (i == length) {
                return;
            }
        }
        this.A00.A06(obj, r7, r8);
    }
}
