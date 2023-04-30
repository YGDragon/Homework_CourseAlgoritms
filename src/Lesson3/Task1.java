package Lesson3;

import static Lesson3.DList.*;

public class Task1 {
    // задача 1 - разворот двусвязного списка
    public static void reverseList() {
        // исходный массив целых чисел
        int[] arrayData = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        // составление исходного списка из элементов массива
        DList list = new DList();
        setList(arrayData);
        // вывод результа
        System.out.print("Исходный двухсвязный список: ");
        printNode(list.getHead());
        System.out.print("Развернутый двухсвязный список: ");
        printNode(reverse(list.getHead()));
        System.out.println();
    }
}