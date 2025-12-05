package repository;

import config.ApplicationContext;
import models.User;

import java.sql.*;

public class UserRepository  {

    private final Connection connection;

//    connection az singleton miad pas nemidim inja be constructur
    public UserRepository() {
        this.connection = ApplicationContext.getInstance().getConnection();
    }


    public User save(User user) {
        String sql = "INSERT INTO users (username, password) VALUES (?, ?)";

//        باید id رو از دیتابیس بگیره

//        PreparedStatement:
//        یه وسیله است برای فرستادن SQL
//        بعد از INSER بیا id تولیدشده رو برگردون
        try (PreparedStatement pps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) { // هرجا نیاز بود خودش close رو انجام می ده

            pps.setString(1, user.getUsername());
            pps.setString(2, user.getPassword());

            pps.executeUpdate();  // SQL اجرا بشه ---> users تو ذخیره بشه

//          برای INSERTهایی هست که ID auto-increment دارن
//          جدول نتایج
            ResultSet rs = pps.getGeneratedKeys(); // یه جدول تولید می کنه که اینجا فقط یه ستون داره که همون id تولید شده هست

//          برای خواندن دادهٔ جدول (داده‌هایی که SELECT برگردوند)
//          ResultSet rs = pps.executeQuery();

//          قبل از rs.next() اشاره‌گر روی قبل از ردیف اول قرار داره یعنی هنوز به داده‌ها دسترسی نداریم
//          تا وقتی next() رو صدا نزنیم اشاره‌گر روی قبل از اولین سطر است
            if (rs.next()) {
                user.setId(rs.getLong(1));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return user;
    }

    public boolean existsByUsername(String username) {

//      هر سطری پیدا کردی، مقدار ستونش رو عدد 1 بذار
        String sql = "SELECT 1 FROM users WHERE username = ?";

        try(PreparedStatement pps = connection.prepareStatement(sql)) {

            pps.setString(1, username);

            ResultSet rs = pps.executeQuery();

            if (rs.next()) {
                return true;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return false;
    }

    public User findByUsername(String username) {
        String sql = "SELECT id, username, password FROM users WHERE username = ?";

        try(PreparedStatement pps = connection.prepareStatement(sql)) {

            pps.setString(1, username);

            ResultSet rs = pps.executeQuery();


            if (rs.next()) {
                User user = new User();

                user.setId(rs.getLong("id"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));

                return user;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return null;
    }
}
