package com.facebook.internal;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.net.http.SslError;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.webkit.SslErrorHandler;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.facebook.AccessToken;
import com.facebook.AccessTokenManager;
import com.facebook.FacebookDialogException;
import com.facebook.FacebookException;
import com.facebook.FacebookOperationCanceledException;
import com.facebook.FacebookRequestError;
import com.facebook.FacebookSdk;
import com.facebook.FacebookServiceException;
import com.oculus.horizon.R;

public class WebDialog extends Dialog {
    public static final int API_EC_DIALOG_CANCEL = 4201;
    public static final int BACKGROUND_GRAY = -872415232;
    public static final String CANCEL_URI = "fbconnect://cancel";
    public static final int DEFAULT_THEME = 16973840;
    public static final boolean DISABLE_SSL_CHECK_FOR_TESTING = false;
    public static final String DISPLAY_TOUCH = "touch";
    public static final String LOG_TAG = "FacebookSDK.WebDialog";
    public static final int MAX_PADDING_SCREEN_HEIGHT = 1280;
    public static final int MAX_PADDING_SCREEN_WIDTH = 800;
    public static final double MIN_SCALE_FACTOR = 0.5d;
    public static final int NO_PADDING_SCREEN_HEIGHT = 800;
    public static final int NO_PADDING_SCREEN_WIDTH = 480;
    public static final String REDIRECT_URI = "fbconnect://success";
    public FrameLayout contentFrameLayout;
    public ImageView crossImageView;
    public String expectedRedirectUrl;
    public boolean isDetached;
    public boolean isPageFinished;
    public boolean listenerCalled;
    public OnCompleteListener onCompleteListener;
    public ProgressDialog spinner;
    public String url;
    public WebView webView;

    public static class Builder {
        public AccessToken accessToken;
        public String action;
        public String applicationId;
        public Context context;
        public OnCompleteListener listener;
        public Bundle parameters;
        public int theme;

        private void finishInit(Context context2, String str, Bundle bundle) {
            this.context = context2;
            this.action = str;
            if (bundle != null) {
                this.parameters = bundle;
            } else {
                this.parameters = new Bundle();
            }
        }

        public WebDialog build() {
            AccessToken accessToken2 = this.accessToken;
            if (accessToken2 != null) {
                this.parameters.putString("app_id", accessToken2.applicationId);
                this.parameters.putString("access_token", this.accessToken.token);
            } else {
                this.parameters.putString("app_id", this.applicationId);
            }
            return new WebDialog(this.context, this.action, this.parameters, this.theme, this.listener);
        }

        public String getApplicationId() {
            return this.applicationId;
        }

        public Context getContext() {
            return this.context;
        }

        public OnCompleteListener getListener() {
            return this.listener;
        }

        public Bundle getParameters() {
            return this.parameters;
        }

        public int getTheme() {
            return this.theme;
        }

        public Builder setOnCompleteListener(OnCompleteListener onCompleteListener) {
            this.listener = onCompleteListener;
            return this;
        }

        public Builder setTheme(int i) {
            this.theme = i;
            return this;
        }

        public Builder(Context context2, String str, Bundle bundle) {
            AccessToken accessToken2 = AccessTokenManager.getInstance().currentAccessToken;
            this.accessToken = accessToken2;
            if (accessToken2 == null) {
                String metadataApplicationId = Utility.getMetadataApplicationId(context2);
                if (metadataApplicationId != null) {
                    this.applicationId = metadataApplicationId;
                } else {
                    throw new FacebookException("Attempted to create a builder without a valid access token or a valid default Application ID.");
                }
            }
            finishInit(context2, str, bundle);
        }

        public Builder(Context context2, String str, String str2, Bundle bundle) {
            str = str == null ? Utility.getMetadataApplicationId(context2) : str;
            Validate.notNullOrEmpty(str, "applicationId");
            this.applicationId = str;
            finishInit(context2, str2, bundle);
        }
    }

