package com.oculus.modules.codegen;

import android.util.Pair;
import com.oculus.appmanager.assets.database.assetcontract.AssetContract;
import com.oculus.panellib.modules.RCTBaseJavaModule;
import com.oculus.panellib.quickpromotion.QuickPromotionGraphQL;
import com.oculus.panellib.quickpromotion.QuickPromotionLogEvent;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

public abstract class QuickPromotionModule extends RCTBaseJavaModule {
    protected static final String MODULE_NAME = QuickPromotionModule.class.getSimpleName();

    public enum QPApplication {
        HOME,
        LIVING_ROOM
    }

    /* access modifiers changed from: protected */
    public abstract void disableSurfaceImpl(double d, Resolver<Void> resolver);

    /* access modifiers changed from: protected */
    public abstract void enableSurfaceImpl(double d, Resolver<Void> resolver);

    /* access modifiers changed from: protected */
    public abstract void initializeImpl(QPApplication qPApplication, boolean z, boolean z2, boolean z3, boolean z4, Resolver<Void> resolver);

    /* access modifiers changed from: protected */
    public abstract void markPromotionActionImpl(QPPromotion qPPromotion, String str, String str2, Resolver<Void> resolver);

    /* access modifiers changed from: protected */
    public abstract void markPromotionExposedImpl(QPPromotion qPPromotion, String str, Resolver<Void> resolver);

    /* access modifiers changed from: protected */
    public abstract void markPromotionHoldoutExposedImpl(QPPromotion qPPromotion, String str, Resolver<Void> resolver);

    /* access modifiers changed from: protected */
    public abstract void markPromotionShownImpl(QPPromotion qPPromotion, String str, Resolver<Void> resolver);

    /* access modifiers changed from: protected */
    public abstract void refetchAllSurfacesImpl(Resolver<Void> resolver);

    /* access modifiers changed from: protected */
    public abstract void refetchSurfaceImpl(double d, Resolver<Void> resolver);

    /* access modifiers changed from: protected */
    public abstract void setAccessTokenImpl(String str, Resolver<Void> resolver);

    /* access modifiers changed from: protected */
    public abstract void signalTriggerImpl(String str, Resolver<Void> resolver);

    /* access modifiers changed from: protected */
    public final String getModuleName() {
        return MODULE_NAME;
    }

    protected static final List<Pair<String, String>> describe() {
        List<Pair<String, String>> list = new ArrayList<>();
        list.add(new Pair<>("disableSurface", "+dii"));
        list.add(new Pair<>("enableSurface", "+dii"));
        list.add(new Pair<>("initialize", "+sbbbbii"));
        list.add(new Pair<>("markPromotionAction", "+jssii"));
        list.add(new Pair<>("markPromotionExposed", "+jsii"));
        list.add(new Pair<>("markPromotionHoldoutExposed", "+jsii"));
        list.add(new Pair<>("markPromotionShown", "+jsii"));
        list.add(new Pair<>("refetchAllSurfaces", "+ii"));
        list.add(new Pair<>("refetchSurface", "+dii"));
        list.add(new Pair<>("setAccessToken", "+sii"));
        list.add(new Pair<>("signalTrigger", "+sii"));
        return list;
    }

    /* access modifiers changed from: protected */
    public final String marshallJSConstants() {
        return null;
    }

    /* access modifiers changed from: protected */
    public final void emitOnTryToShow(QPTryToShowEvent data) {
        nativeEmitEventWithJsonData(this.RVRCtxTag, "QuickPromotionModule_onTryToShow", String.valueOf(data.convertToJSONObject()));
    }

    /* access modifiers changed from: protected */
    public final void disableSurface(double surfaceID, int resolveID, int rejectID) {
        disableSurfaceImpl(surfaceID, ResolverFactory.createVoidResolver(this, resolveID, rejectID));
    }

    /* access modifiers changed from: protected */
    public final void enableSurface(double surfaceID, int resolveID, int rejectID) {
        enableSurfaceImpl(surfaceID, ResolverFactory.createVoidResolver(this, resolveID, rejectID));
    }

