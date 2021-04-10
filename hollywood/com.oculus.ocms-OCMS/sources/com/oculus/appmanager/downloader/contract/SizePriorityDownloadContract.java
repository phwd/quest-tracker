package com.oculus.appmanager.downloader.contract;

public class SizePriorityDownloadContract {

    public static class ExtrasTable {
        public static final String SQL_TABLE_CREATE = ("CREATE TABLE download_size_priority (" + Columns.EXTRA_PACKAGE_NAME + " " + ColumnsTypes.TEXT_NOT_NULL + ", " + Columns.EXTRA_KEY + " " + ColumnsTypes.TEXT_NOT_NULL + ", " + Columns.EXTRA_VALUE + " " + ColumnsTypes.TEXT_NOT_NULL + ", PRIMARY KEY (" + Columns.EXTRA_PACKAGE_NAME + ", " + Columns.EXTRA_KEY + "))");
        public static final String SQL_TABLE_DROP_IF_EXIST = "DROP TABLE IF EXISTS download_size_priority";
        public static final String TABLE_DOWNLOAD_SIZE_PRIORITY = "download_size_priority";

        public static class Columns {
            public static String EXTRA_KEY = "key";
            public static String EXTRA_PACKAGE_NAME = "package_name";
            public static String EXTRA_VALUE = "value";
        }

        public static class ColumnsTypes {
            public static String TEXT_NOT_NULL = "TEXT NOT NULL";
        }
    }
}
