/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ComparisonAlgo.src.com.ducphamle.algo.controller;

import ComparisonAlgo.src.com.ducphamle.algo.model.AlgoModel;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Duc Pham Le
 */
public class AlgoController {
    private AlgoModel model = null;
    
    private ArrayList<Integer> arrayTemp = null;
    
    private ArrayList<Integer> arraySize = null;
    
    private HashMap<String, Integer> checkSort = null;
    
    /**
	 * init(): to initialize instance variables.
	 * 
	 */
    public void init() {
        model = new AlgoModel();
        arrayTemp = new ArrayList<>();
        arraySize = new ArrayList<>();
        checkSort = new HashMap<>();
    }
    
    public AlgoController() {
        init();
    }
    
    /**
     * <b><i>arrGen</i></b>
     * <p>
     * public ArrayList<Integer> arrGen(int size)
     * <p>
     * This method is used to generate a random array
     * @param size - size of the array we want to generate
     * @return that array to later use
     */
    public ArrayList<Integer> arrGen(int size) {
        if (!arrayTemp.isEmpty()) {
            for (int i = 0; i < arrayTemp.size(); i++) {
                arrayTemp.remove(i);
            }
        }
        for (int i = 0; i < size; i++) {
            int temp = (int) (Math.random() * size * size);
            arrayTemp.add(temp);
        }
        return arrayTemp;
    }
    
    /**
     * <b><i>arrGen</i></b>
     * <p>
     * public ArrayList<Integer> arrCopy(ArrayList<Integer> arr)
     * <p>
     * This method is used to copy the generated array for multiple sorting
     * @param arr - the generated array
     * @return - a temp array to use different sorting algorithms
     */
    public ArrayList<Integer> arrCopy(ArrayList<Integer> arr) {
        ArrayList<Integer> temp = new ArrayList<>();
        for (int i = 0; i < arr.size(); i++) {
            temp.add(arr.get(i));
        }
        return temp;
    }
        
    /**
     * <b><i>setTimeInsertion</i></b>
     * <p>  
     * public void setTimeInsertion(int size)
     * <p>
     * This method is used to obtain time for Insertion sort.
     * @param size - size of the array that user chooses
     */
    public void setTimeInsertion(int size) {
        InsertionSort insertion = new InsertionSort();
        double start = System.currentTimeMillis();
        insertion.insertionSort(size);
        double end = System.currentTimeMillis();
        double current = end - start;
        model.setInsertionTime(current);
    }
    
    /**
     * <b><i>setTimeBubble</i></b>
     * <p>  
     * public void setTimeBubble(int size)
     * <p>
     * This method is used to obtain time for Bubble sort.
     * @param size - size of the array that user chooses
     */
    public void setTimeBubble(int size) {
        BubbleSort bubble = new BubbleSort();
        double start = System.currentTimeMillis();
        bubble.bubbleSort(size);
        double end = System.currentTimeMillis();
        double current = end - start;
        model.setBubbleTime(current);
    }
    
    /**
     * <b><i>setTimeMerge</i></b>
     * <p>  
     * public void setTimeMerge(int size)
     * <p>
     * This method is used to obtain time for Merge sort.
     * @param size - size of the array that user chooses
     */
    public void setTimeMerge(int size) {
        MergeSort merge = new MergeSort();
        double start = System.currentTimeMillis();
        merge.mergeSort(arrCopy(arrayTemp), 0, size - 1);
        double end = System.currentTimeMillis();
        double current = end - start;
        model.setMergeTime(current);
    }
    
    /**
     * <b><i>setSortAlgoInfo</i></b>
     * <p>
     * public void setSortAlgoInfo(int size)
     * <p>
     * This method is used to set up sorting time and size for each 
     * sorting model (corresponding to each size)
     * @param size - size of the array we want to sort
     */
    public void setSortAlgoInfo(int size) {
        model.setSize(size);
    }
    
