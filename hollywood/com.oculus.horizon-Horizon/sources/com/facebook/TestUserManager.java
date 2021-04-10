package com.facebook;

import X.AnonymousClass006;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import com.facebook.internal.Utility;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class TestUserManager {
    public static final /* synthetic */ boolean $assertionsDisabled = false;
    public static final String LOG_TAG = "TestUserManager";
    public Map<String, JSONObject> appTestAccounts;
    public String testApplicationId;
    public String testApplicationSecret;

    public enum Mode {
        PRIVATE,
        SHARED
    }

    private synchronized JSONObject findTestAccountMatchingIdentifier(String str) {
        for (JSONObject jSONObject : this.appTestAccounts.values()) {
            if (jSONObject.optString("name").contains(str)) {
                return jSONObject;
            }
        }
        return null;
    }

    private AccessToken getAccessTokenForUser(List<String> list, Mode mode, String str) {
        JSONObject findOrCreateSharedTestAccount;
        List<String> list2 = list;
        retrieveTestAccountsForAppIfNeeded();
        if (Utility.isNullOrEmpty(list)) {
            list2 = Arrays.asList("email", "publish_actions");
        }
        if (mode == Mode.PRIVATE) {
            findOrCreateSharedTestAccount = createTestAccount(list2, mode, str);
        } else {
            findOrCreateSharedTestAccount = findOrCreateSharedTestAccount(list2, mode, str);
        }
        return new AccessToken(findOrCreateSharedTestAccount.optString("access_token"), this.testApplicationId, findOrCreateSharedTestAccount.optString("id"), list2, null, AccessTokenSource.TEST_USER, null, null);
    }

    private synchronized void populateTestAccounts(JSONArray jSONArray, JSONObject jSONObject) {
        for (int i = 0; i < jSONArray.length(); i++) {
            JSONObject optJSONObject = jSONArray.optJSONObject(i);
            try {
                optJSONObject.put("name", jSONObject.optJSONObject(optJSONObject.optString("id")).optString("name"));
            } catch (JSONException e) {
                Log.e(LOG_TAG, "Could not set name", e);
            }
            storeTestAccount(optJSONObject);
        }
    }

    private synchronized void retrieveTestAccountsForAppIfNeeded() {
        if (this.appTestAccounts == null) {
            this.appTestAccounts = new HashMap();
            GraphRequest.defaultBatchApplicationId = this.testApplicationId;
            Bundle bundle = new Bundle();
            bundle.putString("access_token", getAppAccessToken());
            GraphRequest graphRequest = new GraphRequest(null, "app/accounts/test-users", bundle, null);
            graphRequest.batchEntryName = "testUsers";
            graphRequest.batchEntryOmitResultOnSuccess = false;
            Bundle bundle2 = new Bundle();
            bundle2.putString("access_token", getAppAccessToken());
            bundle2.putString("ids", "{result=testUsers:$.data.*.id}");
            bundle2.putString("fields", "name");
            GraphRequest graphRequest2 = new GraphRequest(null, "", bundle2, null);
            graphRequest2.batchEntryDependsOn = "testUsers";
            List<GraphResponse> executeBatchAndWait = GraphRequest.executeBatchAndWait(graphRequest, graphRequest2);
            if (executeBatchAndWait == null || executeBatchAndWait.size() != 2) {
                throw new FacebookException("Unexpected number of results from TestUsers batch query");
            }
            populateTestAccounts(executeBatchAndWait.get(0).graphObject.optJSONArray("data"), executeBatchAndWait.get(1).graphObject);
        }
    }

    private synchronized void storeTestAccount(JSONObject jSONObject) {
        this.appTestAccounts.put(jSONObject.optString("id"), jSONObject);
    }

    public synchronized String getTestApplicationId() {
        return this.testApplicationId;
    }

    public synchronized String getTestApplicationSecret() {
        return this.testApplicationSecret;
    }

    private JSONObject createTestAccount(List<String> list, Mode mode, String str) {
        Bundle bundle = new Bundle();
        bundle.putString("installed", "true");
        bundle.putString("permissions", TextUtils.join(",", list));
        bundle.putString("access_token", getAppAccessToken());
        if (mode == Mode.SHARED) {
            bundle.putString("name", String.format("Shared %s Testuser", getSharedTestAccountIdentifier(list, str)));
        }
        GraphResponse executeAndWait = GraphRequest.executeAndWait(new GraphRequest(null, String.format("%s/accounts/test-users", this.testApplicationId), bundle, HttpMethod.POST));
        FacebookRequestError facebookRequestError = executeAndWait.error;
        JSONObject jSONObject = executeAndWait.graphObject;
        if (facebookRequestError != null) {
            return null;
        }
        if (mode == Mode.SHARED) {
            try {
                jSONObject.put("name", bundle.getString("name"));
            } catch (JSONException e) {
                Log.e(LOG_TAG, "Could not set name", e);
            }
            storeTestAccount(jSONObject);
        }
        return jSONObject;
    }

    private String getPermissionsString(List<String> list) {
        return TextUtils.join(",", list);
    }

    private String getSharedTestAccountIdentifier(List<String> list, String str) {
        long j;
        long hashCode = ((long) TextUtils.join(",", list).hashCode()) & 4294967295L;
        if (str != null) {
            j = ((long) str.hashCode()) & 4294967295L;
        } else {
            j = 0;
        }
        return validNameStringFromInteger(j ^ hashCode);
    }

    public AccessToken getAccessTokenForPrivateUser(List<String> list) {
        return getAccessTokenForUser(list, Mode.PRIVATE, null);
    }

    public final String getAppAccessToken() {
        return AnonymousClass006.A07(this.testApplicationId, "|", this.testApplicationSecret);
    }

    public TestUserManager(String str, String str2) {
        if (Utility.isNullOrEmpty(str2) || Utility.isNullOrEmpty(str)) {
            throw new FacebookException("Must provide app ID and secret");
        }
        this.testApplicationSecret = str;
        this.testApplicationId = str2;
    }

    private JSONObject findOrCreateSharedTestAccount(List<String> list, Mode mode, String str) {
        JSONObject findTestAccountMatchingIdentifier = findTestAccountMatchingIdentifier(getSharedTestAccountIdentifier(list, str));
        if (findTestAccountMatchingIdentifier == null) {
            return createTestAccount(list, mode, str);
        }
        return findTestAccountMatchingIdentifier;
    }

    private String validNameStringFromInteger(long j) {
        String l = Long.toString(j);
        StringBuilder sb = new StringBuilder("Perm");
        char[] charArray = l.toCharArray();
        char c = 0;
        for (char c2 : charArray) {
            c = c2;
            if (c2 == c) {
                c = (char) (c2 + '\n');
            }
            sb.append((char) ((c + 'a') - 48));
        }
        return sb.toString();
    }

    public AccessToken getAccessTokenForSharedUser(List<String> list) {
        return getAccessTokenForSharedUser(list, null);
    }

    public AccessToken getAccessTokenForSharedUser(List<String> list, String str) {
        return getAccessTokenForUser(list, Mode.SHARED, str);
    }
}
