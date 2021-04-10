package X;

import java.util.Collection;
import java.util.Iterator;

/* renamed from: X.0P1  reason: invalid class name */
public final class AnonymousClass0P1 extends C02180iD {
    public static final long serialVersionUID = 1;
    public transient String A00;
    public final Collection<Object> _propertyIds;
    public final Class<?> _referringClass;
    public final String _unrecognizedPropertyName;

    @Override // X.C03620oC
    public final String A03() {
        Collection<Object> collection;
        String str = this.A00;
        if (str != null || (collection = this._propertyIds) == null) {
            return str;
        }
        StringBuilder sb = new StringBuilder(100);
        int size = collection.size();
        if (size != 1) {
            sb.append(" (");
            sb.append(size);
            sb.append(" known properties: ");
            Iterator<Object> it = this._propertyIds.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                sb.append(", \"");
                sb.append(String.valueOf(it.next()));
                sb.append('\"');
                if (sb.length() > 200) {
                    sb.append(" [truncated]");
                    break;
                }
            }
        } else {
            sb.append(" (one known property: \"");
            sb.append(String.valueOf(this._propertyIds.iterator().next()));
            sb.append('\"');
        }
        sb.append("])");
        String obj = sb.toString();
        this.A00 = obj;
        return obj;
    }

    public AnonymousClass0P1(String str, AnonymousClass0o8 r2, Class<?> cls, String str2, Collection<Object> collection) {
        super(str, r2);
        this._referringClass = cls;
        this._unrecognizedPropertyName = str2;
        this._propertyIds = collection;
    }
}
