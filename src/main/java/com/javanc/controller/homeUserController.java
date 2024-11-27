package com.javanc.controller;

import com.javanc.DTO.ProductDTO;
import com.javanc.DTO.UserDTO;
import com.javanc.DTO.UserSession;
import com.javanc.database.database;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

import java.net.URL;
import java.sql.*;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;


public class homeUserController implements Initializable {
    @FXML
    private StackPane mainStack;

    @FXML
    private AnchorPane mainPane;

    @FXML
    private Label hd_logo;

    @FXML
    private TextField hd_timKiemTextF;

    @FXML
    private Button hd_timKiemBtn;

    @FXML
    private ImageView hd_thongBao;

    @FXML
    private ImageView hd_gioHang;

    @FXML
    private MenuButton hd_taiKhoan;

    @FXML
    private MenuItem tk_update;

    @FXML
    private MenuItem hd_changePassword;

    @FXML
    private MenuItem tk_logout;

    @FXML
    private Button danhMucBtn;

    @FXML
    private MenuButton dm_chuyennganh;

    @FXML
    private MenuItem c1_kyThuat;

    @FXML
    private MenuItem c1_tuNhien;

    @FXML
    private MenuItem c3_myThuat;

    @FXML
    private MenuButton dm_giaotrinh;

    @FXML
    private MenuItem c2_thamKhao;

    @FXML
    private MenuButton dm_kinhte;

    @FXML
    private MenuItem c3_market;

    @FXML
    private MenuItem c3_taiChinh;

    @FXML
    private MenuItem c3_nhanSu;

    @FXML
    private MenuButton dm_thieuNhi;

    @FXML
    private MenuItem c4_myThuat_AmNhac;

    @FXML
    private MenuItem c4_truyenTranh;

    @FXML
    private MenuButton dm_ngoaiNgu;

    @FXML
    private MenuItem c5_ngoaiNgu;

    @FXML
    private MenuItem c5_tinHoc;

    @FXML
    private MenuButton dm_ptBanThan;

    @FXML
    private MenuItem c6_lamNguoi;

    @FXML
    private MenuItem c6_danhNhan;

    @FXML
    private MenuItem c6_kiNangSong;

    @FXML
    private AnchorPane homepage_form;

    @FXML
    private ScrollPane scro_productOutstand;

    @FXML
    private ScrollPane scro_productNormal;

    @FXML
    private AnchorPane scr;

    @FXML
    private GridPane outstand_gridPane;

    @FXML
    private GridPane product_gridPane;



    @FXML
    private AnchorPane fom_buy;

    @FXML
    private Label bill_name;

    @FXML
    private Label bill_phone;

    @FXML
    private Label bill_productAddress;

    @FXML
    private Label bill_productName;

    @FXML
    private Label bill_productQuantity;

    @FXML
    private Label bill_total;

    @FXML
    private ComboBox<String> bill_methodPay;

    @FXML
    private Button bill_order;

    @FXML
    private Button bill_cancel;

    private Alert alert;
    private Connection connect;
    private PreparedStatement pr;
    private Statement st;
    private ResultSet rs;

