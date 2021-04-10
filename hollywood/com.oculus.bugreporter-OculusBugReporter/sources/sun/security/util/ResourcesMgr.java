package sun.security.util;

import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.ResourceBundle;

public class ResourcesMgr {
    private static ResourceBundle altBundle;
    private static ResourceBundle bundle;

    public static String getString(String s) {
        if (bundle == null) {
            bundle = (ResourceBundle) AccessController.doPrivileged(new PrivilegedAction<ResourceBundle>() {
                /* class sun.security.util.ResourcesMgr.AnonymousClass1 */

                @Override // java.security.PrivilegedAction
                public ResourceBundle run() {
                    return ResourceBundle.getBundle(Resources.class.getName());
                }
            });
        }
        return bundle.getString(s);
    }

    public static String getString(String s, final String altBundleName) {
        if (altBundle == null) {
            altBundle = (ResourceBundle) AccessController.doPrivileged(new PrivilegedAction<ResourceBundle>() {
                /* class sun.security.util.ResourcesMgr.AnonymousClass2 */

                @Override // java.security.PrivilegedAction
                public ResourceBundle run() {
                    return ResourceBundle.getBundle(String.this);
                }
            });
        }
        return altBundle.getString(s);
    }
}
