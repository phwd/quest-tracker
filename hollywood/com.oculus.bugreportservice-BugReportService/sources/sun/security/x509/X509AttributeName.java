package sun.security.x509;

public class X509AttributeName {
    private String prefix = null;
    private String suffix = null;

    public X509AttributeName(String str) {
        int indexOf = str.indexOf(46);
        if (indexOf < 0) {
            this.prefix = str;
            return;
        }
        this.prefix = str.substring(0, indexOf);
        this.suffix = str.substring(indexOf + 1);
    }

    public String getPrefix() {
        return this.prefix;
    }

    public String getSuffix() {
        return this.suffix;
    }
}
