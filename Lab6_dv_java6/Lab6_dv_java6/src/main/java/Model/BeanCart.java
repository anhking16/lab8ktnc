package Model;

import java.util.HashMap;
import java.util.Map;

public class BeanCart {
    private Map<String, Integer> cart;

    public BeanCart() {
        cart = new HashMap<>();
    }

    // Thêm sản phẩm vào giỏ hàng
    public void addToCart(String item) {
        if (cart.containsKey(item)) {
            // Nếu sản phẩm đã tồn tại, tăng số lượng
            cart.put(item, cart.get(item) + 1);
        } else {
            // Nếu chưa tồn tại, thêm sản phẩm với số lượng 1
            cart.put(item, 1);
        }
    }

    // Xóa sản phẩm khỏi giỏ hàng
    public void deleteItem(String item) {
        if (cart.containsKey(item)) {
            // Giảm số lượng hoặc xóa nếu số lượng là 1
            int quantity = cart.get(item);
            if (quantity > 1) {
                cart.put(item, quantity - 1);
            } else {
                cart.remove(item);
            }
        }
    }

    // Lấy toàn bộ giỏ hàng
    public Map<String, Integer> getCart() {
        return cart;
    }
}
