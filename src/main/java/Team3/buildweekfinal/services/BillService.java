package Team3.buildweekfinal.services;

import Team3.buildweekfinal.entities.Bill;
import Team3.buildweekfinal.entities.Client;
import Team3.buildweekfinal.exceptions.NotFoundException;
import Team3.buildweekfinal.payloads.BillDTO;
import Team3.buildweekfinal.payloads.UsersDTO;
import Team3.buildweekfinal.repositories.BillsDAO;
import Team3.buildweekfinal.repositories.ClientsDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class BillService {
    @Autowired
    private BillsDAO billsDAO;
    @Autowired
    private ClientsDAO clientsDAO;
    @Autowired
    private ClientService clientService;

    public Page<Bill> getBill(int page, int size, String orderBy) {
        if (size >= 100) size = 100;
        Pageable pageable = PageRequest.of(page, size, Sort.by(orderBy));
        return billsDAO.findAll(pageable);
    }

    public Bill findById(UUID idBill) {
        return billsDAO.findById(idBill).orElseThrow(() -> new NotFoundException(idBill));
    }

    public Bill save(BillDTO body, UUID id) {
        Bill newbill = new Bill();
        newbill.setDate(body.date());
        newbill.setTotal(body.total());
        newbill.setBillNumber(body.billNumber());
        newbill.setClient(clientsDAO.findById(id).orElseThrow(() -> new NotFoundException(id)));
        return billsDAO.save(newbill);
    }

    public Bill findByIdAndUpdate(UUID idbill, BillDTO body) {
        Bill found = this.findById(idbill);
        found.setDate(body.date());
        found.setTotal(body.total());
        found.setBillNumber(body.billNumber());
        return billsDAO.save(found);
    }

    public void findByIdAndDelete(UUID idbill) {
        Bill found = this.findById(idbill);
        billsDAO.delete(found);
    }

    public Page<Bill> findBillByClient(int page, int size, String sortBy, UUID idClient) {
        if (size < 0)
            size = 10;
        if (size > 100)
            size = 20;
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return billsDAO.findByClientPiva(pageable, idClient);
    }
}
