package pt.ipp.estg.tourpediaexample;

import java.util.List;

import pt.ipp.estg.tourpediaexample.Models.POI;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 *
 * @author Pedro Machado pedroma2000
 */
public interface TourDataApi {

    @GET("getPlaces")
    Call<List<POI>> getListPointsOfInterest(@Query("location") String location,
                                            @Query("name") String name);
}
