package X;

public final class fF extends JF {
    public static final long serialVersionUID = -800374828948534376L;
    public final String[] _typeNames;
    public final AbstractC1024qt[] _typeParameters;

    public static fF A00(Class cls) {
        return new fF(cls, null, null, null, null, false);
    }

    @Override // X.AbstractC1024qt
    public final boolean equals(Object obj) {
        int length;
        if (obj == this) {
            return true;
        }
        if (obj != null && obj.getClass() == getClass()) {
            fF fFVar = (fF) obj;
            if (fFVar._class == this._class) {
                AbstractC1024qt[] qtVarArr = this._typeParameters;
                AbstractC1024qt[] qtVarArr2 = fFVar._typeParameters;
                if (qtVarArr == null) {
                    return qtVarArr2 == null || qtVarArr2.length == 0;
                }
                if (qtVarArr2 != null && (length = qtVarArr.length) == qtVarArr2.length) {
                    for (int i = 0; i < length; i++) {
                        if (qtVarArr[i].equals(qtVarArr2[i])) {
                        }
                    }
                    return true;
                }
            }
        }
        return false;
    }

    @Override // X.AbstractC1024qt
    public final String toString() {
        StringBuilder sb = new StringBuilder(40);
        sb.append("[simple type, class ");
        sb.append(A0I());
        sb.append(']');
        return sb.toString();
    }

    public fF(Class cls) {
        this(cls, null, null, null, null, false);
    }

    public fF(Class cls, String[] strArr, AbstractC1024qt[] qtVarArr, Object obj, Object obj2, boolean z) {
        super(cls, 0, obj, obj2, z);
        if (strArr == null || strArr.length == 0) {
            this._typeNames = null;
            this._typeParameters = null;
            return;
        }
        this._typeNames = strArr;
        this._typeParameters = qtVarArr;
    }
}
