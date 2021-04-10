package com.oculus.library.utils.app;

import android.database.Cursor;
import android.util.Log;
import com.oculus.appmanager.assets.database.assetcontract.AssetContract;
import com.oculus.library.model.AssetInfo;
import java.util.ArrayList;
import java.util.HashMap;

public class AssetConverter {
    private static final String TAG = "AssetConverter";
    public String[] REQUIRED_COLUMNS = {AssetContract.AssetTableColumns.INSTALLED_BINARY_ID, "asset_id", "asset_name", "package_name", AssetContract.AssetTableColumns.ASSET_ENTITLEMENT, AssetContract.AssetTableColumns.ASSET_TYPE, AssetContract.AssetTableColumns.ASSET_METADATA, AssetContract.AssetTableColumns.ASSET_URI, AssetContract.AssetTableColumns.ASSET_SIZE, AssetContract.AssetTableColumns.REQUIRED, AssetContract.AssetTableColumns.LANGUAGE_CODE, AssetContract.AssetTableColumns.LANGUAGE_ENGLISH_NAME, AssetContract.AssetTableColumns.LANGUAGE_NATIVE_NAME};

    public AssetInfo[] extractFromCursor(Cursor cursor) {
        if (cursor == null) {
            Log.w(TAG, "Cannot load database due to null cursor");
            return new AssetInfo[0];
        } else if (!cursor.moveToFirst()) {
            Log.w(TAG, "Unable to move cursor to the first position");
            cursor.close();
            return new AssetInfo[0];
        } else {
            ArrayList arrayList = new ArrayList();
            do {
                AssetInfo extractNextAssetFromCursor = extractNextAssetFromCursor(cursor);
                if (extractNextAssetFromCursor != null) {
                    arrayList.add(extractNextAssetFromCursor);
                } else {
                    Log.w(TAG, "Got unexpected null from cursor app extraction");
                }
            } while (cursor.moveToNext());
            return (AssetInfo[]) arrayList.toArray(new AssetInfo[0]);
        }
    }

    public AssetInfo extractNextAssetFromCursor(Cursor cursor) {
        if (cursor == null) {
            return null;
        }
        HashMap hashMap = new HashMap();
        String[] strArr = this.REQUIRED_COLUMNS;
        for (String str : strArr) {
            int columnIndex = cursor.getColumnIndex(str);
            if (columnIndex == -1) {
                Log.e(TAG, "missing column: " + str);
                return null;
            }
            hashMap.put(str, Integer.valueOf(columnIndex));
        }
        return new AssetInfo(cursor.getString(((Integer) hashMap.get(AssetContract.AssetTableColumns.INSTALLED_BINARY_ID)).intValue()), cursor.getString(((Integer) hashMap.get("package_name")).intValue()), cursor.getString(((Integer) hashMap.get("asset_id")).intValue()), cursor.getString(((Integer) hashMap.get("asset_name")).intValue()), cursor.getString(((Integer) hashMap.get(AssetContract.AssetTableColumns.ASSET_TYPE)).intValue()), cursor.getString(((Integer) hashMap.get(AssetContract.AssetTableColumns.ASSET_METADATA)).intValue()), cursor.getString(((Integer) hashMap.get(AssetContract.AssetTableColumns.ASSET_URI)).intValue()), cursor.getLong(((Integer) hashMap.get(AssetContract.AssetTableColumns.ASSET_SIZE)).intValue()), cursor.getString(((Integer) hashMap.get(AssetContract.AssetTableColumns.ASSET_ENTITLEMENT)).intValue()), cursor.getInt(((Integer) hashMap.get(AssetContract.AssetTableColumns.REQUIRED)).intValue()) == 1, cursor.getString(((Integer) hashMap.get(AssetContract.AssetTableColumns.LANGUAGE_CODE)).intValue()), cursor.getString(((Integer) hashMap.get(AssetContract.AssetTableColumns.LANGUAGE_ENGLISH_NAME)).intValue()), cursor.getString(((Integer) hashMap.get(AssetContract.AssetTableColumns.LANGUAGE_NATIVE_NAME)).intValue()));
    }
}
