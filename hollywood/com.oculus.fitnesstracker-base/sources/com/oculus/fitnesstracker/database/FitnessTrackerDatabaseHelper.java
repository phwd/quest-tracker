package com.oculus.fitnesstracker.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import java.io.BufferedReader;
import java.io.IOException;

public class FitnessTrackerDatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "fitness_tracker";
    private static final int DATABASE_VERSION = 2;
    public static final String TAG = "FitnessTrackerDatabaseHelper";
    private static Context sContext;
    private static FitnessTrackerDatabaseHelper sInstance;

    public void onDowngrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
    }

    public static synchronized FitnessTrackerDatabaseHelper getInstance(Context context, SQLiteDatabase.CursorFactory cursorFactory) {
        FitnessTrackerDatabaseHelper fitnessTrackerDatabaseHelper;
        synchronized (FitnessTrackerDatabaseHelper.class) {
            if (sInstance == null) {
                sInstance = new FitnessTrackerDatabaseHelper(context.getApplicationContext(), cursorFactory);
            }
            fitnessTrackerDatabaseHelper = sInstance;
        }
        return fitnessTrackerDatabaseHelper;
    }

    private FitnessTrackerDatabaseHelper(Context context, SQLiteDatabase.CursorFactory cursorFactory) {
        super(context, DATABASE_NAME, cursorFactory, 2);
        sContext = context;
    }

    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        Log.d(TAG, "Create Database Helper");
        readAndExecuteSQLScript(sQLiteDatabase, sContext, "table_create.sql");
    }

    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        String str = TAG;
        Log.d(str, "Updating table from " + i + " to " + i2);
        while (i < i2) {
            try {
                i++;
                String format = String.format("from_%d_to_%d.sql", Integer.valueOf(i), Integer.valueOf(i));
                String str2 = TAG;
                Log.d(str2, "Looking for migration file: " + format);
                readAndExecuteSQLScript(sQLiteDatabase, sContext, format);
            } catch (Exception e) {
                Log.e(TAG, "Exception running upgrade script:", e);
                return;
            }
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:24:0x004f A[SYNTHETIC, Splitter:B:24:0x004f] */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x005c A[SYNTHETIC, Splitter:B:30:0x005c] */
    /* JADX WARNING: Removed duplicated region for block: B:36:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void readAndExecuteSQLScript(android.database.sqlite.SQLiteDatabase r4, android.content.Context r5, java.lang.String r6) {
        /*
        // Method dump skipped, instructions count: 103
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.fitnesstracker.database.FitnessTrackerDatabaseHelper.readAndExecuteSQLScript(android.database.sqlite.SQLiteDatabase, android.content.Context, java.lang.String):void");
    }

    private static void executeSQLScript(SQLiteDatabase sQLiteDatabase, BufferedReader bufferedReader) throws IOException {
        StringBuilder sb = new StringBuilder();
        while (true) {
            String readLine = bufferedReader.readLine();
            if (readLine != null) {
                sb.append(readLine);
                sb.append("\n");
                if (readLine.endsWith(";")) {
                    String str = TAG;
                    Log.d(str, "Executing sql: " + sb.toString());
                    sQLiteDatabase.execSQL(sb.toString());
                    sb = new StringBuilder();
                }
            } else {
                return;
            }
        }
    }
}
