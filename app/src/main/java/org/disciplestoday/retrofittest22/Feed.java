package org.disciplestoday.retrofittest22;

import java.util.List;

/**
 * Created by neil on 5/25/16.
 */

public class Feed {

    public Site getSite() {
        return site;
    }

    private Site site;

    private List<Item> items;

    public List<Item> getItems() {
        return items;
    }

}