    public class DialogWebViewClient extends WebViewClient {
        @Override // android.webkit.WebViewClient
        public boolean shouldOverrideUrlLoading(WebView webView, String str) {
            int i;
            WebDialog webDialog = WebDialog.this;
            if (str.startsWith(webDialog.expectedRedirectUrl)) {
                Bundle parseResponseUri = webDialog.parseResponseUri(str);
                String string = parseResponseUri.getString("error");
                if (string == null) {
                    string = parseResponseUri.getString("error_type");
                }
                String string2 = parseResponseUri.getString(FacebookRequestError.ERROR_MSG_KEY);
                if (string2 == null && (string2 = parseResponseUri.getString("error_message")) == null) {
                    string2 = parseResponseUri.getString(NativeProtocol.BRIDGE_ARG_ERROR_DESCRIPTION);
                }
                String string3 = parseResponseUri.getString("error_code");
                if (!Utility.isNullOrEmpty(string3)) {
                    try {
                        i = Integer.parseInt(string3);
                    } catch (NumberFormatException unused) {
                    }
                    if (!Utility.isNullOrEmpty(string) && Utility.isNullOrEmpty(string2) && i == -1) {
                        WebDialog.this.sendSuccessToListener(parseResponseUri);
                        return true;
                    } else if ((string != null || (!string.equals("access_denied") && !string.equals("OAuthAccessDeniedException"))) && i != 4201) {
                        WebDialog.this.sendErrorToListener(new FacebookServiceException(new FacebookRequestError(i, string, string2), string2));
                        return true;
                    } else {
                        WebDialog.this.cancel();
                        return true;
                    }
                }
                i = -1;
                if (!Utility.isNullOrEmpty(string)) {
                }
                if (string != null) {
                }
                WebDialog.this.sendErrorToListener(new FacebookServiceException(new FacebookRequestError(i, string, string2), string2));
                return true;
            } else if (str.startsWith("fbconnect://cancel")) {
                webDialog.cancel();
                return true;
            } else if (str.contains("touch")) {
                return false;
            } else {
                try {
                    WebDialog.this.getContext().startActivity(new Intent("android.intent.action.VIEW", Uri.parse(str)));
                    return true;
                } catch (ActivityNotFoundException unused2) {
                    return false;
                }
            }
        }

        public void onPageFinished(WebView webView, String str) {
            super.onPageFinished(webView, str);
            WebDialog webDialog = WebDialog.this;
            if (!webDialog.isDetached) {
                webDialog.spinner.dismiss();
            }
            WebDialog.this.contentFrameLayout.setBackgroundColor(0);
            WebDialog.this.webView.setVisibility(0);
            WebDialog.this.crossImageView.setVisibility(0);
            WebDialog.this.isPageFinished = true;
        }

        public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
            super.onPageStarted(webView, str, bitmap);
            WebDialog webDialog = WebDialog.this;
            if (!webDialog.isDetached) {
                webDialog.spinner.show();
            }
        }

        public void onReceivedError(WebView webView, int i, String str, String str2) {
            super.onReceivedError(webView, i, str, str2);
            WebDialog.this.sendErrorToListener(new FacebookDialogException(str, i, str2));
        }

        public void onReceivedSslError(WebView webView, SslErrorHandler sslErrorHandler, SslError sslError) {
            super.onReceivedSslError(webView, sslErrorHandler, sslError);
            sslErrorHandler.cancel();
            WebDialog.this.sendErrorToListener(new FacebookDialogException(null, -11, null));
        }

