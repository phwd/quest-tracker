package com.oculus.library.utils.app;

import android.database.MatrixCursor;
import com.oculus.appmanager.assets.database.assetcontract.AssetContract;

public class AssetsCursor extends MatrixCursor {
    public static final String[] COLUMNS = {AssetContract.AssetTableColumns.INSTALLED_BINARY_ID, "package_name", "asset_id", "asset_name", AssetContract.AssetTableColumns.ASSET_TYPE, AssetContract.AssetTableColumns.ASSET_METADATA, AssetContract.AssetTableColumns.ASSET_URI, AssetContract.AssetTableColumns.ASSET_SIZE, AssetContract.AssetTableColumns.ASSET_ENTITLEMENT, AssetContract.AssetTableColumns.REQUIRED, AssetContract.AssetTableColumns.LANGUAGE_CODE, AssetContract.AssetTableColumns.LANGUAGE_ENGLISH_NAME, AssetContract.AssetTableColumns.LANGUAGE_NATIVE_NAME};
}
