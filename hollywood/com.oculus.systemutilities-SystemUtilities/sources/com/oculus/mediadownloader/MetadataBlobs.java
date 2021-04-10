package com.oculus.mediadownloader;

import android.net.Uri;
import android.text.TextUtils;
import android.util.Pair;
import android.webkit.MimeTypeMap;
import java.util.LinkedList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;

public class MetadataBlobs {
    private final List<Pair<Uri, String>> blobEntries = new LinkedList();
    public final String srcJson;

    public MetadataBlobs(String json) {
        this.srcJson = json;
        if (!TextUtils.isEmpty(this.srcJson)) {
            try {
                JSONArray array = new JSONArray(json);
                for (int i = 0; i < array.length(); i++) {
                    Uri uri = Uri.parse(array.getString(i));
                    this.blobEntries.add(Pair.create(uri, i + "." + MimeTypeMap.getFileExtensionFromUrl(uri.toString())));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    public String toString() {
        JSONArray jsonArray = new JSONArray();
        for (int i = 0; i < this.blobEntries.size(); i++) {
            jsonArray.put(this.blobEntries.get(i).first);
        }
        return jsonArray.toString();
    }
}
