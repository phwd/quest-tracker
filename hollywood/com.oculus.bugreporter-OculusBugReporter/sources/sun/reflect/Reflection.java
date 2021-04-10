package sun.reflect;

import dalvik.system.VMStack;
import java.lang.reflect.Modifier;

public class Reflection {
    public static Class<?> getCallerClass() {
        return VMStack.getStackClass2();
    }

    public static void ensureMemberAccess(Class<?> currentClass, Class<?> memberClass, Object target, int modifiers) throws IllegalAccessException {
        if (currentClass == null || memberClass == null) {
            throw new InternalError();
        } else if (!verifyMemberAccess(currentClass, memberClass, target, modifiers)) {
            throw new IllegalAccessException("Class " + currentClass.getName() + " can not access a member of class " + memberClass.getName() + " with modifiers \"" + Modifier.toString(modifiers) + "\"");
        }
    }

    public static boolean verifyMemberAccess(Class<?> currentClass, Class<?> memberClass, Object target, int modifiers) {
        boolean gotIsSameClassPackage = false;
        boolean isSameClassPackage = false;
        if (currentClass == memberClass) {
            return true;
        }
        if (!Modifier.isPublic(memberClass.getAccessFlags())) {
            isSameClassPackage = isSameClassPackage(currentClass, memberClass);
            gotIsSameClassPackage = true;
            if (!isSameClassPackage) {
                return false;
            }
        }
        if (Modifier.isPublic(modifiers)) {
            return true;
        }
        boolean successSoFar = false;
        if (Modifier.isProtected(modifiers) && isSubclassOf(currentClass, memberClass)) {
            successSoFar = true;
        }
        if (!successSoFar && !Modifier.isPrivate(modifiers)) {
            if (!gotIsSameClassPackage) {
                isSameClassPackage = isSameClassPackage(currentClass, memberClass);
                gotIsSameClassPackage = true;
            }
            if (isSameClassPackage) {
                successSoFar = true;
            }
        }
        if (!successSoFar) {
            return false;
        }
        if (Modifier.isProtected(modifiers)) {
            Class<?> targetClass = target == null ? memberClass : target.getClass();
            if (targetClass != currentClass) {
                if (!gotIsSameClassPackage) {
                    isSameClassPackage = isSameClassPackage(currentClass, memberClass);
                }
                if (isSameClassPackage || isSubclassOf(targetClass, currentClass)) {
                    return true;
                }
                return false;
            }
        }
        return true;
    }

    private static boolean isSameClassPackage(Class<?> c1, Class<?> c2) {
        return isSameClassPackage(c1.getClassLoader(), c1.getName(), c2.getClassLoader(), c2.getName());
    }

    private static boolean isSameClassPackage(ClassLoader loader1, String name1, ClassLoader loader2, String name2) {
        int idx1;
        int idx2;
        if (loader1 != loader2) {
            return false;
        }
        int lastDot1 = name1.lastIndexOf(46);
        int lastDot2 = name2.lastIndexOf(46);
        if (lastDot1 != -1 && lastDot2 != -1) {
            int idx12 = 0;
            int idx22 = 0;
            if (name1.charAt(0) == '[') {
                do {
                    idx12++;
                } while (name1.charAt(idx12) == '[');
                if (name1.charAt(idx12) == 'L') {
                    idx1 = idx12;
                } else {
                    throw new InternalError("Illegal class name " + name1);
                }
            } else {
                idx1 = 0;
            }
            if (name2.charAt(0) == 91) {
                do {
                    idx22++;
                } while (name2.charAt(idx22) == '[');
                if (name2.charAt(idx22) == 'L') {
                    idx2 = idx22;
                } else {
                    throw new InternalError("Illegal class name " + name2);
                }
            } else {
                idx2 = 0;
            }
            int length1 = lastDot1 - idx1;
            if (length1 != lastDot2 - idx2) {
                return false;
            }
            return name1.regionMatches(false, idx1, name2, idx2, length1);
        } else if (lastDot1 == lastDot2) {
            return true;
        } else {
            return false;
        }
    }

    static boolean isSubclassOf(Class<?> queryClass, Class<?> ofClass) {
        while (queryClass != null) {
            if (queryClass == ofClass) {
                return true;
            }
            queryClass = queryClass.getSuperclass();
        }
        return false;
    }
}
