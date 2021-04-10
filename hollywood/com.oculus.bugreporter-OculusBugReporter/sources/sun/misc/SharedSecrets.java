package sun.misc;

public class SharedSecrets {
    private static JavaIOFileDescriptorAccess javaIOFileDescriptorAccess;

    public static void setJavaIOFileDescriptorAccess(JavaIOFileDescriptorAccess jiofda) {
        javaIOFileDescriptorAccess = jiofda;
    }

    public static JavaIOFileDescriptorAccess getJavaIOFileDescriptorAccess() {
        if (javaIOFileDescriptorAccess == null) {
            try {
                Class.forName("java.io.FileDescriptor");
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
        return javaIOFileDescriptorAccess;
    }
}