    /* access modifiers changed from: protected */
    public final void initialize(String application, boolean enabled, boolean shouldSendActionGraphQL, boolean shouldSendExposureGraphQL, boolean shouldSendImpressionGraphQL, int resolveID, int rejectID) {
        initializeImpl(QPApplication.valueOf(application), enabled, shouldSendActionGraphQL, shouldSendExposureGraphQL, shouldSendImpressionGraphQL, ResolverFactory.createVoidResolver(this, resolveID, rejectID));
    }

    /* access modifiers changed from: protected */
    public final void markPromotionAction(JSONObject promotion, String trigger, String action, int resolveID, int rejectID) {
        markPromotionActionImpl(QPPromotion.makeFromJSONObject(promotion), trigger, action, ResolverFactory.createVoidResolver(this, resolveID, rejectID));
    }

    /* access modifiers changed from: protected */
    public final void markPromotionExposed(JSONObject promotion, String trigger, int resolveID, int rejectID) {
        markPromotionExposedImpl(QPPromotion.makeFromJSONObject(promotion), trigger, ResolverFactory.createVoidResolver(this, resolveID, rejectID));
    }

    /* access modifiers changed from: protected */
    public final void markPromotionHoldoutExposed(JSONObject promotion, String trigger, int resolveID, int rejectID) {
        markPromotionHoldoutExposedImpl(QPPromotion.makeFromJSONObject(promotion), trigger, ResolverFactory.createVoidResolver(this, resolveID, rejectID));
    }

    /* access modifiers changed from: protected */
    public final void markPromotionShown(JSONObject promotion, String trigger, int resolveID, int rejectID) {
        markPromotionShownImpl(QPPromotion.makeFromJSONObject(promotion), trigger, ResolverFactory.createVoidResolver(this, resolveID, rejectID));
    }

    /* access modifiers changed from: protected */
    public final void refetchAllSurfaces(int resolveID, int rejectID) {
        refetchAllSurfacesImpl(ResolverFactory.createVoidResolver(this, resolveID, rejectID));
    }

    /* access modifiers changed from: protected */
    public final void refetchSurface(double surfaceID, int resolveID, int rejectID) {
        refetchSurfaceImpl(surfaceID, ResolverFactory.createVoidResolver(this, resolveID, rejectID));
    }

    /* access modifiers changed from: protected */
    public final void setAccessToken(String accessToken, int resolveID, int rejectID) {
        setAccessTokenImpl(accessToken, ResolverFactory.createVoidResolver(this, resolveID, rejectID));
    }

    /* access modifiers changed from: protected */
    public final void signalTrigger(String trigger, int resolveID, int rejectID) {
        signalTriggerImpl(trigger, ResolverFactory.createVoidResolver(this, resolveID, rejectID));
    }

    public void shutdownModule() {
    }

    public static class QPTryToShowEvent extends NativeModuleParcel {
        public List<QPPromotion> promotions;
        public String trigger;

        @Override // com.oculus.modules.codegen.NativeModuleParcel
        public final JSONObject convertToJSONObject() {
            JSONObject parcel = new JSONObject();
            try {
                parcel.put("promotions", NativeModuleParcel.convertParcelListToJSONArray(this.promotions));
                parcel.put(QuickPromotionLogEvent.KEY_TRIGGER, this.trigger);
            } catch (JSONException e) {
            }
            return parcel;
        }

        @Override // com.oculus.modules.codegen.NativeModuleParcel
        public final void setFromJSONObject(JSONObject json) {
            this.promotions = NativeModuleParcel.convertJSONArrayToParcelList(json.optJSONArray("promotions"), QPPromotion.class);
            this.trigger = json.optString(QuickPromotionLogEvent.KEY_TRIGGER);
        }

        public static final QPTryToShowEvent makeFromJSONObject(JSONObject json) {
            if (json == null) {
                return null;
            }
            QPTryToShowEvent result = new QPTryToShowEvent();
            result.setFromJSONObject(json);
            return result;
        }
    }

