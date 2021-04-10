package com.oculus.alpenglow.database;

import X.AbstractC01280Fx;
import X.AbstractC03360cA;
import X.AbstractC03430cM;
import X.AbstractC03440cN;
import X.AnonymousClass006;
import X.AnonymousClass0Fr;
import X.AnonymousClass0G7;
import X.C03410cF;
import android.database.Cursor;
import androidx.annotation.NonNull;
import com.oculus.alpenglow.logging.LoggerConstants;
import com.oculus.library.model.AppStatus;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public final class ApplicationDao_Impl implements ApplicationDao {
    public final AnonymousClass0Fr __db;
    public final AbstractC03440cN<Application> __deletionAdapterOfApplication;
    public final AbstractC03440cN<Asset> __deletionAdapterOfAsset;
    public final AbstractC03430cM<Application> __insertionAdapterOfApplication;
    public final AbstractC01280Fx __preparedStmtOfSetAppInstalled;
    public final AbstractC01280Fx __preparedStmtOfUpdateApplication;
    public final AbstractC01280Fx __preparedStmtOfUpdateDownloadedBytes;
    public final AbstractC01280Fx __preparedStmtOfUpdateDownloadedBytes_1;
    public final AbstractC01280Fx __preparedStmtOfUpdateStatus;

    @Override // com.oculus.alpenglow.database.ApplicationDao
    public final List<Application> A2t() {
        C03410cF A00 = C03410cF.A00("SELECT * FROM application", 0);
        this.__db.A04();
        Cursor A002 = this.__db.A00(A00);
        try {
            int A003 = AnonymousClass0G7.A00(A002, LoggerConstants.APP_ID_KEY);
            int A004 = AnonymousClass0G7.A00(A002, "package_name");
            int A005 = AnonymousClass0G7.A00(A002, "last_updated_on_backend");
            int A006 = AnonymousClass0G7.A00(A002, "status");
            int A007 = AnonymousClass0G7.A00(A002, "downloaded_bytes");
            int A008 = AnonymousClass0G7.A00(A002, "total_bytes");
            int A009 = AnonymousClass0G7.A00(A002, "app_source");
            ArrayList arrayList = new ArrayList(A002.getCount());
            while (A002.moveToNext()) {
                Application application = new Application();
                application.appId = A002.getString(A003);
                application.packageName = A002.getString(A004);
                application.lastUpdatedOnBackend = A002.getLong(A005);
                application.status = AppStatus.values()[A002.getInt(A006)];
                application.downloadedBytes = A002.getLong(A007);
                application.totalBytes = A002.getLong(A008);
                application.appSource = AppSource.values()[A002.getInt(A009)];
                arrayList.add(application);
            }
            return arrayList;
        } finally {
            A002.close();
            A00.A01();
        }
    }

    @Override // com.oculus.alpenglow.database.ApplicationDao
    public final Application A2w(String str) {
        C03410cF A00 = C03410cF.A00("SELECT * FROM application WHERE app_id = ?", 1);
        if (str == null) {
            A00.A1U(1);
        } else {
            A00.A1W(1, str);
        }
        this.__db.A04();
        Application application = null;
        Cursor A002 = this.__db.A00(A00);
        try {
            int A003 = AnonymousClass0G7.A00(A002, LoggerConstants.APP_ID_KEY);
            int A004 = AnonymousClass0G7.A00(A002, "package_name");
            int A005 = AnonymousClass0G7.A00(A002, "last_updated_on_backend");
            int A006 = AnonymousClass0G7.A00(A002, "status");
            int A007 = AnonymousClass0G7.A00(A002, "downloaded_bytes");
            int A008 = AnonymousClass0G7.A00(A002, "total_bytes");
            int A009 = AnonymousClass0G7.A00(A002, "app_source");
            if (A002.moveToFirst()) {
                application = new Application();
                application.appId = A002.getString(A003);
                application.packageName = A002.getString(A004);
                application.lastUpdatedOnBackend = A002.getLong(A005);
                application.status = AppStatus.values()[A002.getInt(A006)];
                application.downloadedBytes = A002.getLong(A007);
                application.totalBytes = A002.getLong(A008);
                application.appSource = AppSource.values()[A002.getInt(A009)];
            }
            return application;
        } finally {
            A002.close();
            A00.A01();
        }
    }

    @Override // com.oculus.alpenglow.database.ApplicationDao
    public final List<Application> A2y(String str) {
        C03410cF A00 = C03410cF.A00("SELECT * FROM application WHERE package_name = ?", 1);
        if (str == null) {
            A00.A1U(1);
        } else {
            A00.A1W(1, str);
        }
        this.__db.A04();
        Cursor A002 = this.__db.A00(A00);
        try {
            int A003 = AnonymousClass0G7.A00(A002, LoggerConstants.APP_ID_KEY);
            int A004 = AnonymousClass0G7.A00(A002, "package_name");
            int A005 = AnonymousClass0G7.A00(A002, "last_updated_on_backend");
            int A006 = AnonymousClass0G7.A00(A002, "status");
            int A007 = AnonymousClass0G7.A00(A002, "downloaded_bytes");
            int A008 = AnonymousClass0G7.A00(A002, "total_bytes");
            int A009 = AnonymousClass0G7.A00(A002, "app_source");
            ArrayList arrayList = new ArrayList(A002.getCount());
            while (A002.moveToNext()) {
                Application application = new Application();
                application.appId = A002.getString(A003);
                application.packageName = A002.getString(A004);
                application.lastUpdatedOnBackend = A002.getLong(A005);
                application.status = AppStatus.values()[A002.getInt(A006)];
                application.downloadedBytes = A002.getLong(A007);
                application.totalBytes = A002.getLong(A008);
                application.appSource = AppSource.values()[A002.getInt(A009)];
                arrayList.add(application);
            }
            return arrayList;
        } finally {
            A002.close();
            A00.A01();
        }
    }

    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:12:0x003f */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r12v0, types: [android.database.Cursor] */
    /* JADX WARN: Type inference failed for: r12v1, types: [android.database.Cursor] */
    /* JADX WARN: Type inference failed for: r12v2, types: [android.database.MatrixCursor] */
    /* JADX WARNING: Unknown variable types count: 1 */
    @Override // com.oculus.alpenglow.database.ApplicationDao
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.util.List<com.oculus.alpenglow.database.AssetsForApplication> A2z(java.lang.String r16) {
        /*
        // Method dump skipped, instructions count: 449
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.alpenglow.database.ApplicationDao_Impl.A2z(java.lang.String):java.util.List");
    }

    private void A01(HashMap<String, ArrayList<Asset>> hashMap) {
        ArrayList<Asset> arrayList;
        int i;
        Set<String> keySet = hashMap.keySet();
        if (keySet.isEmpty()) {
            return;
        }
        if (hashMap.size() > 999) {
            HashMap<String, ArrayList<Asset>> hashMap2 = new HashMap<>((int) AnonymousClass0Fr.MAX_BIND_PARAMETER_CNT);
            loop0:
            while (true) {
                i = 0;
                for (String str : keySet) {
                    hashMap2.put(str, hashMap.get(str));
                    i++;
                    if (i == 999) {
                        A01(hashMap2);
                        hashMap2 = new HashMap<>((int) AnonymousClass0Fr.MAX_BIND_PARAMETER_CNT);
                    }
                }
                break loop0;
            }
            if (i > 0) {
                A01(hashMap2);
                return;
            }
            return;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT `asset_id`,`app_id`,`last_updated_on_backend` FROM `asset` WHERE `app_id` IN (");
        int size = keySet.size();
        for (int i2 = 0; i2 < size; i2++) {
            sb.append("?");
            if (i2 < size - 1) {
                sb.append(",");
            }
        }
        sb.append(")");
        C03410cF A00 = C03410cF.A00(sb.toString(), size + 0);
        int i3 = 1;
        for (String str2 : keySet) {
            if (str2 == null) {
                A00.A1U(i3);
            } else {
                A00.A1W(i3, str2);
            }
            i3++;
        }
        Cursor A002 = this.__db.A00(A00);
        try {
            int A003 = A00(A002, LoggerConstants.APP_ID_KEY);
            if (A003 != -1) {
                int A004 = A00(A002, "asset_id");
                int A005 = A00(A002, LoggerConstants.APP_ID_KEY);
                int A006 = A00(A002, "last_updated_on_backend");
                while (A002.moveToNext()) {
                    if (!A002.isNull(A003) && (arrayList = hashMap.get(A002.getString(A003))) != null) {
                        Asset asset = new Asset();
                        if (A004 != -1) {
                            asset.assetId = A002.getString(A004);
                        }
                        if (A005 != -1) {
                            asset.appId = A002.getString(A005);
                        }
                        if (A006 != -1) {
                            asset.lastUpdatedOnBackend = A002.getLong(A006);
                        }
                        arrayList.add(asset);
                    }
                }
            }
        } finally {
            A002.close();
        }
    }

    @Override // com.oculus.alpenglow.database.ApplicationDao
    public final void A5I(Application... applicationArr) {
        this.__db.A04();
        this.__db.A05();
        try {
            this.__insertionAdapterOfApplication.A04(applicationArr);
            this.__db.A07();
        } finally {
            this.__db.A06();
        }
    }

    @Override // com.oculus.alpenglow.database.ApplicationDao
    public final void A7M(Application application, List<Asset> list) {
        Throwable th;
        this.__db.A04();
        this.__db.A05();
        try {
            AbstractC03440cN<Application> r2 = this.__deletionAdapterOfApplication;
            AbstractC03360cA A00 = r2.A00();
            try {
                r2.A03(A00, application);
                A00.A2S();
                r2.A02(A00);
                AbstractC03440cN<Asset> r3 = this.__deletionAdapterOfAsset;
                AbstractC03360cA A002 = r3.A00();
                try {
                    Iterator<T> it = list.iterator();
                    while (it.hasNext()) {
                        r3.A03(A002, it.next());
                        A002.A2S();
                    }
                    r3.A02(A002);
                    this.__db.A07();
                } catch (Throwable th2) {
                    th = th2;
                    r3.A02(A002);
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                r2.A02(A00);
                throw th;
            }
        } finally {
            this.__db.A06();
        }
    }

    @Override // com.oculus.alpenglow.database.ApplicationDao
    public final void A7k(String str, String str2, int i) {
        this.__db.A04();
        AbstractC03360cA A00 = this.__preparedStmtOfSetAppInstalled.A00();
        A00.A1Q(1, (long) i);
        if (str2 == null) {
            A00.A1U(2);
        } else {
            A00.A1W(2, str2);
        }
        if (str == null) {
            A00.A1U(3);
        } else {
            A00.A1W(3, str);
        }
        this.__db.A05();
        try {
            A00.A2S();
            this.__db.A07();
        } finally {
            this.__db.A06();
            this.__preparedStmtOfSetAppInstalled.A02(A00);
        }
    }

    @Override // com.oculus.alpenglow.database.ApplicationDao
    public final void A8i(String str, long j) {
        this.__db.A04();
        AbstractC03360cA A00 = this.__preparedStmtOfUpdateDownloadedBytes_1.A00();
        A00.A1Q(1, j);
        if (str == null) {
            A00.A1U(2);
        } else {
            A00.A1W(2, str);
        }
        this.__db.A05();
        try {
            A00.A2S();
            this.__db.A07();
        } finally {
            this.__db.A06();
            this.__preparedStmtOfUpdateDownloadedBytes_1.A02(A00);
        }
    }

    @Override // com.oculus.alpenglow.database.ApplicationDao
    public final void A8j(String str, long j, long j2) {
        this.__db.A04();
        AbstractC03360cA A00 = this.__preparedStmtOfUpdateDownloadedBytes.A00();
        A00.A1Q(1, j);
        A00.A1Q(2, j2);
        if (str == null) {
            A00.A1U(3);
        } else {
            A00.A1W(3, str);
        }
        this.__db.A05();
        try {
            A00.A2S();
            this.__db.A07();
        } finally {
            this.__db.A06();
            this.__preparedStmtOfUpdateDownloadedBytes.A02(A00);
        }
    }

    @Override // com.oculus.alpenglow.database.ApplicationDao
    public final void A8l(String str, int i) {
        this.__db.A04();
        AbstractC03360cA A00 = this.__preparedStmtOfUpdateStatus.A00();
        A00.A1Q(1, (long) i);
        if (str == null) {
            A00.A1U(2);
        } else {
            A00.A1W(2, str);
        }
        this.__db.A05();
        try {
            A00.A2S();
            this.__db.A07();
        } finally {
            this.__db.A06();
            this.__preparedStmtOfUpdateStatus.A02(A00);
        }
    }

    public ApplicationDao_Impl(AnonymousClass0Fr r2) {
        this.__db = r2;
        this.__insertionAdapterOfApplication = new AbstractC03430cM<Application>(r2) {
            /* class com.oculus.alpenglow.database.ApplicationDao_Impl.AnonymousClass1 */

            @Override // X.AbstractC01280Fx
            public final String A01() {
                return "INSERT OR REPLACE INTO `application` (`app_id`,`package_name`,`last_updated_on_backend`,`status`,`downloaded_bytes`,`total_bytes`,`app_source`) VALUES (?,?,?,?,?,?,?)";
            }

            /* JADX DEBUG: Method arguments types fixed to match base method, original types: [X.0cA, java.lang.Object] */
            @Override // X.AbstractC03430cM
            public final void A03(AbstractC03360cA r4, Application application) {
                Application application2 = application;
                String str = application2.appId;
                if (str == null) {
                    r4.A1U(1);
                } else {
                    r4.A1W(1, str);
                }
                String str2 = application2.packageName;
                if (str2 == null) {
                    r4.A1U(2);
                } else {
                    r4.A1W(2, str2);
                }
                r4.A1Q(3, application2.lastUpdatedOnBackend);
                AppStatus appStatus = application2.status;
                if (appStatus == null) {
                    appStatus = AppStatus.UNKNOWN;
                }
                r4.A1Q(4, (long) appStatus.ordinal());
                r4.A1Q(5, application2.downloadedBytes);
                r4.A1Q(6, application2.totalBytes);
                AppSource appSource = application2.appSource;
                if (appSource == null) {
                    appSource = AppSource.REMOTE_APP_BASED;
                }
                r4.A1Q(7, (long) appSource.ordinal());
            }
        };
        this.__deletionAdapterOfApplication = new AbstractC03440cN<Application>(r2) {
            /* class com.oculus.alpenglow.database.ApplicationDao_Impl.AnonymousClass2 */

            @Override // X.AbstractC01280Fx, X.AbstractC03440cN
            public final String A01() {
                return "DELETE FROM `application` WHERE `app_id` = ?";
            }

            /* JADX DEBUG: Method arguments types fixed to match base method, original types: [X.0cA, java.lang.Object] */
            @Override // X.AbstractC03440cN
            public final void A03(AbstractC03360cA r3, Application application) {
                String str = application.appId;
                if (str == null) {
                    r3.A1U(1);
                } else {
                    r3.A1W(1, str);
                }
            }
        };
        this.__deletionAdapterOfAsset = new AbstractC03440cN<Asset>(r2) {
            /* class com.oculus.alpenglow.database.ApplicationDao_Impl.AnonymousClass3 */

            @Override // X.AbstractC01280Fx, X.AbstractC03440cN
            public final String A01() {
                return "DELETE FROM `asset` WHERE `asset_id` = ?";
            }

            /* JADX DEBUG: Method arguments types fixed to match base method, original types: [X.0cA, java.lang.Object] */
            @Override // X.AbstractC03440cN
            public final void A03(AbstractC03360cA r3, Asset asset) {
                String str = asset.assetId;
                if (str == null) {
                    r3.A1U(1);
                } else {
                    r3.A1W(1, str);
                }
            }
        };
        this.__preparedStmtOfUpdateDownloadedBytes = new AbstractC01280Fx(r2) {
            /* class com.oculus.alpenglow.database.ApplicationDao_Impl.AnonymousClass4 */

            @Override // X.AbstractC01280Fx
            public final String A01() {
                return "UPDATE application SET downloaded_bytes = ?, total_bytes = ? WHERE app_id = ?";
            }
        };
        this.__preparedStmtOfUpdateDownloadedBytes_1 = new AbstractC01280Fx(r2) {
            /* class com.oculus.alpenglow.database.ApplicationDao_Impl.AnonymousClass5 */

            @Override // X.AbstractC01280Fx
            public final String A01() {
                return "UPDATE application SET downloaded_bytes = ? WHERE app_id = ?";
            }
        };
        this.__preparedStmtOfUpdateStatus = new AbstractC01280Fx(r2) {
            /* class com.oculus.alpenglow.database.ApplicationDao_Impl.AnonymousClass6 */

            @Override // X.AbstractC01280Fx
            public final String A01() {
                return "UPDATE application SET status = ? WHERE app_id = ?";
            }
        };
        this.__preparedStmtOfUpdateApplication = new AbstractC01280Fx(r2) {
            /* class com.oculus.alpenglow.database.ApplicationDao_Impl.AnonymousClass7 */

            @Override // X.AbstractC01280Fx
            public final String A01() {
                return "UPDATE application SET status = ?, downloaded_bytes = ?, total_bytes = ? WHERE app_id = ?";
            }
        };
        this.__preparedStmtOfSetAppInstalled = new AbstractC01280Fx(r2) {
            /* class com.oculus.alpenglow.database.ApplicationDao_Impl.AnonymousClass8 */

            @Override // X.AbstractC01280Fx
            public final String A01() {
                return "UPDATE application SET status = ?, package_name = ? WHERE app_id = ?";
            }
        };
    }

    public static int A00(@NonNull Cursor cursor, @NonNull String str) {
        int columnIndex = cursor.getColumnIndex(str);
        if (columnIndex < 0) {
            return cursor.getColumnIndex(AnonymousClass006.A07("`", str, "`"));
        }
        return columnIndex;
    }
}
