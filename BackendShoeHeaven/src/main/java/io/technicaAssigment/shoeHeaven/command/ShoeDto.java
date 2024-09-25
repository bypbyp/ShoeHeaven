package io.technicaAssigment.shoeHeaven.command;
import io.technicaAssigment.shoeHeaven.persistence.Style;

import javax.validation.constraints.*;

public class ShoeDto {
    private int id;

    @NotNull(message = "Brand is mandatory")
    @NotBlank(message = "Brand is mandatory")
    @Size(min = 1, max = 30)
    private String brand;

    @NotNull(message = "Name is mandatory")
    @NotBlank(message = "Name is mandatory")
    @Size(min = 2 , max = 100)
    private String name;

    private Style style;
    private String color;
    private int number;
    private int quantity;
    private double price;
    private String image;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public @NotNull(message = "Brand is mandatory") @NotBlank(message = "Brand is mandatory") @Size(min = 1, max = 30) String getBrand() {
        return brand;
    }

    public void setBrand(@NotNull(message = "Brand is mandatory") @NotBlank(message = "Brand is mandatory") @Size(min = 1, max = 30) String brand) {
        this.brand = brand;
    }

    public @NotNull(message = "Name is mandatory") @NotBlank(message = "Name is mandatory") @Size(min = 2, max = 100) String getName() {
        return name;
    }

    public void setName(@NotNull(message = "Name is mandatory") @NotBlank(message = "Name is mandatory") @Size(min = 2, max = 100) String name) {
        this.name = name;
    }

    public Style getStyle() {
        return style;
    }

    public void setStyle(Style style) {
        this.style = style;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
