package com.oculus.appmanager.downloader.contract;

public class SizePriorityDownloadContract {

    public static class ExtrasTable {
        public static final String SQL_TABLE_CREATE = "CREATE TABLE download_size_priority (package_name TEXT NOT NULL, key TEXT NOT NULL, value TEXT NOT NULL, PRIMARY KEY (package_name, key))";
        public static final String SQL_TABLE_DROP_IF_EXIST = "DROP TABLE IF EXISTS download_size_priority";
        public static final String TABLE_DOWNLOAD_SIZE_PRIORITY = "download_size_priority";

        public static class Columns {
            public static final String EXTRA_KEY = "key";
            public static final String EXTRA_PACKAGE_NAME = "package_name";
            public static final String EXTRA_VALUE = "value";
        }

        public static class ColumnsTypes {
            public static final String TEXT_NOT_NULL = "TEXT NOT NULL";
        }
    }
}
