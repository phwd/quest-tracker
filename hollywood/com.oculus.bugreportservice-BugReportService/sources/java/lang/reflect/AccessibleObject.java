package java.lang.reflect;

public class AccessibleObject implements AnnotatedElement {
    boolean override;

    public void setAccessible(boolean z) {
        setAccessible0(this, z);
    }

    private static void setAccessible0(AccessibleObject accessibleObject, boolean z) {
        if ((accessibleObject instanceof Constructor) && z) {
            Constructor constructor = (Constructor) accessibleObject;
            Class declaringClass = constructor.getDeclaringClass();
            if (constructor.getDeclaringClass() == Class.class) {
                throw new SecurityException("Can not make a java.lang.Class constructor accessible");
            } else if (declaringClass == Method.class) {
                throw new SecurityException("Can not make a java.lang.reflect.Method constructor accessible");
            } else if (declaringClass == Field.class) {
                throw new SecurityException("Can not make a java.lang.reflect.Field constructor accessible");
            }
        }
        accessibleObject.override = z;
    }

    protected AccessibleObject() {
    }
}
