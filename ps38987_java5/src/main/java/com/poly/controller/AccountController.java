package com.poly.controller;

import com.poly.dao.AccountDAO;
import com.poly.model.Account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/admin/accounts")
public class AccountController {

    @Autowired
    private AccountDAO accountDAO;
    @GetMapping("/")
    public String showLoginPage() {
        return "index";
    }
    @GetMapping
    public String list(@RequestParam(value = "id", required = false) String id, Model model) {
        if (id != null) {
            Optional<Account> acc = accountDAO.findById(id);
            acc.ifPresent(account -> model.addAttribute("account", account));
        } else {
            model.addAttribute("account", new Account());
        }
        model.addAttribute("accounts", accountDAO.findAll());
        return "account/index";
    }


    @PostMapping("/save")
    public String save(@ModelAttribute Account account) {
        accountDAO.save(account);
        return "redirect:/admin/accounts";
    }

    @GetMapping("/edit/{username}")
    public String edit(@PathVariable String username, Model model) {
        Optional<Account> acc = accountDAO.findById(username);
        acc.ifPresent(account -> model.addAttribute("account", account));
        model.addAttribute("accounts", accountDAO.findAll());
        return "account/index";
    }

    @GetMapping("/delete/{username}")
    public String delete(@PathVariable String username) {
        accountDAO.deleteById(username);
        return "redirect:/admin/accounts";
    }
    
  
}
