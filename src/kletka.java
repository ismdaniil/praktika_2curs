
public class kletka extends Koordinata {
    Nasekomoe nas; //Указатель на насекомое, поле будет вызывать его методы напрямую
    Nektar ed;        //Указатель на нектар, поле будет вызывать его методы напрямую
    boolean flagpererisovki; //Флажок указывающий надо перерисовывать поле или нет
    public kletka() {
        super();
        nas = null;
        ed = null;
        flagpererisovki = true;
    }

    public void setNasekomoe(Nasekomoe nas) {
        this.nas = nas;
    }
    public Nasekomoe getNasekomoe() {
        return nas;
    }
    public void setNektar(Nektar ed) {
        this.ed = ed;
    }
    public Nektar getNektar() {
        return ed;
    }
}

