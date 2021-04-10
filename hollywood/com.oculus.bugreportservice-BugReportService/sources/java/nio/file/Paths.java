package java.nio.file;

public final class Paths {
    public static Path get(String str, String... strArr) {
        return FileSystems.getDefault().getPath(str, strArr);
    }
}
