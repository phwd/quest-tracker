package com.facebook.acra.anr;

import androidx.annotation.GuardedBy;
import com.facebook.debug.log.BLog;
import com.facebook.infer.annotation.Nullsafe;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import javax.annotation.Nullable;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@Nullsafe(Nullsafe.Mode.LOCAL)
public class SigquitRecord {
    private static final long ERROR_READING_TIME_INFO = -1;
    private static final String TAG = "SigquitRecord";
    @GuardedBy("SigquitRecord.class")
    private static final ArrayList<SigquitRecord> sRecords = new ArrayList<>();
    public final long callbackUptimeMs;
    public final long sigquitUptimeMs;

    public static void updateRecords(long sigquitCallbackUptimeMs, @Nullable String sigquitTimeFilepath) {
        if (sigquitTimeFilepath != null) {
            synchronized (SigquitRecord.class) {
                if (sRecords.size() <= 0 || sRecords.get(sRecords.size() - 1).callbackUptimeMs != sigquitCallbackUptimeMs) {
                    sRecords.add(new SigquitRecord(sigquitCallbackUptimeMs, readSigquitUptimeMs(sigquitTimeFilepath)));
                }
            }
        }
    }

    public static long readSigquitUptimeMs(String sigquitTimeFilepath) {
        try {
            FileInputStream input = new FileInputStream(sigquitTimeFilepath);
            try {
                byte[] buffer = new byte[8];
                if (input.read(buffer) != 8) {
                    BLog.w(TAG, "Corrupted file %s", sigquitTimeFilepath);
                    input.close();
                    return -1;
                }
                long convertRawBytesToLong = convertRawBytesToLong(buffer) / 1000000;
                input.close();
                return convertRawBytesToLong;
            } catch (Throwable th) {
                th.addSuppressed(th);
            }
            throw th;
        } catch (IOException e) {
            BLog.w(TAG, "Could not read from file %s", sigquitTimeFilepath, e);
            return -1;
        }
    }

    public static synchronized String getRecordsJson(@Nullable Long maxCallbackUptimeMs) {
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
                    resultsString = "";
                }
            } catch (JSONException e) {
                resultsString = "\"json error\"";
            }
        }
        return resultsString;
    }

    private static long convertRawBytesToLong(byte[] buffer) {
        long value = 0;
        int shift = 0;
        for (int i = 0; i < 8; i++) {
            value += (((long) buffer[i]) & 255) << shift;
            shift += 8;
        }
        return value;
    }

    private SigquitRecord(long callbackUptimeMs2, long sigquitUptimeMs2) {
        this.callbackUptimeMs = callbackUptimeMs2;
        this.sigquitUptimeMs = sigquitUptimeMs2;
    }
}
