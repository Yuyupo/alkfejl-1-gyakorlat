<html>
<body>
<!-- action = Melyik servletet fogja hasznalni -->
<!-- method = GET/POST azt a doGet vagy doPost metodust fogja meghivni -->
<form action="HelloWorldServlet" method="get">
    <!-- type = milyen tiusu inputot var -->
    <!-- name = valtozo nev -->
    First Name: <input type="text" name="first_name">
    <br/>
    Last Name: <input type="text" name="last_name" />
    <input type="submit" value="Submit" />
</form>

<!-- Bejelentkezes, ami a LoginServlet-t hasznalja -->
<form action="LoginServlet" method="post">
    Name:<input type="text" name="userName"/><br/>
    Password:<input type="password" name="userPass"/><br/>
    <input type="submit" value="Login"/>
</form>

<form action="servlet1" method="get">
    Load servlet 1: <input type="submit" value="Load">
</form>
</body>
</html>