package com.javanc.DTO;

public class ProductDTO {
    private String id;
    private String name;
    private String author;
    private Integer quantity;
    private Float price;
    private String image;
    private String describe;
    private Integer categoryID;

    public ProductDTO(String id, String name, String author, Integer quantity, Float price, String image, String describe, Integer categoryID) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.quantity = quantity;
        this.price = price;
        this.image = image;
        this.describe = describe;
        this.categoryID = categoryID;
    }

    public ProductDTO(String id, String name, String author, Float price, String image) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.price = price;
        this.image = image;
    }

    public ProductDTO(String id, String name, String author, Integer quantity, Float price, String image) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.quantity = quantity;
        this.price = price;
        this.image = image;
    }

    public ProductDTO(String name, Integer quantity) {
        this.name = name;
        this.quantity = quantity;
    }



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public Integer getCategoryID() {
        return categoryID;
    }

    public void setCategory(Integer categoryID) {
        this.categoryID = categoryID;
    }

//    private String id;
//    private String name;
//    private String author;
//    private String category;
//    private Integer quantity;
//    private Float price;
//    private String image;
//    private String describe;
//
//    public ProductDTO(String id, String name, String author, String category, Integer quantity, Float price, String image, String describe) {
//        this.id = id;
//        this.name = name;
//        this.author = author;
//        this.category = category;
//        this.quantity = quantity;
//        this.price = price;
//        this.image = image;
//        this.describe = describe;
//    }
//
//    public String getId() {
//        return id;
//    }
//
//    public void setId(String id) {
//        this.id = id;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public String getAuthor() {
//        return author;
//    }
//
//    public void setAuthor(String author) {
//        this.author = author;
//    }
//
//    public String getCategory() {
//        return category;
//    }
//
//    public void setCategory(String category) {
//        this.category = category;
//    }
//
//    public Integer getQuantity() {
//        return quantity;
//    }
//
//    public void setQuantity(Integer quantity) {
//        this.quantity = quantity;
//    }
//
//    public Float getPrice() {
//        return price;
//    }
//
//    public void setPrice(Float price) {
//        this.price = price;
//    }
//
//    public String getImage() {
//        return image;
//    }
//
//    public void setImage(String image) {
//        this.image = image;
//    }
//
//    public String getDescribe() {
//        return describe;
//    }
//
//    public void setDescribe(String describe) {
//        this.describe = describe;
//    }
}

