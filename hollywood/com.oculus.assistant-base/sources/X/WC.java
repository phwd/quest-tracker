package X;

import android.app.job.JobParameters;
import android.app.job.JobScheduler;
import android.database.SQLException;
import android.preference.PreferenceManager;
import com.facebook.assistant.clientplatform.keyboard.learning.util.LearningStore;
import com.oculus.assistant.learning.DataDeletionJobService;

public final class WC implements Runnable {
    public static final String __redex_internal_original_name = "com.oculus.assistant.learning.DataDeletionJobService$1";
    public final /* synthetic */ JobParameters A00;
    public final /* synthetic */ DataDeletionJobService A01;

    public WC(DataDeletionJobService dataDeletionJobService, JobParameters jobParameters) {
        this.A01 = dataDeletionJobService;
        this.A00 = jobParameters;
    }

    public final void run() {
        boolean deleteDatabase;
        int delete;
        try {
            DataDeletionJobService dataDeletionJobService = this.A01;
            String string = PreferenceManager.getDefaultSharedPreferences(dataDeletionJobService.getApplicationContext()).getString("assistant_keyboard_fl_device_name", "oculus");
            if (WB.A00()) {
                LearningStore A002 = LearningStore.A00(string, dataDeletionJobService);
                synchronized (A002) {
                    delete = A002.A00.getWritableDatabase().delete(A002.A00.A01, AnonymousClass08.A05("timestamp < datetime('now', '-", Integer.toString(88), " days')"), new String[0]);
                }
                String format = String.format("Deletion job ran successfully, number of deleted rows: %d", Integer.valueOf(delete));
                C0139Dd.A09("com.oculus.assistant.learning.DataDeletionJobService", format);
                if (delete != 0) {
                    C00799i.A00.logServiceEvent(format);
                }
                dataDeletionJobService.jobFinished(this.A00, true);
            } else if (PreferenceManager.getDefaultSharedPreferences(dataDeletionJobService.getApplicationContext()).getBoolean("assistant_keyboard_fl_user_opted_in_setting", false)) {
                LearningStore A003 = LearningStore.A00(string, dataDeletionJobService);
                synchronized (A003) {
                    A003.A00.close();
                    deleteDatabase = dataDeletionJobService.deleteDatabase("federated_learning_store.db");
                }
                String format2 = String.format("User opted out of FL, Deletion job deleting entire example store. Succeeded: %b", Boolean.valueOf(deleteDatabase));
                C0139Dd.A09("com.oculus.assistant.learning.DataDeletionJobService", format2);
                C00799i.A00.logServiceEvent(format2);
                C00638p.A00(dataDeletionJobService, "assistant_keyboard_fl_device_name");
                C00638p.A00(dataDeletionJobService, "assistant_keyboard_fl_papaya_last_submit");
                C00638p.A00(dataDeletionJobService, "assistant_keyboard_fl_data_num_rows");
                C00638p.A02(dataDeletionJobService, "assistant_keyboard_fl_user_opted_in_setting", false);
                JobScheduler jobScheduler = (JobScheduler) dataDeletionJobService.getSystemService("jobscheduler");
                if (jobScheduler != null) {
                    jobScheduler.cancel(2020);
                }
                dataDeletionJobService.jobFinished(this.A00, true);
            }
        } catch (SQLException e) {
            C0139Dd.A0S("com.oculus.assistant.learning.DataDeletionJobService", e, "SQL error while enforcing retention policy");
            C00799i.A00.logException("SQL error while enforcing retention policy", e);
            this.A01.jobFinished(this.A00, true);
        } catch (Exception e2) {
            C0139Dd.A0S("com.oculus.assistant.learning.DataDeletionJobService", e2, "Unexpected error while enforcing retention policy");
            C00799i.A00.logException("Unexpected error while enforcing retention policy", e2);
            this.A01.jobFinished(this.A00, false);
        }
    }
}
