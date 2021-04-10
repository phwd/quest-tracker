package X;

public abstract class JF extends AbstractC1024qt implements OB {
    public static final long serialVersionUID = -3581199092426900829L;
    public volatile transient String A00;

    public final String A0I() {
        StringBuilder sb;
        String str;
        if (!(this instanceof fF)) {
            if (this instanceof C0681fG) {
                C0681fG fGVar = (C0681fG) this;
                sb = new StringBuilder();
                sb.append(fGVar._class.getName());
                AbstractC1024qt qtVar = fGVar._keyType;
                if (qtVar != null) {
                    sb.append('<');
                    sb.append(qtVar.A02());
                    sb.append(',');
                    str = fGVar._valueType.A02();
                }
                return sb.toString();
            } else if (!(this instanceof C0682fH)) {
                return this._class.getName();
            } else {
                C0682fH fHVar = (C0682fH) this;
                sb = new StringBuilder();
                sb.append(fHVar._class.getName());
                AbstractC1024qt qtVar2 = fHVar._elementType;
                if (qtVar2 != null) {
                    sb.append('<');
                    str = qtVar2.A02();
                }
                return sb.toString();
            }
            sb.append(str);
            sb.append('>');
            return sb.toString();
        }
        fF fFVar = (fF) this;
        StringBuilder sb2 = new StringBuilder();
        sb2.append(fFVar._class.getName());
        AbstractC1024qt[] qtVarArr = fFVar._typeParameters;
        if (qtVarArr != null && (r4 = qtVarArr.length) > 0) {
            sb2.append('<');
            boolean z = true;
            for (AbstractC1024qt qtVar3 : qtVarArr) {
                if (z) {
                    z = false;
                } else {
                    sb2.append(',');
                }
                sb2.append(qtVar3.A02());
            }
            sb2.append('>');
        }
        return sb2.toString();
    }

    @Override // X.OB
    public final void A4x(AbstractC1012qg qgVar, AbstractC1031r2 r2Var) {
        qgVar.A0P(A0I());
    }

    @Override // X.OB
    public final void A4y(AbstractC1012qg qgVar, AbstractC1031r2 r2Var, PU pu) {
        pu.A03(this, qgVar);
        A4x(qgVar, r2Var);
        pu.A06(this, qgVar);
    }

    public JF(Class cls, int i, Object obj, Object obj2, boolean z) {
        super(cls, i, obj, obj2, z);
    }
}
