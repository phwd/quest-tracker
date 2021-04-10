package X;

import com.google.gson.internal.bind.ReflectiveTypeAdapterFactory;
import java.io.IOException;
import java.lang.reflect.Field;

/* renamed from: X.0dq  reason: invalid class name and case insensitive filesystem */
public class C01380dq extends AnonymousClass14C {
    public final /* synthetic */ AnonymousClass13N A00;
    public final /* synthetic */ AnonymousClass13Y A01;
    public final /* synthetic */ ReflectiveTypeAdapterFactory A02;
    public final /* synthetic */ AnonymousClass14H A03;
    public final /* synthetic */ Field A04;
    public final /* synthetic */ boolean A05;
    public final /* synthetic */ boolean A06;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public C01380dq(ReflectiveTypeAdapterFactory reflectiveTypeAdapterFactory, String str, boolean z, boolean z2, Field field, boolean z3, AnonymousClass13Y r7, AnonymousClass13N r8, AnonymousClass14H r9, boolean z4) {
        super(str, z, z2);
        this.A02 = reflectiveTypeAdapterFactory;
        this.A04 = field;
        this.A06 = z3;
        this.A01 = r7;
        this.A00 = r8;
        this.A03 = r9;
        this.A05 = z4;
    }

    @Override // X.AnonymousClass14C
    public final void A00(AnonymousClass14I r3, Object obj) throws IOException, IllegalAccessException {
        Object A022 = this.A01.A02(r3);
        if (A022 != null || !this.A05) {
            this.A04.set(obj, A022);
        }
    }

    @Override // X.AnonymousClass14C
    public final void A01(AnonymousClass14L r6, Object obj) throws IOException, IllegalAccessException {
        AnonymousClass13Y r3;
        Object obj2 = this.A04.get(obj);
        if (this.A06) {
            r3 = this.A01;
        } else {
            r3 = new AnonymousClass0dg(this.A00, this.A01, this.A03.A02);
        }
        r3.A03(r6, obj2);
    }

    @Override // X.AnonymousClass14C
    public final boolean A02(Object obj) throws IOException, IllegalAccessException {
        if (!super.A01 || this.A04.get(obj) == obj) {
            return false;
        }
        return true;
    }
}
