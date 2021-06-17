package id.rrdev.mvvmroomdatabase.view.webService;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import id.rrdev.mvvmroomdatabase.data.network.response.AddRoomResponse;
import id.rrdev.mvvmroomdatabase.data.network.response.GetRoomResponse;
import id.rrdev.mvvmroomdatabase.data.repository.RoomRespository;

public class WebServiceViewModel extends AndroidViewModel {

    private RoomRespository roomRespository;
    private LiveData<GetRoomResponse> getRoomLiveData;

    public WebServiceViewModel(@NonNull Application application) {
        super(application);

        roomRespository = new RoomRespository();
        this.getRoomLiveData = roomRespository.getllRoom();
    }

    public LiveData<GetRoomResponse> getRoomLiveData() {
        return getRoomLiveData;
    }

    public void deleteRoom(Integer idRoom){
        roomRespository.deleteRoom(idRoom);
    }

    public LiveData<AddRoomResponse> addRoom(String namaRoom, int kapasitas, String fasilitas1, String fasilitas2, String fasilitas3, String fasilitas4, String deskripsi){
        return roomRespository.addRoom(namaRoom, kapasitas, fasilitas1, fasilitas2, fasilitas3, fasilitas4, deskripsi);
    }

}
