package darius.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

public class Product implements Serializable {
    private Long id;
    private String name;
    private String productCode;
    private String pictureHref;
    private BigDecimal ronPricePerUnit;
    private Integer taxPercentage;
    private Integer availableQuantity;

    private String category;
    private Integer starRating;

    private List<Review> reviewList;

    public Product() {
    }

    public Product(Long id) {
        this.id = id;
    }

    public Product(Long id, String name, String productCode, String pictureHref, BigDecimal ronPricePerUnit, Integer taxPercentage, Integer availableQuantity, String category, Integer starRating) {
        this.id = id;
        this.name = name;
        this.productCode = productCode;
        this.pictureHref = pictureHref;
        this.ronPricePerUnit = ronPricePerUnit;
        this.taxPercentage = taxPercentage;
        this.availableQuantity = availableQuantity;
        this.category = category;
        this.starRating = starRating;
    }

    public Product(String name, String productCode, String pictureHref, BigDecimal ronPricePerUnit, Integer taxPercentage, Integer availableQuantity, String category, Integer starRating) {
        this.name = name;
        this.productCode = productCode;
        this.pictureHref = pictureHref;
        this.ronPricePerUnit = ronPricePerUnit;
        this.taxPercentage = taxPercentage;
        this.availableQuantity = availableQuantity;
        this.category = category;
        this.starRating = starRating;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Integer getStarRating() {
        return starRating;
    }

    public void setStarRating(Integer starRating) {
        this.starRating = starRating;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getPictureHref() {
        return pictureHref;
    }

    public void setPictureHref(String pictureHref) {
        this.pictureHref = pictureHref;
    }

    public BigDecimal getRonPricePerUnit() {
        return ronPricePerUnit;
    }

    public void setRonPricePerUnit(BigDecimal ronPricePerUnit) {
        this.ronPricePerUnit = ronPricePerUnit;
    }

    public Integer getTaxPercentage() {
        return taxPercentage;
    }

    public void setTaxPercentage(Integer taxPercentage) {
        this.taxPercentage = taxPercentage;
    }

    public Integer getAvailableQuantity() {
        return availableQuantity;
    }

    public void setAvailableQuantity(Integer availableQuantity) {
        this.availableQuantity = availableQuantity;
    }

    public List<Review> getReviewList() {
        return reviewList;
    }

    public void setReviewList(List<Review> reviewList) {
        this.reviewList = reviewList;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", productCode='" + productCode + '\'' +
                ", pictureHref='" + pictureHref + '\'' +
                ", ronPricePerUnit=" + ronPricePerUnit +
                ", taxPercentage=" + taxPercentage +
                ", availableQuantity=" + availableQuantity +
                ", category='" + category + '\'' +
                ", starRating=" + starRating +
                ", reviewList=" + reviewList +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(id, product.id) && Objects.equals(name, product.name) && Objects.equals(productCode, product.productCode) && Objects.equals(pictureHref, product.pictureHref) && Objects.equals(ronPricePerUnit, product.ronPricePerUnit) && Objects.equals(taxPercentage, product.taxPercentage) && Objects.equals(availableQuantity, product.availableQuantity) && Objects.equals(category, product.category) && Objects.equals(starRating, product.starRating) && Objects.equals(reviewList, product.reviewList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, productCode, pictureHref, ronPricePerUnit, taxPercentage, availableQuantity, category, starRating, reviewList);
    }
}