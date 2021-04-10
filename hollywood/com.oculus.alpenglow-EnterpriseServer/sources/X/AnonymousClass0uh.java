package X;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import com.facebook.rti.common.guavalite.annotations.VisibleForTesting;
import java.util.Map;
import javax.annotation.Nullable;

/* renamed from: X.0uh  reason: invalid class name */
public abstract class AnonymousClass0uh extends IntentService {
    public static final String TAG = "FbnsCallbackHandlerBase";
    public AnonymousClass0ux mIRtiSharedPreferences;
    public C07800w2 mSignatureAuthSecureIntent;

    @Nullable
    public AbstractC01590Jn getFbErrorReporter() {
        return null;
    }

    public abstract void onMessage(Intent intent);

    public abstract void onMessageDeleted(int i);

    public abstract void onRegistered(String str, boolean z);

    public abstract void onRegistrationError(String str);

    public abstract void onUnregistered();

    public void reportEvent(@Nullable String str, String str2, @Nullable Map<String, String> map) {
    }

    public static void clearTokenCache(Context context) {
        C07720vq A2E = new C07710vp(context).A00(EnumC07690vn.TOKEN_STORE).A2E();
        A2E.A00.clear();
        A2E.A00();
    }

    public void onHandleIntent(@Nullable Intent intent) {
        if (intent != null) {
            try {
                processIntent(intent);
            } finally {
                AbstractC09060yb.A00(intent);
            }
        }
    }

    public int onStartCommand(Intent intent, int i, int i2) {
        this.mIRtiSharedPreferences = new C07710vp(this).A00(EnumC07690vn.TOKEN_STORE);
        return super.onStartCommand(intent, i, i2);
    }

    public void processIntent(Intent intent) {
        if ("com.facebook.rti.fbns.intent.RECEIVE".equals(intent.getAction())) {
            intent.toString();
            if (this.mSignatureAuthSecureIntent.A03(C07800w2.A00(intent))) {
                String stringExtra = intent.getStringExtra("receive_type");
                if ("message".equals(stringExtra)) {
                    String stringExtra2 = intent.getStringExtra("token");
                    String A4Z = this.mIRtiSharedPreferences.A4Z("token_key", "");
                    intent.getStringExtra("extra_notification_id");
                    if (TextUtils.isEmpty(A4Z) || A4Z.equals(stringExtra2)) {
                        onMessage(intent);
                    } else {
                        AnonymousClass0NK.A02(TAG, "Dropping unintended message.");
                    }
                } else if ("registered".equals(stringExtra)) {
                    String stringExtra3 = intent.getStringExtra("data");
                    C07720vq A2E = this.mIRtiSharedPreferences.A2E();
                    A2E.A00.putString("token_key", stringExtra3);
                    A2E.A00();
                    onRegistered(stringExtra3, AnonymousClass0vB.A00(C07800w2.A00(intent)));
                } else if ("reg_error".equals(stringExtra)) {
                    onRegistrationError(intent.getStringExtra("data"));
                } else if ("deleted".equals(stringExtra)) {
                    onMessageDeleted(-1);
                } else if ("unregistered".equals(stringExtra)) {
                    onUnregistered();
                } else {
                    AnonymousClass0NK.A01(TAG, "Unknown message type");
                }
            }
        }
    }

    public AnonymousClass0uh(String str) {
        super(str);
        this.mSignatureAuthSecureIntent = new C07800w2(this);
    }

    @VisibleForTesting
    public AnonymousClass0uh(String str, C07800w2 r2, AnonymousClass0ux r3) {
        super(str);
        this.mSignatureAuthSecureIntent = r2;
        this.mIRtiSharedPreferences = r3;
    }
}
