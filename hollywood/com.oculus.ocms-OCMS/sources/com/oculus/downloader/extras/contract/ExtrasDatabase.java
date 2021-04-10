package com.oculus.downloader.extras.contract;

public class ExtrasDatabase {
    public static final String SQL_TABLE_CREATE = ("CREATE TABLE download_extras (" + Columns.DOWNLOAD_ID + " " + ColumnsTypes.INTEGER + ", " + Columns.EXTRA_KEY + " " + ColumnsTypes.TEXT + ", " + Columns.EXTRA_VALUE + " " + ColumnsTypes.TEXT + ", PRIMARY KEY (" + Columns.DOWNLOAD_ID + ", " + Columns.EXTRA_KEY + "))");
    public static final String SQL_TABLE_DROP_IF_EXIST = "DROP TABLE IF EXISTS download_extras";
    public static final String TABLE_DOWNLOAD_EXTRA = "download_extras";

    public static class Columns {
        public static String DOWNLOAD_ID = "download_id";
        public static String EXTRA_KEY = "key";
        public static String EXTRA_VALUE = "value";
    }

    public static class ColumnsTypes {
        public static String INTEGER = "INTEGER";
        public static String TEXT = "TEXT";
    }
}
