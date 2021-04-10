package com.oculus.panelapp.debug;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.ResultReceiver;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.oculus.auth.service.contract.AuthTwoFactorMethod;
import com.oculus.authapi.AuthError;
import com.oculus.authapi.AuthLoginError;
import com.oculus.authapi.AuthResultCallback;
import com.oculus.authapi.OVRAuth;
import com.oculus.panelapp.debug.DebugTabHost;
import com.oculus.vrshell.panels.views.ShellButton;
import com.oculus.vrshell.util.CallerInfoHelper;
import java.util.ArrayList;
import java.util.Iterator;

public final class UserTab extends LinearLayout implements DebugTabHost.DebugTab {
    private static final String TAG = "DebugUserTab";
    private EditText mEmail;
    private TextView mEmailTitle;
    private TextView mLoginStatus;
    private ShellButton mLogout;
    private final OVRAuth mOVRAuth;
    private EditText mPassword;
    private TextView mPasswordTitle;
    private ShellButton mPinCancelButton;
    private TextView mPinTitle;
    private ShellButton mSubmit;
    private ShellButton mTwoFacSelectBackButton;
    private RecyclerView mTwoFacSelectRecyclerView;
    private TextView mTwoFacSelectTitle;
    private ShellButton mVerifyPin;
    private EditText pin;

    /* access modifiers changed from: package-private */
    public interface TwoFacMethodSelectCallback {
        void sendTwoFacCode(String str, String str2);
    }

    public UserTab(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mOVRAuth = new OVRAuth(context.getApplicationContext(), new OVRAuth.CallerInfoProvider() {
            /* class com.oculus.panelapp.debug.UserTab.AnonymousClass1 */

            @Override // com.oculus.authapi.OVRAuth.CallerInfoProvider
            public Intent attachCallerInfo(Intent intent) {
                return CallerInfoHelper.attachCallerInfo(intent, UserTab.this.getContext(), null);
            }
        });
    }

