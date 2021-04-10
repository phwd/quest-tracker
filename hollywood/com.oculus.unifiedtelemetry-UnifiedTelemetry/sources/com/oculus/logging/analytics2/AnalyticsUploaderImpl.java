package com.oculus.logging.analytics2;

import X.AbstractC0096Hu;
import X.QB;
import X.QC;
import android.content.Context;
import com.facebook.analytics2.uploader.okhttp3.OkHttp3AnalyticsUploader;

public class AnalyticsUploaderImpl extends OkHttp3AnalyticsUploader implements QB {
    public QC _UL_mInjectionContext;

    public AnalyticsUploaderImpl(Context context) {
        super(context);
        this._UL_mInjectionContext = new QC(3, AbstractC0096Hu.get(context));
    }
}
