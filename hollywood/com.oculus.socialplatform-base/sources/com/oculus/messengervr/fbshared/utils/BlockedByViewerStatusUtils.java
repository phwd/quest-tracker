package com.oculus.messengervr.fbshared.utils;

import android.annotation.TargetApi;
import com.facebook.infer.annotation.Nullsafe;
import com.oculus.messengervr.interfaces.BlockedByViewerStatus;

@TargetApi(25)
@Nullsafe(Nullsafe.Mode.LOCAL)
public final class BlockedByViewerStatusUtils {
    public static BlockedByViewerStatus transformBlockedByViewerStatus(Integer num) {
        int intValue = num.intValue();
        if (intValue == 0) {
            return BlockedByViewerStatus.UNBLOCKED;
        }
        if (intValue == 1) {
            return BlockedByViewerStatus.MESSAGE_BLOCKED;
        }
        if (intValue == 2) {
            return BlockedByViewerStatus.FULLY_BLOCKED;
        }
        StringBuilder sb = new StringBuilder("blockedByViewerStatus not recognized: ");
        sb.append(num);
        throw new RuntimeException(sb.toString());
    }
}
