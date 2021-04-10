package X;

public final class MJ extends RuntimeException {
    public MJ() {
    }

    public MJ(String str) {
        super("A local injection was attempted before the constructor completed or before injectMe was called.");
    }
}