    @Override // com.oculus.panelapp.debug.DebugTabHost.DebugTab
    public void initialize(final ShellDebugPanelApp shellDebugPanelApp, DebugTabHost debugTabHost) {
        this.mLoginStatus = (TextView) findViewById(R.id.login_status);
        this.mEmailTitle = (TextView) findViewById(R.id.user_email_title);
        this.mEmail = (EditText) findViewById(R.id.user_email);
        this.mPassword = (EditText) findViewById(R.id.user_password);
        this.mPasswordTitle = (TextView) findViewById(R.id.user_password_title);
        this.mSubmit = (ShellButton) findViewById(R.id.submit_login);
        this.mLogout = (ShellButton) findViewById(R.id.logout);
        this.mPinTitle = (TextView) findViewById(R.id.user_pin_title);
        this.pin = (EditText) findViewById(R.id.user_pin);
        this.mVerifyPin = (ShellButton) findViewById(R.id.verify_login_pin);
        this.mPinCancelButton = (ShellButton) findViewById(R.id.pin_cancel_button);
        this.mTwoFacSelectTitle = (TextView) findViewById(R.id.two_fac_select_title);
        this.mTwoFacSelectBackButton = (ShellButton) findViewById(R.id.two_fac_select_back_button);
        this.mTwoFacSelectRecyclerView = (RecyclerView) findViewById(R.id.two_fac_select_list);
        this.mTwoFacSelectRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        this.mTwoFacSelectRecyclerView.setAdapter(new TwoFacMethodSelectAdapter(new TwoFacMethodSelectCallback() {
            /* class com.oculus.panelapp.debug.UserTab.AnonymousClass2 */

            @Override // com.oculus.panelapp.debug.UserTab.TwoFacMethodSelectCallback
            public void sendTwoFacCode(final String str, final String str2) {
                UserTab.this.mOVRAuth.sendTwoFactorCode(str, str2, new AuthResultCallback<Void, AuthError>() {
                    /* class com.oculus.panelapp.debug.UserTab.AnonymousClass2.AnonymousClass1 */

                    public void onResult(Void r2) {
                        UserTab.this.post(new Runnable() {
                            /* class com.oculus.panelapp.debug.UserTab.AnonymousClass2.AnonymousClass1.AnonymousClass1 */

                            public void run() {
                                Log.d(UserTab.TAG, "sendTwoFactorCode: success");
                                UserTab.this.setTwoFacSelectUIState(shellDebugPanelApp, false, null, null);
                                UserTab.this.setPinUIState(shellDebugPanelApp, true, str2, str);
                            }
                        });
                    }

                    @Override // com.oculus.authapi.AuthResultCallback
                    public void onError(AuthError authError) {
                        Log.d(UserTab.TAG, "sendTwoFactorCode: onError = " + authError.mErrorMessage);
                    }
                });
            }
        }, new ArrayList()));
        setLoginStatus();
        setLoginUIState(shellDebugPanelApp, true);
        setPinUIState(shellDebugPanelApp, false, null, null);
        setTwoFacSelectUIState(shellDebugPanelApp, false, null, null);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setLoginUIState(final ShellDebugPanelApp shellDebugPanelApp, boolean z) {
        if (z) {
            this.mEmailTitle.setVisibility(0);
            this.mEmail.setVisibility(0);
            this.mPassword.setVisibility(0);
            this.mPasswordTitle.setVisibility(0);
            this.mSubmit.setVisibility(0);
            this.mLogout.setVisibility(0);
            this.mSubmit.setOnClickListener(new View.OnClickListener() {
                /* class com.oculus.panelapp.debug.UserTab.AnonymousClass3 */

                public void onClick(View view) {
                    UserTab.this.mOVRAuth.loginWithEmailAndPassword(UserTab.this.mEmail.getText().toString(), UserTab.this.mPassword.getText().toString(), new AuthResultCallback<Void, AuthLoginError>() {
                        /* class com.oculus.panelapp.debug.UserTab.AnonymousClass3.AnonymousClass1 */

                        public void onResult(Void r2) {
                            UserTab.this.post(new Runnable() {
                                /* class com.oculus.panelapp.debug.UserTab.AnonymousClass3.AnonymousClass1.AnonymousClass1 */

                                public void run() {
                                    Log.d(UserTab.TAG, "loginWithEmailAndPassword: success");
                                    UserTab.this.setLoginStatus();
                                }
                            });
                        }

                        public void onError(final AuthLoginError authLoginError) {
                            UserTab.this.post(new Runnable() {
                                /* class com.oculus.panelapp.debug.UserTab.AnonymousClass3.AnonymousClass1.AnonymousClass2 */

                                public void run() {
                                    Log.d(UserTab.TAG, "loginWithEmailAndPassword: error with code = " + authLoginError.mErrorCode);
                                    if (authLoginError.mErrorCode == -2) {
                                        Log.d(UserTab.TAG, "result login approvals error.");
                                        UserTab.this.setLoginUIState(shellDebugPanelApp, false);
                                        UserTab.this.setPinUIState(shellDebugPanelApp, true, null, authLoginError.getNonce());
                                    } else if (authLoginError.mErrorCode == -8) {
                                        Log.d(UserTab.TAG, "result two fac error.");
                                        UserTab.this.setLoginUIState(shellDebugPanelApp, false);
                                        UserTab.this.setTwoFacSelectUIState(shellDebugPanelApp, true, authLoginError.getTwoFactorMethods(), authLoginError.getNonce());
                                    }
                                    UserTab.this.setLoginStatus();
                                }
                            });
                        }
                    });
                }
            });
            this.mLogout.setOnClickListener(new View.OnClickListener() {
                /* class com.oculus.panelapp.debug.UserTab.AnonymousClass4 */

                public void onClick(View view) {
                    UserTab.this.mOVRAuth.logout(new ResultReceiver(null) {
                        /* class com.oculus.panelapp.debug.UserTab.AnonymousClass4.AnonymousClass1 */

                        /* access modifiers changed from: protected */
                        public void onReceiveResult(int i, Bundle bundle) {
                            UserTab.this.post(new Runnable() {
                                /* class com.oculus.panelapp.debug.UserTab.AnonymousClass4.AnonymousClass1.AnonymousClass1 */

                                public void run() {
                                    UserTab.this.setLoginStatus();
                                }
                            });
                        }
                    });
                }
            });
            return;
        }
        this.mEmailTitle.setVisibility(8);
        this.mEmail.setVisibility(8);
        this.mEmail.setOnFocusChangeListener(null);
        this.mPasswordTitle.setVisibility(8);
        this.mPassword.setVisibility(8);
        this.mPassword.setOnFocusChangeListener(null);
        this.mSubmit.setVisibility(8);
        this.mSubmit.clearOnClickListener();
        this.mLogout.setVisibility(8);
        this.mLogout.clearOnClickListener();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setPinUIState(final ShellDebugPanelApp shellDebugPanelApp, boolean z, final String str, final String str2) {
        if (z) {
            this.mPinTitle.setVisibility(0);
            this.pin.setVisibility(0);
            this.mVerifyPin.setVisibility(0);
            this.mPinCancelButton.setVisibility(0);
            this.mVerifyPin.setOnClickListener(new View.OnClickListener() {
                /* class com.oculus.panelapp.debug.UserTab.AnonymousClass5 */

                public void onClick(View view) {
                    UserTab.this.mOVRAuth.verifyLogin(str2, UserTab.this.pin.getText().toString(), str, null, false, new AuthResultCallback<Void, AuthError>() {
                        /* class com.oculus.panelapp.debug.UserTab.AnonymousClass5.AnonymousClass1 */

                        public void onResult(Void r4) {
                            Log.d(UserTab.TAG, "verifyLogin: success");
                            UserTab.this.setPinUIState(shellDebugPanelApp, false, null, null);
                            UserTab.this.setLoginUIState(shellDebugPanelApp, true);
                            UserTab.this.setLoginStatus();
                        }

                        @Override // com.oculus.authapi.AuthResultCallback
                        public void onError(AuthError authError) {
                            Log.d(UserTab.TAG, "verifyLogin: onError with " + authError.mErrorCode + ", " + authError.mErrorTitle + ", " + authError.mErrorMessage);
                        }
                    });
                }
            });
            this.mPinCancelButton.setOnClickListener(new View.OnClickListener() {
                /* class com.oculus.panelapp.debug.UserTab.AnonymousClass6 */

                public void onClick(View view) {
                    UserTab.this.setPinUIState(shellDebugPanelApp, false, null, null);
                    UserTab.this.setLoginUIState(shellDebugPanelApp, true);
                }
            });
            return;
        }
        this.mPinTitle.setVisibility(8);
        this.pin.setVisibility(8);
        this.pin.setOnFocusChangeListener(null);
        this.mVerifyPin.setVisibility(8);
        this.mVerifyPin.clearOnClickListener();
        this.mPinCancelButton.setVisibility(8);
        this.mPinCancelButton.clearOnClickListener();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setTwoFacSelectUIState(final ShellDebugPanelApp shellDebugPanelApp, boolean z, ArrayList<AuthTwoFactorMethod> arrayList, String str) {
        TwoFacMethodSelectAdapter twoFacMethodSelectAdapter = (TwoFacMethodSelectAdapter) this.mTwoFacSelectRecyclerView.getAdapter();
        if (z) {
            this.mTwoFacSelectTitle.setVisibility(0);
            this.mTwoFacSelectBackButton.setVisibility(0);
            this.mTwoFacSelectRecyclerView.setVisibility(0);
            twoFacMethodSelectAdapter.setItems(arrayList);
            twoFacMethodSelectAdapter.setNonce(str);
            this.mTwoFacSelectBackButton.setOnClickListener(new View.OnClickListener() {
                /* class com.oculus.panelapp.debug.UserTab.AnonymousClass7 */

                public void onClick(View view) {
                    UserTab.this.setTwoFacSelectUIState(shellDebugPanelApp, false, null, null);
                    UserTab.this.setLoginUIState(shellDebugPanelApp, true);
                }
            });
            return;
        }
        this.mTwoFacSelectTitle.setVisibility(8);
        this.mTwoFacSelectBackButton.setVisibility(8);
        this.mTwoFacSelectBackButton.clearOnClickListener();
        this.mTwoFacSelectRecyclerView.setVisibility(8);
        twoFacMethodSelectAdapter.setItems(null);
        twoFacMethodSelectAdapter.setNonce(str);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setLoginStatus() {
        this.mOVRAuth.status(new ResultReceiver(null) {
            /* class com.oculus.panelapp.debug.UserTab.AnonymousClass8 */

            /* access modifiers changed from: protected */
            public void onReceiveResult(final int i, Bundle bundle) {
                UserTab.this.post(new Runnable() {
                    /* class com.oculus.panelapp.debug.UserTab.AnonymousClass8.AnonymousClass1 */

                    public void run() {
                        Log.d(UserTab.TAG, "setLoginStatus resultCode = " + i);
                        if (i == -1) {
                            UserTab.this.mLoginStatus.setText(R.string.user_tab_user_is_logged_in);
                        } else {
                            UserTab.this.mLoginStatus.setText(R.string.user_tab_user_is_logged_out);
                        }
                    }
                });
            }
        });
    }

    private void dumpTwoFacMethods(ArrayList<AuthTwoFactorMethod> arrayList) {
        Log.d(TAG, "dumpTwoFacMethods called with twoFactorMethods = " + arrayList);
        if (arrayList != null) {
            Iterator<AuthTwoFactorMethod> it = arrayList.iterator();
            while (it.hasNext()) {
                AuthTwoFactorMethod next = it.next();
                Log.d(TAG, "twoFactorMethod id = " + next.id);
                Log.d(TAG, "twoFactorMethod label = " + next.label);
                Log.d(TAG, "twoFactorMethod instructions = " + next.instructions);
                Log.d(TAG, "twoFactorMethod resendText = " + next.resendText);
                Log.d(TAG, "twoFactorMethod sendOption = " + next.sendOption);
                Log.d(TAG, "twoFactorMethod enterCodeOption = " + next.enterCodeOption);
            }
        }
    }
}
