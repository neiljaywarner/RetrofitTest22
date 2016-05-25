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

    public String getFulltext() {
        return fulltext;
    }

    public void setFulltext(String fulltext) {
        this.fulltext = fulltext;
    }

    private String fulltext;

    //NOTE: Must match json. TODO: use annotation so it doesn't have to.
    @SerializedName("extra_fields")
    private List<ExtraField> extra_fields;

    public List<ExtraField> getExtraFields() {
        Log.e("NJW2", "extra fields size=" + extra_fields.size());
        return extra_fields;
    }
    //TODO: Use extra fields when needed.

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIntroText() {
        return introtext;
    }

    public void setIntroText(String introText) {
        this.introtext = introtext;
    }

    public void setExtraFields(List<ExtraField> extraFields) {
        this.extra_fields = extraFields;
    }

    public String getImage()
    {
        String imageUrl;
        imageUrl = getImageUrl(introtext, "jpg");
        if (!imageUrl.isEmpty())
        {
            return imageUrl;
        }

        imageUrl = getImageUrl(introtext, "png");
        if (!imageUrl.isEmpty())
        {
            return imageUrl;
        }

        imageUrl = getImageUrl(introtext, "gif");
        if (!imageUrl.isEmpty())
        {
            return imageUrl;
        }

        imageUrl = getImageUrl(fulltext, "jpg");
        if (!imageUrl.isEmpty())
        {
            return imageUrl;
        }

        imageUrl = getImageUrl(fulltext, "png");
        if (!imageUrl.isEmpty())
        {
            return imageUrl;
        }

        imageUrl = getImageUrl(fulltext, "gif");
        if (!imageUrl.isEmpty())
        {
            return imageUrl;
        }



        return "";

    }

    //TODO: Cleanup code!!!

    private String getImageUrl(String fieldToSearch, String fileSuffix)
    {
        int start = fieldToSearch.indexOf("images");
        if (start < 0) {
            return "";
        }
        int end = fieldToSearch.indexOf("." + fileSuffix);
        if (end < 0)
        {
            return "";
        }

        String s = fieldToSearch.substring(start, end);
        String image = "http://disciplestoday.org/" + s + "." + fileSuffix;
        return image;
    }

}
