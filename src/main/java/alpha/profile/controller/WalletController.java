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
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class WalletController {
    @Autowired
    private WalletServices walletService;

    @GetMapping("/wallet")
    public List<Wallet> getAllWallets() {
        return walletService.getAllWallet();
    }


    @GetMapping("/wallet/{userId}")
    public List<Wallet> getAllWalletsByUserId(@PathVariable("userId")String userId) {
        try {
            return walletService.getWalletsByUserID( userId );
        } catch (WalletNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,e.getMessage());
        }
    }

    @GetMapping("/wallet/{userId}/{walletid}")
    public Wallet getWalletByUserIdWalletId(@PathVariable("userId")String userId, @PathVariable("walletid")String walletId) throws WalletNotFoundException {
        return walletService.getWalletByUserIdWalletId(userId, walletId );
    }

    @PostMapping("/wallet")
    public Wallet createWallet( @RequestBody Wallet wallet) {
        return walletService.createWallet(wallet);
    }


    @PutMapping("/wallet/{userId}/{walletId}")
    public Wallet updateWalletByUserIdWalletId(@PathVariable("userId") String userId,@PathVariable("walletId") String walletId,@RequestBody Wallet wallet) throws WalletNotFoundException{
        return walletService.updateWalletByUserIdWalletId(userId,walletId, wallet);
    }


    //delete a particular address of a user taking their userId and the particular addressId
    @DeleteMapping("/wallet/{userId}/{walletId}")
    public void deleteWalletByUserIdWalletId(@PathVariable("userId") String userId,@PathVariable("walletId") String walletId) throws WalletNotFoundException{
        walletService.deleteWalletByUserIdWalletId(userId, walletId);
    }
}