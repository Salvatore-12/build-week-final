package Team3.buildweekfinal.services;
import Team3.buildweekfinal.payloads.UsersDTO;
import Team3.buildweekfinal.entities.User;
import Team3.buildweekfinal.exceptions.NotFoundException;
import Team3.buildweekfinal.repositories.UsersDAO;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UsersService
{
    @Autowired
    private UsersDAO usersDAO;
    @Autowired
    private Cloudinary cloudinaryUploader;

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
    public String uploadPicture(MultipartFile file) throws IOException
    {

        String url = (String) cloudinaryUploader.uploader()
                .upload(file.getBytes(), ObjectUtils.emptyMap())
                .get("url");
        return url;
    }

    public User findByEmail(String email)
    {
        return usersDAO.findByEmail(email).orElseThrow(()->new NotFoundException(email));
    }



}
