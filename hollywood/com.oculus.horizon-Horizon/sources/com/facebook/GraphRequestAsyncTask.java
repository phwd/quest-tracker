package com.facebook;

import android.os.AsyncTask;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import java.net.HttpURLConnection;
import java.util.Collection;
import java.util.List;

public class GraphRequestAsyncTask extends AsyncTask<Void, Void, List<GraphResponse>> {
    public static final String TAG = "com.facebook.GraphRequestAsyncTask";
    public final HttpURLConnection connection;
    public Exception exception;
    public final GraphRequestBatch requests;

    public String toString() {
        StringBuilder sb = new StringBuilder("{RequestAsyncTask: ");
        sb.append(" connection: ");
        sb.append(this.connection);
        sb.append(", requests: ");
        sb.append(this.requests);
        sb.append("}");
        return sb.toString();
    }

    public final Exception getException() {
        return this.exception;
    }

    public final GraphRequestBatch getRequests() {
        return this.requests;
    }

    public void onPreExecute() {
        Handler handler;
        super.onPreExecute();
        if (this.requests.callbackHandler == null) {
            if (Thread.currentThread() instanceof HandlerThread) {
                handler = new Handler();
            } else {
                handler = new Handler(Looper.getMainLooper());
            }
            this.requests.callbackHandler = handler;
        }
    }

    public GraphRequestAsyncTask(GraphRequestBatch graphRequestBatch) {
        this((HttpURLConnection) null, graphRequestBatch);
    }

    public GraphRequestAsyncTask(HttpURLConnection httpURLConnection, GraphRequestBatch graphRequestBatch) {
        this.requests = graphRequestBatch;
        this.connection = httpURLConnection;
    }

    public GraphRequestAsyncTask(HttpURLConnection httpURLConnection, Collection<GraphRequest> collection) {
        this(httpURLConnection, new GraphRequestBatch(collection));
    }

    public GraphRequestAsyncTask(HttpURLConnection httpURLConnection, GraphRequest... graphRequestArr) {
        this(httpURLConnection, new GraphRequestBatch(graphRequestArr));
    }

    public GraphRequestAsyncTask(Collection<GraphRequest> collection) {
        this((HttpURLConnection) null, new GraphRequestBatch(collection));
    }

    public GraphRequestAsyncTask(GraphRequest... graphRequestArr) {
        this((HttpURLConnection) null, new GraphRequestBatch(graphRequestArr));
    }

    public List<GraphResponse> doInBackground(Void... voidArr) {
        try {
            HttpURLConnection httpURLConnection = this.connection;
            if (httpURLConnection == null) {
                return GraphRequest.executeBatchAndWait(this.requests);
            }
            return GraphRequest.executeConnectionAndWait(httpURLConnection, this.requests);
        } catch (Exception e) {
            this.exception = e;
            return null;
        }
    }

    public void onPostExecute(List<GraphResponse> list) {
        super.onPostExecute((Object) list);
        Exception exc = this.exception;
        if (exc != null) {
            exc.getMessage();
        }
    }
}
