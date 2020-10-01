package alpha.profile.controller;

import alpha.profile.exceptions.AddressNotFoundException;
import alpha.profile.exceptions.MaxLimitReached;
import alpha.profile.exceptions.WalletNotFoundException;
import alpha.profile.model.Address;
import alpha.profile.model.Wallet;
import alpha.profile.services.WalletServices;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;


import java.util.List;
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class WalletController {


    private final WalletServices walletService;

    public WalletController(WalletServices walletService) {
        this.walletService = walletService;
    }

    @Value("${limit.max}")
    private int max;

    @ApiOperation(value = "get all wallets in db")
    @GetMapping("/wallet")
    public List<Wallet> getAllWallets() {
        return walletService.getAllWallet();
    }

    @ApiOperation(value = "get all wallets of a user by their userId")
    @GetMapping("/wallet/{userId}")
    public List<Wallet> getAllWalletsByUserId(@PathVariable("userId")String userId) {
        try {
            return walletService.getWalletsByUserID( userId );
        } catch (WalletNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,e.getMessage());
        }
    }
    @ApiOperation(value = "get particular wallet of a user by their userId and wallet id")
    @GetMapping("/wallet/{userId}/{walletid}")
    public Wallet getWalletByUserIdWalletId(@PathVariable("userId")String userId, @PathVariable("walletid")String walletId) throws WalletNotFoundException {
        return walletService.getWalletByUserIdWalletId(userId, walletId );
    }

    @ApiOperation(value = "create a wallet")
    @PostMapping("/wallet")
    public Wallet createWallet( @RequestBody Wallet wallet) throws MaxLimitReached {
        String userId=wallet.getUserid();
        if(getAllWalletsByUserId(userId).size()==max) throw new MaxLimitReached("can not add more than 5 wallet");

        return walletService.createWallet(wallet);
    }

    @ApiOperation(value = "update particular wallet of a user by their userId and wallet id")
    @PutMapping("/wallet/{userId}/{walletId}")
    public Wallet updateWalletByUserIdWalletId(@PathVariable("userId") String userId,@PathVariable("walletId") String walletId,@RequestBody Wallet wallet) throws WalletNotFoundException{
        return walletService.updateWalletByUserIdWalletId(userId,walletId, wallet);
    }


    //delete a particular address of a user taking their userId and the particular addressId
    @ApiOperation(value = "delete particular wallet of a user by their userId and wallet id")
    @DeleteMapping("/wallet/{userId}/{walletId}")
    public void deleteWalletByUserIdWalletId(@PathVariable("userId") String userId,@PathVariable("walletId") String walletId) throws WalletNotFoundException{
        walletService.deleteWalletByUserIdWalletId(userId, walletId);
    }
}