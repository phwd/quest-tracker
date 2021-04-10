package com.facebook.acra.anr;

import X.C0139Dd;
import com.facebook.assistant.oacr.OacrConstants;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class SigquitRecord {
    public static final long ERROR_READING_TIME_INFO = -1;
    public static final String TAG = "SigquitRecord";
    public static final ArrayList sRecords = new ArrayList();
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

    public static synchronized String getRecordsJson(Long l) {
        String str;
        synchronized (SigquitRecord.class) {
            try {
                JSONArray jSONArray = new JSONArray();
                Iterator it = sRecords.iterator();
                while (it.hasNext()) {
                    SigquitRecord sigquitRecord = (SigquitRecord) it.next();
                    if (l != null && sigquitRecord.callbackUptimeMs > l.longValue()) {
                        break;
                    }
                    JSONObject jSONObject = new JSONObject();
                    jSONObject.put("callback_uptime_ms", sigquitRecord.callbackUptimeMs);
                    jSONObject.put("sigquit_uptime_ms", sigquitRecord.sigquitUptimeMs);
                    jSONArray.put(jSONObject);
                }
                str = jSONArray.toString();
                if (str == null) {
                    str = OacrConstants.AUTO_SPEECH_DOMAIN;
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
                    C0139Dd.A0P(TAG, "Corrupted file %s", str);
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
            C0139Dd.A0P(TAG, "Could not read from file %s", str, e);
            return -1;
        }
    }

    public static void updateRecords(long j, String str) {
        if (str != null) {
            synchronized (SigquitRecord.class) {
                ArrayList arrayList = sRecords;
                if (arrayList.size() <= 0 || ((SigquitRecord) arrayList.get(arrayList.size() - 1)).callbackUptimeMs != j) {
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
