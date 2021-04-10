package com.facebook.internal;

import X.AnonymousClass006;
import android.app.Activity;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookDialog;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.acra.AppComponentStats;
import java.util.Iterator;
import java.util.List;

public abstract class FacebookDialogBase<CONTENT, RESULT> implements FacebookDialog<CONTENT, RESULT> {
    public static final Object BASE_AUTOMATIC_MODE = new Object();
    public static final String TAG = "FacebookDialog";
    public final Activity activity;
    public final FragmentWrapper fragmentWrapper;
    public final List<FacebookDialogBase<CONTENT, RESULT>.ModeHandler> modeHandlers;
    public int requestCode;

    public abstract class ModeHandler {
        public abstract boolean canShow(CONTENT content);

        public abstract AppCall createAppCall(CONTENT content);

        public ModeHandler() {
        }

        public Object getMode() {
            return FacebookDialogBase.BASE_AUTOMATIC_MODE;
        }
    }

    public abstract AppCall createBaseAppCall();

    public abstract List<FacebookDialogBase<CONTENT, RESULT>.ModeHandler> getOrderedModeHandlers();

    public abstract void registerCallbackImpl(CallbackManagerImpl callbackManagerImpl, FacebookCallback<RESULT> facebookCallback);

    private List<FacebookDialogBase<CONTENT, RESULT>.ModeHandler> cachedModeHandlers() {
        List<FacebookDialogBase<CONTENT, RESULT>.ModeHandler> list = this.modeHandlers;
        if (list != null) {
            return list;
        }
        throw null;
    }

    private AppCall createAppCallForMode(CONTENT content, Object obj) {
        Object obj2 = BASE_AUTOMATIC_MODE;
        boolean z = false;
        if (obj == obj2) {
            z = true;
        }
        Iterator<FacebookDialogBase<CONTENT, RESULT>.ModeHandler> it = cachedModeHandlers().iterator();
        while (it.hasNext()) {
            it.next();
            if (z) {
                throw null;
            } else if (Utility.areObjectsEqual(obj2, obj)) {
                throw null;
            }
        }
        throw null;
    }

    @Override // com.facebook.FacebookDialog
    public boolean canShow(CONTENT content) {
        return canShowImpl(content, BASE_AUTOMATIC_MODE);
    }

    public boolean canShowImpl(CONTENT content, Object obj) {
        Object obj2 = BASE_AUTOMATIC_MODE;
        boolean z = false;
        if (obj == obj2) {
            z = true;
        }
        Iterator<FacebookDialogBase<CONTENT, RESULT>.ModeHandler> it = cachedModeHandlers().iterator();
        while (it.hasNext()) {
            it.next();
            if (z) {
                throw null;
            } else if (Utility.areObjectsEqual(obj2, obj)) {
                throw null;
            }
        }
        return false;
    }

    public Activity getActivityContext() {
        Activity activity2 = this.activity;
        if (activity2 != null) {
            return activity2;
        }
        FragmentWrapper fragmentWrapper2 = this.fragmentWrapper;
        if (fragmentWrapper2 != null) {
            return fragmentWrapper2.getActivity();
        }
        return null;
    }

    @Override // com.facebook.FacebookDialog
    public void show(CONTENT content) {
        showImpl(content, BASE_AUTOMATIC_MODE);
        throw new RuntimeException("Redex: Unreachable code after no-return invoke");
    }

    public int getRequestCode() {
        return this.requestCode;
    }

    public void setRequestCode(int i) {
        if (!FacebookSdk.isFacebookRequestCode(i)) {
            this.requestCode = i;
            return;
        }
        throw new IllegalArgumentException(AnonymousClass006.A02("Request code ", i, " cannot be within the range reserved by the Facebook SDK."));
    }

    public void showImpl(CONTENT content, Object obj) {
        createAppCallForMode(content, obj);
        throw new RuntimeException("Redex: Unreachable code after no-return invoke");
    }

    public FacebookDialogBase(Activity activity2, int i) {
        Validate.notNull(activity2, AppComponentStats.TAG_ACTIVITY);
        this.activity = activity2;
        this.fragmentWrapper = null;
        this.requestCode = i;
    }

    public FacebookDialogBase(FragmentWrapper fragmentWrapper2, int i) {
        Validate.notNull(fragmentWrapper2, "fragmentWrapper");
        this.fragmentWrapper = fragmentWrapper2;
        this.activity = null;
        this.requestCode = i;
        if (fragmentWrapper2.getActivity() == null) {
            throw new IllegalArgumentException("Cannot use a fragment that is not attached to an activity");
        }
    }

    @Override // com.facebook.FacebookDialog
    public final void registerCallback(CallbackManager callbackManager, FacebookCallback<RESULT> facebookCallback) {
        if (callbackManager instanceof CallbackManagerImpl) {
            throw null;
        }
        throw new FacebookException("Unexpected CallbackManager, please use the provided Factory.");
    }

    @Override // com.facebook.FacebookDialog
    public final void registerCallback(CallbackManager callbackManager, FacebookCallback<RESULT> facebookCallback, int i) {
        setRequestCode(i);
        registerCallback(callbackManager, facebookCallback);
        throw new RuntimeException("Redex: Unreachable code after no-return invoke");
    }
}
