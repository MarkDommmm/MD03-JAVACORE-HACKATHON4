package Model;

import java.util.Scanner;

public class Book {
    private static int nextBookId = 1;
    private int bookId;
    private String bookName;
    private String author;
    private String descriptions;
    private double importPrice;
    private double exportPrice;
    private float interest;
    private boolean bookStatus;

    public Book() {
        this.bookId = nextBookId++;
        this.bookStatus = true;

    }

    public Book(int bookId, String bookName,String descriptions, String author, double importPrice, double exportPrice, float interest, boolean bookStatus) {
        this.bookId = bookId;
        this.bookName = bookName;
        this.author = author;
        this.importPrice = importPrice;
        this.exportPrice = exportPrice;
        this.interest = interest;
        this.bookStatus = bookStatus;
        calculateInterest();
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getDescriptions() {
        return descriptions;
    }

    public void setDescriptions(String descriptions) {
        this.descriptions = descriptions;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public double getImportPrice() {
        return importPrice;
    }

    public void setImportPrice(double importPrice) {
        this.importPrice = importPrice;
    }

    public double getExportPrice() {
        return exportPrice;
    }

    public void setExportPrice(double exportPrice) {
        this.exportPrice = exportPrice;
    }

    public void setBookStatus(boolean bookStatus) {
        this.bookStatus = bookStatus;
    }

    public boolean isBookStatus() {
        return bookStatus;
    }

    public float getInterest() {
        return interest;
    }

    public void setInterest(float interest) {
        this.interest = interest;
    }

    public  void inputData(){
        Scanner scanner = new Scanner(System.in);

        System.out.println("Nhập tên sách: ");
        bookName = scanner.nextLine();

        System.out.println("Nhập tên tác giả: ");
        author = scanner.nextLine();

        System.out.println("Nhập mô tả về sách (ít nhất 10 ký tự)");
        descriptions = scanner.nextLine();

        while (descriptions.length() < 10){
            System.out.println("Mo tả sách phải ít nhất 10 ký tự");
            descriptions = scanner.nextLine();
        }
        System.out.println("Nhập giá nhập vào: ");
        importPrice = scanner.nextDouble();

        while (importPrice <=0){
            System.out.println("Giá nhập vào phải lớn hơn 0");
            importPrice = scanner.nextDouble();
        }
        System.out.println("Nhập giá xuất ( phải lớn hơn 1.2 lần giá nhập)");
        exportPrice = scanner.nextDouble();
        while (exportPrice <= importPrice *1.2){
            System.out.println("Giá xuất phải lơn hơn 1.2 lần giá nhập");
            exportPrice  = scanner.nextDouble();
        }
        calculateInterest();
    }
    private  void  calculateInterest(){
        interest = (float) (exportPrice - importPrice);
    }
    public  void displayData(){
        System.out.println("Mã sách: " + bookId);
        System.out.println("Tên sách: " + bookName);
        System.out.println("Tác giả "+ author);
        System.out.println("Mô tả về sách " + descriptions);
        System.out.println("Giá nhập " + importPrice);
        System.out.println("Giá xuất " + exportPrice);
        System.out.println("Lợi nhuận " + interest);
        System.out.println("Trạng thái " + (bookStatus ? " Có sẳn":"Không sẳn"));
    }
}
