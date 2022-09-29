public class ArraySum {
    public ArraySum(){
    }

    public int sumOfArray(Integer[] arr, int index){
        if (index == -1){
            return 0;
        }
        else{
            return arr[index]+sumOfArray(arr, index-1);
        }
    }

}
