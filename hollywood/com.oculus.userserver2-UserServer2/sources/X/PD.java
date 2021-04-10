package X;

public class PD extends ThreadLocal<PE> {
    /* Return type fixed from 'java.lang.Object' to match base method */
    @Override // java.lang.ThreadLocal
    public final PE initialValue() {
        return new PE();
    }
}
