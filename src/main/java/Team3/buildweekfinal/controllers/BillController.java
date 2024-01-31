package Team3.buildweekfinal.controllers;

import Team3.buildweekfinal.entities.Bill;
import Team3.buildweekfinal.exceptions.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/Bill")
public class BillController {
    @Autowired
    private BillService billService;

    @GetMapping
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Bill findById(@PathVariable UUID id)
    {
        return billService.findById(id);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Page<Bill> getBills(@RequestParam(defaultValue="0") int page,
                               @RequestParam(defaultValue="10")int size,
                               @RequestParam(defaultValue="id")String orderBy){
        return billService.getBills(page,size,orderBy);
    }
    @PostMapping
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.CREATED)
    public BillsResponseDTO saveBill(@RequestBody @Validated BillsDTO body, BindingResult validation){
        if (validation.hasErrors()) {
            throw new BadRequestException(validation.getAllErrors());
        }else {
            Bill newBill=billService.save(body);
            return new BilllsResponseDTO(newBill.getIdBill());
        }
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void findByIdAndDelete(@PathVariable UUID id){
        billService.findByIDAndDelete(id);
    }



}
