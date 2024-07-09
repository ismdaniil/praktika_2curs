import java.awt.*;

public class Nektar {
    kletka kletk;
    Color nektarcolor = new Color(255,191,0);  //Цвет нектара
    Color nektarcolor2 = new Color(255,255,0); //Цвет поедаемого нектара
    Muravei poedanie; //Указатель на пчелу, которая питается нектаром

    public Eda(int koorX, int koorY){
        kletk = new kletka();
        kletk.setKoordinataX(koorX);
        kletk.setKoordinataY(koorY);
        poedanie = null;
    }
    public void smenasostoania(){

    }
    public void risovanie(Graphics g, int shirinakletki, int visotakletki, int otstupX, int otstupY){

        int X = kletk.getKoordinataX()*shirinakletki + otstupX;
        int Y = kletk.getKoordinataY()*visotakletki + otstupY;
        if(poedanie != null) {
            g.setColor(nektarcolor2);
        }
        else {
            g.setColor(nektarcolor);
        }
        g.fillRect(X,Y,shirinakletki,visotakletki);

    }
}