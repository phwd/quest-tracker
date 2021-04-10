package com.fasterxml.jackson.databind;

import com.oculus.common.build.BuildConfig;
import java.io.Serializable;

public class PropertyName implements Serializable {
    public static final PropertyName NO_NAME = new PropertyName(new String("#disabled"), null);
    public static final PropertyName USE_DEFAULT = new PropertyName(BuildConfig.PROVIDER_SUFFIX, null);
    private static final long serialVersionUID = 7930806520033045126L;
    protected final String _namespace;
    protected final String _simpleName;

    public PropertyName(String str, String str2) {
        this._simpleName = str == null ? BuildConfig.PROVIDER_SUFFIX : str;
        this._namespace = str2;
    }

    /* access modifiers changed from: protected */
    public Object readResolve() {
        String str = this._simpleName;
        if (str == null || BuildConfig.PROVIDER_SUFFIX.equals(str)) {
            return USE_DEFAULT;
        }
        return this._simpleName.equals("#disabled") ? NO_NAME : this;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || obj.getClass() != getClass()) {
            return false;
        }
        PropertyName propertyName = (PropertyName) obj;
        String str = this._simpleName;
        if (str == null) {
            if (propertyName._simpleName != null) {
                return false;
            }
        } else if (!str.equals(propertyName._simpleName)) {
            return false;
        }
        String str2 = this._namespace;
        if (str2 != null) {
            return str2.equals(propertyName._namespace);
        }
        if (propertyName._namespace == null) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        String str = this._namespace;
        if (str == null) {
            return this._simpleName.hashCode();
        }
        return str.hashCode() ^ this._simpleName.hashCode();
    }

    public String toString() {
        if (this._namespace == null) {
            return this._simpleName;
        }
        return "{" + this._namespace + "}" + this._simpleName;
    }
}
