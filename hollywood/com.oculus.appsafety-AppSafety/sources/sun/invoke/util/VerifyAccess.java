package sun.invoke.util;

import java.lang.reflect.Modifier;

public class VerifyAccess {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final boolean ALLOW_NESTMATE_ACCESS = false;
    private static final int ALL_ACCESS_MODES = 7;
    private static final int PACKAGE_ALLOWED = 8;
    private static final int PACKAGE_ONLY = 0;
    private static final int PROTECTED_OR_PACKAGE_ALLOWED = 12;

    private VerifyAccess() {
    }

    public static boolean isMemberAccessible(Class<?> refc, Class<?> defc, int mods, Class<?> lookupClass, int allowedModes) {
        if (allowedModes == 0 || !isClassAccessible(refc, lookupClass, allowedModes)) {
            return false;
        }
        if (defc == lookupClass && (allowedModes & 2) != 0) {
            return true;
        }
        int i = mods & 7;
        if (i != 0) {
            if (i == 1) {
                return true;
            }
            if (i == 2) {
                return false;
            }
            if (i != 4) {
                throw new IllegalArgumentException("bad modifiers: " + Modifier.toString(mods));
            } else if ((allowedModes & 12) != 0 && isSamePackage(defc, lookupClass)) {
                return true;
            } else {
                if ((allowedModes & 4) == 0) {
                    return false;
                }
                if (((mods & 8) == 0 || isRelatedClass(refc, lookupClass)) && (allowedModes & 4) != 0 && isSubClass(lookupClass, defc)) {
                    return true;
                }
                return false;
            }
        } else if ((allowedModes & 8) == 0 || !isSamePackage(defc, lookupClass)) {
            return false;
        } else {
            return true;
        }
    }

    static boolean isRelatedClass(Class<?> refc, Class<?> lookupClass) {
        return refc == lookupClass || isSubClass(refc, lookupClass) || isSubClass(lookupClass, refc);
    }

    static boolean isSubClass(Class<?> lookupClass, Class<?> defc) {
        return defc.isAssignableFrom(lookupClass) && !lookupClass.isInterface();
    }

    public static boolean isClassAccessible(Class<?> refc, Class<?> lookupClass, int allowedModes) {
        if (allowedModes == 0) {
            return false;
        }
        if (Modifier.isPublic(refc.getModifiers())) {
            return true;
        }
        if ((allowedModes & 8) == 0 || !isSamePackage(lookupClass, refc)) {
            return false;
        }
        return true;
    }

    public static boolean isSamePackage(Class<?> class1, Class<?> class2) {
        if (class1.isArray() || class2.isArray()) {
            throw new IllegalArgumentException();
        } else if (class1 == class2) {
            return true;
        } else {
            if (class1.getClassLoader() != class2.getClassLoader()) {
                return false;
            }
            String name1 = class1.getName();
            String name2 = class2.getName();
            int dot = name1.lastIndexOf(46);
            if (dot != name2.lastIndexOf(46)) {
                return false;
            }
            for (int i = 0; i < dot; i++) {
                if (name1.charAt(i) != name2.charAt(i)) {
                    return false;
                }
            }
            return true;
        }
    }

    public static boolean isSamePackageMember(Class<?> class1, Class<?> class2) {
        if (class1 == class2) {
            return true;
        }
        if (isSamePackage(class1, class2) && getOutermostEnclosingClass(class1) == getOutermostEnclosingClass(class2)) {
            return true;
        }
        return false;
    }

    private static Class<?> getOutermostEnclosingClass(Class<?> c) {
        Class<?> pkgmem = c;
        Class<?> enc = c;
        while (true) {
            Class<?> enclosingClass = enc.getEnclosingClass();
            enc = enclosingClass;
            if (enclosingClass == null) {
                return pkgmem;
            }
            pkgmem = enc;
        }
    }
}
