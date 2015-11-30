package se.kth.jazulay.lab3;

public class Main {
    public static void main (String[] args) {
        BubbleSortLinkedList sorted = new BubbleSortLinkedList();
        sorted.add(4);
        sorted.add(3);
        sorted.add(2);
        sorted.add(20);
        sorted.add(1);
        sorted.add(4);
        sorted.add(12);
        sorted.add(3);
        sorted.add(2);
        sorted.add(1);
        sorted.add(4);
        sorted.add(34);
        sorted.add(3);
        sorted.add(2);
        sorted.add(203);
        sorted.add(1);
        sorted.add(4);
        sorted.add(3);
        sorted.add(2);
        sorted.add(1);
        sorted.add(1);
        
        System.out.println(sorted.inversionCount());
        sorted.bubbleSort();
    }
}
