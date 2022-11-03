package Metodos;

class Threading implements Runnable {
    private String name;
    private Thread t;

    public Threading(String threadname) {
        name = threadname;
        t = new Thread(this, name);
        System.out.println("New thread: " + t);
        t.start();
    }

    public void run() {

    }
}

class MultiThread {
    public static void main(String[] args) {
        new Threading("One");
        new Threading("Two");
        new Threading("Three");
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            System.out.println("Main thread Interrupted");
        }
        System.out.println("Main thread exiting.");
    }
}
