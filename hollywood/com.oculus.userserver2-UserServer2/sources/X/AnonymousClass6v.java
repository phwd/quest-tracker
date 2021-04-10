package X;

import java.io.Serializable;

/* renamed from: X.6v  reason: invalid class name */
public class AnonymousClass6v implements Serializable {
    public static final long serialVersionUID = 1;
    public String _fieldName;
    public Object _from;
    public int _index = -1;

    public final String toString() {
        Class<?> cls;
        char c;
        StringBuilder sb = new StringBuilder();
        Object obj = this._from;
        if (obj instanceof Class) {
            cls = (Class) obj;
        } else {
            cls = obj.getClass();
        }
        Package r0 = cls.getPackage();
        if (r0 != null) {
            sb.append(r0.getName());
            sb.append('.');
        }
        sb.append(cls.getSimpleName());
        sb.append('[');
        String str = this._fieldName;
        if (str != null) {
            c = '\"';
            sb.append('\"');
            sb.append(str);
        } else {
            int i = this._index;
            if (i >= 0) {
                sb.append(i);
                sb.append(']');
                return sb.toString();
            }
            c = '?';
        }
        sb.append(c);
        sb.append(']');
        return sb.toString();
    }

    public AnonymousClass6v() {
    }

    public AnonymousClass6v(Object obj, String str) {
        this._from = obj;
        if (str != null) {
            this._fieldName = str;
            return;
        }
        throw new NullPointerException("Can not pass null fieldName");
    }
}
