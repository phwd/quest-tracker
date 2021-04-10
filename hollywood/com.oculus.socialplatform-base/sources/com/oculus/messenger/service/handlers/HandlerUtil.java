package com.oculus.messenger.service.handlers;

import X.AbstractC02170iC;
import X.AbstractC03180ld;
import X.AbstractC05710wh;
import X.AnonymousClass006;
import X.AnonymousClass00r;
import X.AnonymousClass04L;
import X.AnonymousClass0Hr;
import X.AnonymousClass0RE;
import X.AnonymousClass0VC;
import X.AnonymousClass0VF;
import X.AnonymousClass0lg;
import X.AnonymousClass1TK;
import X.C02130i6;
import X.C03620oC;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import com.facebook.ultralight.AutoGeneratedAccessMethod;
import com.facebook.ultralight.AutoGeneratedFactoryMethod;
import com.facebook.ultralight.Dependencies;
import com.facebook.ultralight.Inject;
import com.google.common.collect.ImmutableList;
import java.util.Map;
import java.util.Set;
import javax.annotation.Nullable;
import javax.inject.Provider;

@Dependencies({"_UL__ULSEP_com_facebook_common_json_FbObjectMapper_ULSEP_BINDING_ID"})
public final class HandlerUtil {
    public static final String CALL_ID_KEY = "call_id";
    public static final String MSG_RESULT_KEY = "result";
    public static final String TAG = "HandlerUtil";
    public AnonymousClass0RE _UL_mInjectionContext;

    public static class MessageTypes {
        public static final int ERROR = -1;
    }

    public void sendGenericReply(Set<Messenger> set, Message message, boolean z) {
        if (z) {
            sendReply(set, message.getData().getInt("call_id", -1), message.what, message.replyTo, ((C02130i6) AnonymousClass0VF.A03(0, 20, this._UL_mInjectionContext)).A04());
        } else {
            sendErrorReply(set, message, "Failed in Messenger Manager");
        }
    }

    @AutoGeneratedAccessMethod
    public static final AbstractC03180ld _UL__ULSEP_com_facebook_inject_Lazy_ULLT_com_oculus_messenger_service_handlers_HandlerUtil_ULGT__ULSEP_ACCESS_METHOD(AnonymousClass0lg r1) {
        return AnonymousClass0Hr.A00(14, r1);
    }

    @AutoGeneratedAccessMethod
    public static final HandlerUtil _UL__ULSEP_com_oculus_messenger_service_handlers_HandlerUtil_ULSEP_ACCESS_METHOD(AnonymousClass0lg r2) {
        return (HandlerUtil) AnonymousClass1TK.A00(14, r2, null);
    }

    @AutoGeneratedFactoryMethod
    public static final HandlerUtil _UL__ULSEP_com_oculus_messenger_service_handlers_HandlerUtil_ULSEP_FACTORY_METHOD(AnonymousClass0lg r1, Object obj) {
        return new HandlerUtil(r1);
    }

    @AutoGeneratedAccessMethod
    public static final Provider _UL__ULSEP_javax_inject_Provider_ULLT_com_oculus_messenger_service_handlers_HandlerUtil_ULGT__ULSEP_ACCESS_METHOD(AnonymousClass0lg r1) {
        return AnonymousClass0VC.A00(14, r1);
    }

    private void sendReply(Set<Messenger> set, int i, int i2, Messenger messenger, AnonymousClass04L r9) {
        AnonymousClass00r r0;
        Map<String, AbstractC02170iC> map = r9.A00;
        if (i > 10 || i < -1) {
            r0 = new AnonymousClass00r(i);
        } else {
            r0 = AnonymousClass00r.A01[i - -1];
        }
        map.put("callId", r0);
        try {
            String A06 = ((C02130i6) AnonymousClass0VF.A03(0, 20, this._UL_mInjectionContext)).A06(r9);
            Bundle bundle = new Bundle();
            bundle.putString("result", A06);
            Message obtain = Message.obtain((Handler) null, i2);
            obtain.setData(bundle);
            try {
                messenger.send(obtain);
            } catch (RemoteException unused) {
                set.remove(messenger);
            }
        } catch (C03620oC unused2) {
        }
    }

    public boolean isMessageValid(Set<Messenger> set, Message message, @Nullable ImmutableList<String> immutableList) {
        String A07;
        if (!set.contains(message.replyTo)) {
            A07 = "Invalid caller, aborting";
        } else if (immutableList == null) {
            return true;
        } else {
            Bundle data = message.getData();
            AbstractC05710wh<String> A0I = immutableList.iterator();
            while (A0I.hasNext()) {
                String next = A0I.next();
                if (!data.containsKey(next)) {
                    A07 = AnonymousClass006.A07("Missing param: ", next);
                }
            }
            return true;
        }
        sendErrorReply(set, message, A07);
        return false;
    }

    @Inject
    public HandlerUtil(AnonymousClass0lg r3) {
        this._UL_mInjectionContext = new AnonymousClass0RE(1, r3);
    }

    private void sendErrorReply(Set<Messenger> set, int i, Messenger messenger, String str) {
        AnonymousClass04L A04 = ((C02130i6) AnonymousClass0VF.A03(0, 20, this._UL_mInjectionContext)).A04();
        A04.A03("message", str);
        sendReply(set, i, -1, messenger, A04);
    }

    private void sendErrorReply(Set<Messenger> set, Message message, String str) {
        sendErrorReply(set, message.getData().getInt("call_id", -1), message.replyTo, str);
    }
}
