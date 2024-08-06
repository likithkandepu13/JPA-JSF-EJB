<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Add Item to Stock</title>
</head>
<body>
<h1>Add Item to Stock</h1>
<form action="AddItemsServlet" method="post">
    Item ID: <input type="text" name="item_id" required><br><br>
    Item Name: <input type="text" name="item_name" required><br><br>
    Buyer Price: <input type="text" name="buyer_price" required><br><br>
    Seller Price: <input type="text" name="seller_price" required><br><br>
    Date of Expiry: <input type="date" name="expiry_date" required><br><br>
    Number of Stocks: <input type="number" name="no_of_stocks" required><br><br>
    <input type="submit" value="Add Item">
</form>
</body>
</html>
