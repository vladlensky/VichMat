import java.io.*;

public class ReadMatrix {
    private static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedReader inFile;

    public static double[][] readFromFile(String fileName) {
        while (true) {
            try {
                inFile = new BufferedReader(new InputStreamReader(new FileInputStream(fileName)));
                break;
            } catch (FileNotFoundException e) {
                System.out.println("Файла не существует! Введите значение снова: ");
                while (true) {
                    try {
                        fileName = in.readLine();
                        break;
                    } catch (IOException es) {
                        System.out.println("Ввод некорректен! Введите значение снова: ");
                    }
                }
            }
        }
        try {
            int size = Integer.parseInt(inFile.readLine());
            if (size <= 1 && size >= 21) {
                System.out.println("Размер должен быть от 2 до 20!");
                return null;
            }
            double[][] matrix = new double[size][size + 1];
            for (int i = 0; i < size; i++) {
                String[] next = inFile.readLine().split(" ");
                for (int j = 0; j <= size; j++) {
                    matrix[i][j] = Double.parseDouble(next[j]);
                }
            }
            return matrix;
        } catch (Exception e) {
            System.out.println("Неправильная матрица в файле! Читайте мануал!!!");
            return null;
        }
    }

    public static double[][] readFromLine() {
        while (true) {
            try {
                int size = Integer.parseInt(in.readLine());
                if (size <= 1 && size >= 21) {
                    System.out.println("Размер должен быть от 2 до 20!Повторите ввод: ");
                    continue;
                }

                double[][] matrix = new double[size][size + 1];
                for (int i = 0; i < size; i++) {
                    while (true) {
                        try {
                            String[] next = in.readLine().split(" ");
                            for (int j = 0; j <= size; j++) {
                                matrix[i][j] = Double.parseDouble(next[j]);
                            }
                            break;
                        } catch (Exception e) {
                            System.out.println("Вы ввели некорректную строку матрицы! Повторите ввод:");
                        }
                    }
                }
                return matrix;
            } catch (Exception e) {
                System.out.println("Вы ввели некорректный размер матрицы! Повторите ввод:");
            }
        }
    }

    public static void printDoubleMatrix(double[][] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j <= arr.length; j++) {
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
    }
    public static void printMatrix(double[] arr){
        for(int i = 0;i < arr.length;i++)
            System.out.println(arr[i]);
    }
}
