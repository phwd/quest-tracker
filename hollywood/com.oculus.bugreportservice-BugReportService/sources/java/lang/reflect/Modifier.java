package java.lang.reflect;

public class Modifier {
    public static int constructorModifiers() {
        return 7;
    }

    public static boolean isPrivate(int i) {
        return (i & 2) != 0;
    }

    public static boolean isProtected(int i) {
        return (i & 4) != 0;
    }

    public static boolean isPublic(int i) {
        return (i & 1) != 0;
    }

    public static boolean isStatic(int i) {
        return (i & 8) != 0;
    }

    public static boolean isVolatile(int i) {
        return (i & 64) != 0;
    }

    public static int methodModifiers() {
        return 3391;
    }

    public static String toString(int i) {
        StringBuilder sb = new StringBuilder();
        if ((i & 1) != 0) {
            sb.append("public ");
        }
        if ((i & 4) != 0) {
            sb.append("protected ");
        }
        if ((i & 2) != 0) {
            sb.append("private ");
        }
        if ((i & 1024) != 0) {
            sb.append("abstract ");
        }
        if ((i & 8) != 0) {
            sb.append("static ");
        }
        if ((i & 16) != 0) {
            sb.append("final ");
        }
        if ((i & 128) != 0) {
            sb.append("transient ");
        }
        if ((i & 64) != 0) {
            sb.append("volatile ");
        }
        if ((i & 32) != 0) {
            sb.append("synchronized ");
        }
        if ((i & 256) != 0) {
            sb.append("native ");
        }
        if ((i & 2048) != 0) {
            sb.append("strictfp ");
        }
        if ((i & 512) != 0) {
            sb.append("interface ");
        }
        int length = sb.length();
        return length > 0 ? sb.toString().substring(0, length - 1) : "";
    }
}
