package com.oculus.panelapp.dialog.commonsystemdialogs.social;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;
import com.oculus.assistant.service.api.panel.AssistantComponentContract;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.panelapp.dialog.CommonDialog;
import com.oculus.panelapp.dialog.R;
import com.oculus.systemdialog.CommonSystemDialog;
import com.oculus.systemdialog.CommonSystemDialogActions;
import com.oculus.systemdialog.DialogButton;
import com.oculus.systemdialog.DialogButtonText;
import com.oculus.systemdialog.DialogDefinitionCustom;
import com.oculus.systemdialog.DialogHeroImage;
import com.oculus.vrshell.SystemUXRoute;
import java.util.Map;
import org.json.JSONArray;

public final class SocialCreatePartyPreviewDialog extends CommonDialog {
    public static final String APP_ID_PARAM_IN_APP_INFO_FETCH_QUERY = "activity_id";
    public static final String APP_ID_PARAM_IN_CREATE_PARTY_QUERY = "invite_activity_id";
    public static final String CURRENT_PARTY_ID_INDEX = "current_party_id";
    public static final String GET_APP_INFO_URI = "content://com.oculus.horizon.friendlist/social_activity";
    public static final float HERO_IMAGE_ASPECT_RATIO = 1.778f;
    public static final String HERO_IMAGE_BACKGROUND_COLOR = "0xFF1A1A1A";
    public static final String IMAGE_URI_INDEX = "image_uri";
    public static final String INCOMING_APP_ID_PARAM = "app_id";
    public static final String PARTY_CREATE_URI = "content://com.oculus.horizon.friendlist/party_create";
    public static final String PARTY_TYPE_PARAM = "party_type";
    public static final String ROLE_PARAM = "role";
    private static final String TAG = LoggingUtil.tag(SocialCreatePartyPreviewDialog.class);
    private String mAppID;
    private Context mContext;
    private DialogDefinitionCustom mDefinition;
    private String mPartyType;
    private final Resources mResources = this.mContext.getResources();

    public SocialCreatePartyPreviewDialog(Context context, Map<String, String> map) {
        this.mAppID = getStringParameterOrDefault(map, "app_id", "");
        this.mPartyType = getStringParameterOrDefault(map, "party_type", "");
        this.mContext = context;
        setDialogConfiguration();
    }

    private void setDialogConfiguration() {
        this.mDefinition = new DialogDefinitionCustom(CommonSystemDialog.SOCIAL_CREATE_PARTY_PREVIEW.getDialogId(), this.mResources.getString(R.string.social_create_party_preview_title), this.mResources.getString(R.string.social_create_party_preview_body_text));
        new AsyncTask<Void, Void, Void>() {
            /* class com.oculus.panelapp.dialog.commonsystemdialogs.social.SocialCreatePartyPreviewDialog.AnonymousClass1 */

            /* access modifiers changed from: protected */
            public Void doInBackground(Void... voidArr) {
                SocialCreatePartyPreviewDialog.this.fetchAppInfo();
                return null;
            }
        }.execute(new Void[0]);
        this.mDefinition.setPrimaryButton(new DialogButtonText(CommonSystemDialogActions.CREATE_PARTY, this.mResources.getString(R.string.social_create_party_preview_start_party_cta_label)));
        this.mDefinition.setSecondaryButton(new DialogButtonText("cancel", this.mResources.getString(R.string.social_create_party_preview_cancel_cta_label)));
        this.mDefinition.setTertiaryButton(new DialogButtonText(CommonSystemDialogActions.COMMUNITY_GUIDELINES, this.mResources.getString(R.string.social_party_community_guidelines)));
        this.mDefinition.setControllerBackButton(new DialogButton("cancel"));
        setPendingDialogConfiguration(this.mDefinition);
    }

