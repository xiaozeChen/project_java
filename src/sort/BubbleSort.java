package sort;

/**
 * 冒泡排序
 */
public class BubbleSort implements SortUtil.Sort {
    /**
     * 冒泡排序
     *
     * @param data
     */
    public void sort(int[] data) {
        int temp;
        for (int i = 0; i < data.length; i++) {
            for (int j = data.length - 1; j > i; j--) {
                if (data[j] < data[j - 1]) {
                    SortUtil.swap(data, j, j - 1);
                }
            }
        }
    }
}  