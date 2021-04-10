package java.net;

/* access modifiers changed from: package-private */
/* compiled from: URL */
public class Parts {
    String path;
    String query;
    String ref;

    Parts(String str, String str2) {
        String str3;
        int indexOf = str.indexOf(35);
        if (indexOf < 0) {
            str3 = null;
        } else {
            str3 = str.substring(indexOf + 1);
        }
        this.ref = str3;
        str = indexOf >= 0 ? str.substring(0, indexOf) : str;
        int lastIndexOf = str.lastIndexOf(63);
        if (lastIndexOf != -1) {
            this.query = str.substring(lastIndexOf + 1);
            this.path = str.substring(0, lastIndexOf);
        } else {
            this.path = str;
        }
        String str4 = this.path;
        if (str4 != null && str4.length() > 0 && this.path.charAt(0) != '/' && str2 != null && !str2.isEmpty()) {
            this.path = '/' + this.path;
        }
    }

    /* access modifiers changed from: package-private */
    public String getPath() {
        return this.path;
    }

    /* access modifiers changed from: package-private */
    public String getQuery() {
        return this.query;
    }

    /* access modifiers changed from: package-private */
    public String getRef() {
        return this.ref;
    }
}
