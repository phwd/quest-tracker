package X;

import com.facebook.common.internal.DoNotStrip;
import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
public interface Kt {
    @DoNotStrip
    long now();
}
