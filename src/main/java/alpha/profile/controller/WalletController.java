package alpha.profile.controller;

import alpha.profile.exceptions.AddressNotFoundException;
import alpha.profile.exceptions.WalletNotFoundException;
import alpha.profile.model.Address;
import alpha.profile.model.Wallet;
import alpha.profile.services.WalletServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;


import java.util.List;

@RestController
@RequestMapping("/api")
public class WalletController {
    @Autowired
    private WalletServices walletService;

    @GetMapping("/wallet")
    public List<Wallet> getAllWallets() {
        return walletService.getAllWallet();
    }


    @GetMapping("/wallet/{emailId}")
    public List<Wallet> getAllWalletsByEmail(@PathVariable("emailId")String emailId) {
        try {
            return walletService.getWalletsByEmail( emailId );
        } catch (WalletNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,e.getMessage());
        }
    }

    @GetMapping("/wallet/{emailid}/{walletid}")
    public Wallet getWalletByEmailIdWalletId(@PathVariable("emailid")String emailId, @PathVariable("walletid")String walletId) throws WalletNotFoundException {
        return walletService.getWalletByEmailIdWalletId(emailId, walletId );
    }

    @PostMapping("/wallet")
    public Wallet createWallet( @RequestBody Wallet wallet) {
        return walletService.createWallet(wallet);
    }

//    @DeleteMapping("/wallet/{walletId}")
//    public void deleteWalletById(@PathVariable("walletId") String walletId) throws WalletNotFoundException {
//        walletService.deleteWalletById(walletId);
//    }

    @PutMapping("/wallet/{emailId}/{walletId}")
    public Wallet updateWalletByEmailIdWalletId(@PathVariable("emailId") String emailId,@PathVariable("walletId") String walletId,@RequestBody Wallet wallet) throws WalletNotFoundException{
        return walletService.updateWalletByEmailIdWalletId(emailId,walletId, wallet);
    }


    //delete a particular address of a user taking their emailId and the particular addressId
    @DeleteMapping("/wallet/{emailId}/{walletId}")
    public void deleteWalletByEmailIdWalletId(@PathVariable("emailId") String emailId,@PathVariable("walletId") String walletId) throws WalletNotFoundException{
        walletService.deleteWalletByEmailIdWalletId(emailId, walletId);
    }
}