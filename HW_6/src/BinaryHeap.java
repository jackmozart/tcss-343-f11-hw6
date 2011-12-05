/**
 * A binary minheap of DijkstraNode objects.
 * 
 * @author Donald Chinn
 * @version September 19, 2003
 */
public class BinaryHeap {
    
    /* the heap is organized using the implicit array implementation.
     * Array index 0 is not used
     */
    private DijkstraNode[] elements;
    private int size;       // index of last element in the heap
    
    // Constructord
    public BinaryHeap() {
        int initialCapacity = 10;
        
        this.elements = new DijkstraNode[initialCapacity + 1];
        this.elements[0] = null;
        this.size = 0;
    }
    
    
    /**
     * Constructor
     * @param capacity  number of active elements the heap can contain
     */    
    public BinaryHeap(int capacity) {
        this.elements = new DijkstraNode[capacity + 1];
        this.elements[0] = null;
        this.size = 0;
    }
    
    
    /**
     * Given an array of DijkstraNodes, return a binary heap of those
     * elements.
     * @param data  an array of data (no particular order)
     * @return  a binary heap of the given data
     */
    public static BinaryHeap buildHeap(DijkstraNode[] data) {
        BinaryHeap newHeap = new BinaryHeap(data.length);
        for (int i = 0; i < data.length; i++) {
            newHeap.elements[i+1] = data[i];
            newHeap.elements[i+1].setMy_heap_loc(i+1);
        }
        newHeap.size = data.length;
        for (int i = newHeap.size / 2; i > 0; i--) {
            newHeap.percolateDown(i);
        }
        return newHeap;
    }


    /**
     * Determine whether the heap is empty.
     * @return  true if the heap is empty; false otherwise
     */
    public boolean isEmpty() {
        return (size < 1);
    }
    
    
    /**
     * Insert an object into the heap.
     * @param key   a key
     */
    public void insert(DijkstraNode key) {
    	
        if (size >= elements.length - 1) {
            // not enough room -- create a new array and copy
            // the elements of the old array to the new
            DijkstraNode[] newElements = new DijkstraNode[2*size];
            for (int i = 0; i < elements.length; i++) {
                newElements[i] = elements[i];
            }
            elements = newElements;
        }
        
        size++;
        elements[size] = key;
        key.setMy_heap_loc(size);//modfied to apply a heap loc
        percolateUp(size);
    }
    
    
    /**
     * Remove the object with minimum key from the heap.
     * @return  the object with minimum key of the heap
     */
    public DijkstraNode deleteMin() throws EmptyHeapException {
        if (!isEmpty()) {
            DijkstraNode returnValue = elements[1];
            elements[1] = elements[size];
            size--;
            percolateDown(1);
            return returnValue;
            
        } else {
            throw new EmptyHeapException();
        }
    }
    
    
    /**
     * Given an index in the heap array, percolate that key up the heap.
     * @param index     an index into the heap array
     */
   public void percolateUp(int index) {
        DijkstraNode temp = elements[index];  // keep track of the item to be moved
        
        
        while (index > 1) {
            if (temp.compareTo(elements[index/2]) < 0) {
                elements[index] = elements[index/2];
                index = index / 2;
            } else {
                break;
            }
        }
        
        elements[index] = temp;
        elements[index].setMy_heap_loc(index);
    }
    
    
    /**
     * Given an index in the heap array, percolate that key down the heap.
     * @param index     an index into the heap array
     */
    private void percolateDown(int index) {
        int child;
        DijkstraNode temp = elements[index];
        
        while (2*index <= size) {
            child = 2 * index;
            if ((child != size) &&
                (elements[child + 1].compareTo(elements[child]) < 0)) {
                child++;
            }
            // ASSERT: at this point, elements[child] is the smaller of
            // the two children
            if (elements[child].compareTo(temp) < 0) {
                elements[index] = elements[child];
                index = child;
            } else {
                break;
            }
        }
        elements[index] = temp;
        elements[index].setMy_heap_loc(index);
    
    }
}