    /* JADX WARNING: Removed duplicated region for block: B:17:0x0038  */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x0041  */
    @Override // com.oculus.panelapp.dialog.CommonDialog
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onAction(java.lang.String r5, java.lang.String[] r6) {
        /*
            r4 = this;
            int r6 = r5.hashCode()
            r0 = -1367724422(0xffffffffae7a2e7a, float:-5.68847E-11)
            r1 = 0
            r2 = 2
            r3 = 1
            if (r6 == r0) goto L_0x002b
            r0 = -507984054(0xffffffffe1b8c74a, float:-4.2607015E20)
            if (r6 == r0) goto L_0x0021
            r0 = 1911131724(0x71e98e4c, float:2.3130251E30)
            if (r6 == r0) goto L_0x0017
            goto L_0x0035
        L_0x0017:
            java.lang.String r6 = "communityGuidelines"
            boolean r5 = r5.equals(r6)
            if (r5 == 0) goto L_0x0035
            r5 = r2
            goto L_0x0036
        L_0x0021:
            java.lang.String r6 = "createParty"
            boolean r5 = r5.equals(r6)
            if (r5 == 0) goto L_0x0035
            r5 = r1
            goto L_0x0036
        L_0x002b:
            java.lang.String r6 = "cancel"
            boolean r5 = r5.equals(r6)
            if (r5 == 0) goto L_0x0035
            r5 = r3
            goto L_0x0036
        L_0x0035:
            r5 = -1
        L_0x0036:
            if (r5 == 0) goto L_0x0041
            if (r5 == r3) goto L_0x004b
            if (r5 == r2) goto L_0x003d
            goto L_0x004b
        L_0x003d:
            r4.launchBrowserForGuidelines()
            goto L_0x004b
        L_0x0041:
            com.oculus.panelapp.dialog.commonsystemdialogs.social.SocialCreatePartyPreviewDialog$2 r5 = new com.oculus.panelapp.dialog.commonsystemdialogs.social.SocialCreatePartyPreviewDialog$2
            r5.<init>()
            java.lang.Void[] r6 = new java.lang.Void[r1]
            r5.execute(r6)
        L_0x004b:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.panelapp.dialog.commonsystemdialogs.social.SocialCreatePartyPreviewDialog.onAction(java.lang.String, java.lang.String[]):void");
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void fetchAppInfo() {
        new JSONArray();
        Cursor cursor = null;
        try {
            cursor = this.mContext.getContentResolver().query(Uri.parse(GET_APP_INFO_URI).buildUpon().appendQueryParameter("activity_id", this.mAppID).build(), null, null, null, null);
            if (cursor != null) {
                cursor.moveToFirst();
                this.mDefinition.setHeroImage(new DialogHeroImage(cursor.getString(cursor.getColumnIndexOrThrow("image_uri")), 1.778f, HERO_IMAGE_BACKGROUND_COLOR));
                setPendingDialogConfiguration(this.mDefinition);
            } else {
                Log.i(TAG, "Attempted to fetch app info but app image is missing.");
            }
            if (cursor == null) {
                return;
            }
        } catch (Exception e) {
            Log.e(TAG, "An error occured when attempting to fetch app info.", e);
            if (0 == 0) {
                return;
            }
        } catch (Throwable th) {
            if (0 != 0) {
                cursor.close();
            }
            throw th;
        }
        cursor.close();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void createParty() {
        new JSONArray();
        Cursor cursor = null;
        try {
            Uri.Builder buildUpon = Uri.parse(PARTY_CREATE_URI).buildUpon();
            if (this.mAppID != null && !this.mAppID.isEmpty()) {
                buildUpon = buildUpon.appendQueryParameter("invite_activity_id", this.mAppID);
            }
            if (this.mPartyType != null && !this.mPartyType.isEmpty()) {
                buildUpon = buildUpon.appendQueryParameter("party_type", this.mPartyType);
            }
            cursor = this.mContext.getContentResolver().query(buildUpon.build(), null, null, null, null);
            if (cursor != null) {
                cursor.moveToFirst();
                String string = cursor.getString(cursor.getColumnIndexOrThrow("current_party_id"));
                if (string != null && !string.isEmpty()) {
                    String str = TAG;
                    Log.i(str, "Created a party with partyID: " + string + " redirecting to AUI");
                    this.mContext.sendBroadcast(new Intent("com.oculus.vrshell.intent.action.LAUNCH").setPackage("com.oculus.vrshell").putExtra("intent_data", Uri.parse(SystemUXRoute.AUI_SOCIAL.path())));
                }
            } else {
                Log.i(TAG, "Attempted to create a party but the party ID is missing.");
            }
            if (cursor == null) {
                return;
            }
        } catch (Exception e) {
            Log.e(TAG, "An error occured when attempting to create a party.", e);
            if (0 == 0) {
                return;
            }
        } catch (Throwable th) {
            if (0 != 0) {
                cursor.close();
            }
            throw th;
        }
        cursor.close();
    }

    private void launchBrowserForGuidelines() {
        this.mContext.sendBroadcast(new Intent("com.oculus.vrshell.intent.action.LAUNCH").setPackage("com.oculus.vrshell").putExtra("intent_data", Uri.parse(SystemUXRoute.DEFAULT_BROWSER.path())).putExtra(AssistantComponentContract.Components.RemoteImageViewComponent.URI, CommonSocialDialogConstants.COMMUNITY_GUIDELINES_URI));
    }
}
