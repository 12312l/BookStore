package com.javanc.controller;

import com.javanc.DTO.BillDTO;
import com.javanc.DTO.DataDTO;
import com.javanc.DTO.ProductDTO;
import com.javanc.DTO.UserDTO;
import com.javanc.database.database;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;


import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class mainFormController implements Initializable {
    @FXML
    private TableView<BillDTO> tableView_bill;

    @FXML
    private TableColumn<BillDTO, Integer> col_billID;

    @FXML
    private TableColumn<BillDTO, Integer> col_billUserId;

    @FXML
    private TableColumn<BillDTO, Integer> col_billQuantity;

    @FXML
    private TableColumn<BillDTO, Double> col_billTotal;

    @FXML
    private TableColumn<UserDTO, String> col_billDoe;

    @FXML
    private AreaChart<?, ?> chart_collectData;

    @FXML
    private AreaChart<?, ?> chart_bill;

    @FXML
    private AnchorPane form_homepage;

    @FXML
    private AnchorPane form_bill;

    @FXML
    private AnchorPane form_account;
    @FXML
    private AnchorPane main_form;

    @FXML
    private Label username;

    @FXML
    private Button homepage_btn;

    @FXML
    private Button inventory_btn;

    @FXML
    private Button menu_btn;

    @FXML
    private Button bill_btn;

    @FXML
    private Button logout_btn;

    @FXML
    private AnchorPane inventory_form;

    @FXML
    private TableView<ProductDTO> inventory_tableView;

    @FXML
    private TableColumn<ProductDTO, String> inventory_col_productID;

    @FXML
    private TableColumn<ProductDTO, String> inventory_col_productName;

    @FXML
    private TableColumn<ProductDTO, String> inventory_col_productAuthor;

    @FXML
    private TableColumn<UserDTO, String> inventory_col_productCategory;

    @FXML
    private TableColumn<ProductDTO, Integer> inventory_col_productQuantity;

    @FXML
    private TableColumn<ProductDTO, Float> inventory_col_productPrice;

    @FXML
    private TableColumn<ProductDTO, String> inventory_col_productImage;

    @FXML
    private TableColumn<ProductDTO, String> inventory_col_productDescribe;

    @FXML
    private TextField inventory_productID;

    @FXML
    private TextField inventory_productName;

    @FXML
    private TextField inventory_productAuthor;

    @FXML
    private ComboBox<String> inventory_productCategory;

    @FXML
    private TextField inventory_productQuantity;

    @FXML
    private TextField inventory_productPrice;

    @FXML
    private TextField inventory_productDescribe;

    @FXML
    private Button inventory_importBtn;

    @FXML
    private ImageView inventory_imageView;

    @FXML
    private Button inventory_addBtn;

    @FXML
    private Button inventory_repairBtn;

    @FXML
    private Button inventory_deleteBtn;

    @FXML
    private Button inventory_updateBtn;

    @FXML
    private TableView<UserDTO> tableView_Account;

    @FXML
    private TableColumn<UserDTO, Integer> col_userID;

    @FXML
    private TableColumn<UserDTO, String> col_userName;

    @FXML
    private TableColumn<UserDTO, String> col_userEmail;

    @FXML
    private TableColumn<UserDTO, String> col_userPhone;

    @FXML
    private TableColumn<UserDTO, String> col_userAddress;

    @FXML
    private TableColumn<UserDTO, String> col_userGender;

    @FXML
    private TableColumn<UserDTO, String> col_userDob;

    @FXML
    private TextField acc_name;

    @FXML
    private TextField acc_email;

    @FXML
    private TextField acc_phone;

    @FXML
    private TextField acc_address;

    @FXML
    private TextField acc_gender;

    @FXML
    private DatePicker acc_date;

    @FXML
    private TextField acc_account;

    @FXML
    private TextField acc_password;

    @FXML
    private Button acc_updateBtn;

    @FXML
    private Button acc_deleteBtn;

    private Alert alert;
    private Connection connect;
    private PreparedStatement pr;
    private Statement st;
    private ResultSet rs;

    private Image image;


    public void handleProductImageSelection() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif", "*.jpeg"));

        // Hiển thị hộp thoại chọn tệp
        File selectedFile = fileChooser.showOpenDialog(inventory_importBtn.getScene().getWindow());

        if (selectedFile != null) {

            DataDTO.path = selectedFile.getAbsolutePath();
            image = new Image(selectedFile.toURI().toString(), 140, 180, false, true);
            // Thiết lập hình ảnh cho ImageView
            inventory_imageView.setImage(image);
        }
    }


    //them
