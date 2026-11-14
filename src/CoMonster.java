import java.util.Random;
import java.util.Scanner;

public class CoMonster extends Monster { // CoMonster - composition monster - умножить-монстр
    private static String MS = "<"; // MS - monster symbol - символ монстра

    static boolean task(int DC) {
        Scanner sc = new Scanner(System.in);
        int[] res = preparation(DC);

        int trueAnswer = res[0] * res[1];
        System.out.println("Решите пример: " + res[0] + " * " + res[1] + " = ?");
        int ans = sc.nextInt();
        return trueAnswer == ans;
    }

    static public String gMS() { // gMS - get monster symbol - получить символ монстра
        return MS; }

    static public void sMS(String ms) { // gMS - set monster symbol - изменить символ монстра
        MS = ms; }
}
