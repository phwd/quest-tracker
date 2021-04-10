package com.oculus.alpenglow.database;

import androidx.room.Embedded;
import androidx.room.Relation;
import com.oculus.alpenglow.logging.LoggerConstants;
import java.util.List;

public class AssetsForApplication {
    @Embedded
    public Application application;
    @Relation(entityColumn = LoggerConstants.APP_ID_KEY, parentColumn = LoggerConstants.APP_ID_KEY)
    public List<Asset> assets;
}
