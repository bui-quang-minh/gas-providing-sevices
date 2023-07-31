/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Minh Bui
 */
public class ProductImage {
    private int productImageID;
    private int productID;
    private byte[] productImage;
    private String encodedProductImage;
    private String imageType;

    public ProductImage(int productImageID, int productID, byte[] productImage, String encodedProductImage) {
        this.productImageID = productImageID;
        this.productID = productID;
        this.productImage = productImage;
        this.encodedProductImage = encodedProductImage;
    }

    public ProductImage(int productImageID, int productID, byte[] productImage, String encodedProductImage, String imageType) {
        this.productImageID = productImageID;
        this.productID = productID;
        this.productImage = productImage;
        this.encodedProductImage = encodedProductImage;
        this.imageType = imageType;
    }

    public String getImageType() {
        return imageType;
    }

    public void setImageType(String imageType) {
        this.imageType = imageType;
    }

    public ProductImage() {
    }

    public int getProductImageID() {
        return productImageID;
    }

    public void setProductImageID(int productImageID) {
        this.productImageID = productImageID;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public byte[] getProductImage() {
        return productImage;
    }

    public void setProductImage(byte[] productImage) {
        this.productImage = productImage;
    }

    public String getEncodedProductImage() {
        return encodedProductImage;
    }

    public void setEncodedProductImage(String encodedProductImage) {
        this.encodedProductImage = encodedProductImage;
    }
    
    
}
