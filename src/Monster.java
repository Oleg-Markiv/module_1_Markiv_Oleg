import java.util.Random;
import java.util.Scanner;

public class Monster { // Monster - monster - (плюс)-монстр
    private static String MS = "$"; // MS - monster symbol - символ монстра

    static int[] preparation(int DC) {
        Random random = new Random();
        int a = random.nextInt(100 * DC);
        int b = random.nextInt(100 * DC);
        int[] res = new int[2];
        res[0] = a;
        res[1] = b;
        return res;
    }
    static boolean task(int DC) {
        Scanner sc = new Scanner(System.in);
        int[] res = preparation(DC);

        int trueAnswer = res[0] + res[1];
        System.out.println("Решите пример: " + res[0] + " + " + res[1] + " = ?");
        int ans = sc.nextInt();
        return trueAnswer == ans;
    }

    static public String gMS() { // gMS - get monster symbol - получить символ монстра
        return MS; }

    static public void sMS(String ms) { // gMS - set monster symbol - изменить символ монстра
        MS = ms; }
}
