import java.util.Scanner;
import java.util.Random;

public class Main {
    static private class Monster {
        static boolean task(int DC) {
            Random random = new Random();
            int a = random.nextInt(100 * DC);
            int b = random.nextInt(100 * DC);
            int trueAnswer = a + b;
            System.out.println("Реши пример: " + a + " + " + b + " = ?");
            Scanner sc = new Scanner(System.in);
            int ans = sc.nextInt();
            return trueAnswer == ans;
        }
    }

    static private class MMonster extends Monster {
        static boolean task(int DC) {
            Random random = new Random();
            int a = random.nextInt(100 * DC);
            int b = random.nextInt(100 * DC);
            int trueAnswer = a - b;
            System.out.println("Реши пример: " + a + " - " + b + " = ?");
            Scanner sc = new Scanner(System.in);
            int ans = sc.nextInt();
            return trueAnswer == ans;
        }
    }

    public static void main(String[] args) {

        String GGC = ""; // GGC - global game choice - глобальный игровой выбор
        println("Добро пожаловать в 'Дойди до замка'");
        println("!ИНФО! Для максимально удобной игры сделайте консоль высотой в 15 строк");
        println("!ИНФО! Пожалуйста, не перезаполняйте строки - у меня везде int!!!!!\n");

        while (!GGC.equals("END")) {
            String[] symbols = new String[8];
            symbols[0] = "*"; // это герой
            symbols[1] = "@"; // это замок
            symbols[2] = "#"; // это ловушка
            symbols[3] = "$"; // это монстр
            symbols[4] = "_"; // это пустая клетка
            symbols[5] = "/"; // это бревно (предмет)
            symbols[6] = "^"; // это шлем (лечение; предмет)
            symbols[7] = ">"; // это м-монстр

            int size = 5;
            int lives = 5;
            String[][][] board = new String[2][size][size];
            createBoard(board, symbols);
            int len = board[0].length;

            int Hy = len - 1; // Hy - hero y - вертикальная координата героя
            int Hx = Integer.parseInt(board[1][0][0]); // Hx - hero x - горизонтальная координата героя
            String com = ""; // com - comment - комментарий к предыдущему ходу

            println("Условные обозначения:");
            println(" - Hero (Герой) - " + symbols[0]);
            println(" - Castle (Замок) - " + symbols[1]);
            println(" - Trap (Ловушка) - " + symbols[2]);
            println(" - Monster (Монстр) - " + symbols[3]);
            println(" - Space (Пустая клетка) - " + symbols[4]);
            println(" - Beam (Бревно) - " + symbols[5]);
            println(" - Helmet (Шлем) - " + symbols[6]);
            println(" - Size (Размер поля (оно квадратное)) - " + size);
            println(" - Lives (Жизни героя со старта) - " + lives);

            println("\nВыйти из игры - введите - 'END' (без кавычек, любой регистр)");
            println("Продолжить играть - введите любой другой текст");

            GGC = input().toUpperCase();

            if (!GGC.equals("END")) {
                println("\n!ИНФО! Ходить при помощи клавиатуры:");
                println("!ИНФО! w - вперёд, a - влево, s - вниз, d - вправо");

                String tDC; // tDC - temporary difficult choice - временный выбор сложности
                int DC; // DC - difficult choice - выбор сложности
                while (true) {
                    println("\nВыберите сложность (от 1 до 3):");
                    tDC = input();
                    if (!isInt(tDC)) {
                        println("\n - Сложность должна быть целым числом");
                        continue;
                    }
                    DC = Integer.parseInt(tDC);
                    if (!(1 <= DC && DC <= 3)) {
                        println("\n - Сложность не должна быть меньше 1 или больше 3");
                        continue;
                    }
                    break;
                }

                String Sm = ""; // Sm - symbol to move - символ в той клетке, в которую походим в данное перемещение
                int BC = 0; // BC - beam counter - количество брёвен
                int S = 1; // S - step - шаг - номер хода

                while (lives > 0 && !Sm.equals(symbols[1])) {
                    boolean moveFlag = true;
                    int Dx = 0; // Dx - direction x - направление хода по горизонтали
                    int Dy = 0; // Dy - direction y - направление хода по вертикали
                    println(com);
                    showBoard(board[0]);
                    println("У вас осталось " + lives + " жизней.");
                    println("Сделайте новый ход (" + S + "-й ход):");
                    String move = input();

                    switch (move) {
                        case "w":
                            if (Hy - 1 < 0) {
                                moveFlag = false;
                                com = "Вы пытались выйти за верхнюю границу поля";
                            } else {
                                Dy = -1;
                                com = "Вы переместились на одну клетку вверх";
                            }
                            break;
                        case "a":
                            if (Hx - 1 < 0) {
                                moveFlag = false;
                                com = "Вы пытались выйти за левую границы поля";
                            } else {
                                Dx = -1;
                                com = "Вы переместились на одну клетку влево";
                            }
                            break;
                        case "s":
                            if (Hy + 1 >= len) {
                                moveFlag = false;
                                com = "Вы пытались выйти за нижнюю границу поля";
                            } else {
                                Dy = 1;
                                com = "Вы переместились на одну клетку вниз";
                            }
                            break;
                        case "d":
                            if (Hx + 1 >= len) {
                                moveFlag = false;
                                com = "Вы пытались выйти за правую границу поля";
                            } else {
                                Dx = 1;
                                com = "Вы переместились на одну клетку вправо";
                            }
                            break;
                        default:
                            moveFlag = false;
                            com = "Вы ввели какие-то символы кроме 'w', 'a', 's', 'd'";
                            break;
                    }

                    if (moveFlag) {
                        Sm = board[0][Hy + Dy][Hx + Dx];
                        S++;
                        if (Sm.equals(symbols[2])) {
                            if (BC < DC) {
                                println("\nВы уверены что хотите походить сюда? У вас нет (недостаточно) брёвен чтобы обезвредить ловушку");
                                print("(чтобы обезвредить ловушку требуется ещё " + (DC - BC) + " брёвен");
                                if (lives > DC) {
                                    println(")");
                                } else {
                                    println("; вы можете погибнуть(!), так как у вас недостаточно жизней чтобы выбраться из ловушки)");
                                }
                                println("Если уверены - введите 'YES' (без кавычек; любой регистр)");
                                println("Если нет - введите любой другой текст");
                                String TCh = input().toUpperCase(); // TCh - trap choice - выбор по поводу ловушки
                                if (TCh.equals("YES")) {
                                    board[0][Hy + Dy][Hx + Dx] = symbols[0];
                                    board[0][Hy][Hx] = symbols[4];
                                    lives -= DC;
                                    Hy += Dy;
                                    Hx += Dx;
                                    com += "; Вы попались в ловушку; У вас осталось " + lives + " жизней";
                                } else {
                                    S--;
                                    com += "; Вы отступили от ловушки; Ход остался тот же; Ваша позиция осталась той же";
                                }
                            } else {
                                println("\nВы уверены что хотите походить сюда? У вас достаточно брёвен чтобы обезвредить ловушку");
                                println("Если выберите обезвредить - бревно(а) будет использовано");
                                println("Если уверены - введите 'YES' (без кавычек; любой регистр)");
                                println("Если нет - введите любой другой текст");
                                String TCh = input().toUpperCase(); // TCh - trap choice - выбор по поводу ловушки
                                if (TCh.equals("YES")) {
                                    board[0][Hy + Dy][Hx + Dx] = symbols[0];
                                    board[0][Hy][Hx] = symbols[4];
                                    BC -= DC;
                                    Hy += Dy;
                                    Hx += Dx;
                                    com += "; Вы обезвредили ловушку; У вас осталось " + BC + " брёвен";
                                } else {
                                    S--;
                                    com += "; Вы отступили от ловушки; Ход остался тот же; Ваша позиция осталась той же";
                                }
                            }
                        } else if (Sm.equals(symbols[3]) || Sm.equals(symbols[7])) {
                            board[0][Hy + Dy][Hx + Dx] = symbols[0];
                            board[0][Hy][Hx] = symbols[4];
                            Hy += Dy;
                            Hx += Dx;
                            println("Вы встретили монстра. Чтобы победить, решите задачу:");
                            println("Убежать вы не можете - монстр вас догонит. Если не решите задачу - монстр отнимет у вас жизнь(и)");
                            boolean TR;
                            if (Sm.equals(symbols[3])) {
                                TR = Monster.task(DC); // TR - task result - результат задания
                            } else {
                                TR = MMonster.task(DC); // TR - task result - результат задания
                            }
                            if (TR) {
                                com += "; Вы победили монстра";
                            } else {
                                lives -= DC;
                                com += "; Вы проиграли монстру; У вас осталось " + lives + " жизней";
                            }
                        } else if (Sm.equals(symbols[4]) || Sm.equals(symbols[5]) || Sm.equals(symbols[6])) {
                            board[0][Hy + Dy][Hx + Dx] = symbols[0];
                            board[0][Hy][Hx] = symbols[4];
                            Hy += Dy;
                            Hx += Dx;
                            if (Sm.equals(symbols[5])) {
                                BC += 1; // ! % & // @ # $ ^ *
                                com += "; Вы подобрали одно бревно; Количество брёвен - " + BC;
                            } else if (Sm.equals(symbols[6])) {
                                lives += 1;
                                com += "; Вы подобрали шлем; Ваше здоровье увеличено на 1; Количество жизней - " + lives;
                            }
                        }
                    }
                }

                if (lives > 0) {
                    println("-~- Вы дошли до замка! Поздравляю! -~-"); //  У вас осталось " + lives + " жизней; Вы нашли " + BC + "досок, " + HC + "шлемов; Вы попались в " + TC + "ловушек
                } else {
                    println("-~- Вы погибли по пути в замок. :( Сочувствую! -~-");
                }
            }
        } println("Вы вышли из игры. Приходите ещё!");
    }

