package X;

public class MD extends ThreadLocal<ME> {
    /* Return type fixed from 'java.lang.Object' to match base method */
    @Override // java.lang.ThreadLocal
    public final ME initialValue() {
        return new ME();
    }
}
