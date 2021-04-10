package X;

import java.io.IOException;
import java.lang.reflect.Field;

public class T7 extends AbstractC0419hU {
    public final /* synthetic */ HY A00;
    public final /* synthetic */ AbstractC0131Ob A01;
    public final /* synthetic */ T5 A02;
    public final /* synthetic */ lQ A03;
    public final /* synthetic */ Field A04;
    public final /* synthetic */ boolean A05;
    public final /* synthetic */ boolean A06;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public T7(T5 t5, String str, boolean z, boolean z2, Field field, boolean z3, AbstractC0131Ob ob, HY hy, lQ lQVar, boolean z4) {
        super(str, z, z2);
        this.A02 = t5;
        this.A04 = field;
        this.A06 = z3;
        this.A01 = ob;
        this.A00 = hy;
        this.A03 = lQVar;
        this.A05 = z4;
    }

    @Override // X.AbstractC0419hU
    public final void A00(lk lkVar, Object obj) throws IOException, IllegalAccessException {
        Object A022 = this.A01.A02(lkVar);
        if (A022 != null || !this.A05) {
            this.A04.set(obj, A022);
        }
    }

    @Override // X.AbstractC0419hU
    public final void A01(mm mmVar, Object obj) throws IOException, IllegalAccessException {
        AbstractC0131Ob sx;
        Object obj2 = this.A04.get(obj);
        if (this.A06) {
            sx = this.A01;
        } else {
            sx = new C0162Sx(this.A00, this.A01, this.A03.type);
        }
        sx.A03(mmVar, obj2);
    }

    @Override // X.AbstractC0419hU
    public final boolean A02(Object obj) throws IOException, IllegalAccessException {
        if (!super.A01 || this.A04.get(obj) == obj) {
            return false;
        }
        return true;
    }
}
