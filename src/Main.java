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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

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

    public static void calc_path(int a, int b, int c, int d, HashMap<Integer, Long> hash_map) {
        int buf = b;
        ArrayList<String> path = new ArrayList<>();
        while (buf > a){
            if (Objects.equals(hash_map.get(buf), hash_map.get(buf - 1))){
                path.add(0, "k1");
                buf -= d;
            } else {
                path.add(0, "k2");
                buf /= c;
            }
        }
        System.out.println(path);
    }

    public static long create_map(int a, int b, int c, int d) {
        int length = b-a+1;
        int[] arr = new int[length];
        for(int i = 0; i < length; i++){
            arr[i] = a + i;
        }
        int buf = a;
        HashMap<Integer, Long> hash_map = new HashMap<Integer, Long>();
        hash_map.put(a, 1L);
        for(int j = 1; j < length; j++){
            if (arr[j]%c == 0){
                if (arr[j]/c >= a) {
                    hash_map.put(arr[j], hash_map.get(arr[j] / c) + hash_map.get(arr[j] - d));
                    if (hash_map.get(arr[j] / c) + hash_map.get(arr[j] - d) > 0){
                        buf = arr[j];
                    }
                } else {
                    if (arr[j] - d >= a) {
                        hash_map.put(arr[j], hash_map.get(arr[j] - d));
                        if (hash_map.get(arr[j] - d) > 0){
                            buf = arr[j];
                        }
                    } else {
                        hash_map.put(arr[j], 0L);
                    }
                }
            } else if (arr[j]%c > 0 && arr[j] - d == buf) {
                if (arr[j] - d >= a) {
                    hash_map.put(arr[j], hash_map.get(arr[j] - d));
                    buf = arr[j];
                } else {
                    hash_map.put(arr[j], 0L);
                }
            } else {
                hash_map.put(arr[j], 0L);
            }
        }
        calc_path(a, b, c, d, hash_map);
        System.out.println(hash_map);
        return hash_map.get(b);
    }

    public static void main(String[] args) {
//        //        a: 2 b: 7 c: 2 d: 1 -> 3
//        long solution = create_map(2, 7, 2, 1);
//        System.out.println(solution);
//        //        a: 3 b: 27 c: 3 d: 2 -> 6
//        solution = create_map(3, 27, 3, 2);
//        System.out.println(solution);
//        //        a: 30 b: 345 c: 5 d: 6 -> 0
//        solution = create_map(30, 345, 5, 6);
//        System.out.println(solution);
//        //        a: 30 b: 345 c: 2 d: 1 -> 7047
//        solution = create_map(30, 345, 2, 1);
//        System.out.println(solution);
        //        a: 22 b: 333 c: 3 d: 1 -> 467
        long solution = create_map(22, 333, 3, 1);
        System.out.println(solution);
//        //        a: 55 b: 555 c: 5 d: 2 -> 30
//        solution = create_map(55, 555, 5, 2);
//        System.out.println(solution);
//        //        a: 22 b: 2022 c: 11 d: 56 -> 0
//        solution = create_map(22, 2022, 11, 56);
//        System.out.println(solution);
//        //        a: 22 b: 2022 c: 11 d: 10 -> 18
//        solution = create_map(22, 2022, 11, 10);
//        System.out.println(solution);
//        //        a: 22 b: 2022 c: 3 d: 1 -> 763827
//        solution = create_map(22, 2022, 3, 1);
//        System.out.println(solution);
//        //        a: 22 b: 20220 c: 3 d: 1 -> 535173226980
//        solution = create_map(22, 20220, 3, 1);
//        System.out.println(solution);
//        //        a: 1 b: 1111 c: 2 d: 1 -> 3990330794
//        solution = create_map(1, 1111, 2, 1);
//        System.out.println(solution);
//        //        a: 1 b: 11111 c: 2 d: 1 -> 606408167570737286
//        solution = create_map(1, 11111, 2, 1);
//        System.out.println(solution);
    }
}
