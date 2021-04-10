package X;

public final class MU implements Runnable {
    public static final String __redex_internal_original_name = "com.facebook.voiceplatform.tts.AudioTrackPlayer$1";
    public final /* synthetic */ C0239Mb A00;
    public final /* synthetic */ Exception A01;

    public MU(C0239Mb mb, Exception exc) {
        this.A00 = mb;
        this.A01 = exc;
    }

    public final void run() {
        this.A00.A04.onError(this.A01);
    }
}
