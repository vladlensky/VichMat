import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class App {
    private static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    public static void info(){
        System.out.println("Информация для пользователя.");
        System.out.println("Программа может считать матрицу из строки и из файла");
        System.out.println("Ввод одинаков для строки и для файла");
        System.out.println("В первой строке вводится размер матрицы(целое число N от 2 до 20).");
        System.out.println("Далее вводятся N строк по N + 1 значений в каждой через пробел.");
        System.out.println("Введите новую команду: ");
    }
    public static double[][] begin() {
        System.out.println("Прочитайте мануал перед использованием! ");
        System.out.println("Введите f или l чтобы ввести из файла или со строки соответственно, i для прочтения мануала или r для случайного набора значений: ");
        while (true){
            try {
                String input = in.readLine();
                if(input.length()==1) {
                    char a = input.charAt(0);
                    if(a=='i') {
                        info();
                        continue;
                    }
                    if(a=='f'){
                        System.out.println("Ввод с файла. Введите путь к файлу: ");
                        return ReadMatrix.readFromFile(in.readLine());
                    }
                    if(a=='l'){
                        System.out.println("Ввод со строки. Введите матрицу по мануалу(сначала размер): ");
                        return ReadMatrix.readFromLine();
                    }
                    if(a=='r'){
                        System.out.println("Рандомный набор значений.Введите размер матрицы: ");
                        while (true){
                            try{
                                String size = in.readLine();
                                int siz = Integer.parseInt(size);
                                if(siz>=2&&siz<=20) {
                                    double[][] ret = new double[1][1];
                                    ret[0][0] = siz;
                                    return ret;
                                }
                                else
                                    System.out.println("Некорректный ввод размера массива(от 2 до 20 целое число). Повтор: ");
                            }catch (Exception e){System.out.println("Некорректный ввод размера массива. Повтор: ");}
                        }
                    }
                    else
                        System.out.println("Некорректный ввод команды(i,f,l,r).Повторите :");
                }
                else
                    System.out.println("Некорректный ввод команды(i,f,l,r).Повторите :");
            }catch (IOException ex){System.out.println("Некорректный ввод команды(i,f,l,r).Повторите :");}
        }
    }
    public static void printAnswer(Gauss matrix){
        System.out.println("Выберите необходимые данные для вывода на экран: ");
        System.out.println("o - вывести значение определителя матрицы.");
        System.out.println("s - вывести начальную матрицу.");
        System.out.println("t - вывести треугольную матрицу.");
        System.out.println("r - вывести столбец решений.");
        System.out.println("i - вывести столбец погрешностей(невязок).");
        System.out.println("q - выход из программы.");
        while(true) {
            char key;
            while (true) {
                try {
                    String command = in.readLine();
                    if(command.length()==1) {
                        key = command.charAt(0);
                        break;
                    }
                    else
                        System.out.println("Неправильный ввод команды. Повторите ввод: ");
                } catch (Exception e) {
                    System.out.println("Неправильный ввод команды. Повторите ввод: ");
                }
            }
            switch (key) {
                case 'o': {
                    System.out.println(matrix.CalculateMatrix(matrix.getStartMatrix()));
                    System.out.println("Введите новую команду: ");
                    break;
                }
                case 's': {
                    ReadMatrix.printDoubleMatrix(matrix.getStartMatrix());
                    System.out.println("Введите новую команду: ");
                    break;
                }
                case 't': {
                    ReadMatrix.printDoubleMatrix(matrix.getMatrix());
                    System.out.println("Введите новую команду: ");
                    break;
                }
                case 'r': {
                    ReadMatrix.printMatrix(matrix.getRightAnswer());
                    System.out.println("Введите новую команду: ");
                    break;
                }
                case 'i': {
                    ReadMatrix.printMatrix(matrix.getInnacurasy());
                    System.out.println("Введите новую команду: ");
                    break;
                }
                case 'q': System.out.println("Выход из программы."); return;
                default: System.out.println("Неправильный ввод команды. Повторите ввод: ");
            }
        }
    }
}
