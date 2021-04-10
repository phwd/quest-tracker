package com.facebook.tigon;

import X.C0233Lr;
import X.C0237Lv;
import X.C0998qK;
import X.C1000qR;
import X.LU;
import X.LV;
import X.Ln;
import X.Lx;
import X.Lz;
import X.M1;
import X.M5;
import X.M6;
import X.M9;
import X.MA;
import X.MB;
import X.MC;
import X.MD;
import android.text.TextUtils;
import com.facebook.tigon.iface.FacebookLoggingRequestInfo;
import com.facebook.tigon.iface.RedirectRequestInfo;
import com.facebook.tigon.iface.TigonRequestBuilder;
import java.util.Map;

public class TigonCallbacksIntegerBufferJavaHelper {
    public static void onEOM(TigonCallbacks tigonCallbacks, byte[] bArr, int i) {
        tigonCallbacks.onEOM(C0998qK.A06(new LV(bArr, i)));
    }

    public static void onResponse(TigonCallbacks tigonCallbacks, byte[] bArr, int i) {
        LV lv = new LV(bArr, i);
        LU.A00(lv);
        tigonCallbacks.onResponse(new C0233Lr(LU.A03(lv)));
    }

    public static void onStarted(TigonCallbacks tigonCallbacks, byte[] bArr, int i) {
        M6 m6;
        M1 m1;
        M9 m9;
        MC mc;
        LV lv = new LV(bArr, i);
        String A02 = LU.A02(lv);
        String A022 = LU.A02(lv);
        Map A03 = LU.A03(lv);
        byte A00 = lv.A00();
        Lx lx = new Lx(lv.A00(), LU.A04(lv));
        boolean A04 = LU.A04(lv);
        long A01 = LU.A01(lv);
        long A012 = LU.A01(lv);
        long A013 = LU.A01(lv);
        long A014 = LU.A01(lv);
        long A015 = LU.A01(lv);
        int A002 = LU.A00(lv);
        boolean A042 = LU.A04(lv);
        LU.A04(lv);
        String A023 = LU.A02(lv);
        int A003 = LU.A00(lv);
        long A016 = LU.A01(lv);
        Lz lz = null;
        FacebookLoggingRequestInfo facebookLoggingRequestInfo = LU.A04(lv) ? new FacebookLoggingRequestInfo(LU.A02(lv), LU.A02(lv), LU.A02(lv)) : null;
        if (LU.A04(lv)) {
            LU.A00(lv);
            LU.A00(lv);
            LU.A00(lv);
            LU.A00(lv);
            m6 = new M6();
        } else {
            m6 = null;
        }
        if (LU.A04(lv)) {
            LU.A04(lv);
            LU.A02(lv);
            m1 = new M1(new MD(LU.A03(lv)), LU.A03(lv));
        } else {
            m1 = null;
        }
        RedirectRequestInfo redirectRequestInfo = LU.A04(lv) ? new RedirectRequestInfo(LU.A04(lv)) : null;
        if (LU.A04(lv)) {
            LU.A01(lv);
            LU.A01(lv);
            m9 = new M9();
        } else {
            m9 = null;
        }
        MA ma = LU.A04(lv) ? new MA(LU.A02(lv), LU.A04(lv), LU.A04(lv), LU.A04(lv)) : null;
        if (LU.A04(lv)) {
            Map A032 = LU.A03(lv);
            mc = new MC();
            for (String str : A032.values()) {
                String[] split = str.split(":");
                int length = split.length;
                if (length >= 3 && length <= 4) {
                    String str2 = split[0];
                    if (str2.length() == 3) {
                        String str3 = split[1];
                        if (!(str3.length() == 0 || split[2].length() == 0)) {
                            MB mb = new MB(str3);
                            str2.charAt(0);
                            "ELT".charAt(0);
                            split[0].charAt(1);
                            "ELT".charAt(1);
                            split[0].charAt(2);
                            "ELT".charAt(2);
                            mc.A00.put(mb.A00, mb);
                        }
                    }
                }
            }
        } else {
            mc = null;
        }
        C0237Lv A07 = LU.A04(lv) ? C0998qK.A07(lv) : null;
        if (LU.A04(lv)) {
            LU.A02(lv);
            lz = new Lz();
        }
        TigonRequestBuilder tigonRequestBuilder = new TigonRequestBuilder();
        tigonRequestBuilder.mMethod = A02;
        tigonRequestBuilder.mUrl = A022;
        for (Map.Entry entry : A03.entrySet()) {
            String str4 = (String) entry.getKey();
            String str5 = (String) entry.getValue();
            if (!TextUtils.isEmpty(str4) && !TextUtils.isEmpty(str5)) {
                tigonRequestBuilder.mHeaders.put(str4, str5);
            }
        }
        tigonRequestBuilder.mTigonPriority = A00;
        tigonRequestBuilder.mHttpPriority = lx;
        tigonRequestBuilder.mRetryable = A04;
        if (A01 >= 0) {
            tigonRequestBuilder.mConnectionTimeoutMS = A01;
            if (A012 >= 0) {
                tigonRequestBuilder.mIdleTimeoutMS = A012;
                if (A013 >= 0) {
                    tigonRequestBuilder.mRequestTimeoutMS = A013;
                    tigonRequestBuilder.mSoftDeadlineMs = A014;
                    tigonRequestBuilder.mExpectedResponseSizeBytes = A015;
                    tigonRequestBuilder.mRequestType = A002;
                    tigonRequestBuilder.mFallbackToBackupHost = A042;
                    tigonRequestBuilder.mIsReliableMediaEnabled = true;
                    tigonRequestBuilder.mLoggingId = A023;
                    tigonRequestBuilder.mStartupStatusOnAdded = A003;
                    tigonRequestBuilder.mAddedToMiddlewareSinceEpochMS = A016;
                    tigonRequestBuilder.addLayerInformation(M5.A03, facebookLoggingRequestInfo);
                    tigonRequestBuilder.addLayerInformation(M5.A07, m6);
                    tigonRequestBuilder.addLayerInformation(M5.A04, m1);
                    tigonRequestBuilder.addLayerInformation(M5.A05, redirectRequestInfo);
                    tigonRequestBuilder.addLayerInformation(M5.A0A, m9);
                    tigonRequestBuilder.addLayerInformation(M5.A08, ma);
                    tigonRequestBuilder.addLayerInformation(M5.A09, mc);
                    tigonRequestBuilder.addLayerInformation(M5.A01, A07);
                    tigonRequestBuilder.addLayerInformation(M5.A06, lz);
                    tigonCallbacks.onStarted(new C1000qR(tigonRequestBuilder));
                    return;
                }
                throw new IllegalArgumentException("RequestTimeout should be nonzero.");
            }
            throw new IllegalArgumentException("IdleTimeout should be nonzero.");
        }
        throw new IllegalArgumentException("ConnectionTimeout should be nonzero.");
    }

    public static void onError(TigonCallbacks tigonCallbacks, byte[] bArr, int i) {
        Ln A05 = C0998qK.A05(bArr, i);
        tigonCallbacks.onError(A05.A00, A05.A01);
    }

    public static void onWillRetry(TigonCallbacks tigonCallbacks, byte[] bArr, int i) {
        Ln A05 = C0998qK.A05(bArr, i);
        tigonCallbacks.onWillRetry(A05.A00, A05.A01);
    }
}
