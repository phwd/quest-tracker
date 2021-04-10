package com.oculus.alpenglow.database;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.TypeConverters;
import com.oculus.alpenglow.database.AppSource;
import com.oculus.alpenglow.logging.LoggerConstants;
import com.oculus.library.model.AppStatus;

@Entity(primaryKeys = {LoggerConstants.APP_ID_KEY}, tableName = "application")
public class Application {
    @NonNull
    @ColumnInfo(name = LoggerConstants.APP_ID_KEY)
    public String appId;
    @TypeConverters({AppSource.AppSourceConverter.class})
    @ColumnInfo(name = "app_source")
    public AppSource appSource;
    @ColumnInfo(name = "downloaded_bytes")
    public long downloadedBytes;
    @ColumnInfo(name = "last_updated_on_backend")
    public long lastUpdatedOnBackend;
    @ColumnInfo(name = "package_name")
    public String packageName;
    @TypeConverters({AppStatusConverter.class})
    @ColumnInfo(name = "status")
    public AppStatus status;
    @ColumnInfo(name = "total_bytes")
    public long totalBytes;

    public static class AppStatusConverter {
    }
}
