import java.util.Scanner;

public class BookManagement {
    static Book[] books = new Book[100];
    static int currentIndex = 0;
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        do{
            System.out.println("***********************MENU**************************");
            System.out.println("1. Danh sach sach");
            System.out.println("2. Them moi sach");
            System.out.println("3. Tinh loi nhuan cua cac sach");
            System.out.println("4. Cap nhat sach");
            System.out.println("5. Xoa sach");
            System.out.println("6. Sap xep sach theo loi nhuan tang dan");
            System.out.println("7. Tim kiem sach theo tac gia");
            System.out.println("8. Tim kiem sach theo khoang gia(tim theo khoang gia ban)");
            System.out.println("9. Thong ke sach theo moi tac gia");
            System.out.println("10. Thoat");

            int choice = Integer.parseInt(scanner.nextLine());
            switch(choice){
                case 1:
                    displayBook();
                    break;
                case 2:
                    addBook(scanner);
                    break;
                case 3:
                    caculateInterest(scanner);
                    System.out.println("Da tinh loi nhuan xong");
                    break;
                case 4:
                    updateBook(scanner);
                    break;
                case 5:
                    deleteBookById(scanner);
                    break;
                case 6:
                    sortBooksByInterest();
                    displayBook();
                    break;
                case 7:
                    searchBooksByAuthorName(scanner);
                    break;
                case 8:
                    searchBooksByExportRange(scanner);
                    break;
                case 9:
                    countBookByAuthor(scanner);
                    break;
                case 10:
                    System.exit(0);
                    break;
                    default:
                        System.out.println("Vui long nhap lai");
            }
        }while(true);
    }

    public static void displayBook(){
        System.out.println("Danh sach toan bo sach");
        for (int i = 0; i < currentIndex; i++ ){
            books[i].displayData();
        }
    }
    public static void addBook(Scanner scanner){
        System.out.println("Nhap so sach can them");
        int numberOfBooks = Integer.parseInt(scanner.nextLine());
        for (int i = 0; i < numberOfBooks; i++){
            // khoi tao currentIndex la 1 doi tuong sach
            books[currentIndex] = new Book();

            // goi phuong thuc inputDAta de nhap du lieu cho phan tu tại vị trí currentIndex
            books[currentIndex].inputData(scanner);
            currentIndex++;
        }
    }

    // tinh loi nhuan

    public static void  caculateInterest(Scanner scanner){
        for (int i = 0; i < currentIndex; i++ ){
            books[i].setInterest(books[i].callInterest());
        }
    }

    //Cap nhat sach
    // ham lay ra id cua sach
    public static int getBookById(String bookId){
        for (int i = 0; i < currentIndex; i++ ){
            if (books[i].getBookId().equals(bookId)){
                return i;
            }
        }
        return -1;
    }

    public static void updateBook(Scanner scanner){
        System.out.println("Nhap id sach can cap nhat: ");
        String bookId= scanner.nextLine();
        int index = getBookById(bookId);

            if (index == -1){
                System.out.printf("Khong tim thay sach co ID la %s\n", bookId);
            }else{
                boolean isExit = true;
                do{
                    System.out.println("****************CAP NHAT THONG TIN SACH**********");
                    System.out.println("1. Cap nhat ten sach");
                    System.out.println("2. Cap nhat gia nhap sach");
                    System.out.println("3. Cap nhat gia ban sach");
                    System.out.println("4. Cap nhat tieu de sach");
                    System.out.println("5. Cap nhat tac gia");
                    System.out.println("6. Cap nhat nam xuat ban");
                    System.out.println("7. Thoát");
                    System.out.print("Chọn mục cần cập nhật: ");
                    int choiceUpdate = Integer.parseInt(scanner.nextLine());
                    switch(choiceUpdate){
                        case 1:
                            System.out.println("Nhap vao ten sach moi");
                            books[index].setBookName(scanner.nextLine());
                            break;
                        case 2:
                            System.out.println("Nhap vao gia nhap sach moi");
                            books[index].setImportPrice(Double.parseDouble(scanner.nextLine()));
                            break;
                        case 3:
                            System.out.println("Nhap vao gia ban sach moi");
                            books[index].setExportPrice(Double.parseDouble(scanner.nextLine()));
                            break;
                        case 4:
                            System.out.println("Nhap vao tieu de sach moi");
                            books[index].setTitle(scanner.nextLine());
                            break;
                        case 5:
                            System.out.println("Nhap vao ten tac gia moi");
                            books[index].setAuthor(scanner.nextLine());
                            break;
                        case 6:
                            System.out.println("Nhap vao nam xuat ban");
                            books[index].setYear(Integer.parseInt(scanner.nextLine()));
                            break;
                        case 7:
                            isExit = false;
                            break;
                            default:
                                System.out.println("Chon lai chuc nang");
                    }
                }while(isExit);
            }
    }
    // xoa sach theo id sach
    public static void deleteBookById(Scanner scanner){
        System.out.println("Nhap vao id sach muon xoa");
        String bookId = scanner.nextLine() ;
        int index = getBookById(bookId);
        if (index == -1){
            System.out.println("Khong tim thay sach co id la " + bookId);
        }else {
            for (int i = index; i < currentIndex - 1; i++ ){
                books[index] = books[index + 1];
            }
            currentIndex--;
        }
        System.out.println("xoa thanh cong sach co id" + bookId);
    }
    // sap xem sach theo loi nhuan(dung sap xep noi bot)
    public static void sortBooksByInterest() {
        for (int i = 0; i < currentIndex - 1; i++) {
            for (int j = 0; j < currentIndex - 1 - i; j++) {
                if (books[j].getInterest() > books[j + 1].getInterest()) {
                    Book temp = books[j];
                    books[j] = books[j + 1];
                    books[j + 1] = temp;
                }
            }
        }
    }
    // tim kiem sach theo ten tac gia => tra ve ten sach
    public static void searchBooksByAuthorName(Scanner scanner){
        System.out.println("Nhap vao ten tac gia");
        String input = scanner.nextLine();
        System.out.println("DAnh sach cac sach theo ten tac gia "+input);
        for (int i = 0; i < currentIndex; i++) {
            if (books[i].getAuthor().equals(input)){
                books[i].displayData();
            }
        }
    }
    public static void searchBooksByExportRange(Scanner scanner){
        System.out.println("Nhap vao start");
        double start = Double.parseDouble(scanner.nextLine());
        System.out.println("Nhap vao end");
        double end = Double.parseDouble(scanner.nextLine());

        System.out.printf("Danh sach cac sach trong khoang gia tu %f den %f la \n", start, end);
        for(int i= 0; i < currentIndex; i++){
            if(books[i].getExportPrice() >= start && books[i].getExportPrice() <= end){
                books[i].displayData();
            }
        }
    }
    public static void countBookByAuthor(Scanner scanner){
        System.out.println("Nhap vao ten tac gia");
        String input = scanner.nextLine();
        int count = 0;
        for (int i = 0; i < currentIndex; i++) {
            if (books[i].getAuthor().equals(input)){
                count ++;
            }
        }
        System.out.printf("Tac gia %s co %d quyen sach", input, count);
    }
}
