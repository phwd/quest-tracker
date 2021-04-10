package java.io;

class DefaultFileSystem {
    public static FileSystem getFileSystem() {
        return new UnixFileSystem();
    }
}
