package Team3.buildweekfinal.services;

import Team3.buildweekfinal.entities.CTYPE;
import Team3.buildweekfinal.entities.Client;
import Team3.buildweekfinal.Payloads.ClientsDTO;
import Team3.buildweekfinal.exceptions.NotFoundException;
import Team3.buildweekfinal.repositories.ClientsDAO;
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
import java.time.LocalDate;
import java.util.UUID;

@Service
public class ClientService
{
    @Autowired
    private ClientsDAO clientsDAO;
    @Autowired
    private Cloudinary cloudinaryUploader;

    public Page<Client> getClients(int page, int size, String orderBy)
    {
        if(size<=100)size=100;
        Pageable pageable= PageRequest.of(page,size, Sort.by(orderBy));
        return clientsDAO.findAll(pageable);
    }
    public Client findByID(UUID id)
    {

        return clientsDAO.findById(id).orElseThrow(()->new NotFoundException(id));
    }
    public void findByIdAndDelete(UUID id)
    {
        Client found=this.findByID(id);
        clientsDAO.delete(found);
    }
    public Client findByIdAndUpdate(UUID id, ClientsDTO body)
    {
        Client found=this.findByID(id);
        found.setEmail(body.email());
        found.setInsertDate(body.insertDate());
        found.setLastCall(body.lastCall());
        found.setAnnualTurnOver(body.annualTurnOver());
        found.setPec(body.pec());
        found.setNumber(body.number());
        found.setCtype(CTYPE.valueOf(body.ctype()));
        return clientsDAO.save(found);
    }
    public String uploadPicture(MultipartFile file) throws IOException {

        String url = (String) cloudinaryUploader.uploader()
                .upload(file.getBytes(), ObjectUtils.emptyMap())
                .get("url");
        return url;
    }
    public Client findByannualTurnOver(double annualTurnOver)
    {
        return clientsDAO.findByannualTurnOver(annualTurnOver).orElseThrow(()->new NotFoundException(String.valueOf(annualTurnOver)));
    }
    public  Client findByInsertData(LocalDate inserData){
        return clientsDAO.orderByInsertDate(inserData).orElseThrow(()->new NotFoundException(String.valueOf(inserData)));
    }
    public  Client findByLastCall(LocalDate lastCall){
        return clientsDAO.orderByLastCall(lastCall).orElseThrow(()->new NotFoundException(String.valueOf(lastCall)));
    }


}
