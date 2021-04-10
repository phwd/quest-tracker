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

    public static void updateRecords(long j, @Nullable String str) {
        if (str != null) {
            synchronized (SigquitRecord.class) {
                if (sRecords.size() <= 0 || sRecords.get(sRecords.size() - 1).callbackUptimeMs != j) {
                    sRecords.add(new SigquitRecord(j, readSigquitUptimeMs(str)));
                }
            }
        }
    }

    public static long readSigquitUptimeMs(String str) {
        try {
            FileInputStream fileInputStream = new FileInputStream(str);
            try {
                byte[] bArr = new byte[8];
                if (fileInputStream.read(bArr) != 8) {
                    BLog.w(TAG, "Corrupted file %s", str);
                    fileInputStream.close();
                    return -1;
                }
                long convertRawBytesToLong = convertRawBytesToLong(bArr) / 1000000;
                fileInputStream.close();
                return convertRawBytesToLong;
            } catch (Throwable unused) {
            }
            throw th;
        } catch (IOException e) {
            BLog.w(TAG, "Could not read from file %s", str, e);
            return -1;
        }
    }

    public static synchronized String getRecordsJson(@Nullable Long l) {
        String jSONArray;
        synchronized (SigquitRecord.class) {
            try {
                JSONArray jSONArray2 = new JSONArray();
                Iterator<SigquitRecord> it = sRecords.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    }
                    SigquitRecord next = it.next();
                    if (l != null && next.callbackUptimeMs > l.longValue()) {
                        break;
                    }
                    JSONObject jSONObject = new JSONObject();
                    jSONObject.put("callback_uptime_ms", next.callbackUptimeMs);
                    jSONObject.put("sigquit_uptime_ms", next.sigquitUptimeMs);
                    jSONArray2.put(jSONObject);
                }
                jSONArray = jSONArray2.toString();
                if (jSONArray == null) {
                    jSONArray = "";
                }
            } catch (JSONException unused) {
                return "\"json error\"";
            }
        }
        return jSONArray;
    }

    private static long convertRawBytesToLong(byte[] bArr) {
        long j = 0;
        int i = 0;
        for (int i2 = 0; i2 < 8; i2++) {
            j += (((long) bArr[i2]) & 255) << i;
            i += 8;
        }
        return j;
    }

    private SigquitRecord(long j, long j2) {
        this.callbackUptimeMs = j;
        this.sigquitUptimeMs = j2;
    }
}
