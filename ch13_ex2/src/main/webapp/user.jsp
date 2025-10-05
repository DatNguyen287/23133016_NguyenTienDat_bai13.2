<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>User Admin - Update User</title>
    <link rel="stylesheet" href="styles/user.css" type="text/css"/>
</head>
<body>
    <h1>Update User</h1>
    <p>NOTE: You can't update the email address.</p>
    
    <form action="userAdmin" method="post">
        <input type="hidden" name="action" value="update_user">        
        <!-- email hiển thị nhưng disable, đồng thời vẫn có hidden để gửi -->
        <label class="pad_top">Email:</label>
        <input type="email" value="${user.email}" disabled><br>
        <input type="hidden" name="email" value="${user.email}">
        
        <label class="pad_top">First Name:</label>
        <input type="text" name="firstName" value="${user.firstName}" required><br>
        
        <label class="pad_top">Last Name:</label>
        <input type="text" name="lastName" value="${user.lastName}" required><br>        
        
        <label>&nbsp;</label>
        <input type="submit" value="Update" class="margin_left">
    </form>
</body>
</html>
