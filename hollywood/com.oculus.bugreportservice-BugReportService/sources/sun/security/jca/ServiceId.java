package sun.security.jca;

public final class ServiceId {
    public final String algorithm;
    public final String type;

    public ServiceId(String str, String str2) {
        this.type = str;
        this.algorithm = str2;
    }
}
