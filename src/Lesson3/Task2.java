package Lesson3;

import static Lesson3.DList.*;

public class Task2 {
    // задача 2 - сортировка пузырьком для двусвязного списка
    public static void sortList() {
        // исходный массив целых чисел
        int[] arrayData = {8, 3, 9, 5, 6, 4, 2, 1, 3};
        // составление исходного списка из элементов массива
        DList list = new DList();
        setList(arrayData);
        // вывод результа
        System.out.print("Исходный двухсвязный список: ");
        printNode(list.getHead());
        System.out.print("Отсортированный двухсвязный список: ");
        printNode(bubbleSort(list.getHead()));
        System.out.println();
    }
}
