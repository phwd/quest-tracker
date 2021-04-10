package com.facebook.crudolib.urimap.runtime;

import android.annotation.SuppressLint;
import android.app.Activity;
import com.facebook.common.componentmap.ComponentMapConfig;
import com.facebook.common.componentmap.ComponentMapType;
import com.facebook.crudolib.urimap.UriMatchPatterns;
import com.facebook.crudolib.urimap.UriPattern;
import com.facebook.crudolib.urimap.UriScheme;
import com.facebook.infer.annotation.Nullsafe;

@UriMatchPatterns({@UriPattern(pattern = "dummy://pattern", scheme = UriScheme.NONE)})
@ComponentMapConfig({ComponentMapType.NONE})
@SuppressLint({"ImplicitOrNoneSchemeInURIHandler"})
@Nullsafe(Nullsafe.Mode.LOCAL)
public class DummyComponentMapActivity extends Activity {
}
