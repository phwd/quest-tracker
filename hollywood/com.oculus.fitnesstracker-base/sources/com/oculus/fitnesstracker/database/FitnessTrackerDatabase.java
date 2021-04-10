package com.oculus.fitnesstracker.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import com.oculus.fitnesstracker.database.FitnessTrackerMoveContract;
import com.oculus.fitnesstracker.database.FitnessTrackerUserContract;
import com.oculus.modules.codegen.FitnessDataModule;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class FitnessTrackerDatabase {
    public static final String TAG = "FitnessTrackerDatabase";
    public static long s10minMs = 600000;
    private static SQLiteDatabase sDatabase = null;
    public static long sDayMs = 86400000;
    public static long sWeekMs = 604800000;
    private final FitnessTrackerDatabaseHelper mFitnessTrackerDatabaseHelper;

    public final void open() {
        this.mFitnessTrackerDatabaseHelper.setWriteAheadLoggingEnabled(true);
        sDatabase = this.mFitnessTrackerDatabaseHelper.getWritableDatabase();
    }

    public final void close() {
        sDatabase.close();
    }

    public FitnessTrackerDatabase(FitnessTrackerDatabaseHelper fitnessTrackerDatabaseHelper) {
        this.mFitnessTrackerDatabaseHelper = fitnessTrackerDatabaseHelper;
    }

    public synchronized void wipeAllData(Context context) {
        FitnessTrackerDatabaseHelper.readAndExecuteSQLScript(sDatabase, context, "table_drop_n_create.sql");
    }

    public void insertOrUpdateUserInfo(FitnessUserInfo fitnessUserInfo) {
        FitnessUserDataAPI.insertOrUpdateUserInfo(sDatabase, fitnessUserInfo);
    }

    public FitnessDataModule.UserInfo readUserInfo() {
        return FitnessUserDataAPI.readUserInfo(sDatabase);
    }

    public void insertUserMetrics(FitnessUserMetrics fitnessUserMetrics) {
        FitnessUserDataAPI.insertUserMetrics(sDatabase, fitnessUserMetrics);
    }

    public FitnessDataModule.UserMetrics readUserMetrics(long j) {
        return FitnessUserDataAPI.readUserMetrics(sDatabase, j);
    }

    public void insertUserGoals(FitnessUserGoals fitnessUserGoals) {
        FitnessUserDataAPI.insertUserGoals(sDatabase, fitnessUserGoals);
    }

    public FitnessDataModule.UserGoals readUserGoals(long j) {
        return FitnessUserDataAPI.readUserGoals(sDatabase, j);
    }

    public void insertCalories(FitnessCaloriesData fitnessCaloriesData, String str) {
        FitnessMoveDataAPI.insertCalories(sDatabase, fitnessCaloriesData, str);
    }

    public long getMostRecentCachedDayStart() {
        return FitnessMoveDataAPI.getMostRecentCachedDayStart(sDatabase);
    }

    public void updateDailyDataTable() {
        FitnessMoveDataAPI.updateDailyDataTable(sDatabase);
    }

    public List<Double> getActiveYears() {
        ArrayList arrayList = new ArrayList();
        Calendar instance = Calendar.getInstance();
        for (int i = 2020; i <= instance.get(1); i++) {
            Calendar instance2 = Calendar.getInstance();
            instance2.set(i, 0, 1, 0, 0, 0);
            Calendar instance3 = Calendar.getInstance();
            instance3.set(i, 11, 31, 23, 59, 59);
            if (FitnessMoveDataAPI.checkDataExistenceBetween(sDatabase, instance2.getTimeInMillis(), instance3.getTimeInMillis())) {
                arrayList.add(new Double((double) i));
            }
        }
        return arrayList;
    }

    public List<FitnessDataModule.TenMinsFitnessData> readDailyFitnessData(long j, long j2) {
        return FitnessMoveDataAPI.readDailyFitnessData(sDatabase, j, j2);
    }

    public List<FitnessDataModule.DailyFitnessData> readWeeklyFitnessData(long j, long j2) {
        return FitnessMoveDataAPI.readWeeklyFitnessData(sDatabase, j, j2);
    }

    public List<FitnessDataModule.WeeklyFitnessData> readMonthlyFitnessData(long j, long j2, int i) {
        return FitnessMoveDataAPI.readMonthlyFitnessData(sDatabase, j, j2, i);
    }

    public List<FitnessDataModule.MonthlyFitnessData> readYearlyFitnessData(long j, long j2) {
        return FitnessMoveDataAPI.readYearlyFitnessData(sDatabase, j, j2);
    }

    public FitnessDataModule.AllTimeSummaryData readAllTimeSummaryData(long j, long j2, List<String> list, int i) {
        updateDailyDataTable();
        return FitnessMoveDataAPI.readAllTimeSummaryData(sDatabase, j, j2, list, i);
    }

    public List<FitnessDataModule.MonthlySummaryData> readYearlySummaryData(long j, long j2, List<String> list, int i) {
        updateDailyDataTable();
        return FitnessMoveDataAPI.readYearlySummaryData$1f54db03(sDatabase, j, j2, list);
    }

    public List<FitnessDataModule.WeeklySummaryData> readMonthlySummaryData(long j, long j2, List<String> list, int i) {
        updateDailyDataTable();
        return FitnessMoveDataAPI.readMonthlySummaryData(sDatabase, j, j2, list, i);
    }

    public List<FitnessDataModule.DailySummaryData> readWeeklySummaryData(long j, long j2, List<String> list) {
        return FitnessMoveDataAPI.readWeeklySummaryData(sDatabase, j, j2, list);
    }

    public FitnessDataModule.DailySummaryData readDailySummaryData(long j, List<String> list) {
        return FitnessMoveDataAPI.readDailySummaryData(sDatabase, j, list);
    }

    public List<FitnessDataModule.ProgressData> readDailyProgress(long j, long j2) {
        updateDailyDataTable();
        return FitnessMoveDataAPI.readDailyProgress(sDatabase, j, j2);
    }

    public void addTestData() {
        Throwable th;
        Calendar instance = Calendar.getInstance();
        instance.set(2019, 6, 1, 0, 0, 0);
        Calendar instance2 = Calendar.getInstance();
        instance2.set(2019, 6, 28, 0, 0, 0);
        Calendar instance3 = Calendar.getInstance();
        instance3.set(2019, 7, 1, 0, 0, 0);
        sDatabase.beginTransactionNonExclusive();
        try {
            try {
                insertOrUpdateUserInfo(new FitnessUserInfo("male", "2000-01-01", "cm", "kg", "kcal"));
                ContentValues contentValues = new ContentValues();
                contentValues.put(FitnessTrackerUserContract.Goals.ACTIVE_TIME_GOAL, (Integer) 2700);
                contentValues.put(FitnessTrackerUserContract.Goals.CALORIES_GOAL, (Integer) 300);
                contentValues.put("creationTime", Long.valueOf(instance.getTimeInMillis()));
                sDatabase.insert(FitnessTrackerUserContract.Goals.TABLE_NAME, null, contentValues);
                ContentValues contentValues2 = new ContentValues();
                contentValues2.put(FitnessTrackerUserContract.Goals.ACTIVE_TIME_GOAL, (Integer) 600);
                contentValues2.put(FitnessTrackerUserContract.Goals.CALORIES_GOAL, (Integer) 60);
                contentValues2.put("creationTime", Long.valueOf(instance3.getTimeInMillis()));
                sDatabase.insert(FitnessTrackerUserContract.Goals.TABLE_NAME, null, contentValues2);
                ContentValues contentValues3 = new ContentValues();
                contentValues3.put(FitnessTrackerUserContract.Metrics.HEIGHT, (Integer) 180);
                contentValues3.put(FitnessTrackerUserContract.Metrics.WEIGHT, (Integer) 75);
                contentValues3.put("creationTime", Long.valueOf(instance.getTimeInMillis()));
                sDatabase.insert(FitnessTrackerUserContract.Metrics.TABLE_NAME, null, contentValues3);
                sDatabase.setTransactionSuccessful();
                long timeInMillis = instance2.getTimeInMillis();
                for (int i = 0; i < 16; i++) {
                    String str = TAG;
                    Log.d(str, "Test session start: " + new Date(timeInMillis));
                    ContentValues contentValues4 = new ContentValues();
                    contentValues4.put(FitnessTrackerMoveContract.Session.ACTIVITY, (String) null);
                    contentValues4.put(FitnessTrackerMoveContract.Session.PACKAGE_NAME, "com.oculus.test" + String.valueOf(i));
                    contentValues4.put(FitnessTrackerMoveContract.Session.START_DATE, Long.valueOf(timeInMillis));
                    long insert = sDatabase.insert(FitnessTrackerMoveContract.Session.TABLE_NAME, null, contentValues4);
                    long j = 900000 + timeInMillis;
                    for (long j2 = timeInMillis; j2 < j; j2 += 3000) {
                        ContentValues contentValues5 = new ContentValues();
                        contentValues5.put("calories", Double.valueOf(0.3d));
                        contentValues5.put("setDate", Long.valueOf(j2));
                        contentValues5.put(FitnessTrackerMoveContract.Calories.SESSION_ID, Long.valueOf(insert));
                        contentValues5.put(FitnessTrackerMoveContract.Calories.IS_ACTIVE, (Integer) 1);
                        sDatabase.insert("calories", null, contentValues5);
                    }
                    timeInMillis += sDayMs + 5000;
                }
                sDatabase.endTransaction();
            } catch (Throwable th2) {
                th = th2;
                sDatabase.endTransaction();
                throw th;
            }
        } catch (Throwable th3) {
            th = th3;
            sDatabase.endTransaction();
            throw th;
        }
    }

    public void addSimulatedData(List<String> list) {
        int i;
        long currentTimeMillis = System.currentTimeMillis() - (sDayMs * 366);
        long currentTimeMillis2 = System.currentTimeMillis();
        insertOrUpdateUserInfo(new FitnessUserInfo("male", "2000-01-01", "cm", "kg", "kcal"));
        sDatabase.beginTransactionNonExclusive();
        try {
            ContentValues contentValues = new ContentValues();
            contentValues.put(FitnessTrackerUserContract.Goals.ACTIVE_TIME_GOAL, (Integer) 2700);
            contentValues.put(FitnessTrackerUserContract.Goals.CALORIES_GOAL, (Integer) 300);
            contentValues.put("creationTime", Long.valueOf(currentTimeMillis));
            String str = null;
            sDatabase.insert(FitnessTrackerUserContract.Goals.TABLE_NAME, null, contentValues);
            ContentValues contentValues2 = new ContentValues();
            contentValues2.put(FitnessTrackerUserContract.Metrics.HEIGHT, (Integer) 180);
            contentValues2.put(FitnessTrackerUserContract.Metrics.WEIGHT, (Integer) 75);
            contentValues2.put("creationTime", Long.valueOf(currentTimeMillis));
            sDatabase.insert(FitnessTrackerUserContract.Metrics.TABLE_NAME, null, contentValues2);
            int i2 = 0;
            long j = currentTimeMillis;
            int i3 = 0;
            while (i3 < 365) {
                int i4 = 0;
                while (true) {
                    if (i4 >= 5) {
                        i = i3;
                        break;
                    }
                    ContentValues contentValues3 = new ContentValues();
                    contentValues3.put(FitnessTrackerMoveContract.Session.ACTIVITY, str);
                    contentValues3.put(FitnessTrackerMoveContract.Session.PACKAGE_NAME, list.get(ThreadLocalRandom.current().nextInt(i2, list.size())));
                    contentValues3.put(FitnessTrackerMoveContract.Session.START_DATE, Long.valueOf(j));
                    i = i3;
                    long insert = sDatabase.insert(FitnessTrackerMoveContract.Session.TABLE_NAME, str, contentValues3);
                    String str2 = TAG;
                    Log.d(str2, "Simulating Session: " + insert);
                    long nextLong = j + (ThreadLocalRandom.current().nextLong(300, 1200) * 1000);
                    while (true) {
                        if (j > nextLong) {
                            break;
                        }
                        ContentValues contentValues4 = new ContentValues();
                        contentValues4.put("calories", Double.valueOf(ThreadLocalRandom.current().nextDouble(0.0d, 0.6d)));
                        contentValues4.put("setDate", Long.valueOf(j));
                        contentValues4.put(FitnessTrackerMoveContract.Calories.SESSION_ID, Long.valueOf(insert));
                        contentValues4.put(FitnessTrackerMoveContract.Calories.IS_ACTIVE, Integer.valueOf(ThreadLocalRandom.current().nextInt(0, 2)));
                        String str3 = TAG;
                        Log.d(str3, "Simulating calories for sessionID " + insert + " at " + j + " to database.");
                        sDatabase.insert("calories", null, contentValues4);
                        j += 3000;
                        if (j >= currentTimeMillis2) {
                            break;
                        }
                    }
                    long nextLong2 = ThreadLocalRandom.current().nextLong(0, 300);
                    Long.signum(nextLong2);
                    j += nextLong2 * 1000;
                    if (j >= currentTimeMillis2) {
                        break;
                    }
                    i4++;
                    i3 = i;
                    i2 = 0;
                    str = null;
                }
                j += sDayMs - (ThreadLocalRandom.current().nextLong(300, 1200) * 2500);
                i3 = i + 1;
                i2 = 0;
                str = null;
            }
            String str4 = TAG;
            Log.d(str4, "Transaction complete: " + (System.currentTimeMillis() - currentTimeMillis2) + " msec");
            sDatabase.setTransactionSuccessful();
            sDatabase.execSQL("DELETE FROM days; VACCUM;");
        } finally {
            sDatabase.endTransaction();
        }
    }
}
