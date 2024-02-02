package Team3.buildweekfinal.services;

import Team3.buildweekfinal.entities.*;
import Team3.buildweekfinal.payloads.ClientsDTO;
import Team3.buildweekfinal.exceptions.NotFoundException;
import Team3.buildweekfinal.repositories.AddressDAO;
import Team3.buildweekfinal.repositories.BillsDAO;
import Team3.buildweekfinal.repositories.ClientsDAO;
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
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class ClientService
{
    @Autowired
    private ClientsDAO clientsDAO;
    @Autowired
    private BillsDAO billsDAO;
    @Autowired
    private UsersDAO usersDAO;
    @Autowired
    private AddressDAO addressDAO;
    @Autowired
    private Cloudinary cloudinaryUploader;

    public Page<Client> getClients(int page, int size, String orderBy)
    {
        if (size < 0)
            size = 10;
        if (size > 100)
            size = 20;
        Pageable pageable= PageRequest.of(page,size, Sort.by(orderBy));
        return clientsDAO.findAll(pageable);
    }
    public Client findById(UUID id)
    {
        return clientsDAO.findById(id).orElseThrow(()->new NotFoundException(id));
    }
    public void findByIdAndDelete(UUID id)
    {
        Client found=this.findById(id);
        clientsDAO.delete(found);
    }
    public Client save(UUID idBill,UUID idUser,UUID idAddress, ClientsDTO body)
    {
        Client newclient=new Client();
        newclient.setUser(usersDAO.findById(idUser).orElseThrow(()->new NotFoundException(idUser)));
        List<Bill> billList=new ArrayList<>();
        billList.add(billsDAO.findById(idBill).orElseThrow(()->new NotFoundException(idBill)));
        newclient.setBills(billList);
        List<Address> addressList=new ArrayList<>();
        addressList.add(addressDAO.findById(idAddress).orElseThrow(()->new NotFoundException(idAddress)));
        newclient.setAddress(addressList);
        newclient.setEmail(body.email());
        newclient.setName(body.name());
        newclient.setInsertDate(body.insertDate());
        newclient.setLastCall(body.lastCall());
        newclient.setAnnualTurnOver(body.annualTurnOver());
        newclient.setNumber(body.number());
        newclient.setPec(body.pec());
        newclient.setCtype(CTYPE.valueOf(body.ctype()));
        return clientsDAO.save(newclient);

    }
    public Client findByIdAndUpdate(UUID id, ClientsDTO body)
    {
        Client found=this.findById(id);
        found.setEmail(body.email());
        found.setInsertDate(body.insertDate());
        found.setLastCall(body.lastCall());
        found.setAnnualTurnOver(body.annualTurnOver());
        found.setPec(body.pec());
        found.setNumber(body.number());
        found.setName(body.name());
        found.setCtype(CTYPE.valueOf(body.ctype()));
        return clientsDAO.save(found);
    }
    public String uploadPicture(MultipartFile file) throws IOException {

        String url = (String) cloudinaryUploader.uploader()
                .upload(file.getBytes(), ObjectUtils.emptyMap())
                .get("url");
        return url;
    }
    public Page<Client> findPersonalClients(int page,int size,String orderBy, User currentUser){
        if(size>=100)size=100;
        Pageable pageable= PageRequest.of(page,size, Sort.by(orderBy));
        return clientsDAO.findByUserIdUser(pageable, currentUser.getIdUser());

    }
    //****************************************************************QUERY****************************************************************
    public Client findByAnnualTurnOver(double annualTurnOver)
    {
        return clientsDAO.findByAnnualTurnOver(annualTurnOver).orElseThrow(()->new NotFoundException(String.valueOf(annualTurnOver)));
    }
    public Client findByInsertDate(LocalDate insertDate)
    {
        return clientsDAO.findByInsertDate(insertDate).orElseThrow(()->new NotFoundException(String.valueOf(insertDate)));
    }
    public Client findBylastCall(LocalDate lastCall)
    {
        return clientsDAO.findByLastCall(lastCall).orElseThrow(()->new NotFoundException(String.valueOf(lastCall)));

    }
    public User findByPartName(String name)
    {
        return usersDAO.findByNameContainingIgnoreCase(name).orElseThrow(()->new NotFoundException(name));
    }
   public Bill findByEmail(String email)
   {
       return clientsDAO.findByEmail(email).orElseThrow(()->new NotFoundException(email));
   }
   public Bill findByCtype(CTYPE ctype)
   {
       return clientsDAO.findByCtype(ctype).orElseThrow(()->new NotFoundException(String.valueOf(ctype)));
   }
    public Bill findBydate(LocalDate date)
    {
        return billsDAO.findByDate(date).orElseThrow(()->new NotFoundException(String.valueOf(date)));
    }
    public Bill findByYear(int year)
    {
        return billsDAO.filterByYear(year).orElseThrow(()->new NotFoundException(String.valueOf(year)));
    }
    public Bill filterBytotal(double minAmount,double maxAmount)
    {
        return billsDAO.filterBytotal(minAmount,maxAmount).orElseThrow(()->new NotFoundException("fatture non trovate"));
    }
}
