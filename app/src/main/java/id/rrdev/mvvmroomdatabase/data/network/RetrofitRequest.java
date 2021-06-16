package id.rrdev.mvvmroomdatabase.data.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitRequest {

    static Retrofit retrofit;
//    private static final String URL = "https://asdfasfasdfasdf.000webhostapp.com/RoomBooking/";
    private static final String URL = "https://5e18fbf34c94.ngrok.io/RoomBooking/";

    public static Retrofit getRetrofitInstance() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
