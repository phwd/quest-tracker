package dalvik.system;

public final class ClassExt {
    private Object[] obsoleteDexCaches;
    private Object obsoleteMethods;
    private Object originalDexFile;
    private int preRedefineClassDefIndex;
    private long preRedefineDexFilePtr;
    private Object verifyError;

    private ClassExt() {
    }
}
