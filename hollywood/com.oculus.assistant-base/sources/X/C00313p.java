package X;

import com.facebook.acra.util.JavaProcFileReader;

/* renamed from: X.3p  reason: invalid class name and case insensitive filesystem */
public final class C00313p extends C0681fG {
    public static final long serialVersionUID = -811146779148281500L;

    public static C00313p A00(Class cls, AbstractC1024qt qtVar, AbstractC1024qt qtVar2) {
        return new C00313p(cls, qtVar, qtVar2, null, null, false);
    }

    @Override // X.AbstractC1024qt, X.C0681fG
    public final String toString() {
        StringBuilder sb = new StringBuilder("[map type; class ");
        sb.append(this._class.getName());
        sb.append(", ");
        sb.append(this._keyType);
        sb.append(JavaProcFileReader.LS_SYMLINK_ARROW);
        sb.append(this._valueType);
        sb.append("]");
        return sb.toString();
    }

    public C00313p(Class cls, AbstractC1024qt qtVar, AbstractC1024qt qtVar2, Object obj, Object obj2, boolean z) {
        super(cls, qtVar, qtVar2, obj, obj2, z);
    }
}
