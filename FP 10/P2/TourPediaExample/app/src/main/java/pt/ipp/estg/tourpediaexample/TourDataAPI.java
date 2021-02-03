package pt.ipp.estg.tourpediaexample;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 *
 * @author Pedro Machado pedroma2000
 */
public interface TourDataAPI {

    @GET("getPlaces")
    Call<List<Place>> getListOfPlaces(@Query("location") String location, @Query("category") String Category, @Query("name") String name);

}
