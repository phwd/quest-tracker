package X;

/* renamed from: X.Bu  reason: case insensitive filesystem */
public abstract class AbstractRunnableC0124Bu implements Runnable {
    public static final String __redex_internal_original_name = "com.facebook.common.executors.NamedRunnable";
    public final String category;
    public final String name;

    public Object getInnerRunnable() {
        return this;
    }

    public String getRunnableName() {
        return AnonymousClass08.A05(this.category, "/", this.name);
    }

    public String toString() {
        return getRunnableName();
    }

    public AbstractRunnableC0124Bu(Class cls, String str) {
        this(cls.getSimpleName(), str);
    }

    public AbstractRunnableC0124Bu(String str, String str2) {
        this.category = str;
        this.name = str2;
    }
}
