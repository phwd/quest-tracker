package X;

public enum Gu {
    ERROR(0),
    LAUNCH(1),
    START(2),
    CHECKIN(3),
    DOWNLOAD_DATASET(4),
    DOWNLOAD_TASK(5),
    EXECUTE(6),
    REPORT(7),
    UPLOAD(8),
    FINISH(9),
    CANCEL(10),
    TERMINATE(11),
    KILL_PROCESS(12);
    
    public final int value;

    /* access modifiers changed from: public */
    Gu(int i) {
        this.value = i;
    }

    public static Gu fromInt(int i) {
        Gu[] values = values();
        for (Gu gu : values) {
            if (gu.value == i) {
                return gu;
            }
        }
        throw new IllegalArgumentException("Unsupported event!");
    }
}
