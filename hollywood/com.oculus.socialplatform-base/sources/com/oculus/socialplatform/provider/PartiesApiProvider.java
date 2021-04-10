package com.oculus.socialplatform.provider;

import X.AnonymousClass0MD;
import X.AnonymousClass0RE;
import X.AnonymousClass0VF;
import X.AnonymousClass0lg;
import X.AnonymousClass1SO;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.net.Uri;
import androidx.annotation.VisibleForTesting;
import com.facebook.secure.content.FbPermissionsContentProvider;
import com.google.common.collect.ImmutableList;
import com.oculus.socialplatform.partyservice.PartyService;
import com.oculus.socialplatform.provider.contract.SocialPlatformContent;
import com.oculus.socialplatform.sharedstate.PartySharedState;
import com.oculus.util.constants.PlatformPluginConstants;
import com.oculus.util.constants.SocialPlatformContants;
import com.oculus.vrshell.panels.AndroidPanelLayer;
import javax.annotation.Nullable;
import org.json.JSONException;
import org.json.JSONObject;

public class PartiesApiProvider extends FbPermissionsContentProvider {
    public static final ImmutableList<String> PATHS_WITHOUT_CALLER_AUTH;
    public static final String TAG = "PartiesApiProvider";
    public static final int UPDATE_FAILURE = -1;
    public static final int UPDATE_SUCCESS = 1;
    public AnonymousClass0RE _UL_mInjectionContext;

    public static final void _UL_staticInjectMe(AnonymousClass0lg r2, PartiesApiProvider partiesApiProvider) {
        partiesApiProvider._UL_mInjectionContext = new AnonymousClass0RE(1, r2);
    }

    public static <T> Cursor putSingleValue(String str, T t) {
        MatrixCursor matrixCursor = new MatrixCursor(new String[]{str}, 1);
        matrixCursor.addRow(new Object[]{t});
        return matrixCursor;
    }

    @Override // X.AnonymousClass0jF
    public final boolean allowOwnCallingProcess() {
        return true;
    }

    @Override // com.facebook.secure.content.FbPermissionsContentProvider
    public String getFbPermission() {
        return "com.oculus.socialplatform.fbpermission.PARTIES_API_PROVIDER";
    }

    @VisibleForTesting
    public int stopPartyChat() {
        return 1;
    }

    @VisibleForTesting
    public int syncPartyUI() {
        return 1;
    }

