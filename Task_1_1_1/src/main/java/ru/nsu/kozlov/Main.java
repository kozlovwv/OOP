package ru.nsu.kozlov;

/** class with realization of heapsort
 */
public class Main {

    /** array sorting based on binary heap.
     *
     * @param arr to sort
     * @return sorted array
     */
    static int[] heapsort(int[] arr) {
        int len;
        int indexOfLast;

        len = indexOfLast = arr.length;
        int[] heap = new int[len + 1];

        for (int i = 1; i <= len; i++) {
            heap[i] = arr[i - 1];
            siftUp(heap, i);
        }

        for (int i = 0; i < len; i++) {
            arr[i] = extractMin(heap, indexOfLast);
            indexOfLast--;
        }

        return arr;
    }

    /** sifting an element from bottom to top.
     *
     * @param heap to sift
     * @param k index of element of heap to sift
     */
    static void siftUp(int[] heap, int k) {
        int tmp;
        while (k > 1) {
            if (heap[k] < heap[k / 2]) {
                tmp = heap[k];
                heap[k] = heap[k / 2];
                heap[k / 2] = tmp;
                k = k / 2;
            } else {
                break;
            }
        }
    }

    /** sifting an element from top to bottom.
     *
     * @param heap to sift
     * @param indexOfLast index of last element of heap
     */
    static void siftDown(int[] heap, int indexOfLast) {
        int tmp;
        int v;
        int k = 1;

        while (2 * k <= indexOfLast) {
            v = 2 * k;

            if ((2 * k + 1 <= indexOfLast) && (heap[2 * k + 1] < heap[2 * k])) {
                v++;
            }

            if (heap[v] < heap[k]) {
                tmp = heap[k];
                heap[k] = heap[v];
                heap[v] = tmp;

                k = v;
            } else {
                break;
            }
        }
    }

    /** getting a min element of the heap.
     *
     * @param heap heap
     * @param indexOfLast index of last element of heap
     * @return the min element of heap (the top element of heap)
     */
    static int extractMin(int[] heap, int indexOfLast) {
        int min = heap[1];
        heap[1] = heap[indexOfLast];
        indexOfLast--;
        siftDown(heap, indexOfLast);
        return min;
    }

    /** main method.
     *
     * @param args arguments received from the terminal
     */
    public static void main(String[] args) {
        for (int el : heapsort(new int[] {5, 4, 3, 2, 1})) {
            System.out.print(el + " ");
        }
        System.out.print('\n');
    }
}