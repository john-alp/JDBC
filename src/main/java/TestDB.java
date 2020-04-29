import java.sql.*;

public class TestDB {
    private static final String URL = "jdbc:mysql://localhost:3306/db" +
            "?verifyServerCertificate=false" +
            "&useSSL=false" +
            "&requireSSL=false" +
            "&useLegacyDatetimeCode=false" +
            "&amp" +
            "&serverTimezone=UTC";
    private static final String NAME = "root";
    private static final String PAS = "root";

    public static void main(String[] args) throws ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        try {
            Connection connection = DriverManager.getConnection(URL, NAME, PAS);
            Statement statement = connection.createStatement();
                /*
                Этот метод универсальный, позволяет делать вставку и получение данных, может получать несколько ResultSet-ов
                 */

            //      statement.execute("insert into users (name, age, email) values ('Galina', 71, 'galina@mail.ru')");

                /*
                Этот метод позволяет выполнять insert, update, delete (получать данные нельзя!)
                 возвращает int - количество записей, сколько он выполнил
                 */
            //      int result = statement.executeUpdate("update users set name = 'Daria' where id=2");
            //      System.out.println(result);

                /*
                Этот метод получает ResultSet, то есть этим методом можно выполнять только select!!
                 */
            ResultSet resultSet = statement.executeQuery("select * from users");
            while (resultSet.next()){
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                int age = resultSet.getInt("age");
                String email = resultSet.getString("email");
                System.out.println(id + " " + name + " " + age + " " + email);
            }
                /*
                Если нужно сделать пакетную вставку или пакетную заявку используем этот метод
                 */
            //      statement.addBatch("insert into users (name, age, email) values ('Ggg', 1, 'ggg@mail.ru')");
            //      statement.addBatch("insert into users (name, age, email) values ('Bbb', 2, 'bbb@mail.ru')");
            //      statement.addBatch("insert into users (name, age, email) values ('Aaa', 3, 'aaa@mail.ru')");

                /*
                Метод statement.clearBatch() - удаляет старые запросы и можно поместить новые
                 */
            statement.clearBatch();

            statement.addBatch("delete from users where name='Ggg'");
            statement.addBatch("delete from users where name='Bbb'");
            statement.addBatch("delete from users where name='Aaa'");
            statement.executeBatch();

        } catch (SQLException e) {
            System.out.println(e);
        }
    }

}
