package abhik26.java_programs.java8;

public class InterfaceStaticMethodExample {
    public static void main(String[] args) {
        /* 
         * if we don't define the same method in the child class / interface
         * then we cannot even call the method using child class / interface name.
         */
        MyClass.myStaticPrint();
        MyChildInterface.myStaticPrint();
        MyInterface.myStaticPrint();
    }

    private static interface MyInterface {
        static void myStaticPrint() {
            System.out.println("MyInterface static method!!");
        }
    }

    private static interface MyChildInterface extends MyInterface {
        static void myStaticPrint() {
            System.out.println("MyChildInterface static method!!");
        }
    }

    private static class MyClass implements MyInterface {
        public static void myStaticPrint() {
            System.out.println("MyClass static method!!");
        }
    }
}
