package com.oculus.library.utils.app;

import android.database.MatrixCursor;
import com.oculus.library.model.AssetInfo;
import java.util.Collection;

public class AssetsCursor extends MatrixCursor {
    private static final String[] COLUMNS = {"installed_binary_id", "package_name", "asset_id", "asset_name", "asset_type", "asset_metadata", "asset_uri", "asset_size", "asset_entitlement", "required", "language_code", "language_english_name", "language_native_name"};

    public AssetsCursor(int i) {
        super(COLUMNS, i);
    }

    public void fill(Collection<AssetInfo> collection) {
        for (AssetInfo assetInfo : collection) {
            addRow(new Object[]{assetInfo.installedBinaryID, assetInfo.packageName, assetInfo.id, assetInfo.name, assetInfo.type, assetInfo.metadata, assetInfo.uri, Long.valueOf(assetInfo.size), assetInfo.entitlement, Integer.valueOf(assetInfo.isRequired ? 1 : 0), assetInfo.languageCode, assetInfo.languageEnglishName, assetInfo.languageNativeName});
        }
    }
}
