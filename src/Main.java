// На вход некоторому исполнителю подаётся четыре числа (a, b, c, d).

// У исполнителя есть две команды
// - команда 1 (к1): увеличить в "с" раз, "а" умножается на "c"
// - команда 2 (к2): увеличить на "d" , к "a" прибавляется "d"
// написать программу, которая выдаёт общее количество возможных преобразований "a" в "b"
// набор команд, позволяющий число "a" превратить в число "b" или сообщить, что это невозможно

// Пример 1: а = 1, b = 7, c = 2, d = 1
// ответ: к1, к1, к1, к1, к1, к1 или к1, к2, к1, к1, к1 или к1, к1, к2, к1.
// Пример 2: а = 11, b = 7, c = 2, d = 1
// ответ: нет решения.
// *Подумать над тем, как сделать вывод маршрута (хотя бы одного)

//Тестовые данные
//
//        a: 2 b: 7 c: 2 d: 1 -> 3
//        a: 3 b: 27 c: 3 d: 2 -> 6
//        a: 30 b: 345 c: 5 d: 6 -> 0
//        a: 30 b: 345 c: 2 d: 1 -> 7047
//        a: 22 b: 333 c: 3 d: 1 -> 467
//        a: 55 b: 555 c: 5 d: 2 -> 30
//        a: 22 b: 2022 c: 11 d: 56 -> 0
//        a: 22 b: 2022 c: 11 d: 10 -> 18
//        a: 22 b: 2022 c: 3 d: 1 -> 763827
//        a: 22 b: 20220 c: 3 d: 1 -> 535173226980
//        a: 1 b: 1111 c: 2 d: 1 -> 3990330794
//        a: 1 b: 11111 c: 2 d: 1 -> 606408167570737286

// a	b1	b2	b3	b4	b5	b6	b7	b8	b9	b10	b11	b12	b13	b14	b15	b16	b17	b18	b19	b20	b21	b22	b23	b24
// 3	4	5	6	7	8	9	10	11	12	13	14	15	16	17	18	19	20	21	22	23	24	25	26	27
// 1	0	1	0	1	0	2	0	2	0	2	0	3	0	3	0	3	0	4	0	4	0	4	0	6
//
// к1	.+2	(d)
// к2	*3	(c )
//
// f(x) = f(x - d) если x%c > 0
// f(x) = f(x - d) + f(x/c) если x%c == 0

public class Main {
    public static int func_calc(int a, int b, int c, int d) {
        int x = 0;
        if(b == a) {
            return 1;
        }
        if(b%c == 0){
            x = func_calc(a, b-d, c, d) + func_calc(a, b/c, c, d);
        }
        if(b%c > 0) {
            x = func_calc(a, b-d, c, d);
        }
        return x;
    }

    public static void main(String[] args) {
        int solution = func_calc(2, 7, 2, 1);
        System.out.println(solution);
        solution = func_calc(3, 27, 3, 2);
        System.out.println(solution);
        solution = func_calc(30, 345, 5, 6);
        System.out.println(solution);
//        solution = func_calc(30, 345, 2, 1);
//        System.out.println(solution);
//        solution = func_calc(22, 333, 3, 1);
//        System.out.println(solution);
        solution = func_calc(55, 555, 5, 2);
        System.out.println(solution);
        solution = func_calc(22, 2022, 11, 56);
        System.out.println(solution);
        solution = func_calc(22, 2022, 11, 10);
        System.out.println(solution);
    }
}
