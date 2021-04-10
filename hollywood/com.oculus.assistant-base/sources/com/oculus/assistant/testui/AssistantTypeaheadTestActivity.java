package com.oculus.assistant.testui;

import X.AnonymousClass08;
import X.C0139Dd;
import X.C0387Va;
import X.C1419zB;
import X.Z8;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;
import com.oculus.assistant.R;
import com.oculus.assistant.api.IOculusAssistantService;
import com.oculus.assistant.api.IOculusAssistantSubscriber;
import java.util.UUID;

public class AssistantTypeaheadTestActivity extends Activity {
    public TextView A00;
    public C1419zB A01 = new C1419zB();
    public EditText A02;
    public C0387Va A03;
    public final TextWatcher A04 = new Z8(this);
    public final IOculusAssistantSubscriber A05 = new AnonymousClass1();

    /* renamed from: com.oculus.assistant.testui.AssistantTypeaheadTestActivity$1  reason: invalid class name */
    public final class AnonymousClass1 extends Binder implements IOculusAssistantSubscriber {
        public final IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public final boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) {
            Bundle bundle;
            if (i == 1) {
                parcel.enforceInterface("com.oculus.assistant.api.IOculusAssistantSubscriber");
                String readString = parcel.readString();
                if (parcel.readInt() != 0) {
                    bundle = (Bundle) Bundle.CREATOR.createFromParcel(parcel);
                } else {
                    bundle = null;
                }
                A4H(readString, bundle);
            } else if (i == 2) {
                parcel.enforceInterface("com.oculus.assistant.api.IOculusAssistantSubscriber");
                A3p(parcel.readString(), parcel.readString());
            } else if (i == 3) {
                parcel.enforceInterface("com.oculus.assistant.api.IOculusAssistantSubscriber");
                A41(parcel.readString(), parcel.readString());
            } else if (i != 1598968902) {
                return super.onTransact(i, parcel, parcel2, i2);
            } else {
                parcel2.writeString("com.oculus.assistant.api.IOculusAssistantSubscriber");
                return true;
            }
            parcel2.writeNoException();
            return true;
        }

        @Override // com.oculus.assistant.api.IOculusAssistantSubscriber
        public final void A3p(String str, String str2) {
            C0139Dd.A09("Assistant:ATTA", AnonymousClass08.A04("Activated ", str));
        }

        @Override // com.oculus.assistant.api.IOculusAssistantSubscriber
        public final void A41(String str, String str2) {
            C0139Dd.A09("Assistant:ATTA", AnonymousClass08.A04("Deactivated ", str));
        }

        @Override // com.oculus.assistant.api.IOculusAssistantSubscriber
        public final void A4H(String str, Bundle bundle) {
            C0139Dd.A09("Assistant:ATTA", AnonymousClass08.A04("Got messageType: ", str));
            if (str.equals("OnTypeaheadSuggestionMessage")) {
                String string = bundle.getString("suggestedWord");
                String string2 = bundle.getString("otherSuggestions");
                if (string2 == null) {
                    string2 = "NONE";
                }
                String replace = string2.replace(";", ", ");
                AssistantTypeaheadTestActivity.this.A00.setText(String.format("Suggested: %s\nOther Suggestions: %s", string, replace));
            }
        }

        public AnonymousClass1() {
            attachInterface(this, "com.oculus.assistant.api.IOculusAssistantSubscriber");
        }

        public AnonymousClass1() {
            this();
        }
    }

    public final void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.assistant_smart_keyboard_layout);
        this.A02 = (EditText) findViewById(R.id.typeahead_input);
        this.A00 = (TextView) findViewById(R.id.typeahead_response);
        C1419zB zBVar = this.A01;
        Intent intent = new Intent();
        intent.setComponent(new ComponentName("com.oculus.assistant", "com.oculus.assistant.service.AssistantService"));
        intent.setAction("com.oculus.assistant.ENTRY_SERVICE");
        bindService(intent, zBVar, 1);
        this.A01 = zBVar;
        this.A02.addTextChangedListener(this.A04);
        C0387Va va = new C0387Va("OnTypeaheadSuggestionMessage", this.A05, null);
        this.A03 = va;
        C1419zB zBVar2 = this.A01;
        String obj = UUID.randomUUID().toString();
        IOculusAssistantService iOculusAssistantService = zBVar2.A00;
        if (iOculusAssistantService != null) {
            try {
                obj = iOculusAssistantService.A5D(va.A01, va.A00);
                if (obj == null) {
                    return;
                }
            } catch (RemoteException e) {
                Log.e("OAConnection", "Failed to subscribe to Assistant: ", e);
                return;
            }
        }
        va.A02 = obj;
        zBVar2.A01.put(obj, va);
    }

    public final void onDestroy() {
        super.onDestroy();
        this.A02.removeTextChangedListener(this.A04);
        C1419zB zBVar = this.A01;
        if (zBVar != null) {
            C0387Va va = this.A03;
            zBVar.A01.remove(va.A02);
            IOculusAssistantService iOculusAssistantService = zBVar.A00;
            if (iOculusAssistantService != null) {
                try {
                    iOculusAssistantService.A5K(va.A01, va.A02);
                } catch (RemoteException e) {
                    Log.e("OAConnection", "Failed to unsubscribe to Assistant: ", e);
                }
            }
            this.A01.A00("StopTypeaheadSessionMessage", null);
        }
        this.A03 = null;
        this.A01 = null;
    }
}
