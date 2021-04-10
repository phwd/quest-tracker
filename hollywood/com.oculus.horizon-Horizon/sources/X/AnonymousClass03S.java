package X;

import android.os.AsyncTask;
import android.util.Log;

/* renamed from: X.03S  reason: invalid class name */
public final class AnonymousClass03S extends AsyncTask<Void, Void, Void> {
    public final /* synthetic */ AnonymousClass03W A00;

    public AnonymousClass03S(AnonymousClass03W r1) {
        this.A00 = r1;
    }

    /* Return type fixed from 'java.lang.Object' to match base method */
    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object[]] */
    @Override // android.os.AsyncTask
    public final Void doInBackground(Void[] voidArr) {
        while (true) {
            AnonymousClass03W r2 = this.A00;
            AnonymousClass03U dequeueWork = r2.dequeueWork();
            if (dequeueWork == null) {
                return null;
            }
            r2.onHandleWork(dequeueWork.A3f());
            try {
                dequeueWork.A1m();
            } catch (SecurityException e) {
                if (e.getMessage().contains("Caller no longer running")) {
                    Log.e(AnonymousClass03W.TAG, "Captured a \"Caller no longer running\"", e);
                } else {
                    throw e;
                }
            }
        }
    }

    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object] */
    @Override // android.os.AsyncTask
    public final /* bridge */ /* synthetic */ void onCancelled(Void r2) {
        this.A00.processorFinished();
    }

    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object] */
    @Override // android.os.AsyncTask
    public final /* bridge */ /* synthetic */ void onPostExecute(Void r2) {
        this.A00.processorFinished();
    }
}
