package com.oculus.downloader.extras.contract;

public class ExtrasDatabase {
    public static final String SQL_TABLE_CREATE = "CREATE TABLE download_extras (download_id INTEGER, key TEXT, value TEXT, PRIMARY KEY (download_id, key))";
    public static final String SQL_TABLE_DROP_IF_EXIST = "DROP TABLE IF EXISTS download_extras";
    public static final String TABLE_DOWNLOAD_EXTRA = "download_extras";

    public static class Columns {
        public static final String DOWNLOAD_ID = "download_id";
        public static final String EXTRA_KEY = "key";
        public static final String EXTRA_VALUE = "value";
    }

    public static class ColumnsTypes {
        public static final String INTEGER = "INTEGER";
        public static final String TEXT = "TEXT";
    }
}
