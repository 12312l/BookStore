package com.javanc.controller;

import com.javanc.DTO.ProductDTO;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

import java.net.URL;
import java.util.ResourceBundle;

public class cardProductNewController implements Initializable {
    @FXML
    private HBox cardProduct_form;

    @FXML
    private ImageView product_imageView;

    @FXML
    private Label product_name;

    @FXML
    private Label product_author;


    @FXML
    private Label product_price;

    @FXML
    private Button product_view;

    private ProductDTO productDTO;
    private Image image;

    public void setData(ProductDTO productDTO){
        this.productDTO = productDTO;

        String path = "File:" +productDTO.getImage();
        image = new Image(path, 135, 185, false, true);
        product_imageView.setImage(image);
        product_name.setText(productDTO.getName());
        product_author.setText(productDTO.getAuthor());
        product_price.setText(String.valueOf(productDTO.getPrice()));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
