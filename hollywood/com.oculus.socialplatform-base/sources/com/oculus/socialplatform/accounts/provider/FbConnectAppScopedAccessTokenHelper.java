package com.oculus.socialplatform.accounts.provider;

import android.content.Context;
import android.database.Cursor;
import com.oculus.socialplatform.accounts.provider.Constants;
import java.io.IOException;

public class FbConnectAppScopedAccessTokenHelper {
    public static String queryAccessToken(Context context) throws IOException {
        Cursor query = context.getContentResolver().query(Constants.FbConnect.FB_CONNECT_APP_SCOPED_ACCESS_TOKEN_URI.buildUpon().appendQueryParameter("override_oc_app_id", "3534234083363713").build(), null, null, null, null);
        if (query != null) {
            try {
                if (query.moveToNext()) {
                    int columnIndex = query.getColumnIndex("fb_app_id");
                    if (columnIndex < 0) {
                        throw new IOException("Failed to query access token: Response doesn't contain fb_app_id.");
                    } else if ("581956559359077".equals(query.getString(columnIndex))) {
                        int columnIndexOrThrow = query.getColumnIndexOrThrow("access_token");
                        if (columnIndexOrThrow >= 0) {
                            String string = query.getString(columnIndexOrThrow);
                            if (string != null) {
                                query.close();
                                return string;
                            }
                            throw new IOException("Failed to query access token: Response doesn't contain access token.");
                        }
                        throw new IOException("Failed to query access token: Response doesn't contain access token.");
                    } else {
                        throw new IOException("Failed to query access token: Returned fb_app_id doesn't match Messenger VR.");
                    }
                }
            } catch (Throwable unused) {
            }
        }
        throw new IOException("Failed to query access token: empty response.");
        throw th;
    }
}
