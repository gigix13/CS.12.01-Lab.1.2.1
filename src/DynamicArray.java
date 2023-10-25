import java.lang.reflect.Array;
import java.util.Arrays;

public class DynamicArray<T> {

    private T[] array;
    private int size;

    public DynamicArray(Class<T> type)
    {
        array = (T[]) Array.newInstance(type, 0);
        size = 0;
    }
    public int size(){
        return size;
    }

    public boolean isEmpty(){
        return(size==0);
    }

    public T get(int index){
        if(index < 0 || index > size - 1)
            return null;
        return array[index];
    }

    public boolean contains(T element){
        for(int i = 0; i < size; i++){
            if(element.equals(array[i])) {
                return true;
            }
        }
        return false;
    }

    public void add(T element){
        size ++;
        resize();
        array[size - 1] = element;
    }

    public void add(int index, T element){
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("Array Index out of Bounds!");
        }
        size ++;
        resize();
        System.arraycopy(array, index, array, index + 1, size - index - 1);
        array[index] = element;
    }

    public void set(int index, T element){
        if (index < 0 || index >= size){
            throw new IllegalArgumentException("Array Index out of Bounds!");
        }
        array[index] = element;
    }

    public T remove(int index){
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Array Index out of Bounds!");
        }
        T removed = array[index];
        System.arraycopy(array, index + 1, array, index, size - index - 1);
        size--;
        resizeRemove();
        return removed;
    }

    public T remove(T element){
        for(int i = 0; i < size; i++){
            if(element.equals(array[i]))
            {
                //element = array[i]; // ?
                if(i!=(size-1))
                    System.arraycopy(array, i + 1, array, i, size - i - 1);
                size--;

                // Before calling resize, array is [you, Love, Love]
                resizeRemove();
                // After calling resize, array is [you, null]

                break;
            }
        }
        return element;
    }

    public T removeAll(T element){
        for(int i = 0; i < size; i++){
            if(element.equals(array[i])){
                remove(i);
                i--;
            }
        }
        return element;
    }

    public void clear(){
        for(int i = 0; i < size; i++){
            array[i] = null;
        }
        size = 0;
    }

    public void resize(){
        T[] newArray = (T[]) Array.newInstance((Class<T>) array.getClass().getComponentType(), size);
        System.arraycopy(array, 0, newArray, 0, size-1);
        /*for (int i = 0; i < newArray.length; i++) System.out.print(newArray[i] + " ");
        System.out.println();*/
        array = newArray;
    }

    public void resizeRemove(){
        T[] newArray = (T[]) Array.newInstance((Class<T>) array.getClass().getComponentType(), size);
        System.arraycopy(array, 0, newArray, 0, size);
        /*for (int i = 0; i < newArray.length; i++) System.out.print(newArray[i] + " ");
        System.out.println();*/
        array = newArray;
    }
}
