package com.oculus.localmedia.database;

public class LocalMediaContract {

    public static class ExtrasTable {
        public static final String SQL_TABLE_CREATE = "CREATE TABLE localmedia_metadata (path TEXT, last_modified INTEGER, type INTEGER, is_360 BOOLEAN, is_180 BOOLEAN, is_3d BOOLEAN, is_top_bottom BOOLEAN, orientation INTEGER, audio BOOLEAN, PRIMARY KEY (path))";
        public static final String SQL_TABLE_DROP_IF_EXIST = "DROP TABLE IF EXISTS localmedia_metadata";
        public static final String TABLE_LOCALMEDIA_METADATA = "localmedia_metadata";

        public static class Columns {
            public static final String HAS_AUDIO = "audio";
            public static final String IS_180 = "is_180";
            public static final String IS_360 = "is_360";
            public static final String IS_3D = "is_3d";
            public static final String IS_TB = "is_top_bottom";
            public static final String LAST_MODIFIED = "last_modified";
            public static final String ORIENTATION = "orientation";
            public static final String PATH = "path";
            public static final String TYPE = "type";
        }

        public static class ColumnsTypes {
            public static final String BOOLEAN = "BOOLEAN";
            public static final String INTEGER = "INTEGER";
            public static final String TEXT = "TEXT";
        }
    }
}
