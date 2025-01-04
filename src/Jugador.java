import java.io.Serializable;

class Jugador implements Serializable {
    private String nickname;
    private int puntuacion;

    public Jugador() {
        this.puntuacion = 0;
    }

    public void agregarPuntos(int puntos) {
        this.puntuacion += puntos;
    }

    public int getPuntuacion() {
        return puntuacion;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getNickname() {
        return nickname;
    }
}