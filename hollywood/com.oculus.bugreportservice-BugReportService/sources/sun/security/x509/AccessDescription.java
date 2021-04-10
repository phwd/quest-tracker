package sun.security.x509;

import sun.security.util.DerOutputStream;
import sun.security.util.ObjectIdentifier;

public final class AccessDescription {
    public static final ObjectIdentifier Ad_CAISSUERS_Id = ObjectIdentifier.newInternal(new int[]{1, 3, 6, 1, 5, 5, 7, 48, 2});
    public static final ObjectIdentifier Ad_CAREPOSITORY_Id = ObjectIdentifier.newInternal(new int[]{1, 3, 6, 1, 5, 5, 7, 48, 5});
    public static final ObjectIdentifier Ad_OCSP_Id = ObjectIdentifier.newInternal(new int[]{1, 3, 6, 1, 5, 5, 7, 48, 1});
    public static final ObjectIdentifier Ad_TIMESTAMPING_Id = ObjectIdentifier.newInternal(new int[]{1, 3, 6, 1, 5, 5, 7, 48, 3});
    private GeneralName accessLocation;
    private ObjectIdentifier accessMethod;
    private int myhash;

    public ObjectIdentifier getAccessMethod() {
        return this.accessMethod;
    }

    public GeneralName getAccessLocation() {
        return this.accessLocation;
    }

    public void encode(DerOutputStream derOutputStream) {
        DerOutputStream derOutputStream2 = new DerOutputStream();
        derOutputStream2.putOID(this.accessMethod);
        this.accessLocation.encode(derOutputStream2);
        derOutputStream.write((byte) 48, derOutputStream2);
    }

    public int hashCode() {
        if (this.myhash == -1) {
            this.myhash = this.accessMethod.hashCode() + this.accessLocation.hashCode();
        }
        return this.myhash;
    }

    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof AccessDescription)) {
            return false;
        }
        AccessDescription accessDescription = (AccessDescription) obj;
        if (this == accessDescription) {
            return true;
        }
        if (!this.accessMethod.equals(accessDescription.getAccessMethod()) || !this.accessLocation.equals(accessDescription.getAccessLocation())) {
            return false;
        }
        return true;
    }

    public String toString() {
        String str;
        if (this.accessMethod.equals(Ad_CAISSUERS_Id)) {
            str = "caIssuers";
        } else if (this.accessMethod.equals(Ad_CAREPOSITORY_Id)) {
            str = "caRepository";
        } else if (this.accessMethod.equals(Ad_TIMESTAMPING_Id)) {
            str = "timeStamping";
        } else if (this.accessMethod.equals(Ad_OCSP_Id)) {
            str = "ocsp";
        } else {
            str = this.accessMethod.toString();
        }
        return "\n   accessMethod: " + str + "\n   accessLocation: " + this.accessLocation.toString() + "\n";
    }
}
