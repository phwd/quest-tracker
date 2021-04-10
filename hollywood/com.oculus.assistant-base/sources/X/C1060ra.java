package X;

import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ser.impl.UnwrappingBeanSerializer;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;

/* renamed from: X.ra  reason: case insensitive filesystem */
public class C1060ra implements O5 {
    public static final Object A0I = new Object();
    public AbstractC1024qt A00;
    public JsonSerializer A01;
    public JsonSerializer A02;
    public PU A03;
    public AbstractC0292Po A04;
    public HashMap A05;
    public final C1015qj A06;
    public final AbstractC1024qt A07;
    public final Object A08;
    public final Field A09;
    public final Method A0A;
    public final boolean A0B;
    public final Class[] A0C;
    public final AbstractC1024qt A0D;
    public final OE A0E;
    public final AbstractC1044rJ A0F;
    public final Q0 A0G;
    public final boolean A0H;

    private final JsonSerializer A00(AbstractC0292Po po, Class cls, AbstractC1031r2 r2Var) {
        JsonSerializer A0A2;
        JsonSerializer A0A3;
        AbstractC0292Po A012;
        if (!(this instanceof MN)) {
            AbstractC1024qt qtVar = this.A00;
            if (qtVar != null) {
                AbstractC1024qt A032 = r2Var.A03(qtVar, cls);
                A0A3 = r2Var.A08(A032, this);
                A012 = po.A01(A032._class, A0A3);
            } else {
                A0A3 = r2Var.A0A(cls, this);
                A012 = po.A01(cls, A0A3);
            }
            C0290Pm pm = new C0290Pm(A0A3, A012);
            AbstractC0292Po po2 = pm.A01;
            if (po != po2) {
                this.A04 = po2;
            }
            return pm.A00;
        }
        MN mn = (MN) this;
        AbstractC1024qt qtVar2 = ((C1060ra) mn).A00;
        if (qtVar2 != null) {
            A0A2 = r2Var.A08(r2Var.A03(qtVar2, cls), mn);
        } else {
            A0A2 = r2Var.A0A(cls, mn);
        }
        QC qc = mn.A00;
        if (A0A2 instanceof UnwrappingBeanSerializer) {
            qc = new C1077rt(qc, ((UnwrappingBeanSerializer) A0A2).A00);
        }
        JsonSerializer A072 = A0A2.A07(qc);
        mn.A04 = mn.A04.A01(cls, A072);
        return A072;
    }

    public final C1060ra A01(QC qc) {
        if (this instanceof MN) {
            MN mn = (MN) this;
            return new MN(mn, new C1077rt(qc, mn.A00), new C1015qj(qc.A00(mn.A06.getValue())));
        } else if (this instanceof MP) {
            MP mp = (MP) this;
            return new MP(mp.A00.A01(qc), mp.A01);
        } else if (!(this instanceof MZ)) {
            C1015qj qjVar = this.A06;
            String A002 = qc.A00(qjVar.getValue());
            if (A002.equals(qjVar.toString())) {
                return this;
            }
            return new C1060ra(this, new C1015qj(A002));
        } else {
            MZ mz = (MZ) this;
            return new MZ(mz.A00.A01(qc), mz.A01);
        }
    }

    public final Object A02(Object obj) {
        Method method = this.A0A;
        if (method != null) {
            return method.invoke(obj, new Object[0]);
        }
        return this.A09.get(obj);
    }

    public final void A03(JsonSerializer jsonSerializer) {
        C1060ra raVar;
        if (this instanceof MP) {
            raVar = ((MP) this).A00;
        } else if (!(this instanceof MZ)) {
            JsonSerializer jsonSerializer2 = this.A01;
            if (jsonSerializer2 == null || jsonSerializer2 == jsonSerializer) {
                this.A01 = jsonSerializer;
                return;
            }
            throw new IllegalStateException("Can not override null serializer");
        } else {
            raVar = ((MZ) this).A00;
        }
        raVar.A03(jsonSerializer);
    }

