package X;

public class H3 extends ThreadLocal<char[]> {
    /* Return type fixed from 'java.lang.Object' to match base method */
    @Override // java.lang.ThreadLocal
    public final char[] initialValue() {
        return new char[1024];
    }
}
