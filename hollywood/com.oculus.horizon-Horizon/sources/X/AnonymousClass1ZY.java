package X;

/* JADX WARN: Init of enum DEFAULT can be incorrect */
/* JADX WARN: Init of enum MESSENGER can be incorrect */
/* JADX WARN: Init of enum MESSENGER_IMAGE can be incorrect */
/* JADX WARN: Init of enum MESSENGER_ANIMATED_IMAGE can be incorrect */
/* JADX WARN: Init of enum MESSENGER_VIDEO can be incorrect */
/* JADX WARN: Init of enum MESSENGER_VIDEO_SEGMENTED can be incorrect */
/* JADX WARN: Init of enum MESSENGER_AUDIO can be incorrect */
/* JADX WARN: Init of enum MESSENGER_FILE can be incorrect */
/* JADX WARN: Init of enum FACEBOOK can be incorrect */
/* JADX WARN: Init of enum FACEBOOK_VIDEO2 can be incorrect */
/* JADX WARN: Init of enum FBLITE_PHOTO can be incorrect */
/* JADX WARN: Init of enum INSTAGRAM_VIDEO can be incorrect */
/* JADX WARN: Init of enum INSTAGRAM_PHOTO can be incorrect */
/* JADX WARN: Init of enum IGWB_SELFIE_CAPTCHA can be incorrect */
/* JADX WARN: Init of enum GRAPHQL can be incorrect */
/* JADX WARN: Init of enum GROUPS can be incorrect */
/* JADX WARN: Init of enum FLASH can be incorrect */
/* JADX WARN: Init of enum SPUTNIK_PHOTO can be incorrect */
/* JADX WARN: Init of enum SPUTNIK_VIDEO can be incorrect */
/* JADX WARN: Init of enum RTC_SNAPSHOT can be incorrect */
/* JADX WARN: Init of enum OCULUS_CLOUD_STORAGE can be incorrect */
/* JADX WARN: Init of enum OCULUS_ARIANE_RECORDING_UPLOAD can be incorrect */
/* JADX WARN: Init of enum PAGES_MANAGER can be incorrect */
/* JADX WARN: Init of enum RELIABILITY_EVENT_LOG can be incorrect */
/* JADX WARN: Init of enum AUTHENTICITY can be incorrect */
/* JADX WARN: Init of enum HALO_CROWDSOURCING can be incorrect */
/* JADX WARN: Init of enum ADS_SCREEN_CAPTURE can be incorrect */
/* JADX WARN: Init of enum HOBBI can be incorrect */
/* JADX WARN: Init of enum TRANSIENT_ANALYSIS can be incorrect */
/* JADX WARN: Init of enum PORTAL_PHOTOSHUB_MEDIA can be incorrect */
/* JADX WARN: Init of enum WEARABLES_PHOTOSHUB can be incorrect */
/* JADX WARN: Init of enum PORTAL_COMMS_SECURE_FILE_UPLOAD can be incorrect */
/* JADX WARN: Init of enum CALIBRA can be incorrect */
/* JADX WARN: Init of enum CALIBRA_RC can be incorrect */
/* JADX WARN: Init of enum STELLA_PLAYGROUND_CAPTURE can be incorrect */
/* JADX WARN: Init of enum FB_PHOTO can be incorrect */
/* JADX WARN: Init of enum AI_DEMOS can be incorrect */
/* JADX WARN: Init of enum COMPOSER_MEDIA_TEMPLATES can be incorrect */
/* JADX WARN: Init of enum WEARABLES_BACKUP can be incorrect */
/* JADX WARN: Init of enum SERVICE_MENU_UPLOADS can be incorrect */
/* JADX WARN: Init of enum WEARABLES_SELFCARE_UPLOADER can be incorrect */
/* renamed from: X.1ZY  reason: invalid class name */
public enum AnonymousClass1ZY {
    DEFAULT("up", r2),
    MESSENGER("up", r2),
    MESSENGER_IMAGE("messenger_image", r2),
    MESSENGER_ANIMATED_IMAGE("messenger_gif", r2),
    MESSENGER_VIDEO("messenger_video", r2),
    MESSENGER_VIDEO_SEGMENTED("messenger_videos", r2),
    MESSENGER_AUDIO("messenger_audio", r2),
    MESSENGER_FILE("messenger_file", r2),
    FACEBOOK("fb_video", r4),
    FACEBOOK_VIDEO2("fb_video2", r4),
    FBLITE_PHOTO("fb_lite_photo", r4),
    INSTAGRAM_VIDEO("rupload_igvideo", r4),
    INSTAGRAM_PHOTO("rupload_igphoto", r4),
    IGWB_SELFIE_CAPTCHA("rupload_igvideo", r4),
    GRAPHQL("graphql_mutations", r4),
    GROUPS("groups", r4),
    FLASH("flash", r2),
    SPUTNIK_PHOTO("sputnik_photo", r2),
    SPUTNIK_VIDEO("sputnik_video", r2),
    RTC_PHOTOBOOTH("messenger_image", AnonymousClass1ZZ.CDN_URL),
    RTC_SNAPSHOT("up", r2),
    OCULUS_CLOUD_STORAGE("oculus_cloud_storage", r4),
    OCULUS_ARIANE_RECORDING_UPLOAD("oculus_ariane_recording_upload", r4),
    PAGES_MANAGER("pma", r4),
    RELIABILITY_EVENT_LOG("reliability_event_log", r2),
    AUTHENTICITY("authenticity_upload", r4),
    HALO_CROWDSOURCING("halo_crowdsourcing", r4),
    ADS_SCREEN_CAPTURE("ads_screen_capture", r4),
    HOBBI("hobbi", r4),
    TRANSIENT_ANALYSIS("transient_analysis", r4),
    PORTAL_PHOTOSHUB_MEDIA("photoshub_upload_media", r2),
    WEARABLES_PHOTOSHUB("wearables_photoshub_uploader", r2),
    PORTAL_COMMS_SECURE_FILE_UPLOAD("secure_file_upload", r2),
    CALIBRA("calibra", r4),
    CALIBRA_RC("calibra_rc", r4),
    STELLA_PLAYGROUND_CAPTURE("stella_playground_capture", r4),
    FB_PHOTO("fb_photo", r2),
    AI_DEMOS("ai_demos", r4),
    COMPOSER_MEDIA_TEMPLATES("composer_media_templates-preview", r2),
    WEARABLES_BACKUP("wearables_backup_uploader", r2),
    SERVICE_MENU_UPLOADS("service_menu_uploads", r2),
    WEARABLES_SELFCARE_UPLOADER("wearables_selfcare_uploader", r2);
    
    public final AnonymousClass1ZZ mJsonResponseFieldType;
    public final String mUriPathElement;

    /* access modifiers changed from: public */
    static {
        AnonymousClass1ZZ r2 = AnonymousClass1ZZ.MEDIA_ID;
        AnonymousClass1ZZ r4 = AnonymousClass1ZZ.HANDLE;
    }

    /* access modifiers changed from: public */
    AnonymousClass1ZY(String str, AnonymousClass1ZZ r4) {
        this.mUriPathElement = str;
        this.mJsonResponseFieldType = r4;
    }

    public static AnonymousClass1ZY getNamespaceFromString(String str) {
        AnonymousClass1ZY[] values = values();
        for (AnonymousClass1ZY r1 : values) {
            if (r1.getUriPathElement().equals(str)) {
                return r1;
            }
        }
        return FACEBOOK;
    }

    public AnonymousClass1ZZ getJsonResponseFieldType() {
        return this.mJsonResponseFieldType;
    }

    public String getUriPathElement() {
        return this.mUriPathElement;
    }
}
