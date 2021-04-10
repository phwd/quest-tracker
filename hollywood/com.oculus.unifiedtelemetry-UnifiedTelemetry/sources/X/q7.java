package X;

import java.io.Serializable;

public final class q7 implements Comparable<q7>, Serializable {
    public static final q7 A00 = new q7(0, 0, 0, null, null, null);
    public static final long serialVersionUID = 1;
    public final String _artifactId;
    public final String _groupId;
    public final int _majorVersion;
    public final int _minorVersion;
    public final int _patchLevel;
    public final String _snapshotInfo;

    public final boolean equals(Object obj) {
        if (obj != this) {
            if (obj == null || obj.getClass() != getClass()) {
                return false;
            }
            q7 q7Var = (q7) obj;
            if (!(q7Var._majorVersion == this._majorVersion && q7Var._minorVersion == this._minorVersion && q7Var._patchLevel == this._patchLevel && q7Var._artifactId.equals(this._artifactId) && q7Var._groupId.equals(this._groupId))) {
                return false;
            }
        }
        return true;
    }

    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object] */
    @Override // java.lang.Comparable
    public final int compareTo(q7 q7Var) {
        q7 q7Var2 = q7Var;
        if (q7Var2 == this) {
            return 0;
        }
        int compareTo = this._groupId.compareTo(q7Var2._groupId);
        if (compareTo != 0) {
            return compareTo;
        }
        int compareTo2 = this._artifactId.compareTo(q7Var2._artifactId);
        if (compareTo2 != 0) {
            return compareTo2;
        }
        int i = this._majorVersion - q7Var2._majorVersion;
        if (i != 0) {
            return i;
        }
        int i2 = this._minorVersion - q7Var2._minorVersion;
        if (i2 == 0) {
            return this._patchLevel - q7Var2._patchLevel;
        }
        return i2;
    }

    public final int hashCode() {
        return this._artifactId.hashCode() ^ (((this._groupId.hashCode() + this._majorVersion) - this._minorVersion) + this._patchLevel);
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this._majorVersion);
        sb.append('.');
        sb.append(this._minorVersion);
        sb.append('.');
        sb.append(this._patchLevel);
        String str = this._snapshotInfo;
        if (str != null && str.length() > 0) {
            sb.append('-');
            sb.append(str);
        }
        return sb.toString();
    }

    public q7(int i, int i2, int i3, String str, String str2, String str3) {
        this._majorVersion = i;
        this._minorVersion = i2;
        this._patchLevel = i3;
        this._snapshotInfo = str;
        String str4 = "";
        this._groupId = str2 == null ? str4 : str2;
        this._artifactId = str3 != null ? str3 : str4;
    }
}
