package com.fz.onlineshoestore.service;

import com.fz.onlineshoestore.model.Shoe;
import com.fz.onlineshoestore.model.UserObj;
import com.fz.onlineshoestore.repository.ShoeRepository;
import com.fz.onlineshoestore.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class UserService {
    UserRepository userRepository;
    ShoeRepository shoeRepository;

    public UserService(UserRepository userRepository, ShoeRepository shoeRepository) {
        this.shoeRepository = shoeRepository;
        this.userRepository = userRepository;
    }

    ////
    // متد برای دریافت کاربر جاری از Spring Security
    public Object getUser() throws Exception {
        try {
            Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

            if (principal instanceof UserDetails) {
                Object user = ((UserDetails) principal);
                return user;
            } else {
                Object user = principal;
                return user;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // متد برای اضافه کردن یک کفش به کاربر
    public String addShoe(Long shoeId, String userName) {
        try {
            // دریافت شیء کاربر با نام کاربری
            UserObj userObj = userRepository.findByUsername(userName);

            if (userObj != null) {
                // اگر کاربر یافت شد
                List<Shoe> shoesList = userObj.getShoes();
                // یافتن شیء کفش با استفاده از شناسه
                Optional<Shoe> shoeObj = shoeRepository.findById(shoeId);
                if (shoeObj.isPresent()) {
                    // اگر کفش یافت شد
                    // اضافه کردن کفش به لیست کفش‌های کاربر
                    shoesList.add(shoeObj.get());
                    // تنظیم لیست کفش‌های جدید برای کاربر
                    userObj.setShoes(shoesList);
                    // ذخیره کاربر در دیتابیس
                    userRepository.save(userObj);
                    log.info("Shoe added successfully: id = " + shoeId + ", name = " + shoeObj.get().getName());
                    return ("Shoe added successfully: id = " + shoeId + ", name = " + shoeObj.get().getName());
                } else {
                    // اگر کفش پیدا نشد
                    return ("Shoe not found :") + shoeId;
                }
            } else {
                // اگر کاربر پیدا نشد
                return "User not found";
            }
        } catch (Exception e) {
            // در صورت بروز خطا، پرتاب استثنا
            throw new RuntimeException(e);
        }
    }

    public String removeShoe(Long shoeId, String userName) {
        try {
            // دریافت شیء کاربر با نام کاربری
            UserObj userObj = userRepository.findByUsername(userName);

            if (userObj != null) {
                // اگر کاربر یافت شد
                List<Shoe> shoesList = userObj.getShoes();
                // یافتن شیء کفش با استفاده از شناسه
                Optional<Shoe> shoeObj = shoeRepository.findById(shoeId);
                // اگر کفش یافت شد
                if (shoeObj.isPresent()) {

                    // حذف کردن کفش به از کفش‌های کاربر
                    shoesList.remove(shoeObj.get());
                    // ذخیره کاربر در دیتابیس
                    userRepository.save(userObj);
                    log.info("Shoe removed successfully: id = " + shoeId + ", name = " + shoeObj.get().getName());
                    return ("Shoe removed successfully: id = " + shoeId + ", name = " + shoeObj.get().getName());
                } else {
                    // اگر کفش پیدا نشد
                    return ("Shoe not found :") + shoeId;
                }
            } else {
                // اگر کاربر پیدا نشد
                return "User not found";
            }
        } catch (Exception e) {
            // در صورت بروز خطا، پرتاب استثنا
            throw new RuntimeException(e);
        }
    }

    public List<Shoe> getShoes(String currentUser) {
       UserObj userObj =  userRepository.findByUsername(currentUser);
       List<Shoe> shoes = ((UserObj) userObj).getShoes();
       return shoes;
    }
}