    /**
     * <b><i>arraySizegen</i></b>
     * <p>
     * public void arraySizegen()
     * <p>
     * This method is used to generate different sizes of array for users
     */
    public void arraySizeGen() {
        int initSize = 10;
        for (int i = 0; i < 5; i++) {
            arraySize.add(initSize);
            initSize = initSize * 10;
        }
    }
    
    /**
     * <b><i>getSize</i></b>
     * <p>
     * public int getArraySizeElement(int index)
     * <p>
     * This method is used to get the correct size when user choose
     * @param index - index of the size in which is stored in arraySize
     * @return - the element aka the size that user choose.
     */
    public int getArraySizeElement(int index) {
        if (index < 0 || index > arraySize.size() - 1) 
            return 0;
        return arraySize.get(index);
    }
    
    /**
     * <b><i>getArrayTemp</i></b>
     * <p>
     * public ArrayList<Integer> getArrayTemp()
     * <p>
     * This method is used to get the generated array 
     * @return - generated array
     */
    public ArrayList<Integer> getArrayTemp() {
        return arrayTemp;
    }
    
    /**
     * <b><i>getArraySize</i></b>
     * <p>
     * public ArrayList<Integer> getArraySize()
     * <p>
     * This method is used to get the array that stores different sizes of 
     * generated array
     * @return - array storing different sizes of generated array
     */
    public ArrayList<Integer> getArraySize() {
        return arraySize;
    }
    
    /**
     * <b><i>getAlgoModel</i></b>
     * <p>
     * public AlgoModel getAlgoModel()
     * <p>
     * This method is used to get model of sorting algorithm.
     * @return model of sorting algorithm
     */
    public AlgoModel getAlgoModel() {
        return model;
    }
    
    /**
     *<b><i>removeAllTime</i></b>
     * <p>
     * public void removeAllTime(int size)
     * <p>
     * This method is used to reset all variables of a model to 0 when changing
     * size.
     * @param size - size of the previous model after the change
     */
    public void removeAllTime(int size) {
        model.setInsertionTime(0);
        model.setBubbleTime(0);
        model.setMergeTime(0);  
    }
    
    /**
     * <b><i>assignCheckSort</i></b>
     * <p>
     * public void assignCheckSort()
     * <p>
     * This method is used to assign all the sorting algorithm names into a map
     * called checkSort. Names are stored as keys, and values are initial set 
     * as 0.
     */
    public void assignCheckSort() {
        String[] sortAlgo = {"Insertion sort", "Bubble sort", "Merge sort"};
        for (int i = 0; i < sortAlgo.length; i++) {
            checkSort.put(sortAlgo[i], 0);
        }
    }
    
    /**
     * <b><i>resetCheckSort</i></b>
     * <p>
     * public void resetCheckSort()
     * <p>
     * This method is used to reset the checkSort map value - storing sorting algorithm
     * names that work like a switch to 0.
     */
    public void resetCheckSort() {
        for (Map.Entry<String, Integer> entry : checkSort.entrySet()) {
            if (entry.getValue() != 0) {
                checkSort.put(entry.getKey(), 0);
            }
        }
    }
    
    /**
     * <b><i>resetOneValueCheckSort</i></b>
     * <p>
     * public void resetOneValueCheckSort(String key)
     * <p>
     * This method is used to reset one value of checkSort map to 0
     * @param key - key having a value that needs to reset to 0
     */
    public void resetOneValueCheckSort(String key) {
        for (Map.Entry<String, Integer> entry : checkSort.entrySet()) {
            if (entry.getKey().equals(key)) {
                checkSort.put(entry.getKey(), 0);
            }
        }
    }
    
