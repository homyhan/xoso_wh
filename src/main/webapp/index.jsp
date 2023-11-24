<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Xổ Số Trực Tuyến</title>
    <style>
        header {
            background-color: darkred;
            color: white;
            padding: 10px;
            text-align: center;
        }

        /* Footer styles */
        footer {
            background-color: darkred;
            color: white;
            padding: 10px;
            text-align: center;
            /*position: fixed;*/
            bottom: 0;
            width: 100%;
        }
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f2f2f2;
        }

        #container {
            display: flex;
            height: auto; /* Chiều cao của màn hình */
        }

        #sidebar {
            width: 25%;
            background-color: white;
            color: black;
            padding: 20px;
        }

        #main-content {
            flex: 1;
            padding: 20px;
            text-align: center; /* Canh giữa nội dung trong cột 3/4 */
        }

        h1 {
            color: #333;
        }

        img {
            width: 100%; /* Hình ảnh không vượt quá chiều rộng của cha */
            height: 100px; /* Đặt độ cao của hình ảnh là 100px */
            display: block; /* Loại bỏ khoảng trắng dưới hình ảnh */
            margin: 0 auto; /* Canh giữa hình ảnh trong cột */
        }

        .lottery-bar{
            border-collapse: collapse;
            width: 100%;
            margin-top: 20px;
            background-color: red;
        }
        .lottery-bar th{
            border: 1px solid black;
            padding: 8px;
            text-align: center;
        }
        .lottery-results {
            border-collapse: collapse;
            width: 100%;
            margin-top: 20px;
        }

        .lottery-results td, .lottery-results th {
            border: 1px solid black;
            padding: 8px;
            text-align: center;
        }

        .lottery-results th {
            background-color: white;
            /*color: black;*/
        }
        .lottery-provinces p {
            padding-left: 25px;
            font-size: 15px;
        }

        .eighth{
            font-size: 40px;
            color: red;
        }
        .special{
            font-size: 40px;
            color: red;
        }
    </style>
</head>
<body>
<header>
    <h2>Xổ Số Trực Tuyến</h2>
</header>
<div id="container">
    <div id="sidebar">
        <table class="lottery-bar">
            <thead>
            <tr>
                <th>Kết quả xổ số miền nam ngày....</th>
            </tr>
            </thead>
        </table>
        <h3>Kết quả xổ số Miền Nam</h3>
        <div class="lottery-provinces">
            <a href="provinces.jsp"><p>Xổ số Bến Tre</p></a>
            <a href=""><p>Xổ số Vĩnh Long</p></a>
            <a href=""><p>Xổ số Tiền Giang</p></a>
            <a href=""><p>Xổ số Long An</p></a>
            <a href=""><p>Xổ số Trà Vinh</p></a>
        </div>
        <!-- Các menu khác có thể được thêm vào đây -->
    </div>
    <div id="main-content">
        <img src="/img/topbg.jpg" alt="Xổ Số Hôm Nay">
        <h1>Xổ Số Hôm Nay</h1>
        <table class="lottery-results">
            <thead>
            <tr>
                <th colspan="4" class="center-text">Kết quả xổ số miền nam ngày....</th>
            </tr>
            <tr>
                <th>Thứ</th>
                <th>Bến Tre</th>
                <th>Vĩnh Long</th>
                <th>Tiền Giang</th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <th>Giải tám</th>
                <th class="eighth">71</th>
                <th class="eighth">64</th>
                <th class="eighth">63</th>
            </tr>
            <tr>
                <th>Giải bảy</th>
                <th>244</th>
                <th>178</th>
                <th>795</th>
            </tr>
            <tr>
                <th rowspan="3">Giải sáu</th>
                <th>244</th>
                <th>178</th>
                <th>795</th>
            </tr>
            <tr>
                <th>245</th>
                <th>134</th>
                <th>248</th>
            </tr>
            <tr>
                <th>248</th>
                <th>496</th>
                <th>428</th>
            </tr>
            <tr>
                <th>Giải năm</th>
                <th>2442</th>
                <th>1784</th>
                <th>7958</th>
            </tr>
            <tr>
                <th rowspan="7">Giải tư</th>
                <th>24423</th>
                <th>17842</th>
                <th>79523</th>
            </tr>
            <tr>
                <th>24423</th>
                <th>17842</th>
                <th>79523</th>
            </tr>
            <tr>
                <th>24423</th>
                <th>17842</th>
                <th>79523</th>
            </tr>
            <tr>
                <th>24423</th>
                <th>17842</th>
                <th>79523</th>
            </tr>
            <tr>
                <th>24423</th>
                <th>17842</th>
                <th>79523</th>
            </tr>
            <tr>
                <th>24423</th>
                <th>17842</th>
                <th>79523</th>
            </tr>
            <tr>
                <th>24423</th>
                <th>17842</th>
                <th>79523</th>
            </tr>
            <tr>
                <th rowspan="2">Giải ba</th>
                <th>24423</th>
                <th>17842</th>
                <th>79523</th>
            </tr>
            <tr>
                <th>24423</th>
                <th>17842</th>
                <th>79523</th>
            </tr>
            <tr>
                <th>Giải nhì</th>
                <th>24423</th>
                <th>17842</th>
                <th>79523</th>
            </tr>
            <tr>
                <th>Giải nhất</th>
                <th>24423</th>
                <th>17842</th>
                <th>79523</th>
            </tr>
            <tr>
                <th>Giải đặc biệt</th>
                <th class="special">214423</th>
                <th class="special">127842</th>
                <th class="special">792523</th>
            </tr>
            <!-- Các dòng kết quả xổ số sẽ được thêm vào đây -->
            </tbody>
        </table>
    </div>
</div>
<footer>
    <p>&copy; 2023 Xổ Số Trực Tuyến. All rights reserved.</p>
</footer>
</body>
</html>
