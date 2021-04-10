package com.oculus.horizon.api.profile;

import com.google.common.base.Joiner;
import com.google.common.collect.ImmutableMap;
import com.oculus.http.core.base.ApiRequest;
import java.util.Calendar;

public class MyUserProfileRequest extends ApiRequest<MyUserProfileResponse> {
    private static final int DEFAULT_PIC_SIZE = 360;
    public final String accessToken;
    public final int profilePictureDimension;

    public MyUserProfileRequest() {
        this(null);
    }

    public MyUserProfileRequest(String str) {
        this.accessToken = str;
        this.profilePictureDimension = 360;
    }

    public ImmutableMap<String, String> getParams() {
        Calendar instance = Calendar.getInstance();
        instance.add(12, -10);
        return ImmutableMap.of("pic_dimension", getPicDimensionString(), "sent_after", String.valueOf(instance.getTimeInMillis() / 1000));
    }

    private String getPicDimensionString() {
        return Joiner.on("x").join(Integer.valueOf(this.profilePictureDimension), Integer.valueOf(this.profilePictureDimension), new Object[0]);
    }
}
