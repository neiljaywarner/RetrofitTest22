package org.disciplestoday.retrofittest22;

import android.util.Log;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by neil on 5/25/16.
 */
public class Item {

    String title;
    private String introtext;
    //NOTE: Must match json. TODO: use annotation so it doesn't have to.
    @SerializedName("extra_fields")
    //TODO: WHY doens't this work???
    private List<ExtraField> extra_fields;



    public List<ExtraField> getExtraFields() {
        Log.e("NJW2", "getExtrafields");
        Log.e("NJW2", "size=" + extra_fields.size());
        return extra_fields;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIntroText() {
        Log.e("NJW2", "ingetintrotext");
        return introtext;
    }

    public void setIntroText(String introText) {
        Log.e("NJW2", "inSETintrotext");

        this.introtext = introtext;
    }

    public void setExtraFields(List<ExtraField> extraFields) {
        Log.e("NJW2", "in settetrafields");
        this.extra_fields = extraFields;
    }


    public String getImage()
    {
        Log.e("NJW3", "title=" + title + ";in Item.java->introText=" + introtext);
        int start = introtext.indexOf("images");
        if (start < 0) {
            return "";
        }
        Log.e("NJW3", "start=" + start);
        int end = introtext.indexOf(".jpg");
        if (end < 0)
        {
            return "";
        }

        Log.e("NJW3", "end=" + end);

        String s = introtext.substring(start, end);
        String image = "http://disciplestoday.org/" + s + ".jpg";
        Log.e("NJW3", "image=" + image);
        return image;
    }

}
