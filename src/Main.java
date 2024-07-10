import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {
    public static void main(String[] args) {
        //Gui отвечает за графическую составляющую
        Gui guiobject = new Gui();
        guiobject.setup();
        //Таймер осуществляет цикл
        TimerListener taimer = new TimerListener();
        Timer timer = new Timer(Example.Tact, taimer);
        timer.start();
    }
}

    //Класс обработчика событий для таймера
    class TimerListener implements ActionListener
    {
        //НАЧАЛО ЭВОЛЮЦИОННОГО ЦИКЛА
        public void actionPerformed(ActionEvent evt)
        {
            //Указатель для доступа к полю, ПОЛЕ это SINGLETON
            Pole mypole = Pole.getInstance();
            //Указатель на насекомых которые расположены в поле
            Nasekomoe myNas;
            int nov;

            //Проверяем установлен ли флажок начать бесконечный цикл
            if(Example.flashok == true) {
                //Проверяем не достигли ли максимального количества пчёл
                if (Example.KolPchel() <= ((Example.N * Example.N) / 2)) {
                    for (int i = 0; i < Example.N; i++) {
                        for (int j = 0; j < Example.N; j++) {
                            if (mypole.pol[i][j].nas != null) {
                                //Сообщение насекомому если его в этот ход не трогали
                                if(mypole.pol[i][j].nas.tronuto == false) {
                                    mypole.pol[i][j].nas.smenasostoania();
                                }
                            }
                        }
                    }
                    for (int i = 0; i < Example.N; i++) {
                        for (int j = 0; j < Example.N; j++) {
                                if (mypole.pol[i][j].nas != null) {
                                    mypole.pol[i][j].nas.tronuto = false;
                                }
                            }
                        }
                    Example.frame.repaint();
                } else {
                    Example.flashok = false;
                }
            }
        }
        //КОНЕЦ ЭВОЛЮЦИОННОГО ЦИКЛА
    };

     class Gui implements ActionListener{
         //СОЗДАЮТСЯ УКАЗАТЕЛИ НА ВИЗУАЛЬНЫЕ КОМПОНЕНТЫ
        JButton buttonPlusNektar;
        JButton buttonPlusPchela;
        JButton buttonPlusMatka;
        JButton buttonNachat;
        JButton buttonStop;

         //СОЗДАЮТСЯ ВИЗУАЛЬНЫЕ КОМПОНЕНТЫ
        public void setup() {
            //Создаём фрейм и панели
            Example.frame = new JFrame();
            MyDrawPanel mypanel = new MyDrawPanel();
            JPanel panel1 = new JPanel();
            JPanel panel2 = new JPanel();
            JPanel panel3 = new JPanel();
            JPanel panel4 = new JPanel();
            JPanel panel5 = new JPanel();

            //Устанавливаем режим компоновки для второй панели и пятой
            panel2.setLayout(new BoxLayout(panel2, BoxLayout.Y_AXIS));
            panel5.setLayout(new BoxLayout(panel5, BoxLayout.Y_AXIS));

            //Создаём кнопки
            buttonPlusNektar = new JButton("+ НЕКТАР");
            buttonPlusPchela = new JButton("+ ПЧЕЛА");
            buttonPlusMatka = new JButton("+ МАТКА");
            buttonNachat = new JButton("СТАРТ");
            buttonStop = new JButton("ПАУЗА");

            //Создаю обработчики событий для кнопок
            buttonPlusNektar.addActionListener(this);
            buttonPlusPchela.addActionListener(this);
            buttonPlusMatka.addActionListener(this);
            buttonNachat.addActionListener(this);
            buttonStop.addActionListener(this);
            buttonStop.setEnabled(false);

            //Создаём текстовые поля
            Example.textfield1 = new JTextField(3);
            Example.textfield2 = new JTextField(3);
            Example.textfield3 = new JTextField(3);
            JLabel text1 = new JLabel();
            JLabel text2 = new JLabel();
            JLabel text3 = new JLabel();
            Example.textfield1.setText(" 0");
            Example.textfield2.setText(" 0");
            Example.textfield3.setText(" 0");
            text1.setText("Кол-во нектара:");
            text2.setText("Кол-во пчёл:");
            text3.setText("Кол-во маток:");

            //Добавляем кнопки на вторую панель
            panel2.add(buttonPlusNektar);
            panel2.add(buttonPlusPchela);
            panel2.add(buttonPlusMatka);
            panel2.add(buttonNachat);
            panel2.add(buttonStop);

            //Добавляем текстовые поля на 3,4 и 1 панель
            panel3.add(text1);
            panel3.add(Example.textfield1);
            panel4.add(text2);
            panel4.add(Example.textfield2);
            panel1.add(text3);
            panel1.add(Example.textfield3);
            panel5.add(panel3);
            panel5.add(panel4);
            panel5.add(panel1);

            //Добавляем панели на фрейм
            Example.frame.getContentPane().add(BorderLayout.WEST, panel2);
            Example.frame.getContentPane().add(BorderLayout.EAST, panel5);
            Example.frame.getContentPane().add(BorderLayout.CENTER, mypanel);

            //Устанавливаем размер фрейма делаем его видимым и завершаем работу программы при закрытии окна
            Example.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            Example.frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
            Example.frame.setVisible(true);
        }

        public void actionPerformed(ActionEvent event){
            //Указатель для доступа к полю, ПОЛЕ это SINGLETON
            Pole mypole = Pole.getInstance();
            //Переменные для генерации координаты
            int sluchkoordinataX;
            int sluchkoordinataY;
            int chetchik = 0;

            if(event.getSource() == buttonNachat){

                Example.flashok = true;
                buttonNachat.setEnabled(false);
                buttonStop.setEnabled(true);
                buttonPlusPchela.setEnabled(false);
                buttonPlusNektar.setEnabled(false);
                buttonPlusMatka.setEnabled(false);
            }
            else
                if(event.getSource() == buttonStop){
                    Example.flashok = false;
                    buttonNachat.setEnabled(true);
                    buttonStop.setEnabled(false);
                    buttonPlusPchela.setEnabled(true);
                    buttonPlusNektar.setEnabled(true);
                    buttonPlusMatka.setEnabled(true);
                }
                    else
                        if(event.getSource() == buttonPlusPchela){
                            //Проверяем не достигли ли максимального количества пчёл
                            if(Example.KolPchel()<=((Example.N * Example.N)/2))
                            {
                                //Нужно сгенерировать случайную координату
                                Random random = new Random();

                                //Цикл генерации случайной координаты
                                chetchik = 0;
                                do {
                                    //Генерирует от 0 до max НЕ включая max, чтобы координата не могла быть 0 прибавим 1
                                    sluchkoordinataX = random.nextInt(Example.N);
                                    sluchkoordinataY = random.nextInt(Example.N);

                                    if((mypole.pol[sluchkoordinataX][sluchkoordinataY].nas == null) && (mypole.pol[sluchkoordinataX][sluchkoordinataY].ed == null))
                                    {
                                        mypole.pol[sluchkoordinataX][sluchkoordinataY].nas = new Pchela(sluchkoordinataX,sluchkoordinataY);
                                        mypole.pol[sluchkoordinataX][sluchkoordinataY].flagpererisovki = true;
                                        break;
                                    }
                                    chetchik++;
                                    if(chetchik > 100) break;
                                } while (true);
                            }
                            Example.frame.repaint();
                        }
                        else
                            if(event.getSource() == buttonPlusNektar) {
                                //Проверяем не достигли ли максимального количества нектара
                                if(Example.KolNektara()<=((Example.N * Example.N)/2))
                                {
                                    //Нужно сгенерировать случайную координату
                                    Random random = new Random();

                                    //Цикл генерации случайной координаты
                                    chetchik = 0;
                                    do {
                                        //Генерирует от 0 до max НЕ включая max, чтобы координата не могла быть 0 прибавим 1
                                        sluchkoordinataX = random.nextInt(Example.N);
                                        sluchkoordinataY = random.nextInt(Example.N);

                                        if((mypole.pol[sluchkoordinataX][sluchkoordinataY].ed == null) && (mypole.pol[sluchkoordinataX][sluchkoordinataY].nas == null))
                                        {
                                            mypole.pol[sluchkoordinataX][sluchkoordinataY].ed = new Nektar(sluchkoordinataX,sluchkoordinataY);
                                            mypole.pol[sluchkoordinataX][sluchkoordinataY].flagpererisovki = true;
                                            break;
                                        }
                                        chetchik++;
                                        if(chetchik > 100) break;

                                    } while (true);
                                }
                                Example.frame.repaint();
                            }
                            else
                                if(event.getSource() == buttonPlusMatka) {
                                    //Проверяем не достигли ли максимального количества пчёл
                                    if(Example.KolPchel()<=((Example.N * Example.N)/2))
                                    {
                                        //Нужно сгенерировать случайную координату
                                        Random random = new Random();

                                        //Цикл генерации случайной координаты
                                        chetchik = 0;
                                        do {
                                            //Генерирует от 0 до max НЕ включая max, чтобы коорлдината не могла быть 0 прибавим 1
                                            sluchkoordinataX = random.nextInt(Example.N);
                                            sluchkoordinataY = random.nextInt(Example.N);

                                            if((mypole.pol[sluchkoordinataX][sluchkoordinataY].nas == null) && (mypole.pol[sluchkoordinataX][sluchkoordinataY].ed == null))
                                            {
                                                mypole.pol[sluchkoordinataX][sluchkoordinataY].nas = new Matka(sluchkoordinataX,sluchkoordinataY);
                                                mypole.pol[sluchkoordinataX][sluchkoordinataY].flagpererisovki = true;
                                                break;
                                            }
                                            chetchik++;
                                            if(chetchik > 100) break;
                                        } while (true);
                                    }
                                    Example.frame.repaint();
                                }
        }

    }





