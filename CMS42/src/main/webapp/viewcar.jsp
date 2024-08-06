<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Car List - CarSpot</title>
    <!-- Bootstrap CSS for better table styling -->
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <!-- Google Fonts -->
    <link href="https://fonts.googleapis.com/css2?family=Lobster&family=Roboto:wght@700&family=Cormorant+Infant:wght@600&display=swap" rel="stylesheet">
    <!-- Custom CSS -->
    <style>
        @font-face {
            font-family: 'Footlight MT Light';
            src: url('<%= request.getContextPath() %>/path/to/FootlightMTLight.ttf') format('truetype'); /* Update the path to your Footlight MT Light font file */
        }
        @font-face {
            font-family: 'Comic Sans MS';
            src: url('<%= request.getContextPath() %>/path/to/ComicSansMS.ttf') format('truetype'); /* Update the path to your Comic Sans MS font file */
        }
        @font-face {
            font-family: 'Bradley Hand ITC';
            src: url('<%= request.getContextPath() %>/path/to/BradleyHandITC.ttf') format('truetype'); /* Update the path to your Bradley Hand ITC font file */
        }
        body {
            background-image: url('<%=request.getContextPath()%>/images/crudbackground.jpg');
            background-size: cover;
            background-repeat: no-repeat;
            background-attachment: fixed;
            color: white;
        }
        .container {
            margin-top: 50px;
            background: rgba(0, 0, 0, 0.6); /* Dark semi-transparent background */
            padding: 20px;
            border-radius: 15px; /* Rounded square shape */
        }
        h2 {
            font-family: 'Footlight MT Light', sans-serif;
            font-size: 32px;
            text-align: center;
            color: #f8f9fa;
        }
        .table {
            background: rgba(255, 255, 255, 0.1);
            color: white;
        }
        .table th, .table td {
            font-family: 'Comic Sans MS', sans-serif;
            border-color: #444;
        }
        .thead-dark th {
            background-color: #9b59b6;
            border-color: #8e44ad;
        }
        .thead-dark th, .table td {
            text-align: center;
        }
        img {
            border-radius: 5px;
            width: 100px;
            height: auto;
        }
        .no-image {
            font-family: 'Bradley Hand ITC', sans-serif;
        }
        .btn-purple {
            background-color: #9b59b6;
            border-color: #9b59b6;
            color: white;
            font-family: 'Cormorant Infant', serif;
            font-weight: 600; /* SemiBold */
            margin-top: 20px;
        }
        .btn-purple:hover {
            background-color: #8e44ad;
            border-color: #8e44ad;
        }
    </style>
</head>
<body>
    <%@ page import="java.util.*" %>
    <%@ page import="com.model.*" %>
    <%
        @SuppressWarnings("unchecked") 
        List<Car> cars = (List<Car>) request.getAttribute("list");
    %>
    <div class="container mt-4">
        <h2 class="mb-4">Car List</h2>
        <table class="table table-bordered">
            <thead class="thead-dark">
                <tr>
                    <th>Car ID</th>
                    <th>Car Model</th>
                    <th>Car Price</th>
                    <th>Car Image</th>
                </tr>
            </thead>
            <tbody>
                <% if (cars != null && !cars.isEmpty()) { 
                    for(Car car : cars) { %>
                <tr>
                    <td><%= car.getCarid() %></td>
                    <td><%= car.getCarmodel() %></td>
                    <td><%= car.getCarprice() %></td>
                    <td>
                        <% 
                            if (car.getCarimage() != null) { 
                                byte[] imgData = car.getCarimage().getBytes(1, (int) car.getCarimage().length()); 
                                String base64Image = Base64.getEncoder().encodeToString(imgData);
                        %>
                            <img src="data:image/jpeg;base64,<%= base64Image %>" alt="Car Image"/>
                        <% } else { %>
                            <span class="no-image">No Image</span>
                        <% } %>
                    </td>
                </tr>
                <% } 
                } else { %>
                <tr>
                    <td colspan="4">No cars found</td>
                </tr>
                <% } %>
            </tbody>
        </table>
        <div class="text-center">
            <a href="adminhome.jsp" class="btn btn-purple">Back to Admin Home</a>
        </div>
    </div>
    <!-- Bootstrap JS and dependencies -->
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.2/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
