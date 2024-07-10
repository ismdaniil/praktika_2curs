import java.awt.*;

public class Nasekomoe {
    kletka kletk;
    Example.Napravlenie napravlenie; //Переменная содержит направление движения на поле
    boolean tronuto; //Переменная содержит параметр двигалось ли насекомое в этот ход или нет
    public Nasekomoe(int koorX, int koorY){
        kletk = new kletka();
        kletk.setKoordinataX(koorX);
        kletk.setKoordinataY(koorY);
        napravlenie = Example.Napravlenie.STAY;
        tronuto = false;
    }
    public void smenasostoania(){
        //Переопределён в наследнике
    }
    public void risovanie(Graphics g, int shirinakletki, int visotakletki,int otstupX, int otstupY){
        //Переопределён в наследнике
    }
}