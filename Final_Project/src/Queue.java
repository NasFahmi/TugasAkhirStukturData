// class ini berisi queue sederhana 
// teteapi untuk input dari queue dirubah menjadi string
// dan ada penambahan function untuk mendapatkan queue menurut isi dari queue
public class Queue {
    String[] element;
    int front, rear, maxQueue;

    public Queue(int max) {
        front = 0;
        rear = -1;
        element = new String[max];//inisiasi dari queue
        maxQueue = max - 1;
    }

    public boolean isFull() {
        return rear == maxQueue;
    }

    public void enqueue(String data) {
        if (isFull()) {
            System.out.println("Queue Overflow, tidak dapat mengisi data lagi");
        } else {
            rear +=1;//setting rear = 0 yang awalnya -1 agar sama dengan index pertama
            element[rear]=data;
        }
    }

    public boolean isEmpty() {
        return front>rear;
    }

    public String dequeue() {
        String data = null;
        if (isEmpty()) {
            System.out.println("Queue Underflow");
        } else {
            data = element[front];
            element[front] = null;
            front += 1;
        }
        return data;
    }

    public void printQueue() {
        if (!isEmpty()) {
            System.out.println("Data of Queue: ");
            for (int i = front; i <= rear; i++) {
                System.out.println("Data pada Element ke-"+(i+1)+" " + element[i] + " ");
            }
        } else {
            System.out.println("Queue masih kosong");
        }
    }
    // function untuk menselect element queue mendurut isi dari queue
    public String selectElements(String selecting) {
        
        if (!isEmpty()) {
            System.out.println("Selecting elements in the Queue:");
            for (int i = front; i <= rear; i++) {
                String element = this.element[i];
                // Lakukan seleksi sesuai kebutuhan
                if (element.contains(selecting)) {
                    return element;
                }
            }
        } 
        return null;
    }
    public String peek() {
        if (!isEmpty()) {
            return element[front];
        } else {
            return null;
        }
    }
    
    
    public static void main(String[] args) {
        // void main hanya digunakan untuk testing
        Queue test = new Queue(10);
        test.enqueue("data10");
        test.enqueue("data20");
        test.enqueue("data30");
        test.enqueue("data40");
        test.selectElements("10");
        
        System.out.println(test.peek());
        test.printQueue();
        test.dequeue();
        test.printQueue();
    }
}
