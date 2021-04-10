package com.oculus.library.utils.app;

import android.database.MatrixCursor;
import com.oculus.appmanager.assets.database.assetcontract.AssetContract;
import com.oculus.library.model.AssetInfo;
import java.util.Collection;

public class AssetsCursor extends MatrixCursor {
    private static final String[] COLUMNS = {AssetContract.AssetTableColumns.INSTALLED_BINARY_ID, "package_name", "asset_id", "asset_name", AssetContract.AssetTableColumns.ASSET_TYPE, AssetContract.AssetTableColumns.ASSET_METADATA, AssetContract.AssetTableColumns.ASSET_URI, AssetContract.AssetTableColumns.ASSET_SIZE, AssetContract.AssetTableColumns.ASSET_ENTITLEMENT, AssetContract.AssetTableColumns.REQUIRED, AssetContract.AssetTableColumns.LANGUAGE_CODE, AssetContract.AssetTableColumns.LANGUAGE_ENGLISH_NAME, AssetContract.AssetTableColumns.LANGUAGE_NATIVE_NAME};

    public AssetsCursor(int size) {
        super(COLUMNS, size);
    }

    public void fill(Collection<AssetInfo> assets) {
        for (AssetInfo asset : assets) {
            Object[] values = new Object[13];
            values[0] = asset.installedBinaryID;
            values[1] = asset.packageName;
            values[2] = asset.id;
            values[3] = asset.name;
            values[4] = asset.type;
            values[5] = asset.metadata;
            values[6] = asset.uri;
            values[7] = Long.valueOf(asset.size);
            values[8] = asset.entitlement;
            values[9] = Integer.valueOf(asset.isRequired ? 1 : 0);
            values[10] = asset.languageCode;
            values[11] = asset.languageEnglishName;
            values[12] = asset.languageNativeName;
            addRow(values);
        }
    }
}
