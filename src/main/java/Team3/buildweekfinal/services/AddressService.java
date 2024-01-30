package Team3.buildweekfinal.services;

import Team3.buildweekfinal.entities.Address;
import Team3.buildweekfinal.entities.User;
import Team3.buildweekfinal.exceptions.NotFoundException;
import Team3.buildweekfinal.payloads.AddressDTO;
import Team3.buildweekfinal.repositories.AddressDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AddressService {
    @Autowired
    private AddressDAO addressDAO;

    public Page<Address> getAllAdresses(int page, int size, String orderBy)
    {
        if(size>=100)size=100;
        Pageable pageable= PageRequest.of(page,size, Sort.by(orderBy));
        return addressDAO.findAll(pageable);
    }
    public Address findById(UUID id)
    {
        return addressDAO.findById(id).orElseThrow(()->new NotFoundException(id));
    }


    public Address createNewAddress (AddressDTO body){
        Address newAddress = new Address();
        newAddress.setAddress(body.address());
        newAddress.setCv(body.cv());
        newAddress.setCity(body.city());
        newAddress.setCap(body.cap());
        return addressDAO.save(newAddress);
    }
/*    public Address findAdressByIdAndUpdate (AddressDTO body){

        newAddress.setAddress(body.address());
        newAddress.setCv(body.cv());
        newAddress.setCity(body.city());
        newAddress.setCap(body.cap());
        return addressDAO.save(newAddress);
    }*/


}
