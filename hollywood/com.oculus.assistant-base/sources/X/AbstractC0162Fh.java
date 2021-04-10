package X;

/* renamed from: X.Fh  reason: case insensitive filesystem */
public interface AbstractC0162Fh {
    AbstractC0163Fj getLatestHandle();

    AbstractC0168Ft getNewOverridesTable();

    AbstractC0168Ft getNewOverridesTableIfExists();

    boolean isFetchNeeded();

    boolean isValid();

    void logExposure(String str, String str2, String str3);

    void logShadowResult(String str, String str2, String str3, String str4, String str5, String str6);

    String syncFetchReason();

    boolean tryUpdateConfigsSynchronously(int i);
}