    public static class QPPromotion extends NativeModuleParcel {
        public boolean bypass_surface_delay;
        public QPPromotionContextual_filters contextual_filters;
        public boolean is_holdout;
        public String logging_data;
        public double max_impressions;
        public List<QPCreative> promotion_creatives;
        public String promotion_id;
        public QPTemplate promotion_template;
        public double surface_id;
        public List<String> triggers;

        @Override // com.oculus.modules.codegen.NativeModuleParcel
        public final JSONObject convertToJSONObject() {
            JSONObject parcel = new JSONObject();
            try {
                parcel.put("bypass_surface_delay", this.bypass_surface_delay);
                parcel.put("contextual_filters", this.contextual_filters.convertToJSONObject());
                parcel.put("is_holdout", this.is_holdout);
                parcel.put("logging_data", this.logging_data);
                parcel.put("max_impressions", this.max_impressions);
                parcel.put("promotion_creatives", NativeModuleParcel.convertParcelListToJSONArray(this.promotion_creatives));
                parcel.put(QuickPromotionLogEvent.KEY_PROMOTION_ID, this.promotion_id);
                parcel.put("promotion_template", this.promotion_template.convertToJSONObject());
                parcel.put(QuickPromotionGraphQL.ARGUMENT_SURFACE_ID, this.surface_id);
                parcel.put("triggers", NativeModuleParcel.convertStringListToJSONArray(this.triggers));
            } catch (JSONException e) {
            }
            return parcel;
        }

        @Override // com.oculus.modules.codegen.NativeModuleParcel
        public final void setFromJSONObject(JSONObject json) {
            this.bypass_surface_delay = json.optBoolean("bypass_surface_delay");
            this.contextual_filters = QPPromotionContextual_filters.makeFromJSONObject(json.optJSONObject("contextual_filters"));
            this.is_holdout = json.optBoolean("is_holdout");
            this.logging_data = json.optString("logging_data");
            this.max_impressions = json.optDouble("max_impressions", 0.0d);
            this.promotion_creatives = NativeModuleParcel.convertJSONArrayToParcelList(json.optJSONArray("promotion_creatives"), QPCreative.class);
            this.promotion_id = json.optString(QuickPromotionLogEvent.KEY_PROMOTION_ID);
            this.promotion_template = QPTemplate.makeFromJSONObject(json.optJSONObject("promotion_template"));
            this.surface_id = json.optDouble(QuickPromotionGraphQL.ARGUMENT_SURFACE_ID, 0.0d);
            this.triggers = NativeModuleParcel.convertJSONArrayToStringList(json.optJSONArray("triggers"));
        }

        public static final QPPromotion makeFromJSONObject(JSONObject json) {
            if (json == null) {
                return null;
            }
            QPPromotion result = new QPPromotion();
            result.setFromJSONObject(json);
            return result;
        }
    }

    public static class QPPromotionContextual_filters extends NativeModuleParcel {
        public String clause_type;
        public List<QPPromotionContextual_filtersClauses> clauses;
        public List<QPFilter> filters;

        @Override // com.oculus.modules.codegen.NativeModuleParcel
        public final JSONObject convertToJSONObject() {
            JSONObject parcel = new JSONObject();
            try {
                parcel.put("clause_type", this.clause_type);
                if (this.filters != null) {
                    parcel.put("filters", NativeModuleParcel.convertParcelListToJSONArray(this.filters));
                }
                if (this.clauses != null) {
                    parcel.put("clauses", NativeModuleParcel.convertParcelListToJSONArray(this.clauses));
                }
            } catch (JSONException e) {
            }
            return parcel;
        }

        @Override // com.oculus.modules.codegen.NativeModuleParcel
        public final void setFromJSONObject(JSONObject json) {
            ArrayList arrayList = null;
            this.clause_type = json.isNull("clause_type") ? null : json.optString("clause_type");
            this.filters = json.isNull("filters") ? null : NativeModuleParcel.convertJSONArrayToParcelList(json.optJSONArray("filters"), QPFilter.class);
            if (!json.isNull("clauses")) {
                arrayList = NativeModuleParcel.convertJSONArrayToParcelList(json.optJSONArray("clauses"), QPPromotionContextual_filtersClauses.class);
            }
            this.clauses = arrayList;
        }

