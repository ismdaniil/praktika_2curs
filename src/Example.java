import javax.swing.*;

//Класс для хранения служебных переменных
public class Example {
    public static  int N = 20; //Размерность поля
    public static  int Tact = 50; //Время такта в милисекундах
    public static  int LifePower = 5 * N; //Жизненная сила пчелы в тактах
    public static  int MaxLifePower = 10 * N; //Максимальная жизненная сила
    public static  int MinLifePower = N/2; //Минимальная жизненная сила

    //Переменные которые нужны глобальными
    public static boolean flashok = false;
    public static JFrame frame;
    public static JTextField textfield1;
    public static JTextField textfield2;
    public static JTextField textfield3;

    //Метод считает количество пчёл на поле
    public static int KolPchel(){

        //Указатель для доступа к полю, ПОЛЕ это SINGLETON
        Pole mypole = Pole.getInstance();
        int kolpchel = 0;

        //Цикл проверки сколько пчёл на поле
        for(int i = 0; i < Example.N; i++)
            for (int j = 0; j < Example.N; j++)
                if (mypole.pol[i][j].nas != null)
                    kolpchel++;
        return kolpchel;
    }

    //Метод считает количество нектара на поле
    public static int KolNektara(){

        //Указатель для доступа к полю, ПОЛЕ это SINGLETON
        Pole mypole = Pole.getInstance();
        int kolnektara = 0;

        //Цикл проверки сколько еды на поле
        for(int i = 0; i < Example.N; i++)
            for (int j = 0; j < Example.N; j++)
                if (mypole.pol[i][j].ed != null)
                    kolnektara++;
        return kolnektara;
    }

    //Метод считает количество маток на поле
    public static int KolMatok() {

        //Указатель для доступа к полю, ПОЛЕ это SINGLETON
        Pole mypole = Pole.getInstance();
        int kolmatok = 0;

        //Цикл проверки сколько пчёл на поле
        for (int i = 0; i < Example.N; i++)
            for (int j = 0; j < Example.N; j++)
                if (mypole.pol[i][j].nas != null)
                    if (mypole.pol[i][j].nas instanceof Matka)
                        kolmatok++;
        return kolmatok;
    }

    //Перечисление содержит варианты направления
    public static enum Napravlenie{
        STAY,
        RIGHT,
        BOTTOM,
        LEFT,
        TOP
    }
}
