package com.facebook.messengervr.msys;

import X.AnonymousClass0l0;
import android.annotation.SuppressLint;
import com.facebook.messengervrcqljava.TempMessageList;
import com.facebook.msys.mci.CQLResultSet;
import javax.annotation.Nullable;

@SuppressLint({"MissingNativeLoadLibrary"})
public class ChildResultSetUtils {
    @Nullable
    public static native CQLResultSet getReactionListFromTempMessageListNative(TempMessageList tempMessageList, int i);

    @Nullable
    public static native CQLResultSet getTempMessageAttachmentListFromTempMessageListNative(TempMessageList tempMessageList, int i);

    static {
        AnonymousClass0l0.A06("mcamailboxmessengervrchildresultsetutils");
    }
}
