package dalvik.system;

import java.io.File;

public class DexClassLoader extends BaseDexClassLoader {
    public DexClassLoader(String dexPath, String optimizedDirectory, String librarySearchPath, ClassLoader parent) {
        super(dexPath, (File) null, librarySearchPath, parent);
    }
}