        public static final QPPromotionContextual_filters makeFromJSONObject(JSONObject json) {
            if (json == null) {
                return null;
            }
            QPPromotionContextual_filters result = new QPPromotionContextual_filters();
            result.setFromJSONObject(json);
            return result;
        }
    }

    public static class QPFilter extends NativeModuleParcel {
        public List<QPParameter> extra_datas;
        public String filter_type;
        public String unknown_action;
        public QPParameter value;

        @Override // com.oculus.modules.codegen.NativeModuleParcel
        public final JSONObject convertToJSONObject() {
            JSONObject parcel = new JSONObject();
            try {
                parcel.put("extra_datas", NativeModuleParcel.convertParcelListToJSONArray(this.extra_datas));
                parcel.put("filter_type", this.filter_type);
                parcel.put("unknown_action", this.unknown_action);
                if (this.value != null) {
                    parcel.put("value", this.value.convertToJSONObject());
                }
            } catch (JSONException e) {
            }
            return parcel;
        }

        @Override // com.oculus.modules.codegen.NativeModuleParcel
        public final void setFromJSONObject(JSONObject json) {
            this.extra_datas = NativeModuleParcel.convertJSONArrayToParcelList(json.optJSONArray("extra_datas"), QPParameter.class);
            this.filter_type = json.optString("filter_type");
            this.unknown_action = json.optString("unknown_action");
            this.value = json.isNull("value") ? null : QPParameter.makeFromJSONObject(json.optJSONObject("value"));
        }

        public static final QPFilter makeFromJSONObject(JSONObject json) {
            if (json == null) {
                return null;
            }
            QPFilter result = new QPFilter();
            result.setFromJSONObject(json);
            return result;
        }
    }

    public static class QPParameter extends NativeModuleParcel {
        public Boolean bool_value;
        public String color_value;
        public Double float_value;
        public Double int_value;
        public String name;
        public boolean required;
        public String string_value;
        public String uri_value;

        @Override // com.oculus.modules.codegen.NativeModuleParcel
        public final JSONObject convertToJSONObject() {
            JSONObject parcel = new JSONObject();
            try {
                parcel.put("bool_value", this.bool_value);
                parcel.put("color_value", this.color_value);
                parcel.put("float_value", this.float_value);
                parcel.put("int_value", this.int_value);
                parcel.put("name", this.name);
                parcel.put(AssetContract.AssetTableColumns.REQUIRED, this.required);
                parcel.put("string_value", this.string_value);
                parcel.put("uri_value", this.uri_value);
            } catch (JSONException e) {
            }
            return parcel;
        }

        @Override // com.oculus.modules.codegen.NativeModuleParcel
        public final void setFromJSONObject(JSONObject json) {
            String str = null;
            this.bool_value = json.isNull("bool_value") ? null : Boolean.valueOf(json.optBoolean("bool_value"));
            this.color_value = json.isNull("color_value") ? null : json.optString("color_value");
            this.float_value = json.isNull("float_value") ? null : Double.valueOf(json.optDouble("float_value", 0.0d));
            this.int_value = json.isNull("int_value") ? null : Double.valueOf(json.optDouble("int_value", 0.0d));
            this.name = json.optString("name");
            this.required = json.optBoolean(AssetContract.AssetTableColumns.REQUIRED);
            this.string_value = json.isNull("string_value") ? null : json.optString("string_value");
            if (!json.isNull("uri_value")) {
                str = json.optString("uri_value");
            }
            this.uri_value = str;
        }

        public static final QPParameter makeFromJSONObject(JSONObject json) {
            if (json == null) {
                return null;
            }
            QPParameter result = new QPParameter();
            result.setFromJSONObject(json);
            return result;
        }
    }

    public static class QPPromotionContextual_filtersClauses extends NativeModuleParcel {
        public String clause_type;
        public List<QPPromotionContextual_filtersClausesClauses> clauses;
        public List<QPFilter> filters;

