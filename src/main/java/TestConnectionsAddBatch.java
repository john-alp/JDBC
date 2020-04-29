import java.sql.*;

/**
 * 28.04.2020
 * Statement используется для выполнения SQL-запросов. Существует три типа класса Statement,
 *  которые являются как бы контейнерами для выполнения SQL-выражений через установленное соединение:
 *
 * Statement, базовый;
 * PreparedStatement, наследующий от Statement;
 * CallableStatement, наследующий от PreparedStatement.
 */

public class TestConnectionsAddBatch {
    public static void main(String[] args) throws ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        String url = "jdbc:mysql://localhost:3306/db" +
                "?verifyServerCertificate=false" +
                "&useSSL=false" +
                "&requireSSL=false" +
                "&useLegacyDatetimeCode=false" +
                "&amp" +
                "&serverTimezone=UTC";
        /*
        Подключаемся к нашей БД
         */
        try {
            Connection connection = DriverManager.getConnection(url, "root", "root");
            if(!connection.isClosed()) {
                System.out.println("Соединение с Базой Данных установленно! ");
            }
            /*
            Создаем  statement
             */
            Statement statement = connection.createStatement();
            statement.addBatch("INSERT INTO users (name,age,email) value ('Galina',75,'galina@mail.ru')");
            statement.addBatch("INSERT INTO users (name,age,email) value ('Raya',65,'goodTesha@yandex.ru')");
            statement.executeBatch();
            /*
            Проверяем подключен ли наш statement (подключен - false)
             */
            System.out.println(statement.isClosed());
            /*
            executeQuery - только для select
            Метод возвращает объект ResultSet, который содержит все полученные данные. Как эти данные получить?
            В объекте ResultSet итератор устаналивается на позиции перед первой строкой.
            И чтобы переместиться к первой строке (и ко всем последующим) необходимо вызвать метод next().
            Пока в наборе ResultSet есть доступные строки, метод next будет возвращать true.
             */
            ResultSet resultSet = statement.executeQuery("select * from users");
            while (resultSet.next()){
                System.out.println(resultSet.getRow() + " " + resultSet.getInt("id") + " " +
                        resultSet.getString("name") + " " + resultSet.getInt("age") + " " +
                        resultSet.getString("email"));
            }

            if (connection.isClosed()){
                System.out.println("Соединение с Базой Данных закрыто! ");
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

    }
}