//    public void inventoryAddBtn() {
//        if (inventory_productID.getText().isEmpty() ||
//                inventory_productID.getText().isEmpty() ||
//                inventory_productName.getText().isEmpty() ||
//                inventory_productAuthor.getText().isEmpty() ||
//                inventory_productCategory.getSelectionModel().getSelectedItem() == null ||
//                inventory_productQuantity.getText().isEmpty() ||
//                inventory_productPrice.getText().isEmpty() ||
//                inventory_imageView.getImage() == null ||
//                inventory_productDescribe.getText().isEmpty() ||
//                DataDTO.path == null
//
//        ) {
//            alert = new Alert(Alert.AlertType.ERROR);
//            alert.setTitle("Thông báo lỗi!");
//            alert.setHeaderText(null);
//            alert.setContentText("Vui lòng nhập đủ thông tin!");
//            alert.showAndWait();
//        } else {
//            String checkPro_id = "select product_id from product where product_id='"
//                    + inventory_productID.getText() + "';";
//            connect = database.connectDB();
//
//            try {
//                st = connect.createStatement();
//                rs = st.executeQuery(checkPro_id);
//
//                if (rs.next()) {
//                    alert = new Alert(Alert.AlertType.ERROR);
//                    alert.setTitle("Thông báo lỗi!");
//                    alert.setHeaderText(null);
//                    alert.setContentText(inventory_productID.getText() + " đã tồn tại!");
//                    alert.showAndWait();
//                } else {
//                    String insertPro = "insert into product(product_id, product_name, product_author, product_category, product_quantity, product_price, product_image, product_describe)" +
//                            "values(?,?,?,?,?,?,?,?)";
//                    pr = connect.prepareStatement(insertPro);
//                    pr.setString(1, inventory_productID.getText());
//                    pr.setString(2, inventory_productName.getText());
//                    pr.setString(3, inventory_productAuthor.getText());
//                    pr.setString(4, inventory_productCategory.getSelectionModel().getSelectedItem());
//                    pr.setString(5, inventory_productQuantity.getText());
//                    pr.setString(6, inventory_productPrice.getText());
//                    String path = DataDTO.path;
//                    path = path.replace("\\", "\\\\");
//
//                    pr.setString(7, path);
//                    pr.setString(8, (String) inventory_productDescribe.getText());
//
//                    pr.execute();
//                    alert = new Alert(Alert.AlertType.INFORMATION);
//                    alert.setTitle("Thông báo!");
//                    alert.setHeaderText(null);
//                    alert.setContentText("Thêm sản phẩm thành công!");
//                    alert.showAndWait();
//
//                    inventoryShowData();
//                    inventoryClearBtn();
//                }
//            } catch (Exception e) {
//                e.getStackTrace();
//            }
//        }
//    }

    public void inventoryAddBtn() {
        if (inventory_productID.getText().isEmpty() ||
                inventory_productName.getText().isEmpty() ||
                inventory_productAuthor.getText().isEmpty() ||
                inventory_productCategory.getSelectionModel().getSelectedItem() == null ||
                inventory_productQuantity.getText().isEmpty() ||
                inventory_productPrice.getText().isEmpty() ||
                inventory_imageView.getImage() == null ||
                inventory_productDescribe.getText().isEmpty() ||
                DataDTO.path == null) {

            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Thông báo lỗi!");
            alert.setHeaderText(null);
            alert.setContentText("Vui lòng nhập đủ thông tin!");
            alert.showAndWait();
        } else {
            String checkPro_id = "SELECT product_id FROM product WHERE product_id = ?";
            connect = database.connectDB();

            try {
                pr = connect.prepareStatement(checkPro_id);
                pr.setString(1, inventory_productID.getText());
                rs = pr.executeQuery();

                if (rs.next()) {
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Thông báo lỗi!");
                    alert.setHeaderText(null);
                    alert.setContentText(inventory_productID.getText() + " đã tồn tại!");
                    alert.showAndWait();
                } else {
                    // Lấy category_id dựa trên category_name được chọn từ ComboBox
                    String categoryName = (String) inventory_productCategory.getSelectionModel().getSelectedItem();
                    String getCategoryIDQuery = "SELECT id FROM category WHERE category_name = ?";
                    pr = connect.prepareStatement(getCategoryIDQuery);
                    pr.setString(1, categoryName);
                    rs = pr.executeQuery();

                    int categoryID = 0;
                    if (rs.next()) {
                        categoryID = rs.getInt("id");
                    }

                    String insertPro = "INSERT INTO product(product_id, product_name, product_author, category_id, product_quantity, product_price, product_image, product_describe) " +
                            "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
                    pr = connect.prepareStatement(insertPro);
                    pr.setString(1, inventory_productID.getText());
                    pr.setString(2, inventory_productName.getText());
                    pr.setString(3, inventory_productAuthor.getText());
                    pr.setInt(4, categoryID);
                    pr.setInt(5, Integer.parseInt(inventory_productQuantity.getText()));
                    pr.setFloat(6, Float.parseFloat(inventory_productPrice.getText()));

                    // Chuyển đường dẫn ảnh để lưu trong cơ sở dữ liệu
                    String path = DataDTO.path.replace("\\", "\\\\");
                    pr.setString(7, path);
                    pr.setString(8, inventory_productDescribe.getText());

                    pr.execute();
                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Thông báo!");
                    alert.setHeaderText(null);
                    alert.setContentText("Thêm sản phẩm thành công!");
                    alert.showAndWait();

                    inventoryShowData();
                    inventoryClearBtn();
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    if (rs != null) rs.close();
                    if (pr != null) pr.close();
                    if (connect != null) connect.close();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    //sua danh sach
//    public void updateBtn() {
//        if (inventory_productID.getText().isEmpty() ||
//                inventory_productName.getText().isEmpty() ||
//                inventory_productAuthor.getText().isEmpty() ||
//                inventory_productCategory.getSelectionModel().getSelectedItem() == null ||
//                inventory_productQuantity.getText().isEmpty() ||
//                inventory_productPrice.getText().isEmpty() ||
//                inventory_imageView.getImage() == null ||
//                inventory_productDescribe.getText().isEmpty() ||
//                DataDTO.path == null) {
//
//            alert = new Alert(Alert.AlertType.ERROR);
//            alert.setTitle("Thông báo lỗi!");
//            alert.setHeaderText(null);
//            alert.setContentText("Vui lòng nhập đủ thông tin!");
//            alert.showAndWait();
//        } else {
//            String path = DataDTO.path;
//            path = path.replace("\\", "\\\\");
//            String updatePro = "UPDATE product SET "
//                    + "product_id = ?, "
//                    + "product_name = ?, "
//                    + "product_author = ?, "
//                    + "product_category = ?, "
//                    + "product_quantity = ?, "
//                    + "product_price = ?, "
//                    + "product_image = ?, "
//                    + "product_describe = ? "
//                    + "WHERE product_id = ?";
//
//            connect = database.connectDB();
//
//            try {
//                alert = new Alert(Alert.AlertType.CONFIRMATION);
//                alert.setTitle("Thông báo");
//                alert.setHeaderText(null);
//                alert.setContentText("Bạn có chắc chắn muốn sửa sản phẩm có mã " + inventory_productID.getText() + " không?");
//                Optional<ButtonType> optional = alert.showAndWait();
//
//                if (optional.isPresent() && optional.get().equals(ButtonType.OK)) {
//                    pr = connect.prepareStatement(updatePro);
//
//                    // Thiết lập các giá trị cho PreparedStatement
//                    pr.setString(1, inventory_productID.getText());
//                    pr.setString(2, inventory_productName.getText());
//                    pr.setString(3, inventory_productAuthor.getText());
//                    pr.setString(4, inventory_productCategory.getSelectionModel().getSelectedItem().toString());
//                    pr.setString(5, inventory_productQuantity.getText());
//                    pr.setString(6, inventory_productPrice.getText());
//                    pr.setString(7, path);
//                    pr.setString(8, inventory_productDescribe.getText());
//                    pr.setString(9, inventory_productID.getText());  // Đặt giá trị cho WHERE condition
//
//                    pr.executeUpdate();
//
//                    alert = new Alert(Alert.AlertType.INFORMATION);
//                    alert.setTitle("Thông báo");
//                    alert.setHeaderText(null);
//                    alert.setContentText("Sửa thành công!");
//                    alert.showAndWait();
//
//                    inventoryShowData();
//                    inventoryClearBtn();
//                } else {
//                    alert = new Alert(Alert.AlertType.ERROR);
//                    alert.setTitle("Thông báo lỗi");
//                    alert.setHeaderText(null);
//                    alert.setContentText("Sửa không thành công!");
//                    alert.showAndWait();
//                }
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//
//        }
//    }


    public void updateBtn() {
        // Kiểm tra các trường dữ liệu
        if (inventory_productID.getText().isEmpty() ||
                inventory_productName.getText().isEmpty() ||
                inventory_productAuthor.getText().isEmpty() ||
                inventory_productCategory.getSelectionModel().getSelectedItem() == null ||
                inventory_productQuantity.getText().isEmpty() ||
                inventory_productPrice.getText().isEmpty() ||
                inventory_imageView.getImage() == null ||
                inventory_productDescribe.getText().isEmpty() ||
                DataDTO.path == null) {

            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Thông báo lỗi!");
            alert.setHeaderText(null);
            alert.setContentText("Vui lòng nhập đủ thông tin!");
            alert.showAndWait();
            return;
        }

        // Chuẩn bị đường dẫn ảnh
        String path = DataDTO.path.replace("\\", "\\\\");

        // Câu lệnh cập nhật sản phẩm
        String updatePro = "UPDATE product SET "
                + "product_name = ?, "
                + "product_author = ?, "
                + "category_id = (SELECT id FROM category WHERE category_name = ?), "
                + "product_quantity = ?, "
                + "product_price = ?, "
                + "product_image = ?, "
                + "product_describe = ? "
                + "WHERE product_id = ?";

        try (Connection connect = database.connectDB();
             PreparedStatement pr = connect.prepareStatement(updatePro)) {

            // Hiển thị hộp thoại xác nhận
            alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Thông báo");
            alert.setHeaderText(null);
            alert.setContentText("Bạn có chắc chắn muốn sửa sản phẩm có mã " + inventory_productID.getText() + " không?");
            Optional<ButtonType> optional = alert.showAndWait();

            if (optional.isPresent() && optional.get().equals(ButtonType.OK)) {
                // Thiết lập các giá trị cho PreparedStatement
                pr.setString(1, inventory_productName.getText());
                pr.setString(2, inventory_productAuthor.getText());
                pr.setString(3, inventory_productCategory.getSelectionModel().getSelectedItem());
                pr.setInt(4, Integer.parseInt(inventory_productQuantity.getText()));
                pr.setFloat(5, Float.parseFloat(inventory_productPrice.getText()));
                pr.setString(6, path);
                pr.setString(7, inventory_productDescribe.getText());
                pr.setString(8, inventory_productID.getText());  // Đặt giá trị cho WHERE condition

                // Thực thi câu lệnh cập nhật
                pr.executeUpdate();

                // Thông báo thành công
                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Thông báo");
                alert.setHeaderText(null);
                alert.setContentText("Sửa thành công!");
                alert.showAndWait();

                // Cập nhật lại dữ liệu trong TableView
                inventoryShowData();
                inventoryClearBtn();
            } else {
                // Nếu người dùng không xác nhận
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Thông báo lỗi");
                alert.setHeaderText(null);
                alert.setContentText("Sửa không thành công!");
                alert.showAndWait();
            }

        } catch (Exception e) {
            e.printStackTrace();
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Thông báo lỗi");
            alert.setHeaderText(null);
            alert.setContentText("Lỗi xảy ra khi cập nhật dữ liệu: " + e.getMessage());
            alert.showAndWait();
        }
    }


    public void inventoryDeleteBtn() {
        if (inventory_productID.getText().isEmpty() ||
                inventory_productName.getText().isEmpty() ||
                inventory_productAuthor.getText().isEmpty() ||
                inventory_productCategory.getSelectionModel().getSelectedItem() == null ||
                inventory_productQuantity.getText().isEmpty() ||
                inventory_productPrice.getText().isEmpty() ||
                inventory_imageView.getImage() == null ||
                inventory_productDescribe.getText().isEmpty() ||
                DataDTO.path == null) {

            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Thông báo lỗi!");
            alert.setHeaderText(null);
            alert.setContentText("Vui lòng nhập đủ thông tin!");
            alert.showAndWait();
        } else {
            alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Thông báo");
            alert.setHeaderText(null);
            alert.setContentText("Bạn có chắc chắn muốn xóa sản phẩm có mã " + inventory_productID.getText() + " không?");
            Optional<ButtonType> optional = alert.showAndWait();
            if (optional.get().equals(ButtonType.OK)) {
                String detePro = "delete from product where product_id = ?";
                try {
                    pr = connect.prepareStatement(detePro);
                    pr.setString(1, inventory_productID.getText());
                    pr.executeUpdate();

                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Thông báo");
                    alert.setHeaderText(null);
                    alert.setContentText("Xóa thành công!");
                    alert.showAndWait();

                    inventoryShowData();
                    inventoryClearBtn();
                } catch (Exception e) {
                    e.getStackTrace();
                }
            } else {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Thông báo lỗi");
                alert.setHeaderText(null);
                alert.setContentText("Xóa không thành công!");
                alert.showAndWait();
            }
        }
    }

    //Rỗng các ô sau khi thêm
    public void inventoryClearBtn() {
        inventory_productID.setText("");
        inventory_productName.setText("");
        inventory_productAuthor.setText("");
        inventory_productCategory.getSelectionModel().clearSelection();
        inventory_productQuantity.setText("");
        inventory_productPrice.setText("");
        DataDTO.path = "";
        inventory_imageView.setImage(null);
        inventory_productDescribe.setText("");
    }

    public void accountClearBtn() {
        acc_name.setText("");
        acc_email.setText("");
        acc_phone.setText("");
        acc_address.setText("");
        acc_gender.setText("");
        acc_account.setText("");
        acc_password.setText("");
    }

    public void inventorySelectData() {
        ProductDTO productDTO = inventory_tableView.getSelectionModel().getSelectedItem();
        int num = inventory_tableView.getSelectionModel().getSelectedIndex();
        if ((num - 1) < -1) return;
        inventory_productID.setText(productDTO.getId());
        inventory_productName.setText(productDTO.getName());
        inventory_productAuthor.setText(productDTO.getAuthor());
        inventory_productQuantity.setText(String.valueOf(productDTO.getQuantity()));
        inventory_productPrice.setText(String.valueOf(productDTO.getPrice()));
        inventory_productDescribe.setText(productDTO.getDescribe());
        DataDTO.path = "File:" + productDTO.getImage();
        image = new Image(DataDTO.path, 140, 180, false, true);
        inventory_imageView.setImage(image);
    }

//    public ObservableList<ProductDTO> inventoryDataList() {
//        ObservableList<ProductDTO> listData = FXCollections.observableArrayList();
//
//        String getPro = "select * from product;";
//        connect = database.connectDB();
//        try {
//            pr = connect.prepareStatement(getPro);
//            rs = pr.executeQuery();
//
//            ProductDTO productDTO;
//            while (rs.next()) {
//                productDTO = new ProductDTO(rs.getString("product_id")
//                        , rs.getString("product_name")
//                        , rs.getString("product_author")
//                        , rs.getString("product_category")
//                        , rs.getInt("product_quantity")
//                        , rs.getFloat("product_price")
//                        , rs.getString("product_image")
//                        , rs.getString("product_describe"));
//                listData.add(productDTO);
//            }
//        } catch (Exception e) {
//            e.getStackTrace();
//            alert = new Alert(Alert.AlertType.ERROR);
//            alert.setTitle("Thông báo lỗi");
//            alert.setHeaderText(null);
//            alert.setContentText("Lỗi cập nhật: " + e.getMessage());
//            alert.showAndWait();
//        }
//        return listData;
//    }

    public ObservableList<ProductDTO> inventoryDataList() {
        ObservableList<ProductDTO> listData = FXCollections.observableArrayList();

        String getPro = "select * from product;";
        connect = database.connectDB();
        try {
            pr = connect.prepareStatement(getPro);
            rs = pr.executeQuery();

            ProductDTO productDTO;
            while (rs.next()) {
                productDTO = new ProductDTO(
                        rs.getString("product_id"),
                        rs.getString("product_name"),
                        rs.getString("product_author"),
                        rs.getInt("product_quantity"),
                        rs.getFloat("product_price"),
                        rs.getString("product_image"),
                        rs.getString("product_describe"),
                        rs.getInt("category_id")  // Lấy categoryID thay cho product_category
                );
                listData.add(productDTO);
            }
        } catch (Exception e) {
            e.printStackTrace();  // Sửa thành e.printStackTrace() để hiển thị lỗi chi tiết hơn
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Thông báo lỗi");
            alert.setHeaderText(null);
            alert.setContentText("Lỗi cập nhật: " + e.getMessage());
            alert.showAndWait();
        }
        return listData;
    }


    // Phương thức hiển thị dữ liệu lên TableView
    private ObservableList<ProductDTO> inventoryListData;

    public void inventoryShowData() {
        inventoryListData = inventoryDataList(); // Gọi phương thức lấy dữ liệu


        inventory_col_productID.setCellValueFactory(new PropertyValueFactory<ProductDTO, String>("id"));
        inventory_col_productName.setCellValueFactory(new PropertyValueFactory<ProductDTO, String>("name"));
        inventory_col_productAuthor.setCellValueFactory(new PropertyValueFactory<ProductDTO, String>("author"));

        inventory_col_productCategory.setCellValueFactory(new PropertyValueFactory<UserDTO, String>("categoryID"));
        inventory_col_productQuantity.setCellValueFactory(new PropertyValueFactory<ProductDTO, Integer>("quantity"));
        inventory_col_productPrice.setCellValueFactory(new PropertyValueFactory<ProductDTO, Float>("price"));
        inventory_col_productImage.setCellValueFactory(new PropertyValueFactory<ProductDTO, String>("image"));
        inventory_col_productDescribe.setCellValueFactory(new PropertyValueFactory<ProductDTO, String>("describe"));

        // Thiết lập danh sách dữ liệu cho TableView
        inventory_tableView.setItems(inventoryListData);
    }


//    private String[] categoryList = {"Sách kinh tế", "Sách văn học trong nước", "Sách văn học nước ngoài", "Sách thưởng thức đời sống", "Sách thiếu nhi", "Sách phát triển bản thân", "Sách tin học ngoại ngữ", "Sách chuyên ngành"};
//    private void inventoryCategoryList() {
//        List<String> categoryL = new ArrayList<>();
//
//        for(String data : categoryList) {
//            categoryL.add(data);
//        }
//
//        ObservableList listData = FXCollections.observableArrayList(categoryL);
//        inventory_productCategory.setItems(listData);
//    }


    private void inventoryCategoryList() {
        ObservableList<String> categoryNames = FXCollections.observableArrayList();
        String query = "SELECT category_name FROM category";

        // Kết nối đến cơ sở dữ liệu
        connect = database.connectDB();
        try {
            // Chuẩn bị và thực thi truy vấn
            PreparedStatement pr = connect.prepareStatement(query);
            ResultSet rs = pr.executeQuery();

            // Thêm dữ liệu từ ResultSet vào danh sách categoryNames
            while (rs.next()) {
                categoryNames.add(rs.getString("category_name"));
            }

            // Đóng các tài nguyên
            rs.close();
            pr.close();
            connect.close();

            // Thiết lập danh sách cho ComboBox
            inventory_productCategory.setItems(categoryNames);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    //lay du lieu bang user
    public ObservableList<UserDTO> accountDataList() {
        ObservableList<UserDTO> listData = FXCollections.observableArrayList();

        String getUser = "select * from user;";
        connect = database.connectDB();
        try{
            pr = connect.prepareStatement(getUser);
            rs = pr.executeQuery();

            UserDTO userDTO;
            while (rs.next()){
                userDTO = new UserDTO(rs.getInt("id")
                        , rs.getString("fullname")
                        , rs.getString("username")
                        , rs.getString("password")
                        , rs.getString("email")
                        , rs.getString("phone")
                        , rs.getString("address")
                        , rs.getString("gender")
                        , rs.getString("dob"));
                listData.add(userDTO);
            }
        }catch (Exception e){
            e.getStackTrace();
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Thông báo lỗi");
            alert.setHeaderText(null);
            alert.setContentText("Lỗi cập nhật: " + e.getMessage());
            alert.showAndWait();
        }
        return listData;
    }

    private ObservableList<UserDTO> accountListData;
    // Phương thức hiển thị dữ liệu lên TableView
    public void accountShowData() {
        accountListData = accountDataList(); // Gọi phương thức lấy dữ liệu


        col_userID.setCellValueFactory(new PropertyValueFactory<UserDTO, Integer>("id"));
        col_userName.setCellValueFactory(new PropertyValueFactory<UserDTO, String>("fullname"));
        col_userEmail.setCellValueFactory(new PropertyValueFactory<UserDTO, String>("email"));
        col_userPhone.setCellValueFactory(new PropertyValueFactory<UserDTO, String>("phone"));
        col_userAddress.setCellValueFactory(new PropertyValueFactory<UserDTO, String>("address"));
        col_userGender.setCellValueFactory(new PropertyValueFactory<UserDTO, String>("gender"));
        col_userDob.setCellValueFactory(new PropertyValueFactory<UserDTO, String>("dob"));

        // Thiết lập danh sách dữ liệu cho TableView
        tableView_Account.setItems(accountListData);
    }

    public void accountSelectData(){
        UserDTO userDTO = tableView_Account.getSelectionModel().getSelectedItem();
        int num = tableView_Account.getSelectionModel().getSelectedIndex();
        if((num-1)<-1) return;
        acc_name.setText(userDTO.getFullname());
        acc_email.setText(userDTO.getEmail());
        acc_phone.setText(userDTO.getPhone());
        acc_address.setText(userDTO.getAddress());
        acc_gender.setText(userDTO.getGender());
        //acc_date.setText(userDTO.getDob());
        acc_account.setText(userDTO.getUsername());
        acc_password.setText(userDTO.getPassword());
    }

    public void accountDeleteBtn() {
        if (acc_name.getText().isEmpty() ||
                acc_email.getText().isEmpty() ||
                acc_phone.getText().isEmpty() ||
                acc_address.getText().isEmpty()||
                acc_gender.getText().isEmpty() ||
                acc_account.getText().isEmpty() ||
                acc_password.getText().isEmpty()) {

            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Thông báo lỗi!");
            alert.setHeaderText(null);
            alert.setContentText("Vui lòng nhập đủ thông tin!");
            alert.showAndWait();
        } else{
            alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Thông báo");
            alert.setHeaderText(null);
            alert.setContentText("Bạn có chắc chắn muốn xóa sản phẩm có mã không?");
            Optional<ButtonType> optional = alert.showAndWait();
            if(optional.get().equals(ButtonType.OK)){
                String detePro = "delete from user where username = ?";
                try{
                    pr = connect.prepareStatement(detePro);
                    pr.setString(1, acc_account.getText());
                    pr.executeUpdate();

                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Thông báo");
                    alert.setHeaderText(null);
                    alert.setContentText("Xóa thành công!");
                    alert.showAndWait();

                    accountShowData();
                    accountClearBtn();
                } catch (Exception e) {
                    e.getStackTrace();
                }
            } else{
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Thông báo lỗi");
                alert.setHeaderText(null);
                alert.setContentText("Xóa không thành công!");
                alert.showAndWait();
            }
        }
    }

    //sua danh sach
    public void accountUpdateBtn() {
        if (acc_name.getText().isEmpty() ||
                acc_email.getText().isEmpty() ||
                acc_phone.getText().isEmpty() ||
                acc_address.getText().isEmpty()||
                acc_gender.getText().isEmpty() ||
                acc_account.getText().isEmpty() ||
                acc_password.getText().isEmpty()) {

            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Thông báo lỗi!");
            alert.setHeaderText(null);
            alert.setContentText("Vui lòng nhập đủ thông tin!");
            alert.showAndWait();
        } else {
            String updateUser = "UPDATE user SET "
                    + "fullname = ?, "
                    + "password = ?, "
                    + "email = ?, "
                    + "phone = ?, "
                    + "address = ?, "
                    + "gender = ?, "
                    + "dob = ? "
                    + "WHERE username = ?";

            connect = database.connectDB();

            try {
                alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Thông báo");
                alert.setHeaderText(null);
                alert.setContentText("Bạn có chắc chắn muốn sửa sản phẩm có mã " + acc_account.getText() + " không?");
                Optional<ButtonType> optional = alert.showAndWait();

                if (optional.isPresent() && optional.get().equals(ButtonType.OK)) {
                    pr = connect.prepareStatement(updateUser);

                    // Thiết lập các giá trị cho PreparedStatement
                    pr.setString(1, acc_name.getText()); // Tham số 1: fullname
                    pr.setString(2, acc_password.getText()); // Tham số 2: password
                    pr.setString(3, acc_email.getText()); // Tham số 3: email
                    pr.setString(4, acc_phone.getText()); // Tham số 4: phone
                    pr.setString(5, acc_address.getText()); // Tham số 5: address
                    pr.setString(6, acc_gender.getText()); // Tham số 6: gender
                    LocalDate localDate = acc_date.getValue(); // Lấy ngày sinh từ DatePicker
                    pr.setDate(7, java.sql.Date.valueOf(localDate)); // Tham số 7: dob (ngày sinh)
                    pr.setString(8, acc_account.getText()); // Tham số 8: username (để tìm bản ghi)


                    pr.executeUpdate();

                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Thông báo");
                    alert.setHeaderText(null);
                    alert.setContentText("Sửa thành công!");
                    alert.showAndWait();

                    accountShowData();
                    accountClearBtn();
                } else {
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Thông báo lỗi");
                    alert.setHeaderText(null);
                    alert.setContentText("Sửa không thành công!");
                    alert.showAndWait();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }


    //lay du lieu bang bill
    public ObservableList<BillDTO> billDataList() {
        ObservableList<BillDTO> listData = FXCollections.observableArrayList();

        String getUser = "select * from bill;";
        connect = database.connectDB();
        try{
            pr = connect.prepareStatement(getUser);
            rs = pr.executeQuery();

            BillDTO billDTO;
            while (rs.next()){
                billDTO = new BillDTO(rs.getInt("bill_id")
                        , rs.getInt("user_id")
                        , rs.getInt("quantity")
                        , rs.getDouble("total")
                        , rs.getString("methodPay")
                        , rs.getString("doe"));
                listData.add(billDTO);
            }
        }catch (Exception e){
            e.getStackTrace();
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Thông báo lỗi");
            alert.setHeaderText(null);
            alert.setContentText("Lỗi cập nhật: " + e.getMessage());
            alert.showAndWait();
        }
        return listData;
    }

    private ObservableList<BillDTO> billListData;
    // Phương thức hiển thị dữ liệu lên TableView
    public void billShowData() {
        billListData = billDataList(); // Gọi phương thức lấy dữ liệu


        col_billID.setCellValueFactory(new PropertyValueFactory<BillDTO, Integer>("billID"));
        col_billUserId.setCellValueFactory(new PropertyValueFactory<BillDTO, Integer>("userID"));
        col_billQuantity.setCellValueFactory(new PropertyValueFactory<BillDTO, Integer>("quantity"));
        col_billTotal.setCellValueFactory(new PropertyValueFactory<BillDTO, Double>("total"));
        col_billDoe.setCellValueFactory(new PropertyValueFactory<UserDTO, String>("doe"));

        // Thiết lập danh sách dữ liệu cho TableView
        tableView_bill.setItems(billListData);
    }

    public void logout(){
        try {
            alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Thông báo lỗi!");
            alert.setHeaderText(null);
            alert.setContentText("Bạn có chắc muốn đăng xuất không?");
            Optional<ButtonType> optional = alert.showAndWait();

            if(optional.get().equals(ButtonType.OK)) {
                //ẩn main form
                logout_btn.getScene().getWindow().hide();

                //liet ket den login
                Parent root = FXMLLoader.load(getClass().getResource("/com/javanc/login.fxml"));

                Stage stage = new Stage();

                stage.setTitle("BookStore Management System");
                stage.setMinWidth(600);
                stage.setMinHeight(400);
                Scene scene = new Scene(root);
                scene.getStylesheets().add("file:/D:/btljvnc/FruitMarket/src/main/java/com/javanc/values/loginDesign.css");
                stage.setScene(scene);
                stage.show();
            }
        }
        catch (Exception e) {
            e.getStackTrace();
        }
    }

    public void displayUsername() {
        String user = DataDTO.username;
        user = user.substring(0, 1).toUpperCase() + user.substring(1);
        username.setText(user);
    }


    public void changeScreenHomePage() {
        form_homepage.setVisible(true);
        inventory_form.setVisible(false);
        form_bill.setVisible(false);
        form_account.setVisible(false);
        chartScreenCollection();
        chartScreenBill();
    }

    public void changeScreenInventory() {
        form_homepage.setVisible(false);
        inventory_form.setVisible(true);
        form_bill.setVisible(false);
        form_account.setVisible(false);
    }

    public void changeScreenAccount() {
        form_homepage.setVisible(false);
        inventory_form.setVisible(false);
        form_bill.setVisible(false);
        form_account.setVisible(true);
    }

    public void changeScreenBill() {
        form_homepage.setVisible(false);
        inventory_form.setVisible(false);
        form_bill.setVisible(true);
        form_account.setVisible(false);
    }

    public void chartScreenCollection() {
        chart_collectData.getData().clear();

        String sql = "SELECT b.doe, SUM(bd.total) FROM billdetail bd " +
                "JOIN bill b ON bd.bill_id = b.bill_id " +
                "GROUP BY b.doe ORDER BY b.doe";
        connect = database.connectDB();
        XYChart.Series chart = new XYChart.Series();
        try {
            pr = connect.prepareStatement(sql);
            rs = pr.executeQuery();

            while (rs.next()) {
                chart.getData().add(new XYChart.Data<>(rs.getString(1), rs.getFloat(2)));
            }

            chart_collectData.getData().add(chart);
            database.closeConection(connect);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void chartScreenBill() {
        chart_bill.getData().clear();

        // truy vấn cần sửa
        String sql = "SELECT b.doe, COUNT(DISTINCT b.bill_id) " +
                "FROM billdetail bd " +
                "JOIN bill b ON bd.bill_id = b.bill_id " +
                "GROUP BY b.doe " +
                "ORDER BY b.doe";
        connect= database.connectDB();
        XYChart.Series chart = new XYChart.Series();
        try {
            pr = connect.prepareStatement(sql);
            rs = pr.executeQuery();

            while (rs.next()) {
                chart.getData().add(new XYChart.Data<>(rs.getString(1), rs.getInt(2)));
            }

            chart_bill.getData().add(chart);
            database.closeConection(connect);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //displayUsername();

        inventoryCategoryList();
        //inventoryStatusList();
        inventoryShowData();
        accountShowData();
        chartScreenCollection();
        chartScreenBill();
        billShowData();
    }
}
