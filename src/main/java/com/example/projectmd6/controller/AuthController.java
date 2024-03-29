package com.example.projectmd6.controller;

import com.example.projectmd6.dto.request.ChangeAvatar;
import com.example.projectmd6.dto.request.SignInForm;
import com.example.projectmd6.dto.request.SignUpForm;
import com.example.projectmd6.dto.response.JwtResponse;
import com.example.projectmd6.dto.response.ResponMessage;
import com.example.projectmd6.model.Post;
import com.example.projectmd6.model.Role;
import com.example.projectmd6.model.RoleName;
import com.example.projectmd6.model.Users;
import com.example.projectmd6.security.jwt.JwtProvider;
import com.example.projectmd6.security.jwt.JwtTokenFilter;
import com.example.projectmd6.security.userprincal.UserPrinciple;
import com.example.projectmd6.service.IPostService;
import com.example.projectmd6.service.impl.RoleServiceIpml;
import com.example.projectmd6.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.HashSet;
import java.util.Set;

@RestController
@CrossOrigin(origins = "*")
public class AuthController {
    @Autowired
    UserServiceImpl userService;
    @Autowired
    RoleServiceIpml roleService;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    JwtProvider jwtProvider;
    @Autowired
    JwtTokenFilter jwtTokenFilter;
    @PostMapping("/signup")
    public ResponseEntity<?> register(@Valid @RequestBody SignUpForm signUpForm){
         if(userService.existsByUsername(signUpForm.getUsername())){
            return new ResponseEntity<>(new ResponMessage("no_user"), HttpStatus.OK);
        }
        if(userService.existsByEmail(signUpForm.getEmail())){
            return new ResponseEntity<>(new ResponMessage("no_email"), HttpStatus.OK);
        }
        if(signUpForm.getAvatar() == null || signUpForm.getAvatar().trim().isEmpty()){
            signUpForm.setAvatar("https://img.thuthuatphanmem.vn/uploads/2018/09/19/avatar-facebook-chat-13_105604286.jpg");
        }
        Users user = new Users(signUpForm.getName(), signUpForm.getUsername(), signUpForm.getEmail(),signUpForm.getAvatar(),passwordEncoder.encode(signUpForm.getPassword()));
        Set<String> strRoles = signUpForm.getRoles();
        Set<Role> roles = new HashSet<>();
        strRoles.forEach(role ->{
            switch (role){
                case "admin":
                    Role adminRole = roleService.findByName(RoleName.ADMIN).orElseThrow(
                            ()-> new RuntimeException("Role not found")
                    );
                    roles.add(adminRole);
                    break;
                case "pm":
                    Role pmRole = roleService.findByName(RoleName.PM).orElseThrow( ()-> new RuntimeException("Role not found"));
                    roles.add(pmRole);
                    break;
                default:
                    Role userRole = roleService.findByName(RoleName.USER).orElseThrow( ()-> new RuntimeException("Role not found"));
                    roles.add(userRole);
            }
        });
        user.setRoles(roles);
        userService.save(user);
        return new ResponseEntity<>(new ResponMessage("Create Success!"), HttpStatus.OK);
    }
    @PostMapping("/signin")
    public ResponseEntity<?> login(@Valid @RequestBody SignInForm signInForm){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(signInForm.getUsername(), signInForm.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtProvider.createToken(authentication);
        UserPrinciple userPrinciple = (UserPrinciple) authentication.getPrincipal();
        return ResponseEntity.ok(new JwtResponse(token, userPrinciple.getName(), userPrinciple.getId(),userPrinciple.getAvatar(), userPrinciple.getAuthorities()));
    }

    @PutMapping("/change-avatar")
    public ResponseEntity<?> changeAvatar(HttpServletRequest request, @Valid @RequestBody ChangeAvatar changeAvatar){
        String jwt = jwtTokenFilter.getJwt(request);
        String username = jwtProvider.getUserNameFromToken(jwt);
        Users user;
        try {
            if (changeAvatar.getAvatar()==null){
                return new ResponseEntity<>(new ResponMessage("no"), HttpStatus.OK);
            }else {
                user = userService.findByUsername(username).orElseThrow(()-> new UsernameNotFoundException("USer not found -> username " + username ));
                user.setAvatar(changeAvatar.getAvatar());
                userService.save(user);
            }
            return new ResponseEntity<>(new ResponMessage("yes"), HttpStatus.OK);
        }catch (UsernameNotFoundException exception){
            return new ResponseEntity<>(new ResponMessage(exception.getMessage()), HttpStatus.NOT_FOUND);
        }

    }


}
