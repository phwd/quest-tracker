package oculus.internal;

import android.os.RemoteException;
import com.android.internal.policy.IKeyguardService;
import com.android.internal.widget.LockPatternUtils;
import com.android.internal.widget.LockPatternView;
import java.util.List;

public interface LockPatternUtilsInterface {
    boolean checkPatternOrPassword(String str, int i) throws LockPatternUtils.RequestThrottledException;

    void clearPattern(int i, String str);

    void dismissKeyguard(IKeyguardService iKeyguardService) throws RemoteException;

    void saveLockPassword(String str, String str2, int i, int i2);

    void saveLockPattern(List<LockPatternView.Cell> list, String str, int i);

    void setLockScreenDisabled(boolean z, int i);

    void setVisiblePasswordEnabled(boolean z, int i);

    void setVisiblePatternEnabled(boolean z, int i);
}
