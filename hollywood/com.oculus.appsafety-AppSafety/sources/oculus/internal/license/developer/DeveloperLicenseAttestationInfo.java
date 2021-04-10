package oculus.internal.license.developer;

import java.io.Serializable;

public class DeveloperLicenseAttestationInfo implements Serializable {
    private static final long serialVersionUID = 431545415431L;
    public final long createdTimeSec;
    public final String deviceSerialNumber;
    public final String userId;
    public final int version;

    DeveloperLicenseAttestationInfo(long createdTimeSec2, String userId2, String deviceSerialNumber2, int version2) {
        this.createdTimeSec = createdTimeSec2;
        this.userId = userId2;
        this.deviceSerialNumber = deviceSerialNumber2;
        this.version = version2;
    }
}
