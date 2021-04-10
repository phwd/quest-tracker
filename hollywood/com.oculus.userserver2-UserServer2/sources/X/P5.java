package X;

import java.util.ArrayList;
import java.util.List;

public class P5 extends ThreadLocal<List<Object>> {
    /* Return type fixed from 'java.lang.Object' to match base method */
    @Override // java.lang.ThreadLocal
    public final List<Object> initialValue() {
        return new ArrayList();
    }
}
