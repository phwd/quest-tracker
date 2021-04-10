package com.oculus.horizon.api.profile;

import com.google.common.base.Joiner$3;
import com.google.common.collect.ImmutableMap;
import com.oculus.http.core.base.ApiRequest;
import java.io.IOException;
import java.util.Calendar;
import java.util.Iterator;

public class MyUserProfileRequest extends ApiRequest<MyUserProfileResponse> {
    public static final int DEFAULT_PIC_SIZE = 360;
    public final String accessToken;
    public final int profilePictureDimension;

    private String getPicDimensionString() {
        CharSequence charSequence;
        Integer valueOf = Integer.valueOf(this.profilePictureDimension);
        Iterator it = new Joiner$3(new Object[0], valueOf, valueOf).iterator();
        StringBuilder sb = new StringBuilder();
        try {
            if (it.hasNext()) {
                Object next = it.next();
                if (next != null) {
                    if (next instanceof CharSequence) {
                        charSequence = (CharSequence) next;
                    } else {
                        charSequence = next.toString();
                    }
                    while (true) {
                        sb.append(charSequence);
                        if (!it.hasNext()) {
                            break;
                        }
                        sb.append((CharSequence) "x");
                        Object next2 = it.next();
                        if (next2 == null) {
                            throw null;
                        } else if (next2 instanceof CharSequence) {
                            charSequence = (CharSequence) next2;
                        } else {
                            charSequence = next2.toString();
                        }
                    }
                } else {
                    throw null;
                }
            }
            return sb.toString();
        } catch (IOException e) {
            throw new AssertionError(e);
        }
    }

    public ImmutableMap<String, String> getParams() {
        Calendar instance = Calendar.getInstance();
        instance.add(12, -10);
        return ImmutableMap.A06("pic_dimension", getPicDimensionString(), "sent_after", String.valueOf(instance.getTimeInMillis() / 1000));
    }

    public MyUserProfileRequest() {
        this(null);
    }

    public MyUserProfileRequest(String str) {
        this.accessToken = str;
        this.profilePictureDimension = 360;
    }
}
