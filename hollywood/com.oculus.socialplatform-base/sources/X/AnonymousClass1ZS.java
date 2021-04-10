package X;

import com.facebook.experiment.mca.MailboxExperimentJNI;
import com.facebook.msys.mca.Mailbox;
import com.facebook.msys.util.NotificationScope;

/* renamed from: X.1ZS  reason: invalid class name */
public class AnonymousClass1ZS implements AnonymousClass1YZ<Mailbox> {
    public final /* synthetic */ AnonymousClass1ZT A00;
    public final /* synthetic */ AnonymousClass1Zb A01;
    public final /* synthetic */ String A02;
    public final /* synthetic */ String A03 = "3d19c1c0923de1997b253b97e02dbea669857c5f3a05fc1c18251a9dbf60e6ec";
    public final /* synthetic */ String A04 = "ls_waterfall_sampling:should_log_waterfall|ios_lightspeed_data_trace_config:append_trace_id_to_topic:message_send_trace_sample_rate:rich_media_download_sample_rate:rich_media_upload_sample_rate:securemessage_send_trace_sample_rate:sync_client_mode_enabled:sync_group_contact_enabled:sync_group_experiment_enabled:sync_group_mailbox_enabled:sync_group_securemailbox_enabled:sync_group_settings_enabled:sync_group_stories_enabled:task_client_mode_enabled:task_label_10005_request_id_sample_rate:task_label_145_request_id_sample_rate:task_label_145_send_trace_sample_rate:task_label_20001_send_trace_sample_rate:task_label_209_request_id_sample_rate:task_label_209_send_trace_sample_rate:task_label_228_request_id_sample_rate:task_label_228_send_trace_sample_rate:task_label_255_send_trace_sample_rate:task_label_46_request_id_sample_rate:task_label_4_send_trace_sample_rate:trace_time_window_ms|msys_sync_config:background_sync_optimization_enabled:minimal_sync_interval:skip_sync_when_no_network|msys_tam_trace_config:enable_tam_armadillo_media_download:enable_tam_armadillo_media_receive:enable_tam_armadillo_media_send:enable_tam_armadillo_message_send:enable_tam_carrier_message_send:enable_tam_message_send_trace|";

    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object] */
    @Override // X.AnonymousClass1YZ
    public final /* bridge */ /* synthetic */ void onCompletion(Mailbox mailbox) {
        Mailbox mailbox2 = mailbox;
        String A002 = AnonymousClass1ZT.A00("MCAMailboxExperimentDidSetExperimentSyncParamsNotification");
        NotificationScope A003 = mailbox2.mNotificationCenterCallbackManager.A00(A002, new AnonymousClass1ZU(this));
        this.A01.A04(A002, A003);
        MailboxExperimentJNI.dispatchVOOOOO(1, mailbox2, this.A04, this.A03, this.A02, A003);
    }

    public AnonymousClass1ZS(AnonymousClass1ZT r3, AnonymousClass1Zb r4, String str) {
        this.A00 = r3;
        this.A01 = r4;
        this.A02 = str;
    }
}