    public static void createBoard(String[][][] board, String[] symbols) {
        int len = board[0].length; // len - длина списка (размер поля)
        int TC = len + 1; // TC - trap counter - количество ловушек
        int MC = len * 2; // MC - monster counter - количество монстров
        int BC = len / 2 + 1; // BC - beam counter - количество брёвен
        int HC = len / 3; // HC - helmet counter - количество шлемов
//        int ASC = TC + MC + BC + HC; // ASC - all sum of counters - количество всех объектов

        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                board[0][i][j] = symbols[4];
            }
        }

        int Hx = randint(len); // Hx - hero x - горизонтальная координата героя
        board[0][len - 1][Hx] = symbols[0];
        board[1][0][0] = Integer.toString(Hx);
        int Cx = randint(len); // Cx - castle x - горизонтальная координата замка
        board[0][0][Cx] = symbols[1];

        while (TC + MC + BC + HC > 0) {
            int PC = randint(6); // PC - place choice - выбор того, что будет стоять выбранном месте
            if (PC == 0) {
                int Ty = randint(len); // Ty - trap y - вертикальная координата ловушки
                int Tx = randint(len); // Tx - trap x - горизонтальная координата ловушки
                if ((board[0][Ty][Tx].equals(symbols[4])) && (TC > 0)) {
                    board[0][Ty][Tx] = symbols[2];
                    TC--; }
            } else if (PC == 1) {
                int By = randint(len); // Ty - trap y - вертикальная координата бревна
                int Bx = randint(len); // Tx - trap x - горизонтальная координата бревна
                if ((board[0][By][Bx].equals(symbols[4])) && (BC > 0)) {
                    board[0][By][Bx] = symbols[5];
                    BC--; }
            } else if (PC == 2) {
                int Hly = randint(len); // Hly - helmet y - вертикальная координата шлема
                int Hlx = randint(len); // Hlx - helmet x - горизонтальная координата шлема
                if ((board[0][Hly][Hlx].equals(symbols[4])) && (HC > 0)) {
                    board[0][Hly][Hlx] = symbols[6];
                    HC--; }
            } else {
                int My = randint(len); // My - monster y - вертикальная координата монстра
                int Mx = randint(len); // Mx - monster x - горизонтальная координата монстра
                if ((board[0][My][Mx].equals(symbols[4])) && (MC > 0)) {
                    int MoP = randint(5); // MoP - minus or plus - минус или плюс
                    if (MoP == 1) { board[0][My][Mx] = symbols[7]; }
                    else { board[0][My][Mx] = symbols[3]; }
                    MC --; }
            }
        }
    }

    public static void showBoard(String[][] board) {
        int len = board[0].length;
        String wall = " ---".repeat(len);

        println(wall);
        for (int i = 0; i < len; i++) {
            print("| ");
            for (int j = 0; j < len; j++) {
                print(board[i][j] + " | ");
            }
                println("\n" + wall);
        }
    }


    public static void print(String s) { System.out.print(s);}

    public static void println(String s) { print(s + "\n");}

    public static String input() { Scanner scn = new Scanner(System.in); return scn.nextLine();}

    public static int randint(int limit) { Random rnd = new Random(); return rnd.nextInt(limit);}

    public static boolean isInt(String s) {
        if (s == null || s.isEmpty()) {
            return false;
        }
        return s.matches("-?\\d+");
    }

    public static String strI(int s) { return String.valueOf(s); }
}

//
//if (person.isMoveCorrect(x, y)) {
//String next = board[y - 1][x - 1];
//    if (next.equals("  ")) {
//board[person.y - 1][person.x - 1] = "  ";
//        person.move(x, y);
//step++;
//        System.out.println("Ход корректный; Новые координаты: " + person.x + ", " + person.y +
//        "\\nХод номер: " + step);
//    } else if (next.equals(castle)) {
//        System.out.println("Вы прошли игру!");
//        break;
//                } else {
//                ... // задача и взаимодействие с монстрами
//                }
//                } else {
//                System.out.println("Некорректный ход");
//}
// public int getX(){
//    return x;
//}
//public int getY() {
//    return y;
//}
//public int getLive() {
//    return live;
//}
//public String getImage(){
//    return image;
//}

//public void setX(int x) {
//    this.x = x;
//}
//public void setY(int y) {
//    this.y = y;
//}
//public void setLive(int live) {
//    this.live = live;
//}
//public void setImage(String image) {
//    this.image = image;
//}
