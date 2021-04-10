package com.oculus.panelapp.assistant.dialogs.components;

import android.content.Context;
import com.oculus.assistant.service.api.panel.AssistantComponentContract;
import com.oculus.panelapp.assistant.dialogs.ISurface;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import org.json.JSONException;
import org.json.JSONObject;

public class ComponentFactory {
    private static HashMap<String, Class<? extends BaseComponent>> sComponenets = new HashMap<>();

    static {
        sComponenets.put("text", TextComponent.class);
        sComponenets.put(AssistantComponentContract.Components.ListItemComponent.TYPE, ListItemComponent.class);
        sComponenets.put("feedback", FeedbackComponent.class);
        sComponenets.put("image", RemoteImageViewComponent.class);
        sComponenets.put(AssistantComponentContract.Components.ButtonComponent.TYPE, ButtonComponent.class);
        sComponenets.put(AssistantComponentContract.Components.LinearLayoutComponent.TYPE, LinearLayoutComponent.class);
        sComponenets.put(AssistantComponentContract.Components.FrameLayoutComponent.TYPE, FrameLayoutComponent.class);
        sComponenets.put(AssistantComponentContract.Components.CheckboxComponent.TYPE, CheckboxComponent.class);
        sComponenets.put(AssistantComponentContract.Components.ToggleComponent.TYPE, ToggleComponent.class);
        sComponenets.put(AssistantComponentContract.Components.InformationBoxComponent.TYPE, InformationBoxComponent.class);
    }

    public static BaseComponent create(ISurface iSurface, JSONObject jSONObject, Context context) throws JSONException {
        String string = jSONObject.getString("type");
        JSONObject jSONObject2 = jSONObject.getJSONObject("data");
        try {
            if (sComponenets.containsKey(string)) {
                Constructor<? extends BaseComponent> constructor = sComponenets.get(string).getConstructor(Context.class);
                if (constructor != null) {
                    BaseComponent baseComponent = (BaseComponent) constructor.newInstance(context);
                    baseComponent.applyJson(iSurface, jSONObject2);
                    return baseComponent;
                }
                throw new JSONException("Could not find constructor for type: " + string);
            }
            throw new JSONException("Unknown control type requested: " + string);
        } catch (IllegalAccessException e) {
            throw new JSONException("IllegalAccessException inflating " + string + ":" + e.getMessage());
        } catch (InstantiationException e2) {
            throw new JSONException("InstantiationException inflating " + string + ": " + e2.getMessage());
        } catch (NoSuchMethodException e3) {
            throw new JSONException("NoSuchMethodException inflating " + string + ": " + e3.getMessage());
        } catch (InvocationTargetException e4) {
            throw new JSONException("InvocationTargetException inflating " + string + ": " + e4.getMessage());
        }
    }
}
