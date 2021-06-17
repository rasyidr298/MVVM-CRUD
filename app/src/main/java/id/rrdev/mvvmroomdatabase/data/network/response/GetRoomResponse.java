package id.rrdev.mvvmroomdatabase.data.network.response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetRoomResponse {
    @SerializedName("error")
    private boolean error;

    @SerializedName("room")
    private List<Room> room;


    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public List<Room> getRoom() {
        return room;
    }

    public void setRoom(List<Room> room) {
        this.room = room;
    }
}
