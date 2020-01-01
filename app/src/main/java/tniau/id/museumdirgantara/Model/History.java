package tniau.id.museumdirgantara.Model;

public class History {

    String id,judul_sejarah,deskripsi_sejarah,gambar_sejarah;

    public History(String id, String judul_sejarah, String deskripsi_sejarah, String gambar_sejarah){
        this.id = id;
        this.judul_sejarah = judul_sejarah;
        this.deskripsi_sejarah = deskripsi_sejarah;
        this.gambar_sejarah = gambar_sejarah;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setJudul_sejarah(String judul_sejarah) {
        this.judul_sejarah = judul_sejarah;
    }

    public String getJudul_sejarah() {
        return judul_sejarah;
    }

    public void setDeskripsi_sejarah(String deskripsi_sejarah) {
        this.deskripsi_sejarah = deskripsi_sejarah;
    }

    public String getDeskripsi_sejarah() {
        return deskripsi_sejarah;
    }

    public void setGambar_sejarah(String gambar_sejarah) {
        this.gambar_sejarah = gambar_sejarah;
    }

    public String getGambar_sejarah() {
        return gambar_sejarah;
    }
}
