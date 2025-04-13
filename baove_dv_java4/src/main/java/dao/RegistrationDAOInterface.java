package dao;

import java.util.List;

public interface RegistrationDAOInterface {
    List<Registration> findAll();
    Registration findByUsername(String username);
    List<Registration> searchByLastname(String lastname);
    void save(Registration registration);
    void deleteByUsername(String username);
    boolean isUsernameExist(String username);
}