    @Nullable
    private String makePartyStateJson() {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("passthrough", false);
            jSONObject.put("microphone_muted", getSharedPartyState().getSystemVoipMicrophoneMutedX());
            jSONObject.put("status", getSharedPartyState().getSystemVoipStatusX());
            jSONObject.put("shared_memory_handle", -1);
            return jSONObject.toString();
        } catch (JSONException unused) {
            return null;
        }
    }

    public static void verifyPartyPermission(Uri uri) {
        String queryParameter = uri.getQueryParameter(SocialPlatformContent.Parties3pApi.In.CALLER_PACKAGE);
        if (PATHS_WITHOUT_CALLER_AUTH.contains(uri.getPath())) {
            if (queryParameter != null) {
                AnonymousClass0MD.A04(TAG, "Caller sent CALLER_PACKAGE to unsecured endpoint.");
                throw new SecurityException();
            }
        } else if (queryParameter == null) {
            AnonymousClass0MD.A04(TAG, "Caller sent null CALLER_PACKAGE to secure endpoint.");
            throw new SecurityException();
        } else if (!PlatformPluginConstants.WHITELIST_SYSTEM_VOIP_PACKAGES.contains(queryParameter)) {
            AnonymousClass0MD.A04(TAG, "Caller sent non-whitelisted CALLER_PACKAGE to secure endpoint.");
            throw new SecurityException();
        }
    }

    @Override // X.AnonymousClass0jF
    @Nullable
    public Uri doInsert(Uri uri, ContentValues contentValues) {
        return null;
    }

    public PartySharedState getSharedPartyState() {
        return (PartySharedState) AnonymousClass0VF.A03(0, 85, this._UL_mInjectionContext);
    }

    @VisibleForTesting
    public int setMicMuteState(Integer num) {
        if (num != null) {
            getSharedPartyState().setSystemVoipMicrophoneMutedX(num.intValue());
            return 1;
        }
        throw new IllegalArgumentException();
    }

    @VisibleForTesting
    public int setPartyVolume(Float f) {
        if (f != null) {
            float floatValue = f.floatValue();
            if (floatValue < AndroidPanelLayer.Spec.DEFAULT_CYLINDER_POSITION_Z || floatValue > 1.0f) {
                throw new IllegalArgumentException();
            }
            getSharedPartyState().setPartyChatVolumeX(floatValue);
            return 1;
        }
        throw new IllegalArgumentException();
    }

    @VisibleForTesting
    public int setSuppressed(Boolean bool) {
        if (bool != null) {
            getSharedPartyState().setSuppressed(bool.booleanValue());
            return 1;
        }
        throw new IllegalArgumentException();
    }

    @VisibleForTesting
    public int startPartyChat(Long l) {
        if (l != null) {
            return 1;
        }
        throw new IllegalArgumentException();
    }

    static {
        ImmutableList.Builder A02 = ImmutableList.A02();
        A02.add((Object) SocialPlatformContent.Parties3pApi.Updates.SET_SUPPRESSED.getPath());
        A02.add((Object) SocialPlatformContent.Parties3pApi.Queries.GET_STATUS.getPath());
        A02.add((Object) SocialPlatformContent.Parties3pApi.Queries.GET_MIC_MUTE_STATE.getPath());
        A02.add((Object) SocialPlatformContent.Parties3pApi.Queries.GET_DATA.getPath());
        A02.add((Object) SocialPlatformContent.Parties3pApi.Queries.GET_VOIP_CONNECTION_STATUS.getPath());
        PATHS_WITHOUT_CALLER_AUTH = A02.build();
    }

    public static final void _UL_injectMe(Context context, PartiesApiProvider partiesApiProvider) {
        _UL_staticInjectMe(AnonymousClass0VF.get(context), partiesApiProvider);
    }

    @Override // X.AnonymousClass0jF
    @Nullable
    public Cursor doQuery(Uri uri, String[] strArr, String str, String[] strArr2, String str2) {
        uri.getPath();
        verifyPartyPermission(uri);
        String path = uri.getPath();
        if (path == null) {
            throw new IllegalArgumentException();
        } else if (path.equals(SocialPlatformContent.Parties3pApi.Queries.GET_DATA.getPath())) {
            return getPartyData();
        } else {
            if (path.equals(SocialPlatformContent.Parties3pApi.Queries.GET_VOLUME.getPath())) {
                return getPartyChatVolume();
            }
            if (path.equals(SocialPlatformContent.Parties3pApi.Queries.GET_STATUS.getPath())) {
                return getPartyChatStatus();
            }
            if (path.equals(SocialPlatformContent.Parties3pApi.Queries.GET_MIC_MUTE_STATE.getPath())) {
                return getMicMuteState();
            }
            if (path.equals(SocialPlatformContent.Parties3pApi.Queries.GET_VOIP_CONNECTION_STATUS.getPath())) {
                return getVoipConnectionStatus();
            }
            return null;
        }
    }

    @Override // X.AnonymousClass0jF
    public int doUpdate(Uri uri, ContentValues contentValues, String str, String[] strArr) {
        uri.getPath();
        verifyPartyPermission(uri);
        String path = uri.getPath();
        if (path == null) {
            throw new IllegalArgumentException();
        } else if (path.equals(SocialPlatformContent.Parties3pApi.Updates.SET_VOLUME.getPath())) {
            return setPartyVolume(contentValues.getAsFloat("volume"));
        } else {
            if (path.equals(SocialPlatformContent.Parties3pApi.Updates.START_CHAT.getPath())) {
                return startPartyChat(contentValues.getAsLong("party_id"));
            }
            if (path.equals(SocialPlatformContent.Parties3pApi.Updates.STOP_CHAT.getPath())) {
                return 1;
            }
            if (path.equals(SocialPlatformContent.Parties3pApi.Updates.SET_SUPPRESSED.getPath())) {
                return setSuppressed(contentValues.getAsBoolean(SocialPlatformContent.Parties3pApi.In.VOIP_SUPPRESSED));
            }
            if (path.equals(SocialPlatformContent.Parties3pApi.Updates.SET_MIC_MUTE_STATE.getPath())) {
                return setMicMuteState(contentValues.getAsInteger("mic_mute_state"));
            }
            if (path.equals(SocialPlatformContent.Parties3pApi.Updates.RUN_PARTY_ENFORCER.getPath())) {
                return runPartyEnforcer();
            }
            if (path.equals(SocialPlatformContent.Parties3pApi.Updates.ON_APP_SWITCH.getPath())) {
                return onAppSwitch(contentValues.getAsString("package_name"), contentValues.getAsBoolean("exclusive_mic").booleanValue());
            }
            if (path.equals(SocialPlatformContent.Parties3pApi.Updates.SET_VOIP_CONNECTION_STATUS.getPath())) {
                return setVoipConnectionStatus(contentValues.getAsString("voip_connection_status"));
            }
            if (path.equals(SocialPlatformContent.Parties3pApi.Updates.SYNC_MIC_STATE.getPath())) {
                return 1;
            }
            return -1;
        }
    }

    @VisibleForTesting
    public Cursor getMicMuteState() {
        return putSingleValue("mic_mute_state", getSharedPartyState().getSystemVoipMicrophoneMutedX());
    }

    @VisibleForTesting
    public Cursor getPartyChatStatus() {
        return putSingleValue("status", getSharedPartyState().getSystemVoipStatusX());
    }

    @VisibleForTesting
    public Cursor getPartyChatVolume() {
        return putSingleValue("volume", Float.valueOf(getSharedPartyState().getPartyChatVolumeX()));
    }

    @VisibleForTesting
    public Cursor getPartyData() {
        return putSingleValue(SocialPlatformContent.Parties3pApi.Out.STATE_JSON, makePartyStateJson());
    }

    @VisibleForTesting
    public Cursor getVoipConnectionStatus() {
        return putSingleValue("voip_connection_status", getSharedPartyState().getVoipConnectionStatus());
    }

    @VisibleForTesting
    public int onAppSwitch(String str, boolean z) {
        if (getContext() == null) {
            AnonymousClass0MD.A04(TAG, "Null context in onAppSwitch");
            return -1;
        }
        Intent intent = new Intent(getContext(), PartyService.class);
        intent.setAction(SocialPlatformContants.ON_APP_SWITCH_ACTION);
        intent.putExtra("package_name", str);
        intent.putExtra("exclusive_mic", z);
        AnonymousClass1SO.A00(intent, getContext());
        return 1;
    }

    @Override // X.AnonymousClass0jF
    public void onInitialize() {
        _UL_injectMe(getContext(), this);
    }

    @VisibleForTesting
    public int runPartyEnforcer() {
        if (getContext() == null) {
            AnonymousClass0MD.A04(TAG, "Null context in runPartyEnforcer");
            return -1;
        }
        Intent intent = new Intent(getContext(), PartyService.class);
        intent.setAction(SocialPlatformContants.RUN_PARTY_CHAT_ENFORCER_ACTION);
        AnonymousClass1SO.A00(intent, getContext());
        return 1;
    }

    @VisibleForTesting
    public int setVoipConnectionStatus(String str) {
        getSharedPartyState().setVoipConnectionStatus(str);
        return 1;
    }

    @Override // X.AnonymousClass0jF
    @Nullable
    public String doGetType(Uri uri) {
        return null;
    }

    @Override // X.AnonymousClass0jF
    public int doDelete(Uri uri, String str, String[] strArr) {
        return 0;
    }
}
