package com.group.groupware.controller;

import com.group.groupware.dto.User;
import com.group.groupware.repository.UserDAO;
import com.group.groupware.security.UnauthorizedException;
import com.group.groupware.security.jwtService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
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

    /**
     *
     * <pre>
     * 1. 메소드명 : Login
     * 2. 작성일 : 2024. 07. 30.
     * 3. 작성자 : seran
     * 4. 설명 : 로그인 컨트롤러
     * </pre>
     * @param params
     * @return ResponseEntity
     * @throws Exception
     */
    @PostMapping("/login-jwt")
    @CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true", methods = RequestMethod.POST)
    public ResponseEntity<User> Login(@RequestBody User params) throws Exception {
        log.info("Received parameter: {}", params.getUSER_ID());
        log.info("Received parameter: {}", params.getPWD());

        User resultVO = new User();

        String token = null;
        List<User> userChk = userDAO.LoginChk(params);
        System.out.println("loginCHk: {}" + userChk.toString());
        if (userChk == null)
            new UnauthorizedException();
        else {
            User user = userChk.get(0);
            token = jwtService.createToken(user.getUSER_ID() + "", (60 * 1000 * 60));
            System.out.println(token);
            resultVO.setUSER_ID(user.getUSER_ID());
            resultVO.setTOKEN(token);
            resultVO.setREG_STATUS("200");
            resultVO.setUSER_NM(user.getUSER_NM());
        }
        return ResponseEntity.ok(resultVO);
    }
    /**
     *
     * <pre>
     * 1. 메소드명 : Login
     * 2. 작성일 : 2024. 07. 30.
     * 3. 작성자 : seran
     * 4. 설명 : 로그아웃 컨트롤러
     * </pre>
     * @param
     * @return ResponseEntity
     * @throws Exception
     */
    @GetMapping ("/logout")
    @CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true", methods = RequestMethod.GET)
    public ResponseEntity<Map<String, String>> LogOut (){
        log.info("LogOut method");
        Map<String, String> res = new HashMap<>();
        res.put("resultCode", "200");
        return ResponseEntity.ok(res);

    }
}
