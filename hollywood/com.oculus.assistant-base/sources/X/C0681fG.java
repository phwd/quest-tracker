package X;

import com.facebook.acra.util.JavaProcFileReader;

/* renamed from: X.fG  reason: case insensitive filesystem */
public class C0681fG extends JF {
    public static final long serialVersionUID = 416067702302823522L;
    public final AbstractC1024qt _keyType;
    public final AbstractC1024qt _valueType;

    @Override // X.AbstractC1024qt
    public final boolean equals(Object obj) {
        if (obj != this) {
            if (obj == null || obj.getClass() != getClass()) {
                return false;
            }
            C0681fG fGVar = (C0681fG) obj;
            if (this._class != fGVar._class || !this._keyType.equals(fGVar._keyType) || !this._valueType.equals(fGVar._valueType)) {
                return false;
            }
        }
        return true;
    }

    public final AbstractC1024qt A0J(Class cls) {
        if (!(this instanceof C00313p)) {
            AbstractC1024qt qtVar = this._keyType;
            if (cls == qtVar._class) {
                return this;
            }
            return new C0681fG(this._class, qtVar.A09(cls), this._valueType, this._valueHandler, this._typeHandler, this._asStatic);
        }
        AbstractC1024qt qtVar2 = this._keyType;
        if (cls == qtVar2._class) {
            return this;
        }
        return new C00313p(this._class, qtVar2.A09(cls), this._valueType, this._valueHandler, this._typeHandler, this._asStatic);
    }

    public final C0681fG A0K(Object obj) {
        if (!(this instanceof C00313p)) {
            return new C0681fG(this._class, this._keyType.A0E(obj), this._valueType, this._valueHandler, this._typeHandler, this._asStatic);
        }
        return new C00313p(this._class, this._keyType.A0E(obj), this._valueType, this._valueHandler, this._typeHandler, this._asStatic);
    }

    @Override // X.AbstractC1024qt
    public String toString() {
        StringBuilder sb = new StringBuilder("[map-like type; class ");
        sb.append(this._class.getName());
        sb.append(", ");
        sb.append(this._keyType);
        sb.append(JavaProcFileReader.LS_SYMLINK_ARROW);
        sb.append(this._valueType);
        sb.append("]");
        return sb.toString();
    }

    public C0681fG(Class cls, AbstractC1024qt qtVar, AbstractC1024qt qtVar2, Object obj, Object obj2, boolean z) {
        super(cls, qtVar.hashCode() ^ qtVar2.hashCode(), obj, obj2, z);
        this._keyType = qtVar;
        this._valueType = qtVar2;
    }
}
