package com.example.akashadiyath;

public class model {
    String id,product_name,productprice,package1,description,productimage;

    public model(String id, String product_name, String productprice, String package1, String description, String productimage) {
        this.id = id;
        this.product_name = product_name;
        this.productprice = productprice;
        this.package1 = package1;
        this.description = description;
        this.productimage = productimage;
    }

    public String getId() {
        return id;
    }

    public String getProduct_name() {
        return product_name;
    }

    public String getProductprice() {
        return productprice;
    }

    public String getPackage1() {
        return package1;
    }

    public String getDescription() {
        return description;
    }

    public String getProductimage() {
        return productimage;
    }
}
