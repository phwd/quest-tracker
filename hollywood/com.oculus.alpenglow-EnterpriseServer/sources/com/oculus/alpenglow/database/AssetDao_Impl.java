package com.oculus.alpenglow.database;

import X.AbstractC03360cA;
import X.AbstractC03430cM;
import X.AnonymousClass0Fr;
import X.AnonymousClass0G7;
import X.C03410cF;
import android.database.Cursor;
import com.oculus.alpenglow.logging.LoggerConstants;
import java.util.ArrayList;
import java.util.List;

public final class AssetDao_Impl implements AssetDao {
    public final AnonymousClass0Fr __db;
    public final AbstractC03430cM<Asset> __insertionAdapterOfAsset;

    @Override // com.oculus.alpenglow.database.AssetDao
    public final List<Asset> A2t() {
        C03410cF A00 = C03410cF.A00("SELECT * FROM asset", 0);
        this.__db.A04();
        Cursor A002 = this.__db.A00(A00);
        try {
            int A003 = AnonymousClass0G7.A00(A002, "asset_id");
            int A004 = AnonymousClass0G7.A00(A002, LoggerConstants.APP_ID_KEY);
            int A005 = AnonymousClass0G7.A00(A002, "last_updated_on_backend");
            ArrayList arrayList = new ArrayList(A002.getCount());
            while (A002.moveToNext()) {
                Asset asset = new Asset();
                asset.assetId = A002.getString(A003);
                asset.appId = A002.getString(A004);
                asset.lastUpdatedOnBackend = A002.getLong(A005);
                arrayList.add(asset);
            }
            return arrayList;
        } finally {
            A002.close();
            A00.A01();
        }
    }

    @Override // com.oculus.alpenglow.database.AssetDao
    public final void A5J(Asset... assetArr) {
        this.__db.A04();
        this.__db.A05();
        try {
            this.__insertionAdapterOfAsset.A04(assetArr);
            this.__db.A07();
        } finally {
            this.__db.A06();
        }
    }

    public AssetDao_Impl(AnonymousClass0Fr r2) {
        this.__db = r2;
        this.__insertionAdapterOfAsset = new AbstractC03430cM<Asset>(r2) {
            /* class com.oculus.alpenglow.database.AssetDao_Impl.AnonymousClass1 */

            @Override // X.AbstractC01280Fx
            public final String A01() {
                return "INSERT OR REPLACE INTO `asset` (`asset_id`,`app_id`,`last_updated_on_backend`) VALUES (?,?,?)";
            }

            /* JADX DEBUG: Method arguments types fixed to match base method, original types: [X.0cA, java.lang.Object] */
            @Override // X.AbstractC03430cM
            public final void A03(AbstractC03360cA r4, Asset asset) {
                Asset asset2 = asset;
                String str = asset2.assetId;
                if (str == null) {
                    r4.A1U(1);
                } else {
                    r4.A1W(1, str);
                }
                String str2 = asset2.appId;
                if (str2 == null) {
                    r4.A1U(2);
                } else {
                    r4.A1W(2, str2);
                }
                r4.A1Q(3, asset2.lastUpdatedOnBackend);
            }
        };
    }
}
