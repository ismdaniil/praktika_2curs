public class Pole {
    private static Pole instance;
    kletka [][] pol = new kletka[Example.N][Example.N];
    private Pole(){
        for (int i = 0; i < Example.N; i++) {
            for (int j = 0; j < Example.N; j++) {
                pol[i][j] = new kletka();
            }
        }
        chistka();
    }
    public static Pole getInstance(){
        if (instance == null){
            instance = new Pole();
        }
        return instance;
    }
    public void chistka() {
        for (int i = 0; i < Example.N; i++) {
            for (int j = 0; j < Example.N; j++) {
                pol[i][j].nas = null; //Освобождать память под объект не нужно, это сделает сборщик мусора
                pol[i][j].ed = null;  //Освобождать память под объект не нужно, это сделает сборщик мусора
                pol[i][j].setKoordinataX(i);
                pol[i][j].setKoordinataY(j);
            }
        }
    }
}

