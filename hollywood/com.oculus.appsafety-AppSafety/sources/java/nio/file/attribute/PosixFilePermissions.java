package java.nio.file.attribute;

import java.util.Collections;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.Set;

public final class PosixFilePermissions {
    private PosixFilePermissions() {
    }

    private static void writeBits(StringBuilder sb, boolean r, boolean w, boolean x) {
        if (r) {
            sb.append('r');
        } else {
            sb.append('-');
        }
        if (w) {
            sb.append('w');
        } else {
            sb.append('-');
        }
        if (x) {
            sb.append('x');
        } else {
            sb.append('-');
        }
    }

    public static String toString(Set<PosixFilePermission> perms) {
        StringBuilder sb = new StringBuilder(9);
        writeBits(sb, perms.contains(PosixFilePermission.OWNER_READ), perms.contains(PosixFilePermission.OWNER_WRITE), perms.contains(PosixFilePermission.OWNER_EXECUTE));
        writeBits(sb, perms.contains(PosixFilePermission.GROUP_READ), perms.contains(PosixFilePermission.GROUP_WRITE), perms.contains(PosixFilePermission.GROUP_EXECUTE));
        writeBits(sb, perms.contains(PosixFilePermission.OTHERS_READ), perms.contains(PosixFilePermission.OTHERS_WRITE), perms.contains(PosixFilePermission.OTHERS_EXECUTE));
        return sb.toString();
    }

    private static boolean isSet(char c, char setValue) {
        if (c == setValue) {
            return true;
        }
        if (c == '-') {
            return false;
        }
        throw new IllegalArgumentException("Invalid mode");
    }

    private static boolean isR(char c) {
        return isSet(c, 'r');
    }

    private static boolean isW(char c) {
        return isSet(c, 'w');
    }

    private static boolean isX(char c) {
        return isSet(c, 'x');
    }

    public static Set<PosixFilePermission> fromString(String perms) {
        if (perms.length() == 9) {
            Set<PosixFilePermission> result = EnumSet.noneOf(PosixFilePermission.class);
            if (isR(perms.charAt(0))) {
                result.add(PosixFilePermission.OWNER_READ);
            }
            if (isW(perms.charAt(1))) {
                result.add(PosixFilePermission.OWNER_WRITE);
            }
            if (isX(perms.charAt(2))) {
                result.add(PosixFilePermission.OWNER_EXECUTE);
            }
            if (isR(perms.charAt(3))) {
                result.add(PosixFilePermission.GROUP_READ);
            }
            if (isW(perms.charAt(4))) {
                result.add(PosixFilePermission.GROUP_WRITE);
            }
            if (isX(perms.charAt(5))) {
                result.add(PosixFilePermission.GROUP_EXECUTE);
            }
            if (isR(perms.charAt(6))) {
                result.add(PosixFilePermission.OTHERS_READ);
            }
            if (isW(perms.charAt(7))) {
                result.add(PosixFilePermission.OTHERS_WRITE);
            }
            if (isX(perms.charAt(8))) {
                result.add(PosixFilePermission.OTHERS_EXECUTE);
            }
            return result;
        }
        throw new IllegalArgumentException("Invalid mode");
    }

    public static FileAttribute<Set<PosixFilePermission>> asFileAttribute(Set<PosixFilePermission> perms) {
        final Set<PosixFilePermission> perms2 = new HashSet<>(perms);
        for (PosixFilePermission p : perms2) {
            if (p == null) {
                throw new NullPointerException();
            }
        }
        return new FileAttribute<Set<PosixFilePermission>>() {
            /* class java.nio.file.attribute.PosixFilePermissions.AnonymousClass1 */

            @Override // java.nio.file.attribute.FileAttribute
            public String name() {
                return "posix:permissions";
            }

            @Override // java.nio.file.attribute.FileAttribute
            public Set<PosixFilePermission> value() {
                return Collections.unmodifiableSet(Set.this);
            }
        };
    }
}
