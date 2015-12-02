package se.kth.jazulay.project1;

import java.util.Comparator;
import java.util.List;

public class BubbleSort <T> {
    Comparator<T> comparator;

    public BubbleSort (Comparator<T> comparator) {
        this.comparator = comparator;
    }
    
    public void sort (List<T> list, boolean reverse) {
        int shouldReverse = reverse ? -1 : 1;
        int r = list.size() - 2;
        boolean swapped = true;
        
        while (r >= 0 && swapped) {
            swapped = false;
            
            for (int i = 0; i <= r; i += 1) {
                int compareResult = this.comparator.compare(list.get(i), list.get(i + 1)) * shouldReverse;
                
                if (compareResult > 0) {
                    T temp = list.get(i);
                    list.set(i, list.get(i + 1));
                    list.set(i + 1, temp);
                    swapped = true;
                }
            }
            r -= 1;
        }
    }
}
