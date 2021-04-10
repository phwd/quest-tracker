package com.oculus.fitnesstracker.provider;

import android.database.Cursor;
import android.database.MatrixCursor;
import com.oculus.fitnesstracker.database.FitnessTrackerMoveContract;
import com.oculus.fitnesstracker.database.FitnessTrackerUserContract;
import com.oculus.modules.codegen.FitnessDataModule;

public class FitnessDataConverter {
    public static Cursor getCursorFromDailySummaryData(FitnessDataModule.DailySummaryData dailySummaryData) {
        MatrixCursor matrixCursor = new MatrixCursor(new String[]{FitnessTrackerMoveContract.Days.ACTIVE_TIME, FitnessTrackerUserContract.Goals.ACTIVE_TIME_GOAL, FitnessTrackerMoveContract.Days.ACTIVE_TIME_PROGRESS, "calories", FitnessTrackerUserContract.Goals.CALORIES_GOAL, FitnessTrackerMoveContract.Days.CALORIES_PROGRESS, "startOfDay"});
        matrixCursor.newRow().add(FitnessTrackerMoveContract.Days.ACTIVE_TIME, Double.valueOf(dailySummaryData.activeTime)).add(FitnessTrackerUserContract.Goals.ACTIVE_TIME_GOAL, dailySummaryData.activeTimeGoal).add(FitnessTrackerMoveContract.Days.ACTIVE_TIME_PROGRESS, dailySummaryData.activeTimeProgress).add("calories", Double.valueOf(dailySummaryData.calories)).add(FitnessTrackerUserContract.Goals.CALORIES_GOAL, dailySummaryData.caloriesGoal).add(FitnessTrackerMoveContract.Days.CALORIES_PROGRESS, dailySummaryData.caloriesProgress).add("startOfDay", Double.valueOf(dailySummaryData.startOfDay));
        return matrixCursor;
    }
}
