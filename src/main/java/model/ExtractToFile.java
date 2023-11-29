package model;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.io.File;

public class ExtractToFile {
    public static void main(String[] args) {
        try {
            // Kết nối đến trang web và lấy HTML
            String url = "https://www.minhngoc.net.vn/xo-so-truc-tiep/mien-nam.html"; // Thay đổi địa chỉ trang web cần lấy dữ liệu
            Document document = Jsoup.connect(url).get();

            // Lấy ngày tháng năm hiện tại
            LocalDate currentDate = LocalDate.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
            String formattedDate = currentDate.format(formatter);

            // Lấy bảng có class là "bkqmiennam"
            Element table = document;

            // Kiểm tra xem có bảng không
            if (table != null) {
                // Tạo tên tệp với ngày tháng năm
                String fileName = "data_" + formattedDate + ".txt";

                // Đường dẫn tới thư mục lưu trữ
                String directoryPath = "D:\\WH\\";

                // Tạo đối tượng File cho đường dẫn đầy đủ
                FileWriter fileWriter = new FileWriter(new File(directoryPath + fileName));

                // Lưu nội dung bảng vào tệp
                fileWriter.write(table.outerHtml());
                fileWriter.close();

                System.out.println("Dữ liệu từ bảng đã được lưu vào tệp " + fileName);
            } else {
                System.out.println("Không tìm thấy bảng có class là 'bkqmiennam'.");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
