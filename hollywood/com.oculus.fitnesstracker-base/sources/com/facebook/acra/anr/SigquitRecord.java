package com.facebook.acra.anr;

import com.facebook.debug.log.BLog;
import com.facebook.infer.annotation.Nullsafe;
import com.oculus.common.build.BuildConfig;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@Nullsafe(Nullsafe.Mode.LOCAL)
public class SigquitRecord {
    private static final ArrayList<SigquitRecord> sRecords = new ArrayList<>();
    public final long callbackUptimeMs;
    public final long sigquitUptimeMs;

    public static void updateRecords(long j, String str) {
        if (str != null) {
            synchronized (SigquitRecord.class) {
                if (sRecords.size() > 0) {
                    ArrayList<SigquitRecord> arrayList = sRecords;
                    if (arrayList.get(arrayList.size() - 1).callbackUptimeMs == j) {
                        return;
                    }
                }
                sRecords.add(new SigquitRecord(j, readSigquitUptimeMs(str)));
            }
        }
    }

    private static long readSigquitUptimeMs(String str) {
        try {
            FileInputStream fileInputStream = new FileInputStream(str);
            try {
                byte[] bArr = new byte[8];
                if (fileInputStream.read(bArr) != 8) {
                    BLog.w("SigquitRecord", "Corrupted file %s", str);
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
            BLog.w("SigquitRecord", "Could not read from file %s", str, e);
            return -1;
        }
    }

    public static synchronized String getRecordsJson(Long l) {
        synchronized (SigquitRecord.class) {
            try {
                JSONArray jSONArray = new JSONArray();
                Iterator<SigquitRecord> it = sRecords.iterator();
                while (it.hasNext()) {
                    SigquitRecord next = it.next();
                    if (l != null && next.callbackUptimeMs > l.longValue()) {
                        break;
                    }
                    JSONObject jSONObject = new JSONObject();
                    jSONObject.put("callback_uptime_ms", next.callbackUptimeMs);
                    jSONObject.put("sigquit_uptime_ms", next.sigquitUptimeMs);
                    jSONArray.put(jSONObject);
                }
                String jSONArray2 = jSONArray.toString();
                if (jSONArray2 == null) {
                    return BuildConfig.PROVIDER_SUFFIX;
                }
                return jSONArray2;
            } catch (JSONException unused) {
                return "\"json error\"";
            }
        }
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