    public void A04(JsonSerializer jsonSerializer) {
        JsonSerializer jsonSerializer2 = this.A02;
        if (jsonSerializer2 == null || jsonSerializer2 == jsonSerializer) {
            this.A02 = jsonSerializer;
            return;
        }
        throw new IllegalStateException("Can not override serializer");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:16:0x002e, code lost:
        if (r0 != false) goto L_0x0030;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void A05(java.lang.Object r7, X.AbstractC1012qg r8, X.AbstractC1031r2 r9) {
        /*
        // Method dump skipped, instructions count: 157
        */
        throw new UnsupportedOperationException("Method not decompiled: X.C1060ra.A05(java.lang.Object, X.qg, X.r2):void");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:62:0x00ba, code lost:
        if ((r2 instanceof com.fasterxml.jackson.databind.ser.impl.UnwrappingBeanSerializer) == false) goto L_0x00ca;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void A06(java.lang.Object r7, X.AbstractC1012qg r8, X.AbstractC1031r2 r9) {
        /*
        // Method dump skipped, instructions count: 235
        */
        throw new UnsupportedOperationException("Method not decompiled: X.C1060ra.A06(java.lang.Object, X.qg, X.r2):void");
    }

    public final String toString() {
        String name;
        String A042;
        StringBuilder sb = new StringBuilder(40);
        sb.append("property '");
        sb.append(this.A06.getValue());
        sb.append("' (");
        Method method = this.A0A;
        if (method != null) {
            sb.append("via method ");
            sb.append(method.getDeclaringClass().getName());
            sb.append("#");
            name = method.getName();
        } else {
            sb.append("field \"");
            Field field = this.A09;
            sb.append(field.getDeclaringClass().getName());
            sb.append("#");
            name = field.getName();
        }
        sb.append(name);
        JsonSerializer jsonSerializer = this.A02;
        if (jsonSerializer == null) {
            A042 = ", no static serializer";
        } else {
            A042 = AnonymousClass08.A04(", static serializer of type ", jsonSerializer.getClass().getName());
        }
        sb.append(A042);
        sb.append(')');
        return sb.toString();
    }

    @Override // X.O5
    public final AbstractC1044rJ A2e() {
        return this.A0F;
    }

    @Override // X.O5
    public final AbstractC1024qt A34() {
        return this.A0D;
    }

    public C1060ra(PE pe, AbstractC1044rJ rJVar, Q0 q0, AbstractC1024qt qtVar, JsonSerializer jsonSerializer, PU pu, AbstractC1024qt qtVar2, boolean z, Object obj) {
        C1062rc rcVar;
        Class[] clsArr;
        this.A0F = rJVar;
        this.A0G = q0;
        this.A06 = new C1015qj(pe.A0D());
        this.A0E = pe.A06();
        this.A0D = qtVar;
        this.A02 = jsonSerializer;
        if (jsonSerializer == null) {
            rcVar = C1062rc.A00;
        } else {
            rcVar = null;
        }
        this.A04 = rcVar;
        this.A03 = pu;
        this.A07 = qtVar2;
        this.A0H = pe.A0J();
        if (rJVar instanceof St) {
            this.A0A = null;
            this.A09 = (Field) rJVar.A0R();
        } else if (rJVar instanceof AnonymousClass0q) {
            this.A0A = (Method) rJVar.A0R();
            this.A09 = null;
        } else {
            throw new IllegalArgumentException(AnonymousClass08.A04("Can not pass member of type ", rJVar.getClass().getName()));
        }
        this.A0B = z;
        this.A08 = obj;
        if (!(pe instanceof C1052rR)) {
            clsArr = null;
        } else {
            C1052rR rRVar = (C1052rR) pe;
            clsArr = (Class[]) C1052rR.A04(rRVar, new C1048rN(rRVar));
        }
        this.A0C = clsArr;
        this.A01 = null;
    }

    public C1060ra(C1060ra raVar, C1015qj qjVar) {
        this.A06 = qjVar;
        this.A0E = raVar.A0E;
        this.A0F = raVar.A0F;
        this.A0G = raVar.A0G;
        this.A0D = raVar.A0D;
        this.A0A = raVar.A0A;
        this.A09 = raVar.A09;
        this.A02 = raVar.A02;
        this.A01 = raVar.A01;
        HashMap hashMap = raVar.A05;
        if (hashMap != null) {
            this.A05 = new HashMap(hashMap);
        }
        this.A07 = raVar.A07;
        this.A04 = raVar.A04;
        this.A0B = raVar.A0B;
        this.A08 = raVar.A08;
        this.A0C = raVar.A0C;
        this.A03 = raVar.A03;
        this.A00 = raVar.A00;
        this.A0H = raVar.A0H;
    }
}
