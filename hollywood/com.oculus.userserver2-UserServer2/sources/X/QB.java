package X;

public class QB extends ThreadLocal<QA> {
    /* Return type fixed from 'java.lang.Object' to match base method */
    @Override // java.lang.ThreadLocal
    public final QA initialValue() {
        return new QA();
    }
}
