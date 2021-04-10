package com.facebook.acra.anr;

import X.Mu;
import androidx.annotation.GuardedBy;
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
    public static final long ERROR_READING_TIME_INFO = -1;
    public static final String TAG = "SigquitRecord";
    @GuardedBy("SigquitRecord.class")
    public static final ArrayList<SigquitRecord> sRecords = new ArrayList<>();
    public final long callbackUptimeMs;
    public final long sigquitUptimeMs;

    public static long convertRawBytesToLong(byte[] bArr) {
        long j = 0;
        int i = 0;
        for (int i2 = 0; i2 < 8; i2++) {
            j += (((long) bArr[i2]) & 255) << i;
            i += 8;
        }
        return j;
    }

    public static synchronized String getRecordsJson(@Nullable Long l) {
        String str;
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
                str = jSONArray.toString();
                if (str == null) {
                    str = "";
                }
            } catch (JSONException unused) {
                str = "\"json error\"";
            }
        }
        return str;
    }

    public static long readSigquitUptimeMs(String str) {
        try {
            FileInputStream fileInputStream = new FileInputStream(str);
            try {
                byte[] bArr = new byte[8];
                if (fileInputStream.read(bArr) != 8) {
                    Mu.A06(TAG, "Corrupted file %s", str);
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
            Mu.A06(TAG, "Could not read from file %s", str, e);
            return -1;
        }
    }

    public static void updateRecords(long j, @Nullable String str) {
        if (str != null) {
            synchronized (SigquitRecord.class) {
                ArrayList<SigquitRecord> arrayList = sRecords;
                if (arrayList.size() <= 0 || arrayList.get(arrayList.size() - 1).callbackUptimeMs != j) {
                    arrayList.add(new SigquitRecord(j, readSigquitUptimeMs(str)));
                }
            }
        }
    }

    public SigquitRecord(long j, long j2) {
        this.callbackUptimeMs = j;
        this.sigquitUptimeMs = j2;
    }
}
