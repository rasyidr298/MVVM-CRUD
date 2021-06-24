package id.rrdev.mvvmroomdatabase.data.repository;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import id.rrdev.mvvmroomdatabase.data.network.ApiRequest;
import id.rrdev.mvvmroomdatabase.data.network.RetrofitRequest;
import id.rrdev.mvvmroomdatabase.data.network.response.AddRoomResponse;
import id.rrdev.mvvmroomdatabase.data.network.response.GetRoomResponse;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RoomRespository {

    private static final String TAG = RoomRespository.class.getSimpleName();
    private ApiRequest apiRequest;

    public RoomRespository() {
        apiRequest = RetrofitRequest.getRetrofitInstance().create(ApiRequest.class);
    }

    //get all
    public LiveData<GetRoomResponse> getAllRoom() {
        final MutableLiveData<GetRoomResponse> data = new MutableLiveData<>();
        apiRequest.getAllRoom()
                .enqueue(new Callback<GetRoomResponse>() {

                    @Override
                    public void onResponse(Call<GetRoomResponse> call, Response<GetRoomResponse> response) {
                        Log.d(TAG, "onResponse response " + response);

                        if (response.body() != null){
                            data.setValue(response.body());

                            Log.d(TAG, "room size " + response.body().getRoom().size());
                            Log.d(TAG, "room detail " + response.body().getRoom());
                        }
                    }

                    @Override
                    public void onFailure(Call<GetRoomResponse> call, Throwable t) {
                        data.setValue(null);
                    }

                });
        return data;
    }

    //delete
    public void deleteRoom(Integer idRoom){
        apiRequest.deleteRoom(idRoom)
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        Log.d(TAG, "onResponse response : " + response);

                        if (response.body() != null){
                            Log.d(TAG, "room delete : "+ response.body());
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Log.d(TAG, "room delete : "+t.getMessage());
                    }
                });
    }

    //add
    public LiveData<AddRoomResponse> addRoom(String namaRoom, int kapasitas, String fasilitas1, String fasilitas2, String fasilitas3, String fasilitas4, String deskripsi ) {

        final MutableLiveData<AddRoomResponse> data = new MutableLiveData<>();

        apiRequest.addRoom(namaRoom, kapasitas, fasilitas1, fasilitas2, fasilitas3, fasilitas4, deskripsi)
                .enqueue(new Callback<AddRoomResponse>() {
                    @Override
                    public void onResponse(Call<AddRoomResponse> call, Response<AddRoomResponse> response) {
                        Log.d(TAG, "onResponse response : " + response);

                        if (response.body() != null){
                            data.setValue(response.body());

                            Log.d(TAG, "room add : "+ response.body().message);
                        }
                    }

                    @Override
                    public void onFailure(Call<AddRoomResponse> call, Throwable t) {
                        data.setValue(null);
                    }
                });

        return data;

    }

    //update
    public void updateRoom ( int idRoom, int idRoomParams, int kapsitas, String fasilitas1, String fasilitas2, String fasilitas3, String fasilitas4,String deskripsi){

        apiRequest.updateRoom(idRoom, idRoomParams, kapsitas, fasilitas1, fasilitas2, fasilitas3, fasilitas4, deskripsi)
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        Log.d(TAG, "onResponse response : " + response);

                        if (response.body() != null){
                            Log.d(TAG, "room update : "+ response.body());
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Log.d(TAG, "room update : "+t.getMessage());
                    }
                });
    }
}
