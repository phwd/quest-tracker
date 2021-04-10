package com.oculus.modules;

import X.AnonymousClass0MD;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import com.oculus.modules.codegen.PartyCallsManagerModule;
import com.oculus.modules.codegen.Resolver;

public class PartyCallsManagerModuleImpl extends PartyCallsManagerModule {
    public static final String APP_ID_PARAM_IN_CREATE_PARTY_QUERY = "invite_activity_id";
    public static final String CALLER_ID_COLUMN = "caller_id";
    public static final String CALLER_NAME_COLUMN = "caller_name";
    public static final String CALLER_PHOTO_COLUMN = "caller_photo";
    public static final String CALL_ID_COLUMN = "call_id";
    public static final String CURRENT_PARTY_ID_COLUMN = "current_party_id";
    public static final String PARTY_CREATE_URI_HORIZON = "content://com.oculus.horizon.friendlist/party_create";
    public static final String PARTY_CREATE_URI_SOCIAL_PLATFORM = "content://com.oculus.socialplatform.friendlist/party_create";
    public static final String PARTY_JOIN_URI_HORIZON = "content://com.oculus.horizon.friendlist/party_join_with_error_callback";
    public static final String PARTY_JOIN_URI_SOCIAL_PLATFORM = "content://com.oculus.socialplatform.friendlist/party_join_with_error_callback";
    public static final String PARTY_TYPE_PARAM = "party_type";
    public static PartyCallsManagerModuleImpl mInstance;
    public final Context mContext;

    @Override // com.oculus.modules.codegen.PartyCallsManagerModule
    public void shutdownModule() {
    }

    @Override // com.oculus.modules.codegen.PartyCallsManagerModule
    public void createPartyImpl(String str, boolean z, String str2, Resolver<String> resolver) {
        String str3;
        if (z) {
            str3 = PARTY_CREATE_URI_SOCIAL_PLATFORM;
        } else {
            str3 = PARTY_CREATE_URI_HORIZON;
        }
        Cursor cursor = null;
        try {
            Uri.Builder buildUpon = Uri.parse(str3).buildUpon();
            if (str2 != null && !str2.isEmpty()) {
                buildUpon = buildUpon.appendQueryParameter("invite_activity_id", str2);
            }
            if (str != null && !str.isEmpty()) {
                buildUpon = buildUpon.appendQueryParameter("party_type", str);
            }
            cursor = this.mContext.getContentResolver().query(buildUpon.build(), null, null, null, null);
            if (cursor != null) {
                cursor.moveToFirst();
                String string = cursor.getString(cursor.getColumnIndexOrThrow("current_party_id"));
                if (string == null || string.isEmpty()) {
                    resolver.reject(new Error("Created partyID was null."));
                } else {
                    resolver.resolve(string);
                }
                cursor.close();
                return;
            }
            AnonymousClass0MD.A04(PartyCallsManagerModule.MODULE_NAME, "Attempted to create a party but the party ID is missing.");
            resolver.reject(new Error("Could not create party successfully."));
        } catch (Exception e) {
            AnonymousClass0MD.A0C(PartyCallsManagerModule.MODULE_NAME, e, "An error occured when attempting to create a party.");
            resolver.reject(e);
            if (0 == 0) {
            }
        } catch (Throwable th) {
            if (0 != 0) {
                cursor.close();
            }
            throw th;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:15:0x004f, code lost:
        r2 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0050, code lost:
        r3 = null;
     */
    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    /* JADX WARNING: Removed duplicated region for block: B:28:? A[RETURN, SYNTHETIC] */
    @Override // com.oculus.modules.codegen.PartyCallsManagerModule
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void joinPartyImpl(java.lang.String r9, boolean r10, com.oculus.modules.codegen.Resolver<java.lang.Void> r11) {
        /*
        // Method dump skipped, instructions count: 106
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.modules.PartyCallsManagerModuleImpl.joinPartyImpl(java.lang.String, boolean, com.oculus.modules.codegen.Resolver):void");
    }

    public PartyCallsManagerModuleImpl(Context context) {
        this.mContext = context;
    }
}