        @Override // com.oculus.modules.codegen.NativeModuleParcel
        public final JSONObject convertToJSONObject() {
            JSONObject parcel = new JSONObject();
            try {
                parcel.put("clause_type", this.clause_type);
                if (this.filters != null) {
                    parcel.put("filters", NativeModuleParcel.convertParcelListToJSONArray(this.filters));
                }
                if (this.clauses != null) {
                    parcel.put("clauses", NativeModuleParcel.convertParcelListToJSONArray(this.clauses));
                }
            } catch (JSONException e) {
            }
            return parcel;
        }

        @Override // com.oculus.modules.codegen.NativeModuleParcel
        public final void setFromJSONObject(JSONObject json) {
            ArrayList arrayList = null;
            this.clause_type = json.isNull("clause_type") ? null : json.optString("clause_type");
            this.filters = json.isNull("filters") ? null : NativeModuleParcel.convertJSONArrayToParcelList(json.optJSONArray("filters"), QPFilter.class);
            if (!json.isNull("clauses")) {
                arrayList = NativeModuleParcel.convertJSONArrayToParcelList(json.optJSONArray("clauses"), QPPromotionContextual_filtersClausesClauses.class);
            }
            this.clauses = arrayList;
        }

        public static final QPPromotionContextual_filtersClauses makeFromJSONObject(JSONObject json) {
            if (json == null) {
                return null;
            }
            QPPromotionContextual_filtersClauses result = new QPPromotionContextual_filtersClauses();
            result.setFromJSONObject(json);
            return result;
        }
    }

    public static class QPPromotionContextual_filtersClausesClauses extends NativeModuleParcel {
        public String clause_type;
        public List<QPPromotionContextual_filtersClausesClausesClauses> clauses;
        public List<QPFilter> filters;

        @Override // com.oculus.modules.codegen.NativeModuleParcel
        public final JSONObject convertToJSONObject() {
            JSONObject parcel = new JSONObject();
            try {
                parcel.put("clause_type", this.clause_type);
                if (this.filters != null) {
                    parcel.put("filters", NativeModuleParcel.convertParcelListToJSONArray(this.filters));
                }
                if (this.clauses != null) {
                    parcel.put("clauses", NativeModuleParcel.convertParcelListToJSONArray(this.clauses));
                }
            } catch (JSONException e) {
            }
            return parcel;
        }

        @Override // com.oculus.modules.codegen.NativeModuleParcel
        public final void setFromJSONObject(JSONObject json) {
            ArrayList arrayList = null;
            this.clause_type = json.isNull("clause_type") ? null : json.optString("clause_type");
            this.filters = json.isNull("filters") ? null : NativeModuleParcel.convertJSONArrayToParcelList(json.optJSONArray("filters"), QPFilter.class);
            if (!json.isNull("clauses")) {
                arrayList = NativeModuleParcel.convertJSONArrayToParcelList(json.optJSONArray("clauses"), QPPromotionContextual_filtersClausesClausesClauses.class);
            }
            this.clauses = arrayList;
        }

        public static final QPPromotionContextual_filtersClausesClauses makeFromJSONObject(JSONObject json) {
            if (json == null) {
                return null;
            }
            QPPromotionContextual_filtersClausesClauses result = new QPPromotionContextual_filtersClausesClauses();
            result.setFromJSONObject(json);
            return result;
        }
    }

    public static class QPPromotionContextual_filtersClausesClausesClauses extends NativeModuleParcel {
        public String clause_type;
        public List<QPPromotionContextual_filtersClausesClausesClausesClauses> clauses;
        public List<QPFilter> filters;

        @Override // com.oculus.modules.codegen.NativeModuleParcel
        public final JSONObject convertToJSONObject() {
            JSONObject parcel = new JSONObject();
            try {
                parcel.put("clause_type", this.clause_type);
                if (this.filters != null) {
                    parcel.put("filters", NativeModuleParcel.convertParcelListToJSONArray(this.filters));
                }
                if (this.clauses != null) {
                    parcel.put("clauses", NativeModuleParcel.convertParcelListToJSONArray(this.clauses));
                }
            } catch (JSONException e) {
            }
            return parcel;
        }

