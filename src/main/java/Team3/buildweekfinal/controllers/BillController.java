package Team3.buildweekfinal.controllers;

import Team3.buildweekfinal.entities.Bill;
import Team3.buildweekfinal.exceptions.BadRequestException;
import Team3.buildweekfinal.payloads.BillDTO;
import Team3.buildweekfinal.payloads.BillResponseDTO;
import Team3.buildweekfinal.services.BillService;
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

    @GetMapping("/{idBill}")
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Bill findById(@PathVariable UUID id)
    {
        return billService.findById(id);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Page<Bill> getBills(@RequestParam(defaultValue="0") int page,
                               @RequestParam(defaultValue="10")int size,
                               @RequestParam(defaultValue="idBill")String orderBy){
        return billService.getBill(page,size,orderBy);
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Bill saveBill(@RequestBody BillDTO body,@PathVariable UUID id, BindingResult validation)
    {
        if (validation.hasErrors()) {
            throw new BadRequestException(validation.getAllErrors());
        }else {
            return billService.save(body,id);
        }
    }
    @PutMapping
    public Bill FindByIdAndUpdate(@PathVariable UUID id,@RequestBody BillDTO billDTOPayloads)
    {
        return billService.findByIdAndUpdate(id,billDTOPayloads);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void findByIdAndDelete(@PathVariable UUID id){
        billService.findByIdAndDelete(id);
    }
}
