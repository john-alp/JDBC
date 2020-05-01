
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;
import java.util.Calendar;

public class addPeople {
    private static final String URL = "jdbc:mysql://localhost:3306/db" +
            "?verifyServerCertificate=false" +
            "&useSSL=false" +
            "&requireSSL=false" +
            "&useLegacyDatetimeCode=false" +
            "&amp" +
            "&serverTimezone=UTC";
    private static final String NAME = "root";
    private static final String PASS = "root";
    String name = null;
    int age;
    String phone = null;
    //  private static final String INSERT_NEW = "INSERT INTO people VALUES(?,?,?,?)";
    private final String INSERT_NEW = "INSERT INTO users (name, age, email) VALUES (?,?,?)";
    PreparedStatement preparedStatement = null;

    public static void main(String[] args) throws ClassNotFoundException, IOException, SQLException {
        addPeople addPeople = new addPeople();
        System.out.println("Running with DB (c)demiurg ");
        System.out.println("Enter ");

        addPeople.start();
    }
    public void start() throws IOException, SQLException, ClassNotFoundException {
        addPeople addPeople = new addPeople();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Input please name: ");
        name = bufferedReader.readLine();
        System.out.println("Input age: ");
        age = Integer.valueOf(bufferedReader.readLine());
        System.out.println("Enter phone: ");
        phone = bufferedReader.readLine();
        addPeople.input(name,age,phone);
    }
    public void input(String name, int age, String phone) throws SQLException, ClassNotFoundException {
        this.name = name;
        this.age = age;
        this.phone = phone;
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection(URL, NAME, PASS);
        Statement statement = connection.createStatement();

        preparedStatement = connection.prepareStatement(INSERT_NEW);
        preparedStatement.setString(1, name);
        preparedStatement.setInt(2, age);
        preparedStatement.setString(3, phone);
        preparedStatement.execute();
        // }
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
        System.out.println("num : index : name : age : phone");
        while (resultSet.next()) {
            int row = resultSet.getRow();
            int id = resultSet.getInt("id");
            String name2 = resultSet.getString("name");
            int age2 = resultSet.getInt("age");
            String phone2 = resultSet.getString("email");

            System.out.println(row + "    :    " + id + "  " + name2 + "  " + age2 + "  " + phone2);
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
//            statement.clearBatch();
//
//            statement.addBatch("delete from users where name='Ggg'");
//            statement.addBatch("delete from users where name='Bbb'");
//            statement.addBatch("delete from users where name='Aaa'");
//            statement.executeBatch();

//        } catch (SQLException e) {
//            System.out.println(e);
//        }
    }
}

