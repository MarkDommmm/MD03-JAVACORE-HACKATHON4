import Model.Book;

import java.sql.SQLOutput;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;


public class Main {
    private static final int Max_Books = 100;
    private static Book[] bookList = new Book[Max_Books];
    private static int bookCount = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choise;
        do {
            System.out.println("****************JAVA-HACKATHON-05-BASIC-MENU***************");
            System.out.println("1. Nhập số lượng sách thêm mới và nhập thông tin cho từng cuốn sách");
            System.out.println("2. Hiển thị thông tin tất cả sách trong thư viện");
            System.out.println("3. Sắp xếp sách theo lợi nhuận tăng dần");
            System.out.println("4. Xóa sách theo mã sách");
            System.out.println("5. Tìm kiếm tương đối sách theo tên sách hoặc mô tả");
            System.out.println("6. Thay đổi thông tin sách theo mã sách");
            System.out.println("7. Thoát");
            System.out.print("Chọn một chức năng (1-7): ");
            choise = scanner.nextInt();
            scanner.nextLine();

            switch (choise) {
                case 1:
                    //Thêm sách mới
                    addBooks(scanner);
                    break;
                case 2:
                    //Hiện thị sách
                    displayBooks();
                    break;
                case 3:
                    //Sắp xếp sách theo lợi nhuận tăng dần
                    sortBooksByProfit();
                    break;
                case 4:
                    //Xóa sách
                    deleteBook(scanner);
                    break;
                case 5:
                    //tìm kiếm sách
                    searchBooks(scanner);
                    break;
                case 6:
                    //Cập nhật sách
                    updateBook(scanner);
                    break;
                case 7:
                    System.out.println("Thoát khỏi chương trình");
                    break;

                default:
                    System.out.println("Xin vui lòng nhập lại");
                    break;

            }
        } while (choise != 7);

    }

    //Thêm sách mới
    private static void addBooks(Scanner scanner) {
        System.out.println("Nhập số lượng cần thêm mới");
        int count = scanner.nextInt();
        scanner.nextLine();
        if (bookCount + count > Max_Books) {
            System.out.println("Danh sách đã đầy");
            return;
        }
        for (int i = 0; i < count; i++) {
            System.out.println("Nhập thông tin cuốn sách thứ " + (i + 1));
            Book book = new Book();
            book.inputData();
            bookList[bookCount++] = book;
            System.out.println("sách đã thêm vào danh sách");
        }
    }

    //Hiện thị sách
    private static void displayBooks() {
        if (bookCount == 0) {
            System.out.println("Danh sách rỗng");
            return;
        }
        for (int i = 0; i < bookCount; i++) {
            System.out.println("thông tin sách thứ" + (i + 1) + ":");
            bookList[i].displayData();
        }
    }

    //Sắp xếp sách theo lợi nhuận tăng dần
    private static void sortBooksByProfit() {
        if (bookCount == 0) {
            System.out.println("Danh sách sách rỗng");
            return;
        }
        Arrays.sort(bookList, 0, bookCount, Comparator.comparingDouble(Book::getInterest));
        System.out.println("Danh sách đã sắp xep theo lợi nhuận tăng dần");
        displayBooks();
    }

    //Xóa sách
    private static void deleteBook(Scanner scanner) {
        if (bookCount == 0) {
            System.out.println("danh sách sách rỗng");
            return;
        }
        System.out.println("Nhập ID sách cần xóa");
        int bookId = scanner.nextInt();
        scanner.nextLine();

        int index = findBookIndex(bookId);
        if (index == -1) {
            System.out.println("Không tìm thấy ID sách " + bookId + ".");
            return;
        }
        for (int i = index; i < bookCount; i++) {
            bookList[i] = bookList[i + 1];
        }
        bookList[bookCount - 1] = null;
        bookCount--;
        System.out.println("Sách có mã " + bookId + "đã được xóa khỏi danh sách");
    }

    //Cập nhật sách
    private static void updateBook(Scanner scanner) {
        if (bookCount == 0) {
            System.out.println("Danh sách sách rỗng!");
            return;
        }
        System.out.println("Nhập ID sách cần cập nhật!");
        int bookId = scanner.nextInt();
        scanner.nextLine();
        int index = findBookIndex(bookId);
        if (index == -1) {
            System.out.println("Không tim thấy ID sách cần update " + bookId + ".");
            return;
        }
        System.out.println("Nhập thông tin cập nhật cho sách có ID " + bookId);
        Book book = bookList[index];
        book.inputData();
        System.out.println("Sách đã cập nhật!!!");
        book.displayData();
    }

    private static void searchBooks(Scanner scanner) {
        if (bookCount == 0) {
            System.out.println("Danh sách sách rỗng");
        }
        System.out.println("Nhập sách tìm kiếm");
        String search = scanner.nextLine();
        int count = 0;
        for (int i = 0; i < bookCount; i++) {
            Book book = bookList[i];
            if (book.getBookName().contains(search) || book.getDescriptions().contains(search)) {
                System.out.println("Thông tin sách thứ " + (i + 1) + ":");
                book.displayData();
                count++;
            }
        }
        if (count == 0) {
            System.out.println("KHông tìm thấy sách phù hợp với bạn muốn tìm kiếm");
        } else {
            System.out.println("tìm thấy tổng cộng" + count + "Sách phù hợp với tìm kiếm");
        }
    }

    //Tìm index của sách
    private static int findBookIndex(int bookId) {
        for (int i = 0; i < bookCount; i++) {
            if (bookList[i].getBookId() == bookId) {
                return i;
            }
        }
        return -1;
    }
}