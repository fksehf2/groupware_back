package com.group.groupware.controller;

import com.group.groupware.dto.User;
import com.group.groupware.repository.UserDAO;
import com.group.groupware.security.UnauthorizedException;
import com.group.groupware.security.jwtService;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class UserController {

    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    private final UserDAO userDAO;

    private final jwtService jwtService;


    public UserController(UserDAO userDAO, jwtService jwtService) {
        this.userDAO = userDAO;
        this.jwtService = jwtService;
    }


    @RestControllerAdvice
    public class GlobalExceptionHandler {

        @ExceptionHandler(Exception.class)
        public ResponseEntity<String> handleException(Exception e) {
            log.error("Exception: ", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .header(HttpHeaders.CONTENT_TYPE, "application/json")
                    .body("{\"error\":\"" + e.getMessage() + "\"}");
        }
    }

    @PostMapping("/login-jwt")
    @CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true", methods = RequestMethod.POST)
    public ResponseEntity<Map<String, Object>> Login(@RequestBody User params) throws Exception {
        log.info("Received parameter: {}", params.getUSER_ID());
        log.info("Received parameter: {}", params.getPWD());

        Map<String, Object> res = new HashMap<>();

        String token = null;
        User userChk = userDAO.LoginChk(params);
        System.out.println("loginCHk: {}" + userChk);
        if (userChk == null)
            new UnauthorizedException();
        else {
            token = jwtService.createToken(userChk.getUSER_ID() + "", (60 * 1000 * 60));
            System.out.println(token);
            res.put("token", token);
            res.put("id", userChk.getUSER_ID());
        }
        return ResponseEntity.ok(res);
    }
}
