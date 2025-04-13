package com.poly.controller;

import com.poly.dao.SinhVienDAO;
import com.poly.model.SinhVien;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/admin/sinhvien")
public class SinhVienController {

    @Autowired
    private SinhVienDAO sinhVienDAO;

    @GetMapping
    public String listSinhVien(Model model) {
        model.addAttribute("sinhVien", new SinhVien());
        model.addAttribute("sinhViens", sinhVienDAO.findAll());
        return "sinhvien/index";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute SinhVien sinhVien) {
        sinhVienDAO.save(sinhVien);
        return "redirect:/admin/sinhvien";
    }

    @GetMapping("/edit/{masv}")
    public String edit(@PathVariable String masv, Model model) {
        Optional<SinhVien> sinhVien = sinhVienDAO.findById(masv);
        sinhVien.ifPresent(value -> model.addAttribute("sinhVien", value));
        model.addAttribute("sinhViens", sinhVienDAO.findAll());
        return "sinhvien/index";
    }

    @GetMapping("/delete/{masv}")
    public String delete(@PathVariable String masv) {
        sinhVienDAO.deleteById(masv);
        return "redirect:/admin/sinhvien";
    }
}