        @Override // com.oculus.modules.codegen.NativeModuleParcel
        public final void setFromJSONObject(JSONObject json) {
            ArrayList arrayList = null;
            this.clause_type = json.isNull("clause_type") ? null : json.optString("clause_type");
            this.filters = json.isNull("filters") ? null : NativeModuleParcel.convertJSONArrayToParcelList(json.optJSONArray("filters"), QPFilter.class);
            if (!json.isNull("clauses")) {
                arrayList = NativeModuleParcel.convertJSONArrayToParcelList(json.optJSONArray("clauses"), QPPromotionContextual_filtersClausesClausesClausesClauses.class);
            }
            this.clauses = arrayList;
        }

        public static final QPPromotionContextual_filtersClausesClausesClauses makeFromJSONObject(JSONObject json) {
            if (json == null) {
                return null;
            }
            QPPromotionContextual_filtersClausesClausesClauses result = new QPPromotionContextual_filtersClausesClausesClauses();
            result.setFromJSONObject(json);
            return result;
        }
    }

    public static class QPPromotionContextual_filtersClausesClausesClausesClauses extends NativeModuleParcel {
        public String clause_type;

        @Override // com.oculus.modules.codegen.NativeModuleParcel
        public final JSONObject convertToJSONObject() {
            JSONObject parcel = new JSONObject();
            try {
                parcel.put("clause_type", this.clause_type);
            } catch (JSONException e) {
            }
            return parcel;
        }

        @Override // com.oculus.modules.codegen.NativeModuleParcel
        public final void setFromJSONObject(JSONObject json) {
            this.clause_type = json.optString("clause_type");
        }

        public static final QPPromotionContextual_filtersClausesClausesClausesClauses makeFromJSONObject(JSONObject json) {
            if (json == null) {
                return null;
            }
            QPPromotionContextual_filtersClausesClausesClausesClauses result = new QPPromotionContextual_filtersClausesClausesClausesClauses();
            result.setFromJSONObject(json);
            return result;
        }
    }

    public static class QPCreative extends NativeModuleParcel {
        public QPImage auto_size_image;
        public String content_text;
        public QPAction dismiss_action;
        public QPAction primary_action;
        public QPAction secondary_action;
        public String title_text;

        @Override // com.oculus.modules.codegen.NativeModuleParcel
        public final JSONObject convertToJSONObject() {
            JSONObject parcel = new JSONObject();
            try {
                if (this.auto_size_image != null) {
                    parcel.put("auto_size_image", this.auto_size_image.convertToJSONObject());
                }
                if (this.content_text != null) {
                    parcel.put("content_text", this.content_text);
                }
                if (this.dismiss_action != null) {
                    parcel.put("dismiss_action", this.dismiss_action.convertToJSONObject());
                }
                if (this.primary_action != null) {
                    parcel.put("primary_action", this.primary_action.convertToJSONObject());
                }
                if (this.secondary_action != null) {
                    parcel.put("secondary_action", this.secondary_action.convertToJSONObject());
                }
                if (this.title_text != null) {
                    parcel.put("title_text", this.title_text);
                }
            } catch (JSONException e) {
            }
            return parcel;
        }

        @Override // com.oculus.modules.codegen.NativeModuleParcel
        public final void setFromJSONObject(JSONObject json) {
            String str = null;
            this.auto_size_image = json.isNull("auto_size_image") ? null : QPImage.makeFromJSONObject(json.optJSONObject("auto_size_image"));
            this.content_text = json.isNull("content_text") ? null : json.optString("content_text");
            this.dismiss_action = json.isNull("dismiss_action") ? null : QPAction.makeFromJSONObject(json.optJSONObject("dismiss_action"));
            this.primary_action = json.isNull("primary_action") ? null : QPAction.makeFromJSONObject(json.optJSONObject("primary_action"));
            this.secondary_action = json.isNull("secondary_action") ? null : QPAction.makeFromJSONObject(json.optJSONObject("secondary_action"));
            if (!json.isNull("title_text")) {
                str = json.optString("title_text");
            }
            this.title_text = str;
        }

