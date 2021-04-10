package com.oculus.library.utils;

import com.oculus.errorreporting.ErrorReporter;
import com.oculus.library.model.AppStatus;
import com.oculus.model.ProductStatus;
import java.util.ArrayList;

public class AppStatusConverter {
    public static final String TAG = "AppStatusConverter";

    public static ProductStatus toLegacyProductStatus(AppStatus appStatus) {
        switch (appStatus) {
            case INCOMPATIBLE:
            case ENTITLED:
                return ProductStatus.NOT_DOWNLOADED;
            case DOWNLOAD_QUEUED:
                return ProductStatus.QUEUED;
            case DOWNLOADING:
                return ProductStatus.DOWNLOADING;
            case INSTALLING:
                return ProductStatus.INSTALLING;
            case INSTALLED:
                return ProductStatus.INSTALLED;
            case UNINSTALLING:
                return ProductStatus.UNINSTALLING;
            default:
                ErrorReporter.softReport(TAG, "Cannot convert status to legacy status: " + appStatus.name());
                return null;
        }
    }

    public static boolean isIncompatible(String str, String str2) {
        return str != null && str.equals(str2);
    }

    public static AppStatus toAppStatus(String str, String str2, ProductStatus productStatus) {
        if (isIncompatible(str, str2)) {
            return AppStatus.INCOMPATIBLE;
        }
        switch (productStatus) {
            case NOT_PURCHASED:
                throw new UnsupportedOperationException("Unsupported status: NOT_PURCHASED");
            case NOT_DOWNLOADED:
                return AppStatus.ENTITLED;
            case QUEUED:
                return AppStatus.DOWNLOAD_QUEUED;
            case DOWNLOADING:
                return AppStatus.DOWNLOADING;
            case DOWNLOADED:
                return AppStatus.DOWNLOADING;
            case INSTALLING:
                return AppStatus.INSTALLING;
            case INSTALLED:
                return AppStatus.INSTALLED;
            case UNINSTALLING:
                return AppStatus.UNINSTALLING;
            default:
                throw new IllegalArgumentException("Unsupported product status: " + productStatus + " for package " + str);
        }
    }

    public static ProductStatus[] appStatusNamesToLegacyProductStatuses(String[] strArr) {
        ArrayList arrayList = new ArrayList();
        for (String str : strArr) {
            ProductStatus legacyProductStatus = toLegacyProductStatus(parseAppStatusNameWithDefault(str, AppStatus.UNKNOWN));
            if (legacyProductStatus != null) {
                arrayList.add(legacyProductStatus);
            }
        }
        return (ProductStatus[]) arrayList.toArray(new ProductStatus[arrayList.size()]);
    }

    private static AppStatus parseAppStatusNameWithDefault(String str, AppStatus appStatus) {
        try {
            return AppStatus.valueOf(str);
        } catch (IllegalArgumentException unused) {
            return appStatus;
        }
    }
}
