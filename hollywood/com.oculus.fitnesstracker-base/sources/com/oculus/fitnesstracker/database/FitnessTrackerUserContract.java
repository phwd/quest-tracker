package com.oculus.fitnesstracker.database;

import android.provider.BaseColumns;

public class FitnessTrackerUserContract {

    public static class Goals implements BaseColumns {
        public static final String ACTIVE_TIME_GOAL = "activeTimeGoal";
        public static final String CALORIES_GOAL = "caloriesGoal";
        public static final String CREATION_TIME = "creationTime";
        public static final String TABLE_NAME = "goals";
    }

    public static class Metrics implements BaseColumns {
        public static final String CREATION_TIME = "creationTime";
        public static final String HEIGHT = "height";
        public static final String TABLE_NAME = "metrics";
        public static final String WEIGHT = "weight";
    }

    public static class Timezone implements BaseColumns {
        public static final String SET_DATE = "setDate";
        public static final String TABLE_NAME = "timezone";
        public static final String TIMEZONE = "timezone";
    }

    public static class User implements BaseColumns {
        public static final String CALORIES_UNIT = "caloriesUnit";
        public static final String CREATION_TIME = "creationTime";
        public static final String DATE_OF_BIRTH = "dateOfBirth";
        public static final String HEIGHT_UNIT = "heightUnit";
        public static final String SEX = "sex";
        public static final String TABLE_NAME = "user";
        public static final String WEIGHT_UNIT = "weightUnit";
    }

    private FitnessTrackerUserContract() {
    }
}
