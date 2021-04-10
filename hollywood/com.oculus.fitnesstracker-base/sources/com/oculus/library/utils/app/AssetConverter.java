package com.oculus.library.utils.app;

import android.database.Cursor;
import android.util.Log;
import com.oculus.library.model.AssetInfo;
import java.util.ArrayList;
import java.util.HashMap;

public class AssetConverter {
    private static final String TAG = "AssetConverter";
    public String[] REQUIRED_COLUMNS = {"installed_binary_id", "asset_id", "asset_name", "package_name", "asset_entitlement", "asset_type", "asset_metadata", "asset_uri", "asset_size", "required", "language_code", "language_english_name", "language_native_name"};

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
        return new AssetInfo(cursor.getString(((Integer) hashMap.get("installed_binary_id")).intValue()), cursor.getString(((Integer) hashMap.get("package_name")).intValue()), cursor.getString(((Integer) hashMap.get("asset_id")).intValue()), cursor.getString(((Integer) hashMap.get("asset_name")).intValue()), cursor.getString(((Integer) hashMap.get("asset_type")).intValue()), cursor.getString(((Integer) hashMap.get("asset_metadata")).intValue()), cursor.getString(((Integer) hashMap.get("asset_uri")).intValue()), cursor.getLong(((Integer) hashMap.get("asset_size")).intValue()), cursor.getString(((Integer) hashMap.get("asset_entitlement")).intValue()), cursor.getInt(((Integer) hashMap.get("required")).intValue()) == 1, cursor.getString(((Integer) hashMap.get("language_code")).intValue()), cursor.getString(((Integer) hashMap.get("language_english_name")).intValue()), cursor.getString(((Integer) hashMap.get("language_native_name")).intValue()));
    }
}
