package oculus.internal;

import android.content.Context;
import android.os.RemoteException;
import com.android.internal.policy.IKeyguardDismissCallback;
import com.android.internal.policy.IKeyguardService;
import com.android.internal.widget.LockPatternUtils;
import com.android.internal.widget.LockPatternView;
import java.util.List;

public class LockPatternUtilsCompat implements LockPatternUtilsInterface {
    LockPatternUtils mLockPatternUtils;

    public LockPatternUtilsCompat(Context context) {
        this.mLockPatternUtils = new LockPatternUtils(context);
    }

    public void clearPattern(int i, String str) {
        this.mLockPatternUtils.clearLock(str.getBytes(), i, true);
    }

    public void saveLockPattern(List<LockPatternView.Cell> list, String str, int i) {
        this.mLockPatternUtils.saveLockPattern(list, str == null ? null : str.getBytes(), i);
    }

    public void setVisiblePatternEnabled(boolean z, int i) {
        this.mLockPatternUtils.setVisiblePatternEnabled(z, i);
    }

    public void saveLockPassword(String str, String str2, int i, int i2) {
        this.mLockPatternUtils.saveLockPassword(str, str2, i, i2);
    }

    public void setVisiblePasswordEnabled(boolean z, int i) {
        this.mLockPatternUtils.setVisiblePasswordEnabled(z, i);
    }

    public void setLockScreenDisabled(boolean z, int i) {
        this.mLockPatternUtils.setLockScreenDisabled(z, i);
    }

    public void dismissKeyguard(IKeyguardService iKeyguardService) throws RemoteException {
        iKeyguardService.dismiss((IKeyguardDismissCallback) null, "keyguard dismissed");
    }

    public boolean checkPatternOrPassword(String str, int i) throws LockPatternUtils.RequestThrottledException {
        if (this.mLockPatternUtils.isLockPatternEnabled(i)) {
            return this.mLockPatternUtils.checkPattern(LockPatternUtils.stringToPattern(str), i);
        }
        if (this.mLockPatternUtils.isLockPasswordEnabled(i)) {
            return this.mLockPatternUtils.checkPassword(str, i);
        }
        return false;
    }
}
