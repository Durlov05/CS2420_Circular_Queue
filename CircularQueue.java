package a02_Circular_Queue;

import java.lang.reflect.Array;

public class CircularQueue<Item> implements Iterable<Item> {
	   
	/* Iterator:
	The iterator should iterate over the queue elements in the order in which they are removed.
	*/
	
	private Item[] itemArray;
	private int front;
	private int rear;
	
	CircularQueueA(int capacity)
	{
		itemArray = (Item[]) Array.newInstance(itemArray, capacity); // This is an error! Figure it out.
		front = -1;
		rear = -1;
	}
	   public boolean isFull()
	   {   
		   if (front == ((rear + 1) % itemArray.length))
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
		   return front == -1;
	   }
	   
	   public int size()
	   {
		   if (front <= rear) {size = rear - front + 1;}
		   else {size = (itemArray.length - front) + rear + 1;}
		   
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
		   if (isFull) => throw new UnsupprtedOperationException("The Array is Full");
		   else if (front == -1)
		   {
			   front = 0;
			   rear = 0;
			   itemArray[rear] = item;
		   }
		   else // front > -1 and rear > -1
		   {
			   itemArray[rear] = item;
			   rear = (rear + 1) % itemArray.length
			   // front = 5; rear = 7
			   //rear = (rear + 1) % itemArray.length = (7+1)%8 = 0;
		   }
		   
	   }
	   
	   public Item dequeue()
	   // front = (front + 1) % maxIndex
	   // set equal to front = rear = -1, if emptied.
	   {
		   if (isEmpty) => throw new NoSuchElementException("The Array is Empty");
		   else // front > -1 and rear > -1
		   {
			   itemArray[front] = null; // Might be 0.
			   front = (front + 1) %  itemArray.length;
	
			   // front = 5; rear = 7
			   // front = (front + 1) % itemArray.length = (5+1)%8 = 6;
			   
			   // front = 7; rear = 4
			   // front = (front + 1) % itemArray.length = (7+1)%8 = 0;
		   }
		   
		   //return Item?
	   }
	   
	   public Item peek()
	   // shows front item.
	   {
	       return itemArray[front];  
	   }
	   
	   @Override
	   public String toString()
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
	   
	   public static void main (String[] arg)
	   {
		   CircularQueue <String>myCirlceQueue = new CircularQueue<String>(8);
		   
		   myCirlceQueue.enqueue("This");
		   myCirlceQueue.enqueue("is");
		   myCirlceQueue.enqueue("a");
		   myCirlceQueue.enqueue("Circular");
		   myCirlceQueue.enqueue("Queue");
		   
		   myCirlceQueue.peek();
		   
		   myCirlceQueue.dequeue();
		   
		   myCirlceQueue.peek();
		   
		   myCirlceQueue.enqueue("This");
		   
		   myCirlceQueue.toString();
		   
		   // Test isEmpty for True and False;
		   
		   // Test isFull for True and False;
	   }
	}