package com.oculus.library.utils.app;

import android.database.MatrixCursor;
import com.oculus.appmanager.assets.database.assetcontract.AssetContract;
import com.oculus.library.model.AssetInfo;
import java.util.Collection;

public class AssetsCursor extends MatrixCursor {
    private static final String[] COLUMNS = {AssetContract.AssetTableColumns.INSTALLED_BINARY_ID, "package_name", "asset_id", "asset_name", AssetContract.AssetTableColumns.ASSET_TYPE, AssetContract.AssetTableColumns.ASSET_METADATA, AssetContract.AssetTableColumns.ASSET_URI, AssetContract.AssetTableColumns.ASSET_SIZE, AssetContract.AssetTableColumns.ASSET_ENTITLEMENT, AssetContract.AssetTableColumns.REQUIRED, AssetContract.AssetTableColumns.LANGUAGE_CODE, AssetContract.AssetTableColumns.LANGUAGE_ENGLISH_NAME, AssetContract.AssetTableColumns.LANGUAGE_NATIVE_NAME};

    public AssetsCursor(int i) {
        super(COLUMNS, i);
    }

    public void fill(Collection<AssetInfo> collection) {
        for (AssetInfo assetInfo : collection) {
            addRow(new Object[]{assetInfo.installedBinaryID, assetInfo.packageName, assetInfo.id, assetInfo.name, assetInfo.type, assetInfo.metadata, assetInfo.uri, Long.valueOf(assetInfo.size), assetInfo.entitlement, Integer.valueOf(assetInfo.isRequired ? 1 : 0), assetInfo.languageCode, assetInfo.languageEnglishName, assetInfo.languageNativeName});
        }
    }
}
