package java.security;

public final class AccessController {
    public static Object doPrivileged(PrivilegedAction privilegedAction) {
        return privilegedAction.run();
    }

    public static Object doPrivileged(PrivilegedAction privilegedAction, AccessControlContext accessControlContext) {
        return privilegedAction.run();
    }

    public static Object doPrivileged(PrivilegedExceptionAction privilegedExceptionAction) {
        try {
            return privilegedExceptionAction.run();
        } catch (RuntimeException e) {
            throw e;
        } catch (Exception e2) {
            throw new PrivilegedActionException(e2);
        }
    }

    public static AccessControlContext getContext() {
        return new AccessControlContext(null);
    }
}
