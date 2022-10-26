package com.cybersoft.crm.services;

import com.cybersoft.crm.model.UsersModel;
import com.cybersoft.crm.repository.UsersRepository;

import java.util.List;
import java.util.regex.Pattern;

public class LoginService {

    UsersRepository usersRepository = new UsersRepository();

    public boolean checkValidEmail(String email) {
        String EMAIL_PATTERN = "^[a-zA-Z][\\w-]+@([\\w]+\\.[\\w]+|[\\w]+\\.[\\w]{2,}\\.[\\w]{2,})$";
        if (Pattern.matches(EMAIL_PATTERN, email) == true) return true;
        else return false;
    }

    public boolean checkLogin(String email, String password) {
        List<UsersModel> list = usersRepository.getUsersByEmailAndPassword(email, password);
        if (list.size() > 0) {
            //Đăng nhập thành công
            return true;
        } else {
            //Đăng nhập thất bại
            return false;
        }

    }

}
