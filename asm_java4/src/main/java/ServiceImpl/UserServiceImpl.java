package ServiceImpl;

import java.util.List;

import dao.UserDao;
import entily.User;
import impl.UserDaoImpl;
import service.UserService;

public class UserServiceImpl implements UserService{
private UserDao dao;
public UserServiceImpl() {
	dao = new UserDaoImpl();
}
	@Override
	public User findById(Integer id) {
		// TODO Auto-generated method stub
		return dao.findById(id);
	}

	@Override
	public User findByEmail(String email) {
		// TODO Auto-generated method stub
		return dao.findByEmail(email);
	}

	@Override
	public User findByUsername(String username) {
		// TODO Auto-generated method stub
		return dao.findByUsername(username);
	}

	@Override
	public User login(String username, String passwords) {
		// TODO Auto-generated method stub
		return dao.findByUsernameAndPassword(username, passwords);
	}

	@Override
	public User resetPassword(String email) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> findAll() {
		// TODO Auto-generated method stub
		return dao.findAll();
	}

	@Override
	public List<User> findAll(int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
		return dao.findAll(pageNumber, pageSize);
	}

	@Override
	public User register(String username, String passwords, String email) {
		// TODO Auto-generated method stub
		User newUser = new User( );
		newUser.setUsername(username);
		newUser.setPassword(passwords);
		newUser.setEmail(email);
		newUser.setIsAdmin(Boolean. FALSE);
		newUser.setIsActive(Boolean.TRUE);
		return dao.create(newUser);
	}

	@Override
	public User update(User entity) {
		// TODO Auto-generated method stub
		return dao.update(entity);
	}

	@Override
	public User delete(String username) {
		// TODO Auto-generated method stub
		User users = dao.findByUsername(username);
		users.setIsActive(Boolean.FALSE);
		return dao. update(users);
	}
	@Override
	public boolean register(User newUser) {
		// TODO Auto-generated method stub
		return false;
	}

}
