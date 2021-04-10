package libcore.icu;

import java.nio.charset.Charset;
import libcore.util.NativeAllocationRegistry;

public final class NativeConverter {
    private static final NativeAllocationRegistry registry = new NativeAllocationRegistry(NativeConverter.class.getClassLoader(), getNativeFinalizer(), getNativeSize());

    public static native Charset charsetForName(String str);

    public static native long getNativeFinalizer();

    public static native long getNativeSize();
}
