package fr.arcelormittal.Helpers;

import fr.arcelormittal.Managers.DAOManager;
import fr.arcelormittal.Models.Stand;
import fr.arcelormittal.Models.User;

import java.io.IOException;
import java.sql.*;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ApplicationHelper {

    private static final Logger LOGGER = LoggerFactory.getLogger(ApplicationHelper.class);

    public static User getUser(String mail, String hPassword) throws IOException, SQLException {
        DAOManager connect = DAOManager.getInstance();
        Connection connectionDB = connect.getConnection();
        User user = null;

        PreparedStatement pStmt = connectionDB.prepareStatement("SELECT userID, userName, userRole " +
                                                                    "FROM USERS WHERE userMail = ? " +
                                                                    "AND hPwd = ?;");
        pStmt.setString(1,mail);
        pStmt.setString(2,hPassword);
        ResultSet result = pStmt.executeQuery();
        boolean next = result.next();
        user = new User(result.getInt("userID"),
                result.getString("userName"),
                mail, hPassword,
                result.getInt("userRole"));
        return user;
    }

    public static void deleteUser(User user) throws IOException {
        DAOManager.getInstance().deleteUser(user.getId());
        LOGGER.info("Utilisateur {} a été supprimé",user.getId());
    }

    public static void updateUser(User user){
        try {
            DAOManager.getInstance().updateUser(user.getId(), user.getName(), user.getEmail(), user.getPassword(), user.getRole());
            LOGGER.info("Utilisateur {} a été modifié",user.getId());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<User> getUsers() throws IOException {
        return DAOManager.getInstance().getUsers();
    }

    public static List<Stand> getStands() throws IOException {
        return DAOManager.getInstance().getStands();
    }

}
