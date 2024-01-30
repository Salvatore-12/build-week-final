package Team3.buildweekfinal.services;
import Team3.buildweekfinal.payloads.UsersDTO;
import Team3.buildweekfinal.entities.User;
import Team3.buildweekfinal.exceptions.NotFoundException;
import Team3.buildweekfinal.repositories.UsersDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UsersService
{
    @Autowired
    private UsersDAO usersDAO;

    public static Optional<User> getUserById(UUID idUser) {
        return null;
    }

    public static User saveUser(User user) {
        return null;
    }

    public static Optional<User> updateUser(UUID idUser, User updatedUser) {
        return null;
    }

    public static Object deleteUser(UUID idUser) {
        return null;
    }


    public Page<User> getUsers(int page,int size,String orderBy)
    {
        if(size>=100)size=100;
        Pageable pageable= PageRequest.of(page,size, Sort.by(orderBy));
        return usersDAO.findAll(pageable);
    }
    public User findById(UUID id)
    {
       return usersDAO.findById(id).orElseThrow(()->new NotFoundException(id));
    }
    public void findByIdAndDelete(UUID id)
    {
        User found=this.findById(id);
        usersDAO.delete(found);
    }
    public User findByIdAndUpdate(UUID id, UsersDTO body)
    {
        User found=this.findById(id);
        found.setName(body.name());
        found.setSurname(body.surname());
        found.setEmail(body.email());
        found.setUsername(body.username());
        found.setPassword(body.password());//inserire bcrypt
        return usersDAO.save(found);
    }
    public User findByEmail(String email)
    {
        return usersDAO.findByEmail(email).orElseThrow(()->new NotFoundException(email));
    }
    public User findByName(String name)
    {
        return usersDAO.findByName(name).orElseThrow(()->new NotFoundException(name));
    }

    public List<User> getAllUser() {
        return null;
    }
}
