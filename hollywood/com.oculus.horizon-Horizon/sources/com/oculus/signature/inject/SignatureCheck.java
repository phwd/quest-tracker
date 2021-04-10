package com.oculus.signature.inject;

import X.AbstractC06640p5;
import X.AnonymousClass0QC;
import com.facebook.common.stringformat.StringFormatUtil;
import com.facebook.inject.Assisted;
import com.facebook.ultralight.Dependencies;
import com.facebook.ultralight.Eager;
import com.facebook.ultralight.Inject;
import com.oculus.debug.DebugMode;
import com.oculus.unlockulus_helper.UnlockulusHelper;

@Dependencies({"_UL__ULSEP_android_content_Context_ULSEP_com_facebook_inject_ForAppContext_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_debug_DebugMode_ULSEP_BINDING_ID"})
public class SignatureCheck {
    public static final Class<?> TAG = SignatureCheck.class;
    public AnonymousClass0QC _UL_mInjectionContext;
    @Inject
    @Eager
    public final DebugMode mDebugMode;
    public CheckState mFirstPartyDebugSignature;
    public CheckState mFirstPartyProductionSignature;
    public CheckState mIsTrusted;
    public CheckState mSelfCheck;
    public CheckState mSelfDebugBuild;
    public CheckState mUnlockulus;
    public final UnlockulusHelper mUnlockulusHelper = new UnlockulusHelper();
    public final String packageName;

    public enum CheckState {
        UNCHECKED,
        PASSED,
        FAILED
    }

    /* JADX WARNING: Removed duplicated region for block: B:16:0x0046  */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x0081  */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x0098  */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x009e  */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x00aa A[ORIG_RETURN, RETURN, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x00ac  */
    /* JADX WARNING: Removed duplicated region for block: B:45:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean A00() {
        /*
        // Method dump skipped, instructions count: 185
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.signature.inject.SignatureCheck.A00():boolean");
    }

    public final String toString() {
        return StringFormatUtil.formatStrLocaleSafe("%s(packageName = %s, isTrusted = %s)[self = %s, firstPartyProductionSignature = %s, firstPartyDebugSignature = %s, selfDebugBuild = %s, unlockulus = %s]", "SignatureCheck", this.packageName, this.mIsTrusted.name(), this.mSelfCheck.name(), this.mFirstPartyProductionSignature.name(), this.mFirstPartyDebugSignature.name(), this.mSelfDebugBuild.name(), this.mUnlockulus.name());
    }

    @Inject
    public SignatureCheck(AbstractC06640p5 r3, @Assisted String str) {
        CheckState checkState = CheckState.UNCHECKED;
        this.mIsTrusted = checkState;
        this.mSelfCheck = checkState;
        this.mFirstPartyProductionSignature = checkState;
        this.mFirstPartyDebugSignature = checkState;
        this.mSelfDebugBuild = checkState;
        this.mUnlockulus = checkState;
        this._UL_mInjectionContext = new AnonymousClass0QC(1, r3);
        this.mDebugMode = DebugMode.A00(r3);
        this.packageName = str;
    }
}
