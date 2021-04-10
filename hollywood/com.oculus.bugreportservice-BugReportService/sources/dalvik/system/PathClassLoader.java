package dalvik.system;

public class PathClassLoader extends BaseDexClassLoader {
    public PathClassLoader(String str, String str2, ClassLoader classLoader) {
        super(str, null, str2, classLoader);
    }
}
