package com.oculus.modules.codegen;

import android.util.Pair;
import com.oculus.ocms.library.provider.contract.OCMSLibraryContract;
import com.oculus.panellib.modules.RCTBaseJavaModule;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

public abstract class UserManagerModule extends RCTBaseJavaModule {
    protected static final String MODULE_NAME = UserManagerModule.class.getSimpleName();

    public enum LibrarySharingResultCode {
        CANT_CONNECT_TO_SERVICE,
        ERROR_UNKNOWN,
        NETWORK_ERROR,
        RESULT_OK
    }

    /* access modifiers changed from: protected */
    public abstract void addUserImpl();

    /* access modifiers changed from: protected */
    public abstract void canAddMoreUsersImpl(Resolver<Boolean> resolver);

    /* access modifiers changed from: protected */
    public abstract void getHasSeenMuAccountsNuxImpl(Resolver<Boolean> resolver);

    /* access modifiers changed from: protected */
    public abstract void getIsMultiUserBetaEnabledImpl(Resolver<Boolean> resolver);

    /* access modifiers changed from: protected */
    public abstract void getSelfImpl(Resolver<OculusUserType> resolver);

    /* access modifiers changed from: protected */
    public abstract void getUsersImpl(Resolver<List<OculusUserType>> resolver);

    /* access modifiers changed from: protected */
    public abstract void hasSeenAppSharingUpsellImpl(Resolver<Boolean> resolver);

    /* access modifiers changed from: protected */
    public abstract void hasSeenPatternPromptImpl(Resolver<Boolean> resolver);

    /* access modifiers changed from: protected */
    public abstract void isLibrarySharingEnabledImpl(Resolver<Boolean> resolver);

    /* access modifiers changed from: protected */
    public abstract void isLocalAccountModeEnabledImpl(Resolver<Boolean> resolver);

    /* access modifiers changed from: protected */
    public abstract void isSystemUserImpl(Resolver<Boolean> resolver);

    /* access modifiers changed from: protected */
    public abstract void launchUnlockPatternImpl(String str);

    /* access modifiers changed from: protected */
    public abstract void launchUnlockPatternWithReturnUriImpl(String str, String str2);

    /* access modifiers changed from: protected */
    public abstract boolean marshallJSConstantIsMultiUserEnabled();

    /* access modifiers changed from: protected */
    public abstract void removeSelfImpl(Resolver<Void> resolver);

    /* access modifiers changed from: protected */
    public abstract void removeUserImpl(double d, Resolver<Void> resolver);

    /* access modifiers changed from: protected */
    public abstract void setHasSeenAppSharingUpsellImpl(boolean z);

    /* access modifiers changed from: protected */
    public abstract void setHasSeenMuAccountsNuxImpl(boolean z);

    /* access modifiers changed from: protected */
    public abstract void setHasSeenPatternPromptImpl(boolean z);

    /* access modifiers changed from: protected */
    public abstract void setIsLibrarySharingEnabledImpl(boolean z, Resolver<LibrarySharingResultCode> resolver);

    /* access modifiers changed from: protected */
    public abstract void setIsLocalAccountModeEnabledImpl(boolean z);

    /* access modifiers changed from: protected */
    public abstract void setIsMultiUserBetaEnabledImpl(boolean z);

    /* access modifiers changed from: protected */
    public final String getModuleName() {
        return MODULE_NAME;
    }

    protected static final List<Pair<String, String>> describe() {
        List<Pair<String, String>> list = new ArrayList<>();
        list.add(new Pair<>("addUser", ""));
        list.add(new Pair<>("canAddMoreUsers", "+ii"));
        list.add(new Pair<>("getHasSeenMuAccountsNux", "+ii"));
        list.add(new Pair<>("getIsMultiUserBetaEnabled", "+ii"));
        list.add(new Pair<>("getSelf", "+ii"));
        list.add(new Pair<>("getUsers", "+ii"));
        list.add(new Pair<>("hasSeenAppSharingUpsell", "+ii"));
        list.add(new Pair<>("hasSeenPatternPrompt", "+ii"));
        list.add(new Pair<>("isLibrarySharingEnabled", "+ii"));
        list.add(new Pair<>("isLocalAccountModeEnabled", "+ii"));
        list.add(new Pair<>("isSystemUser", "+ii"));
        list.add(new Pair<>("launchUnlockPattern", "s"));
        list.add(new Pair<>("launchUnlockPatternWithReturnUri", "ss"));
        list.add(new Pair<>("removeSelf", "+ii"));
        list.add(new Pair<>("removeUser", "+dii"));
        list.add(new Pair<>("setHasSeenAppSharingUpsell", "b"));
        list.add(new Pair<>("setHasSeenMuAccountsNux", "b"));
        list.add(new Pair<>("setHasSeenPatternPrompt", "b"));
        list.add(new Pair<>("setIsLibrarySharingEnabled", "+bii"));
        list.add(new Pair<>("setIsLocalAccountModeEnabled", "b"));
        list.add(new Pair<>("setIsMultiUserBetaEnabled", "b"));
        return list;
    }

    /* access modifiers changed from: protected */
    public final String marshallJSConstants() {
        JSONObject result = new JSONObject();
        try {
            result.put("isMultiUserEnabled", marshallJSConstantIsMultiUserEnabled());
        } catch (JSONException e) {
        }
        return result.toString();
    }

    /* access modifiers changed from: protected */
    public final void addUser() {
        addUserImpl();
    }

