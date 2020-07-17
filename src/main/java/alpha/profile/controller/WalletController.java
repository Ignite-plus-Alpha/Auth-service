package alpha.profile.controller;

import alpha.profile.exceptions.WalletNotFoundException;
import alpha.profile.model.Wallet;
import alpha.profile.services.WalletServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;


import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class WalletController {
    @Autowired
    private WalletServices walletService;

    @GetMapping("/wallet")
    public List<Wallet> getAllWallet() {
        return walletService.getAllWallet();
    }

    @GetMapping("/wallet/{walletId}")
    public Optional<Wallet> getWalletById(@PathVariable("walletId")String walletId) {
        try {
            return walletService.getWalletById( walletId );
        } catch (WalletNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,e.getMessage());
        }
    }

    @PostMapping("/wallet")
    public Wallet createWallet( @RequestBody Wallet wallet) {
        return walletService.createWallet(wallet);
    }

    @DeleteMapping("/wallet/{walletId}")
    public void deleteWalletById(@PathVariable("walletId") String walletId) throws WalletNotFoundException {
        walletService.deleteWalletById(walletId);
    }

    @PutMapping("/wallet/{walletId}")
    public Wallet updateWalletByIdById(@PathVariable("walletId") String walletId,@RequestBody String wallet) throws WalletNotFoundException{
        return walletService.updateWalletById(walletId, wallet);
    }
}
