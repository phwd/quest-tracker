package sun.misc;

public class SharedSecrets {
    private static JavaIOFileDescriptorAccess javaIOFileDescriptorAccess;

    public static void setJavaIOFileDescriptorAccess(JavaIOFileDescriptorAccess javaIOFileDescriptorAccess2) {
        javaIOFileDescriptorAccess = javaIOFileDescriptorAccess2;
    }
}
