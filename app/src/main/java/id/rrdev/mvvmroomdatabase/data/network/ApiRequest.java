package id.rrdev.mvvmroomdatabase.data.network;

import java.util.List;

import id.rrdev.mvvmroomdatabase.data.network.response.RoomResponse;
import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiRequest {

    @GET("TableRooms/GetAllRooms.php")
    Call<RoomResponse> getAllRoom();
}
