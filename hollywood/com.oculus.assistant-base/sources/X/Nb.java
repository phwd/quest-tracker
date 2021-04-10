package X;

import com.facebook.assistant.oacr.OacrConstants;
import java.io.Serializable;

public final class Nb implements Comparable, Serializable {
    public static final Nb A00 = new Nb(0, 0, 0, null, null, null);
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
            Nb nb = (Nb) obj;
            if (!(nb._majorVersion == this._majorVersion && nb._minorVersion == this._minorVersion && nb._patchLevel == this._patchLevel && nb._artifactId.equals(this._artifactId) && nb._groupId.equals(this._groupId))) {
                return false;
            }
        }
        return true;
    }

    @Override // java.lang.Comparable
    public final int compareTo(Object obj) {
        Nb nb = (Nb) obj;
        if (nb == this) {
            return 0;
        }
        int compareTo = this._groupId.compareTo(nb._groupId);
        if (compareTo != 0) {
            return compareTo;
        }
        int compareTo2 = this._artifactId.compareTo(nb._artifactId);
        if (compareTo2 != 0) {
            return compareTo2;
        }
        int i = this._majorVersion - nb._majorVersion;
        if (i != 0) {
            return i;
        }
        int i2 = this._minorVersion - nb._minorVersion;
        if (i2 == 0) {
            return this._patchLevel - nb._patchLevel;
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

    public Nb(int i, int i2, int i3, String str, String str2, String str3) {
        this._majorVersion = i;
        this._minorVersion = i2;
        this._patchLevel = i3;
        this._snapshotInfo = str;
        String str4 = OacrConstants.AUTO_SPEECH_DOMAIN;
        this._groupId = str2 == null ? str4 : str2;
        this._artifactId = str3 != null ? str3 : str4;
    }
}