class MyDrawPanel extends JPanel{
    public void paintComponent(Graphics g) {

        //Указатель для доступа к полю
        Pole mypole = Pole.getInstance();

        //Создаём свои цвета
        Color mycolor1 = new Color(140,120,170);
        Color mycolor2 = new Color(0,255,0);
        g.setColor(mycolor1);

        //Вычисляем ширину и высоту клетки
        int Width = this.getWidth();    //Вычисляем ширину панели
        int Height = this.getHeight();  //Вычисляем высоту панели
        int OtstupWidth = (Width % Example.N)/2;
        int OtstupHeight = (Height % Example.N)/2;
        int shirinakletki = Width / Example.N;
        int visotakletki  = Height  / Example.N;

        //Координаты левого верхнего угла клетки
        int X;
        int Y;

        //Заливаем сразу все поле цветом чтобы пчёлы не рисовались в упор к стенке.
        g.fillRect(0,0,Width,Height);
        
        //Цикл прорисовки поля по клеткам
        for(int i = 0; i < Example.N; i++) {
            for(int j = 0; j < Example.N; j++) {
                if(mypole.pol[i][j].flagpererisovki == true){
                    if (mypole.pol[i][j].nas != null) {
                        mypole.pol[i][j].nas.risovanie(g,shirinakletki,visotakletki,OtstupWidth,OtstupHeight);
                    }
                    else {
                        if (mypole.pol[i][j].ed != null) {
                            mypole.pol[i][j].ed.risovanie(g, shirinakletki, visotakletki, OtstupWidth, OtstupHeight);
                        } else {
                            g.setColor(mycolor1);
                            X = i * shirinakletki + OtstupWidth;
                            Y = j * visotakletki + OtstupHeight;
                            g.fillRect(X, Y, X + shirinakletki, Y + visotakletki);
                        }
                    }
                }
            }
        }

        //Меняем количество нектара и пчёл в текстовых полях
        Example.textfield1.setText(Integer.toString(Example.KolNektara()));
        Example.textfield2.setText(Integer.toString(Example.KolPchel()));
        Example.textfield3.setText(Integer.toString(Example.KolMatok()));

    }
}