    /* access modifiers changed from: protected */
    public final void canAddMoreUsers(int resolveID, int rejectID) {
        canAddMoreUsersImpl(ResolverFactory.createBasicResolver(this, resolveID, rejectID));
    }

    /* access modifiers changed from: protected */
    public final void getHasSeenMuAccountsNux(int resolveID, int rejectID) {
        getHasSeenMuAccountsNuxImpl(ResolverFactory.createBasicResolver(this, resolveID, rejectID));
    }

    /* access modifiers changed from: protected */
    public final void getIsMultiUserBetaEnabled(int resolveID, int rejectID) {
        getIsMultiUserBetaEnabledImpl(ResolverFactory.createBasicResolver(this, resolveID, rejectID));
    }

    /* access modifiers changed from: protected */
    public final void getSelf(int resolveID, int rejectID) {
        getSelfImpl(ResolverFactory.createParcelResolver(this, resolveID, rejectID));
    }

    /* access modifiers changed from: protected */
    public final void getUsers(int resolveID, int rejectID) {
        getUsersImpl(ResolverFactory.createParcelListResolver(this, resolveID, rejectID));
    }

    /* access modifiers changed from: protected */
    public final void hasSeenAppSharingUpsell(int resolveID, int rejectID) {
        hasSeenAppSharingUpsellImpl(ResolverFactory.createBasicResolver(this, resolveID, rejectID));
    }

    /* access modifiers changed from: protected */
    public final void hasSeenPatternPrompt(int resolveID, int rejectID) {
        hasSeenPatternPromptImpl(ResolverFactory.createBasicResolver(this, resolveID, rejectID));
    }

    /* access modifiers changed from: protected */
    public final void isLibrarySharingEnabled(int resolveID, int rejectID) {
        isLibrarySharingEnabledImpl(ResolverFactory.createBasicResolver(this, resolveID, rejectID));
    }

    /* access modifiers changed from: protected */
    public final void isLocalAccountModeEnabled(int resolveID, int rejectID) {
        isLocalAccountModeEnabledImpl(ResolverFactory.createBasicResolver(this, resolveID, rejectID));
    }

    /* access modifiers changed from: protected */
    public final void isSystemUser(int resolveID, int rejectID) {
        isSystemUserImpl(ResolverFactory.createBasicResolver(this, resolveID, rejectID));
    }

    /* access modifiers changed from: protected */
    public final void launchUnlockPattern(String mode) {
        launchUnlockPatternImpl(mode);
    }

    /* access modifiers changed from: protected */
    public final void launchUnlockPatternWithReturnUri(String mode, String returnUri) {
        launchUnlockPatternWithReturnUriImpl(mode, returnUri);
    }

    /* access modifiers changed from: protected */
    public final void removeSelf(int resolveID, int rejectID) {
        removeSelfImpl(ResolverFactory.createVoidResolver(this, resolveID, rejectID));
    }

    /* access modifiers changed from: protected */
    public final void removeUser(double userId, int resolveID, int rejectID) {
        removeUserImpl(userId, ResolverFactory.createVoidResolver(this, resolveID, rejectID));
    }

    /* access modifiers changed from: protected */
    public final void setHasSeenAppSharingUpsell(boolean seen) {
        setHasSeenAppSharingUpsellImpl(seen);
    }

    /* access modifiers changed from: protected */
    public final void setHasSeenMuAccountsNux(boolean hasSeenMuNux) {
        setHasSeenMuAccountsNuxImpl(hasSeenMuNux);
    }

    /* access modifiers changed from: protected */
    public final void setHasSeenPatternPrompt(boolean seen) {
        setHasSeenPatternPromptImpl(seen);
    }

    /* access modifiers changed from: protected */
    public final void setIsLibrarySharingEnabled(boolean isEnabled, int resolveID, int rejectID) {
        setIsLibrarySharingEnabledImpl(isEnabled, ResolverFactory.createEnumResolver(this, resolveID, rejectID));
    }

    /* access modifiers changed from: protected */
    public final void setIsLocalAccountModeEnabled(boolean isEnabled) {
        setIsLocalAccountModeEnabledImpl(isEnabled);
    }

    /* access modifiers changed from: protected */
    public final void setIsMultiUserBetaEnabled(boolean isEnabled) {
        setIsMultiUserBetaEnabledImpl(isEnabled);
    }

    public void shutdownModule() {
    }

    public static class OculusUserType extends NativeModuleParcel {
        public String dateCreated;
        public double id;
        public String name;
        public String pictureUri;

        @Override // com.oculus.modules.codegen.NativeModuleParcel
        public final JSONObject convertToJSONObject() {
            JSONObject parcel = new JSONObject();
            try {
                parcel.put("dateCreated", this.dateCreated);
                parcel.put(OCMSLibraryContract.ASSETS_PATH_BY_ID, this.id);
                parcel.put("name", this.name);
                parcel.put("pictureUri", this.pictureUri);
            } catch (JSONException e) {
            }
            return parcel;
        }

        @Override // com.oculus.modules.codegen.NativeModuleParcel
        public final void setFromJSONObject(JSONObject json) {
            this.dateCreated = json.optString("dateCreated");
            this.id = json.optDouble(OCMSLibraryContract.ASSETS_PATH_BY_ID, 0.0d);
            this.name = json.optString("name");
            this.pictureUri = json.optString("pictureUri");
        }

        public static final OculusUserType makeFromJSONObject(JSONObject json) {
            if (json == null) {
                return null;
            }
            OculusUserType result = new OculusUserType();
            result.setFromJSONObject(json);
            return result;
        }
    }
}
