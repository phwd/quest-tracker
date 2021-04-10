package com.facebook.tigon;

import com.facebook.http.constants.FbHttpConstants;
import com.facebook.infer.annotation.Nullsafe;
import com.facebook.proguard.annotations.DoNotStrip;
import com.facebook.tigon.iface.TigonErrorCodeUtil;
import java.io.IOException;
import javax.annotation.Nullable;

@DoNotStrip
@Nullsafe(Nullsafe.Mode.LOCAL)
public class TigonErrorException extends IOException {
    public final TigonError tigonError;

    @DoNotStrip
    public TigonErrorException(TigonError tigonError2) {
        super(formatTigonError(tigonError2));
        this.tigonError = tigonError2;
    }

    static String formatTigonError(TigonError tigonError2) {
        StringBuilder sb = new StringBuilder();
        sb.append("TigonError(");
        sb.append("error=");
        sb.append(TigonErrorCodeUtil.toString(tigonError2.category()));
        sb.append(", ");
        sb.append("errorDomain=");
        sb.append(tigonError2.errorDomain());
        sb.append(", ");
        sb.append("domainErrorCode=");
        sb.append(tigonError2.domainErrorCode());
        if (tigonError2.analyticsDetail() != null && !tigonError2.analyticsDetail().isEmpty()) {
            sb.append(", analyticsDetail=\"");
            sb.append(tigonError2.analyticsDetail());
            sb.append("\"");
        }
        sb.append(")");
        return sb.toString();
    }

    @Nullable
    public static String formatTigonException(@Nullable IOException iOException) {
        if (iOException == null) {
            return null;
        }
        TigonError underlyingTigonError = getUnderlyingTigonError(iOException);
        if (underlyingTigonError != null) {
            String errorDomain = underlyingTigonError.errorDomain();
            if (errorDomain.startsWith("Tigon") && errorDomain.endsWith("Domain")) {
                errorDomain = errorDomain.substring(5, errorDomain.length() - 6);
            }
            return underlyingTigonError.category() + ":" + errorDomain + ":" + underlyingTigonError.domainErrorCode();
        }
        String simpleName = iOException.getClass().getSimpleName();
        Throwable cause = iOException.getCause();
        if (cause == null) {
            return simpleName;
        }
        return simpleName + "|" + cause.getClass().getSimpleName();
    }

    @Nullable
    public static TigonError getUnderlyingTigonError(Throwable th) {
        TigonErrorException tigonErrorException;
        while (true) {
            if (!(th instanceof TigonErrorException)) {
                th = th.getCause();
                if (!(th instanceof IOException)) {
                    tigonErrorException = null;
                    break;
                }
            } else {
                tigonErrorException = (TigonErrorException) th;
                break;
            }
        }
        if (tigonErrorException != null) {
            return tigonErrorException.tigonError;
        }
        return null;
    }

    public static String convertExceptionToRequestStatus(@Nullable IOException iOException) {
        if (iOException == null) {
            return FbHttpConstants.STATE_DONE;
        }
        TigonError underlyingTigonError = getUnderlyingTigonError(iOException);
        if (underlyingTigonError == null) {
            return "error";
        }
        return convertErrorToRequestStatus(underlyingTigonError);
    }

    private static String convertErrorToRequestStatus(TigonError tigonError2) {
        int category = tigonError2.category();
        if (category != 0) {
            return category != 1 ? "error" : "cancelled";
        }
        return FbHttpConstants.STATE_DONE;
    }
}
