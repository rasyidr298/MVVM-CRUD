package id.rrdev.mvvmroomdatabase.data.network;

import id.rrdev.mvvmroomdatabase.data.network.response.AddRoomResponse;
import id.rrdev.mvvmroomdatabase.data.network.response.GetRoomResponse;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiRequest {

    @GET("TableRooms/GetAllRooms.php")
    Call<GetRoomResponse> getAllRoom();

    @GET("TableRooms/DeleteRoom.php")
    Call<ResponseBody> deleteRoom(
            @Query("idRoom") int idRoom);

    @FormUrlEncoded
    @POST("TableRooms/CreateRoom.php")
    Call<AddRoomResponse> addRoom(
            @Field("namaRoom") String namaRoom,
            @Field("kapasitas") int kapasitas,
            @Field("fasilitas1") String fasilitas1,
            @Field("fasilitas2") String fasilitas2,
            @Field("fasilitas3") String fasilitas3,
            @Field("fasilitas4") String fasilitas4,
            @Field("deskripsi") String deskripsi
    );

    @FormUrlEncoded
    @POST("TableRooms/UpdateRoom.php")
    Call<ResponseBody> updateRoom(
            @Query("idRoom") int idRoom,
            @Field("idRoom") int idRoomParams,
            @Field("kapasitas") int kapasitas,
            @Field("fasilitas1") String fasilitas1,
            @Field("fasilitas2") String fasilitas2,
            @Field("fasilitas3") String fasilitas3,
            @Field("fasilitas4") String fasilitas4,
            @Field("deskripsi") String deskripsi
    );
}
