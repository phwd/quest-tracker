package com.oculus.library.utils.app;

import X.AnonymousClass006;
import android.database.Cursor;
import android.util.Log;
import com.oculus.appmanager.assets.database.assetcontract.AssetContract;
import com.oculus.library.model.AssetInfo;
import java.util.ArrayList;
import java.util.HashMap;

public class AssetConverter {
    public static final String TAG = "AssetConverter";
    public String[] REQUIRED_COLUMNS = {AssetContract.AssetTableColumns.INSTALLED_BINARY_ID, "asset_id", "asset_name", "package_name", AssetContract.AssetTableColumns.ASSET_ENTITLEMENT, AssetContract.AssetTableColumns.ASSET_TYPE, AssetContract.AssetTableColumns.ASSET_METADATA, AssetContract.AssetTableColumns.ASSET_URI, AssetContract.AssetTableColumns.ASSET_SIZE, AssetContract.AssetTableColumns.REQUIRED, AssetContract.AssetTableColumns.LANGUAGE_CODE, AssetContract.AssetTableColumns.LANGUAGE_ENGLISH_NAME, AssetContract.AssetTableColumns.LANGUAGE_NATIVE_NAME};

    public AssetInfo[] extractFromCursor(Cursor cursor) {
        if (cursor == null) {
            Log.w(TAG, "Cannot load database due to null cursor");
        } else if (!cursor.moveToFirst()) {
            Log.w(TAG, "Unable to move cursor to the first position");
            cursor.close();
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
        return new AssetInfo[0];
    }

    public AssetInfo extractNextAssetFromCursor(Cursor cursor) {
        if (cursor != null) {
            HashMap hashMap = new HashMap();
            String[] strArr = this.REQUIRED_COLUMNS;
            for (String str : strArr) {
                int columnIndex = cursor.getColumnIndex(str);
                if (columnIndex == -1) {
                    Log.e(TAG, AnonymousClass006.A07("missing column: ", str));
                } else {
                    hashMap.put(str, Integer.valueOf(columnIndex));
                }
            }
            String string = cursor.getString(((Number) hashMap.get(AssetContract.AssetTableColumns.INSTALLED_BINARY_ID)).intValue());
            String string2 = cursor.getString(((Number) hashMap.get("package_name")).intValue());
            String string3 = cursor.getString(((Number) hashMap.get("asset_id")).intValue());
            String string4 = cursor.getString(((Number) hashMap.get("asset_name")).intValue());
            String string5 = cursor.getString(((Number) hashMap.get(AssetContract.AssetTableColumns.ASSET_TYPE)).intValue());
            String string6 = cursor.getString(((Number) hashMap.get(AssetContract.AssetTableColumns.ASSET_METADATA)).intValue());
            String string7 = cursor.getString(((Number) hashMap.get(AssetContract.AssetTableColumns.ASSET_URI)).intValue());
            long j = cursor.getLong(((Number) hashMap.get(AssetContract.AssetTableColumns.ASSET_SIZE)).intValue());
            String string8 = cursor.getString(((Number) hashMap.get(AssetContract.AssetTableColumns.ASSET_ENTITLEMENT)).intValue());
            boolean z = false;
            if (cursor.getInt(((Number) hashMap.get(AssetContract.AssetTableColumns.REQUIRED)).intValue()) == 1) {
                z = true;
            }
            return new AssetInfo(string, string2, string3, string4, string5, string6, string7, j, string8, z, cursor.getString(((Number) hashMap.get(AssetContract.AssetTableColumns.LANGUAGE_CODE)).intValue()), cursor.getString(((Number) hashMap.get(AssetContract.AssetTableColumns.LANGUAGE_ENGLISH_NAME)).intValue()), cursor.getString(((Number) hashMap.get(AssetContract.AssetTableColumns.LANGUAGE_NATIVE_NAME)).intValue()));
        }
        return null;
    }
}
