package X;

import android.app.IntentService;
import android.content.Intent;
import android.text.TextUtils;
import com.oculus.horizon.push.FbnsPushNotificationHandler;
import javax.annotation.Nullable;

/* renamed from: X.0aK  reason: invalid class name and case insensitive filesystem */
public abstract class AbstractIntentServiceC02350aK extends IntentService {
    public static final String TAG = "FbnsCallbackHandlerBase";
    public AnonymousClass0WD mIRtiSharedPreferences;
    public C01890Xx mSignatureAuthSecureIntent = new C01890Xx(this);

    public abstract void A00(int i);

    public abstract void A01(Intent intent);

    public abstract void A02(String str);

    public abstract void A03(String str, boolean z);

    public AbstractIntentServiceC02350aK() {
        super(FbnsPushNotificationHandler.TAG);
    }

    public final void onHandleIntent(@Nullable Intent intent) {
        if (intent != null) {
            try {
                if ("com.facebook.rti.fbns.intent.RECEIVE".equals(intent.getAction())) {
                    intent.toString();
                    C01890Xx r0 = this.mSignatureAuthSecureIntent;
                    if (AnonymousClass0WZ.A01(r0.A00, C01890Xx.A00(intent))) {
                        String stringExtra = intent.getStringExtra("receive_type");
                        if ("message".equals(stringExtra)) {
                            String stringExtra2 = intent.getStringExtra("token");
                            String A4R = this.mIRtiSharedPreferences.A4R("token_key", "");
                            intent.getStringExtra("extra_notification_id");
                            if (TextUtils.isEmpty(A4R) || A4R.equals(stringExtra2)) {
                                A01(intent);
                            } else {
                                AnonymousClass0NO.A09(TAG, "Dropping unintended message.");
                            }
                        } else if ("registered".equals(stringExtra)) {
                            String stringExtra3 = intent.getStringExtra("data");
                            C06520nY A2L = this.mIRtiSharedPreferences.A2L();
                            A2L.A00.putString("token_key", stringExtra3);
                            A2L.A00();
                            A03(stringExtra3, C01880Xv.A00(C01890Xx.A00(intent)));
                        } else if ("reg_error".equals(stringExtra)) {
                            A02(intent.getStringExtra("data"));
                        } else if ("deleted".equals(stringExtra)) {
                            A00(-1);
                        } else if (!"unregistered".equals(stringExtra)) {
                            AnonymousClass0NO.A08(TAG, "Unknown message type");
                        }
                    }
                }
            } finally {
                AnonymousClass0Vk.A00(intent);
            }
        }
    }

    public final int onStartCommand(Intent intent, int i, int i2) {
        this.mIRtiSharedPreferences = new C06510nV(this).A00(AnonymousClass0WE.TOKEN_STORE);
        return super.onStartCommand(intent, i, i2);
    }
}
