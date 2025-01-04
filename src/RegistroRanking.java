import java.io.Serializable;
import java.util.Date;

class RegistroRanking implements Serializable {
    private String nickname;
    private int puntuacion;
    private Date fecha;

    public RegistroRanking(String nickname, int puntuacion, Date fecha) {
        this.nickname = nickname;
        this.puntuacion = puntuacion;
        this.fecha = fecha;
    }

    public String getNickname() {
        return nickname;
    }

    public int getPuntuacion() {
        return puntuacion;
    }

    public Date getFecha() {
        return fecha;
    }
}