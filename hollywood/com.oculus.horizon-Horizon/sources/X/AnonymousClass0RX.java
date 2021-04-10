package X;

import com.facebook.mobileconfig.MobileConfigCxxChangeListener;
import java.util.Map;

/* renamed from: X.0RX  reason: invalid class name */
public interface AnonymousClass0RX {
    void deleteOldUserData(int i);

    AnonymousClass0RZ getLatestHandle();

    AnonymousClass0Ri getNewOverridesTableIfExists();

    boolean isConsistencyLoggingNeeded(AnonymousClass0RO v);

    boolean isFetchNeeded();

    boolean isValid();

    void logConfigs(String str, AnonymousClass0RO v, Map<String, String> map);

    void logExposure(String str, String str2, String str3);

    void logShadowResult(String str, String str2, String str3, String str4, String str5, String str6);

    boolean registerConfigChangeListener(MobileConfigCxxChangeListener mobileConfigCxxChangeListener);

    boolean saveCurrentParamsMapToDisk();

    String syncFetchReason();

    boolean tryUpdateConfigsSynchronously(int i);

    boolean updateConfigs();

    boolean updateConfigsSynchronouslyWithDefaultUpdater(int i);
}
