package X;

import com.google.gson.internal.bind.ReflectiveTypeAdapterFactory;
import java.io.IOException;
import java.lang.reflect.Field;

/* renamed from: X.0Wj  reason: invalid class name and case insensitive filesystem */
public class C01650Wj extends AbstractC09070zL {
    public final /* synthetic */ C08780ya A00;
    public final /* synthetic */ AnonymousClass0yl A01;
    public final /* synthetic */ ReflectiveTypeAdapterFactory A02;
    public final /* synthetic */ C09110zQ A03;
    public final /* synthetic */ Field A04;
    public final /* synthetic */ boolean A05;
    public final /* synthetic */ boolean A06;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public C01650Wj(ReflectiveTypeAdapterFactory reflectiveTypeAdapterFactory, String str, boolean z, boolean z2, Field field, boolean z3, AnonymousClass0yl r7, C08780ya r8, C09110zQ r9, boolean z4) {
        super(str, z, z2);
        this.A02 = reflectiveTypeAdapterFactory;
        this.A04 = field;
        this.A06 = z3;
        this.A01 = r7;
        this.A00 = r8;
        this.A03 = r9;
        this.A05 = z4;
    }

    @Override // X.AbstractC09070zL
    public final void A00(C09120zR r3, Object obj) throws IOException, IllegalAccessException {
        Object A022 = this.A01.A02(r3);
        if (A022 != null || !this.A05) {
            this.A04.set(obj, A022);
        }
    }

    @Override // X.AbstractC09070zL
    public final void A01(C09130zU r6, Object obj) throws IOException, IllegalAccessException {
        AnonymousClass0yl r3;
        Object obj2 = this.A04.get(obj);
        if (this.A06) {
            r3 = this.A01;
        } else {
            r3 = new AnonymousClass0VA(this.A00, this.A01, this.A03.A02);
        }
        r3.A03(r6, obj2);
    }

    @Override // X.AbstractC09070zL
    public final boolean A02(Object obj) throws IOException, IllegalAccessException {
        if (!super.A01 || this.A04.get(obj) == obj) {
            return false;
        }
        return true;
    }
}
