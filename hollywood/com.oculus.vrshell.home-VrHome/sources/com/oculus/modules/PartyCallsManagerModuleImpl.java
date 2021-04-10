package com.oculus.modules;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import com.facebook.debug.log.BLog;
import com.google.common.base.Strings;
import com.oculus.modules.codegen.PartyCallsManagerModule;
import com.oculus.modules.codegen.Resolver;
import com.oculus.provider.OculusContent;
import org.json.JSONArray;

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
    private static PartyCallsManagerModuleImpl mInstance = null;
    private final Context mContext;

    public PartyCallsManagerModuleImpl(Context context) {
        this.mContext = context;
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.modules.codegen.PartyCallsManagerModule
    public void joinPartyImpl(String partyID, boolean useSocialPlatformContentProvider, Resolver<Void> resolver) {
        String partyJoinURI = useSocialPlatformContentProvider ? PARTY_JOIN_URI_SOCIAL_PLATFORM : PARTY_JOIN_URI_HORIZON;
        BLog.i(MODULE_NAME, "Contacting Horizon content provider with uri: " + partyJoinURI + " to join party with id: " + partyID);
        new JSONArray();
        Cursor cursor = null;
        try {
            Cursor cursor2 = this.mContext.getContentResolver().query(Uri.parse(partyJoinURI).buildUpon().appendQueryParameter("party_id", partyID).build(), null, null, null, null);
            if (cursor2 == null || !cursor2.moveToNext()) {
                resolver.resolve(null);
            } else {
                String userFacingErrorMessage = cursor2.getString(cursor2.getColumnIndexOrThrow(OculusContent.FriendList.PARTY_JOIN_ERROR_COLUMN));
                if (!Strings.isNullOrEmpty(userFacingErrorMessage)) {
                    resolver.reject(new Exception(userFacingErrorMessage));
                }
            }
            if (cursor2 != null) {
                cursor2.close();
            }
        } catch (Exception e) {
            BLog.e(MODULE_NAME, e, "An error occured when attempting to join a party.");
            resolver.reject(null);
            if (0 != 0) {
                cursor.close();
            }
        } catch (Throwable th) {
            if (0 != 0) {
                cursor.close();
            }
            throw th;
        }
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.modules.codegen.PartyCallsManagerModule
    public void createPartyImpl(String partyType, boolean useSocialPlatformContentProvider, String socialActivityID, Resolver<String> resolver) {
        String partyCreateURI = useSocialPlatformContentProvider ? PARTY_CREATE_URI_SOCIAL_PLATFORM : PARTY_CREATE_URI_HORIZON;
        BLog.i(MODULE_NAME, "Contacting Horizon content provider with uri: " + partyCreateURI + " to create party");
        new JSONArray();
        Cursor cursor = null;
        try {
            Uri.Builder uriBuilder = Uri.parse(partyCreateURI).buildUpon();
            if (socialActivityID != null && !socialActivityID.isEmpty()) {
                uriBuilder = uriBuilder.appendQueryParameter("invite_activity_id", socialActivityID);
            }
            if (partyType != null && !partyType.isEmpty()) {
                uriBuilder = uriBuilder.appendQueryParameter("party_type", partyType);
            }
            Uri uri = uriBuilder.build();
            BLog.i(MODULE_NAME, "Querying Horizon content provider with uri: " + uri + " to create a party");
            Cursor cursor2 = this.mContext.getContentResolver().query(uri, null, null, null, null);
            if (cursor2 != null) {
                cursor2.moveToFirst();
                String partyID = cursor2.getString(cursor2.getColumnIndexOrThrow("current_party_id"));
                if (partyID == null || partyID.isEmpty()) {
                    resolver.reject(new Error("Created partyID was null."));
                } else {
                    BLog.i(MODULE_NAME, "Created party with party id: " + partyID);
                    resolver.resolve(partyID);
                }
            } else {
                BLog.e(MODULE_NAME, "Attempted to create a party but the party ID is missing.");
                resolver.reject(new Error("Could not create party successfully."));
            }
            if (cursor2 != null) {
                cursor2.close();
            }
        } catch (Exception e) {
            BLog.e(MODULE_NAME, e, "An error occured when attempting to create a party.");
            resolver.reject(e);
            if (0 != 0) {
                cursor.close();
            }
        } catch (Throwable th) {
            if (0 != 0) {
                cursor.close();
            }
            throw th;
        }
    }

    @Override // com.oculus.modules.codegen.PartyCallsManagerModule
    public void shutdownModule() {
    }
}
