package com.oculus.alpenglow.database;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import com.oculus.alpenglow.logging.LoggerConstants;

@Entity(primaryKeys = {"asset_id"}, tableName = "asset")
public class Asset {
    @ColumnInfo(name = LoggerConstants.APP_ID_KEY)
    public String appId;
    @NonNull
    @ColumnInfo(name = "asset_id")
    public String assetId;
    @ColumnInfo(name = "last_updated_on_backend")
    public long lastUpdatedOnBackend;
}