    private ObservableList<ProductDTO> cardListData = FXCollections.observableArrayList();
    public ObservableList<ProductDTO> productData(){
        String sql = "select * from product;";
        connect = database.connectDB();
        ObservableList<ProductDTO> listData = FXCollections.observableArrayList();

        try {
            pr = connect.prepareStatement(sql);
            rs = pr.executeQuery();

            while (rs.next()) {
                ProductDTO productDTO = new ProductDTO(
                        rs.getString("product_id"),
                        rs.getString("product_name"),
                        rs.getString("product_author"),
//                        rs.getInt("product_quantity"),
                        rs.getFloat("product_price"),  // Lấy giá sản phẩm
                        rs.getString("product_image")  // Lấy tên hình ảnh sản phẩm
                );
                listData.add(productDTO);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listData;
    }



//    public void productDisplay(){
//        cardListData.clear();
//        cardListData.addAll(productData());
//
//        int row =0;
//        int  column =0;
//
//        product_gridPane.getRowConstraints().clear();
//        product_gridPane.getColumnConstraints().clear();
//        product_gridPane.setHgap(10);  // Khoảng cách ngang giữa các cột
//        product_gridPane.setVgap(20);  // Khoảng cách dọc giữa các hàng
//
//        for(int q = 0; q< cardListData.size(); q++) {
//            try{
//                FXMLLoader loader = new FXMLLoader();
//                loader.setLocation(getClass().getResource("/com/javanc/cardProduct.fxml"));
//
//                VBox vBox = loader.load();
//                cardProductController cardC = loader.getController();
//
//                cardC.setData(cardListData.get(q));
//
//                cardC.setOnBuyButtonClick(new OnBuyButtonClick() {
//                    @Override
//                    public void onBuy(ProductDTO product, int quantity) {
//                        fom_buy.setVisible(true); // Hiển thị form mua hàng
//                    }
//                });
//
//                if(column == 5){
//                    column = 0;
//                    row+=1;
//                }
//                product_gridPane.add(vBox, column++, row);
//            }catch (Exception e){
//                e.getStackTrace();
//            }
//        }
//    }

    public void productDisplay() {
        cardListData.clear();
        cardListData.addAll(productData());

        int row = 0;
        int column = 0;

        product_gridPane.getRowConstraints().clear();
        product_gridPane.getColumnConstraints().clear();
        product_gridPane.setHgap(10); // Khoảng cách ngang giữa các cột
        product_gridPane.setVgap(20); // Khoảng cách dọc giữa các hàng

        for (int q = 0; q < cardListData.size(); q++) {
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/com/javanc/cardProduct.fxml"));

                VBox vBox = loader.load();
                cardProductController cardC = loader.getController();

                // Set thông tin sản phẩm vào card
                ProductDTO currentProduct = cardListData.get(q);
                cardC.setData(currentProduct);

                // Gắn sự kiện khi click "Mua ngay"
                cardC.setOnBuyButtonClick((product, quantity) -> {
                    // Hiển thị form mua hàng
                    fom_buy.setVisible(true);

                    // Lấy thông tin người dùng (giả định ID người dùng hiện tại là 1)
                    UserDTO currentUser = getUserData(1);

                    // Hiển thị thông tin người dùng
                    bill_name.setText(currentUser.getFullname());
                    bill_phone.setText(currentUser.getPhone());
                    bill_productAddress.setText(currentUser.getAddress());

                    // Hiển thị thông tin sản phẩm
                    bill_productName.setText(product.getName());
                    bill_productQuantity.setText(String.valueOf(quantity));

                    // Tính và hiển thị tổng tiền
                    double total = product.getPrice() * quantity;
                    bill_total.setText(String.format("%.2f", total));
                });

                if (column == 5) {
                    column = 0;
                    row += 1;
                }
                product_gridPane.add(vBox, column++, row);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }




    public void closeFomBuy() {
        fom_buy.setVisible(false);
    }



    //    public ObservableList<ProductDTO> productNewData(){
//        String sql = "select * from product";
//        connect = database.connectDB();
//
//        ObservableList<ProductDTO> listData = FXCollections.observableArrayList();
//        try{
//            pr = connect.prepareStatement(sql);
//            rs = pr.executeQuery();
//
//            ProductDTO productDTO;
//            while(rs.next()){
//                productDTO = new ProductDTO(rs.getString("product_id")
//                        ,rs.getString("product_name")
//                        ,rs.getString("product_author")
//                        ,rs.getFloat("product_price")
//                        ,rs.getString("product_image"));
//                listData.add(productDTO);
//            }
//        }catch (Exception e){
//            e.getStackTrace();
//        }
//        return listData;
//    }


    private ObservableList<ProductDTO> cardListDataNew = FXCollections.observableArrayList();
    public ObservableList<ProductDTO> productNewData(){
        String sql = "SELECT p.product_id, p.product_name, p.product_author, p.product_price, p.product_image, SUM(bd.quantity) AS total_sold " +
                "FROM product p " +
                "JOIN billdetail bd ON p.product_id = bd.product_id " +
                "GROUP BY p.product_id, p.product_name, p.product_author, p.product_price, p.product_image " +
                "ORDER BY total_sold DESC " +
                "LIMIT 4";
        connect = database.connectDB();
        ObservableList<ProductDTO> listData = FXCollections.observableArrayList();

        try {
            pr = connect.prepareStatement(sql);
            rs = pr.executeQuery();

            while (rs.next()) {
                ProductDTO productDTO = new ProductDTO(
                        rs.getString("product_id"),
                        rs.getString("product_name"),
                        rs.getString("product_author"),
                        rs.getFloat("product_price"),  // Lấy giá sản phẩm
                        rs.getString("product_image")  // Lấy tên hình ảnh sản phẩm
                );
                listData.add(productDTO);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listData;
    }


//    public ObservableList<ProductDTO> productNewData(){
//        String sql = "select * from product";
//        connect = database.connectDB();
//
//        ObservableList<ProductDTO> listData = FXCollections.observableArrayList();
//        try{
//            pr = connect.prepareStatement(sql);
//            rs = pr.executeQuery();
//
//            ProductDTO productDTO;
//            while(rs.next()){
//                productDTO = new ProductDTO(rs.getString("product_id")
//                        ,rs.getString("product_name")
//                        ,rs.getString("product_author")
//                        ,rs.getFloat("product_price")
//                        ,rs.getString("product_image"));
//                listData.add(productDTO);
//            }
//        }catch (Exception e){
//            e.getStackTrace();
//        }
//        return listData;
//    }

    public void productNewDisplay(){
        cardListDataNew.clear();
        cardListDataNew.addAll(productNewData());

        int row =0;
        int  column =0;

        outstand_gridPane.getRowConstraints().clear();
        outstand_gridPane.getColumnConstraints().clear();
        for(int q = 0; q< cardListDataNew.size(); q++) {
            try{
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/com/javanc/cardProductNew.fxml"));
                HBox hBox = loader.load();
                cardProductNewController cardC = loader.getController();
                cardC.setData(cardListDataNew.get(q));
                if(column == 4){
                    column = 0;
                    row+=1;
                }
                outstand_gridPane.add(hBox, column++, row);
            }catch (Exception e){
                e.getStackTrace();
            }
        }
    }


    public void changeScreenBuy(){
        fom_buy.setVisible(true);
    }

    public UserDTO getUserData(int userId) {
        UserDTO user = null;
        String query = "SELECT fullname, phone, address FROM user WHERE id = ?";
        try (Connection conn = database.connectDB();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                user = new UserDTO(
                        rs.getString("fullname"),
                        rs.getString("phone"),
                        rs.getString("address")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    public ProductDTO getProductData(String productId) {
        ProductDTO product = null;
        String query = "SELECT product_name, product_quantity FROM product WHERE product_id = ?";
        try (Connection conn = database.connectDB();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, productId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                product = new ProductDTO(
                        rs.getString("product_name"),
                        rs.getInt("product_quantity")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return product;
    }


    private void setupPaymentMethods() {
        ObservableList<String> paymentMethods = FXCollections.observableArrayList(
                "Thanh toán bằng tài khoản ngân hàng",
                "Thanh toán khi nhận hàng"
        );
        bill_methodPay.setItems(paymentMethods);
    }

    // xu ly su kien khi kich nut dat hang
    @FXML
    private void handleBillOrderAction() {
        int userId = UserSession.getUserId();
        int quantity = Integer.parseInt(bill_productQuantity.getText());
        double total = Double.parseDouble(bill_total.getText().replace("Giá: ", "").trim());
        String methodPay = bill_methodPay.getValue();
        LocalDate date = LocalDate.now();

        String query = "INSERT INTO bill (user_id, quantity, total, methodPay, doe) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = database.connectDB();
             PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) { // Sử dụng RETURN_GENERATED_KEYS

            stmt.setInt(1, userId);
            stmt.setInt(2, quantity);
            stmt.setDouble(3, total);
            stmt.setString(4, methodPay);
            stmt.setDate(5, java.sql.Date.valueOf(date));

            int affectedRows = stmt.executeUpdate();

            if (affectedRows > 0) {
                // Lấy billId vừa được tạo
                ResultSet generatedKeys = stmt.getGeneratedKeys();
                int billId = -1;
                if (generatedKeys.next()) {
                    billId = generatedKeys.getInt(1); // Lấy giá trị ID tự sinh
                }

                if (billId != -1) {
                    // Gọi hàm lưu chi tiết hóa đơn
                    saveBillDetail(billId, cardListDataNew); // Tham số thứ hai là danh sách sản phẩm

                    // Hiển thị thông báo thành công
                    showAlert(Alert.AlertType.INFORMATION, "Thông báo", "Đơn hàng đã được đặt thành công!");

                    // Ẩn form_buy sau khi đặt hàng
                    fom_buy.setVisible(false); // Hoặc form_buy.close() nếu là Stage
                } else {
                    showAlert(Alert.AlertType.ERROR, "Lỗi", "Không thể lấy ID hóa đơn vừa tạo.");
                }
            } else {
                showAlert(Alert.AlertType.ERROR, "Lỗi", "Lỗi khi lưu thông tin vào bảng bill.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Lỗi hệ thống", "Có lỗi xảy ra khi lưu thông tin đơn hàng.");
        }
    }



    private void saveBillDetail(int billId, List<ProductDTO> productList) {
        String query = "INSERT INTO billdetail (bill_id, product_id, quantity, total, doe) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = database.connectDB();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            LocalDate date = LocalDate.now(); // Ngày hiện tại

//            for (ProductDTO product : productList) {
//                stmt.setInt(1, billId); // Bill ID vừa mới lưu
//                stmt.setString(2, product.getId()); // Product ID
//                stmt.setInt(3, product.getQuantity()); // Quantity selected
//                stmt.setDouble(4, product.getPrice() * product.getQuantity()); // Total price for the product
//                stmt.setDate(5, java.sql.Date.valueOf(date)); // Ngày hiện tại
//
//                stmt.addBatch(); // Thêm vào batch
//            }

            for (ProductDTO product : productList) {
                if (product.getQuantity() == null) {
                    //showAlert(Alert.AlertType.ERROR, "Lỗi dữ liệu", "Sản phẩm không có số lượng hợp lệ: " + product.getId());
                    return; // Hoặc tiếp tục nếu muốn bỏ qua sản phẩm này
                }
                stmt.setInt(1, billId);
                stmt.setString(2, product.getId());
                stmt.setInt(3, product.getQuantity());
                stmt.setDouble(4, product.getPrice() * product.getQuantity());
                stmt.setDate(5, java.sql.Date.valueOf(date));
                stmt.addBatch();
            }

            int[] affectedRows = stmt.executeBatch(); // Thực hiện batch insert

            // Thông báo thành công
            showAlert(Alert.AlertType.INFORMATION, "Thông báo", "Đặt hàng thành công.");
        } catch (SQLException e) {
            e.printStackTrace();
            // Thông báo lỗi
            showAlert(Alert.AlertType.ERROR, "Lỗi hệ thống", "Có lỗi xảy ra khi lưu chi tiết đơn hàng.");
        }
    }


    public void showAlert(Alert.AlertType alertType, String title, String contentText) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(contentText);
        alert.showAndWait();
    }





    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        productNewDisplay();
        productDisplay();
        setupPaymentMethods();

        // Lắng nghe sự kiện click trên mainPane
        mainStack.setOnMouseClicked(event -> {
            // Nếu fom_buy đang hiển thị và click không xảy ra trong fom_buy
            if (fom_buy.isVisible() && !fom_buy.contains(event.getX(), event.getY())) {
                fom_buy.setVisible(false);
            }
        });

        // Ngăn sự kiện click trên fom_buy làm ẩn chính nó
        fom_buy.setOnMouseClicked(event -> event.consume());
    }
}
