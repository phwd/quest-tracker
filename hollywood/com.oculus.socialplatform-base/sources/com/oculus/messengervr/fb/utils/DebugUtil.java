package com.oculus.messengervr.fb.utils;

import X.AnonymousClass006;
import X.AnonymousClass293;
import X.AnonymousClass298;
import androidx.annotation.Nullable;
import com.facebook.infer.annotation.Nullsafe;
import com.oculus.messengervr.interfaces.MessengerMessage;
import java.util.Iterator;
import java.util.Set;

@Nullsafe(Nullsafe.Mode.LOCAL)
public final class DebugUtil {
    public static boolean hasCapability(long j, int i) {
        long j2 = (long) (1 << i);
        return (j & j2) == j2;
    }

    public static boolean hasMessageCapability(@Nullable Integer num) {
        if (num == null) {
            return false;
        }
        return hasCapability((long) num.intValue(), 0);
    }

    public static void logMessageList(String str, MessengerMessage[] messengerMessageArr) {
        for (MessengerMessage messengerMessage : messengerMessageArr) {
            messengerMessage.getMessageId();
            messengerMessage.getTimestampMs();
            messengerMessage.getText();
            messengerMessage.getAttachmentType();
        }
    }

    public static String contactsBlockedByViewerStatus(int i) {
        if (i == 0) {
            return "UNBLOCKED";
        }
        if (i == 1) {
            return "MESSAGE_BLOCKED";
        }
        if (i == 2) {
            return "FULLY_BLOCKED";
        }
        throw new RuntimeException(AnonymousClass006.A03("blockedByViewerStatus not recognized: ", i));
    }

    public static void logContactList(String str, AnonymousClass293 r3) {
        r3.mResultSet.getCount();
        for (int i = 0; i < r3.mResultSet.getCount(); i++) {
            r3.mResultSet.getLong(i, 1);
            r3.mResultSet.getString(i, 5);
            contactsBlockedByViewerStatus(r3.mResultSet.getInteger(i, 0));
            r3.mResultSet.getNullableInteger(i, 18);
        }
    }

    public static void logThreadList(String str, AnonymousClass298 r3) {
        r3.mResultSet.getCount();
        for (int i = 0; i < r3.mResultSet.getCount(); i++) {
            r3.mResultSet.getLong(i, 4);
            r3.mResultSet.getString(i, 5);
        }
    }

    public static void logStoredProcedureNames(String str, Set<String> set) {
        set.size();
        Iterator<String> it = set.iterator();
        while (it.hasNext()) {
            it.next();
        }
    }

    public static void logMessageLoadMoreState(String str, int i, int i2) {
    }
}
