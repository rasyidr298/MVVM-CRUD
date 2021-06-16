package id.rrdev.mvvmroomdatabase.data.network.response;

public class Room {
    private Integer idRoom;
    private String namaRoom;
    private Integer kapasitas;
    private String fasilitas1;
    private String fasilitas2;
    private String fasilitas3;
    private String fasilitas4;
    private String deskripsi;
    private String image;

    public int getIdRoom() {
        return idRoom;
    }

    public void setIdRoom(int idRoom) {
        this.idRoom = idRoom;
    }

    public String getNamaRoom() {
        return namaRoom;
    }

    public void setNamaRoom(String namaRoom) {
        this.namaRoom = namaRoom;
    }

    public int getKapasitas() {
        return kapasitas;
    }

    public void setKapasitas(int kapasitas) {
        this.kapasitas = kapasitas;
    }

    public String getFasilitas1() {
        return fasilitas1;
    }

    public void setFasilitas1(String fasilitas1) {
        this.fasilitas1 = fasilitas1;
    }

    public String getFasilitas2() {
        return fasilitas2;
    }

    public void setFasilitas2(String fasilitas2) {
        this.fasilitas2 = fasilitas2;
    }

    public String getFasilitas3() {
        return fasilitas3;
    }

    public void setFasilitas3(String fasilitas3) {
        this.fasilitas3 = fasilitas3;
    }

    public String getFasilitas4() {
        return fasilitas4;
    }

    public void setFasilitas4(String fasilitas4) {
        this.fasilitas4 = fasilitas4;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
