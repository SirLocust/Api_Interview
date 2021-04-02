package com.devsirlocust.casotech.auth;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.jackson2.SimpleGrantedAuthorityMixin;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;

public class JWTAuthorizationFIlter extends BasicAuthenticationFilter {

  public JWTAuthorizationFIlter(AuthenticationManager authenticationManager) {
    super(authenticationManager);
  }

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
      throws IOException, ServletException {

    String header = request.getHeader("Authorization");

    if (!requiresAuthentication(header)) {
      chain.doFilter(request, response);
      return;
    }
    boolean validToken;
    Claims token = null;
    try {
      token = Jwts.parserBuilder().setSigningKey(JWTAuthenticationFilter.SECRET_KEY).build()
          .parseClaimsJws(header.replace("Bearer ", "")).getBody();
      validToken = true;
    } catch (JwtException | IllegalArgumentException e) {
      e.printStackTrace();
      validToken = false;
    }

    UsernamePasswordAuthenticationToken authentication = null;
    if (validToken) {
      Object roles = token.get("authorities");

      Collection<? extends GrantedAuthority> authoritie = Arrays
          .asList(new ObjectMapper().addMixIn(SimpleGrantedAuthority.class, SimplenGrantedAuthoritiesMixin.class)
              .readValue(roles.toString().getBytes(), SimpleGrantedAuthority[].class));
      authentication = new UsernamePasswordAuthenticationToken(token.getSubject(), null, authoritie);
    }

    SecurityContextHolder.getContext().setAuthentication(authentication);
    chain.doFilter(request, response);

  }

  protected boolean requiresAuthentication(String header) {
    if (header == null || !header.startsWith("Bearer ")) {
      logger.info('s');

      return false;
    }
    return true;
  }

}
