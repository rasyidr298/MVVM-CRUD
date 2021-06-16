package id.rrdev.mvvmroomdatabase.data.repository;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import id.rrdev.mvvmroomdatabase.data.network.ApiRequest;
import id.rrdev.mvvmroomdatabase.data.network.RetrofitRequest;
import id.rrdev.mvvmroomdatabase.data.network.response.RoomResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RoomRespository {

    private static final String TAG = RoomRespository.class.getSimpleName();
    private ApiRequest apiRequest;

    public RoomRespository() {
        apiRequest = RetrofitRequest.getRetrofitInstance().create(ApiRequest.class);
    }

    public LiveData<RoomResponse> getllRoom() {
        final MutableLiveData<RoomResponse> data = new MutableLiveData<>();
        apiRequest.getAllRoom()
                .enqueue(new Callback<RoomResponse>() {

                    @Override
                    public void onResponse(Call<RoomResponse> call, Response<RoomResponse> response) {
                        Log.d(TAG, "onResponse response " + response);

                        if (response.body() != null){
                            data.setValue(response.body());

                            Log.d(TAG, "room size:: " + response.body().getRoom().size());
                            Log.d(TAG, "room detail:: " + response.body().getRoom());
                        }
                    }

                    @Override
                    public void onFailure(Call<RoomResponse> call, Throwable t) {
                        data.setValue(null);
                    }

                });
        return data;
    }
}
