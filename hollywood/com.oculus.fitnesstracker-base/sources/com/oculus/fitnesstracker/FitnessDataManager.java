package com.oculus.fitnesstracker;

import android.content.Context;
import com.oculus.fitnesstracker.database.FitnessCaloriesData;
import com.oculus.fitnesstracker.database.FitnessTrackerDatabase;
import com.oculus.fitnesstracker.database.FitnessTrackerDatabaseHelper;
import com.oculus.fitnesstracker.database.FitnessUserGoals;
import com.oculus.fitnesstracker.database.FitnessUserInfo;
import com.oculus.fitnesstracker.database.FitnessUserMetrics;
import com.oculus.modules.codegen.FitnessDataModule;
import java.util.List;

public class FitnessDataManager {
    private static final String TAG = "FitnessDataManager";
    private static Context sContext;
    private static FitnessTrackerDatabase sFitnessTrackerDatabase;
    private static FitnessDataManager sInstance;
    private FitnessTrackerDatabaseHelper mFitnessTrackerDatabaseHelper;

    private FitnessDataManager(Context context) {
        sContext = context;
        this.mFitnessTrackerDatabaseHelper = FitnessTrackerDatabaseHelper.getInstance(context, null);
        FitnessTrackerDatabase fitnessTrackerDatabase = new FitnessTrackerDatabase(this.mFitnessTrackerDatabaseHelper);
        sFitnessTrackerDatabase = fitnessTrackerDatabase;
        fitnessTrackerDatabase.open();
    }

    public static FitnessDataManager getInstance(Context context) {
        FitnessDataManager fitnessDataManager = sInstance;
        if (fitnessDataManager != null) {
            return fitnessDataManager;
        }
        FitnessDataManager fitnessDataManager2 = new FitnessDataManager(context);
        sInstance = fitnessDataManager2;
        return fitnessDataManager2;
    }

    public static synchronized void insertCalories(double d, long j, int i, String str) {
        synchronized (FitnessDataManager.class) {
            sFitnessTrackerDatabase.insertCalories(new FitnessCaloriesData(Double.valueOf(d), j, i), str);
        }
    }

    public List<Double> getActiveYears() {
        return sFitnessTrackerDatabase.getActiveYears();
    }

    public List<FitnessDataModule.TenMinsFitnessData> readDailyFitnessData(long j, long j2) {
        return sFitnessTrackerDatabase.readDailyFitnessData(j, j2);
    }

    public List<FitnessDataModule.DailyFitnessData> readWeeklyFitnessData(long j, long j2) {
        return sFitnessTrackerDatabase.readWeeklyFitnessData(j, j2);
    }

    public List<FitnessDataModule.WeeklyFitnessData> readMonthlyFitnessData(long j, long j2, int i) {
        return sFitnessTrackerDatabase.readMonthlyFitnessData(j, j2, i);
    }

    public List<FitnessDataModule.MonthlyFitnessData> readYearlyFitnessData(long j, long j2) {
        return sFitnessTrackerDatabase.readYearlyFitnessData(j, j2);
    }

    public FitnessDataModule.AllTimeSummaryData readAllTimeSummaryData(long j, long j2, List<String> list, int i) {
        return sFitnessTrackerDatabase.readAllTimeSummaryData(j, j2, list, i);
    }

    public List<FitnessDataModule.MonthlySummaryData> readYearlySummaryData(long j, long j2, List<String> list, int i) {
        return sFitnessTrackerDatabase.readYearlySummaryData(j, j2, list, i);
    }

    public List<FitnessDataModule.WeeklySummaryData> readMonthlySummaryData(long j, long j2, List<String> list, int i) {
        return sFitnessTrackerDatabase.readMonthlySummaryData(j, j2, list, i);
    }

    public List<FitnessDataModule.DailySummaryData> readWeeklySummaryData(long j, long j2, List<String> list) {
        return sFitnessTrackerDatabase.readWeeklySummaryData(j, j2, list);
    }

    public FitnessDataModule.DailySummaryData readDailySummaryData(long j, List<String> list) {
        return sFitnessTrackerDatabase.readDailySummaryData(j, list);
    }

    public List<FitnessDataModule.ProgressData> readDailyProgress(long j, long j2) {
        return sFitnessTrackerDatabase.readDailyProgress(j, j2);
    }

    public void insertOrUpdateUserInfo(FitnessDataModule.UserInfo userInfo) {
        sFitnessTrackerDatabase.insertOrUpdateUserInfo(new FitnessUserInfo(userInfo.sex, userInfo.dateOfBirth, userInfo.heightUnit == null ? null : userInfo.heightUnit.name(), userInfo.weightUnit == null ? null : userInfo.weightUnit.name(), userInfo.caloriesUnit == null ? null : userInfo.caloriesUnit.name()));
    }

    public FitnessDataModule.UserInfo readUserInfo() {
        return sFitnessTrackerDatabase.readUserInfo();
    }

    public void insertUserMetrics(FitnessDataModule.UserMetrics userMetrics) {
        sFitnessTrackerDatabase.insertUserMetrics(new FitnessUserMetrics(userMetrics.height, userMetrics.weight));
    }

    public FitnessDataModule.UserMetrics readUserMetrics(long j) {
        return sFitnessTrackerDatabase.readUserMetrics(j);
    }

    public void insertUserGoals(FitnessDataModule.UserGoals userGoals) {
        sFitnessTrackerDatabase.insertUserGoals(new FitnessUserGoals(userGoals.activeTimeGoal, userGoals.caloriesGoal));
    }

    public FitnessDataModule.UserGoals readUserGoals(long j) {
        return sFitnessTrackerDatabase.readUserGoals(j);
    }

    public void addSimulatedData(List<String> list) {
        sFitnessTrackerDatabase.addSimulatedData(list);
    }

    public synchronized void wipeAllData() {
        sFitnessTrackerDatabase.wipeAllData(sContext);
    }

    public long getMostRecentCachedDayStart() {
        return sFitnessTrackerDatabase.getMostRecentCachedDayStart();
    }

    public void updateDailyDataTable() {
        sFitnessTrackerDatabase.updateDailyDataTable();
    }

    public static double getUserBMR(long j) {
        FitnessDataModule.UserInfo readUserInfo = sFitnessTrackerDatabase.readUserInfo();
        FitnessDataModule.UserMetrics readUserMetrics = sFitnessTrackerDatabase.readUserMetrics(j);
        return FitnessBMREstimator.calculateUserBMR(readUserInfo.sex == null ? "other" : readUserInfo.sex, readUserInfo.dateOfBirth, readUserMetrics.height == null ? 0.0d : readUserMetrics.height.doubleValue(), readUserMetrics.weight == null ? 0.0d : readUserMetrics.weight.doubleValue());
    }
}