    /**
     * <b><i>checkSortValue</i></b>
     * <p>
     * public int checkSortValue(String key)
     * <p>
     * Thí method is used to check if the value of a sorting algorithm is equal to 
     * 1 or not.
     * @param key - algorithm name
     * @return 1 if the value is 1, 0 if it is not and -1 if errors occur
     */
    public int checkSortValue(String key) {
        for (Map.Entry<String, Integer> entry : checkSort.entrySet()) {
            if (entry.getKey().equals(key)) {
                if (entry.getValue().equals(1)) {
                    return 1;
                }
                else {
                    return 0;
                }
            }
        }
        return -1;
    }
    
    /**
     * <b><i>setCheckSort</i></b>
     * <p>
     * public void setCheckSort(String algo)
     * <p>
     * This method is used to set a value of a sorting algorithm to 1
     * @param algo - algorithm name
     */
    public void setCheckSort(String algo) {
        checkSort.put(algo, 1);
    }
    
    class InsertionSort {
            /**
         * <b><i>insertionSort</i></b>
         * <p>
         * public void insertionSort(int size)
         * <p>
         * This is insertionSort
         * @param size
         */
        public void insertionSort(int size) {
            ArrayList<Integer> localarr = arrCopy(arrayTemp);
            for (int i = 0; i < size; i++) {
                int k = i;
                int j = i - 1;
                if (i > 0) {
                    while (j >= 0) {
                        if (localarr.get(k) < localarr.get(j)) {
                            int temp = localarr.get(j);
                            localarr.set(j, localarr.get(k));
                            localarr.set(k, temp);
                            k--;
                            j--;
                        }
                        else {
                            j--;
                        }
                    }
                }
            }
        }
    }
    
    class BubbleSort {
            /**
         * <b><i>bubbleSort</i></b>
         * <p>
         * public void bubbleSort(int size)
         * <p>
         * This is bubbleSort
         * @param size
         */
        public void bubbleSort(int size) {
            int temp = 0;
            ArrayList<Integer> localarr = arrCopy(arrayTemp);
            for (int i = 0; i < size; i++) {
                for (int j = i + 1; j < localarr.size(); j++) {
                    if (localarr.get(j) < localarr.get(i)) {
                        temp = localarr.get(i);
                        localarr.set(i, localarr.get(j));
                        localarr.set(j, temp);
                    }
                }
            }
        }
    }
    
    class MergeSort {
            /**
         * <b><i>merge</i></b>
         * <p>
         * public void merge(ArrayList<Integer> array, int left, int mid, int right)
         * <p>
         * This method is used to sort one part of an array by merging
         * @param array - array used to sort
         * @param left - the smallest index of array
         * @param mid - the middle index of array
         * @param right - the highest index of array
         */
        public void merge(ArrayList<Integer> array, int left, int mid, int right) {
            int [] temp = new int[array.size()];
            int i = left;
            int j = mid + 1;
            for (int k = left; k <= right; k++) {
                if (i > mid) {
                    temp[k] = array.get(j);
                    j++;
                }
                else if (j > right) {
                    temp[k] = array.get(i);
                    i++;
                }
                else {
                    if (array.get(i).compareTo(array.get(j)) < 0) {
                        temp[k] = array.get(i);
                        i++;
                    }
                    else {
                        temp[k] = array.get(j);
                        j++;
                    }
                }
            }
            for (int k = left; k <= right; k++) {
                array.set(k, temp[k]);
            }
        }

        /**
         * <b><i>mergeSort</i></b>
         * <p>
         * public void mergeSort(ArrayList<Integer> array, int left, int right)
         * <p>
         * This method is used to call recursively this method and merge method to 
         * successfully merge sort an array.
         * @param array - array that needs sorting
         * @param left - smallest element of the array
         * @param right - highest element of the array
         */
        public void mergeSort(ArrayList<Integer> array, int left, int right) {
            int mid = 0;
            if (left < right) {
                mid = (left + right) / 2;
                mergeSort(array, left, mid);
                mergeSort(array, mid + 1, right);
                merge(array, left, mid, right);
            }
        }
    }
}
