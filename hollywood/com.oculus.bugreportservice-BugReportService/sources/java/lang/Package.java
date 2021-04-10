package java.lang;

import dalvik.system.VMRuntime;
import java.lang.reflect.AnnotatedElement;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class Package implements AnnotatedElement {
    private static Map mans = new HashMap(10);
    private static Map pkgs = new HashMap(31);
    private static Map urls = new HashMap(10);
    private final String implTitle;
    private final String implVendor;
    private final String implVersion;
    private final transient ClassLoader loader;
    private final String pkgName;
    private final URL sealBase;
    private final String specTitle;
    private final String specVendor;
    private final String specVersion;

    public String getName() {
        return this.pkgName;
    }

    public int hashCode() {
        return this.pkgName.hashCode();
    }

    public String toString() {
        String str;
        int targetSdkVersion = VMRuntime.getRuntime().getTargetSdkVersion();
        if (targetSdkVersion <= 0 || targetSdkVersion > 24) {
            String str2 = this.specTitle;
            String str3 = this.specVersion;
            String str4 = "";
            if (str2 == null || str2.length() <= 0) {
                str = str4;
            } else {
                str = ", " + str2;
            }
            if (str3 != null && str3.length() > 0) {
                str4 = ", version " + str3;
            }
            return "package " + this.pkgName + str + str4;
        }
        return "package " + this.pkgName;
    }

    Package(String str, String str2, String str3, String str4, String str5, String str6, String str7, URL url, ClassLoader classLoader) {
        this.pkgName = str;
        this.implTitle = str5;
        this.implVersion = str6;
        this.implVendor = str7;
        this.specTitle = str2;
        this.specVersion = str3;
        this.specVendor = str4;
        this.sealBase = url;
        this.loader = classLoader;
    }
}
