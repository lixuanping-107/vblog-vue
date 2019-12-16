package org.neuedu.vblog.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.neuedu.vblog.model.RespBean;
import org.neuedu.vblog.model.User;
import org.neuedu.vblog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.*;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Configuration
public class UserConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    UserService userService;
    @Autowired
    MyFilter myFilter;
    @Autowired
    Access access;

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/login");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .withObjectPostProcessor(new ObjectPostProcessor<FilterSecurityInterceptor>() {
                    @Override
                    public <O extends FilterSecurityInterceptor> O postProcess(O o) {
                        o.setAccessDecisionManager(access);
                        o.setSecurityMetadataSource(myFilter);
                        return o;
                    }
                })
                .and()
                .formLogin()
                .loginPage("/login")
                .loginProcessingUrl("/doLogin")
                .usernameParameter("username")
                .passwordParameter("password")
                .successHandler(new AuthenticationSuccessHandler() {
                    @Override
                    public void onAuthenticationSuccess(HttpServletRequest req, HttpServletResponse resp, Authentication authentication) throws IOException, ServletException {
                        resp.setContentType("application/json;charset=utf-8");
                        User user = (User) authentication.getPrincipal();
                        user.setPassword(null);
                        RespBean ok = RespBean.ok("登陆成功", user);
                        PrintWriter writer = resp.getWriter();
                        writer.print(new ObjectMapper().writeValueAsString(ok));
                        writer.flush();
                        writer.close();
                    }
                })
                .failureHandler(new AuthenticationFailureHandler() {
                    @Override
                    public void onAuthenticationFailure(HttpServletRequest req, HttpServletResponse resp, AuthenticationException e) throws IOException, ServletException {
                        resp.setContentType("application/json;charset=utf-8");
                        RespBean error = null;
                        if (e instanceof BadCredentialsException) {
                            error = RespBean.error("用户名或密码错误，请重新登录");
                        } else if (e instanceof LockedException) {
                            error = RespBean.error("账户被锁定，请联系管理员");
                        } else if (e instanceof AccountExpiredException) {
                            error = RespBean.error("账户已过期，请联系管理员");
                        } else if (e instanceof CredentialsExpiredException) {
                            error = RespBean.error("账户密码过期，请联系管理员");
                        } else if (e instanceof DisabledException) {
                            error = RespBean.error("账户被禁用，请联系管理员");
                        } else {
                            error = RespBean.error("未知错误");
                        }
                        PrintWriter writer = resp.getWriter();
                        writer.print(new ObjectMapper().writeValueAsString(error));
                        writer.flush();
                        writer.close();
                    }
                })
                .permitAll()
                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessHandler(new LogoutSuccessHandler() {
                    @Override
                    public void onLogoutSuccess(HttpServletRequest req, HttpServletResponse resp, Authentication authentication) throws IOException, ServletException {
                        resp.setContentType("application/json;charset=utf-8");
                        RespBean ok = RespBean.ok("注销成功");
                        PrintWriter writer = resp.getWriter();
                        writer.print(new ObjectMapper().writeValueAsString(ok));
                        writer.flush();
                        writer.close();
                    }
                })
                .permitAll()
                .and()
                .csrf().disable();
    }
}
