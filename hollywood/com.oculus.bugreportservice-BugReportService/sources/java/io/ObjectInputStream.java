package java.io;

import java.util.HashMap;

public class ObjectInputStream extends InputStream implements ObjectInput, ObjectStreamConstants {
    private static final HashMap primClasses = new HashMap(8, 1.0f);
    private static final Object unsharedMarker = new Object();

    public static abstract class GetField {
    }

    public void defaultReadObject() {
        throw null;
    }

    public GetField readFields() {
        throw null;
    }

    public int readInt() {
        throw null;
    }

    public long readLong() {
        throw null;
    }

    public final Object readObject() {
        throw null;
    }

    public String readUTF() {
        throw null;
    }

    static {
        primClasses.put("boolean", Boolean.TYPE);
        primClasses.put("byte", Byte.TYPE);
        primClasses.put("char", Character.TYPE);
        primClasses.put("short", Short.TYPE);
        primClasses.put("int", Integer.TYPE);
        primClasses.put("long", Long.TYPE);
        primClasses.put("float", Float.TYPE);
        primClasses.put("double", Double.TYPE);
        primClasses.put("void", Void.TYPE);
    }
}
