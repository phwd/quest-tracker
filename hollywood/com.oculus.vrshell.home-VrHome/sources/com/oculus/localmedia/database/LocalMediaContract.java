package com.oculus.localmedia.database;

public class LocalMediaContract {

    public static class ExtrasTable {
        public static final String SQL_TABLE_CREATE = ("CREATE TABLE localmedia_metadata (" + Columns.PATH + " " + ColumnsTypes.TEXT + ", " + Columns.LAST_MODIFIED + " " + ColumnsTypes.INTEGER + ", " + Columns.TYPE + " " + ColumnsTypes.INTEGER + ", " + Columns.IS_360 + " " + ColumnsTypes.BOOLEAN + ", " + Columns.IS_180 + " " + ColumnsTypes.BOOLEAN + ", " + Columns.IS_3D + " " + ColumnsTypes.BOOLEAN + ", " + Columns.IS_TB + " " + ColumnsTypes.BOOLEAN + ", " + Columns.ORIENTATION + " " + ColumnsTypes.INTEGER + ", " + Columns.HAS_AUDIO + " " + ColumnsTypes.BOOLEAN + ", " + "PRIMARY KEY (" + Columns.PATH + "))");
        public static final String SQL_TABLE_DROP_IF_EXIST = "DROP TABLE IF EXISTS localmedia_metadata";
        public static final String TABLE_LOCALMEDIA_METADATA = "localmedia_metadata";

        public static class Columns {
            public static String HAS_AUDIO = "audio";
            public static String IS_180 = "is_180";
            public static String IS_360 = "is_360";
            public static String IS_3D = "is_3d";
            public static String IS_TB = "is_top_bottom";
            public static String LAST_MODIFIED = "last_modified";
            public static String ORIENTATION = "orientation";
            public static String PATH = "path";
            public static String TYPE = "type";
        }

        public static class ColumnsTypes {
            public static String BOOLEAN = "BOOLEAN";
            public static String INTEGER = "INTEGER";
            public static String TEXT = "TEXT";
        }
    }
}
