package nyc.c4q.android_final_dogsapp.Networking;

import nyc.c4q.android_final_dogsapp.model.Dogs;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by bobbybah on 2/25/18.
 */

public interface Dog_Caller {

    String BASE_URL = "https://dog.ceo/dog-api/";

    @GET("{breed}/images/random")
    Call<Dogs> getDogImage(@Path("breed") String breed);

    @GET("{breed}/images")
    Call<Dogs.breedList> getList(@Path("breed") String breed);
}
