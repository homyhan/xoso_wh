package model;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.FileWriter;
import java.io.IOException;


import java.io.BufferedWriter;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;
public class Data {

    public static void main(String[] args) {
        String url = "https://www.minhngoc.net.vn/xo-so-mien-nam.html";
        String filePath = "D:\\Nam4ki1\\WH\\sourceMN.txt";

        try {
            // Kết nối và lấy dữ liệu từ trang web
            Document document = Jsoup.connect(url).get();

            // Lấy toàn bộ cấu trúc HTML của trang và lưu vào file
            String htmlStructure = document.outerHtml();
            saveToFile(htmlStructure, filePath);
            System.out.println("Cấu trúc HTML của trang đã được lưu vào file: " + filePath);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void saveToFile(String data, String filePath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
//    public static void main(String[] args) {
//        String url = "https://www.minhngoc.net.vn/"; // Thay thế bằng URL của trang web bạn muốn lấy dữ liệu
//        String outputFile = "D:\\Nam4ki1\\WH\\data.txt"; // Thay thế bằng tên file bạn muốn lưu dữ liệu vào
//
//        try {
//            String htmlContent = fetchDataFromUrl(url);
//            saveDataToFile(htmlContent, outputFile);
//            System.out.println("Dữ liệu đã được lấy và lưu vào file: " + outputFile);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    private static String fetchDataFromUrl(String urlString) throws IOException {
//        URL url = new URL(urlString);
//        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
//        connection.setRequestMethod("GET");
//
//        // Đọc nội dung từ InputStream của kết nối
//        try (Scanner scanner = new Scanner(connection.getInputStream(), "UTF-8")) {
//            scanner.useDelimiter("\\A"); // Đọc hết nội dung
//            return scanner.hasNext() ? scanner.next() : "";
//        }
//    }
//
//    private static void saveDataToFile(String data, String fileName) throws IOException {
//        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
//            writer.write(data);
//        }
//    }
}
