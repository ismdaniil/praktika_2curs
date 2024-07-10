public class koordinata {
    int koordinataX;
    int koordinataY;

    public koordinata() {
        koordinataX = -1;
        koordinataY = -1;
    }

    public void setKoordinataX(int koordinataX) {
        // Проверка на корректность переданного значения координат. Оно должно быть от 0 до N.
        // TODO: Реализовать проверку!!!
        this.koordinataX = koordinataX;
    }

    public void setKoordinataY(int koordinataY) {
        // TODO: Реализовать проверку!!!
        this.koordinataY = koordinataY;
    }

    public int getKoordinataX() {
        return koordinataX;
    }

    public int getKoordinataY() {
        return koordinataY;
    }
}