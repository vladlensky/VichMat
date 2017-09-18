import java.util.*;

public class Gauss {
    private int size;
    private double[] rightColumn;
    private double[] rightAnswer;
    private double[] innacurasy;
    private double[][] matrix;
    private double[][] startMatrix;
    private Stack<Integer> firstSwapped = new Stack<>();
    private Stack<Integer> secondSwapped = new Stack<>();

    public Gauss(int s){
        init(generate(s));
    }

    public Gauss(double[][] mat){
        if(mat.length == 1)
            init(generate((int)mat[0][0]));
        else
            init(mat);
    }

    public double[] getInnacurasy() {
        return innacurasy;
    }

    public double[][] getStartMatrix() {
        return startMatrix;
    }

    public double[] getRightAnswer() {
        return rightAnswer;
    }

    public double[][] getMatrix() {
        return matrix;
    }

    private void init(double[][] mat){
        rightColumn = new double[mat.length];
        for(int i = 0;i < mat.length;i++){
            rightColumn[i] = mat[i][mat.length];
        }
        startMatrix = copyMatrix(mat);
        for(int i = 0;i < mat.length-1;i++){
            if(mat[i][i]!=0) {
                double firstDiv = mat[i][i];
                for (int j = i; j <= mat.length; j++) {
                    mat[i][j] /= firstDiv;
                }
                for (int j = i + 1; j < mat.length; j++) {
                    double temp = -mat[j][i] /mat[i][i] ;
                    for (int k = i; k <= mat.length; k++) {
                        mat[j][k] += temp * mat[i][k];
                    }
                }
            }
            else{
                boolean b = false;
                for(int h = i+1;h < mat.length;h++){
                    if(mat[h][i]!=0){
                        mat=swap(mat,i,h);
                        i--;
                        b=true;
                        break;
                    }
                }
                if(!b) {
                    System.out.println("Решений нет!");
                    return;
                }
            }
        }
        if(mat[mat.length-1][mat.length-1]==0){
            System.out.println("Решений нет!");
            return;
        }//Составил треугольную матрицу и проверил на отсутствие решений.
        mat[mat.length-1][mat.length] /= mat[mat.length-1][mat.length-1];
        mat[mat.length-1][mat.length-1] = 1;
        matrix = mat;
        System.out.println();
        goBack();

    }

    public double[][] generate(int size){
        Random random = new Random();
        double[][] matrix = new double[size][size+1];
        for (int i = 0; i < size; i++){
            for(int j =0;j <=size;j++) {
                matrix[i][j] = random.nextDouble()*100;
            }
        }
        return matrix;
    }

    private double[][] copyMatrix(double[][] matrix){
        double[][]matr = new double[matrix.length][matrix.length+1];
        for(int i = 0;i < matrix.length;i++) {
            for (int j = 0; j <= matrix.length; j++) {
                matr[i][j] = matrix[i][j];
            }
        }
        return matr;
    }

    private void goBack(){
        double[][] matr = copyMatrix(matrix);
        rightAnswer = new double[matrix.length];
        rightAnswer[matr.length-1] = matr[matr.length-1][matr.length];
        size = matr.length;
        int k = 1;
        for(int i = matr.length-2;i >= 0;i--){
            for(int j = 0;j<k;j++){
                matr[i][size] -= matr[size-1-j][size]*matr[size-1-k][size-1-j];
                matr[size-1-k][size-1-j] = 0;
            }
            rightAnswer[i] = matr[i][size];
            k++;
        }
        setInnacurasy();
    }
    private void setInnacurasy(){
        innacurasy= new double[size];
        for(int i = 0;i < size;i++){
            double temp = rightColumn[i];
            for(int j = 0;j < size; j++){
                temp-=startMatrix[i][j]*rightAnswer[j];
            }
            innacurasy[i] = temp;
        }
        System.out.println("Матрица обработана.");
    }
    private double[][] swap(double[][] mat,int i,int h){
        firstSwapped.push(i);
        secondSwapped.push(h);
        for(int j = i;j<=mat.length;j++){
            double temp = mat[i][j];
            mat[i][j] = mat[h][j];
            mat[h][j] = temp;
        }
        return mat;
    }

    public double CalculateMatrix(double[][] matrix){
        double calcResult=0;
        if (matrix.length==2){
            calcResult=matrix[0][0]*matrix[1][1]-matrix[1][0]*matrix[0][1];
        }
        else{
            int koeff;
            for(int i=0; i<matrix.length; i++){
                if(i%2==1){  //я решил не возводить в степень, а просто поставить условие - это быстрее. Т.к. я раскладываю всегда по первой (читай - "нулевой") строке, то фактически я проверяю на четность значение i+0.
                    koeff=-1;
                }
                else{
                    koeff=1;
                };
                //собственно разложение:
                calcResult += koeff*matrix[0][i]*this.CalculateMatrix(this.GetMinor(matrix,0,i));
            }
        }
        //возвращаем ответ
        return calcResult;
    }
    //функция, к-я возвращает нужный нам минор. На входе - определитель, из к-го надо достать минор и номера строк-столбцов, к-е надо вычеркнуть.
    private double[][] GetMinor(double[][] matrix, int row, int column){
        double[][] minor = new double[matrix.length-1][matrix.length-1];
        int dI=0;//эти переменные для того, чтобы "пропускать" ненужные нам строку и столбец
        for(int i=0; i<matrix.length; i++){
            int dJ=0;
            for(int j=0; j<matrix.length; j++){
                if(i==row){
                    dI=1;
                }
                else{
                    if(j==column){
                        dJ=1;
                    }
                    else{
                        minor[i-dI][j-dJ] = matrix[i][j];
                    }
                }
            }
        }
        return minor;
    }
}