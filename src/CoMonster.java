import java.util.Random;
import java.util.Scanner;

public class CoMonster extends Monster { // CoMonster - composition monster - умножить-монстр
    private static String MS = "<"; // MS - monster symbol - символ монстра

    static boolean task(int DC) {
        Random random = new Random();
        Scanner sc = new Scanner(System.in);

        int a = random.nextInt(10 * DC);
        int b = random.nextInt(10 * DC);
        int trueAnswer = a * b;
        System.out.println("Реши пример: " + a + " * " + b + " = ?");
        int ans = sc.nextInt();
        return trueAnswer == ans;
    }

    static public String gMS() { // gMS - get monster symbol - получить символ монстра
        return MS; }

    static public void sMS(String ms) { // gMS - set monster symbol - изменить символ монстра
        MS = ms; }
}
