package com.javanc.controller;

import com.javanc.DTO.ProductDTO;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

public class cardProductController implements Initializable {
    @FXML
    private VBox vbox_product;

    @FXML
    private ImageView product_ImageView;

    @FXML
    private Label product_name;

    @FXML
    private Label product_author;

    @FXML
    private Spinner<Integer> product_quantity;

    @FXML
    private Label product_price;

    @FXML
    private Button addCartBtn;

    @FXML
    private Button buyBtn;


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
    private ComboBox<?> bill_methodPay;

    @FXML
    private Button bill_order;

    private ProductDTO productDTO;
    private Image image;
    private SpinnerValueFactory<Integer> spin;

    public void setData(ProductDTO productDTO){
        this.productDTO = productDTO;

        String path = "File:" +productDTO.getImage();
        image = new Image(path, 219, 180, false, true);
        product_ImageView.setImage(image);
        product_name.setText(productDTO.getName());
        product_author.setText(productDTO.getAuthor());
        //product_quantity.getValueFactory().setValue(productDTO.getQuantity());
        product_price.setText(String.valueOf(productDTO.getPrice()));

        // Cập nhật giá trị cho Spinner và hiển thị giá ban đầu
        if (productDTO.getQuantity() != null) {
            product_quantity.getValueFactory().setValue(productDTO.getQuantity());
        }
        updateProductPrice();
    }

    private void updateProductPrice() {
        int quantity = product_quantity.getValue();
        double price = productDTO.getPrice();
        double total = quantity * price;

        product_price.setText(String.format("Giá: %.2f", total));
    }

    public void setQuantity(){
        spin = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 100, 1);
        product_quantity.setValueFactory(spin);
    }

    private OnBuyButtonClick buyButtonClickCallback;

    public void setOnBuyButtonClick(OnBuyButtonClick callback) {
        this.buyButtonClickCallback = callback;
    }


//    public void changeScreenBuy() {
//        if (buyButtonClickCallback != null) {
//            buyButtonClickCallback.onBuy();
//        } else {
//            System.out.println("No callback defined for buy button.");
//        }
//    }
    public void changeScreenBuy() {
        if (buyButtonClickCallback != null) {
            // Lấy thông tin sản phẩm và số lượng từ giao diện
            int quantity = product_quantity.getValue();
            buyButtonClickCallback.onBuy(productDTO, quantity);
        } else {
            System.out.println("No callback defined for buy button.");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setQuantity();

        // Lắng nghe sự thay đổi của Spinner và cập nhật giá khi số lượng thay đổi
        product_quantity.valueProperty().addListener((obs, oldValue, newValue) -> updateProductPrice());

        // Gắn sự kiện cho nút Mua ngay
        buyBtn.setOnAction(event -> changeScreenBuy());
    }


}
