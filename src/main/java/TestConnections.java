import java.sql.*;

public class TestConnections {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        String url = "jdbc:mysql://localhost:3306/db" +
                "?verifyServerCertificate=false" +
                "&useSSL=false" +
                "&requireSSL=false" +
                "&useLegacyDatetimeCode=false" +
                "&amp" +
                "&serverTimezone=UTC";
        try {
            Connection connection = DriverManager.getConnection(url, "root", "root");
            if(!connection.isClosed()) {
                System.out.println("Соединение с Базой Данных установленно! ");
            }
            /*
            Создаем  statement
             */
            Statement statement = connection.createStatement();
            //statement.execute("insert into users (name, age, email) values ('grandthaver_pihto',101,'pihto@mail.ru')");
            int res = statement.executeUpdate("UPDATE users SET name='Grangfather Pihto' WHERE id=6;");
            System.out.println(res);
            //ResultSet resultSet = statement.executeQuery("select * from users");
            /*
            Проверяем подключение к БД (подключена - false)
            executeUpdate - insert, update, delete  возвращает колличество изменений
            int res = statement.executeUpdate("UPDATE users SET name='Grangfather Pihto' WHERE id=6;");
             */

            System.out.println(statement.isClosed());
            /*
            executeQuery - только для select
             */
            ResultSet resultSet = statement.executeQuery("select * from users");
            while (resultSet.next()){
                System.out.println(resultSet.getRow() + " " + resultSet.getInt("id") + " " +
                        resultSet.getString("name") + " " + resultSet.getInt("age") + " " +
                        resultSet.getString("email"));
            }
            //  statement.execute("insert into users (name, age, email) values ('Kroko', 90, 'kroko@mail.ru')");

            if (connection.isClosed()){
                System.out.println("Соединение с Базой Данных закрыто! ");
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

    }

}
