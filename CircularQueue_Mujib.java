package a02_Circular_Queue;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class CircularQueue<Item> implements Iterable<Item> {
	   
	/* Iterator:
	The iterator should iterate over the queue elements in the order in which they are removed.
	*/
	
	private Item[] itemArray;
	private int front;
	private int rear;
	private int capacity;
	
	public CircularQueue(int capacity)
	{
		this.capacity = capacity;
		this.front = this.rear = -1;
		itemArray = (Item[]) new Object[this.capacity];
	}
	   public boolean isFull()
	   {   
		   if (front == ((rear + 1) % capacity))
		   {
			   System.out.println("This Array is Full!");
			   return true;
			   // front = 0; rear = 7; capacity = 8; size = 5 = {0,1,2,3,4,5,6,7}
			   // (rear + 1) % 8 => (7+1) % 8 = 0 == front => isFull;
			   
			   // front = 1; rear = 0; capacity = 8; size = 5 = {1,2,3,4,5,6,7,0}
			   // (rear + 1) % 8 => (0+1) % 8 = 1 == front => isFull;
			   
		   }
		   
		   return false;
	   }
	   
	   
	   public boolean isEmpty()
	   {   
		   return (front == -1);
	   }
	   
	   public int size()
	   {
		   int s = -1;
		   if (front <= rear) {s = rear - front + 1;}
		   else {s = (capacity - front) + rear + 1;}
		   
		   return s;
		   // front = 2; rear = 5; capacity = 8; size = 4 = {2,3,4,5} 
		   
		   // front = 5; rear = 1; capacity = 8; size = 5 = {5,6,7,0,1} 
		   // size = (8 - 5) + 1 + 1= 3 + 1 + 1 = 5;
		   
		   // front = 7; rear = 0; capacity = 8; size = 2 = {7,0} 
		   // size = (8 - 7) + 0 + 1 = 1 + 1 = 2;
		   
		   // front = 0; rear = 0; capacity = 8; size = 1 = {0} 
		   // size = 0 - 0 + 1 = 0 + 1 = 1;
	   }
	   // front = 5; rear = 1; capacity = 8; size = 5 = {5,6,7,0,1}  
	   
	   public void enqueue(Item item)
	   {
		   if (isFull())  
		   {throw new UnsupportedOperationException("The Array is Full");}
		   else if (front == -1)
		   {
			   front = 0;
			   rear = 0;
			   itemArray[rear] = item;
		   }
		   else // front > -1 and rear > -1
		   {
			   rear = (rear + 1) % capacity;
			   itemArray[rear] = item;
			   // front = 5; rear = 7
			   //rear = (rear + 1) % capacity = (7+1)%8 = 0;
		   }
		   
	   }
	   
	   public Item dequeue()
	   // front = (front + 1) % maxIndex
	   // set equal to front = rear = -1, if emptied.
	   {
		   Item decued;
		   
		   if (isEmpty()) 
		   {throw new NoSuchElementException("The Array is Empty!!");}
		   else // front > -1 and rear > -1
		   {
			   decued = itemArray[front];
			   itemArray[front] = null; // Might be 0 instead of null? I don't think so. 0 is a value.
			   if (front != rear)
			   {front = (front + 1) %  capacity;}
			   else
			   {front = rear = -1;}
			   
			   // front = 5; rear = 7
			   // front = (front + 1) % capacity = (5+1)%8 = 6;
			   
			   // front = 7; rear = 4
			   // front = (front + 1) % capacity = (7+1)%8 = 0;
		   }
		   
		   return decued;  // return the item deleted??
	   }
	   
	   public Item peek()
	   // shows front item.
	   {
	       return itemArray[front];  
	   }
	   
	   @Override
	   public String toString()
	   {
		   String str = "{";
		   
		   if (front == -1) {str = "" + str;}
		   else if (front == rear) {str = (String) itemArray[front];}
		   else if (front > rear)
		   {
			   for (int i = front; i < capacity; i++) 
			   {str = str + (String) itemArray[i] + ", " ;}
			   for (int j = 0; j <= rear; j++)
			   {str = str + (String) itemArray[j] + ", " ;}
		   }
		   else  // front < rear
		   {
			   for (int k = front; k <= rear; k++) 
			   {
				   if (k < rear)
				   { str = str + (String) itemArray[k] + ", ";}
				   else
				   {str = str + (String) itemArray[k];}
			   }
			   
		   }
		   
		   str = str + "}";
		   
		   return str;
	   }
	   // goes from front to rear. 
	   // if front > rear => go from front to maxIndex
	   // then go from 0 to rear.
	   // else go from front to rear.
	   
	   // front = 5; rear = 0; => 
	   // for (int i = front; front < maxIndex; front++) {display element;}
	   // for (int j = 0; j <= rear; j++) {display element;}
	   // front = 5; rear = 1; maxIndex = 8; 
	   // size = 5 = {[5] & 10,[6] & 20,[7] & 30,[0] & 40,[1] & 50}  
	   
	   // for (int i = front; front < maxIndex; i=(i+1)%maxIndex)
	   // {
	   // display element someArray[i];
	   //	if (i == rear}
	   // 	{
	   //     break;
	   //	}
	   // i = 5 => display someArray[5] = 10 => (5+1)%8 => i = 6
	   // i = 6 => display someArray[6] = 20; => (6+1)%5 => 7
	   // i = 7 => display someArray[7] = 30; => (7+1)%5 => 0
	   // i = 0 => display someArray[0] = 40; => (0+1)%5 => 1
	   // i = 1 => display someArray[1] = 50; => (1+1)%5 => 2
	   // i = 2 => display someArray[2] = null; => (2+1)%5 => 3
	   
	   public Iterator<Item> iterator(){ return new itemized();}
	   
	   private class itemized implements Iterator<Item>
	   {		   
		   @Override
		   public boolean hasNext() {return front < rear;}
		   
		   @Override
		   public Item next() 
		   {
			   if (!hasNext())
			   {throw new NoSuchElementException("Array has no more elements");}
			   
			   Item displayData = itemArray[front];
			   front = (front + 1) % capacity;
			   return displayData;
	   		}
		}
	   
	   public static void main (String[] arg)
	   {
		   CircularQueue <String>myCirlceQueue = new CircularQueue<String>(8);
		   
		   myCirlceQueue.enqueue("This");
		   myCirlceQueue.enqueue("is");
		   myCirlceQueue.enqueue("a");
		   myCirlceQueue.enqueue("Circular");
		   myCirlceQueue.enqueue("Queue");
		   
		   System.out.println(myCirlceQueue.toString());
		   System.out.println(myCirlceQueue.peek());
		   myCirlceQueue.dequeue();
		   
		   System.out.println(myCirlceQueue.peek());
		   myCirlceQueue.enqueue("This");
		   myCirlceQueue.enqueue("is");
		   myCirlceQueue.enqueue("a");
		   //myCirlceQueue.enqueue("Circular");
		   //myCirlceQueue.enqueue("Queue");
	   
    	   System.out.println(myCirlceQueue.toString());
    	   
    	   myCirlceQueue.dequeue();
    	   myCirlceQueue.dequeue();
    	   myCirlceQueue.dequeue();
    	   myCirlceQueue.dequeue();
    	   myCirlceQueue.dequeue();
    	   myCirlceQueue.dequeue();
    	   myCirlceQueue.dequeue();
    	   myCirlceQueue.dequeue();
    	   
		   // Test isEmpty for True and False;
		   
		   // Test isFull for True and False;
		   
		   // implement iterator
		   
	   }
	}
