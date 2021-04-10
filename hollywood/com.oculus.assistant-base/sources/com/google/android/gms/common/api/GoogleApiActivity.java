package com.google.android.gms.common.api;

import X.C0319Qs;
import X.C0331Rl;
import X.C1100sW;
import X.QS;
import X.RZ;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.FragmentManager;
import android.app.PendingIntent;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentSender;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.util.TypedValue;
import com.facebook.proxygen.TraceFieldType;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.annotation.KeepName;
import com.oculus.assistant.R;

@KeepName
public class GoogleApiActivity extends Activity implements DialogInterface.OnCancelListener {
    public int A00 = 0;

    public final void onCancel(DialogInterface dialogInterface) {
        this.A00 = 0;
        setResult(0);
        finish();
    }

    public final void onCreate(Bundle bundle) {
        AlertDialog.Builder builder;
        int i;
        String str;
        super.onCreate(bundle);
        if (bundle != null) {
            this.A00 = bundle.getInt("resolution");
        }
        if (this.A00 != 1) {
            Bundle extras = getIntent().getExtras();
            if (extras == null) {
                str = "Activity started without extras";
            } else {
                PendingIntent pendingIntent = (PendingIntent) extras.get("pending_intent");
                Object obj = extras.get(TraceFieldType.ErrorCode);
                if (pendingIntent != null) {
                    try {
                        startIntentSenderForResult(pendingIntent.getIntentSender(), 1, null, 0, 0, 0);
                        this.A00 = 1;
                        return;
                    } catch (ActivityNotFoundException e) {
                        if (extras.getBoolean("notify_manager", true)) {
                            C0319Qs A02 = C0319Qs.A02(this);
                            ConnectionResult connectionResult = new ConnectionResult(22, null);
                            int intExtra = getIntent().getIntExtra("failing_client_id", -1);
                            if (!A02.A04(connectionResult, intExtra)) {
                                Handler handler = A02.A05;
                                handler.sendMessage(handler.obtainMessage(5, intExtra, 0, connectionResult));
                            }
                        } else {
                            String valueOf = String.valueOf(pendingIntent);
                            StringBuilder sb = new StringBuilder(valueOf.length() + 36);
                            sb.append("Activity not found while launching ");
                            sb.append(valueOf);
                            sb.append(".");
                            String obj2 = sb.toString();
                            if (Build.FINGERPRINT.contains("generic")) {
                                obj2 = String.valueOf(obj2).concat(" This may occur when resolving Google Play services connection issues on emulators with Google APIs but not Google Play Store.");
                            }
                            Log.e("GoogleApiActivity", obj2, e);
                        }
                        this.A00 = 1;
                    } catch (IntentSender.SendIntentException e2) {
                        Log.e("GoogleApiActivity", "Failed to launch pendingIntent", e2);
                        finish();
                        return;
                    }
                } else if (obj == null) {
                    str = "Activity started without resolution";
                } else {
                    int intValue = ((Number) obj).intValue();
                    C1100sW sWVar = new C1100sW(GoogleApiAvailability.A00.A02(this, intValue, "d"), this);
                    if (intValue != 0) {
                        TypedValue typedValue = new TypedValue();
                        getTheme().resolveAttribute(16843529, typedValue, true);
                        if ("Theme.Dialog.Alert".equals(getResources().getResourceEntryName(typedValue.resourceId))) {
                            builder = new AlertDialog.Builder(this, 5);
                        } else {
                            builder = new AlertDialog.Builder(this);
                        }
                        builder.setMessage(C0331Rl.A02(this, intValue));
                        builder.setOnCancelListener(this);
                        Resources resources = getResources();
                        if (intValue == 1) {
                            i = R.string.common_google_play_services_install_button;
                        } else if (intValue != 2) {
                            i = R.string.common_google_play_services_enable_button;
                            if (intValue != 3) {
                                i = 17039370;
                            }
                        } else {
                            i = R.string.common_google_play_services_update_button;
                        }
                        String string = resources.getString(i);
                        if (string != null) {
                            builder.setPositiveButton(string, sWVar);
                        }
                        String A01 = C0331Rl.A01(this, intValue);
                        if (A01 != null) {
                            builder.setTitle(A01);
                        }
                        Log.w("GoogleApiAvailability", String.format("Creating dialog for Google Play services availability issue. ConnectionResult=%s", Integer.valueOf(intValue)), new IllegalArgumentException());
                        AlertDialog create = builder.create();
                        if (create != null) {
                            FragmentManager fragmentManager = getFragmentManager();
                            QS qs = new QS();
                            RZ.A02(create, "Cannot display null dialog");
                            create.setOnCancelListener(null);
                            create.setOnDismissListener(null);
                            qs.A00 = create;
                            qs.A01 = this;
                            qs.show(fragmentManager, "GooglePlayServicesErrorDialog");
                        }
                    }
                    this.A00 = 1;
                    return;
                }
            }
            Log.e("GoogleApiActivity", str);
            finish();
        }
    }

    public final void onSaveInstanceState(Bundle bundle) {
        bundle.putInt("resolution", this.A00);
        super.onSaveInstanceState(bundle);
    }

    public final void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == 1) {
            boolean booleanExtra = getIntent().getBooleanExtra("notify_manager", true);
            this.A00 = 0;
            setResult(i2, intent);
            if (booleanExtra) {
                C0319Qs A02 = C0319Qs.A02(this);
                if (i2 == -1) {
                    Handler handler = A02.A05;
                    handler.sendMessage(handler.obtainMessage(3));
                } else if (i2 == 0) {
                    ConnectionResult connectionResult = new ConnectionResult(13, null);
                    int intExtra = getIntent().getIntExtra("failing_client_id", -1);
                    if (!A02.A04(connectionResult, intExtra)) {
                        Handler handler2 = A02.A05;
                        handler2.sendMessage(handler2.obtainMessage(5, intExtra, 0, connectionResult));
                    }
                }
            }
        } else if (i == 2) {
            this.A00 = 0;
            setResult(i2, intent);
        }
        finish();
    }
}
