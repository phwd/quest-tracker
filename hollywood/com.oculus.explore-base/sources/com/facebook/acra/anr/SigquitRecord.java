package com.facebook.acra.anr;

import com.oculus.common.build.BuildConfig;
import java.util.ArrayList;
import java.util.Iterator;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class SigquitRecord {
    private static final ArrayList<SigquitRecord> sRecords = new ArrayList<>();
    public final long callbackUptimeMs;
    public final long sigquitUptimeMs;

    public static synchronized String getRecordsJson(Long maxCallbackUptimeMs) {
        String resultsString;
        synchronized (SigquitRecord.class) {
            try {
                JSONArray results = new JSONArray();
                Iterator<SigquitRecord> it = sRecords.iterator();
                while (it.hasNext()) {
                    SigquitRecord record = it.next();
                    if (maxCallbackUptimeMs != null && record.callbackUptimeMs > maxCallbackUptimeMs.longValue()) {
                        break;
                    }
                    JSONObject jsonRecord = new JSONObject();
                    jsonRecord.put("callback_uptime_ms", record.callbackUptimeMs);
                    jsonRecord.put("sigquit_uptime_ms", record.sigquitUptimeMs);
                    results.put(jsonRecord);
                }
                resultsString = results.toString();
                if (resultsString == null) {
                    resultsString = BuildConfig.PROVIDER_SUFFIX;
                }
            } catch (JSONException e) {
                resultsString = "\"json error\"";
            }
        }
        return resultsString;
    }
}
