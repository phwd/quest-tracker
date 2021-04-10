package com.oculus.assistant.service;

import X.BX;
import X.C00809k;
import X.C0139Dd;
import X.C0435Xn;
import X.C0514bB;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.oculus.os.DumpsysProxyClientService;

public final class AssistantDumpService extends DumpsysProxyClientService {
    public static final String APP_NAME = "Assistant";
    public static final C0435Xn Companion = new C0435Xn();
    public static final int MAX_LOOKBACK_TIME = 5;
    public static final String TAG = "AssistantDumpService";

    public String getAppName() {
        return APP_NAME;
    }

    public String dumpState() {
        StringBuilder sb = new StringBuilder();
        SQLiteDatabase writableDatabase = new C00809k(BX.A00()).getWritableDatabase();
        Cursor query = writableDatabase.query("entries", new String[]{"timestamp", "event", "event_data", "interaction"}, "timestamp BETWEEN datetime('now', '-5 minutes') AND datetime('now')", null, null, null, null, null);
        if (query.moveToFirst()) {
            int columnCount = query.getColumnCount();
            for (int i = 0; i < columnCount; i++) {
                sb.append(query.getColumnName(i));
                sb.append('\t');
            }
            sb.append('\n');
            do {
                int columnCount2 = query.getColumnCount();
                for (int i2 = 0; i2 < columnCount2; i2++) {
                    sb.append(query.getString(i2));
                    sb.append("\t");
                }
                sb.append("\n");
            } while (query.moveToNext());
        } else {
            sb.append("No interactions happened in the last ");
            sb.append(5);
            sb.append(" minutes");
        }
        query.close();
        writableDatabase.close();
        String obj = sb.toString();
        C0514bB.A01(obj, "state.toString()");
        return obj;
    }

    public int onStartCommand(Intent intent, int i, int i2) {
        C0514bB.A02(intent, "intent");
        C0139Dd.A09(TAG, "onStartCommand");
        return 2;
    }

    public void onCreate() {
        AssistantDumpService.super.onCreate();
    }

    public void onDestroy() {
        AssistantDumpService.super.onDestroy();
        C0139Dd.A09(TAG, "StoppingDumpService");
    }
}
