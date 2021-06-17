package id.rrdev.mvvmroomdatabase.data.network;

import id.rrdev.mvvmroomdatabase.data.network.response.RoomResponse;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiRequest {

    @GET("TableRooms/GetAllRooms.php")
    Call<RoomResponse> getAllRoom();

    @GET("TableRooms/DeleteRoom.php")
    Call<ResponseBody> deleteRoom(
            @Query("idRoom") Integer idRoom
    );
}
