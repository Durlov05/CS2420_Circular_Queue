package a02;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class CircularQueue<Item> implements Iterable<Item> {

	private Item[] items;
	private int front, rear, capacity, n;

	public CircularQueue(int capacity) {
		if (capacity < 1)
			throw new IllegalArgumentException("Capacity can't be less then 1.");

		this.capacity = capacity;
		this.items = (Item[]) new Object[this.capacity];
		this.front = -1;
		this.rear = -1;
		this.n = 0;
	}

	public boolean isFull() {
		return ((front == 0 && rear == items.length - 1) || front == rear + 1);
	}

	public boolean isEmpty() {
		return front == -1;
	}

	public int size() {
		if (isEmpty())
			return 0;
		else
			return n;
	}

	public void enqueue(Item item) {
		if (isFull())
			throw new UnsupportedOperationException("Queue is full.");
		else {
			if (front == -1) {
				front = 0;
				rear = 0;
				items[front] = item;
				n++;
			} else {
				rear = (rear + 1) % capacity;
				items[rear] = item;
				n++;
			}
		}

	}

	public Item dequeue() {
		Item element;
		if (isEmpty())
			throw new NoSuchElementException("Can't dequeue empty queue.");
		else {
			element = items[front];
			if (front == rear) {
				items[front] = null;
				front = -1;
				rear = -1;
				n = 0;
			} 
			if (front != rear) {
				items[front] = null;
				front = (front + 1) % capacity;
				n--;
			}
			return element;
		}
	}

	public Item peek() {
		return items[front];
	}
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		if (isEmpty())
			return "[ ]";
		
		sb.append("[ ");
		for (int i = 0, tempFront = front; i < n; i++, tempFront = (front + 1) % capacity) {
			sb.append(items[tempFront]);
			if (i != n - 1 )
				sb.append(", ");
		}
		sb.append(" ]");
		return sb.toString();
	}

	// Need to be done.
	@Override
	public Iterator<Item> iterator() {
		// TODO Auto-generated method stub
		return null;
	}

	// test client
	public static void main(String[] args) {
		CircularQueue<String> q = new CircularQueue<>(8);

		System.out.println(q);

		q.enqueue("hello");
		q.enqueue("hello");
		q.enqueue("hello");
		
		System.out.println(q);
	}
}
