package java.security.cert;

public abstract class CRL {
    private String type;

    protected CRL(String str) {
        this.type = str;
    }
}
