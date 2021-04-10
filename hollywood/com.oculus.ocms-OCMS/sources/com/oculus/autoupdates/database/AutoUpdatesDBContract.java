package com.oculus.autoupdates.database;

public class AutoUpdatesDBContract {

    public static final class AutoUpdatesDBTable {
        public static final String NAME = "autoupdates";
        public static final String SQL_CREATE = ("CREATE TABLE autoupdates (package_name " + COLS_TYPES.TEXT + ", " + COLS.VERSION_CODE + " " + COLS_TYPES.INTEGER + ", " + COLS.ATTEMPTS + " " + COLS_TYPES.INTEGER + ", PRIMARY KEY (" + "package_name" + ", " + COLS.VERSION_CODE + "))");

        public static final class COLS {
            public static final String ATTEMPTS = "attempts";
            public static final String PACKAGE_NAME = "package_name";
            public static final String VERSION_CODE = "version_code";
        }

        public static class COLS_TYPES {
            public static String INTEGER = "INTEGER";
            public static String TEXT = "TEXT";
        }
    }
}
