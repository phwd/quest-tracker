package com.oculus.horizon.api.store;

import com.oculus.horizon.api.common.Item;
import com.oculus.horizon.api.common.Section;
import com.oculus.http.core.base.ValidatableApiResponse;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Nullable;

public class StoresResponse implements ValidatableApiResponse {
    public Viewer viewer;

    public static class Viewer {
        public AppStore app_store;

        public static class AppStore {
            public String id;
            public String name;
            public Sections sections;
            @Nullable
            public Section.Assets store_assets;
            @Nullable
            public List<Item.StoreWideOffer> store_wide_offers;
            @Nullable
            public String style_theme;

            public static class Sections {
                public ArrayList<Section> nodes;
            }
        }
    }

    @Override // com.oculus.http.core.base.ValidatableApiResponse
    public void validate() {
        Viewer viewer2 = this.viewer;
        if (viewer2 == null || viewer2.app_store == null) {
            throw new NullPointerException("Did not receive a valid StoresResponse from server. Response did not contain app_store");
        }
    }
}
