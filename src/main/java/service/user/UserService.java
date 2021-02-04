package service.user;

import model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserService implements IUserService{
    public static final String SELECT_FROM_USER = "select * from user ";
    public static final String INSERT_INTO_USER_VALUES = "insert into user values (?,?,?)";
    public static final String SELECT_FROM_USER_WHERE_ID = "select * from user where id=?";
    private String url = "jdbc:mysql://localhost:3306/userManage";
    private String user = "root";
    private String password = "ss123123";
    protected Connection getConnection(){
        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(url,
                    user,
                    password
            );
        } catch (ClassNotFoundException e) {
            System.out.println("không có driver");
        } catch (SQLException throwables) {
            System.out.println("Không kết nối được");
        }
        System.out.println("ket noi thanh cong");

        return connection;
    }

    public UserService() {
    }

    @Override
    public List<User> findAll() {
        List<User> userList=new ArrayList<>();
        Connection connection=getConnection();
        try {
            PreparedStatement preparedStatement=connection.prepareStatement(SELECT_FROM_USER);
            ResultSet resultSet=preparedStatement.executeQuery();
            while (resultSet.next()){
                int id = resultSet.getInt("id");
                String name=resultSet.getString("name");
                String email=resultSet.getString("email");
                userList.add(new User(id,name,email));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return userList;
    }

    @Override
    public boolean create(User user) {
        boolean check =false;
        Connection connection=getConnection();
        try {
            PreparedStatement preparedStatement=connection.prepareStatement(
                    INSERT_INTO_USER_VALUES);
            preparedStatement.setInt(1,user.getId());
            preparedStatement.setString(2,user.getName());
            preparedStatement.setString(3,user.getEmail());
            check=preparedStatement.executeUpdate()>0;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return check;
    }

    @Override
    public boolean update(int id,User user) {
        boolean check =false;
        Connection connection=getConnection();
        try {
            PreparedStatement preparedStatement=connection.prepareStatement(
                    "update user set name=?,email=? where id=?;");
            preparedStatement.setInt(3,user.getId());
            preparedStatement.setString(1,user.getName());
            preparedStatement.setString(2,user.getEmail());
            check=preparedStatement.executeUpdate()>0;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return check;
    }
    public User findById(int id) {
        User user= null;
        Connection connection = getConnection();
        try {
            PreparedStatement p = connection.prepareStatement(SELECT_FROM_USER_WHERE_ID);
            p.setInt(1, id);
            ResultSet resultSet = p.executeQuery();
            while (resultSet.next()){
                String name = resultSet.getString("name");
                String email = resultSet.getString("email");
                user = new User(id, name, email);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return user;
    }


    @Override
    public User findByEmail(String email) {
        User user= null;
        Connection connection = getConnection();
        try {
            PreparedStatement p = connection.prepareStatement("select * from user where email=?;");
            p.setString(1, email);
            ResultSet resultSet = p.executeQuery();
            while (resultSet.next()){
                String name = resultSet.getString("name");
                int id = resultSet.getInt("id");
                user = new User(id, name, email);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return user;
    }

    @Override
    public List<User> sort() {
        List<User> userList=new ArrayList<>();
        Connection connection=getConnection();
        try {
            PreparedStatement preparedStatement=connection.prepareStatement("select * from User order by name asc ;");
            ResultSet resultSet=preparedStatement.executeQuery();
            while (resultSet.next()){
                int id = resultSet.getInt("id");
                String name=resultSet.getString("name");
                String email=resultSet.getString("email");
                userList.add(new User(id,name,email));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return userList;
    }

    @Override
    public boolean delete(int id) {
        boolean check =false;
        Connection connection=getConnection();
        try {
            PreparedStatement preparedStatement=connection.prepareStatement(
                    "delete from user where id=?;");
            preparedStatement.setInt(1,id);
            check=preparedStatement.executeUpdate()>0;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return check;
    }
}
