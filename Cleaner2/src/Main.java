public class Main {
    public static void main(String[] args) throws InterruptedException {
        Cleaner cleaner = new Cleaner(5 , 5 , 10);
        System.out.println(cleaner);
        cleaner.work();
    }
}