        public DialogWebViewClient() {
        }
    }

    public interface OnCompleteListener {
        void onComplete(Bundle bundle, FacebookException facebookException);
    }

    private int getScaledSize(int i, float f, int i2, int i3) {
        int i4 = (int) (((float) i) / f);
        double d = 0.5d;
        if (i4 <= i2) {
            d = 1.0d;
        } else if (i4 < i3) {
            d = 0.5d + ((((double) (i3 - i4)) / ((double) (i3 - i2))) * 0.5d);
        }
        return (int) (((double) i) * d);
    }

    public void onAttachedToWindow() {
        this.isDetached = false;
        super.onAttachedToWindow();
    }

    public void onDetachedFromWindow() {
        this.isDetached = true;
        super.onDetachedFromWindow();
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i == 4) {
            cancel();
        }
        return super.onKeyDown(i, keyEvent);
    }

    public void cancel() {
        if (this.onCompleteListener != null && !this.listenerCalled) {
            sendErrorToListener(new FacebookOperationCanceledException());
        }
    }

    public void dismiss() {
        ProgressDialog progressDialog;
        WebView webView2 = this.webView;
        if (webView2 != null) {
            webView2.stopLoading();
        }
        if (!this.isDetached && (progressDialog = this.spinner) != null && progressDialog.isShowing()) {
            this.spinner.dismiss();
        }
        super.dismiss();
    }

    public void sendErrorToListener(Throwable th) {
        FacebookException facebookException;
        if (this.onCompleteListener != null && !this.listenerCalled) {
            this.listenerCalled = true;
            if (th instanceof FacebookException) {
                facebookException = (FacebookException) th;
            } else {
                facebookException = new FacebookException(th);
            }
            this.onCompleteListener.onComplete(null, facebookException);
            dismiss();
        }
    }

    public void sendSuccessToListener(Bundle bundle) {
        OnCompleteListener onCompleteListener2 = this.onCompleteListener;
        if (onCompleteListener2 != null && !this.listenerCalled) {
            this.listenerCalled = true;
            onCompleteListener2.onComplete(bundle, null);
            dismiss();
        }
    }

    private void createCrossImage() {
        ImageView imageView = new ImageView(getContext());
        this.crossImageView = imageView;
        imageView.setOnClickListener(new View.OnClickListener() {
            /* class com.facebook.internal.WebDialog.AnonymousClass2 */

            public void onClick(View view) {
                WebDialog.this.cancel();
            }
        });
        this.crossImageView.setImageDrawable(getContext().getResources().getDrawable(R.drawable.com_facebook_close));
        this.crossImageView.setVisibility(4);
    }

    @SuppressLint({"SetJavaScriptEnabled"})
    private void setUpWebView(int i) {
        LinearLayout linearLayout = new LinearLayout(getContext());
        AnonymousClass3 r0 = new WebView(getContext().getApplicationContext()) {
            /* class com.facebook.internal.WebDialog.AnonymousClass3 */
        };
        this.webView = r0;
        r0.setVerticalScrollBarEnabled(false);
        this.webView.setHorizontalScrollBarEnabled(false);
        this.webView.setWebViewClient(new DialogWebViewClient());
        this.webView.getSettings().setJavaScriptEnabled(true);
        this.webView.loadUrl(this.url);
        this.webView.setLayoutParams(new FrameLayout.LayoutParams(-1, -1));
        this.webView.setVisibility(4);
        this.webView.getSettings().setSavePassword(false);
        this.webView.getSettings().setSaveFormData(false);
        this.webView.setFocusable(true);
        this.webView.setFocusableInTouchMode(true);
        this.webView.setOnTouchListener(new View.OnTouchListener() {
            /* class com.facebook.internal.WebDialog.AnonymousClass4 */

            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (view.hasFocus()) {
                    return false;
                }
                view.requestFocus();
                return false;
            }
        });
        linearLayout.setPadding(i, i, i, i);
        linearLayout.addView(this.webView);
        linearLayout.setBackgroundColor(BACKGROUND_GRAY);
        this.contentFrameLayout.addView(linearLayout);
    }

    public OnCompleteListener getOnCompleteListener() {
        return this.onCompleteListener;
    }

    public WebView getWebView() {
        return this.webView;
    }

    public boolean isListenerCalled() {
        return this.listenerCalled;
    }

    public boolean isPageFinished() {
        return this.isPageFinished;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        ProgressDialog progressDialog = new ProgressDialog(getContext());
        this.spinner = progressDialog;
        progressDialog.requestWindowFeature(1);
        this.spinner.setMessage(getContext().getString(R.string.com_facebook_loading));
        this.spinner.setOnCancelListener(new DialogInterface.OnCancelListener() {
            /* class com.facebook.internal.WebDialog.AnonymousClass1 */

            public void onCancel(DialogInterface dialogInterface) {
                WebDialog.this.cancel();
            }
        });
        requestWindowFeature(1);
        this.contentFrameLayout = new FrameLayout(getContext());
        resize();
        getWindow().setGravity(17);
        getWindow().setSoftInputMode(16);
        createCrossImage();
        setUpWebView((this.crossImageView.getDrawable().getIntrinsicWidth() >> 1) + 1);
        this.contentFrameLayout.addView(this.crossImageView, new ViewGroup.LayoutParams(-2, -2));
        setContentView(this.contentFrameLayout);
    }

    public void onStart() {
        super.onStart();
        resize();
    }

    public Bundle parseResponseUri(String str) {
        Uri parse = Uri.parse(str);
        Bundle parseUrlQueryString = Utility.parseUrlQueryString(parse.getQuery());
        parseUrlQueryString.putAll(Utility.parseUrlQueryString(parse.getFragment()));
        return parseUrlQueryString;
    }

    public void resize() {
        Display defaultDisplay = ((WindowManager) getContext().getSystemService("window")).getDefaultDisplay();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        defaultDisplay.getMetrics(displayMetrics);
        int i = displayMetrics.widthPixels;
        int i2 = displayMetrics.heightPixels;
        if (i >= i2) {
            i = i2;
        }
        if (i >= i2) {
            i2 = i;
        }
        float f = displayMetrics.density;
        getWindow().setLayout(Math.min(getScaledSize(i, f, NO_PADDING_SCREEN_WIDTH, 800), i), Math.min(getScaledSize(i2, f, 800, MAX_PADDING_SCREEN_HEIGHT), i2));
    }

    public void setExpectedRedirectUrl(String str) {
        this.expectedRedirectUrl = str;
    }

    public void setOnCompleteListener(OnCompleteListener onCompleteListener2) {
        this.onCompleteListener = onCompleteListener2;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public WebDialog(Context context, String str) {
        this(context, str, FacebookSdk.webDialogTheme);
        Validate.sdkInitialized();
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public WebDialog(android.content.Context r2, java.lang.String r3, int r4) {
        /*
            r1 = this;
            if (r4 != 0) goto L_0x0007
            com.facebook.internal.Validate.sdkInitialized()
            int r4 = com.facebook.FacebookSdk.webDialogTheme
        L_0x0007:
            r1.<init>(r2, r4)
            java.lang.String r0 = "fbconnect://success"
            r1.expectedRedirectUrl = r0
            r0 = 0
            r1.listenerCalled = r0
            r1.isDetached = r0
            r1.isPageFinished = r0
            r1.url = r3
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.internal.WebDialog.<init>(android.content.Context, java.lang.String, int):void");
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public WebDialog(android.content.Context r5, java.lang.String r6, android.os.Bundle r7, int r8, com.facebook.internal.WebDialog.OnCompleteListener r9) {
        /*
            r4 = this;
            if (r8 != 0) goto L_0x0007
            com.facebook.internal.Validate.sdkInitialized()
            int r8 = com.facebook.FacebookSdk.webDialogTheme
        L_0x0007:
            r4.<init>(r5, r8)
            java.lang.String r1 = "fbconnect://success"
            r4.expectedRedirectUrl = r1
            r3 = 0
            r4.listenerCalled = r3
            r4.isDetached = r3
            r4.isPageFinished = r3
            if (r7 != 0) goto L_0x001c
            android.os.Bundle r7 = new android.os.Bundle
            r7.<init>()
        L_0x001c:
            java.lang.String r0 = "redirect_uri"
            r7.putString(r0, r1)
            java.lang.String r1 = "display"
            java.lang.String r0 = "touch"
            r7.putString(r1, r0)
            java.util.Locale r2 = java.util.Locale.ROOT
            r0 = 1
            java.lang.Object[] r1 = new java.lang.Object[r0]
            java.lang.String r0 = "4.8.2"
            r1[r3] = r0
            java.lang.String r0 = "android-%s"
            java.lang.String r1 = java.lang.String.format(r2, r0, r1)
            java.lang.String r0 = "sdk"
            r7.putString(r0, r1)
            java.lang.String r3 = com.facebook.internal.ServerProtocol.getDialogAuthority()
            java.lang.String r2 = "v2.5"
            java.lang.String r1 = "/"
            java.lang.String r0 = "dialog/"
            java.lang.String r0 = X.AnonymousClass006.A08(r2, r1, r0, r6)
            android.net.Uri r0 = com.facebook.internal.Utility.buildUri(r3, r0, r7)
            java.lang.String r0 = r0.toString()
            r4.url = r0
            r4.onCompleteListener = r9
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.internal.WebDialog.<init>(android.content.Context, java.lang.String, android.os.Bundle, int, com.facebook.internal.WebDialog$OnCompleteListener):void");
    }
}
