package X;

public class H2 extends ThreadLocal<byte[]> {
    /* Return type fixed from 'java.lang.Object' to match base method */
    @Override // java.lang.ThreadLocal
    public final byte[] initialValue() {
        return new byte[2048];
    }
}
