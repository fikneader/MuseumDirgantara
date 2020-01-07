package tniau.id.museumdirgantara.Model;

public class ListRoom {

    String id,nama_ruangan,deskripsi_ruangan,gambar_ruangan,id_ruangan;

    public ListRoom(String id, String nama_ruangan, String deskripsi_ruangan, String gambar_ruangan,String id_ruangan){
        this.id = id;
        this.id_ruangan = id_ruangan;
        this.nama_ruangan = nama_ruangan;
        this.gambar_ruangan = gambar_ruangan;
        this.deskripsi_ruangan = deskripsi_ruangan;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId_ruangan(String id_ruangan) {
        this.id_ruangan = id_ruangan;
    }

    public String getId_ruangan() {
        return id_ruangan;
    }

    public void setNama_ruangan(String nama_ruangan) {
        this.nama_ruangan = nama_ruangan;
    }

    public String getNama_ruangan() {
        return nama_ruangan;
    }

    public String getDeskripsi_ruangan() {
        return deskripsi_ruangan;
    }

    public void setDeskripsi_ruangan(String deskripsi_ruangan) {
        this.deskripsi_ruangan = deskripsi_ruangan;
    }

    public String getGambar_ruangan() {
        return gambar_ruangan;
    }

    public void setGambar_ruangan(String gambar_ruangan) {
        this.gambar_ruangan = gambar_ruangan;
    }
}
