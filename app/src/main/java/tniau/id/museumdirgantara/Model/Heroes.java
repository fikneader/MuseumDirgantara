package tniau.id.museumdirgantara.Model;

public class Heroes {
    String id,nama_pahlawan, deskripsi_pahlawan, gambar_pahlawan;

    public Heroes(String id, String nama_pahlawan, String deskripsi_pahlawan, String gambar_pahlawan){
        this.id = id;
        this.nama_pahlawan = nama_pahlawan;
        this.deskripsi_pahlawan = deskripsi_pahlawan;
        this.gambar_pahlawan = gambar_pahlawan;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setNama_pahlawan(String nama_pahlawan) {
        this.nama_pahlawan = nama_pahlawan;
    }

    public String getNama_pahlawan() {
        return nama_pahlawan;
    }

    public void setDeskripsi_pahlawan(String deskripsi_pahlawan) {
        this.deskripsi_pahlawan = deskripsi_pahlawan;
    }

    public String getDeskripsi_pahlawan() {
        return deskripsi_pahlawan;
    }

    public void setGambar_pahlawan(String gambar_pahlawan) {
        this.gambar_pahlawan = gambar_pahlawan;
    }

    public String getGambar_pahlawan() {
        return gambar_pahlawan;
    }
}
