package se.kth.jazulay.lab3;

class BubbleSortLinkedList {
    
    class Node {
        public Node next;
        public int item;
        
        public Node (int item) {
            this.item = item;
        }
    }
    
    int size = 0;
    Node first = null;
    Node last = null;
    
    public BubbleSortLinkedList () {
    
    }
    
    public void bubbleSort () {
        int r = this.size - 2;
        int swapCount = 0;
        boolean swapped = true;
        
        Node current;
        while (r >= 0 && swapped) {
            current = first;
            swapped = false;
            
            for (int i = 0; i <= r; i += 1) {
                if (current.item > current.next.item) {
                    swap(current, current.next);
                    swapCount += 1;
                    swapped = true;
                }
                current = current.next;
            }
            r -= 1;
        }
        System.out.println("Swapcount: " + swapCount);
    }
    
    private void swap (Node swap1, Node swap2) {
        int temp = swap1.item;
        swap1.item = swap2.item;
        swap2.item = temp;
    }

    public void add(int e) {
        Node node = new Node(e);
        if (first == null) {
            first = node;
        } else {
            last.next = node;
        }
        last = node;
        size += 1;
    }
    
    public void print () {
        Node node = first;
        while (node != null) {
            System.out.println(node.item);
            node = node.next;
        }
    }
    
    public int inversionCount () {
        int count = 0;
        Node current = this.first;
        for (int i = 0; i < this.size - 2; i += 1) {
            Node next = current.next;
            for (int i2 = i + 1; i2 < this.size; i2 += 1) {
                if (current.item > next.item) {
                    count += 1;
                }
                next = next.next;
            }
            current = current.next;
        }
        return count;
    }
    
    public int amazingInversionCount () {
        return 0;
    }
}