        public static final QPCreative makeFromJSONObject(JSONObject json) {
            if (json == null) {
                return null;
            }
            QPCreative result = new QPCreative();
            result.setFromJSONObject(json);
            return result;
        }
    }

    public static class QPImage extends NativeModuleParcel {
        public String dimensionless_cache_key;
        public double height;
        public boolean is_silhouette;
        public String mime_type;
        public String name;
        public double scale;
        public String uri;
        public double width;

        @Override // com.oculus.modules.codegen.NativeModuleParcel
        public final JSONObject convertToJSONObject() {
            JSONObject parcel = new JSONObject();
            try {
                parcel.put("dimensionless_cache_key", this.dimensionless_cache_key);
                parcel.put("height", this.height);
                parcel.put("is_silhouette", this.is_silhouette);
                parcel.put("mime_type", this.mime_type);
                parcel.put("name", this.name);
                parcel.put("scale", this.scale);
                parcel.put("uri", this.uri);
                parcel.put("width", this.width);
            } catch (JSONException e) {
            }
            return parcel;
        }

        @Override // com.oculus.modules.codegen.NativeModuleParcel
        public final void setFromJSONObject(JSONObject json) {
            this.dimensionless_cache_key = json.optString("dimensionless_cache_key");
            this.height = json.optDouble("height", 0.0d);
            this.is_silhouette = json.optBoolean("is_silhouette");
            this.mime_type = json.optString("mime_type");
            this.name = json.optString("name");
            this.scale = json.optDouble("scale", 0.0d);
            this.uri = json.optString("uri");
            this.width = json.optDouble("width", 0.0d);
        }

        public static final QPImage makeFromJSONObject(JSONObject json) {
            if (json == null) {
                return null;
            }
            QPImage result = new QPImage();
            result.setFromJSONObject(json);
            return result;
        }
    }

    public static class QPAction extends NativeModuleParcel {
        public boolean dismiss_promotion;
        public double limit;
        public String title_text;
        public String url;

        @Override // com.oculus.modules.codegen.NativeModuleParcel
        public final JSONObject convertToJSONObject() {
            JSONObject parcel = new JSONObject();
            try {
                parcel.put("dismiss_promotion", this.dismiss_promotion);
                parcel.put("limit", this.limit);
                if (this.title_text != null) {
                    parcel.put("title_text", this.title_text);
                }
                if (this.url != null) {
                    parcel.put("url", this.url);
                }
            } catch (JSONException e) {
            }
            return parcel;
        }

        @Override // com.oculus.modules.codegen.NativeModuleParcel
        public final void setFromJSONObject(JSONObject json) {
            String str = null;
            this.dismiss_promotion = json.optBoolean("dismiss_promotion");
            this.limit = json.optDouble("limit", 0.0d);
            this.title_text = json.isNull("title_text") ? null : json.optString("title_text");
            if (!json.isNull("url")) {
                str = json.optString("url");
            }
            this.url = str;
        }

        public static final QPAction makeFromJSONObject(JSONObject json) {
            if (json == null) {
                return null;
            }
            QPAction result = new QPAction();
            result.setFromJSONObject(json);
            return result;
        }
    }

    public static class QPTemplate extends NativeModuleParcel {
        public String name;
        public List<QPParameter> parameters;

        @Override // com.oculus.modules.codegen.NativeModuleParcel
        public final JSONObject convertToJSONObject() {
            JSONObject parcel = new JSONObject();
            try {
                parcel.put("name", this.name);
                parcel.put("parameters", NativeModuleParcel.convertParcelListToJSONArray(this.parameters));
            } catch (JSONException e) {
            }
            return parcel;
        }

        @Override // com.oculus.modules.codegen.NativeModuleParcel
        public final void setFromJSONObject(JSONObject json) {
            this.name = json.optString("name");
            this.parameters = NativeModuleParcel.convertJSONArrayToParcelList(json.optJSONArray("parameters"), QPParameter.class);
        }

        public static final QPTemplate makeFromJSONObject(JSONObject json) {
            if (json == null) {
                return null;
            }
            QPTemplate result = new QPTemplate();
            result.setFromJSONObject(json);
            return result;
        }
    }
}
