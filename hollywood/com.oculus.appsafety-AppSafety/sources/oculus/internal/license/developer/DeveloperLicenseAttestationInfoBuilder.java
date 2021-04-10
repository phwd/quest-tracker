package oculus.internal.license.developer;

public class DeveloperLicenseAttestationInfoBuilder {
    private static final long TIME_UPPERBOUND = 68719476735L;
    private static final int VERSION = 1;
    private long createdTimeSec;
    private String deviceSerial;
    private String userId;

    public DeveloperLicenseAttestationInfoBuilder setCreatedTimeSec(long createdTimeSec2) {
        this.createdTimeSec = createdTimeSec2;
        return this;
    }

    public DeveloperLicenseAttestationInfoBuilder setUserId(String userId2) {
        this.userId = userId2;
        return this;
    }

    public DeveloperLicenseAttestationInfoBuilder setDeviceSerial(String deviceSerial2) {
        this.deviceSerial = deviceSerial2;
        return this;
    }

    public DeveloperLicenseAttestationInfo build() throws IllegalArgumentException {
        long j = this.createdTimeSec;
        if (j <= 0) {
            throw new IllegalArgumentException("invalid time.");
        } else if (j <= TIME_UPPERBOUND) {
            String str = this.userId;
            if (str != null) {
                String str2 = this.deviceSerial;
                if (str2 != null) {
                    return new DeveloperLicenseAttestationInfo(j, str, str2, 1);
                }
                throw new IllegalArgumentException("deviceSerial is required");
            }
            throw new IllegalArgumentException("user id is required");
        } else {
            throw new IllegalArgumentException("Did you set time in milliseconds?");
        }
    }
}
