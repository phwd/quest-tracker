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
import com.oculus.notifications.NotificationConstants;
import com.oculus.notifications.NotificationSender;
import com.oculus.panelapp.dialog.CommonDialog;
import com.oculus.panelapp.dialog.R;
import com.oculus.systemdialog.CommonSystemDialog;
import com.oculus.systemdialog.CommonSystemDialogActions;
import com.oculus.systemdialog.DialogButton;
import com.oculus.systemdialog.DialogButtonText;
import com.oculus.systemdialog.DialogDefinitionCustom;
import com.oculus.systemdialog.DialogIcon;
import com.oculus.systemdialog.DialogInformationBox;
import com.oculus.vrshell.SystemUXRoute;
import java.util.Map;

public final class SocialJoinPartyDialog extends CommonDialog {
    public static final String ALIAS_COLUMN = "alias";
    public static final String GET_PARTY_INFO_WITH_MEMBERS_URI = "content://com.oculus.horizon.friendlist/party_get_info_with_members";
    public static final String PARTY_BLOCKED_USERS_COLUMN = "party_blocked_current_users";
    public static final String PARTY_ID_PARAM = "party_id";
    public static final String PARTY_JOIN_ERROR_COLUMN = "party_join_error";
    public static final String PARTY_JOIN_URI = "content://com.oculus.horizon.friendlist/party_join_with_error_callback";
    public static final String PARTY_MAX_SIZE = "party_max_size";
    public static final String PARTY_USERS_COLUMN = "party_users";
    private static final String TAG = LoggingUtil.tag(SocialJoinPartyDialog.class);
    private int mBlockedUserCount;
    private final Context mContext;
    private DialogDefinitionCustom mDefinition;
    private String mPartyID;
    private String mPartyLeaderAlias;
    private int mPartyMaxSize;
    private int mPartyMembersCount;
    private Resources mResources;

    public SocialJoinPartyDialog(Context context, Map<String, String> map) {
        this.mContext = context;
        this.mPartyID = getStringParameterOrDefault(map, "party_id", "");
        this.mResources = context.getResources();
        fetchPartyDataAsyncTask();
    }

    /* JADX WARNING: Removed duplicated region for block: B:17:0x0037  */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x0040  */
    @Override // com.oculus.panelapp.dialog.CommonDialog
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onAction(java.lang.String r4, java.lang.String[] r5) {
        /*
            r3 = this;
            int r5 = r4.hashCode()
            r0 = -1367724422(0xffffffffae7a2e7a, float:-5.68847E-11)
            r1 = 2
            r2 = 1
            if (r5 == r0) goto L_0x002a
            r0 = -508495908(0xffffffffe1b0f7dc, float:-4.080609E20)
            if (r5 == r0) goto L_0x0020
            r0 = 1911131724(0x71e98e4c, float:2.3130251E30)
            if (r5 == r0) goto L_0x0016
            goto L_0x0034
        L_0x0016:
            java.lang.String r5 = "communityGuidelines"
            boolean r4 = r4.equals(r5)
            if (r4 == 0) goto L_0x0034
            r4 = r1
            goto L_0x0035
        L_0x0020:
            java.lang.String r5 = "joinParty"
            boolean r4 = r4.equals(r5)
            if (r4 == 0) goto L_0x0034
            r4 = 0
            goto L_0x0035
        L_0x002a:
            java.lang.String r5 = "cancel"
            boolean r4 = r4.equals(r5)
            if (r4 == 0) goto L_0x0034
            r4 = r2
            goto L_0x0035
        L_0x0034:
            r4 = -1
        L_0x0035:
            if (r4 == 0) goto L_0x0040
            if (r4 == r2) goto L_0x0043
            if (r4 == r1) goto L_0x003c
            goto L_0x0043
        L_0x003c:
            r3.launchBrowserForGuidelines()
            goto L_0x0043
        L_0x0040:
            r3.joinPartyAsyncTask()
        L_0x0043:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.panelapp.dialog.commonsystemdialogs.social.SocialJoinPartyDialog.onAction(java.lang.String, java.lang.String[]):void");
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setDialogConfiguration() {
        this.mDefinition = new DialogDefinitionCustom(CommonSystemDialog.SOCIAL_JOIN_PARTY.getDialogId(), getDialogTitleText(), getDialogBodyText());
        if (this.mBlockedUserCount > 0) {
            this.mDefinition.setInformationBox(new DialogInformationBox(getDialogInformationBoxText(), DialogIcon.InformationBox.INFO));
        }
        this.mDefinition.setPrimaryButton(new DialogButtonText(CommonSystemDialogActions.JOIN_PARTY, this.mResources.getString(R.string.social_join_party_join)));
        this.mDefinition.setSecondaryButton(new DialogButtonText("cancel", this.mResources.getString(R.string.social_join_party_cancel)));
        this.mDefinition.setTertiaryButton(new DialogButtonText(CommonSystemDialogActions.COMMUNITY_GUIDELINES, this.mResources.getString(R.string.social_party_community_guidelines)));
        this.mDefinition.setControllerBackButton(new DialogButton("cancel"));
        setPendingDialogConfiguration(this.mDefinition);
    }

    private void fetchPartyDataAsyncTask() {
        new AsyncTask<Void, Void, Void>() {
            /* class com.oculus.panelapp.dialog.commonsystemdialogs.social.SocialJoinPartyDialog.AnonymousClass1 */

            /* access modifiers changed from: protected */
            public Void doInBackground(Void... voidArr) {
                if (SocialJoinPartyDialog.this.fetchPartyData()) {
                    Log.i(SocialJoinPartyDialog.TAG, "Setting dialog configs after fetching fetching party info.");
                    SocialJoinPartyDialog.this.setDialogConfiguration();
                    return null;
                }
                Log.i(SocialJoinPartyDialog.TAG, "Unable to set dialog configs because party info is missing.");
                return null;
            }
        }.execute(new Void[0]);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private boolean fetchPartyData() {
        String str = this.mPartyID;
        if (str == null || str.isEmpty()) {
            return false;
        }
        Cursor cursor = null;
        try {
            Cursor query = this.mContext.getContentResolver().query(Uri.parse(GET_PARTY_INFO_WITH_MEMBERS_URI).buildUpon().appendQueryParameter("party_id", this.mPartyID).build(), null, null, null, null);
            if (query != null) {
                query.moveToFirst();
                int columnIndexOrThrow = query.getColumnIndexOrThrow("alias");
                int columnIndexOrThrow2 = query.getColumnIndexOrThrow("party_max_size");
                int columnIndexOrThrow3 = query.getColumnIndexOrThrow("party_blocked_current_users");
                int columnIndexOrThrow4 = query.getColumnIndexOrThrow("party_users");
                this.mPartyLeaderAlias = query.getString(columnIndexOrThrow);
                this.mPartyMaxSize = Integer.parseInt(query.getString(columnIndexOrThrow2));
                this.mBlockedUserCount = Integer.parseInt(query.getString(columnIndexOrThrow3));
                this.mPartyMembersCount = Integer.parseInt(query.getString(columnIndexOrThrow4));
                if (query == null) {
                    return true;
                }
                query.close();
                return true;
            }
            Log.i(TAG, "Attempted to fetch party info but result is missing.");
            if (query != null) {
                query.close();
            }
            return false;
        } catch (Exception e) {
            Log.e(TAG, "An error occured when attempting to fetch party info.", e);
            if (0 != 0) {
                cursor.close();
            }
            return false;
        } catch (Throwable th) {
            if (0 != 0) {
                cursor.close();
            }
            throw th;
        }
    }

