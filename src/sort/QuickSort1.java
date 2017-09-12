package sort;


/**
 * 快速排序算法
 */
public class QuickSort1 {
    public static void main(String[] args) {
        int[] arr = new int[]{9, 8, 7, 6, 5, 4, 3, 2, 1, 0};
        sort(arr, 0, arr.length - 1);
    }

    /**
     * 方法一：
     *
     * @param arr
     * @param low
     * @param high
     */
    public static void sort(int arr[], int low, int high) {
        int l = low;
        int h = high;
        int povit = arr[low];
        while (l < h) {//以当前下标左右大小为比较前提，循环比较，知道左边的下标等于右边的下标，则本次循环停止，此时povit左边的数字都小于等于povit，右边的数字都大于等于povit
            while (l < h && arr[h] >= povit) {//如果左边的下标比右边的下标小并且右边的下标对应的数字比第一个数大，
                // 则右边下标的数字不变，右边下标往左移一位
                h--;
            }
            if (l < h) {//以当前下标左右大小为比较前提，此时右边的下标对应的数字小于左边的第一个数字，则交换两个数字的位置，左边的下标往右移动一位
                int temp = arr[h];
                arr[h] = arr[l];
                arr[l] = temp;
                l++;
            }
            while (l < h && arr[l] <= povit) {//如果左边的下标对应的数字都小于之前的最左边第一个数字，则左边下标继续往右移动一位
                l++;
            }
            if (l < h) {//以当前下标左右大小为比较前提，此时左边的下标对应的数字大于之前被移动过的那个数字，则交换两个数字的位置，右边的下标往左移动一位
                int temp = arr[h];
                arr[h] = arr[l];
                arr[l] = temp;
                h--;
            }
        }
        print(arr);
        System.out.println(" l=" + (l + 1) + "  h=" + (h + 1) + "  povit=" + povit + "\n");
        if (l > low)//如果左边的下标向右移动说明左边数字已经被移动到右边
            sort(arr, low, l - 1);
        if (h < high)////如果右边的下标向左移动说明右边数字已经被移动到左边
            sort(arr, l + 1, high);
    }

    private <T extends Comparable<? super T>> void quickSort2(T[] targetArr, int start, int end) {
        int i = start + 1, j = end;
        T key = targetArr[start];
        SortUtil<T> sUtil = new SortUtil<T>();
        if (start >= end) return;
        //从i++和j--两个方向搜索不满足条件的值并交换。条件为：i++方向小于key，j--方向大于key
        while (true) {
            while (targetArr[j].compareTo(key) > 0) j--;
            while (targetArr[i].compareTo(key) < 0 && i < j) i++;
            if (i >= j) break;
            sUtil.swap(targetArr, i, j);
            if (targetArr[i] == key) {
                j--;
            } else {
                i++;
            }
        }
        //关键数据放到‘中间’
        sUtil.swap(targetArr, start, j);
        if (start < i - 1) {
            this.quickSort2(targetArr, start, i - 1);
        }
        if (j + 1 < end) {
            this.quickSort2(targetArr, j + 1, end);
        }
    }

    /**
     * 方法三 减少交换次数，提高效率
     *
     * @param targetArr
     * @param start
     * @param end
     * @param <T>
     */
    private  <T extends Comparable<? super T>> void quickSort3(T[] targetArr, int start, int end) {
        int i = start, j = end;
        if (start >= end) {
            return;
        }
        T key = targetArr[start];
        while (i < j) {
            //按j--方向遍历目标数组，直到比key小的值为止
            while (j > i && targetArr[j].compareTo(key) >= 0) {
                j--;
            }
            if (i < j) {
                //targetArr[i]已经保存在key中，可将后面的数填入
                targetArr[i] = targetArr[j];
                i++;
            }
            //按i++方向遍历目标数组，直到比key大的值为止
            while (i < j && targetArr[i].compareTo(key) <= 0)
                //此处一定要小于等于零，假设数组之内有一亿个1，0交替出现的话，而key的值又恰巧是1的话，那么这个小于等于的作用就会使下面的if语句少执行一亿次。*/ {
                i++;
        }
        if (i < j) {
            //targetArr[j]已保存在targetArr[i]中，可将前面的值填入
            targetArr[j] = targetArr[i];
            j--;
        }
        //此时i==j
        targetArr[i] = key;
        //递归调用，把key前面的完成排序
        this.quickSort3(targetArr, start, i - 1);
        //递归调用，把key后面的完成排序*/
        this.quickSort3(targetArr, j + 1, end);
    }

    private static void print(int[] arr) {
        StringBuffer arrBuffer = new StringBuffer();
        arrBuffer.append("[");
        for (int i = 0; i < arr.length; i++) {
            if (i == arr.length - 1) {
                arrBuffer.append(arr[i]);
            } else {
                arrBuffer.append(arr[i]).append(",");
            }
        }
        arrBuffer.append("]");
        System.out.println(arrBuffer.toString());
    }

}
