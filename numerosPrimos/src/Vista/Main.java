package Vista;

import Controlador.Controlador;

public class Main {
    public static void main(String[] args) {
        Controlador c = new Controlador();
        c.presentarse("data.txt");
    }
}
