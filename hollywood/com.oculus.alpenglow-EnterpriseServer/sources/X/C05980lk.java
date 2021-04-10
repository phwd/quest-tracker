package X;

import java.io.Serializable;

/* renamed from: X.0lk  reason: invalid class name and case insensitive filesystem */
public final class C05980lk implements Comparable<C05980lk>, Serializable {
    public static final C05980lk A00 = new C05980lk(0, 0, 0, null, null, null);
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
            C05980lk r5 = (C05980lk) obj;
            if (!(r5._majorVersion == this._majorVersion && r5._minorVersion == this._minorVersion && r5._patchLevel == this._patchLevel && r5._artifactId.equals(this._artifactId) && r5._groupId.equals(this._groupId))) {
                return false;
            }
        }
        return true;
    }

    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object] */
    @Override // java.lang.Comparable
    public final int compareTo(C05980lk r3) {
        C05980lk r32 = r3;
        if (r32 == this) {
            return 0;
        }
        int compareTo = this._groupId.compareTo(r32._groupId);
        if (compareTo != 0) {
            return compareTo;
        }
        int compareTo2 = this._artifactId.compareTo(r32._artifactId);
        if (compareTo2 != 0) {
            return compareTo2;
        }
        int i = this._majorVersion - r32._majorVersion;
        if (i != 0) {
            return i;
        }
        int i2 = this._minorVersion - r32._minorVersion;
        if (i2 == 0) {
            return this._patchLevel - r32._patchLevel;
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

    public C05980lk(int i, int i2, int i3, String str, String str2, String str3) {
        this._majorVersion = i;
        this._minorVersion = i2;
        this._patchLevel = i3;
        this._snapshotInfo = str;
        String str4 = "";
        this._groupId = str2 == null ? str4 : str2;
        this._artifactId = str3 != null ? str3 : str4;
    }
}
