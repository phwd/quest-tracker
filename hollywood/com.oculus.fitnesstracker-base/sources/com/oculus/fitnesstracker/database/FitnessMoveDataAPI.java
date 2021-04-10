package com.oculus.fitnesstracker.database;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import com.oculus.common.build.BuildConfig;
import com.oculus.fitnesstracker.database.FitnessTrackerMoveContract;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

/* access modifiers changed from: package-private */
public final class FitnessMoveDataAPI {
    private static int SECS_PER_ENTRY = 3;

    /* JADX WARNING: Code restructure failed: missing block: B:15:0x005e, code lost:
        if (r2.isClosed() == false) goto L_0x0039;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:6:0x0037, code lost:
        if (r2.isClosed() == false) goto L_0x0039;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:7:0x0039, code lost:
        r2.close();
     */
    /* JADX WARNING: Removed duplicated region for block: B:14:0x005a  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean checkDataExistenceBetween(android.database.sqlite.SQLiteDatabase r2, long r3, long r5) {
        /*
        // Method dump skipped, instructions count: 113
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.fitnesstracker.database.FitnessMoveDataAPI.checkDataExistenceBetween(android.database.sqlite.SQLiteDatabase, long, long):boolean");
    }

    private static synchronized void updateSessionInfo(SQLiteDatabase sQLiteDatabase, String str) {
        Throwable th;
        Cursor cursor;
        synchronized (FitnessMoveDataAPI.class) {
            if (str != null) {
                if (!str.isEmpty()) {
                    try {
                        cursor = sQLiteDatabase.rawQuery("SELECT packageName, MAX(startDate) FROM session WHERE startDate <= " + String.valueOf(System.currentTimeMillis()), new String[0]);
                        try {
                            if (!cursor.moveToFirst() || !str.equals(cursor.getString(cursor.getColumnIndexOrThrow(FitnessTrackerMoveContract.Session.PACKAGE_NAME)))) {
                                ContentValues contentValues = new ContentValues();
                                contentValues.put(FitnessTrackerMoveContract.Session.ACTIVITY, (String) null);
                                contentValues.put(FitnessTrackerMoveContract.Session.PACKAGE_NAME, str);
                                contentValues.put(FitnessTrackerMoveContract.Session.START_DATE, Long.valueOf(System.currentTimeMillis()));
                                sQLiteDatabase.insert(FitnessTrackerMoveContract.Session.TABLE_NAME, null, contentValues);
                            }
                            if (!cursor.isClosed()) {
                                cursor.close();
                            }
                        } catch (Throwable th2) {
                            th = th2;
                            try {
                                Log.e(FitnessTrackerDatabase.TAG, "Unexpected error when updating session info: " + th.getMessage());
                            } finally {
                                if (cursor != null && !cursor.isClosed()) {
                                    cursor.close();
                                }
                            }
                        }
                    } catch (Throwable th3) {
                        th = th3;
                        cursor = null;
                        Log.e(FitnessTrackerDatabase.TAG, "Unexpected error when updating session info: " + th.getMessage());
                    }
                }
            }
        }
    }

    public static synchronized void insertCalories(SQLiteDatabase sQLiteDatabase, FitnessCaloriesData fitnessCaloriesData, String str) {
        Cursor cursor;
        Throwable th;
        synchronized (FitnessMoveDataAPI.class) {
            sQLiteDatabase.beginTransactionNonExclusive();
            try {
                updateSessionInfo(sQLiteDatabase, str);
                cursor = sQLiteDatabase.rawQuery("SELECT _id, MAX(startDate) FROM session", new String[0]);
                try {
                    if (!cursor.moveToFirst()) {
                        if (!cursor.isClosed()) {
                            cursor.close();
                        }
                        sQLiteDatabase.endTransaction();
                        return;
                    }
                    long j = cursor.getLong(cursor.getColumnIndexOrThrow("_id"));
                    ContentValues contentValues = new ContentValues();
                    contentValues.put("calories", fitnessCaloriesData.getCalories());
                    contentValues.put("setDate", Long.valueOf(fitnessCaloriesData.getSetDate()));
                    contentValues.put(FitnessTrackerMoveContract.Calories.IS_ACTIVE, Integer.valueOf(fitnessCaloriesData.getIsActive()));
                    contentValues.put(FitnessTrackerMoveContract.Calories.SESSION_ID, Long.valueOf(j));
                    sQLiteDatabase.insert("calories", null, contentValues);
                    sQLiteDatabase.setTransactionSuccessful();
                    if (!cursor.isClosed()) {
                        cursor.close();
                    }
                    sQLiteDatabase.endTransaction();
                } catch (Throwable th2) {
                    th = th2;
                    try {
                        Log.e(FitnessTrackerDatabase.TAG, "Unexpected error when inserting calories: " + th.getMessage());
                    } finally {
                        if (cursor != null && !cursor.isClosed()) {
                            cursor.close();
                        }
                        sQLiteDatabase.endTransaction();
                    }
                }
            } catch (Throwable th3) {
                th = th3;
                cursor = null;
                Log.e(FitnessTrackerDatabase.TAG, "Unexpected error when inserting calories: " + th.getMessage());
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0044, code lost:
        if (r2.isClosed() == false) goto L_0x0022;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:6:0x0020, code lost:
        if (r2.isClosed() == false) goto L_0x0022;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:7:0x0022, code lost:
        r2.close();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static long getMostRecentCachedDayStart(android.database.sqlite.SQLiteDatabase r6) {
        /*
            r0 = 0
            r2 = 0
            java.lang.String r3 = "SELECT strftime('%s', MAX(date) || 'T00:00:00.000', 'utc') * 1000 AS mostRecentDay FROM days"
            r4 = 0
            java.lang.String[] r4 = new java.lang.String[r4]     // Catch:{ all -> 0x0026 }
            android.database.Cursor r2 = r6.rawQuery(r3, r4)     // Catch:{ all -> 0x0026 }
            boolean r6 = r2.moveToFirst()     // Catch:{ all -> 0x0026 }
            if (r6 == 0) goto L_0x001c
            java.lang.String r6 = "mostRecentDay"
            int r6 = r2.getColumnIndexOrThrow(r6)     // Catch:{ all -> 0x0026 }
            long r0 = r2.getLong(r6)     // Catch:{ all -> 0x0026 }
        L_0x001c:
            boolean r6 = r2.isClosed()
            if (r6 != 0) goto L_0x0047
        L_0x0022:
            r2.close()
            goto L_0x0047
        L_0x0026:
            r6 = move-exception
            java.lang.String r3 = com.oculus.fitnesstracker.database.FitnessTrackerDatabase.TAG     // Catch:{ all -> 0x0048 }
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ all -> 0x0048 }
            java.lang.String r5 = "Unexpected error when checking most recent day from DB: "
            r4.<init>(r5)     // Catch:{ all -> 0x0048 }
            java.lang.String r6 = r6.getMessage()     // Catch:{ all -> 0x0048 }
            r4.append(r6)     // Catch:{ all -> 0x0048 }
            java.lang.String r6 = r4.toString()     // Catch:{ all -> 0x0048 }
            android.util.Log.e(r3, r6)     // Catch:{ all -> 0x0048 }
            if (r2 == 0) goto L_0x0047
            boolean r6 = r2.isClosed()
            if (r6 != 0) goto L_0x0047
            goto L_0x0022
        L_0x0047:
            return r0
        L_0x0048:
            r6 = move-exception
            if (r2 == 0) goto L_0x0054
            boolean r0 = r2.isClosed()
            if (r0 != 0) goto L_0x0054
            r2.close()
        L_0x0054:
            goto L_0x0056
        L_0x0055:
            throw r6
        L_0x0056:
            goto L_0x0055
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.fitnesstracker.database.FitnessMoveDataAPI.getMostRecentCachedDayStart(android.database.sqlite.SQLiteDatabase):long");
    }

    public static synchronized void updateDailyDataTable(SQLiteDatabase sQLiteDatabase) {
        Throwable th;
        Cursor cursor;
        synchronized (FitnessMoveDataAPI.class) {
            String[] strArr = new String[0];
            Calendar instance = Calendar.getInstance();
            instance.set(10, 23);
            instance.set(12, 59);
            instance.set(13, 59);
            instance.set(14, 999);
            long mostRecentCachedDayStart = getMostRecentCachedDayStart(sQLiteDatabase);
            sQLiteDatabase.beginTransactionNonExclusive();
            try {
                cursor = sQLiteDatabase.rawQuery(getDailySummarySql(mostRecentCachedDayStart, instance.getTimeInMillis(), new ArrayList(0)), strArr);
                try {
                    HashMap hashMap = new HashMap();
                    hashMap.put(FitnessTrackerMoveContract.Days.DATE, Integer.valueOf(cursor.getColumnIndexOrThrow(FitnessTrackerMoveContract.Days.DATE)));
                    hashMap.put("calories", Integer.valueOf(cursor.getColumnIndexOrThrow("calories")));
                    hashMap.put(FitnessTrackerMoveContract.Days.ACTIVE_TIME, Integer.valueOf(cursor.getColumnIndexOrThrow(FitnessTrackerMoveContract.Days.ACTIVE_TIME)));
                    hashMap.put(FitnessTrackerMoveContract.Days.CALORIES_PROGRESS, Integer.valueOf(cursor.getColumnIndexOrThrow(FitnessTrackerMoveContract.Days.CALORIES_PROGRESS)));
                    hashMap.put(FitnessTrackerMoveContract.Days.ACTIVE_TIME_PROGRESS, Integer.valueOf(cursor.getColumnIndexOrThrow(FitnessTrackerMoveContract.Days.ACTIVE_TIME_PROGRESS)));
                    while (cursor.moveToNext()) {
                        String string = cursor.getString(((Integer) hashMap.get(FitnessTrackerMoveContract.Days.DATE)).intValue());
                        Double valueOf = Double.valueOf(cursor.getDouble(((Integer) hashMap.get("calories")).intValue()));
                        int i = cursor.getInt(((Integer) hashMap.get(FitnessTrackerMoveContract.Days.ACTIVE_TIME)).intValue());
                        Double valueOf2 = Double.valueOf(cursor.getDouble(cursor.getColumnIndexOrThrow(FitnessTrackerMoveContract.Days.CALORIES_PROGRESS)));
                        Double valueOf3 = Double.valueOf(cursor.getDouble(cursor.getColumnIndexOrThrow(FitnessTrackerMoveContract.Days.ACTIVE_TIME_PROGRESS)));
                        ContentValues contentValues = new ContentValues();
                        contentValues.put(FitnessTrackerMoveContract.Days.DATE, string);
                        contentValues.put("calories", valueOf);
                        contentValues.put(FitnessTrackerMoveContract.Days.ACTIVE_TIME, Integer.valueOf(i));
                        contentValues.put(FitnessTrackerMoveContract.Days.CALORIES_PROGRESS, valueOf2);
                        contentValues.put(FitnessTrackerMoveContract.Days.ACTIVE_TIME_PROGRESS, valueOf3);
                        sQLiteDatabase.insertWithOnConflict(FitnessTrackerMoveContract.Days.TABLE_NAME, null, contentValues, 5);
                    }
                    sQLiteDatabase.setTransactionSuccessful();
                    if (!cursor.isClosed()) {
                        cursor.close();
                    }
                    sQLiteDatabase.endTransaction();
                } catch (Throwable th2) {
                    th = th2;
                    if (cursor != null && !cursor.isClosed()) {
                        cursor.close();
                    }
                    sQLiteDatabase.endTransaction();
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                cursor = null;
                cursor.close();
                sQLiteDatabase.endTransaction();
                throw th;
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:18:0x01af, code lost:
        if (r7.isClosed() == false) goto L_0x01d5;
     */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x01cf A[DONT_GENERATE] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.util.List<com.oculus.modules.codegen.FitnessDataModule.TenMinsFitnessData> readDailyFitnessData(android.database.sqlite.SQLiteDatabase r18, long r19, long r21) {
        /*
        // Method dump skipped, instructions count: 488
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.fitnesstracker.database.FitnessMoveDataAPI.readDailyFitnessData(android.database.sqlite.SQLiteDatabase, long, long):java.util.List");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0127, code lost:
        if (r11.isClosed() == false) goto L_0x014d;
     */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x0147 A[DONT_GENERATE] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.util.List<com.oculus.modules.codegen.FitnessDataModule.DailyFitnessData> readWeeklyFitnessData(android.database.sqlite.SQLiteDatabase r11, long r12, long r14) {
        /*
        // Method dump skipped, instructions count: 352
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.fitnesstracker.database.FitnessMoveDataAPI.readWeeklyFitnessData(android.database.sqlite.SQLiteDatabase, long, long):java.util.List");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0144, code lost:
        if (r13.isClosed() == false) goto L_0x0167;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0165, code lost:
        if (r13.isClosed() == false) goto L_0x0167;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0167, code lost:
        r13.close();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.util.List<com.oculus.modules.codegen.FitnessDataModule.WeeklyFitnessData> readMonthlyFitnessData(android.database.sqlite.SQLiteDatabase r10, long r11, long r13, int r15) {
        /*
        // Method dump skipped, instructions count: 378
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.fitnesstracker.database.FitnessMoveDataAPI.readMonthlyFitnessData(android.database.sqlite.SQLiteDatabase, long, long, int):java.util.List");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:9:0x012d, code lost:
        if (r11.isClosed() == false) goto L_0x0153;
     */
    /* JADX WARNING: Removed duplicated region for block: B:16:0x014d A[DONT_GENERATE] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.util.List<com.oculus.modules.codegen.FitnessDataModule.MonthlyFitnessData> readYearlyFitnessData(android.database.sqlite.SQLiteDatabase r11, long r12, long r14) {
        /*
        // Method dump skipped, instructions count: 358
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.fitnesstracker.database.FitnessMoveDataAPI.readYearlyFitnessData(android.database.sqlite.SQLiteDatabase, long, long):java.util.List");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x00a7, code lost:
        if (r6.isClosed() == false) goto L_0x00cd;
     */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x00c7 A[DONT_GENERATE] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.util.List<com.oculus.modules.codegen.FitnessDataModule.ProgressData> readDailyProgress(android.database.sqlite.SQLiteDatabase r6, long r7, long r9) {
        /*
        // Method dump skipped, instructions count: 224
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.fitnesstracker.database.FitnessMoveDataAPI.readDailyProgress(android.database.sqlite.SQLiteDatabase, long, long):java.util.List");
    }

    private static String getDailySummarySql(long j, long j2, List<String> list) {
        String str = "'";
        if (list.size() > 0) {
            String str2 = str;
            for (int i = 0; i < list.size() - 1; i++) {
                str2 = str2 + list.get(i) + "', '";
            }
            str = str2 + list.get(list.size() - 1) + str;
        }
        String str3 = " AND packageName IN (" + str + ")";
        StringBuilder sb = new StringBuilder("SELECT SUM(calories) AS calories, COUNT(CASE WHEN isActive = 1 THEN 1 END) * ");
        sb.append(String.valueOf(SECS_PER_ENTRY));
        sb.append(" AS activeTime, strftime('%Y-%m-%d', setDate");
        sb.append(" / 1000, 'unixepoch', 'localtime') AS date FROM calories");
        sb.append(list.size() == 0 ? BuildConfig.PROVIDER_SUFFIX : " INNER JOIN session ON session._id = sessionId");
        sb.append(" WHERE setDate");
        sb.append(" >= ");
        sb.append(String.valueOf(j));
        sb.append(" AND setDate");
        sb.append(" <= ");
        sb.append(String.valueOf(j2));
        if (list.size() == 0) {
            str3 = BuildConfig.PROVIDER_SUFFIX;
        }
        sb.append(str3);
        sb.append(" GROUP BY date");
        return "SELECT calories, activeTime, caloriesGoal, activeTimeGoal, calories / caloriesGoal AS caloriesProgress, activeTime / activeTimeGoal AS activeTimeProgress, MAX(creationTime), strftime('%s', date || 'T00:00:00.000', 'utc') * 1000 AS startOfDay, date FROM (" + sb.toString() + ") JOIN goals" + " WHERE creationTime" + " <= startOfDay + " + String.valueOf(FitnessTrackerDatabase.sDayMs) + " GROUP BY startOfDay";
    }

    /* JADX WARNING: Code restructure failed: missing block: B:19:0x014a, code lost:
        if (r11.isClosed() == false) goto L_0x016d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x016b, code lost:
        if (r11.isClosed() == false) goto L_0x016d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x016d, code lost:
        r11.close();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.oculus.modules.codegen.FitnessDataModule.AllTimeSummaryData readAllTimeSummaryData(android.database.sqlite.SQLiteDatabase r7, long r8, long r10, java.util.List<java.lang.String> r12, int r13) {
        /*
        // Method dump skipped, instructions count: 382
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.fitnesstracker.database.FitnessMoveDataAPI.readAllTimeSummaryData(android.database.sqlite.SQLiteDatabase, long, long, java.util.List, int):com.oculus.modules.codegen.FitnessDataModule$AllTimeSummaryData");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x015b, code lost:
        if (r9.isClosed() == false) goto L_0x017e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x017c, code lost:
        if (r9.isClosed() == false) goto L_0x017e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x017e, code lost:
        r9.close();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.util.List<com.oculus.modules.codegen.FitnessDataModule.MonthlySummaryData> readYearlySummaryData$1f54db03(android.database.sqlite.SQLiteDatabase r19, long r20, long r22, java.util.List<java.lang.String> r24) {
        /*
        // Method dump skipped, instructions count: 401
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.fitnesstracker.database.FitnessMoveDataAPI.readYearlySummaryData$1f54db03(android.database.sqlite.SQLiteDatabase, long, long, java.util.List):java.util.List");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0169, code lost:
        if (r9.isClosed() == false) goto L_0x018c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x018a, code lost:
        if (r9.isClosed() == false) goto L_0x018c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x018c, code lost:
        r9.close();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.util.List<com.oculus.modules.codegen.FitnessDataModule.WeeklySummaryData> readMonthlySummaryData(android.database.sqlite.SQLiteDatabase r18, long r19, long r21, java.util.List<java.lang.String> r23, int r24) {
        /*
        // Method dump skipped, instructions count: 415
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.fitnesstracker.database.FitnessMoveDataAPI.readMonthlySummaryData(android.database.sqlite.SQLiteDatabase, long, long, java.util.List, int):java.util.List");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x014f, code lost:
        if (r7.isClosed() == false) goto L_0x0175;
     */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x016f A[DONT_GENERATE] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.util.List<com.oculus.modules.codegen.FitnessDataModule.DailySummaryData> readWeeklySummaryData(android.database.sqlite.SQLiteDatabase r22, long r23, long r25, java.util.List<java.lang.String> r27) {
        /*
        // Method dump skipped, instructions count: 392
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.fitnesstracker.database.FitnessMoveDataAPI.readWeeklySummaryData(android.database.sqlite.SQLiteDatabase, long, long, java.util.List):java.util.List");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x00ce, code lost:
        if (r2.isClosed() == false) goto L_0x00f1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x00ef, code lost:
        if (r2.isClosed() == false) goto L_0x00f1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x00f1, code lost:
        r2.close();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.oculus.modules.codegen.FitnessDataModule.DailySummaryData readDailySummaryData(android.database.sqlite.SQLiteDatabase r8, long r9, java.util.List<java.lang.String> r11) {
        /*
        // Method dump skipped, instructions count: 258
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.fitnesstracker.database.FitnessMoveDataAPI.readDailySummaryData(android.database.sqlite.SQLiteDatabase, long, java.util.List):com.oculus.modules.codegen.FitnessDataModule$DailySummaryData");
    }
}
