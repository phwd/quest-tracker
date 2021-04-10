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

    @Override // oculus.internal.LockPatternUtilsInterface
    public void clearPattern(int userId, String oldPattern) {
        this.mLockPatternUtils.clearLock(oldPattern.getBytes(), userId, true);
    }

    @Override // oculus.internal.LockPatternUtilsInterface
    public void saveLockPattern(List<LockPatternView.Cell> pattern, String oldPattern, int userId) {
        this.mLockPatternUtils.saveLockPattern(pattern, oldPattern == null ? null : oldPattern.getBytes(), userId);
    }

    @Override // oculus.internal.LockPatternUtilsInterface
    public void setVisiblePatternEnabled(boolean enabled, int userId) {
        this.mLockPatternUtils.setVisiblePatternEnabled(enabled, userId);
    }

    @Override // oculus.internal.LockPatternUtilsInterface
    public void saveLockPassword(String password, String savedPassword, int requestedQuality, int userId) {
        this.mLockPatternUtils.saveLockPassword(password, savedPassword, requestedQuality, userId);
    }

    @Override // oculus.internal.LockPatternUtilsInterface
    public void setVisiblePasswordEnabled(boolean enabled, int userId) {
        this.mLockPatternUtils.setVisiblePasswordEnabled(enabled, userId);
    }

    @Override // oculus.internal.LockPatternUtilsInterface
    public void setLockScreenDisabled(boolean disabled, int userId) {
        this.mLockPatternUtils.setLockScreenDisabled(disabled, userId);
    }

    @Override // oculus.internal.LockPatternUtilsInterface
    public void dismissKeyguard(IKeyguardService service) throws RemoteException {
        service.dismiss((IKeyguardDismissCallback) null, "keyguard dismissed");
    }

    @Override // oculus.internal.LockPatternUtilsInterface
    public boolean checkPatternOrPassword(String pin, int userId) throws LockPatternUtils.RequestThrottledException {
        if (this.mLockPatternUtils.isLockPatternEnabled(userId)) {
            return this.mLockPatternUtils.checkPattern(LockPatternUtils.stringToPattern(pin), userId);
        }
        if (this.mLockPatternUtils.isLockPasswordEnabled(userId)) {
            return this.mLockPatternUtils.checkPassword(pin, userId);
        }
        return false;
    }

    public static List<LockPatternView.Cell> stringToPattern(String string) {
        return LockPatternUtils.byteArrayToPattern(string.getBytes());
    }
}
