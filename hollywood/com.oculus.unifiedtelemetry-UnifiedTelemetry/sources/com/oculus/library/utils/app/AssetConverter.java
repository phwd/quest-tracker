package com.oculus.library.utils.app;

import com.oculus.appmanager.assets.database.assetcontract.AssetContract;

public class AssetConverter {
    public static final String TAG = "AssetConverter";
    public String[] REQUIRED_COLUMNS = {AssetContract.AssetTableColumns.INSTALLED_BINARY_ID, "asset_id", "asset_name", "package_name", AssetContract.AssetTableColumns.ASSET_ENTITLEMENT, AssetContract.AssetTableColumns.ASSET_TYPE, AssetContract.AssetTableColumns.ASSET_METADATA, AssetContract.AssetTableColumns.ASSET_URI, AssetContract.AssetTableColumns.ASSET_SIZE, AssetContract.AssetTableColumns.REQUIRED, AssetContract.AssetTableColumns.LANGUAGE_CODE, AssetContract.AssetTableColumns.LANGUAGE_ENGLISH_NAME, AssetContract.AssetTableColumns.LANGUAGE_NATIVE_NAME};
}
