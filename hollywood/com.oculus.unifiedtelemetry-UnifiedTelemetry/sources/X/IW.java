package X;

import com.facebook.infer.annotation.Nullsafe;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
@Nullsafe(Nullsafe.Mode.LOCAL)
public final class IW<T> {
    public int A00;
    public ArrayList<T> A01;
    public List<T> A02;

    public final synchronized void A00() {
        this.A00--;
    }

    public IW() {
        ArrayList<T> arrayList = new ArrayList<>();
        this.A01 = arrayList;
        this.A02 = Collections.unmodifiableList(arrayList);
    }
}
