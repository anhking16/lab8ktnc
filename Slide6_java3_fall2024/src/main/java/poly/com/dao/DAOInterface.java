package poly.com.dao;

import java.util.List;

public interface DAOInterface<E, K> {
	List<E> selectAll(); // Tìm tất cả Entities
	E selectById(K id); // Tìm Entity theo Key
	int insert(E entity); // Thêm mới Entity
	int update(E entity); // Sửa Entity
	void delete(K id);  // Xoá Entity với Key
	void printAll(); // In tấy cả Entities ra màn hình
}
