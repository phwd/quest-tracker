package libraries.marauder.analytics.request.protocol;

import java.util.Map;

public interface AnalyticsService {
    public static final String LOGGING_ENDPOINT = "https://graph.facebook.com/logging_client_events";

    int log(String str, Map<String, String> map);
}
