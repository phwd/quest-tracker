package X;

import java.util.concurrent.ThreadFactory;

public final class AV implements ThreadFactory {
    public final int A00;
    public final String A01;
    public final AbstractC0463a6 A02;

    public final Thread newThread(Runnable runnable) {
        int i = this.A00;
        if (i != 0) {
            runnable = new AU(runnable, i);
        }
        String str = this.A01;
        if (str == null) {
            str = (String) this.A02.get();
        }
        return new Thread(runnable, str);
    }

    public AV(String str, int i) {
        this.A01 = str;
        this.A02 = null;
        this.A00 = i;
    }

    public AV(AbstractC0463a6 a6Var, int i) {
        this.A01 = null;
        this.A02 = a6Var;
        this.A00 = i;
    }
}
