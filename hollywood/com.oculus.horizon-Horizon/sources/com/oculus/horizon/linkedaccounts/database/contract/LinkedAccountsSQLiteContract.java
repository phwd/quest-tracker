package com.oculus.horizon.linkedaccounts.database.contract;

public class LinkedAccountsSQLiteContract {
    public static final String DATABASE_NAME = "oculus-linked-accounts";
    public static final int DATABASE_VERSION = 2;
    public static final String SQL_SELECTION_SERVICE_PROVIDER = "service_provider = ?";
    public static final String SQL_TABLE_COPY = "INSERT INTO accounts SELECT _id, user_id, access_token, service_provider FROM accounts_temp";
    public static final String SQL_TABLE_CREATE = "CREATE TABLE accounts(_id INTEGER PRIMARY KEY AUTOINCREMENT, user_id TEXT NOT NULL, token TEXT, service_provider TEXT NOT NULL COLLATE NOCASE UNIQUE)";
    public static final String SQL_TABLE_DROP = "DROP TABLE accounts_temp";
    public static final String SQL_TABLE_RENAME = "ALTER TABLE accounts RENAME TO accounts_temp";
    public static final String TABLE_NAME = "accounts";
    public static final String TABLE_NAME_TEMP = "accounts_temp";

    public static final class Columns {
        public static final String SERVICE_PROVIDER = "service_provider";
        public static final String TOKEN = "token";
        public static final String USER_ID = "user_id";
        public static final String _ID = "_id";
    }
}
