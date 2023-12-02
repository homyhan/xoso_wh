package staging_to_dw;

import connect.DatabaseConnector;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StagingToDW {
    public static void main(String[] args) {
        String dwUrl = "jdbc:mysql://localhost/dw";
        String dwUsername = "root";
        String dwPassword = "";

        try (
                Connection stagingConn = DatabaseConnector.connect();
                Connection dwConn = DriverManager.getConnection(dwUrl, dwUsername, dwPassword)
        ) {

            String latestNgay = getLatestNgay(stagingConn);

            if (latestNgay != null) {
                processAndInsertData(stagingConn, dwConn, latestNgay);
            }

            System.out.println("Dữ liệu đã được chuyển thành công.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static String getLatestNgay(Connection connection) throws SQLException {
        String latestDateQuery = "SELECT MAX(ngay) AS latestNgay FROM KETQUA";
        try (PreparedStatement latestDateSta = connection.prepareStatement(latestDateQuery);
             ResultSet latestDateRS = latestDateSta.executeQuery()) {
            return latestDateRS.next() ? latestDateRS.getString("latestNgay") : null;
        }
    }

    public static void processAndInsertData(Connection stagingConn, Connection dwConn, String latestNgay) throws SQLException {
        String selectQuery = "SELECT * FROM KETQUA WHERE ngay = ?";
        try (PreparedStatement selectStatement = stagingConn.prepareStatement(selectQuery)) {
            selectStatement.setString(1, latestNgay);
            try (ResultSet resultSet = selectStatement.executeQuery()) {
                while (resultSet.next()) {
                    String ngay = resultSet.getString("ngay");
                    String tinh = resultSet.getString("tinh");
                    String maTinh = resultSet.getString("maTinh");
                    String giai = resultSet.getString("giai");
                    String rs = resultSet.getString("rs");

                    if (!checkDataExists(dwConn, ngay, maTinh, giai, rs)) {
                        int dateId = getOrInsertDimTime(dwConn, ngay);
                        insertOrUpdateDimStation(dwConn, maTinh, tinh);
                        insertFactXoSo(dwConn, dateId, maTinh, giai, rs);
                    }
                }
            }
        }
    }

    public static boolean checkDataExists(Connection connection, String ngay, String maTinh, String giai, String rs) throws SQLException {
        String checkDataQuery = "SELECT 1 FROM FactXoSo " +
                "WHERE maTinh = ? " +
                "AND giai = ? " +
                "AND rs = ? " +
                "AND date_id = (" +
                "    SELECT id FROM dimTime WHERE dimTime.ngay = ?" +
                ")";
        PreparedStatement checkDataStatement = connection.prepareStatement(checkDataQuery);
        checkDataStatement.setString(1, maTinh);
        checkDataStatement.setString(2, giai);
        checkDataStatement.setString(3, rs);
        checkDataStatement.setString(4, ngay);
        ResultSet checkDataResultSet = checkDataStatement.executeQuery();

        return checkDataResultSet.next();
    }

    public static int getOrInsertDimTime(Connection connection, String ngay) throws SQLException {
        String selectDimTime = "SELECT id FROM dimTime WHERE ngay = ?";
        PreparedStatement selectDimTimeSta = connection.prepareStatement(selectDimTime);
        selectDimTimeSta.setString(1, ngay);
        ResultSet dimTimeRS = selectDimTimeSta.executeQuery();

        if (dimTimeRS.next()) {
            // Ngày đã tồn tại, trả về id của ngày
            return dimTimeRS.getInt("id");
        } else {
            // Ngày chưa tồn tại, chèn ngày mới và trả về id của ngày mới
            String insertDimTime = "INSERT INTO dimTime (ngay) VALUES (?)";
            PreparedStatement insertDimTimeSta = connection.prepareStatement(insertDimTime, PreparedStatement.RETURN_GENERATED_KEYS);
            insertDimTimeSta.setString(1, ngay);
            insertDimTimeSta.executeUpdate();

            ResultSet generatedKeys = insertDimTimeSta.getGeneratedKeys();
            if (generatedKeys.next()) {
                return generatedKeys.getInt(1);
            } else {
                throw new SQLException("Không thành công trong việc lấy khóa đã tạo cho dimTime.");
            }
        }
    }

    public static void insertOrUpdateDimStation(Connection connection, String maTinh, String tenTinh) throws SQLException {
        String selectDimStation = "SELECT maTinh FROM dimStation WHERE maTinh = ?";
        PreparedStatement selectDimStationSta = connection.prepareStatement(selectDimStation);
        selectDimStationSta.setString(1, maTinh);
        ResultSet dimStationRS = selectDimStationSta.executeQuery();

        if (!dimStationRS.next()) {
            // Mã tỉnh chưa tồn tại, chèn mới
            String insertDimStation = "INSERT INTO dimStation (maTinh, tenTinh) VALUES (?, ?)";
            PreparedStatement insertDimStationSta = connection.prepareStatement(insertDimStation);
            insertDimStationSta.setString(1, maTinh);
            insertDimStationSta.setString(2, tenTinh);
            insertDimStationSta.executeUpdate();
        }
        // Nếu mã tỉnh đã tồn tại, không cần thực hiện gì vì đã tồn tại
    }

    public static void insertFactXoSo(Connection connection, int dateId, String maTinh, String giai, String rs) throws SQLException {
        String insertFactXoSoQuery = "INSERT INTO FactXoSo (date_id, maTinh, giai, rs) VALUES (?, ?, ?, ?)";
        try (PreparedStatement insertFactXoSoSta = connection.prepareStatement(insertFactXoSoQuery)) {
            insertFactXoSoSta.setInt(1, dateId);
            insertFactXoSoSta.setString(2, maTinh);
            insertFactXoSoSta.setString(3, giai);
            insertFactXoSoSta.setString(4, rs);
            insertFactXoSoSta.executeUpdate();
        }
    }
}
