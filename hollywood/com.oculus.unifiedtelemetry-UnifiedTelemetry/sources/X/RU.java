package X;

public interface RU {
    void deleteOldUserData(int i);

    RW getLatestHandle();

    Re getNewOverridesTableIfExists();

    boolean isFetchNeeded();

    boolean isValid();

    void logExposure(String str, String str2, String str3);

    void logShadowResult(String str, String str2, String str3, String str4, String str5, String str6);

    String syncFetchReason();

    boolean tryUpdateConfigsSynchronously(int i);

    boolean updateConfigs();
}
