package org.disciplestoday.retrofittest22;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by neil on 5/25/16.
 */
public interface DTService {
    @GET("component/k2/itemlist?format=json&moduleID=353")
    Call<Feed> listHighlights();


    // http://www.disciplestoday.org/component/k2/itemlist?format=json&moduleID=353
}
