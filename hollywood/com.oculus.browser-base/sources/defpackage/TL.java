package defpackage;

/* renamed from: TL  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public enum TL {
    ACRA_CRASH_REPORT("acra-reports", 1048576, null, ".stacktrace", ".temp_stacktrace"),
    NATIVE_CRASH_REPORT("Crashpad", 8388608, XL0.MINIDUMP, ".dmp"),
    ANR_REPORT("traces", 524288, XL0.SIGQUIT, ".stacktrace", ".temp_stacktrace");
    

    /* renamed from: J  reason: collision with root package name */
    public final String f8951J;
    public final long K;
    public final XL0 L;
    public final String[] M;

    /* access modifiers changed from: public */
    TL(String str, long j, XL0 xl0, String... strArr) {
        this.f8951J = str;
        this.K = j;
        this.L = xl0;
        this.M = strArr;
    }
}
