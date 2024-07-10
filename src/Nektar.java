import java.awt.*;

public class Nektar {
    kletka kletk;
    Color nektarcolor = new Color(255,192,203);  //Цвет нектара // розовый
    Color nektarcolor2 = new Color(128,0,128); //Цвет поедаемого нектара // фиолетовый
    Pchela poedanie; //Указатель на пчелу, которая пьёт нектар

    public Nektar(int koorX, int koorY){
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
