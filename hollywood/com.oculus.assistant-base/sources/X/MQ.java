package X;

public abstract class MQ implements Runnable {
    public static final String __redex_internal_original_name = "com.facebook.voiceplatform.common.NamedRunnable";
    public final String mName;

    public MQ(String str) {
        this.mName = str;
    }

    public String getName() {
        return this.mName;
    }
}
