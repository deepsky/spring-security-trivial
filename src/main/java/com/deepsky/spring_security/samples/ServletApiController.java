/*
 * Copyright 2002-2012 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */
package com.deepsky.spring_security.samples;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.Principal;

/**
 * A Spring MVC Controller that demonstrates Spring Security's integration with the standard Servlet API's. Specifically
 * it demonstrates the following:
 * <ul>
 * {@link javax.servlet.http.HttpServletRequest#authenticate(javax.servlet.http.HttpServletResponse)}</li>
 * {@link javax.servlet.http.HttpServletRequest#login(String, String)}</li>
 * <li>{@link #remoteUser(javax.servlet.http.HttpServletRequest)} - Integration with {@link javax.servlet.http.HttpServletRequest#getRemoteUser()}</li>
 * <li>{@link #userPrincipal(javax.servlet.http.HttpServletRequest)} - Integration with {@link javax.servlet.http.HttpServletRequest#getUserPrincipal()}</li>
 * <li>{@link #authentication(org.springframework.security.core.Authentication)} - Spring MVC's ability to resolve the {@link org.springframework.security.core.Authentication} since it is
 * found on {@link javax.servlet.http.HttpServletRequest#getUserPrincipal()}</li>
 * </ul>
 *
 * @author Rob Winch
 *
 */
@Controller
public class ServletApiController {

    /**
     * Demonstrates that invoking {@link javax.servlet.http.HttpServletRequest#logout()} will log the user out. Note that the response does
     * not get processed, so you need to write something to the response.
     * @param request
     * @param response
     * @param redirect
     * @return
     * @throws javax.servlet.ServletException
     */
    @RequestMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirect) throws ServletException {
        request.logout();
        return "redirect:/";
    }


    /**
     * Demonstrates that Spring Security automatically populates {@link javax.servlet.http.HttpServletRequest#getRemoteUser()} with the
     * current username.
     * @param request
     * @return
     */
    @ModelAttribute("remoteUser")
    public String remoteUser(HttpServletRequest request) {
        return request.getRemoteUser();
    }

    /**
     * Demonstrates that Spring Security automatically populates {@link javax.servlet.http.HttpServletRequest#getUserPrincipal()} with the
     * {@link org.springframework.security.core.Authentication} that is present on {@link org.springframework.security.core.context.SecurityContextHolder#getContext()}
     * @param request
     * @return
     */
    @ModelAttribute("userPrincipal")
    public Principal userPrincipal(HttpServletRequest request) {
        return request.getUserPrincipal();
    }

    /**
     * Spring MVC will automatically resolve any object that implements {@link java.security.Principal} using
     * {@link javax.servlet.http.HttpServletRequest#getUserPrincipal()}. This means you can easily resolve the {@link org.springframework.security.core.Authentication} just
     * by adding it as an argument to your MVC controller. Alternatively, you could also have an argument of type
     * {@link java.security.Principal} which would not couple your controller to Spring Security.
     * @param authentication
     * @return
     */
    @ModelAttribute
    public Authentication authentication(Authentication authentication) {
        return authentication;
    }

    @RequestMapping("/")
    public String welcome(HttpServletRequest request) {
        return "redirect:/login";
    }

    @RequestMapping("/homepage")
    public String homepage(HttpServletRequest request) {
        return "homepage";
    }

    @RequestMapping("/admin/console")
    public String console(HttpServletRequest request) {
        return "/admin/console";
    }

    @RequestMapping("/mod/moderator")
    public String moderator(HttpServletRequest request) {
        return "/mod/moderator";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(@ModelAttribute LoginForm loginForm, HttpServletRequest request) {
        Principal principal = request.getUserPrincipal();
        if(principal != null){
            System.out.println("User principal: " + principal.getName());
        } else {
            System.out.println("Principal is null");
        }

        return "login1";
    }


    /**
     * Demonstrate handing of Access Denied which may happen if user has not appropriate role
     * @param user
     * @return
     */
    @RequestMapping(value = "/403", method = RequestMethod.GET)
    public ModelAndView accessDenied(Principal user) {

        ModelAndView model = new ModelAndView();
        if (user != null) {
            model.addObject("msg", "Hi " + user.getName()
                    + ", you do not have permission to access this page!");
        } else {
            model.addObject("msg",
                    "You do not have permission to access this page!");
        }

        model.setViewName("403");
        return model;
    }

}