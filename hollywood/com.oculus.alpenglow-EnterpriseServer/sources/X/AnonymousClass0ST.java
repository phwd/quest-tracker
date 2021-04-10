package X;

/* renamed from: X.0ST  reason: invalid class name */
public interface AnonymousClass0ST {
    void deleteOldUserData(int i);

    AnonymousClass0SV getLatestHandle();

    AnonymousClass0Sr getNewOverridesTableIfExists();

    boolean isFetchNeeded();

    boolean isValid();

    void logExposure(String str, String str2, String str3);

    void logShadowResult(String str, String str2, String str3, String str4, String str5, String str6);

    String syncFetchReason();

    boolean tryUpdateConfigsSynchronously(int i);

    boolean updateConfigs();
}
