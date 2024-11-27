//package com.javanc.controller;
//
//import com.javanc.DTO.ProductDTO;
//import com.javanc.database.database;
//import javafx.collections.FXCollections;
//import javafx.collections.ObservableList;
//import javafx.fxml.FXML;
//import javafx.fxml.Initializable;
//import javafx.scene.control.TableColumn;
//import javafx.scene.control.TableView;
//import javafx.scene.control.cell.PropertyValueFactory;
//
//import java.net.URL;
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.Statement;
//import java.util.ResourceBundle;
//
//public class testController implements Initializable {
//    @FXML
//    private TableView<ProductDTO> table;
//
//    @FXML
//    private TableColumn<ProductDTO, String> ma;
//
//    @FXML
//    private TableColumn<ProductDTO, String> ten;
//
//    @FXML
//    private TableColumn<ProductDTO, String> tacgia;
//
//    @FXML
//    private TableColumn<ProductDTO, String> theloai;
//
//    @FXML
//    private TableColumn<ProductDTO, String> soluong;
//
//    @FXML
//    private TableColumn<ProductDTO, String> gia;
//
//    @FXML
//    private TableColumn<ProductDTO, String> anh;
//
//    @FXML
//    private TableColumn<ProductDTO, String> trangthai;
//
//    private ObservableList<ProductDTO> inventoryListData;
//    private Connection connect;
//    private PreparedStatement pr;
//    private Statement st;
//    private ResultSet rs;
//
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
//                        , rs.getString("product_status"));
//                listData.add(productDTO);
//            }
//
//        } catch (Exception e) {
//            e.getStackTrace();
//        }
//        return listData;
//    }
//
//    public void inventoryShowData() {
//        inventoryListData = inventoryDataList(); // Gọi phương thức lấy dữ liệu
//
//
//        //ma.setCellValueFactory();
//        ten.setCellValueFactory(new PropertyValueFactory<>("name"));
//        tacgia.setCellValueFactory(new PropertyValueFactory<>("author"));
//        theloai.setCellValueFactory(new PropertyValueFactory<>("category"));
//        soluong.setCellValueFactory(new PropertyValueFactory<>("quantity"));
//        gia.setCellValueFactory(new PropertyValueFactory<>("price"));
//        anh.setCellValueFactory(new PropertyValueFactory<>("image"));
//        trangthai.setCellValueFactory(new PropertyValueFactory<>("status"));
//        ma.setCellValueFactory(new PropertyValueFactory<>("id"));
//        // Thiết lập danh sách dữ liệu cho TableView
//        table.setItems(inventoryListData);
//    }
//
//    @Override
//    public void initialize(URL url, ResourceBundle resourceBundle) {
//        inventoryShowData();
//
//    }
//}