    private void joinPartyAsyncTask() {
        new AsyncTask<Void, Void, Void>() {
            /* class com.oculus.panelapp.dialog.commonsystemdialogs.social.SocialJoinPartyDialog.AnonymousClass2 */

            /* access modifiers changed from: protected */
            public Void doInBackground(Void... voidArr) {
                SocialJoinPartyDialog.this.joinParty();
                return null;
            }
        }.execute(new Void[0]);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void joinParty() {
        Cursor cursor = null;
        try {
            cursor = this.mContext.getContentResolver().query(Uri.parse(PARTY_JOIN_URI).buildUpon().appendQueryParameter("party_id", this.mPartyID).build(), null, null, null, null);
            if (cursor != null) {
                cursor.moveToFirst();
                String string = cursor.getString(cursor.getColumnIndexOrThrow("party_join_error"));
                if (string != null && !string.isEmpty()) {
                    String str = TAG;
                    Log.i(str, "Party join is unsuccessful and results contain an error: " + string);
                    showToast("oculus_mobile_social_public_party_join_error", string);
                }
                if (cursor != null) {
                    cursor.close();
                    return;
                }
                return;
            }
            Log.i(TAG, "Party join is successfull!");
            redirectToAUISocialView();
            if (cursor != null) {
                cursor.close();
            }
        } catch (Exception e) {
            Log.e(TAG, "An error occured when attempting to join party.", e);
            if (0 == 0) {
            }
        } catch (Throwable th) {
            if (0 != 0) {
                cursor.close();
            }
            throw th;
        }
    }

    private void showToast(String str, String str2) {
        NotificationSender.build(str, this.mResources.getString(R.string.social_join_party_error_title), str2, R.drawable.ic_notif_alert).setIconImageType(NotificationConstants.LargeImageType.INVALID).send(this.mContext);
    }

    private void redirectToAUISocialView() {
        this.mContext.sendBroadcast(new Intent("com.oculus.vrshell.intent.action.LAUNCH").setPackage("com.oculus.vrshell").putExtra("intent_data", Uri.parse(SystemUXRoute.AUI_SOCIAL.path())));
    }

    private void launchBrowserForGuidelines() {
        this.mContext.sendBroadcast(new Intent("com.oculus.vrshell.intent.action.LAUNCH").setPackage("com.oculus.vrshell").putExtra("intent_data", Uri.parse(SystemUXRoute.DEFAULT_BROWSER.path())).putExtra(AssistantComponentContract.Components.RemoteImageViewComponent.URI, CommonSocialDialogConstants.COMMUNITY_GUIDELINES_URI));
    }

    private String getDialogTitleText() {
        return this.mResources.getString(R.string.social_join_public_party_title, this.mPartyLeaderAlias);
    }

    private String getDialogBodyText() {
        String str;
        if (this.mPartyMembersCount == 0 || this.mPartyMaxSize == 0) {
            str = "" + this.mResources.getString(R.string.social_join_public_party_member_count_zero);
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append("");
            Resources resources = this.mResources;
            int i = R.plurals.social_join_public_party_member_count;
            int i2 = this.mPartyMembersCount;
            sb.append(resources.getQuantityString(i, i2, Integer.valueOf(i2)));
            sb.append(this.mResources.getString(R.string.social_join_public_party_open_slot_count, Integer.valueOf(this.mPartyMaxSize - this.mPartyMembersCount), Integer.valueOf(this.mPartyMaxSize)));
            str = sb.toString();
        }
        return str + this.mResources.getString(R.string.social_join_public_party_info);
    }

    private String getDialogInformationBoxText() {
        return this.mResources.getString(R.string.social_join_public_party_blocked_user_info);
    }
}
