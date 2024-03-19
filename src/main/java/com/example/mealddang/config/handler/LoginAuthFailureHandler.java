package com.example.mealddang.config.handler;

import java.io.IOException;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import com.example.mealddang.config.constant.AuthenticationTypes;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@Component
public class LoginAuthFailureHandler extends SimpleUrlAuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        exception.printStackTrace();

        // HttpSession을 통해 에러 메시지 전달
        HttpSession session = request.getSession();
        AuthenticationTypes authenticationTypes = AuthenticationTypes.valueOf(exception.getClass().getSimpleName());
        String errorMessage = authenticationTypes.getMsg();
        int code = authenticationTypes.getCode();
        log.error("message: "+errorMessage+" / code: "+code);

        // 한글 인코딩 깨짐 문제 방지를 위해 URLEncoder 사용하지 않고, 세션을 사용합니다.
        session.setAttribute("authenticationErrorMessage", errorMessage);
        session.setAttribute("authenticationErrorCode", code);

        // 기본 실패 URL 설정 ("/loginform"에 에러 메시지를 쿼리 파라미터로 전달하지 않음)
        setDefaultFailureUrl("/loginform");

        super.onAuthenticationFailure(request, response, exception);
    }
}

// @Slf4j
// @Component
// public class LoginAuthFailureHandler extends SimpleUrlAuthenticationFailureHandler {
//     @Override
//     public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
//         exception.printStackTrace();
//         writePrintErrorResponse(response, exception);
//         super.onAuthenticationFailure(request, response, exception);
//     }
    
//     private void writePrintErrorResponse(HttpServletResponse response, AuthenticationException exception) throws IOException {

//         AuthenticationTypes authenticationTypes = AuthenticationTypes.valueOf(exception.getClass().getSimpleName());
//         String errorMessage = authenticationTypes.getMsg();
//         int code = authenticationTypes.getCode();
//         log.error("message: "+errorMessage+" / code: "+code);
        
//         errorMessage = URLEncoder.encode(errorMessage, "UTF-8"); /* 한글 인코딩 깨짐 문제 방지 */
//         setDefaultFailureUrl("/loginform?errorMessage="+errorMessage);
//     }
// }
