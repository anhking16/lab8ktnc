package impl;

import java.util.List;

import dao.AbstractDao;
import dao.UserDao;
import entily.User;

public class UserDaoImpl extends AbstractDao<User> implements UserDao {

	@Override
	public User findById(Integer id) {
		// TODO Auto-generated method stub
		return super.findById(User.class, id);
	}

	
	@Override
	public User findByEmail(String email) {
		// TODO Auto-generated method stub
		String sql = "SELECT o FROM User o WHERE o.email = ?0";
		return super.findOne(User.class, sql, email);
	}

	
	@Override
	public User findByUsername(String username) {
		// TODO Auto-generated method stub
		String sql = "SELECT o FROM User o WHERE o.username = ?0";
		return super.findOne(User.class, sql, username);
	}

	
	@Override
	public User findByUsernameAndPassword(String username, String passwords) {
		// TODO Auto-generated method stub
		String sql = "SELECT o FROM User o WHERE o.username = ?0 AND o.passwords = ?1";
		return super.findOne(User.class, sql, username, passwords);
	}

	
	@Override
	public List<User> findAll() {
		// TODO Auto-generated method stub
		return super.findAll(User.class, true);
	}

	

	@Override
	public List<User> findAll(int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
		return super.findAll(User.class,true, pageNumber, pageSize);
	}

}
