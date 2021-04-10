package X;

import java.lang.reflect.Method;
import javax.annotation.Nullable;

public class Hv {
    @Nullable
    public final Method A00;
    public final Method A01;
    public final Method A02;
    public final Method A03;
    public final Method A04;

    public Hv(@Nullable Method method, Method method2, Method method3, Method method4, Method method5) {
        this.A00 = method;
        this.A02 = method2;
        this.A01 = method3;
        this.A03 = method4;
        this.A04 = method5;
    }
}
