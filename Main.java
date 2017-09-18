public class Main {

    public static void main(String[] args) {
        Gauss method = new Gauss(App.begin());
        if(method.getRightAnswer()!=null)
            App.printAnswer(method);
    }

}