package id.rrdev.mvvmroomdatabase.view.webService;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import id.rrdev.mvvmroomdatabase.data.network.response.RoomResponse;
import id.rrdev.mvvmroomdatabase.data.repository.RoomRespository;

public class WebServiceViewModel extends AndroidViewModel {

    private RoomRespository roomRespository;
    private LiveData<RoomResponse> getRoomLiveData;

    public WebServiceViewModel(@NonNull Application application) {
        super(application);

        roomRespository = new RoomRespository();
        this.getRoomLiveData = roomRespository.getllRoom();
    }

    public LiveData<RoomResponse> getRoomLiveData() {
        return getRoomLiveData;
    }

    public void deleteRoom(Integer idRoom){
        roomRespository.deleteRoom(idRoom);
    }

}
