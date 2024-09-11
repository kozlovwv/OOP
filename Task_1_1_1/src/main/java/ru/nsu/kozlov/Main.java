package ru.nsu.kozlov;

public class Main {

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

    static void siftUp(int[] heap, int k) {
        int tmp;
        while (k > 1) {
            if (heap[k] < heap[k/2]) {
                tmp = heap[k];
                heap[k] = heap[k/2];
                heap[k/2] = tmp;
                k = k / 2;
            }
            else {
                break;
            }
        }
    }

    static void siftDown(int[] heap, int N) {
        int tmp, v;
        int k = 1;

        while (2*k <= N) {
            v = 2*k;

            if ((2*k + 1 <= N) && (heap[2*k + 1] < heap[2*k])) {
                v++;
            }

            if (heap[v] < heap[k]) {
                tmp = heap[k];
                heap[k] = heap[v];
                heap[v] = tmp;

                k = v;
            }
            else {
                break;
            }
        }
    }

    static int extractMin(int[] heap, int indexOfLast) {
        int min = heap[1];
        heap[1] = heap[indexOfLast];
        indexOfLast--;
        siftDown(heap, indexOfLast);
        return min;
    }

    public static void main(String[] args) {
        for (int el: heapsort(new int[] {5, 4, 3, 2, 1})) {
            System.out.print(el + " ");
        }
        System.out.print('\n');
    }
}