package com.oculus.fitnesstracker.database;

import android.provider.BaseColumns;

public class FitnessTrackerMoveContract {

    public static class Calories implements BaseColumns {
        public static final String CALORIES = "calories";
        public static final String IS_ACTIVE = "isActive";
        public static final String SESSION_ID = "sessionId";
        public static final String SET_DATE = "setDate";
        public static final String TABLE_NAME = "calories";
    }

    public static class Days implements BaseColumns {
        public static final String ACTIVE_TIME = "activeTime";
        public static final String ACTIVE_TIME_PROGRESS = "activeTimeProgress";
        public static final String CALORIES = "calories";
        public static final String CALORIES_PROGRESS = "caloriesProgress";
        public static final String DATE = "date";
        public static final String TABLE_NAME = "days";
    }

    public static class Session implements BaseColumns {
        public static final String ACTIVITY = "activity";
        public static final String PACKAGE_NAME = "packageName";
        public static final String START_DATE = "startDate";
        public static final String TABLE_NAME = "session";
    }

    private FitnessTrackerMoveContract() {
    }
}
