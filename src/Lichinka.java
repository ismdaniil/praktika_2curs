import java.awt.*;

public class Lichinka extends Muravei{
    Color trudovoysacolor = new Color(255,255,255); //Цвет яйца
    public Lichinka(int koordinX, int koordinY){
        super(koordinX,koordinY);
        LifePower = Example.MaxLifePower;
    }
    @Override
    public void risovanie(Graphics g, int shirinakletki, int visotakletki,int otstupX, int otstupY){

        int X = kletk.getKoordinataX()*shirinakletki + otstupX;
        int Y = kletk.getKoordinataY()*visotakletki + otstupY;
        g.setColor(trudovoysacolor);
        g.fillRect(X, Y, shirinakletki, visotakletki);
    }

    @Override
    public void smenasostoania(){
        //Указатель для доступа к полю, ПОЛЕ это SINGLETON
        Pole mypole = Pole.getInstance();

        if(LifePower <= Example.MaxLifePower) {
            napravlenie = Example.Napravlenie.STAY;
            LifePower--;
            if(LifePower < 1){
                //Яйцо выбыло по старой координате
                mypole.pol[kletk.koordinataX][kletk.koordinataY].setNasekomoe(null);
                mypole.pol[kletk.koordinataX][kletk.koordinataY].flagpererisovki = true;
                //Появлися новый муравей по старой координате
                mypole.pol[kletk.koordinataX][kletk.koordinataY].nas = new Muravei(kletk.koordinataX, kletk.koordinataY);
                mypole.pol[kletk.koordinataX][kletk.koordinataY].flagpererisovki = true;
            }
        }
    };
}
