<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="pl" class="h-100">
<head>
    <meta charset="UTF-8">
    <title>Books</title>
    <link rel="stylesheet" href="../css/style.css">
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="../js/booksHome.js" type="text/javascript"></script>
</head>
<body>
<form id="addBook" method="post" class="bookForm" data-url="books/">
    <input type="text" placeholder="Podaj tytuł książki" id="addBookTitle"/>
    <input type="text" placeholder="Podaj autora" id="addBookAuthor"/>
    <input type="text" placeholder="Podaj wydawcę" id="addBookPublisher"/>
    <input type="text" placeholder="Podaj ISBN książki" id="addBookIsbn"/>
    <input type="text" placeholder="Podaj typ książki" id="addBookType"/>
    <input type='submit' data-method="post" value='Dodaj'>
</form>
<br>
<div class="bookContainer" data-method="GET">
    <table class="redTable">

    </table>
</div>
</body>
</html>