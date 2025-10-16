package mftplus.model.entity;

import com.google.gson.Gson;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@NoArgsConstructor
@SuperBuilder
@Getter
@Setter
public class Product {
    private int productId;
    private String Name;
    private int price;
    private int stock;

    @Override
    public String toString() {
        Gson gson = new Gson();
        return gson.toJson(this);

    }
}
