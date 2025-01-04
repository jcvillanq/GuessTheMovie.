import java.io.*;
import java.util.*;

class Ranking {
    private static final int MAX_RANKING = 5;
    private ArrayList<RegistroRanking> ranking;

    public Ranking() {
        cargarRanking();
    }

    private void cargarRanking() {
        try (ObjectInputStream ois = new ObjectInputStream(
                new FileInputStream("ranking.dat"))) {
            ranking = (ArrayList<RegistroRanking>) ois.readObject();
        } catch (Exception e) {
            ranking = new ArrayList<>();
        }
    }

    public boolean esElegibleParaRanking(int puntuacion) {
        return ranking.size() < MAX_RANKING ||
                puntuacion > ranking.get(ranking.size() - 1).getPuntuacion();
    }

    public boolean esNicknameValido(String nickname) {
        if (nickname == null || nickname.trim().isEmpty()) {
            return false;
        }
        return ranking.stream().noneMatch(r -> r.getNickname().equalsIgnoreCase(nickname));
    }

    public void agregarPuntuacion(Jugador jugador) {
        ranking.add(new RegistroRanking(
                jugador.getNickname(),
                jugador.getPuntuacion(),
                new Date()
        ));

        Collections.sort(ranking, (a, b) -> b.getPuntuacion() - a.getPuntuacion());

        if (ranking.size() > MAX_RANKING) {
            ranking = new ArrayList<>(ranking.subList(0, MAX_RANKING));
        }

        guardarRanking();
    }

    private void guardarRanking() {
        try (ObjectOutputStream oos = new ObjectOutputStream(
                new FileOutputStream("ranking.dat"))) {
            oos.writeObject(ranking);
        } catch (IOException e) {
            System.out.println("Error al guardar el ranking.");
        }
    }

    public void mostrarRanking() {
        System.out.println("\n=== RANKING TOP 5 ===");
        for (int i = 0; i < ranking.size(); i++) {
            RegistroRanking registro = ranking.get(i);
            System.out.printf("%d. %s - %d puntos%n",
                    i + 1,
                    registro.getNickname(),
                    registro.getPuntuacion()
            );
        }
    }
}