package com.oculus.fitnesstracker.database;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import com.oculus.fitnesstracker.database.FitnessTrackerUserContract;

/* access modifiers changed from: package-private */
public final class FitnessUserDataAPI {
    public static String[] userColumns = {FitnessTrackerUserContract.User.SEX, FitnessTrackerUserContract.User.DATE_OF_BIRTH, FitnessTrackerUserContract.User.HEIGHT_UNIT, FitnessTrackerUserContract.User.WEIGHT_UNIT, FitnessTrackerUserContract.User.CALORIES_UNIT, "creationTime"};

    public static void insertOrUpdateUserInfo(SQLiteDatabase sQLiteDatabase, FitnessUserInfo fitnessUserInfo) {
        sQLiteDatabase.beginTransactionNonExclusive();
        try {
            ContentValues contentValues = new ContentValues();
            contentValues.put(FitnessTrackerUserContract.User.SEX, fitnessUserInfo.getSex());
            contentValues.put(FitnessTrackerUserContract.User.DATE_OF_BIRTH, fitnessUserInfo.getDateOfBirth());
            contentValues.put(FitnessTrackerUserContract.User.HEIGHT_UNIT, fitnessUserInfo.getHeightUnit());
            contentValues.put(FitnessTrackerUserContract.User.WEIGHT_UNIT, fitnessUserInfo.getWeightUnit());
            contentValues.put(FitnessTrackerUserContract.User.CALORIES_UNIT, fitnessUserInfo.getCaloriesUnit());
            contentValues.put("creationTime", Long.valueOf(System.currentTimeMillis()));
            if (sQLiteDatabase.insertWithOnConflict(FitnessTrackerUserContract.User.TABLE_NAME, null, contentValues, 4) == -1) {
                sQLiteDatabase.update(FitnessTrackerUserContract.User.TABLE_NAME, contentValues, "_id=?", new String[]{"1"});
            }
            sQLiteDatabase.setTransactionSuccessful();
        } finally {
            sQLiteDatabase.endTransaction();
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:26:0x012c, code lost:
        if (r15.isClosed() == false) goto L_0x0152;
     */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x014c A[DONT_GENERATE] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.oculus.modules.codegen.FitnessDataModule.UserInfo readUserInfo(android.database.sqlite.SQLiteDatabase r15) {
        /*
        // Method dump skipped, instructions count: 355
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.fitnesstracker.database.FitnessUserDataAPI.readUserInfo(android.database.sqlite.SQLiteDatabase):com.oculus.modules.codegen.FitnessDataModule$UserInfo");
    }

    public static void insertUserMetrics(SQLiteDatabase sQLiteDatabase, FitnessUserMetrics fitnessUserMetrics) {
        sQLiteDatabase.beginTransactionNonExclusive();
        try {
            ContentValues contentValues = new ContentValues();
            contentValues.put(FitnessTrackerUserContract.Metrics.HEIGHT, fitnessUserMetrics.getHeight());
            contentValues.put(FitnessTrackerUserContract.Metrics.WEIGHT, fitnessUserMetrics.getWeight());
            contentValues.put("creationTime", Long.valueOf(System.currentTimeMillis()));
            sQLiteDatabase.insert(FitnessTrackerUserContract.Metrics.TABLE_NAME, null, contentValues);
            sQLiteDatabase.setTransactionSuccessful();
        } finally {
            sQLiteDatabase.endTransaction();
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:18:0x00b0, code lost:
        if (r6.isClosed() == false) goto L_0x00d6;
     */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x00d0 A[DONT_GENERATE] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.oculus.modules.codegen.FitnessDataModule.UserMetrics readUserMetrics(android.database.sqlite.SQLiteDatabase r6, long r7) {
        /*
        // Method dump skipped, instructions count: 231
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.fitnesstracker.database.FitnessUserDataAPI.readUserMetrics(android.database.sqlite.SQLiteDatabase, long):com.oculus.modules.codegen.FitnessDataModule$UserMetrics");
    }

    public static void insertUserGoals(SQLiteDatabase sQLiteDatabase, FitnessUserGoals fitnessUserGoals) {
        sQLiteDatabase.beginTransactionNonExclusive();
        try {
            ContentValues contentValues = new ContentValues();
            contentValues.put(FitnessTrackerUserContract.Goals.ACTIVE_TIME_GOAL, fitnessUserGoals.getActiveTimeGoal());
            contentValues.put(FitnessTrackerUserContract.Goals.CALORIES_GOAL, fitnessUserGoals.getCaloriesGoal());
            contentValues.put("creationTime", Long.valueOf(System.currentTimeMillis()));
            sQLiteDatabase.insert(FitnessTrackerUserContract.Goals.TABLE_NAME, null, contentValues);
            sQLiteDatabase.setTransactionSuccessful();
        } finally {
            sQLiteDatabase.endTransaction();
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:18:0x00b0, code lost:
        if (r6.isClosed() == false) goto L_0x00d6;
     */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x00d0 A[DONT_GENERATE] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.oculus.modules.codegen.FitnessDataModule.UserGoals readUserGoals(android.database.sqlite.SQLiteDatabase r6, long r7) {
        /*
        // Method dump skipped, instructions count: 231
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.fitnesstracker.database.FitnessUserDataAPI.readUserGoals(android.database.sqlite.SQLiteDatabase, long):com.oculus.modules.codegen.FitnessDataModule$UserGoals");
    }
}
