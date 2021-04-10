package dalvik.system;

import java.nio.ByteBuffer;

public final class InMemoryDexClassLoader extends BaseDexClassLoader {
    public InMemoryDexClassLoader(ByteBuffer[] dexBuffers, String librarySearchPath, ClassLoader parent) {
        super(dexBuffers, librarySearchPath, parent);
    }

    public InMemoryDexClassLoader(ByteBuffer[] dexBuffers, ClassLoader parent) {
        this(dexBuffers, null, parent);
    }

    public InMemoryDexClassLoader(ByteBuffer dexBuffer, ClassLoader parent) {
        this(new ByteBuffer[]{dexBuffer}, parent);
    }
}
