package file_to_stagging;

import connect.DatabaseConnector;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class FileToStagging {
    public static void main(String[] args) {

        try {
            // Load the HTML file using jsoup
            File input = new File("D:\\WH\\data_20231129.txt");
            Document doc = Jsoup.parse(input, "UTF-8");

            Connection connection = DatabaseConnector.connect();
            String ngay = doc.select("td.ngay").text();
            Elements rightTables = doc.select("table.rightcl");
            for (Element rightTable : rightTables) {
//                lấy giá trị tỉnh
                String tinh = rightTable.select("td.tinh").text();
//                lấy giá trị mã tỉnh
                String maTinh = rightTable.select("td.matinh span").text();
//                    lấy giá trị của kết quả
                Elements rsElements = rightTable.select("td[class^=giai]");

                for (Element rsElement : rsElements) {
                    System.out.println(rsElement.className());
                    String giai = rsElement.className();
                    String rsValue = rsElement.text();


                    // thêm dl vào MySQL
                    String query = "INSERT INTO KETQUA (rs, tinh, maTinh, ngay, giai) VALUES (?, ?, ?, ?, ?)";
                    try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                        preparedStatement.setString(1, rsValue);
                        preparedStatement.setString(2, tinh);
                        preparedStatement.setString(3, maTinh);
                        preparedStatement.setString(4, ngay);
                        preparedStatement.setString(5, giai);
                        preparedStatement.executeUpdate();
                    }

                }

            }

            // Close the database connection
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
