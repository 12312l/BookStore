package com.javanc.controller;

import com.javanc.DTO.DataDTO;
import com.javanc.DTO.UserSession;
import com.javanc.database.database;
import javafx.animation.TranslateTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class loginController implements Initializable {
    @FXML
    private AnchorPane si_loginForm;

    @FXML
    private TextField si_username;

    @FXML
    private PasswordField si_password;

    @FXML
    private Button si_loginBtn;

    @FXML
    private Hyperlink si_forgetPass;

    @FXML
    private AnchorPane su_signupForm;

    @FXML
    private TextField su_username;

    @FXML
    private PasswordField su_password;

    @FXML
    private ComboBox<?> su_gender;

//    @FXML
//    private ComboBox<String> su_role;

    @FXML
    private TextField su_email;

    @FXML
    private Button su_signupBtn;

    @FXML
    private TextField su_phone;

    @FXML
    private TextField su_fullname;

    @FXML
    private TextField su_address;

    @FXML
    private DatePicker su_dob;

    @FXML
    private AnchorPane fg_forgetPasswordForm;

    @FXML
    private TextField fg_username;

    @FXML
    private TextField fg_email;

    @FXML
    private Button fg_backBtn;

    @FXML
    private Button fg_nextBtn;

    @FXML
    private AnchorPane np_newPasswordForm;

    @FXML
    private PasswordField np_newPassword;

    @FXML
    private PasswordField np_confirmPassword;

    @FXML
    private Button np_backBtn;

    @FXML
    private Button np_confirmBtn;

    @FXML
    private AnchorPane side_form;

    @FXML
    private Button side_CreateBtn;

    @FXML
    private Button side_alreadtHave;



    private Connection connect;
    private PreparedStatement prepare;
    private ResultSet result;
    private Alert alert;


//    private void roleList() {
//        ObservableList<String> role = FXCollections.observableArrayList();
//        String query = "SELECT role FROM account";
//
//        // Kết nối đến cơ sở dữ liệu
//        connect = database.connectDB();
//        try {
//            // Chuẩn bị và thực thi truy vấn
//            PreparedStatement pr = connect.prepareStatement(query);
//            ResultSet rs = pr.executeQuery();
//
//            // Thêm dữ liệu từ ResultSet vào danh sách categoryNames
//            while (rs.next()) {
//                role.add(rs.getString("role"));
//            }
//
//            // Đóng các tài nguyên
//            rs.close();
//            pr.close();
//            connect.close();
//
//            // Thiết lập danh sách cho ComboBox
//            su_role.setItems(role);
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

//    private String[] roleList = {"admin", "user"};
//
//    public void roleList() {
//        List<String> listQ = new ArrayList<>();
//
//        for (String data : roleList) {
//            listQ.add(data);
//        }
//
//        ObservableList listData = FXCollections.observableArrayList(listQ);
//        su_role.setItems(listData);
//    }

    private String[] questionList = {"Nam", "Nữ"};

    public void regLquesttionList() {
        List<String> listQ = new ArrayList<>();

        for (String data : questionList) {
            listQ.add(data);
        }

        ObservableList listData = FXCollections.observableArrayList(listQ);
        su_gender.setItems(listData);
    }

    private PreparedStatement pr;
    private ResultSet rs;

//    public void registerBtn() throws SQLException {
//        if (su_fullname.getText().isEmpty() || su_username.getText().isEmpty() || su_password.getText().isEmpty() || su_email.getText().isEmpty() || su_phone.getText().isEmpty() || su_address.getText().isEmpty() || su_gender.getSelectionModel().getSelectedItem() == null || su_role.getSelectionModel().getSelectedItem() == null || su_dob.getValue() == null) {
//            alert = new Alert(Alert.AlertType.ERROR);
//            alert.setTitle("Thông báo lỗi");
//            alert.setHeaderText(null);
//            alert.setContentText("Vui lòng nhập đủ thông tin!");
//            alert.showAndWait();
//        } else {
//            String regData = "INSERT INTO user (fullname, username, password, email, phone, address, gender, dob)"
//                    + "VALUES(?, ?, ?, ?, ?, ?, ?, ?);";
//            connect = database.connectDB();
//
//            String checkUserName = "SELECT username FROM user WHERE username = '" + su_username.getText() + "';";
//            prepare = connect.prepareStatement(checkUserName);
//            result = prepare.executeQuery();
//            try {
//                if (result.next()) {
//                    alert = new Alert(Alert.AlertType.ERROR);
//                    alert.setTitle("Thông báo lỗi!!");
//                    alert.setHeaderText(null);
//                    alert.setContentText("Tên tài khoản đã tồn tại vui lòng chọn tên khác!");
//                    alert.showAndWait();
//                } else if (su_password.getText().length() < 8) {
//                    alert = new Alert(Alert.AlertType.ERROR);
//                    alert.setTitle("Thông báo lỗi!!");
//                    alert.setHeaderText(null);
//                    alert.setContentText("Mật khẩu phải có ít nhất 8 ký tự!");
//                    alert.showAndWait();
//                }
//                else if (!su_phone.getText().matches("\\d+")) {  // Kiểm tra số điện thoại chỉ chứa số
//                    alert = new Alert(Alert.AlertType.ERROR);
//                    alert.setTitle("Thông báo lỗi!!");
//                    alert.setHeaderText(null);
//                    alert.setContentText("Số điện thoại chỉ được chứa ký tự số!");
//                    alert.showAndWait();
//                }
//                else{
////                    prepare = connect.prepareStatement(regData);
//                    prepare.setString(1, su_fullname.getText());
//                    prepare.setString(2, su_username.getText());
//                    prepare.setString(3, su_password.getText());
//                    prepare.setString(4, su_email.getText());
//                    prepare.setString(5, su_phone.getText());
//                    prepare.setString(6, su_address.getText());
//                    prepare.setString(7, (String) su_gender.getSelectionModel().getSelectedItem());
//                    prepare.setString(8, su_dob.getValue().toString());
//
//                    prepare.execute();
//
//                    alert = new Alert(Alert.AlertType.INFORMATION);
//                    alert.setTitle("Thông báo thành công");
//                    alert.setHeaderText(null);
//                    alert.setContentText("Đăng ký tài khoản thành công!");
//                    alert.showAndWait();
//
//                    su_fullname.setText("");
//                    su_username.setText("");
//                    su_password.setText("");
//                    su_email.setText("");
//                    su_phone.setText("");
//                    su_address.setText("");
//                    su_gender.getSelectionModel().clearSelection();
//                    su_dob.setValue(null);
//                    su_role.getSelectionModel().clearSelection();
//
//                    TranslateTransition slider = new TranslateTransition();
//
//                    slider.setNode(side_form);
//                    slider.setToX(0);
//                    slider.setDuration(Duration.seconds(0.5));
//                    slider.setOnFinished((ActionEvent e) -> {
//                        side_alreadtHave.setVisible(false);
//                        side_CreateBtn.setVisible(true);
//                    });
//                    slider.play();
//                }
//                database.closeConection(connect);
//            } catch(Exception e){
//                e.getStackTrace();
//            }
//        }
//    }

//    public void registerBtn() throws SQLException {
//        if (su_fullname.getText().isEmpty() || su_username.getText().isEmpty() || su_password.getText().isEmpty() || su_email.getText().isEmpty() || su_phone.getText().isEmpty() || su_address.getText().isEmpty() || su_gender.getSelectionModel().getSelectedItem() == null || su_dob.getValue() == null) {
//            alert = new Alert(Alert.AlertType.ERROR);
//            alert.setTitle("Thông báo lỗi");
//            alert.setHeaderText(null);
//            alert.setContentText("Vui lòng nhập đủ thông tin!");
//            alert.showAndWait();
//        } else {
//            String regData = "INSERT INTO user (fullname, username, password, email, phone, address, gender, dob) VALUES(?, ?, ?, ?, ?, ?, ?, ?);";
//            String regAccount = "INSERT INTO account (role, user_id) VALUES (user, ?);";
//            connect = database.connectDB();
//
//            String checkUserName = "SELECT username FROM user WHERE username = ?;";
//            prepare = connect.prepareStatement(checkUserName);
//            prepare.setString(1, su_username.getText());
//            result = prepare.executeQuery();
//
//            try {
//                if (result.next()) {
//                    alert = new Alert(Alert.AlertType.ERROR);
//                    alert.setTitle("Thông báo lỗi!!");
//                    alert.setHeaderText(null);
//                    alert.setContentText("Tên tài khoản đã tồn tại vui lòng chọn tên khác!");
//                    alert.showAndWait();
//                } else if (su_password.getText().length() < 8) {
//                    alert = new Alert(Alert.AlertType.ERROR);
//                    alert.setTitle("Thông báo lỗi!!");
//                    alert.setHeaderText(null);
//                    alert.setContentText("Mật khẩu phải có ít nhất 8 ký tự!");
//                    alert.showAndWait();
//                } else if (!su_phone.getText().matches("\\d+")) {
//                    alert = new Alert(Alert.AlertType.ERROR);
//                    alert.setTitle("Thông báo lỗi!!");
//                    alert.setHeaderText(null);
//                    alert.setContentText("Số điện thoại chỉ được chứa ký tự số!");
//                    alert.showAndWait();
//                } else {
//                    // Thêm dữ liệu vào bảng user
//                    prepare = connect.prepareStatement(regData, Statement.RETURN_GENERATED_KEYS);
//                    prepare.setString(1, su_fullname.getText());
//                    prepare.setString(2, su_username.getText());
//                    prepare.setString(3, su_password.getText());
//                    prepare.setString(4, su_email.getText());
//                    prepare.setString(5, su_phone.getText());
//                    prepare.setString(6, su_address.getText());
//                    prepare.setString(7, (String) su_gender.getSelectionModel().getSelectedItem());
//                    prepare.setString(8, su_dob.getValue().toString());
//
//                    prepare.executeUpdate();
//
//                    // Lấy user_id vừa thêm
//                    ResultSet generatedKeys = prepare.getGeneratedKeys();
//                    if (generatedKeys.next()) {
//                        int userId = generatedKeys.getInt(1);
//
//                        // Lấy vai trò từ su_role và thêm vào bảng account
//                        //String role = (String) su_role.getSelectionModel().getSelectedItem();
//                        prepare = connect.prepareStatement(regAccount); // Chuẩn bị lại câu lệnh chèn cho bảng account
//                        //prepare.setString(1, role);
//                        prepare.setInt(2, userId);
//                        prepare.executeUpdate();
//                    }
//
//                    alert = new Alert(Alert.AlertType.INFORMATION);
//                    alert.setTitle("Thông báo thành công");
//                    alert.setHeaderText(null);
//                    alert.setContentText("Đăng ký tài khoản thành công!");
//                    alert.showAndWait();
//
//                    // Xóa dữ liệu trong các trường nhập liệu
//                    su_fullname.setText("");
//                    su_username.setText("");
//                    su_password.setText("");
//                    su_email.setText("");
//                    su_phone.setText("");
//                    su_address.setText("");
//                    su_gender.getSelectionModel().clearSelection();
//                    su_dob.setValue(null);
//                    //su_role.getSelectionModel().clearSelection();
//
//                    TranslateTransition slider = new TranslateTransition();
//                    slider.setNode(side_form);
//                    slider.setToX(0);
//                    slider.setDuration(Duration.seconds(0.5));
//                    slider.setOnFinished((ActionEvent e) -> {
//                        side_alreadtHave.setVisible(false);
//                        side_CreateBtn.setVisible(true);
//                    });
//                    slider.play();
//                }
//                database.closeConection(connect);
//            } catch(Exception e){
//                e.printStackTrace();
//            }
//        }
//    }

    public void registerBtn() throws SQLException {
        if (su_fullname.getText().isEmpty() || su_username.getText().isEmpty() || su_password.getText().isEmpty() || su_email.getText().isEmpty() || su_phone.getText().isEmpty() || su_address.getText().isEmpty() || su_gender.getSelectionModel().getSelectedItem() == null || su_dob.getValue() == null) {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Thông báo lỗi");
            alert.setHeaderText(null);
            alert.setContentText("Vui lòng nhập đủ thông tin!");
            alert.showAndWait();
        } else {
            String regData = "INSERT INTO user (fullname, username, password, email, phone, address, gender, dob) VALUES(?, ?, ?, ?, ?, ?, ?, ?);";
            String regAccount = "INSERT INTO account (role, user_id) VALUES (?, ?);";
            connect = database.connectDB();

            String checkUserName = "SELECT username FROM user WHERE username = ?;";
            prepare = connect.prepareStatement(checkUserName);
            prepare.setString(1, su_username.getText());
            result = prepare.executeQuery();

            try {
                if (result.next()) {
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Thông báo lỗi!!");
                    alert.setHeaderText(null);
                    alert.setContentText("Tên tài khoản đã tồn tại vui lòng chọn tên khác!");
                    alert.showAndWait();
                } else if (su_password.getText().length() < 8) {
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Thông báo lỗi!!");
                    alert.setHeaderText(null);
                    alert.setContentText("Mật khẩu phải có ít nhất 8 ký tự!");
                    alert.showAndWait();
                } else if (!su_phone.getText().matches("\\d+")) {
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Thông báo lỗi!!");
                    alert.setHeaderText(null);
                    alert.setContentText("Số điện thoại chỉ được chứa ký tự số!");
                    alert.showAndWait();
                } else {
                    // Thêm dữ liệu vào bảng user
                    prepare = connect.prepareStatement(regData, Statement.RETURN_GENERATED_KEYS);
                    prepare.setString(1, su_fullname.getText());
                    prepare.setString(2, su_username.getText());
                    prepare.setString(3, su_password.getText());
                    prepare.setString(4, su_email.getText());
                    prepare.setString(5, su_phone.getText());
                    prepare.setString(6, su_address.getText());
                    prepare.setString(7, (String) su_gender.getSelectionModel().getSelectedItem());
                    prepare.setString(8, su_dob.getValue().toString());

                    prepare.executeUpdate();

                    // Lấy user_id vừa thêm
                    ResultSet generatedKeys = prepare.getGeneratedKeys();
                    if (generatedKeys.next()) {
                        int userId = generatedKeys.getInt(1);

                        // Mặc định vai trò là "user"
                        String role = "user";
                        prepare = connect.prepareStatement(regAccount); // Chuẩn bị lại câu lệnh chèn cho bảng account
                        prepare.setString(1, role);
                        prepare.setInt(2, userId);
                        prepare.executeUpdate();
                    }

                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Thông báo thành công");
                    alert.setHeaderText(null);
                    alert.setContentText("Đăng ký tài khoản thành công!");
                    alert.showAndWait();

                    // Xóa dữ liệu trong các trường nhập liệu
                    su_fullname.setText("");
                    su_username.setText("");
                    su_password.setText("");
                    su_email.setText("");
                    su_phone.setText("");
                    su_address.setText("");
                    su_gender.getSelectionModel().clearSelection();
                    su_dob.setValue(null);

                    TranslateTransition slider = new TranslateTransition();
                    slider.setNode(side_form);
                    slider.setToX(0);
                    slider.setDuration(Duration.seconds(0.5));
                    slider.setOnFinished((ActionEvent e) -> {
                        side_alreadtHave.setVisible(false);
                        side_CreateBtn.setVisible(true);
                    });
                    slider.play();
                }
                database.closeConection(connect);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }



    //    login
//    public void loginBtn() throws SQLException {
//        if (si_username.getText().isEmpty() || si_password.getText().isEmpty() ) {
//            alert = new Alert(Alert.AlertType.ERROR);
//            alert.setTitle("Thông báo lỗi");
//            alert.setHeaderText(null);
//            alert.setContentText("Vui lòng nhập đủ thông tin!");
//            alert.showAndWait();
//        }
//        else {
//            connect = database.connectDB();
//            String query = "SELECT username, password FROM user WHERE username = ? AND password = ?;";
//            //String query = "SELECT user.username, user.password, account.role FROM user JOIN account ON user.id = account.user_id WHERE username = ? AND password = ?;";
//            try {
//                prepare = connect.prepareStatement(query);
//                prepare.setString(1, si_username.getText());
//                prepare.setString(2, si_password.getText());
//                result = prepare.executeQuery();
//                if(result.next()) {
//                    DataDTO.username = si_username.getText();
//                    alert = new Alert(Alert.AlertType.INFORMATION);
//                    alert.setTitle("Thông báo!");
//                    alert.setHeaderText(null);
//                    alert.setContentText("Đăng nhập thành công!");
//                    alert.showAndWait();
//
//                    // lien ket den trang main form
//                    Parent root = FXMLLoader.load(getClass().getResource("/com/javanc/mainForm.fxml"));
//
//                    Stage stage = new Stage();
//
//                    stage.setTitle("BookStore Management System");
//                    stage.setMinWidth(1100);
//                    stage.setMinHeight(600);
//                    Scene scene = new Scene(root);
//                    scene.getStylesheets().add("file:/D:/btljvnc/FruitMarket/src/main/java/com/javanc/values/mainFormDesign.css");
//                    stage.setScene(scene);
//                    stage.show();
//
//                    si_loginBtn.getScene().getWindow().hide();
//
//                }else {
//                    alert = new Alert(Alert.AlertType.ERROR);
//                    alert.setTitle("Thông báo lỗi");
//                    alert.setHeaderText(null);
//                    alert.setContentText("Đăng nhập không thành công!");
//                    alert.showAndWait();
//                }
//            }
//            catch (Exception e){
//                e.printStackTrace();
//            }
//        }
//    }

//    public void loginBtn() throws SQLException {
//        if (si_username.getText().isEmpty() || si_password.getText().isEmpty()) {
//            alert = new Alert(Alert.AlertType.ERROR);
//            alert.setTitle("Thông báo lỗi");
//            alert.setHeaderText(null);
//            alert.setContentText("Vui lòng nhập đủ thông tin!");
//            alert.showAndWait();
//        } else {
//            connect = database.connectDB();
//            String query = "SELECT user.username, user.password, account.role FROM user JOIN account ON user.id = account.user_id WHERE username = ? AND password = ?;";
//            try {
//                prepare = connect.prepareStatement(query);
//                prepare.setString(1, si_username.getText());
//                prepare.setString(2, si_password.getText());
//                result = prepare.executeQuery();
//
//                if(result.next()) {
//                    String role = result.getString("role"); // Lấy vai trò người dùng
//                    DataDTO.username = si_username.getText();
//
//                    alert = new Alert(Alert.AlertType.INFORMATION);
//                    alert.setTitle("Thông báo!");
//                    alert.setHeaderText(null);
//                    alert.setContentText("Đăng nhập thành công!");
//                    alert.showAndWait();
//
//                    // Điều hướng dựa trên vai trò
//                    Parent root;
//                    if ("admin".equals(role)) {
//                        root = FXMLLoader.load(getClass().getResource("/com/javanc/mainForm.fxml"));
//                    } else {
//                        root = FXMLLoader.load(getClass().getResource("/com/javanc/homeUser.fxml"));
//                    }
//
//                    Stage stage = new Stage();
//                    stage.setTitle("BookStore Management System");
//                    stage.setMinWidth(1100);
//                    stage.setMinHeight(600);
//                    Scene scene = new Scene(root);
//                    scene.getStylesheets().add("file:/D:/btljvnc/FruitMarket/src/main/java/com/javanc/values/mainFormDesign.css");
//                    scene.getStylesheets().add("file:/D:/btljvnc/FruitMarket/src/main/java/com/javanc/values/homeUser.css");
//                    scene.getStylesheets().add("file:/D:/btljvnc/FruitMarket/src/main/java/com/javanc/values/cardProduct.css");
//                    scene.getStylesheets().add("file:/D:/btljvnc/FruitMarket/src/main/java/com/javanc/values/cardProductNew.css");
//                    stage.setScene(scene);
//                    stage.show();
//
//                    si_loginBtn.getScene().getWindow().hide();
//
//                } else {
//                    alert = new Alert(Alert.AlertType.ERROR);
//                    alert.setTitle("Thông báo lỗi");
//                    alert.setHeaderText(null);
//                    alert.setContentText("Đăng nhập không thành công!");
//                    alert.showAndWait();
//                }
//            } catch (Exception e) {
//                e.printStackTrace();
//            } finally {
//                database.closeConection(connect);
//            }
//        }
//    }

    public void loginBtn() throws SQLException {
        if (si_username.getText().isEmpty() || si_password.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Thông báo lỗi", "Vui lòng nhập đủ thông tin!");
            return;
        }

        String query = "SELECT user.id, user.username, user.password, account.role FROM user JOIN account ON user.id = account.user_id WHERE username = ? AND password = ?;";

        try (Connection connect = database.connectDB();
             PreparedStatement prepare = connect.prepareStatement(query)) {

            prepare.setString(1, si_username.getText());
            prepare.setString(2, si_password.getText());

            try (ResultSet result = prepare.executeQuery()) {
                if (result.next()) {
                    String role = result.getString("role");
                    int userId = result.getInt("id"); // Lấy userId từ cơ sở dữ liệu

                    // Lưu username và userId vào session (hoặc UserSession)
                    DataDTO.username = si_username.getText();
                    UserSession.setUserId(userId); // Lưu userId vào UserSession

                    showAlert(Alert.AlertType.INFORMATION, "Thông báo!", "Đăng nhập thành công!");

                    // Điều hướng dựa trên vai trò
//                    Parent root;
//                    if ("admin".equals(role)) {
//                        root = FXMLLoader.load(getClass().getResource("/com/javanc/mainForm.fxml"));
//                    } else {
//                        root = FXMLLoader.load(getClass().getResource("/com/javanc/homeUser.fxml"));
//                    }
//
//                    Stage stage = new Stage();
//                    stage.setTitle("BookStore Management System");
//                    stage.setMinWidth(1100);
//                    stage.setMinHeight(600);
//                    Scene scene = new Scene(root);
//                    scene.getStylesheets().add("file:/D:/btljvnc/FruitMarket/src/main/java/com/javanc/values/mainFormDesign.css");
//                    scene.getStylesheets().add("file:/D:/btljvnc/FruitMarket/src/main/java/com/javanc/values/homeUser.css");
//                    scene.getStylesheets().add("file:/D:/btljvnc/FruitMarket/src/main/java/com/javanc/values/cardProduct.css");
//                    scene.getStylesheets().add("file:/D:/btljvnc/FruitMarket/src/main/java/com/javanc/values/cardProductNew.css");
//                    stage.setScene(scene);
//                    stage.show();
//
//                    si_loginBtn.getScene().getWindow().hide();
                    Parent root;
                    Scene scene;

                    if ("admin".equals(role)) {
                        // Tải giao diện admin
                        root = FXMLLoader.load(getClass().getResource("/com/javanc/mainForm.fxml"));
                        scene = new Scene(root);
                        // Áp dụng stylesheet cho admin
                        scene.getStylesheets().add("file:/D:/btljvnc/FruitMarket/src/main/java/com/javanc/values/mainFormDesign.css");
                    } else {
                        root = FXMLLoader.load(getClass().getResource("/com/javanc/homeUser.fxml"));
                        scene = new Scene(root);
                        scene.getStylesheets().add("file:/D:/btljvnc/FruitMarket/src/main/java/com/javanc/values/homeUser.css");
                        scene.getStylesheets().add("file:/D:/btljvnc/FruitMarket/src/main/java/com/javanc/values/cardProduct.css");
                        scene.getStylesheets().add("file:/D:/btljvnc/FruitMarket/src/main/java/com/javanc/values/cardProductNew.css");
                    }

                    Stage stage = new Stage();
                    stage.setTitle("BookStore Management System");
                    stage.setMinWidth(1100);
                    stage.setMinHeight(600);
                    stage.setScene(scene);
                    stage.show();

// Ẩn cửa sổ hiện tại
                    si_loginBtn.getScene().getWindow().hide();


                } else {
                    showAlert(Alert.AlertType.ERROR, "Thông báo lỗi", "Đăng nhập không thành công!");
                }
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Lỗi hệ thống", "Có lỗi xảy ra, vui lòng thử lại sau.");
        }
    }

    public void showAlert(Alert.AlertType alertType, String title, String contentText) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(contentText);
        alert.showAndWait();
    }

    //next
    public void nextBTn() {
        if (fg_username.getText().isEmpty() || fg_email.getText().isEmpty()) {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Thông báo lỗi");
            alert.setHeaderText(null);
            alert.setContentText("Vui lòng nhập đủ thông tin!");
            alert.showAndWait();
        } else {
            connect = database.connectDB();
            String query = "SELECT username, email FROM user WHERE username = ? AND email = ?;";

            try {
                prepare = connect.prepareStatement(query);
                prepare.setString(1, fg_username.getText());
                prepare.setString(2, fg_email.getText());
                result = prepare.executeQuery();
                if(result.next()) {
                    np_newPasswordForm.setVisible(true);
                    fg_forgetPasswordForm.setVisible(false);
                }else {
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Thông báo lỗi");
                    alert.setHeaderText(null);
                    alert.setContentText("Username hoặc email không đúng!");
                    alert.showAndWait();
                }
            }
            catch (Exception e) {
                e.getStackTrace();
            }
        }
    }

    public void changePassword() {
        if(np_newPassword.getText().isEmpty() || np_confirmPassword.getText().isEmpty()) {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Thông báo lỗi!");
            alert.setHeaderText(null);
            alert.setContentText("Vui lòng nhập thông tin đầy đủ!");
            alert.showAndWait();
        } else if (np_newPassword.getText().length() < 8) {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Thông báo lỗi!!");
            alert.setHeaderText(null);
            alert.setContentText("Mật khẩu phải có ít nhất 8 ký tự!");
            alert.showAndWait();
        } else if (!np_newPassword.getText().equals(np_confirmPassword.getText())) {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Thông báo lỗi!!");
            alert.setHeaderText(null);
            alert.setContentText("Mật khẩu phải giống nhau!");
            alert.showAndWait();
        }
        else {
//            String getUser = "select * from user where '" +fg_username.getText() + "';";

            connect = database.connectDB();
            try{
//                prepare = connect.prepareStatement(getUser);
//                result = prepare.executeQuery();

                String query = "UPDATE user SET password ='" +
                        np_newPassword.getText() + "' WHERE username = '" + fg_username.getText() + "';";

                prepare = connect.prepareStatement(query);
                //prepare.setString(1, np_newPassword.getText());
                prepare.executeUpdate();


//                String updatePass = "update user set username = '" + np_newPassword.getText() + "' where username = '" +fg_username+"';";
                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Thông báo");
                alert.setHeaderText(null);
                alert.setContentText("Thay đổi mật khẩu thành công!");
                alert.showAndWait();

                np_newPasswordForm.setVisible(false);
                si_loginForm.setVisible(true);
                database.closeConection(connect);
            }
            catch (Exception e) {
                e.getStackTrace();
            }
        }

    }
    public void switchForgetPassword() {
        fg_forgetPasswordForm.setVisible(true);
        si_loginForm.setVisible(false);
    }

    public void switchBackToLogin(){
        fg_forgetPasswordForm.setVisible(false);
        si_loginForm.setVisible(true);
    }

    public void swichBackToForget() {
        np_newPasswordForm.setVisible(false);
        fg_forgetPasswordForm.setVisible(true);
    }
    public void switchForm(ActionEvent event) {
        TranslateTransition slider = new TranslateTransition();
        if (event.getSource() == side_CreateBtn) {
            slider.setNode(side_form);
            slider.setToX(400);
            slider.setDuration(Duration.seconds(0.5));
            slider.setOnFinished((ActionEvent e) -> {
                side_alreadtHave.setVisible(true);
                side_CreateBtn.setVisible(false);

                fg_forgetPasswordForm.setVisible(false);
                si_loginForm.setVisible(true);
                np_newPasswordForm.setVisible(false);

                regLquesttionList();
            });
            slider.play();
        } else if (event.getSource() == side_alreadtHave) {
            slider.setNode(side_form);
            slider.setToX(0);
            slider.setDuration(Duration.seconds(0.5));
            slider.setOnFinished((ActionEvent e) -> {
                side_alreadtHave.setVisible(false);
                side_CreateBtn.setVisible(true);

                fg_forgetPasswordForm.setVisible(false);
                si_loginForm.setVisible(true);
                np_newPasswordForm.setVisible(false);

            });
            slider.play();
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        //roleList();
        regLquesttionList();
    }
}