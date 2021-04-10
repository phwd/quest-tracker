package com.oculus.modules;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import com.oculus.modules.codegen.ConnectivityStateModule;
import com.oculus.modules.codegen.Resolver;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

public class ConnectivityStateModuleImpl extends ConnectivityStateModule {
    private static final long INTERNET_CONNECTION_CHECK_PERIOD_MS = TimeUnit.SECONDS.toMillis(15);
    private static final int INTERNET_CONNECTION_CHECK_RESPONSE_CODE = 204;
    private static final long INTERNET_CONNECTION_CHECK_TIMEOUT_MS = TimeUnit.SECONDS.toMillis(10);
    private static final String INTERNET_CONNECTION_CHECK_URL = "http://portal.fb.com/mobile/status.php";
    private static final String TAG = ConnectivityStateModule.class.getSimpleName();
    private static ConnectivityManager connectivityManager;
    private Boolean connectedToInternet = null;
    private Boolean connectedToWiFi = null;
    private Timer sInternetConnectionCheckTimer = null;
    private InternetConnectionCheckTimerTask sInternetConnectionCheckTimerTask = null;

    private class InternetConnectionCheckTimerTask extends TimerTask {
        private InternetConnectionCheckTimerTask() {
        }

        public void run() {
            ConnectivityStateModuleImpl.this.setInternetConnection();
        }
    }

    public ConnectivityStateModuleImpl(Context context) {
        super(context);
        connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
    }

    @Override // com.oculus.panellib.modules.AbstractBroadcastReceiverModule
    public IntentFilter getIntentFilter() {
        return new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE");
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.panellib.modules.AbstractBroadcastReceiverModule
    public void onNoStickyIntent() {
        onWiFiConnectivityUpdated();
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.panellib.modules.AbstractBroadcastReceiverModule
    public void processIntent(Intent intent, boolean forceNativeUpdate) {
        Boolean previousConnectedToWiFi = this.connectedToWiFi;
        if (intent.getAction() == "android.net.conn.CONNECTIVITY_CHANGE") {
            setWifiConnection();
        }
        if (forceNativeUpdate || previousConnectedToWiFi != this.connectedToWiFi) {
            onWiFiConnectivityUpdated();
        }
    }

    private void onWiFiConnectivityUpdated() {
        ConnectivityStateModule.ConnectivityUpdate update = new ConnectivityStateModule.ConnectivityUpdate();
        update.connected = this.connectedToWiFi == null ? false : this.connectedToWiFi.booleanValue();
        emitOnWiFiConnectivityUpdated(update);
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.modules.codegen.ConnectivityStateModule
    public void startCheckingForInternetConnectionImpl(Resolver<Boolean> resolver) {
        if (this.sInternetConnectionCheckTimerTask == null) {
            this.sInternetConnectionCheckTimerTask = new InternetConnectionCheckTimerTask();
            this.sInternetConnectionCheckTimer = new Timer("Internet Connection Check Timer");
            this.sInternetConnectionCheckTimer.schedule(this.sInternetConnectionCheckTimerTask, 0, INTERNET_CONNECTION_CHECK_PERIOD_MS);
            resolver.resolve(true);
            return;
        }
        Log.w(TAG, "Internet connection check is already running.");
        resolver.resolve(false);
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.modules.codegen.ConnectivityStateModule
    public void stopCheckingForInternetConnectionImpl(Resolver<Boolean> resolver) {
        if (this.sInternetConnectionCheckTimerTask != null) {
            this.sInternetConnectionCheckTimerTask.cancel();
            this.sInternetConnectionCheckTimer.cancel();
            this.sInternetConnectionCheckTimerTask = null;
            this.sInternetConnectionCheckTimer = null;
            this.connectedToInternet = null;
            resolver.resolve(true);
            return;
        }
        Log.w(TAG, "Internet connection was not running.");
        resolver.resolve(false);
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.modules.codegen.ConnectivityStateModule
    public void isConnectedToWifiImpl(Resolver<Boolean> resolver) {
        if (this.connectedToWiFi == null) {
            setWifiConnection();
        }
        resolver.resolve(this.connectedToWiFi);
    }

    private void setWifiConnection() {
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        this.connectedToWiFi = Boolean.valueOf(networkInfo != null && networkInfo.isConnectedOrConnecting());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setInternetConnection() {
        boolean z;
        Boolean previousConnectedToInternet = this.connectedToInternet;
        try {
            HttpURLConnection conn = (HttpURLConnection) new URL(INTERNET_CONNECTION_CHECK_URL).openConnection();
            conn.setRequestProperty("Connection", "close");
            conn.setConnectTimeout((int) INTERNET_CONNECTION_CHECK_TIMEOUT_MS);
            if (conn.getResponseCode() == 204) {
                z = true;
            } else {
                z = false;
            }
            this.connectedToInternet = Boolean.valueOf(z);
        } catch (SocketTimeoutException e) {
            this.connectedToInternet = false;
            Log.e(TAG, "Timed out while checking internet connection.", e);
        } catch (IOException e2) {
            Log.e(TAG, "Error while checking internet connection.", e2);
            this.connectedToInternet = false;
        }
        if (previousConnectedToInternet != this.connectedToInternet) {
            onInternetConnectivityUpdated();
        }
    }

    private void onInternetConnectivityUpdated() {
        ConnectivityStateModule.ConnectivityUpdate update = new ConnectivityStateModule.ConnectivityUpdate();
        update.connected = this.connectedToInternet.booleanValue();
        emitOnInternetConnectivityUpdated(update);
    }
}
