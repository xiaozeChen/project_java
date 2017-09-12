package sort;

/**
 * 插入排序
 */
public class InsertSort implements SortUtil.Sort{
    /**
     * 插入排序
     * @param data
     */
    public void sort(int[] data) {  
        int temp;  
        for(int i=1;i<data.length;i++){  
            for(int j=i;(j>0)&&(data[j]<data[j-1]);j--){  
                SortUtil.swap(data,j,j-1);  
            }  
        }          
    }  
}  