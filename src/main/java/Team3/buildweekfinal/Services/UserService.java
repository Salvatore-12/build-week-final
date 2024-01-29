package Team3.buildweekfinal.Services;
import Team3.buildweekfinal.Payloads.UsersDTO;
import Team3.buildweekfinal.Entities.User;
import Team3.buildweekfinal.repositories.UsersDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserService
{
    @Autowired
    private UsersDAO usersDAO;


    public Page<User> getUsers(int page,int size,String orderBy)
    {
        if(size>=100)size=100;
        Pageable pageable= PageRequest.of(page,size, Sort.by(orderBy));
        return usersDAO.findAll(pageable);
    }
    public User findById(UUID id)
    {
        //inserire NotFoundException
        return usersDAO.findById(id).orElseThrow(()->new RuntimeException());
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
}
